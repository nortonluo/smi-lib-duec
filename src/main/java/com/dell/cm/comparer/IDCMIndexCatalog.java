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

import com.dell.cm.updateinformationmodel.DCMGroupManifest;
import com.dell.cm.updateinformationmodel.DCMManifestIndex;
import com.dell.cm.updateinformationmodel.DCMManifestInformation;
import java.io.File;
import java.util.Date;

/**
 * Interface for catalog related interface like parsing and getting the latest
 * updates
 *
 * @author Raveendra_Madala
 */
public interface IDCMIndexCatalog {

    /**
     * Method for parsing the catalog
     *
     * @param inCatalogFile specifies the catalog file to be parsed
     * @return the manifest of the parsed catalog
     */
    public DCMManifestIndex parseCatalog(File inCatalogFile);

    /**
     * Method for getting the latest index catalog information
     *
     * @return the blob for latest index catalog
     */
    public DCMManifestIndex getLatestIndex();

    /**
     * Method for getting index group from latest index catalog
     *
     * @param inId manifest id
     * @return the list of catalogs with the id
     */
    public DCMGroupManifest getIndexbyId(String inId);

    /**
     * Method for getting index group from latest index catalog
     *
     * @param inManifestType manifest type MTPDK,MTLC,etc..
     * @return the list of catalogs with the type
     */
    public DCMGroupManifest getIndexbyType(String inManifestType);

    /**
     * Method for getting the catalog path nearest to the date
     *
     * @param inManifestType manifest type MTPDK,MTLC,etc..
     * @return the destination catalog
     */
    public DCMManifestInformation getLatestCatalogbyType(String inManifestType);
    
    /**
     *Method for getting the catalog path by manifest id
     * @param id
     * @return
     */
    public DCMManifestInformation getLatestCatalogByIndexID(String id);
    /**
     * Method for getting the catalog path nearest to the date
     *
     * @param inManifestType manifest type MTPDK,MTLC,etc..
     * @param inDate input date
     * @return the destination catalog
     */
    public DCMManifestInformation getCatalogbyTypeandDate(String inManifestType, Date inDate);

    /**
     * Method for getting the catalog path nearest to the date
     *
     * @param inIndexCatalog input index catalog
     * @param inManifestType manifest type MTPDK,MTLC,etc..
     * @param inDate input date
     * @return the destination catalog
     */
    public DCMManifestInformation getCatalogbyTypeandDate(DCMManifestIndex inIndexCatalog, String inManifestType, Date inDate);
}
