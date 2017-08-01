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
import com.hierynomus.mssmb2.SMB2CreateOptions;
import com.hierynomus.mssmb2.SMB2ShareAccess;
import com.hierynomus.smbj.SMBClient;
import com.hierynomus.smbj.SmbConfig;
import com.hierynomus.smbj.auth.AuthenticationContext;
import com.hierynomus.smbj.connection.Connection;
import com.hierynomus.smbj.session.Session;
import com.hierynomus.smbj.share.DiskShare;
import com.hierynomus.smbj.share.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rahul_Ranjan2
 */
public class DSMSMBUploader implements IDSMLocation {

    public DSMSMBUploader(DSMAuthenticationParameters mAuth, String shareServer, String sharePath) {
        mRelativePath = null;

        if (sharePath.contains("/") || sharePath.contains("\\")) {
            sharePath = sharePath.replace("/", "\\");
            int index = sharePath.indexOf("\\", 1);
            mShareName = sharePath.substring((sharePath.startsWith("\\")) ? 1 : 0, index);
            mRelativePath = sharePath.substring(index, sharePath.length());
        } else {
            mShareName = sharePath;
        }

        mShareServer = shareServer;

        mShareUsername = mAuth.getUserName();
        mSharePassword = mAuth.getPassword();
        mDomain = mAuth.getDomainName();
    }

    // re-write this logic
    private int createDirectories(String targetRelativePath) {
        String temp = "";
        int res = DCMErrorCodes.SUCCESS;
        String targetPath = "";
        //temp = temp + targetPath.substring(0, targetPath.lastIndexOf("\\"));
        if (!targetRelativePath.startsWith("\\")) {
            targetRelativePath = "\\" + targetRelativePath;
        }
        targetRelativePath = targetRelativePath.replace("\\", "/");

        if (targetRelativePath.contains("/")) {
            String[] tempPath = targetRelativePath.split("/");
            for (int i = 1; i < tempPath.length; i++) {
                targetPath = targetPath + tempPath[i];
                if (!share.folderExists(targetPath)) {
                    share.mkdir(targetPath);
                }
                targetPath = targetPath + "\\";
            }
        } else if (!share.folderExists(temp)) {
            share.mkdir(temp);
        } else {
            res = DCMErrorCodes.ALREADY_PRESENT;
        }
        return res;
    }

    private String getPath(String inPath) {
        String targetpath = inPath;
        if (mRelativePath != null) {
            targetpath = mRelativePath + "\\" + inPath;
        }
        targetpath = targetpath.replace("\\", "/");

        String destination = "";

        for (String folder : targetpath.split("/")) {
            if (folder != null && !folder.isEmpty()) {
                if (destination.isEmpty()) {
                    destination = folder;
                } else {
                    destination = destination + "\\" + folder;
                }
            }
        }
        return destination;
    }

    @Override
    public int uploadFile(String inSourceLocation, String inTargetRelativePath) {

        int res = DCMErrorCodes.SUCCESS;
        try {
            if (null == inSourceLocation || null == inTargetRelativePath) {
                LOG.log(Level.INFO, "FAILURE. Invalid upload parameters.");
                res = DCMErrorCodes.INVALID_PARAMETER;
            }

            Path sourcePath = Paths.get(inSourceLocation);
            res = initialize();
            if (res == DCMErrorCodes.SUCCESS) {
                if (!Files.exists(sourcePath)) {
                    res = DCMErrorCodes.INVALID_PARAMETER;
                }
                if (res == DCMErrorCodes.SUCCESS) {
                    String temp = "";
                    String targetPath = getPath(inTargetRelativePath);
                    if (targetPath.contains("\\")) {
                        res = createDirectories(targetPath.substring(0, targetPath.lastIndexOf("\\")));
                    }
                    if (res == DCMErrorCodes.SUCCESS || res == DCMErrorCodes.ALREADY_PRESENT) {
                        File file;
                        if (targetPath.startsWith("\\")) {
                            targetPath = targetPath.substring(targetPath.indexOf("\\") + 1, targetPath.length());
                        }

                        LOG.log(Level.INFO, "Uploading file from {0} to {1}", new Object[]{inSourceLocation, targetPath});
                        file = share.openFile(targetPath,
                                EnumSet.of(AccessMask.GENERIC_ALL), null,
                                SMB2ShareAccess.ALL,
                                SMB2CreateDisposition.FILE_OVERWRITE_IF,
                                EnumSet.of(SMB2CreateOptions.FILE_DIRECTORY_FILE));
                        if (null != file) {
                            java.io.InputStream is = null;
                            OutputStream os = null;

                            try {
                                int totallength = 0;
                                is = new FileInputStream(sourcePath.toFile());
                                os = file.getOutputStream();
                                byte[] buffer = new byte[1024];
                                int length;
                                while ((length = is.read(buffer)) > 0) {
                                    os.write(buffer, 0, length);
                                    totallength += length;
                                }
                                LOG.log(Level.INFO, "Uploaded file {0} size={1}", new Object[]{targetPath, totallength});

                            } catch (IOException ex) {
                                LOG.log(Level.SEVERE, null, ex);
                                res = DCMErrorCodes.FAILURE;

                            } finally {
                                os.flush();
                                is.close();
                                os.close();
                            }

                        } else {
                            res = DCMErrorCodes.FAILURE;
                        }

                    }
                }
            }

        } catch (Exception ex) {
            res = DCMErrorCodes.FAILURE;
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            reset();
        }
        return res;
    }

    @Override
    public boolean exists(String inPath) {
        boolean result = false;

        try {
            int res = initialize();
            if (res == DCMErrorCodes.SUCCESS) {
                inPath = getPath(inPath);
                if (!share.folderExists(inPath)) {
                    if (!share.fileExists(inPath)) {
                        result = false;
                    } else {
                        result = true;
                    }
                } else {
                    result = true;
                }
            } else {
                result = false;
            }
        } catch (Exception ex) {
            if (ex.getMessage().contains("STATUS_NOT_A_DIRECTORY")) {
                return true;
            }
            LOG.log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            reset();
        }
        return result;
    }

    private int initialize() {
        SmbConfig config = SmbConfig.createDefaultConfig();
        SMBClient smbClient = new SMBClient(config);

        int res = DCMErrorCodes.SUCCESS;
        try {
            mConnection = smbClient.connect(mShareServer);
            session = mConnection.authenticate(new AuthenticationContext(mShareUsername, mSharePassword.toCharArray(), mDomain));
            share = (DiskShare) session.connectShare(mShareName);
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

    @Override
    public boolean check() throws Exception {

        int res = initialize();
        if (res == DCMErrorCodes.SMB_VERSION_NOT_SUPPORTED) {
            return false;
        } else if (res == DCMErrorCodes.SUCCESS) {
            return true;
        }
        throw new Exception("Invalid Connection parameter");
    }

    private String mShareServer;
    private String mShareName;
    private String mRelativePath;
    private String mShareUsername;
    private String mSharePassword;
    private DiskShare share;
    private Session session;
    private Connection mConnection;
    private String mDomain;
    public static final Logger LOG = Logger.getLogger(DSMSMBUploader.class
            .getName());

}
