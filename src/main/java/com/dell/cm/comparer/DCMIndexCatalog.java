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
package com.dell.cm.comparer;

import com.dell.cm.repository.DCMIndexCatalogHelper;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMGroupManifest;
import com.dell.cm.updateinformationmodel.DCMManifestIndex;
import com.dell.cm.updateinformationmodel.DCMManifestInformation;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.downloader.DSMDownloader;
import com.dell.sm.downloader.DSMProxy;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interface for catalog related interface like parsing and getting the latest
 * updates
 *
 * @author Raveendra_Madala
 */
public class DCMIndexCatalog implements IDCMIndexCatalog {

    private DSMAuthenticationParameters mAuthenticationParamaters;
    private Collection<DSMProxy> mProxyCollection;

    public DCMIndexCatalog() {
        mAuthenticationParamaters = null;
        mProxyCollection = null;
    }

    public DCMIndexCatalog(DSMAuthenticationParameters authParameters, DSMProxy proxyParameters) {
        mAuthenticationParamaters = authParameters;
        mProxyCollection = new ArrayList<>();
        mProxyCollection.add(proxyParameters);
    }

    /**
     * Method for parsing the catalog
     *
     * @param inCatalogFile specifies the catalog file to be parsed
     * @return the manifest of the parsed catalog
     */
    @Override
    public DCMManifestIndex parseCatalog(File inCatalogFile) {
        DCMManifestIndex manifestindex = new DCMManifestIndex();
        if (new DCMIndexCatalogHelper().deserializeXMLFile(inCatalogFile, manifestindex) == DCMErrorCodes.SUCCESS) {
            return manifestindex;
        }
        return null;
    }

    /**
     * Method for getting the latest index catalog information
     *
     * @return the blob for latest index catalog
     */
    @Override
    public DCMManifestIndex getLatestIndex() {
        Path tempFile = null;
        File inCatalogFile = null;
        try {
            tempFile = Files.createTempDirectory("duec");
            DSMDownloader downloader = new DSMDownloader();
            downloader.initialize(mAuthenticationParamaters, mProxyCollection);
            if (downloader.downloadIndexCatalog(tempFile.toString()) == DCMErrorCodes.SUCCESS) {
                inCatalogFile = new File(tempFile.toString() + "/CatalogIndex.xml");
                DCMManifestIndex manifestindex = new DCMManifestIndex();
                if (new DCMIndexCatalogHelper().deserializeXMLFile(inCatalogFile, manifestindex) == DCMErrorCodes.SUCCESS) {
                    return manifestindex;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DCMIndexCatalog.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (inCatalogFile != null && inCatalogFile.exists()) {
                    inCatalogFile.delete();
                }
                Files.delete(tempFile);
            } catch (IOException ex) {
                Logger.getLogger(DCMIndexCatalog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Method for getting index group from latest index catalog
     *
     * @param inId manifest id
     * @return the list of catalogs with the id
     */
    @Override
    public DCMGroupManifest getIndexbyId(String inId) {
        DCMManifestIndex manifestindex = getLatestIndex();
        if (manifestindex != null) {
            return manifestindex.getGroupManifestCollection().getGroupManifest(inId);
        }
        return null;

    }

    /**
     * Method for getting index group from latest index catalog
     *
     * @param inManifestType manifest type MTPDK,MTLC,etc..
     * @return the list of catalogs with the type
     */
    @Override
    public DCMGroupManifest getIndexbyType(String inManifestType) {
        DCMManifestIndex manifestindex = getLatestIndex();
        if (manifestindex != null) {

            for (DCMGroupManifest groupmanifest : manifestindex.getGroupManifestCollection().getGroupManifests()) {
                if (groupmanifest.getType().equals(inManifestType)) {
                    return groupmanifest;
                }
            }
        }
        return null;

    }

    /**
     * Method for getting the catalog path nearest to the date
     *
     * @param inManifestType manifest type MTPDK,MTLC,etc..
     * @return the destination catalog
     */
    @Override
    public DCMManifestInformation getLatestCatalogbyType(String inManifestType) {
        return getCatalogbyTypeandDate(getLatestIndex(), inManifestType, new Date());
    }

    /**
     * Method for getting the catalog path nearest to the date
     *
     * @param inManifestType manifest type MTPDK,MTLC,etc..
     * @param inDate input date
     * @return the destination catalog
     */
    @Override
    public DCMManifestInformation getCatalogbyTypeandDate(String inManifestType, Date inDate) {
        return getCatalogbyTypeandDate(getLatestIndex(), inManifestType, inDate);
    }

    /**
     * Method for getting the catalog path nearest to the date
     *
     * @param inIndexCatalog input index catalog
     * @param inManifestType manifest type MTPDK,MTLC,etc..
     * @param inDate input date
     * @return the destination catalog
     */
    @Override
    public DCMManifestInformation getCatalogbyTypeandDate(DCMManifestIndex inIndexCatalog, String inManifestType, Date inDate) {
        DCMManifestInformation outmanifest = null;

        DCMGroupManifest manifestindex = null;
        if (inIndexCatalog != null) {

            for (DCMGroupManifest groupmanifest : inIndexCatalog.getGroupManifestCollection().getGroupManifests()) {
                if (groupmanifest.getType().equals(inManifestType)) {
                    manifestindex = groupmanifest;
                }
            }
            if (manifestindex != null) {
                long minDiff = -1, currentTime = inDate.getTime();

                for (DCMManifestInformation manifest : manifestindex.getManifestInformationCollection().getManifestInformations()) {
                    try {
                        Date sourcedate = new SimpleDateFormat(DCMConstants.DATE_FORMAT_STRING).parse(manifest.getCreationDateTime());
                        long diff = Math.abs(currentTime - sourcedate.getTime());
                        if ((minDiff == -1) || (diff < minDiff)) {
                            minDiff = diff;
                            //closest manifest
                            outmanifest = manifest;
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(DCMIndexCatalog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return outmanifest;
    }

    @Override
    public DCMManifestInformation getLatestCatalogByIndexID(String id) {
        DCMManifestInformation outmanifest = null;
        DCMManifestIndex indexManifest = getLatestIndex();
        if (null != id && !id.isEmpty()) {

            for (DCMGroupManifest grpManifest : indexManifest.getGroupManifestCollection().getGroupManifests()) {
                if (grpManifest.getId().equals(id)) {
                    Date inDate = new Date();
                    long minDiff = -1, currentTime = inDate.getTime();
                    for (DCMManifestInformation manifest : grpManifest.getManifestInformationCollection().getManifestInformations()) {
                        try {
                            Date sourcedate = new SimpleDateFormat(DCMConstants.DATE_FORMAT_STRING).parse(manifest.getCreationDateTime());
                            long diff = Math.abs(currentTime - sourcedate.getTime());
                            if ((minDiff == -1) || (diff < minDiff)) {
                                minDiff = diff;
                                //closest manifest
                                outmanifest = manifest;
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(DCMIndexCatalog.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        return outmanifest;
    }

}
