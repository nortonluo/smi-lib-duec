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

import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.hierynomus.msdtyp.AccessMask;
import com.hierynomus.mssmb2.SMB2CreateDisposition;
import com.hierynomus.mssmb2.SMB2ShareAccess;
import com.hierynomus.smbj.SMBClient;
import com.hierynomus.smbj.SmbConfig;
import com.hierynomus.smbj.auth.AuthenticationContext;
import com.hierynomus.smbj.connection.Connection;
import com.hierynomus.smbj.session.Session;
import com.hierynomus.smbj.share.DiskShare;
import com.hierynomus.smbj.share.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rahul_Ranjan2
 */
public class DSMSMBDownloader implements IDSMDownload {

    @Override
    public int downloadFile(String inRelativePath, String inDestinationFolder, String inBaseLocation) throws Exception {
        String url = inBaseLocation + "\\" + inRelativePath;
        Logger.getLogger(DSMSMBUploader.class.getName()).log(Level.SEVERE, "Connecting to", url);
        return downloadFile(url, inDestinationFolder);

    }

    @Override
    public int downloadFile(String inURL, String inDestinationFile) throws Exception {
        int res = DCMErrorCodes.SUCCESS;
        Logger.getLogger(DSMSMBUploader.class.getName()).info("Connecting :" + inURL);
        if (inURL.startsWith("smb:")) {
            inURL = inURL.replace("smb:", "");
        }
        inURL = inURL.replace("\\", "/");
        String fileName = "";
        for (String token : inURL.split("/")) {
            if (token != null || !token.isEmpty()) {
                if (mShareName == null || mShareName.isEmpty()) {
                    mShareName = token;
                    continue;
                }
                if (mSharePath == null || mSharePath.isEmpty()) {
                    mSharePath = token;
                    continue;
                }
                fileName += "/" + token;
            }
        }
        fileName = fileName.substring(1);
        fileName = fileName.replace("/", "\\");
        try {
            res = initialise();
            if (res == DCMErrorCodes.SUCCESS) {
                if (share.fileExists(fileName)) {
                    Logger.getLogger(DSMSMBDownloader.class.getName()).info("DownloadingFile: " + fileName);
                    File downloadFile = share.openFile(fileName,
                            EnumSet.of(AccessMask.GENERIC_ALL), null,
                            SMB2ShareAccess.ALL,
                            SMB2CreateDisposition.FILE_OPEN_IF,
                            null);

                    if (null != downloadFile) {
                        java.io.File dest = new java.io.File(inDestinationFile);
                        if (dest.getParent() != null && !dest.getParent().isEmpty()) {
                            Logger.getLogger(DSMSMBDownloader.class.getName()).info("Creating folder: " + dest.getParent());
                            Files.createDirectories(dest.getParentFile().toPath());
                        }
                        Logger.getLogger(DSMSMBDownloader.class.getName()).info("Downloading To: " + dest.getAbsolutePath());
                        OutputStream out;
                        out = new FileOutputStream(dest);
                        downloadFile.read(out);
                        res = DCMErrorCodes.SUCCESS;
                    }
                } else {
                    res = DCMErrorCodes.DOES_NOT_EXIST;
                }
            }
        } catch (Exception ex) {
            res = DCMErrorCodes.FAILURE;
            ex.printStackTrace();
        } finally {
            reset();
        }
        return res;
    }

    @Override
    public void setProxyDetails(DSMProxy inProxy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAuthenticationParameters(DSMAuthenticationParameters inAuthenticationParameters) {
        mShareUsername = inAuthenticationParameters.getUserName();
        mSharePassword = inAuthenticationParameters.getPassword();
        mDomain = inAuthenticationParameters.getDomainName();
    }

    private int initialise() {
        SmbConfig config = SmbConfig.createDefaultConfig();
        SMBClient smbClient = new SMBClient(config);
        int res = DCMErrorCodes.SUCCESS;
        try {
            mConnection = smbClient.connect(mShareName);
            session = mConnection.authenticate(new AuthenticationContext(mShareUsername, mSharePassword.toCharArray(), mDomain));
            share = (DiskShare) session.connectShare(mSharePath);
        } catch (Exception ex) {
            share = null;
            String msg = ex.getMessage();
            if (msg.contains("TransportException")) {
                res = DCMErrorCodes.SMB_VERSION_NOT_SUPPORTED;
            } else if (msg.contains("STATUS_BAD_NETWORK_NAME")) {
                res = DCMErrorCodes.DOES_NOT_EXIST;
            } else if (msg.contains("STATUS_LOGON_FAILURE")) {
                res = DCMErrorCodes.AUTH_FAILURE;
            } else {
                res = DCMErrorCodes.FAILURE;

            }
            Logger.getLogger(DSMSMBUploader.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    private void reset() {

        try {
            mConnection.close();

        } catch (Exception ex) {
            Logger.getLogger(DSMSMBUploader.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    private String mShareName;
    private String mSharePath;
    private String mShareUsername;
    private String mSharePassword;
    private DiskShare share;
    private Session session;
    private Connection mConnection;
    private String mDomain;
    private String mRelativePath;
}
