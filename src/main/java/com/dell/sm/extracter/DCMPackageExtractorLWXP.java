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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author Ujjwal_Kumar_Gupta
 */
public class DCMPackageExtractorLWXP implements IDCMPackageExtractor {

    public class DupExeInputStream extends InputStream {

        public final int LOCALFILEHEADERMAGIC = 0x504B0304;
        public final int CDRHEADERMAGIC = 0x504B0102;
        public final int FILEHEADERMAGIC = 0x504B0506;
        boolean initialzed = false;
        long zipStartOffset = 0;
        RandomAccessFile raf = null;

        public DupExeInputStream(File file) {
            try {
                raf = new RandomAccessFile(file, "r");
            } catch (IOException ex) {
                Logger.getLogger(DCMPackageExtractorLWXP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        long findFileHeader(long length, int SIGNATURE) throws IOException {
            long readcount = 0;

            while (length > readcount++) {

                raf.seek(length - readcount);
                byte lastbyte = (byte) raf.read();

                if (lastbyte == (byte) (SIGNATURE & 0xFF)) {
                    // move 3 offset back and get the signature.
                    readcount += 3;
                    raf.seek(length - readcount);
                    if (raf.readInt() == SIGNATURE) {
                        //End of central directory record (EOCD)
                        return length - readcount;
                    }
                }
            }
            return -1;
        }

        long findFirstFileHeader(long finaloffset, int numberOfEntries, long cderoffset) throws IOException {
            if (!initialzed) {
                // set the file pointer at 0 position
                long cdr = finaloffset;
                int itemcount = 0;
                int totalcompression = 0;
                int[] compressionSizes = new int[numberOfEntries];
                while (cdr < cderoffset) {
                    raf.seek(cdr + 28);
                    int sizen = Short.reverseBytes(raf.readShort());
                    int sizem = Short.reverseBytes(raf.readShort());
                    int sizek = Short.reverseBytes(raf.readShort());
                    raf.seek(cdr + 20);
                    int compressionsize = Integer.reverseBytes(raf.readInt());
                    /*
                    // Debug data for debugging purpose
                    int totalsize = Integer.reverseBytes(raf.readInt());
                    raf.seek(cdr + 42);
                    int offset = Integer.reverseBytes(raf.readInt());
                    byte[] buffer = new byte[sizen];
                    raf.read(buffer);
                    String name = new String(buffer);
                    System.out.printf("%d)%X - [%X]%s[%d] - %X\n", itemcount + 1, cdr, offset, name, compressionsize, finaloffset - totalcompression);
                     */
                    totalcompression += compressionsize + sizen + sizem + sizek + 30;
                    int extra = sizem + sizek + sizen;

                    cdr += sizen + sizem + sizek + 46;
                    compressionSizes[itemcount] = compressionsize;
                    itemcount++;
                }
                long offset = finaloffset - totalcompression - (numberOfEntries * 149);
                if (offset > 0) {
                    raf.seek(offset);
                    if (raf.readInt() == LOCALFILEHEADERMAGIC) {
                        return offset;
                    }
                }
                long estimate = finaloffset;
                for (itemcount = numberOfEntries - 1; itemcount >= 0; itemcount--) {
                    estimate -= compressionSizes[itemcount];
                    if (estimate < 0) {
                        estimate += compressionSizes[itemcount];
                        break;
                    }
                    long newoffset = findFileHeader(estimate, LOCALFILEHEADERMAGIC);
                    System.out.printf("%d\t%X - [%X] diff=%d \n", itemcount, estimate, newoffset, estimate - newoffset);
                    estimate = newoffset;
                }
                if (estimate > 0) {
                    raf.seek(estimate);
                    if (raf.readInt() == LOCALFILEHEADERMAGIC) {
                        return estimate;
                    }
                }
            }
            return -1;
        }

        void skipToZipData() throws IOException {
            if (!initialzed) {
                // set the file pointer at 0 position
                long length = raf.length();
                //End of central directory record (EOCD)
                long cderoffset = findFileHeader(length, FILEHEADERMAGIC);
                if (cderoffset < 0) {
                    throw new IOException("Unable to find CDR final entry");
                }

                //Central directory file header 1st record
                raf.seek(cderoffset + 10);
                short cdrCount = Short.reverseBytes(raf.readShort());
                long sizeofallcdrs = Integer.reverseBytes(raf.readInt());
                long cdrstartoffset = Integer.reverseBytes(raf.readInt());

                int sign = 0;
                //Get Signature and check
                if (cdrstartoffset > 0 && cdrstartoffset < length) {
                    raf.seek(cdrstartoffset);
                    sign = raf.readInt();
                }

                long lfhoffset = -1;

                if (sign != CDRHEADERMAGIC) {
                    LOG.warning("Incorrect offset for CDRHEADERMAGIC in the Dup file, try to locate start of zip with CDR records.");
                    lfhoffset = findFirstFileHeader(cderoffset - sizeofallcdrs, cdrCount, cderoffset);
                } else {
                    //Local file header first record
                    raf.seek(cdrstartoffset + 42);
                    lfhoffset = Integer.reverseBytes(raf.readInt());
                }
                if (lfhoffset < 0) {
                    throw new IOException("Unable to find first zip entry");
                }
                raf.seek(lfhoffset);
                //Check magicnumber "PK\03\04"
                if (raf.readInt() != LOCALFILEHEADERMAGIC) {
                    throw new IOException("Invalid DUP file type");
                }
                LOG.log(Level.INFO, "LOCALFILEHEADERMAGIC @{0} located in the Dup file.", lfhoffset);
                initialzed = true;
                zipStartOffset = lfhoffset;
                raf.seek(zipStartOffset);
            }
        }

        @Override
        public int read() throws IOException {
            // Find zip magic number and skip all those bytes before.
            skipToZipData();
            return raf.read();
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            skipToZipData();
            return raf.read(b, off, len);
        }

        @Override
        public void close() {
            try {
                raf.close();
            } catch (IOException ex) {
                Logger.getLogger(DCMPackageExtractorLWXP.class.getName()).log(Level.SEVERE, null, ex);
            }
            initialzed = false;
        }

        public void clear() throws IOException {
            skipToZipData();
            raf.seek(zipStartOffset);
        }
    }

    /**
     *
     * @param dupfile : In format of .EXE
     */
    public DCMPackageExtractorLWXP(File dupfile) {
        mDupFile = dupfile;
        zipInStream = new DupExeInputStream(dupfile);
    }

    /**
     * Given LWXP file adds path of repository to list of repository paths
     *
     * @return DCMErrorCodes
     */
    @Override
    public String extractPackageXML() {
        ZipInputStream zin = null;
        try {
            zipInStream.clear();
            zin = new ZipInputStream(zipInStream);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.getName().toLowerCase().equals("package.xml")) {
                    int filesize = (int) ze.getSize();
                    byte[] buffer = new byte[filesize];

                    int len = zin.read(buffer);
                    while (len != filesize) {
                        buffer[len++] = (byte) zin.read();
                    }
                    String charset = "UTF-8";
                    if (buffer[0] == -1 && buffer[1] == -2) {
                        charset = "UTF-16LE";
                    }

                    String target = new String(buffer, java.nio.charset.Charset.forName(charset));
                    return target;
                }
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Method to extract the payload folder from DUP
     *
     * @param inDestination Folder to extract and save payload file
     * @return Success or Failure
     */
    @Override
    public int extractPayloadFile(String inDestination) {
        return extractFileContent("payload/(.*)", inDestination, null, null);
    }

    @Override
    public int extractPackageXMLFile(String inDestination) {
        return extractFileContent("package.xml(.*)", inDestination, null, null);
    }

    @Override
    public void close() {
        zipInStream.close();

    }

    /**
     * Method to get the payload files hash
     *
     * @param inDigest to calculate the hash of the file
     * @return Success or Failure
     */
    @Override
    public int getPayloadHash(MessageDigest inDigest) {
        Map<String, MessageDigest> mapHashes = new TreeMap<>();
        int status = extractFileContent("payload/(.*)", null, mapHashes, inDigest.getAlgorithm());
        LOG.info("Extracted hashes of all payload files.");
        if (status == DCMErrorCodes.SUCCESS) {
            try {
                String hash = DCMPackageHashofHash.getHashOfHash(mapHashes, inDigest);
                LOG.info("Hash of hash : " + hash);
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    private int extractFileContent(String inPattern, String inDestination, Map<String, MessageDigest> mapHashes, String digestAlgorithm) {

        ZipInputStream zin = null;
        try {
            File destDir = null;
            if (inDestination != null) {
                destDir = new File(inDestination);
                destDir.mkdirs();
            }
            zipInStream.clear();
            zin = new ZipInputStream(zipInStream);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.getName().matches(inPattern)) {
                    File filename = new File(destDir + File.separator + ze.getName());
                    if (!ze.getName().isEmpty() && !ze.getName().endsWith("/")) {
                        LOG.log(Level.INFO, "Payload File: {0}", ze.getName());

                        FileOutputStream out = null;
                        if (inDestination != null) {
                            filename.getParentFile().mkdirs();
                            out = new FileOutputStream(filename);
                        }
                        // create the message digest based on algorithm
                        MessageDigest digest = null;
                        if (mapHashes != null) {
                            digest = MessageDigest.getInstance(digestAlgorithm);
                            mapHashes.put(ze.getName(), digest);
                        }
                        byte[] buffer = new byte[BUFFER_SIZE];
                        int len;
                        while ((len = zin.read(buffer)) != -1) {
                            if (out != null) {
                                out.write(buffer, 0, len);
                            }
                            if (digest != null) {
                                digest.update(buffer, 0, len);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return DCMErrorCodes.SUCCESS;
    }

    private static final Logger LOG = Logger.getLogger(DSMGZipFileExtracter.class.getName());
    private static final int BUFFER_SIZE = 1024;

    private File mDupFile = null;
    private DupExeInputStream zipInStream = null;
}
