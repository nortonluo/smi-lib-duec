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

import com.dell.sm.extracter.ExtracterConstants;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Class for downloading files from a given location through ftp protocol
 *
 * @author VIVEKANANDH_N_R
 */
public class DSMFTPDownloader implements IDSMDownload {

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
        LOG.log(Level.INFO, "Entering FTP DownloadFile method");

        String urlString = inURL.replace(BACKWARD_SLASH, FORWARD_SLASH); // replace backward slash to forward slash
        urlString = urlString.replace(" ", "%20");
        urlString = urlString.replaceFirst("ftp://", "");
        if (urlString.startsWith("//")) {
            urlString = urlString.replaceFirst("//", "");
        }
        if (urlString.startsWith("/")) {
            urlString = urlString.replaceFirst("/", "");
        }
        urlString = "ftp://" + urlString;
        LOG.log(Level.INFO, "source uri path = {0}", urlString);
        URL url = new URL(urlString);
        LOG.log(Level.INFO, "urlString = {0}", url.toString());
        long startTime = System.currentTimeMillis();

        String userName = "anonymous";
        String password = "anonymous";

        if (this.mAuthenticationParameters != null) {
            LOG.log(Level.INFO, "Setting up Authentication Params");
            userName = mAuthenticationParameters.getUserName();
            password = mAuthenticationParameters.getPassword();

            if (userName == null || password == null) {
                LOG.log(Level.SEVERE, "Invalid user credentials ");
                return DSMErrorCodes.INVALID_PARAMETER;
            }
        }

        URLConnection con = null;
        try {

            // Create the download directory until the file to be downloaded
            inDestinationFile = inDestinationFile.replace(BACKWARD_SLASH, FORWARD_SLASH);
            File destinationDirectory = new File(inDestinationFile.substring(0, inDestinationFile.lastIndexOf("/")));
            if (!destinationDirectory.exists()) {
                LOG.log(Level.INFO, "Creating the directory" + destinationDirectory.getAbsolutePath());
                destinationDirectory.mkdirs();
            }

            // Downloads the selected file
            File destinationFile = new File(inDestinationFile);
            LOG.log(Level.INFO, "Absolute path of the download file" + destinationFile.getAbsolutePath());

            // if the proxy is provided then set the proxy settings in the
            // system to be used
            LOG.log(Level.INFO, "Proxy object." + this.mProxy);

            // We do not want NTLM to be priority.
            LOG.log(Level.INFO, "Setting up FTP System Property");
            System.setProperty("http.auth.preference", "basic");
            LOG.log(Level.INFO, "FTP System Property set");
            Proxy proxy = null;

            if (this.mProxy != null) {
                LOG.log(Level.INFO, "Using the proxy connection details to connect");
                // Default proxy type is HTTP if not mentioned explicitly by user
                Proxy.Type proxyType = Proxy.Type.HTTP;
                if (mProxy.getProtocol() == DSMProtocolEnum.SOCKS) {
                    proxyType = Proxy.Type.SOCKS;
                }
                proxy = new Proxy(proxyType, new InetSocketAddress(mProxy.getURL(), mProxy.getPortNumber()));

                // Connect using proxy
                con = url.openConnection(proxy);

                // If proxy and server cred are provided
                String proxyUsername = mProxy.getProxyUserName();
                String proxyPassword = mProxy.getProxyPassword();
                if (proxyUsername != null && proxyPassword != null
                        && !proxyUsername.isEmpty() && !proxyPassword.isEmpty()
                        && mAuthenticationParameters != null) {

                    Authenticator.setDefault(new DSMProxyAuthenticator(mProxy, url.getHost(), userName, password));
                    // If only proxy is required
                } else {
                    Authenticator.setDefault(new DSMProxyAuthenticator(mProxy, true));
                }
            } else {
                LOG.log(Level.INFO, "FTP : Proxy is null.");
                if (mAuthenticationParameters != null) {
                    Authenticator.setDefault(new DSMProxyAuthenticator(
                            null, url.getHost(), userName, password));
                }

                con = url.openConnection();
            }

            try {
                LOG.log(Level.INFO, "Connecting to URL" + url.toString());
                con.connect();
            } catch (Exception ex) {
                LOG.log(Level.WARNING, "FTP URL Connection failed because of unknown credentials " + ex.getMessage());

                String ftpUrl = "ftp://%s:%s@%s%s;type=i";
                ftpUrl = String.format(ftpUrl, userName, password, url.getAuthority(), url.getPath());
                url = new URL(ftpUrl);

                LOG.log(Level.INFO, "Retry URL Connection with login credentials " + userName);
                if (proxy != null) {
                    con = url.openConnection(proxy);
                } else {
                    con = url.openConnection();
                }
                con.connect();
            }

            LOG.log(Level.INFO, "FTP Connection Created");

            long filesize = con.getContentLength();
            LOG.log(Level.INFO, "Size of the file to download in kb is:-" + filesize / 1024);

            long totalContentBytes = con.getContentLengthLong();
            LOG.log(Level.INFO, "Bytes supposed to be copied :" + totalContentBytes);

            long copiedBytes = Files.copy(con.getInputStream(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            LOG.log(Level.INFO, "Bytes copied :" + copiedBytes);

            long endTime = System.currentTimeMillis();
            LOG.log(Level.INFO, "File:: " + destinationFile.getName() + " downloaded in " + (endTime - startTime) / 1000 + " secs");

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
            LOG.log(Level.SEVERE, "Error in FTP downloadFile method", ex);
            throw ex;
        } finally {
            // Resetting so that we are back to normal
            LOG.log(Level.INFO, "Resetting Auth");
            Authenticator.setDefault(null);
        }
        LOG.log(Level.INFO, "Exiting FTP DownloadFile method");

        return DSMErrorCodes.SUCCESS;
    }

    /**
     * Method to download a file to a given destination directory. Override
     * method to support the FTP protocol download
     *
     * @param inRelativePath - relative path of the file to be downloaded (file
     * name with the relative path)
     * @param inDestinationFolder - destination directory
     * @param inBaseLocation - Base location where the file is hosted
     * @return SUCCESS if the specified file is downloaded properly
     * @throws Exception Exception
     */
    @Override
    public int downloadFile(String inRelativePath, String inDestinationFolder, String inBaseLocation) throws Exception {

        LOG.log(Level.INFO, "Entering FTP DownloadFile method");
        String urlString;
        urlString = inBaseLocation + File.separator + inRelativePath;
        File destinationFile = new File(inDestinationFolder + File.separator + inRelativePath);

        return downloadFile(urlString, destinationFile.toString());
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
    private static final Logger LOG = Logger.getLogger(DSMFTPDownloader.class.getName());

}
