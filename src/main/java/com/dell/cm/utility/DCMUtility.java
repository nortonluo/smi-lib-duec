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
package com.dell.cm.utility;

import com.dell.cm.comparer.DCMCatalog;
import com.dell.cm.comparer.DCMComparer;
import com.dell.cm.comparer.DCMConsiderationEnum;
import com.dell.cm.comparer.DCMUpdateInformation;
import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMSystemInventory;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Shabu_VC
 */
public class DCMUtility {

    /**
     * Method for getting the applicable updates
     *
     * @param inSystemInventories specifies the set of inventories
     * @param inCatalogFile specifies the catalog
     * @param inConsideration specifies whether to report updates/downgrades
     * while determining the applicable updates
     * @return the applicable updates
     */
    public static HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(HashSet<DCMSystemInventory> inSystemInventories,
            File inCatalogFile, DCMConsiderationEnum inConsideration) {
        return new DCMComparer().getApplicableUpdates(inSystemInventories, inCatalogFile, DCMBundleType.BTW32, inConsideration);
    }

    /**
     * Method for getting the applicable updates
     *
     * @param inSystemInventories specifies the set of inventories
     * @param inCatalogFile specifies the catalog
     * @param inConsideration specifies whether to report updates/downgrades
     * while determining the applicable updates
     * @param inBundleType specifies the interested bundle type
     * @return the applicable updates
     */
    public static HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(HashSet<DCMSystemInventory> inSystemInventories,
            File inCatalogFile, DCMBundleType inBundleType, DCMConsiderationEnum inConsideration) {
        return new DCMComparer().getApplicableUpdates(inSystemInventories, inCatalogFile, inBundleType, inConsideration);
    }

    /**
     * Getting the catalog information
     *
     * @param catalogFilePath specifies path to Catalog schema
     * @return DCMManifest object
     */
    public static DCMManifest getCatalogInformation(String catalogFilePath) {
        return new DCMCatalog().getCatalogInformation(catalogFilePath);
    }

    /**
     * Getting the Package schema information
     *
     * @param packageFilePath specifies path to DUP file
     * @return DCMUpdatePackageInformation object
     */
    public static DCMUpdatePackageInformation getUpdatePackageInformation(String packageFilePath) {
        return new DCMComparer().getUpdatePackageInformation(packageFilePath);
    }

    /**
     * Getting the applicable updates
     *
     * @param inSystemInventories specifies DCMSystemInventory object
     * @param packageFilePath specifies dup path
     * @param inBundleType specifies type of update to consider
     * @param inConsideration specifies whether to report updates/downgrades
     * while determining the applicable updates
     * @return the applicable updates
     */
    public static HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdatesByUpdatePackage(HashSet<DCMSystemInventory> inSystemInventories,
            String packageFilePath, DCMBundleType inBundleType, DCMConsiderationEnum inConsideration) {
        return new DCMComparer().getApplicableUpdatesByUpdatePackage(inSystemInventories, packageFilePath, inBundleType, inConsideration);
    }

}
