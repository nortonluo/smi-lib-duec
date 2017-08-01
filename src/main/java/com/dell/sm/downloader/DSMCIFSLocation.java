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
import java.io.File;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

/**
 *
 * @author Shilpa_Pv
 */
public class DSMCIFSLocation implements IDSMLocation {

    private static final char FORWARD_SLASH = '/';
    private static final char BACKWARD_SLASH = '\\';

    public DSMCIFSLocation(DSMAuthenticationParameters inAuthenticationParameters, String inSharedName, String inSharePath) {
        setShareName(inSharedName);
        setSharePath(inSharePath);
        setAuthenticationParameters(inAuthenticationParameters);
    }

    @Override
    public boolean exists(String inPath) {

        String location = "";
        location = mShareName + FORWARD_SLASH + mSharePath + FORWARD_SLASH;
        if (inPath != null) {
            location += inPath;
        }

        try {
            SmbFile tempPath = new SmbFile(location, mAuthenticationParameters);
            if (tempPath.exists()) {
                return true;
            }
        } catch (MalformedURLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return false;
        } catch (SmbException ex) {
            LOG.log(Level.SEVERE, null, ex);

            return false;
        }
        return false;
    }

    public void setAuthenticationParameters(DSMAuthenticationParameters inAuthenticationParameters) {
        LOG.log(Level.INFO, "Setting Authentication Parameters");
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
        }

        // Set as NTLM Auth param
        mAuthenticationParameters = new NtlmPasswordAuthentication(domainName, inAuthenticationParameters.getUserName(),
                inAuthenticationParameters.getPassword());
    }

    @Override
    public int uploadFile(String inSourceLocation, String inTargetRelativePath) throws Exception {
        LOG.log(Level.INFO, "Entering uploadFile method");
        if (null == inSourceLocation || null == inTargetRelativePath) {
            LOG.log(Level.INFO, "FAILURE. Invalid upload parameters.");
            return DCMErrorCodes.FAILURE;
        }
        OutputStream outputStream;
        try {
            String tempPath = "";
            tempPath = mShareName + FORWARD_SLASH + mSharePath + FORWARD_SLASH;
            if (inTargetRelativePath.contains("/")) {
                tempPath += inTargetRelativePath.substring(0, inTargetRelativePath.lastIndexOf(FORWARD_SLASH));
            }

            // creating the directories if they doesn't exist.
            SmbFile smbDir = new SmbFile(tempPath, mAuthenticationParameters);
            if (!smbDir.exists()) {
                smbDir.mkdirs();
            }

            tempPath = mShareName + FORWARD_SLASH + mSharePath + FORWARD_SLASH + inTargetRelativePath;
            SmbFile smbFile = new SmbFile(tempPath, mAuthenticationParameters);
            smbFile.connect();
            outputStream = smbFile.getOutputStream();
            File baseFile = new File(inSourceLocation);
            LOG.log(Level.INFO, "Copying the baseFile={0} content to target Location", new Object[]{baseFile.getAbsolutePath()});
            Files.copy(baseFile.toPath(), outputStream);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error while writing into the file", ex);
            return DCMErrorCodes.NOT_SUPPORTED;
        }
        LOG.log(Level.INFO, "SUCCESS. Returning from uploadFile Method");
        return DCMErrorCodes.SUCCESS;
    }

    public String getShareName() {
        return mShareName;
    }

    public void setShareName(String mShareName) {
        mShareName = mShareName.replace(BACKWARD_SLASH, FORWARD_SLASH);
        if (mShareName.startsWith("//")) {
            this.mShareName = "smb:" + mShareName.replace(BACKWARD_SLASH, FORWARD_SLASH);
        } else {
            this.mShareName = "smb://" + mShareName.replace(BACKWARD_SLASH, FORWARD_SLASH);
        }
    }

    public String getSharePath() {
        return mSharePath;
    }

    public void setSharePath(String inSharePath) {
        this.mSharePath = inSharePath.replace(BACKWARD_SLASH, FORWARD_SLASH);
    }

    @Override
    public boolean check() throws Exception {

        String location = "";
        location = mShareName + FORWARD_SLASH + mSharePath + FORWARD_SLASH;

        try {
            SmbFile tempPath = new SmbFile(location, mAuthenticationParameters);
            if (tempPath.canRead()) {
                return true;
            }
            throw new Exception("Non Readable Location");
        } catch (SmbException ex) {
            LOG.log(Level.SEVERE, null, ex);

            if (ex.toString().contains("TransportException")) {
                return false;
            }
            throw ex;
        }
    }
    public String mShareName;
    public String mSharePath;
    public NtlmPasswordAuthentication mAuthenticationParameters;
    public static final Logger LOG = Logger.getLogger(DSMCIFSDownloader.class.getName());

}
