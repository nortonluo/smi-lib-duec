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
package com.dell.cm.testPackage;

import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.downloader.DSMFTPDownloader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Md_Shahbaz_Alam
 */
public class TestFTPDownloaderWithNullProxy {

    public static void main(String[] args) {
        TestFTPDownloaderWithNullProxy ftpDownloader = new TestFTPDownloaderWithNullProxy();
        ftpDownloader.userName = args[0];
        ftpDownloader.password = args[1];
        ftpDownloader.baseLocation = args[2];
        ftpDownloader.destinationFolder = args[3];
        ftpDownloader.relativePath = args[4];

        DSMFTPDownloader instance = new DSMFTPDownloader();
        DSMAuthenticationParameters auth = new DSMAuthenticationParameters();
        auth.setUserName(ftpDownloader.userName);
        auth.setPassword(ftpDownloader.password);
        instance.setAuthenticationParameters(auth);
        instance.setProxyDetails(null);
        try {
            int result = instance.downloadFile(ftpDownloader.relativePath, ftpDownloader.destinationFolder, ftpDownloader.baseLocation);
        } catch (Exception ex) {
            Logger.getLogger(TestFTPDownloaderWithNullProxy.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public String userName;
    public String password;
    public String baseLocation;
    public String destinationFolder;
    public String relativePath;
}
