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
import java.util.logging.Logger;

/**
 * Class for downloading files from a given location through SOCKS
 *
 * @author VIVEKANANDH_N_R
 */
public class DSMSocksDownloader implements IDSMDownload {

    /**
     * Method to download a file to a given destination directory
     *
     * @param inURL specifies the source URL to download the file
     * @param inDestinationFile specifies the Destination file path
     * @throws Exception Exception
     */
    @Override
    public int downloadFile(String inURL, String inDestinationFile) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
    }

    /**
     * Method to download a file to a given destination directory. Override
     * method to support the socks download
     *
     * @param inRelativePath - relative path of the file to be downloaded (file
     * name with the relative path)
     * @param inDestinationFolder - destination directory
     * @param inBaseLocation - Base location where the file is hosted
     * @return success / failure based on the download of the given file
     * @throws Exception Exception
     */
    @Override
    public int downloadFile(String inRelativePath, String inDestinationFolder, String inBaseLocation) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Method to set the proxy details
     *
     * @param inProxy - Object with Proxy details
     */
    @Override
    public void setProxyDetails(DSMProxy inProxy) {
        this.mProxy = inProxy;
    }

    /**
     * Method to set the Authentication Parameters
     *
     * @param inAuthenticationParameters specifies the authentication parameters
     */
    @Override
    public void setAuthenticationParameters(DSMAuthenticationParameters inAuthenticationParameters) {
        this.mAuthenticationParameters = inAuthenticationParameters;
    }
    private DSMProxy mProxy;
    private DSMAuthenticationParameters mAuthenticationParameters;
    private static final Logger LOG = Logger.getLogger(DSMSocksDownloader.class.getName());
}
