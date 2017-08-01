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

import java.util.Collection;

/**
 * Interface providing the methods for downloading catalog file and the dup
 * files contained in the catalog from downloads.dell.com or a specified
 * location confirming the Dell specified standard mechanism of hosting updates
 *
 * @author VIVEKANANDH_N_R
 */
public interface IDSMDownloader {

    /**
     * Method for initializing
     *
     * @param inParameters specifies the authentication parameters to be used
     * for downloading
     * @param inProxyCollection specifies the collection of proxies to be used
     * @return SUCCESS if the initialization is successful else appropriate
     * error code is returned.
     */
    public int initialize(DSMAuthenticationParameters inParameters, Collection<DSMProxy> inProxyCollection);

    /**
     * Method for determining whether the initialization is done or not
     *
     * @return true if the initialization is successful
     */
    public Boolean isInitialized();

    /**
     * Method for downloading catalog
     *
     * @return SUCCESS if the catalog is downloaded else appropriate error code
     * is returned.
     * @throws java.lang.Exception Lang Exception
     */
    public int downloadCatalog() throws Exception;

    /**
     * Method for downloading catalog.xml.gz file
     *
     * @return SUCCESS if the catalog is downloaded else appropriate error code
     * is returned.
     * @throws java.lang.Exception Lang Exception
     */
    public int downloadGZipCatalog() throws Exception;

    /**
     * Method for downloading CatalogIndex.gz file
     *
     * @param destination location to download file
     * @return SUCCESS if the catalog is downloaded else appropriate error code
     * is returned.
     * @throws java.lang.Exception Lang Exception
     */
    public int downloadIndexCatalog(String destination) throws Exception;

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
    public int downloadFile(String inRelativePath) throws Exception;

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
    public int downloadFile(String inRelativePath, String inDestinationFolder) throws Exception;

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
    public int downloadFile(String inRelativePath, String inDestinationFolder, String inBaseLocation) throws Exception;

    /**
     * Method for downloading the files from http://downloads.dell.com into the
     * current folder
     *
     * @param inRelativePaths specifies the relative paths of the files to be
     * downloaded
     * @return SUCCESS if the download could be done else appropriate error code
     * is returned.
     * @throws java.lang.Exception Lang Exception
     */
    public int downloadFiles(Collection<String> inRelativePaths) throws Exception;

    /**
     * Method for downloading the files from http://downloads.dell.com
     *
     * @param inRelativePaths specifies the relative paths of the files to be
     * downloaded
     * @param inDestinationFolder specifies the folder to which the files are to
     * be downloaded
     * @return SUCCESS if the download could be done else appropriate error code
     * is returned.
     * @throws java.lang.Exception Lang Exception
     */
    public int downloadFiles(Collection<String> inRelativePaths, String inDestinationFolder) throws Exception;

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
     * @throws java.lang.Exception Lang Exception
     */
    public int downloadFiles(Collection<String> inRelativePaths, String inDestinationFolder, String inBaseLocation) throws Exception;

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
     * @throws java.lang.Exception Lang Exception
     */
    public int downloadFiles(Collection<String> inRelativePaths, String inDestinationFolder, String inBaseLocation, DSMProtocolEnum inProtocol) throws Exception;

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
     * @throws java.lang.Exception Lang Exception
     */
    public int downloadFile(String inRelativePath, String inDestinationFolder, String inBaseLocation, DSMProtocolEnum inProtocol) throws Exception;

    /**
     * Method for downloading a file
     *
     * @param inDSMLocation specifies the DSM Location
     * @param inSourceRelativePath specifies the relative path of the file
     * @param inTargetFileName specifies the Target file Name
     * @param inTargetFolderPath specifies the folder path for the target
     * @param inBaseLocation specifies the base location from which the relative
     * path is specified
     * @param inProtocol specifies the protocol to be used
     * @return SUCCESS if the file could be downloaded else appropriate error
     * code is returned.
     * @throws java.lang.Exception Lang Exception
     */
    public int downloadFile(String inSourceRelativePath, IDSMLocation inDSMLocation, String inTargetFolderPath, String inTargetFileName, String inBaseLocation, DSMProtocolEnum inProtocol) throws Exception;
}

