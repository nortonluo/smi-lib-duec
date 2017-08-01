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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

/**
 * Class for extracting the gzip files compressed using gzip compression format
 * which implements interface IDSMExtracter
 *
 * @author VIVEKANANDH_N_R
 */
public class DSMGZipFileExtracter implements IDSMExtracter {

    @Override
    /**
     * Method for extracting the gzip file format
     *
     * @param inputCompressedFile location of the gzip file with relative path
     * @param inputTargetLocation location of the directory in which the gzip
     * file to be uncompressed
     * @return returns success or failure (true/false)
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean extractFile(String inputCompressedFile, String outputTargetLocation) throws FileNotFoundException, IOException {
        //check whether the inputzip file is present
//        File inputFile = new File(inputCompressedFile);
//
//        if (!inputFile.exists()) {
//            throw new FileNotFoundException();
//        }
//        // file name minus the .gz extension
//        String outputFileName = inputFile.getName().substring(0, inputFile.getName().length() - 3);
//        LOG.log(Level.INFO, "outputFileName::" + outputFileName);
//
//        File outputFile = new File(outputTargetLocation, outputFileName);
//        // check whether the target location is writable
////       if(!outputFile.canWrite())
////       {
////           LOG.log(Level.SEVERE,"Unable to unzip the file as there is no write permission");
////           return false;
////       }
//        // create gzip input stream to read
//        // create a output stream to write to the target location
//        GZIPInputStream inputStream = new GZIPInputStream(new FileInputStream(inputFile));
//        FileOutputStream outputStream = new FileOutputStream(outputFile);
//        byte[] readBytes = new byte[ExtracterConstants.BUFF_SIZE];
//        while (inputStream.read(readBytes) != -1) {
//            LOG.log(Level.INFO, "Bytes read::" + readBytes.toString());
//            outputStream.write(readBytes);
//        }
//        //flush and close all the input and output streams.
//        outputStream.flush();
//        outputStream.close();
//        inputStream.close();

        return extractGZipFile(inputCompressedFile, outputTargetLocation);
    }

    public boolean extractGZipFile(String inputCompressedFile, String outputTargetLocation) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        BufferedReader reader = null;
        BufferedWriter writer = null;
        File inputFile = new File(inputCompressedFile);

        if (!inputFile.exists()) {
            throw new FileNotFoundException();
        }

        // file name minus the .gz extension
        String outputFileName = inputFile.getName().substring(0, inputFile.getName().length() - 3);
        LOG.log(Level.INFO, "outputFileName::" + outputFileName);

        File outputFile = new File(outputTargetLocation, outputFileName);

        reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(inputFile)), "UTF-16"));

        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-16"));

        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        writer.write(sb.toString());

        LOG.log(Level.INFO, "File Content" + sb.toString().substring(0, 20) + "...");

        reader.close();
        writer.flush();
        writer.close();

        return true;

    }
    private static final Logger LOG = Logger.getLogger(DSMGZipFileExtracter.class.getName());
}
