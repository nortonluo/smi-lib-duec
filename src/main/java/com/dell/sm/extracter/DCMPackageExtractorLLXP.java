/* 
 * Copyright 2017 Dell Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dell.sm.extracter;

import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author Ujjwal_Kumar_Gupta
 */
public class DCMPackageExtractorLLXP implements IDCMPackageExtractor {

    class DupTarInputStream extends InputStream {

        ByteBuffer buf;

        public DupTarInputStream(ByteBuffer buf) {
            this.buf = buf;
        }

        public int read() throws IOException {
            if (!buf.hasRemaining()) {
                return -1;
            }
            return buf.get() & 0xFF;
        }

        public int read(byte[] bytes, int off, int len)
                throws IOException {
            if (!buf.hasRemaining()) {
                return -1;
            }

            len = Math.min(len, buf.remaining());
            buf.get(bytes, off, len);
            return len;
        }
    }

    public DCMPackageExtractorLLXP(File dupFile) {
        mDupFile = dupFile;

    }

    private boolean initialize() {
        if (!mInitialized) {
            try {
                FileChannel channel = FileChannel.open(mDupFile.toPath(), StandardOpenOption.READ);
                mFileSize = (int) mDupFile.length();
                mFileBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, mFileSize);
                mManifestMarker = findLine(MANIFESTXMLMARKER);
                mArchiveMarker = findLine(ARCHIVEXMLMARKER);
                mInitialized = true;
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
        if (mInitialized) {
            mFileBuffer.clear();
        }
        return mInitialized;
    }

    private int findLine(String line) {
        int ip = -1;
        while (++ip < mFileSize) {
            StringBuilder search = new StringBuilder();
            // read each line 
            while (++ip < mFileSize) {
                char ch = (char) mFileBuffer.get();
                if (ch == '\r' || ch == '\n') {
                    if (search.length() == 0) {
                        continue;
                    } else {
                        break;
                    }
                }
                search.append(ch);
            }
            //System.out.println(search.toString());
            if (search.toString().equals(line)) {
                return mFileBuffer.position() - line.length();
            }
        }
        return -1;
    }

    byte[] getBlock(GZIPInputStream gzin) {
        try {
            byte[] content = new byte[BLOCKSIZE];
            int len = gzin.read(content);
            if (len == -1) {
                return null;
            }
            while (len != BLOCKSIZE) {
                content[len++] = (byte) gzin.read();
            }
            return content;
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Method to extract payload from the DUP BIN File
     *
     * @param inDestinationFile
     * @return Success of Failure
     */
    @Override
    public int extractPayloadFile(String inDestinationFile) {
        if (initialize()) {
            LOG.info("Extracting all payload files to " + inDestinationFile);
            return extractFileContent("payload/(.*)", inDestinationFile, null, null);
        }
        return DCMErrorCodes.FAILURE;
    }

    /**
     * Method to extract package.xml and sign from the DUP BIN File
     *
     * @param inDestinationFile
     * @return Success of Failure
     */
    @Override
    public int extractPackageXMLFile(String inDestinationFile) {
        if (initialize()) {
            LOG.info("Extracting package files to " + inDestinationFile);
            return extractFileContent("package.xml(.*)", inDestinationFile, null, null);
        }
        return DCMErrorCodes.FAILURE;
    }

    /**
     * Given LLXP file adds path of repository to list of repository paths
     *
     * @param inDigest : Hash based on the digest algorithm
     * @return DCMErrorCodes
     */
    @Override
    public int getPayloadHash(MessageDigest inDigest) {
        if (initialize()) {
            Map<String, MessageDigest> mapHashes = new TreeMap<>();
            int status = extractFileContent("payload/(.*)", null, mapHashes, inDigest.getAlgorithm());
            LOG.info("Extracted hashes of all payload files.");
            if (status == DCMErrorCodes.SUCCESS) {
                try {
                    String hash = DCMPackageHashofHash.getHashOfHash(mapHashes, inDigest);
                    LOG.info(hash);
                } catch (Exception ex) {
                    Logger.getLogger(DCMPackageExtractorLLXP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return DCMErrorCodes.FAILURE;
    }

    private int extractFileContent(String inPattern, String inDestinationFile, Map<String, MessageDigest> mapHashes, String digestAlgorithm) {

        mFileBuffer.position(mArchiveMarker + ARCHIVEXMLMARKER.length() + 1);
        try {
            InputStream gzFile = new DupTarInputStream(mFileBuffer.slice());
            LOG.log(Level.INFO, "Extracting tar from DUP/BIN");
            GZIPInputStream gzin = new GZIPInputStream(gzFile);
            byte[] header;
            try {
                // read header block in the tar.gz file
                while ((header = getBlock(gzin)) != null) {
                    String filename = new String(header, 0, 100);
                    filename = filename.substring(0, filename.indexOf('\0'));
                    if (filename.isEmpty()) {
                        continue;
                    }
                    long fileSize = parseOctal(header, DATAMARKER, 12);

                    // if file matches pattern write the output else just read all the file blocks.
                    if (filename.toLowerCase().matches(inPattern)) {
                        File outfile = (inDestinationFile == null) ? null : new File(inDestinationFile + File.separator + filename);
                        // Check if the file is a directory in the tar.gz
                        if (filename.endsWith("/")) {
                            if (outfile != null) {
                                outfile.mkdirs();
                            }
                            continue;
                        }
                        // create the message digest based on algorithm
                        MessageDigest digest = null;
                        if (mapHashes != null && digestAlgorithm != null) {
                            digest = MessageDigest.getInstance(digestAlgorithm);
                            mapHashes.put(filename, digest);
                        }
                        LOG.log(Level.INFO, "Writing/Hashing payload: {0}", filename);
                        writeContentToFile(gzin, fileSize, outfile, digest);
                    } else {
                        // just skip all the file blocks and move to the next header
                        long reminder = fileSize % BLOCKSIZE;
                        long skipSize = (reminder > 0) ? (fileSize - reminder + BLOCKSIZE) : fileSize;
                        gzin.skip(skipSize);
                    }
                }
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, "Failed extracting content from DUP", ex);
            } finally {
                gzin.close();
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return DCMErrorCodes.SUCCESS;
    }

    private void writeContentToFile(GZIPInputStream gzin, long fileSize, File outfile, MessageDigest digest) {
        FileOutputStream fos = null;
        BufferedOutputStream dest = null;
        try {
            if (outfile != null) {
                fos = new FileOutputStream(outfile);
            }
            try {
                // Open the outstream if we want to write the content.
                if (outfile != null) {
                    dest = new BufferedOutputStream(fos);
                }
                long sizeContent = fileSize;
                while (sizeContent > 0) {
                    byte[] content = getBlock(gzin);
                    if (content == null) {
                        break;
                    }
                    // Write the complete block or the partial content in the last block
                    if (sizeContent < BLOCKSIZE) {
                        // Write to output file 
                        if (outfile != null) {
                            dest.write(content, 0, (int) sizeContent);
                        }
                        // Calculate the hash 
                        if (digest != null) {
                            digest.update(content, 0, (int) sizeContent);
                        }
                    } else {
                        // Write to output file 
                        if (outfile != null) {
                            dest.write(content);
                        }
                        // Calculate the hash 
                        if (digest != null) {
                            digest.update(content);
                        }
                    }
                    sizeContent -= BLOCKSIZE;
                }
            } catch (IOException ex) {
                Logger.getLogger(DCMPackageExtractorLLXP.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DCMPackageExtractorLLXP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (dest != null) {
                    dest.flush();
                    dest.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(DCMPackageExtractorLLXP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Given LLXP file adds path of repository to list of repository paths
     *
     * @return String
     */
    @Override
    public String extractPackageXML() {
        if (initialize()) {
            int size = mArchiveMarker - mManifestMarker - MANIFESTXMLMARKER.length() - 2; // 2 eol characters
            byte[] buffer = new byte[size - 2]; // -2 to eliminate final eol
            mFileBuffer.position(mManifestMarker + MANIFESTXMLMARKER.length());
            mFileBuffer.get(buffer);
            String charset = "UTF-8";
            if (buffer[0] == -1 && buffer[1] == -2) {
                charset = "UTF-16LE";
            }

            String target = new String(buffer, java.nio.charset.Charset.forName(charset));
            LOG.info("Extracted package.xml with size " + target.length());
            return target;
        }
        return null;
    }

    //Convert octal base to long
    private long parseOctal(byte[] buffer, int offset, int length) {
        long result = 0;
        boolean stillPadding = true;
        int end = offset + length;
        for (int i = offset; i < end - 1; i++) {
            if (buffer[i] == '0') {
                if (stillPadding) {
                    continue;
                }
            }
            stillPadding = false;
            result = (result << 3) + (buffer[i] - '0');
        }
        return result;
    }

    /**
     * Clear the left over contents
     *
     */
    @Override
    public void close() {
        mFileBuffer = null;
        mInitialized = false;
        mFileSize = 0;
    }

    //Member variables and contants
    private final int BLOCKSIZE = 512;
    private final int DATAMARKER = 124;
    private static final Logger LOG = Logger.getLogger(DCMPackageExtractorLLXP.class
            .getName());
    private static final String MANIFESTXMLMARKER = "#####Startofpackage#####";
    private static final String ARCHIVEXMLMARKER = "#####Startofarchive#####";

    private int mArchiveMarker = 0;
    private int mManifestMarker = 0;

    File mDupFile;
    int mFileSize = 0;
    MappedByteBuffer mFileBuffer;
    boolean mInitialized = false;

}
