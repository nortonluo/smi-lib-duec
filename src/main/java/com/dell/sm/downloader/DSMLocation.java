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

import com.dell.cm.updateinformationmodel.DCMConstants;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import jcifs.smb.NtlmPasswordAuthentication;

/**
 *
 * @author Shilpa_Pv
 */
public class DSMLocation implements IDSMLocation {

    private static final char FORWARD_SLASH = '/';
    private static final char BACKWARD_SLASH = '\\';

    public DSMLocation(String inTargetPath) {
        setTargetPath(inTargetPath);
    }

    @Override
    public int uploadFile(String inSourceLocation, String inTargetRelativePath) throws Exception {
        inSourceLocation = inSourceLocation.replace("\\", "/");
        File sourceFile = new File(inSourceLocation);
        String targetFilePath = mTargetPath + "/" + inTargetRelativePath;
        targetFilePath = targetFilePath.replace("\\", "/");
        File targetFile = new File(targetFilePath);
        if (!sourceFile.exists()) {
            return DSMErrorCodes.FAILURE;
        }

        File destinationDirectory = new File(targetFile.getParent());
        LOG.log(Level.INFO, "Destination Directory = {0}", new Object[]{destinationDirectory.getAbsolutePath()});
        if (!destinationDirectory.exists()) {
            LOG.log(Level.INFO, "Creating Destination Directory = {0}", new Object[]{destinationDirectory.getAbsolutePath()});
            destinationDirectory.mkdirs();
        }

        Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return DSMErrorCodes.SUCCESS;
    }

    @Override
    public boolean exists(String inTargetRelativePath) {
        String targetFilePath = mTargetPath;
        if (inTargetRelativePath != null || !inTargetRelativePath.isEmpty()) {
            targetFilePath += FORWARD_SLASH + inTargetRelativePath;
        }
        File targetFile = new File(targetFilePath);

        return targetFile.exists();
    }

    public String getTargetPath() {
        return mTargetPath;
    }

    public void setTargetPath(String inTargetPath) {
        this.mTargetPath = inTargetPath;
    }

    @Override
    public boolean check() throws Exception {
        return true;
    }

    public static IDSMLocation create(String inTargetPath, DSMAuthenticationParameters inAuthentication) throws Exception {
        IDSMLocation location;

        LOG.log(Level.INFO, "Identifying location for path {0}", new Object[]{inTargetPath});

        if (Pattern.matches(DCMConstants.winRegex, inTargetPath.replace(FORWARD_SLASH, BACKWARD_SLASH))) {
            location = new DSMLocation(inTargetPath.replace(FORWARD_SLASH, BACKWARD_SLASH));
            LOG.log(Level.INFO, "Identified as windows path {0}", new Object[]{inTargetPath});
        } else if (Pattern.matches(DCMConstants.linuxRegex, inTargetPath.replace(BACKWARD_SLASH, FORWARD_SLASH))) {
            location = new DSMLocation(inTargetPath.replace(BACKWARD_SLASH, FORWARD_SLASH));
            LOG.log(Level.INFO, "Identified as linux path {0}", new Object[]{inTargetPath});
        } else {
            inTargetPath = inTargetPath.replace(BACKWARD_SLASH, FORWARD_SLASH);
            String[] pathFolders = inTargetPath.split("/");

            String destination = "";
            if (pathFolders.length > 3) {
                for (int i = 3; i < pathFolders.length; i++) {
                    destination = destination + File.separator + pathFolders[i];
                }
            }

            String shareserver = pathFolders[2];
    
            LOG.log(Level.INFO, "Trying smb path smb://{0}/{1}", new Object[]{shareserver,destination});
            location = new DSMSMBUploader(inAuthentication, shareserver, destination);
            if (!location.check()) {
                location = new DSMCIFSLocation(inAuthentication, shareserver, destination);
                LOG.log(Level.INFO, "Trying cifs(smb v1) path smb://{0}/{1}", new Object[]{shareserver,destination});
            }
        }
        return location;
    }

    private String mTargetPath;
    public static final Logger LOG = Logger.getLogger(DSMCIFSDownloader.class
            .getName());

}
