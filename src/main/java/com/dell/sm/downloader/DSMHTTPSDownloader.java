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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for downloading files from a given location through HTTPS
 *
 * @author VIVEKANANDH_N_R
 */
public class DSMHTTPSDownloader implements IDSMDownload {

    private static final String FORWARD_SLASH = "/";
    private static final String BACKWARD_SLASH = "\\";

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
     * Method to download a file to a given destination directory
     *
     * @param inURL specifies the source URL to download the file
     * @param inDestinationFile specifies the Destination file path
     * @throws Exception Exception
     */
    @Override
    public int downloadFile(String inURL, String inDestinationFile) throws Exception {
        LOG.log(Level.INFO, "Entering HTTPS DownloadFile method");
        try {
            String urlString = inURL.replace("\\", "/"); // replace backward slash to forward slash
            urlString = urlString.replaceAll(" ", "%20");
            urlString = urlString.replaceFirst("https://", "");
            if (urlString.startsWith("//")) {
                urlString = urlString.replaceFirst("//", "");
            }
            inDestinationFile=inDestinationFile.replace("\\", "/");
            URL url = new URL("https://" + urlString);
            HttpURLConnection urlConnection = null;
            if (mProxy == null) {
                urlConnection = (HttpURLConnection) url.openConnection();
                LOG.log(Level.FINEST, "urlConnection object{0}", urlConnection.toString());
            } else {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(mProxy.getURL(), mProxy.getPortNumber()));
                urlConnection = (HttpURLConnection) url.openConnection(proxy);
            }
            urlConnection.connect();
            LOG.log(Level.INFO, "URL={0}, DestinationFILE={1}", new Object[]{urlString, inDestinationFile});

            // Create the download directory until the file to be downloaded
            
             File destinationDirectory = new File(inDestinationFile.substring(0, inDestinationFile.lastIndexOf("/")));

            if (!destinationDirectory.exists()) {
                destinationDirectory.mkdirs();
            }

            LOG.log(Level.INFO, "Creating the directory" + destinationDirectory.getAbsolutePath());

            File destinationFile = new File(inDestinationFile);

            long totalContentBytes = urlConnection.getContentLengthLong();
            LOG.log(Level.INFO, "Bytes supposed to be copied :" + totalContentBytes);

            long copiedBytes = Files.copy(urlConnection.getInputStream(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            LOG.log(Level.INFO, "Bytes copied :" + copiedBytes);

            // Well, check that we are precise in downloading....
            if (totalContentBytes != -1 && totalContentBytes != copiedBytes) {
                return DSMErrorCodes.FAILURE;
            }

        } catch (MalformedURLException ex) {
            LOG.log(Level.SEVERE, "URL is not proper", ex);
            throw ex;
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error while reading the file", ex);
            throw ex;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error while reading the file", ex);
            throw ex;
        }

        LOG.log(Level.INFO, "Exiting HTTPS DownloadFile method");
        return DSMErrorCodes.SUCCESS;
    }

    /**
     * Method to download a file to a given destination directory. Override
     * method to support the https download
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
        LOG.log(Level.INFO, "Entering HTTPS DownloadFile method");

        String urlString;
        urlString = "https://" + inBaseLocation + File.separator + inRelativePath;
        File destinationFile = new File(inDestinationFolder + File.separator + inRelativePath);

        return downloadFile(urlString, destinationFile.toString());
    }

    /**
     * Method to set the Authentication Parameters
     *
     * @param inAuthenticationParameters specifies the authentication parameter
     */
    @Override
    public void setAuthenticationParameters(DSMAuthenticationParameters inAuthenticationParameters) {
        this.mAuthenticationParameters = inAuthenticationParameters;
    }
    private DSMProxy mProxy;
    private DSMAuthenticationParameters mAuthenticationParameters;
    private static final Logger LOG = Logger.getLogger(DSMHTTPSDownloader.class.getName());
}
