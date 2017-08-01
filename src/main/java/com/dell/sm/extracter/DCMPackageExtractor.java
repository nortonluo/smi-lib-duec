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

import com.dell.cm.comparer.DCMCatalog;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shabu_VC
 */
public class DCMPackageExtractor {

    /**
     * Given DUP file extract package xml and return string data
     *
     * @param inDupFile : dup file path
     * @return String of XML
     */
    public static String extractPackageXML(File inDupFile) {
        IDCMPackageExtractor extractor = getExtractor(inDupFile);;

        String result = extractor.extractPackageXML();
        extractor.close();
        return result;
    }

    /**
     * Given LLXP file adds path of repository to list of repository paths
     *
     * @param inDupFile : dup file path
     * @param inDestination : Destination to store content
     * @return DCMErrorCodes
     */
    public static int extractPayloadFile(File inDupFile, String inDestination) {
        IDCMPackageExtractor extractor = getExtractor(inDupFile);;

        int result = extractor.extractPayloadFile(inDestination);
        extractor.close();
        return result;

    }

    /**
     * Given LLXP file adds path of repository to list of repository paths
     *
     * @param inDupFile : dup file path
     * @return DCMErrorCodes
     */
    public static String getPayloadHash(File inDupFile) {
        MessageDigest digest;
        IDCMPackageExtractor extractor = getExtractor(inDupFile);;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            extractor.getPayloadHash(digest);
            return DCMPackageHashofHash.convertByteArrayToHexString(digest.digest());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DCMPackageExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
        extractor.close();
        return null;
    }

    /**
     * Given DUP file verify the DUP's Hash of hash
     *
     * @param inDupFile : dup file path
     * @param inGPGExePath : Path for gpg executable
     * @return DCMErrorCodes
     */
    public static int verifyPayLoadHash(File inDupFile, File inGPGExePath) {
        Path destination = null;
        File signFile = null;
        File packageFile = null;
        IDCMPackageExtractor extractor = getExtractor(inDupFile);
        try {
            String xml = extractor.extractPackageXML();
            DCMUpdatePackageInformation package1 = new DCMCatalog().parseSoftwareComponent(xml, inDupFile.toString());
            String xmlhash = package1.getHashofHash();
            if (xmlhash != null && xmlhash.isEmpty()) {
                return DCMErrorCodes.SIGN_HASH_MISSING;
            }
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            extractor.getPayloadHash(digest);
            String calculatedhash = DCMPackageHashofHash.convertByteArrayToHexString(digest.digest());
            if (!calculatedhash.equals(xmlhash)) {
                return DCMErrorCodes.SIGN_HASH_NOT_EQUAL;
            }

            destination = Files.createTempDirectory("duec");

            if (extractor.extractPackageXMLFile(destination.toString()) != DCMErrorCodes.SUCCESS) {
                return DCMErrorCodes.SIGN_PACKAGE_INVALID;
            }
            signFile = new File(destination.toString() + File.separator + "package.xml.sign");
            packageFile = new File(destination.toString() + File.separator + "package.xml");
            if (!signFile.exists() || !packageFile.exists()) {
                return DCMErrorCodes.SIGN_PACKAGE_INVALID;
            }

            // run gpg and verify
            String target = String.format("%s --verify \"%s\" \"%s\"", inGPGExePath, signFile, packageFile);
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(target);
            proc.waitFor();
            if (proc.exitValue() == 0) {
                return DCMErrorCodes.SUCCESS;
            }
        } catch (NoSuchAlgorithmException | IOException | InterruptedException ex) {
            Logger.getLogger(DCMPackageExtractor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (signFile != null) {
                signFile.delete();
            }
            if (packageFile != null) {
                packageFile.delete();
            }
            if (destination != null) {
                destination.toFile().delete();
            }
            extractor.close();
        }
        return DCMErrorCodes.SIGN_CHECK_FAILED;
    }

    /**
     * Given dup file adds get the extractor object
     *
     * @param inDupFile : dup file path
     * @return DCMErrorCodes
     */
    public static IDCMPackageExtractor getExtractor(File inDupFile) {
        if (inDupFile.getName().toLowerCase().endsWith(".exe")) {
            return new DCMPackageExtractorLWXP(inDupFile);
        } else {
            return new DCMPackageExtractorLLXP(inDupFile);
        }

    }
}
