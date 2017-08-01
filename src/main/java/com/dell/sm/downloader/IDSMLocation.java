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
package com.dell.sm.downloader;

/**
 *
 * @author Shilpa_Pv
 */
public interface IDSMLocation {

    /**
     * Method to upload a file to a given destination directory
     *
     * @param inTargetRelativePath - destination directory
     * @param inLocation - Base location where the file is hosted
     * @return SUCCESS if the file specified is updated properly
     * @throws Exception Exception
     */
    public int uploadFile(String inLocation, String inTargetRelativePath) throws Exception;

    /**
     * Method to check a file exists or not
     *
     * @param inPath - destination directory
     * @return SUCCESS if the file specified is updated properly
     */
    public boolean exists(String inPath);
    
    /**
     * Method to check a file exists or not
     *
     * @return SUCCESS if the file has access through the protocol
     */
    public boolean check() throws Exception;

}
