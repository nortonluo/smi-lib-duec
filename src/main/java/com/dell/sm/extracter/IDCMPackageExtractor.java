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

import java.security.MessageDigest;

/**
 * Interface for extracting repository
 *
 * @author Ujjwal_Kumar_Gupta
 */
public interface IDCMPackageExtractor {

    /**
     * Given DUP file extract package xml and return string data
     *
     * @return String of XML
     */
    public String extractPackageXML();

    /**
     * Given LLXP file adds path of repository to list of repository paths
     *
     * @param inDestination : Destination to store content
     * @return DCMErrorCodes
     */
    public int extractPayloadFile(String inDestination);

    /**
     * Method to extract package.xml and sign from the DUP BIN File
     *
     * @param inDestination Folder to extract and save payload file
     * @return Success or Failure
     */
    public int extractPackageXMLFile(String inDestination);

    /**
     * Given LLXP file adds path of repository to list of repository paths
     *
     * @param inDigest : Hash based on the digest algorithm
     * @return DCMErrorCodes
     */
    public int getPayloadHash(MessageDigest inDigest);
    
    /**
     * Clear the left over contents
     *
     */
    public void close();

}
