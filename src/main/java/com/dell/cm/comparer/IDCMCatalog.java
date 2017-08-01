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

import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMSystem;
import java.io.File;
import java.util.List;
import java.util.Collection;

/**
 * Interface for catalog related interface like parsing and getting the latest
 * updates
 *
 * @author Raveendra_Madala
 */
public interface IDCMCatalog {

    /**
     * Method for parsing the catalog
     *
     * @param inCatalogFile specifies the catalog file to be parsed
     * @return the manifest of the parsed catalog
     */
    public DCMManifest parseCatalog(File inCatalogFile);

    /**
     * Method for getting the latest updates in the destination catalog
     *
     * @param inSourceCatalog specifies the source catalog for comparing
     * @param inLatestCatalog specifies the destination catalog
     * @return the list of latest updates in the destination catalog
     */
    public Collection<DCMUpdateInformation> getLatestUpdates(File inSourceCatalog, File inLatestCatalog);

    /**
     * Method for parsing the repository given base location for repository
     *
     * @param inBaseLocation specifies the location of repository
     * @param inSystemCollection specifies collection of DCMSystems
     * @return manifest of the parsed repository
     */
    public DCMManifest parseRepository(String inBaseLocation, List<DCMSystem> inSystemCollection);
}
