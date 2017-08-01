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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbAuthException;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

import com.dell.cm.updateinformationmodel.DCMErrorCodes;

/**
 * Class for downloading files from a given location through CIFS
 *
 * @author punit_ghodasara
 */
public class DSMCIFSDownloader implements IDSMDownload {

    private static final char FORWARD_SLASH = '/';
    private static final char BACKWARD_SLASH = '\\';

    // No need of proxy
    @Override
    public void setProxyDetails(DSMProxy inProxy) {
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
        LOG.log(Level.INFO, "Entering into CIFS downloadFile method");
        String path;
        try {
            path = inURL;
            path = path.replace(BACKWARD_SLASH, FORWARD_SLASH); // replace backward slash to forward slash
            path = path.replaceFirst("smb://", "");
            if (path.startsWith("//")) {
                path = path.replaceFirst("//", "");
            }
            path = "smb://" + path;

            LOG.log(Level.INFO, "File to download {0}", path);

            // Get Samba file
            SmbFile smbFile = null;
            InputStream inputStream = null;
            try {
                smbFile = new SmbFile(path, mAuthenticationParameters);
                LOG.log(Level.INFO, "parameteres passed");
                smbFile.connect();
                LOG.log(Level.INFO, "SMB Connection Successful");
                inputStream = smbFile.getInputStream();
            } catch (SmbAuthException authException) {
                LOG.log(Level.SEVERE, "SMB Authentication Exception", authException);
                return DCMErrorCodes.AUTH_FAILURE;
            } catch (SmbException exception) {
                LOG.log(Level.SEVERE, "SMB Exception", exception);
                if (exception.toString().contains("TransportException")) {
                    DSMAuthenticationParameters auth = new DSMAuthenticationParameters();
                    auth.setUserName(mAuthenticationParameters.getUsername());
                    auth.setPassword(mAuthenticationParameters.getPassword());
                    auth.setDomainName(mAuthenticationParameters.getDomain());
                    DSMSMBDownloader smbDownloader = new DSMSMBDownloader();
                    smbDownloader.setAuthenticationParameters(auth);
                    return smbDownloader.downloadFile(inURL.replace("smb:", ""), inDestinationFile);
                } else {
                    return DCMErrorCodes.INVALID_PARAMETER;
                }
            }

            LOG.log(Level.INFO, "URL={0}, DestinationFile={1}", new Object[]{
                inURL, inDestinationFile});

            // Create the download directory until the file to be downloaded
            File destinationDirectory = new File(inDestinationFile.substring(0, inDestinationFile.lastIndexOf(File.separator)));
            LOG.log(Level.INFO, "Creating the directory{0}", destinationDirectory.getAbsolutePath());
            if (!destinationDirectory.exists()) {
                destinationDirectory.mkdirs();
            }

            File destinationFile = new File(inDestinationFile);
            destinationFile.createNewFile();

            long totalContentBytes = smbFile.getContentLength();
            LOG.log(Level.INFO, "Bytes supposed to be copied :{0}", totalContentBytes);

            long copiedBytes = Files.copy(inputStream, destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            LOG.log(Level.INFO, "Bytes copied :{0}", copiedBytes);

            // Well, check that we are precise in downloading....
            if (totalContentBytes != -1 && totalContentBytes != copiedBytes) {
                return DSMErrorCodes.FAILURE;
            }

        } catch (MalformedURLException ex) {
            LOG.log(Level.SEVERE, "URL is not proper", ex);
            throw ex;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error while reading the file", ex);

            throw ex;
        }

        LOG.log(Level.INFO, "Exiting CIFS DownloadFile method");
        return DSMErrorCodes.SUCCESS;

    }

    /**
     * Method to download a file to a given destination directory. Override
     * method to support the CIFS protocol
     *
     * @param inRelativePath - relative path of the file to be downloaded (file
     * name with the relative path)
     * @param inDestinationFolder - destination directory
     * @param inBaseLocation - Base location where the file is hosted
     * @return SUCCESS if the file is downloaded properly
     * @throws Exception Exception
     */
    @Override
    public int downloadFile(String inRelativePath, String inDestinationFolder, String inBaseLocation) throws Exception {
        LOG.log(Level.INFO, "Entering CIFS DownloadFile method");

        String urlString = "smb://" + inBaseLocation + "/" + inRelativePath;
        String destinationFile = inDestinationFolder + File.separator + inRelativePath;
        return downloadFile(urlString, destinationFile);
    }

    /**
     * Method to set the Authentication Parameters
     *
     * @param inAuthenticationParameters authentication parameters
     */
    @Override
    public void setAuthenticationParameters(DSMAuthenticationParameters inAuthenticationParameters) {
        // If domain is specified,
        String domainName = null;
        domainName = inAuthenticationParameters.getDomainName();

        // Replace domain to jCIFS specification
        if (domainName != null && !domainName.isEmpty()) {
            // This code replaces, mydomain.com TO mydomain
            int dotPlace = domainName.indexOf('.');
            if (dotPlace >= 0) {
                domainName = domainName.substring(0, dotPlace);
            }
        } else {
            LOG.log(Level.INFO, "Domain Name : localhost");
        }

        // Set as NTLM Auth param
        LOG.log(Level.INFO, "Domain Name {0}", domainName);
        this.mAuthenticationParameters = new NtlmPasswordAuthentication(domainName,
                inAuthenticationParameters.getUserName(), inAuthenticationParameters.getPassword());

    }

    private NtlmPasswordAuthentication mAuthenticationParameters;
    private static final Logger LOG = Logger.getLogger(DSMCIFSDownloader.class.getName());

}
