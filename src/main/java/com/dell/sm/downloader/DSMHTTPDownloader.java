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
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for downloading files from a given location through HTTPS
 *
 * @author punit_ghodasara
 */
public class DSMHTTPDownloader implements IDSMDownload {

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
     * Method to download a file to a given destination directory method to
     * support the http protocol
     *
     * @param inURL specifies the source URL to download the file
     * @param inDestinationFile specifies the Destination file path
     * @throws Exception Exception
     */
    @Override
    public int downloadFile(String inURL, String inDestinationFile) throws Exception {

        LOG.log(Level.INFO, "Entering HTTP DownloadFile method");

        try {
            inURL = inURL.replaceAll(" ", "%20");
            String urlString = inURL.replace(BACKWARD_SLASH, FORWARD_SLASH); // replace backward slash to forward slash
            urlString = urlString.replaceFirst("http://", "");
            if (urlString.startsWith("//")) {
                urlString = urlString.replaceFirst("//", "");
            }
            LOG.log(Level.INFO, "source uri path = {0}", urlString);
            URL url = new URL("http://" + urlString);
            LOG.log(Level.INFO, "urlString = {0}", url.toString());
            HttpURLConnection urlConnection = null;

            // We do not want NTLM to be priority.
            LOG.log(Level.INFO, "Setting up HTTP System property");
            System.setProperty("http.auth.preference", "basic");
            LOG.log(Level.INFO, "HTTP System property Set");
            LOG.log(Level.INFO, "mProxy = {0}", mProxy);
            if (null == mProxy) {
                LOG.log(Level.INFO, "mProxy is null");
                urlConnection = (HttpURLConnection) url.openConnection();
                LOG.log(Level.INFO, "urlConnection object{0}", urlConnection.toString());
            } else {
                LOG.log(Level.INFO, "mProxy not null");
                    // Default proxy type is HTTP if not mentioned explicitly by
                // user
                Proxy.Type proxyType = Proxy.Type.HTTP;
                if (mProxy.getProtocol() == DSMProtocolEnum.SOCKS) {
                    proxyType = Proxy.Type.SOCKS;
                }
                Proxy proxy = new Proxy(proxyType, new InetSocketAddress(mProxy.getURL(), mProxy.getPortNumber()));

                LOG.log(Level.INFO, "url-Host{0}", url.getHost());

                // Connect using proxy
                urlConnection = (HttpURLConnection) url.openConnection(proxy);

                // If proxy and server cred are provided
                String proxyUsername = mProxy.getProxyUserName();
                String proxyPassword = mProxy.getProxyPassword();
                if (proxyUsername != null && proxyPassword != null
                        && !proxyUsername.isEmpty() && !proxyPassword.isEmpty()
                        && mAuthenticationParameters != null) {

                    Authenticator.setDefault(new DSMProxyAuthenticator(
                            mProxy, url.getHost(), mAuthenticationParameters.getUserName(),
                            mAuthenticationParameters.getPassword()));
                    // If only proxy is required
                } else {
                    Authenticator.setDefault(new DSMProxyAuthenticator(mProxy, true));
                }
            }
            LOG.log(Level.INFO, "URL={0}, DestinationFile={1}", new Object[]{inURL, inDestinationFile});
            urlConnection.connect();

            // Create the download directory until the file to be downloaded
//            String relativePathDirectory = inRelativePath.substring(0, inRelativePath.lastIndexOf("/"));
//
//            File destinationDirectory = new File(inDestinationFolder + File.separator + relativePathDirectory);
            LOG.log(Level.INFO, "Getting destination Directory");
            File destinationDirectory = new File(inDestinationFile.substring(0, inDestinationFile.lastIndexOf(File.separator)));
            LOG.log(Level.INFO, "Destination Directory = {0}", new Object[]{destinationDirectory.getAbsolutePath()});
            if (!destinationDirectory.exists()) {
                LOG.log(Level.INFO, "Creating Destination Directory = {0}", new Object[]{destinationDirectory.getAbsolutePath()});
                destinationDirectory.mkdirs();
            }
//            File destinationFile = new File(inDestinationFolder + File.separator + inTargetFileName);
            LOG.log(Level.INFO, "Getting Destination File ");
            File destinationFile = new File(inDestinationFile);
            LOG.log(Level.INFO, "Creating new Destination File = {0}", new Object[]{destinationFile});
            destinationFile.createNewFile();

            InputStream in = urlConnection.getInputStream();
            long totalContentBytes = urlConnection.getContentLengthLong();
            LOG.log(Level.INFO, "Bytes supposed to be copied :" + totalContentBytes);

            long copiedBytes = Files.copy(in, destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
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
            LOG.log(Level.SEVERE, "Exception in HTTP downloadFile method", ex);
            throw ex;
        } finally {
            // Resetting so that we are back to normal
            LOG.log(Level.INFO, "Resetting Auth");
            Authenticator.setDefault(null);
        }

        LOG.log(Level.INFO, "Exiting HTTP DownloadFile method");
        return DSMErrorCodes.SUCCESS;

    }

    /**
     * Method to download a file to a given destination directory. Override
     * method to support the http protocol
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

        LOG.log(Level.INFO, "Entering HTTP DownloadFile method");

//        URL url;
//
//        url = new URL("http://" + inBaseLocation + "/" + inRelativePath);
        String urlString = "http://" + inBaseLocation + "/" + inRelativePath;
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

    public boolean isEnableAbsolutePathDownload() {
        return mEnableAbsolutePathDownload;
    }

    public void setEnableAbsolutePathDownload(boolean inEnableAbsolutePathDownload) {
        this.mEnableAbsolutePathDownload = inEnableAbsolutePathDownload;
    }

    private DSMProxy mProxy;
    private DSMAuthenticationParameters mAuthenticationParameters;
    private static final Logger LOG = Logger.getLogger(DSMHTTPDownloader.class.getName());
    private boolean mEnableAbsolutePathDownload;

}
