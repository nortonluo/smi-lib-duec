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

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory class for returning the Download class based on the given protocol
 * Currently the following protocols are supported 1. HTTP 2. HTTPS 3. FTP
 *
 * @author VIVEKANANDH_N_R
 */
public class DSMProtocolDownloadFactory {

    /**
     * Method to get the download implementation instance based on the protocol,
     * currently supported protocol list includes HTTP,HTTPS and FTP
     *
     * @param protocol Protocol takes one of the enumeration value defined in
     * DSMProtocolEnum
     * @param mProxyMap Proxy Details Map
     * @return IDSMDownload implementation class based on the given protocol
     */
    public static IDSMDownload getProtocolDownloaderInstance(DSMProtocolEnum protocol, HashMap<DSMProtocolEnum, DSMProxy> mProxyMap) {
        LOG.log(Level.INFO, "Entering getProtocolDownloaderInstance");

        LOG.log(Level.INFO, "Protocol :: {0}", protocol.name());

        IDSMDownload downloader = null;

        switch (protocol) {
            case HTTP:
                downloader = new DSMHTTPDownloader();
                downloader.setProxyDetails(mProxyMap.get(DSMProtocolEnum.HTTP));
                break;
            case HTTPS:
                downloader = new DSMHTTPSDownloader();
                downloader.setProxyDetails(mProxyMap.get(DSMProtocolEnum.HTTPS));
                break;
            case FTP:
                downloader = new DSMFTPDownloader();
                downloader.setProxyDetails(mProxyMap.get(DSMProtocolEnum.FTP));
                break;
            case CIFS:
                downloader = new DSMCIFSDownloader();
                break;
            default:
                downloader = new DSMHTTPDownloader();
                downloader.setProxyDetails(mProxyMap.get(DSMProtocolEnum.HTTP));
                break;
        }

        LOG.log(Level.INFO, "Exiting getProtocolDownloaderInstance");

        return downloader;
    }
    private static final Logger LOG = Logger.getLogger(DSMProtocolDownloadFactory.class.getName());

}
