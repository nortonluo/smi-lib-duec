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

import com.dell.cm.comparer.DCMCatalog;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.sm.extracter.DSMGZipFileExtracter;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Class providing implementation for IDSMDownloader interface, provides methods
 * for downloading files
 *
 * @author Raveendra_Madala
 */
public class DSMDownloader implements IDSMDownloader {

    /**
     * Method for initializing
     *
     * @param inParameters specifies the authentication parameters to be used
     * for downloading
     * @param inProxyCollection specifies the collection of proxies to be used
     * @return SUCCESS if the initialization is successful else appropriate
     * error code is returned.
     */
    @Override
    public int initialize(DSMAuthenticationParameters inParameters, Collection<DSMProxy> inProxyCollection) {
        LOG.log(Level.INFO, " Initializing DSMDownloader inAuthenticationparams and inProxyCollection");
        mAuthenticationParamters = inParameters;

        if (inProxyCollection != null) {
            Iterator<DSMProxy> proxyIterator = inProxyCollection.iterator();
            while (proxyIterator.hasNext()) {
                DSMProxy proxy = proxyIterator.next();
                if (proxy != null) {
                    mProxyMap.put(proxy.getProtocol(), proxy);
                }
            }
        }
        return DSMErrorCodes.SUCCESS;
    }

    /**
     * Method for determining whether the initialization is done or not
     *
     * @return true if the initialization is successful
     */
    @Override
    public Boolean isInitialized() {
        return mInitialized;
    }

    /**
     * Method for downloading catalog
     *
     * @return SUCCESS if the catalog is downloaded else appropriate error code
     * is returned.
     * @throws java.lang.Exception Lang Exception
     */
    @Override
    public int downloadCatalog() throws Exception {
        return downloadFile("catalog//catalog.cab");
    }

    /**
     * Method for downloading catalog.xml.gz file
     *
     * @return SUCCESS if the catalog is downloaded else appropriate error code
     * is returned.
     * @throws java.lang.Exception Lang Exception
     */
    @Override
    public int downloadGZipCatalog() throws Exception {
        return downloadFile("catalog//catalog.xml.gz");
    }

    /**
     * Method for downloading CatalogIndex.gz file
     *
     * @param destination location to download file
     * @return SUCCESS if the catalog is downloaded else appropriate error code
     * is returned.
     * @throws java.lang.Exception Lang Exception
     */
    @Override
    public int downloadIndexCatalog(String destination) throws Exception {
        try {
            String indexcatalog = "catalog/CatalogIndex.gz";
            if (destination == null | destination.isEmpty()) {
                destination = new java.io.File(".").getCanonicalPath();
            }
            int error = downloadFile(indexcatalog, destination);
            if (error == 0) {
                Path indexfilegz = Paths.get(destination, indexcatalog);
                if (new DSMGZipFileExtracter().extractGZipFile(indexfilegz.toString(), destination)) {
                    Path indexxml = Paths.get(destination, "CatalogIndex");
                    Files.deleteIfExists(indexfilegz);
                    Files.deleteIfExists(indexfilegz.getParent());
                    Files.move(indexxml, Paths.get(destination, "CatalogIndex.xml"));
                    return DSMErrorCodes.SUCCESS;

                }
                return DSMErrorCodes.FAILURE;
            } else {
                return error;
            }

        } catch (Exception ex) {
            Logger.getLogger(DSMDownloader.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    /**
     * Method for downloading the file from http://downloads.dell.com into
     * current folder
     *
     * @param inRelativePath specifies the relative path of the file to be
     * downloaded
     * @return SUCCESS if the file can be downloaded else appropriate error code
     * is returned.
     * @throws java.lang.Exception Lang Exception
     */
    @Override
    public int downloadFile(String inRelativePath) throws Exception {
        try {
            String currentPath = new java.io.File(".").getCanonicalPath();
            return downloadFile(inRelativePath, currentPath);
        } catch (Exception ex) {
            Logger.getLogger(DSMDownloader.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        // return DSMErrorCodes.FAILURE;
        // return DSMErrorCodes.FAILURE;
    }

    /**
     * Method for downloading the file from http://downloads.dell.com into
     * specified folder
     *
     * @param inRelativePath specifies the relative path of the file to be
     * downloaded
     * @param inDestinationFolder specifies the folder into which the file is to
     * be downloaded
     * @return SUCCESS if the file can be downloaded else appropriate error code
     * is returned.
     * @throws java.lang.Exception Lang Exception
     */
    @Override
    public int downloadFile(String inRelativePath, String inDestinationFolder) throws Exception {
        return downloadFile(inRelativePath, inDestinationFolder, "downloads.dell.com");
    }

    /**
     * Method for downloading the file from the base location using HTTP into
     * specified folder
     *
     * @param inRelativePath specifies the relative path of the file to be
     * downloaded
     * @param inDestinationFolder specifies the folder into which the file is to
     * be downloaded
     * @param inBaseLocation specifies the base location from which the file is
     * to be downloaded
     * @return SUCCESS if the file can be downloaded else appropriate error code
     * is returned.
     * @throws java.lang.Exception Lang Exception
     */
    @Override
    public int downloadFile(String inRelativePath, String inDestinationFolder, String inBaseLocation) throws Exception {
        return downloadFile(inRelativePath, inDestinationFolder, inBaseLocation, DSMProtocolEnum.HTTP);
    }

    /**
     * Method for downloading the files from http://downloads.dell.com into the
     * current folder
     *
     * @param inRelativePaths specifies the relative paths of the files to be
     * downloaded
     * @return SUCCESS if the download could be done else appropriate error code
     * is returned.
     */
    @Override
    public int downloadFiles(Collection<String> inRelativePaths) throws Exception {
        try {
            String currentPath = new java.io.File(".").getCanonicalPath();
            return downloadFiles(inRelativePaths, currentPath, "downloads.dell.com", DSMProtocolEnum.HTTP);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw ex;
        }
        //  return DSMErrorCodes.FAILURE;
    }

    /**
     * Method for downloading the files from http://downloads.dell.com
     *
     * @param inRelativePaths specifies the relative paths of the files to be
     * downloaded
     * @param inDestinationFolder specifies the folder to which the files are to
     * be downloaded
     * @return SUCCESS if the download could be done else appropriate error code
     * is returned.
     */
    @Override
    public int downloadFiles(Collection<String> inRelativePaths, String inDestinationFolder) throws Exception {
        return downloadFiles(inRelativePaths, inDestinationFolder, "downloads.dell.com", DSMProtocolEnum.HTTP);
    }

    /**
     * Method for downloading the files using HTTP
     *
     * @param inRelativePaths specifies the relative paths of the files to be
     * downloaded
     * @param inDestinationFolder specifies the folder to which the files are to
     * be downloaded
     * @param inBaseLocation specifies the base location from which the files
     * are to be downloaded
     * @return SUCCESS if the download could be done else appropriate error code
     * is returned.
     */
    public int downloadFiles(Collection<String> inRelativePaths, String inDestinationFolder, String inBaseLocation) throws Exception {
        return downloadFiles(inRelativePaths, inDestinationFolder, inBaseLocation, DSMProtocolEnum.HTTP);
    }

    /**
     * Method for downloading files
     *
     * @param inRelativePaths specifies the relative paths of the files to be
     * downloaded
     * @param inDestinationFolder specifies the folder to which the files are to
     * be downloaded
     * @param inBaseLocation specifies the base location from which the files
     * are to be downloaded
     * @param inProtocol specifies the protocol to be used for accessing the
     * base location
     * @return SUCCESS if the download could be done else appropriate error code
     * is returned.
     */
    @Override
    public int downloadFiles(Collection<String> inRelativePaths, String inDestinationFolder, String inBaseLocation, DSMProtocolEnum inProtocol) throws Exception {
        int retVal = DSMErrorCodes.SUCCESS;
        Iterator<String> pathIterator = inRelativePaths.iterator();
        while (pathIterator.hasNext()) {
            String path = pathIterator.next();
            int downloadFileResult = downloadFile(path, inDestinationFolder, inBaseLocation, inProtocol);
            if (downloadFileResult != DSMErrorCodes.SUCCESS && downloadFileResult != DSMErrorCodes.ALREADY_PRESENT) {
                retVal = DCMErrorCodes.PARTIAL_DOWNLOAD;
            }
        }
        return retVal;
    }

    /**
     * Constructor
     */
    public DSMDownloader() {
        mInitialized = false;
        mAuthenticationParamters = null;
        mProxyMap = new HashMap<>();
    }

    /**
     * Method for downloading a file
     *
     * @param inRelativePath specifies the relative path of the file
     * @param inDestinationFolder specifies the folder to which the file is to
     * be downloaded
     * @param inBaseLocation specifies the base location from which the relative
     * path is specified
     * @param inProtocol specifies the protocol to be used
     * @return SUCCESS if the file could be downloaded else appropriate error
     * code is returned.
     */
    @Override
    public int downloadFile(String inRelativePath, String inDestinationFolder, String inBaseLocation, DSMProtocolEnum inProtocol) throws Exception {
        // Based on protocol passed down,  get the correct downloader instance and call the download method

        IDSMLocation location = DSMLocation.create(inDestinationFolder, mAuthenticationParamters);

        String targetFile = inRelativePath;

        if (mAbsoluteFolderPath) {
            targetFile = new File(inRelativePath).getName();;
        }
        return downloadFile(inRelativePath, location, "", targetFile, inBaseLocation, inProtocol);
    }

    /**
     * Method for downloading files
     *
     * @param inSourceRelativePath specifies the relative paths of the files to
     * be downloaded
     * @param inTargetFolderPath specifies the folder to which the files are to
     * be downloaded
     * @param inTargetFileName specifies the target File Name (used for Renaming
     * during download)
     * @param inBaseLocation specifies the base location from which the files
     * are to be downloaded
     * @return SUCCESS if the download could be done else appropriate error code
     * is returned.
     * @throws java.lang.Exception Lang Exception
     */
    @Override
    public int downloadFile(String inSourceRelativePath, IDSMLocation targetDSMLocation, String inTargetFolderPath, String inTargetFileName, String inBaseLocation, DSMProtocolEnum inSourceProtocol) throws Exception {
        LOG.log(Level.INFO, "Entering into DSMDownloader downloadFile method");
        if (null == inSourceRelativePath || null == targetDSMLocation || null == inTargetFileName) {
            return DSMErrorCodes.FAILURE;
        }
        try {

            if (targetDSMLocation.exists(inTargetFolderPath + "/" + inTargetFileName)) {
                LOG.log(Level.INFO, "Target file is already present {0}", inTargetFolderPath + "/" + inTargetFileName);
                return DSMErrorCodes.ALREADY_PRESENT;
            }

            IDSMDownload downloader = DSMProtocolDownloadFactory.getProtocolDownloaderInstance(inSourceProtocol, mProxyMap);
            downloader.setAuthenticationParameters(mAuthenticationParamters);
            downloader.setProxyDetails(mProxyMap.get(inSourceProtocol));
            int uploadResult = 0;

            String urlString;
            urlString = inBaseLocation + "/" + inSourceRelativePath;
            urlString = urlString.replaceAll(" ", "%20");

            //create a temp file
            File tempFile = File.createTempFile("Duectemp", ".tmp");// getting the temp path

            LOG.log(Level.INFO, "DSMDownloader : Call to download file={0}, URL={1}" + tempFile.getPath(), urlString);
            int downloadResult = downloader.downloadFile(urlString, tempFile.getPath());// downloading the file to temp path
            if (downloadResult == DSMErrorCodes.SUCCESS) {
                uploadResult = targetDSMLocation.uploadFile(tempFile.getPath(), inTargetFolderPath + "/" + inTargetFileName);// code in this method will upload file to CIFS Location
                if (uploadResult != DCMErrorCodes.SUCCESS) {
                    LOG.log(Level.SEVERE, "Fail to download File", inTargetFileName);
                    return uploadResult;
                }
                if (tempFile.exists()) {
                    try {
                        if (!tempFile.delete()) {
                            LOG.log(Level.WARNING, "Temporary File can not be deleted");
                        }
                    } catch (SecurityException ex) {
                        LOG.log(Level.WARNING, "Temporary File can not be deleted", ex);
                    }
                }
            } else {
                return downloadResult;
            }

            return uploadResult;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean isEnableAbsolutePathDownload() {
        return mAbsoluteFolderPath;
    }

    public void setEnableAbsolutePathDownload(boolean inEnableAbsolutePathDownload) {
        this.mAbsoluteFolderPath = inEnableAbsolutePathDownload;
    }

    private Boolean mInitialized;
    private DSMAuthenticationParameters mAuthenticationParamters;
    private HashMap<DSMProtocolEnum, DSMProxy> mProxyMap;
    private static final Logger LOG = Logger.getLogger(DSMDownloader.class.getName());
    private boolean mAbsoluteFolderPath;
}
