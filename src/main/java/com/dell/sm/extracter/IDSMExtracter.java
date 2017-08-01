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

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface to define the extraction methods based on the type of the file to
 * be decompressed Direct known implementing classes are
 * DSMGZipFileExtracter,etc
 *
 * @author VIVEKANANDH_N_R
 */
public interface IDSMExtracter {

    /**
     * Method for extracting the any compressed file
     *
     * @param inputCompressedFile location of the compressed file with relative
     * path
     * @param inputTargetLocation location of the directory in which the
     * compressed file to be uncompressed
     * @return returns success or failure (true/false)
     * @throws FileNotFoundException handles FileNotFoundException
     * @throws IOException handles IOException
     */
    public boolean extractFile(String inputCompressedFile, String inputTargetLocation) throws FileNotFoundException, IOException;

}
