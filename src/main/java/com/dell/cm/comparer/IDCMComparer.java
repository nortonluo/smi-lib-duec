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

import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMCatalogComparisonResult;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMSystemInventory;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Raveendra_Madala
 */
public interface IDCMComparer {

    /**
     * Method for getting the applicable updates
     *
     * @param inInventory specifies the inventory information
     * @param inIdentifier specifies the system or group identifier
     * @param inManifest specifies the catalog
     * @param inConsideration specifies whether to report updates/downgrades
     * while determining the applicable updates
     * @return the applicable updates
     */
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(DCMMultiSystemInventory inInventory, String inIdentifier, DCMManifest inManifest, DCMConsiderationEnum inConsideration);

    /**
     * Method for getting the applicable updates
     *
     * @param inInventory specifies the inventory information
     * @param inIdentifier specifies the system or group identifier
     * @param inManifest specifies the catalog
     * @param inBundleType specifies the interested bundle type
     * @param inConsideration specifies whether to report updates/downgrades
     * while determining the applicable updates
     * @return the applicable updates
     */
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(DCMMultiSystemInventory inInventory, String inIdentifier, DCMManifest inManifest, DCMBundleType inBundleType, DCMConsiderationEnum inConsideration);

    /**
     * Method for getting the non-compliant updates with respect to the base
     * system
     *
     * @param inInventory specifies the complete inventory
     * @param inBaseSystemIdentifier specifies the base system identifier with
     * which the comparison is to be done
     * @param inSystemIdentifier specifies the system identifier for which the
     * comparison is to be done
     * @param inConsideration specifies whether to take base-inventory-instance
     * into consideration or not.
     * @return non-compliant updates from the base inventory
     */
    public HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> getNonCompliantUpdates(DCMMultiSystemInventory inInventory, String inBaseSystemIdentifier, String inSystemIdentifier, DCMInstanceConsiderationEnum inConsideration);

    /**
     * Method for getting the non-compliant updates with respect to the base
     * system
     *
     * @param inTargetInventory specifies the target multi-system-inventory
     * @param inBaseInventory specifies the base multi-system-inventory
     * @param inConsideration specifies whether to take base-inventory-instance
     * into consideration or not.
     * @return non-compliant updates from the base inventory
     */
    public HashMap<String, HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>>> getNonCompliantUpdates(DCMMultiSystemInventory inTargetInventory, DCMMultiSystemInventory inBaseInventory, DCMInstanceConsiderationEnum inConsideration);

    /**
     * Method for getting the non-compliant updates with respect to the base
     * inventory
     *
     * @param inBaseInventory specifies the base inventory against which the
     * comparison would be done
     * @param inInventoryToCompare specifies the inventory that would be
     * compared against the base inventory
     * @return non-compliant updates from the base inventory
     * @param inConsideration specifies whether to take base-inventory-instance
     * into consideration or not.
     */
    public HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> getNonCompliantUpdates(File inBaseInventory, File inInventoryToCompare, DCMInstanceConsiderationEnum inConsideration);

    /**
     * Method for getting the applicable updates
     *
     * @param inInventoryCollection specifies the inventory information
     * @param inSystemIdentifier specifies the system or group identifier
     * @param inCatalogFile specifies the catalog
     * @param inConsideration specifies whether to report updates/downgrades
     * while determining the applicable updates
     * @return the applicable updates
     */
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(HashSet<File> inInventoryCollection, String inSystemIdentifier, File inCatalogFile, DCMConsiderationEnum inConsideration);

    /**
     * Method for getting the applicable updates
     *
     * @param inMultiInventoryFile specifies the multi inventory File path
     * @param inCatalogFile specifies the catalog
     * @param inConsideration specifies whether to report updates/downgrades
     * while determining the applicable updates
     * @return the applicable updates
     */
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(File inMultiInventoryFile, File inCatalogFile, DCMConsiderationEnum inConsideration);

    /**
     * Method for getting the applicable updates
     *
     * @param inHashSetDCMSystemInventory specifies the set of inventories
     * @param inCatalogFile specifies the catalog
     * @param inBundleType specifies the interested bundle type
     * @param inConsideration specifies whether to report updates/downgrades
     * while determining the applicable updates
     * @return the applicable updates
     */
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(HashSet<DCMSystemInventory> inHashSetDCMSystemInventory, File inCatalogFile, DCMBundleType inBundleType, DCMConsiderationEnum inConsideration);

    /**
     * Method for getting the applicable updates
     *
     * @param inMultiSystemInventoryObj specifies the multi inventory object
     * @param inCatalogFile specifies the catalog
     * @param bundleType specifies the interested bundle type
     * @param inConsideration specifies whether to report updates/downgrades
     * while determining the applicable updates
     * @return the applicable updates
     */
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(DCMMultiSystemInventory inMultiSystemInventoryObj, File inCatalogFile, DCMBundleType bundleType, DCMConsiderationEnum inConsideration);
    
    /**
     * Method to compare two catalogs
     * @param inSourceCatalog specifies the source catalog file
     * @param inLatestCatalog specifies the latest catalog file
     * @return return catalog comparison 
     */
    public DCMCatalogComparisonResult compareCatalogs(File inSourceCatalog , File inLatestCatalog);
    
    /**
     * Method to compare two catalog
     * @param inSourceManifest specifies the source manifest
     * @param inLatestManifest specifies the latest manifest
     * @return catalog comparison
     */
    public DCMCatalogComparisonResult compareCatalogs(DCMManifest inSourceManifest , DCMManifest inLatestManifest);
}
