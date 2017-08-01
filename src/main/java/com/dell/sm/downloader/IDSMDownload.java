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

import java.net.URL;

/**
 * Interface providing methods for downloading a particular file. Known classes
 * implementing this interface are DSMFTPDownloader, DSMHTTPDownloader,
 * DSMHTTPSDownloader, DSMSocksDownloader
 *
 * @author VIVEKANANDH_N_R
 */
public interface IDSMDownload {

    /**
     * Method to download a file to a given destination directory
     *
     * @param inRelativePath - relative path of the file to be downloaded (file
     * name with the relative path)
     * @param inDestinationFolder - destination directory
     * @param inBaseLocation - Base location where the file is hosted
     * @return SUCCESS if the file is downloaded properly
     * @throws Exception Exception
     */
    public int downloadFile(String inRelativePath, String inDestinationFolder, String inBaseLocation) throws Exception;

    /**
     * Method to download a file to a given destination directory
     *
     * @param inURL specifies the source URL to download the file
     * @param inDestinationFile specifies the Destination file path
     * @return SUCCESS if the file is downloaded properly
     * @throws Exception  Exception
     */
    public int downloadFile(String inURL, String inDestinationFile) throws Exception;

    /**
     * Method to set the proxy details
     *
     * @param inProxy - Object with Proxy details
     */
    public void setProxyDetails(DSMProxy inProxy);

    /**
     * Method to set the Authentication Parameters
     *
     * @param inAuthenticationParameters specifies the authentication parameters
     */
    public void setAuthenticationParameters(DSMAuthenticationParameters inAuthenticationParameters);

}
