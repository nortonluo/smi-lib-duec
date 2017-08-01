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
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author Shabu_VC
 */
public class DCMPackageHashofHash {

    /**
     * Returns the concatenated hash values of all files under payload folder
     *
     * @param folderPath
     * @param writer
     * @return
     * @throws Exception
     */
    public static String getHashOfHash(Map<String, MessageDigest> inDigests, MessageDigest outDigest) throws Exception {

        for (String path :inDigests.keySet()) {
            try {
                byte[] hashedBytes = inDigests.get(path).digest();

                String sha256Hash = convertByteArrayToHexString(hashedBytes);

                //Getting the hash value of the given file
                outDigest.update(sha256Hash.getBytes());

                LOG.info("SHA-256 Hash for File : " + path + " - " + sha256Hash );

            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        return convertByteArrayToHexString(((MessageDigest)outDigest.clone()).digest());
    }

    /**
     * Returns the hex string value of given byte array
     *
     * @param arrayBytes
     * @return
     */
    public static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }

    private static final Logger LOG = Logger.getLogger(DCMPackageHashofHash.class
            .getName());
}
