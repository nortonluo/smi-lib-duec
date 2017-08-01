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

import com.dell.cm.inventory.DCMInventory;
import com.dell.cm.updateinformationmodel.DCMBundle;
import com.dell.cm.updateinformationmodel.DCMBundleCollection;
import com.dell.cm.updateinformationmodel.DCMBundleComparisonResult;
import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMCatalogComparisonResult;
import com.dell.cm.updateinformationmodel.DCMComparisonResultType;
import com.dell.cm.updateinformationmodel.DCMComponentComparisonResult;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMSystem;
import com.dell.cm.updateinformationmodel.DCMSystemInstance;
import com.dell.cm.updateinformationmodel.DCMSystemInstanceCollection;
import com.dell.cm.updateinformationmodel.DCMSystemInventory;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import com.dell.cm.updateinformationmodel.DCMVersionComparison;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import com.dell.cm.updateinformationmodel.DCMVersionInformationCollection;
import com.dell.sm.extracter.DCMPackageExtractorLLXP;
import com.dell.sm.extracter.DCMPackageExtractorLWXP;
import com.dell.sm.extracter.IDCMPackageExtractor;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class for implementing the comparer interface
 *
 * @author Raveendra_Madala
 */
public class DCMComparer implements IDCMComparer {

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
    @Override
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(DCMMultiSystemInventory inInventory, String inIdentifier, DCMManifest inManifest, DCMConsiderationEnum inConsideration) {
        return getApplicableUpdates(inInventory, inIdentifier, inManifest, DCMBundleType.BTW32, inConsideration);
    }

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
    @Override
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(DCMMultiSystemInventory inInventory, String inIdentifier, DCMManifest inManifest, DCMBundleType inBundleType, DCMConsiderationEnum inConsideration) {
        if (inInventory == null || inIdentifier == null || inManifest == null) {
            return null;
        }
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> retVal = new HashMap<>();
        // First get the relevant bundle identifiers for the given identifier
        HashMap<String, Collection<String>> relevantBundleIdentifiers = inManifest.getRelevantBundleIdentifiers(inInventory, inIdentifier, inBundleType);
        // For each of the system instance get the applicable updates
        logger.log(Level.INFO, "Relevant Bundle identifiers: " + relevantBundleIdentifiers.size());
        logger.log(Level.INFO, "Relevant Bundle Identifiers KeySet: " + relevantBundleIdentifiers.keySet().toString());
        for (String systemInstnaceIdentifier : relevantBundleIdentifiers.keySet()) {
            logger.log(Level.INFO, "SystemInstanceIdentifier: " + systemInstnaceIdentifier);
            logger.log(Level.INFO, "SystemIdentifier: " + inInventory.getSystemIdentifier(systemInstnaceIdentifier));
            // Get the version information collecction for the system instance
            DCMVersionInformationCollection versionInformationCollection = inInventory.getVersionInformationCollection(systemInstnaceIdentifier);
            Collection<String> bundleIdentifiers = relevantBundleIdentifiers.get(systemInstnaceIdentifier);
            // TODO choose the proper bundle if more than one is there. For now we will choose the first bundle
            logger.log(Level.INFO, "Bundle Identifier: " + bundleIdentifiers.size());
            if (bundleIdentifiers == null || bundleIdentifiers.isEmpty()) {
                continue;
            }
            Iterator<String> itBundleIdentifier = bundleIdentifiers.iterator();
            String bundleIdentifier = itBundleIdentifier.next();
            DCMBundle bundle = inManifest.getBundle(bundleIdentifier);
            if (bundle == null) {
                continue;
            }
            String osIdentifier = inInventory.getOSIdentifier(versionInformationCollection);
            String systemTypeIdentifier = inInventory.getSystemIdentifier(systemInstnaceIdentifier);
            // Get Probable updates in the bundle for this system
            HashMap<DCMVersionInformation, DCMUpdateInformation> applicableUpdates
                    = inManifest.getApplicableUpdates(bundleIdentifier, versionInformationCollection, systemTypeIdentifier, osIdentifier, inConsideration);
            logger.log(Level.INFO, "Applicable Updates: " + applicableUpdates.size());
            if (DCMBundleType.BTALL == inBundleType) {
                while (itBundleIdentifier.hasNext()) {
                    bundleIdentifier = itBundleIdentifier.next();
                    applicableUpdates.putAll(inManifest.getApplicableUpdates(bundleIdentifier, versionInformationCollection, systemTypeIdentifier, osIdentifier, inConsideration));
                }
            } else if (DCMBundleType.BTWMIX == inBundleType && bundleIdentifiers.size() > 1) {
                // Handle the situation where MIX of windows component updates
                while (itBundleIdentifier.hasNext()) {
                    bundleIdentifier = itBundleIdentifier.next();
                    DCMBundle bundle2 = inManifest.getBundle(bundleIdentifier);
                    if (bundle2 != null) {
                        // Get Probable updates in the bundle for this system
                        HashMap<DCMVersionInformation, DCMUpdateInformation> applicableUpdates1 = applicableUpdates;
                        HashMap<DCMVersionInformation, DCMUpdateInformation> applicableUpdates2
                                = inManifest.getApplicableUpdates(bundleIdentifier, versionInformationCollection, systemTypeIdentifier, osIdentifier, inConsideration);
                        if (bundle2.getType() == DCMBundleType.BTW64) {
                            applicableUpdates1 = applicableUpdates2;
                            applicableUpdates2 = applicableUpdates;
                        }

                        for (Entry<DCMVersionInformation, DCMUpdateInformation> update1 : applicableUpdates1.entrySet()) {
                            DCMVersionInformation availableitem = null;
                            for (Entry<DCMVersionInformation, DCMUpdateInformation> update2 : applicableUpdates2.entrySet()) {
                                if (update1.getKey().getTargetIdentifier().equals(update2.getKey().getTargetIdentifier())) {
                                    availableitem = update2.getKey();
                                    break;
                                }
                            }
                            if (availableitem != null) {
                                applicableUpdates2.remove(availableitem);
                            }
                        }
                        applicableUpdates1.putAll(applicableUpdates2);
                        applicableUpdates = applicableUpdates1;
                    }
                }
            }
            retVal.put(systemInstnaceIdentifier, applicableUpdates);
        }
        return retVal;
    }

    /**
     * Method for getting the non-compliant updates with respect to the base
     * system
     *
     * @param inInventory specifies the complete inventory
     * @param inBaseSystemIdentifier specifies the base system identifier with
     * which the comparison is to be done
     * @param inSystemIdentifier specifies the system identifier for which the
     * comparison is to be done
     * @return non-compliant updates from the base inventory
     */
    @Override
    public HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> getNonCompliantUpdates(DCMMultiSystemInventory inInventory, String inBaseSystemIdentifier, String inSystemIdentifier, DCMInstanceConsiderationEnum inConsideration) {
        if (null == inInventory || null == inBaseSystemIdentifier || null == inSystemIdentifier) {
            return null;
        }
        HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> versionInformationMap = new HashMap<>();
        DCMVersionInformationCollection baseVersionInformationCollection = inInventory.getVersionInformationCollection(inBaseSystemIdentifier);
        DCMVersionInformationCollection targetVersionInformationCollection = inInventory.getVersionInformationCollection(inSystemIdentifier);
        HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> tempVersionInformationMap = new HashMap<>();
        tempVersionInformationMap = versionInformationCollectionComparison(baseVersionInformationCollection, targetVersionInformationCollection, inConsideration);
        if (null != tempVersionInformationMap) {
            versionInformationMap.putAll(tempVersionInformationMap);
        }
        return versionInformationMap;
    }

    /**
     * Method for getting the non-compliant updates with respect to the base
     * inventory
     *
     * @param inBaseInventory specifies the base inventory against which the
     * comparison would be done
     * @param inInventoryToCompare specifies the inventory that would be
     * compared against the base inventory
     * @return non-compliant updates from the base inventory
     */
    @Override
    public HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> getNonCompliantUpdates(File inBaseInventory, File inInventoryToCompare, DCMInstanceConsiderationEnum inConsideration) {
        if (null == inBaseInventory || null == inInventoryToCompare) {
            return null;
        }
        DCMInventory inventoryImplementation = new DCMInventory();
        DCMMultiSystemInventory baseInventory = inventoryImplementation.createEmptyMultiSystemInventory();
        int inventoryParsingResult = inventoryImplementation.addInventory(inBaseInventory, "", baseInventory, Boolean.FALSE);
        if (baseInventory == null || inventoryParsingResult != DCMErrorCodes.SUCCESS) {
            return null;
        }
        DCMMultiSystemInventory targetInventory = inventoryImplementation.createEmptyMultiSystemInventory();
        inventoryParsingResult = inventoryImplementation.addInventory(inInventoryToCompare, "", targetInventory, Boolean.FALSE);
        if (targetInventory == null || inventoryParsingResult != DCMErrorCodes.SUCCESS) {
            return null;
        }
        if (!baseInventory.getLineOfBusinessCollection().contains(targetInventory.getLineOfBusinessCollection())) {
            return null;
        }
        if (!baseInventory.getSystemCollection().contains(targetInventory.getSystemCollection())) {
            return null;
        }
        if (!baseInventory.getOSCollection().contains(targetInventory.getOSCollection())) {
            return null;
        }
        HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> versionInformationMap = new HashMap<>();
        if (null == targetInventory.getCollectionOfVersionInformationCollections()) {
            return null;
        }
        // For each systemInstance in target Inventory, compare the versions of each devices against the same in Base Inventory.
        for (DCMSystemInstance systemInstance : targetInventory.getSystemInstanceCollection().getSystemInstances()) {
            String targetSystemIdentifier = systemInstance.getUniqueID();
            DCMVersionInformationCollection baseVersionInformationCollection = baseInventory.getVersionInformationCollection(targetSystemIdentifier);
            if (null == targetInventory.getVersionInformationCollection(targetSystemIdentifier)) {
                continue;
            }
            HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> tempVersionInformationMap = new HashMap<>();
            tempVersionInformationMap = versionInformationCollectionComparison(baseVersionInformationCollection, targetInventory.getVersionInformationCollection(targetSystemIdentifier), inConsideration);
            if (null == tempVersionInformationMap) {
                continue;
            }
            for (DCMComparerResultType resultType : tempVersionInformationMap.keySet()) {
                if (versionInformationMap.containsKey(resultType)) {
                    versionInformationMap.get(resultType).addAll(tempVersionInformationMap.get(resultType));
                } else {
                    versionInformationMap.put(resultType, tempVersionInformationMap.get(resultType));
                }
            }
        }
        return versionInformationMap;
    }

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
    @Override
    public HashMap<String, HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>>> getNonCompliantUpdates(DCMMultiSystemInventory inTargetInventory, DCMMultiSystemInventory inBaseInventory, DCMInstanceConsiderationEnum inConsideration) {
        if (null == inTargetInventory || null == inBaseInventory) {
            return null;
        }
        HashMap<String, HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>>> versionInformationMap = new HashMap<>();
        for (DCMSystemInstance targetSystemInstance : inTargetInventory.getSystemInstanceCollection().getSystemInstances()) {
            String targetSystemInstanceTypeIdentifier = targetSystemInstance.getSystemTypeIdentifier();
            String targetSystemInstanceUniqueIdentifier = targetSystemInstance.getUniqueID();
            DCMVersionInformationCollection targetVersionInformationCollection = inTargetInventory.getVersionInformationCollection(targetSystemInstanceUniqueIdentifier);
            for (DCMSystemInstance baseSystemInstance : inBaseInventory.getSystemInstanceCollection().getSystemInstances()) {
                String baseSystemInstanceTypeIdentifier = baseSystemInstance.getSystemTypeIdentifier();
                String baseSystemInstanceUniqueIdentifier = baseSystemInstance.getUniqueID();
                if (null == baseSystemInstanceTypeIdentifier || !baseSystemInstanceTypeIdentifier.equals(targetSystemInstanceTypeIdentifier)) {
                    continue;
                }
                DCMVersionInformationCollection baseVersionInformationCollection = inBaseInventory.getVersionInformationCollection(baseSystemInstanceUniqueIdentifier);
                HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> tempVersionInformationMap = new HashMap<>();
                tempVersionInformationMap = (versionInformationCollectionComparison(baseVersionInformationCollection, targetVersionInformationCollection, inConsideration));
                if (null != tempVersionInformationMap) {
                    versionInformationMap.put(targetSystemInstanceUniqueIdentifier, tempVersionInformationMap);
                }
            }
        }
        return versionInformationMap;
    }

    private DCMVersionInformation getMatchedVersionInformation(DCMVersionInformationCollection inVersionInformationCollection, String inVersionInformationIdentifier, DCMInstanceConsiderationEnum inConsideration) {
        if (null == inVersionInformationCollection) {
            return null;
        }
        DCMVersionInformation matchedVersionInformation = null;
        DCMComparisonResultType comparisonResult;

        for (DCMVersionInformation versionInformationInBase : inVersionInformationCollection.getVersionInformationObjects()) {
            String baseDeviceIdentifier = versionInformationInBase.getUpdateableComponentIdentifier();
            if (baseDeviceIdentifier.equalsIgnoreCase(inVersionInformationIdentifier)) {
                if (matchedVersionInformation == null) {
                    matchedVersionInformation = versionInformationInBase;
                } else {
                    DCMVersionComparison versionComparison = new DCMVersionComparison(versionInformationInBase.getVersion(), matchedVersionInformation.getVersion());
                    comparisonResult = versionComparison.compare();
                    if (inConsideration == DCMInstanceConsiderationEnum.CONSIDER_MAX_INSTANCE_VERSION && comparisonResult == DCMComparisonResultType.GREATER) {
                        matchedVersionInformation = versionInformationInBase;
                    } else if (inConsideration == DCMInstanceConsiderationEnum.CONSIDER_MIN_INSTANCE_VERSION && comparisonResult == DCMComparisonResultType.LOWER) {
                        matchedVersionInformation = versionInformationInBase;
                    }
                }
            }
        }
        return matchedVersionInformation;
    }

    private HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> versionInformationCollectionComparison(DCMVersionInformationCollection inBaseVersionInformationCollection, DCMVersionInformationCollection inTargetVersionInformationCollection, DCMInstanceConsiderationEnum inConsideration) {
        if (null == inTargetVersionInformationCollection || null == inBaseVersionInformationCollection) {
            return null;
        }
        HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> versionInformationMap = new HashMap<>();
        DCMVersionInformationComparisonResult versionInformationComparisonResult;
        for (DCMVersionInformation versionInformationInTarget : inTargetVersionInformationCollection.getVersionInformationObjects()) {
            String targetDeviceInstanceIdentifier = versionInformationInTarget.getUniqueIdentifier();
            String targetDeviceIdentifier = versionInformationInTarget.getUpdateableComponentIdentifier();
            DCMVersionInformation baseVersionInformation = null;
            if (inConsideration == DCMInstanceConsiderationEnum.CONSIDER_INSTANCE_STRICTLY) {
                baseVersionInformation = inBaseVersionInformationCollection.getVersionInformation(targetDeviceInstanceIdentifier);
            } else {
                baseVersionInformation = getMatchedVersionInformation(inBaseVersionInformationCollection, targetDeviceIdentifier, inConsideration);
            }

            // Setting source versionInformation and destination versionInformation in versionInformationComparisonResult Object.           
            versionInformationComparisonResult = new DCMVersionInformationComparisonResult();
            versionInformationComparisonResult.setSourceVersion(baseVersionInformation);
            versionInformationComparisonResult.setDestinationVersion(versionInformationInTarget);

            if (baseVersionInformation == null) {
                if (versionInformationMap.containsKey(DCMComparerResultType.NOT_PRESENT_IN_SOURCE)) {
                    versionInformationMap.get(DCMComparerResultType.NOT_PRESENT_IN_SOURCE).add(versionInformationComparisonResult);
                } else {
                    versionInformationMap.put(DCMComparerResultType.NOT_PRESENT_IN_SOURCE, addVersionInformationObjectInCollection(versionInformationComparisonResult));
                }
                continue;
            }
            String baseVersion = baseVersionInformation.getVersion();
            DCMVersionComparison versionComparison = new DCMVersionComparison(baseVersion, versionInformationInTarget.getVersion());
            DCMComparisonResultType comparisonResult = versionComparison.compare();

            if (comparisonResult == DCMComparisonResultType.EQUAL) {
                if (versionInformationMap.containsKey(DCMComparerResultType.EQUAL)) {
                    versionInformationMap.get(DCMComparerResultType.EQUAL).add(versionInformationComparisonResult);
                } else {
                    versionInformationMap.put(DCMComparerResultType.EQUAL, addVersionInformationObjectInCollection(versionInformationComparisonResult));
                }
            } else if (comparisonResult == DCMComparisonResultType.GREATER) {
                if (versionInformationMap.containsKey(DCMComparerResultType.UPGRADE)) {
                    versionInformationMap.get(DCMComparerResultType.UPGRADE).add(versionInformationComparisonResult);
                } else {
                    versionInformationMap.put(DCMComparerResultType.UPGRADE, addVersionInformationObjectInCollection(versionInformationComparisonResult));
                }
            } else if (versionComparison.compare() == DCMComparisonResultType.LOWER) {
                if (versionInformationMap.containsKey(DCMComparerResultType.DOWNGRADE)) {
                    versionInformationMap.get(DCMComparerResultType.DOWNGRADE).add(versionInformationComparisonResult);
                } else {
                    versionInformationMap.put(DCMComparerResultType.DOWNGRADE, addVersionInformationObjectInCollection(versionInformationComparisonResult));
                }
            }
        }
        if (inConsideration == DCMInstanceConsiderationEnum.CONSIDER_INSTANCE_STRICTLY) {
            // Testing for components that are not present in target inventory w.r.t base inventory
            for (DCMVersionInformation versionInformationInbase : inBaseVersionInformationCollection.getVersionInformationObjects()) {
                String versionInformationInBaseIdentifier = versionInformationInbase.getUniqueIdentifier();
                DCMVersionInformation targetVersionInformation = inTargetVersionInformationCollection.getVersionInformation(versionInformationInBaseIdentifier);

                if (null == targetVersionInformation) {
                    versionInformationComparisonResult = new DCMVersionInformationComparisonResult();
                    versionInformationComparisonResult.setSourceVersion(versionInformationInbase);
                    versionInformationComparisonResult.setDestinationVersion(targetVersionInformation);

                    if (versionInformationMap.containsKey(DCMComparerResultType.NOT_PRESENT_IN_DESTINATION)) {
                        versionInformationMap.get(DCMComparerResultType.NOT_PRESENT_IN_DESTINATION).add(versionInformationComparisonResult);
                    } else {
                        versionInformationMap.put(DCMComparerResultType.NOT_PRESENT_IN_DESTINATION, addVersionInformationObjectInCollection(versionInformationComparisonResult));
                    }
                }
            }
        }
        return versionInformationMap;
    }

    private Collection<DCMVersionInformationComparisonResult> addVersionInformationObjectInCollection(DCMVersionInformationComparisonResult inVersionInformationComparisonResultObject) {
        Set<DCMVersionInformationComparisonResult> tempCollection = new HashSet<>();
        tempCollection.add(inVersionInformationComparisonResultObject);
        return tempCollection;
    }

    @Override
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(HashSet<File> inInventoryCollection, String inSystemIdentifier, File inCatalogFile, DCMConsiderationEnum inConsideration) {
        if (null == inInventoryCollection || inInventoryCollection.isEmpty()) {
            return null;
        }
        // HashMap to store the output of getApplicableUpdates
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> applicableUpdatesMap = new HashMap<>();

        DCMCatalog catalogObj = new DCMCatalog();
        if (!inCatalogFile.exists() || !inCatalogFile.canRead()) {
            return null;
        }
        DCMManifest manifestObj = catalogObj.parseCatalog(inCatalogFile);

        DCMInventory inventoryObj = new DCMInventory();
        DCMMultiSystemInventory multiSystemInventoryObj = inventoryObj.createEmptyMultiSystemInventory();
        // adding inventoryCollection to multiSystemInventory obj with inSystemIdentifier as identifier.
        if (DCMErrorCodes.SUCCESS != inventoryObj.addInventory(inInventoryCollection, inSystemIdentifier, multiSystemInventoryObj)) {
            return null;
        }

        DCMSystemInstanceCollection dcmSystemInstanceCollection = multiSystemInventoryObj.getSystemInstanceCollection();
        Collection<DCMSystemInstance> SystemInstanceCollection = dcmSystemInstanceCollection.getSystemInstances();
        String systemIdentifier = "";
        for (DCMSystemInstance systemInstance : SystemInstanceCollection) {
            systemIdentifier = systemInstance.getServiceTag();
            HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> tempAppicableUpdatesMap
                    = getApplicableUpdates(multiSystemInventoryObj, systemIdentifier, manifestObj, DCMConsiderationEnum.REPORT_ALL);
            if (null != tempAppicableUpdatesMap) {
                applicableUpdatesMap.putAll(tempAppicableUpdatesMap);
            }
        }
        return applicableUpdatesMap;
    }

    @Override
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(File inMultiInventoryFile, File inCatalogFile, DCMConsiderationEnum inConsideration) {
        // HashMap to store the output of getApplicableUpdates
        logger.log(Level.INFO, "Inside getApplicable Updates ");

        DCMInventory inventoryObj = new DCMInventory();
        DCMMultiSystemInventory multiSystemInventoryObj = inventoryObj.createEmptyMultiSystemInventory();
        if (!inMultiInventoryFile.exists() || !inMultiInventoryFile.canRead()) {
            return null;
        }

        logger.log(Level.INFO, "deserializing a multi inventory XML file" + inMultiInventoryFile);
        if (DCMErrorCodes.SUCCESS != inventoryObj.deserializeXMLFile(inMultiInventoryFile, multiSystemInventoryObj)) {
            return null;
        }

        return getApplicableUpdates(multiSystemInventoryObj, inCatalogFile, DCMBundleType.BTW32, inConsideration);
    }

    /**
     * Method for getting the applicable updates
     *
     * @param inHashSetDCMSystemInventory specifies the set of inventories
     * @param inCatalogFile specifies the catalog
     * @param inBundleType specifies bundle type
     * @param inConsideration specifies whether to report updates/downgrades
     * while determining the applicable updates
     * @return the applicable updates
     */
    @Override
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(HashSet<DCMSystemInventory> inHashSetDCMSystemInventory, File inCatalogFile, DCMBundleType inBundleType, DCMConsiderationEnum inConsideration) {
        if (null == inHashSetDCMSystemInventory || inHashSetDCMSystemInventory.isEmpty()) {
            return null;
        }
        DCMInventory inventory = new DCMInventory();
        DCMMultiSystemInventory multiSystemInventoryObj = inventory.createEmptyMultiSystemInventory();

        for (DCMSystemInventory systemInventory : inHashSetDCMSystemInventory) {
            if (null != systemInventory) {
                inventory.addInventory(systemInventory, multiSystemInventoryObj);
            }
        }
        logger.log(Level.INFO, "multisystemInventory Object created from hashset of DCMSystemInventory");
        return getApplicableUpdates(multiSystemInventoryObj, inCatalogFile, inBundleType, inConsideration);
    }

    /**
     * Getting the applicable updates
     *
     * @param inHashSetDCMSystemInventory specifies DCMSystemInventory object
     * @param packageFilePath specifies dup path
     * @param inBundleType specifies type of update to consider
     * @param inConsideration specifies whether to report updates/downgrades
     * while determining the applicable updates
     * @return the applicable updates
     */
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdatesByUpdatePackage(HashSet<DCMSystemInventory> inHashSetDCMSystemInventory, String packageFilePath, DCMBundleType inBundleType, DCMConsiderationEnum inConsideration) {
        logger.log(Level.INFO, "Inside getApplicableUpdatesByUpdatePackage method");

        List<DCMSystem> listOfSystem = new ArrayList<DCMSystem>();
        List<File> listExeBinFile = new ArrayList<File>();
        DCMInventory inventory = new DCMInventory();
        DCMMultiSystemInventory multiSystemInventoryObj = inventory.createEmptyMultiSystemInventory();

        packageFilePath = packageFilePath.replace(BACKWARD_SLASH, FORWARD_SLASH);
        File exeBinFile = new File(packageFilePath);

        logger.log(Level.INFO, "Adding DCMSystem to list");
        if (inHashSetDCMSystemInventory != null) {
            Iterator it = inHashSetDCMSystemInventory.iterator();
            while (it.hasNext()) {
                DCMSystemInventory systeminventory = (DCMSystemInventory) it.next();
                DCMSystem system = systeminventory.getSystem();
                listOfSystem.add(system);
            }
        }

        String inBaseLocation = packageFilePath.substring(0, packageFilePath.lastIndexOf("/"));
        listExeBinFile.add(exeBinFile);

        logger.log(Level.INFO, "Getting DCMManifest object by passing baselocation, PackageFilePath, list of DCMSystem");
        DCMCatalog catalogObj = new DCMCatalog();
        DCMManifest manifestObj = catalogObj.parseRepository(inBaseLocation, listExeBinFile, listOfSystem);

        logger.log(Level.INFO, "Adding systemInventory to MunltiSystemInventory Object");
        for (DCMSystemInventory systemInventory : inHashSetDCMSystemInventory) {
            if (null != systemInventory) {
                inventory.addInventory(systemInventory, multiSystemInventoryObj);
            }
        }

        DCMSystemInstanceCollection dcmSystemInstanceCollection = multiSystemInventoryObj.getSystemInstanceCollection();
        Collection<DCMSystemInstance> SystemInstanceCollection = dcmSystemInstanceCollection.getSystemInstances();

        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> applicableUpdatesMap = new HashMap<>();

        logger.log(Level.INFO, "Getting the SystemIdentofier and calling getApplicableUpdate method");
        for (DCMSystemInstance systemInstance : SystemInstanceCollection) {
            String systemIdentifier = "";
            systemIdentifier = systemInstance.getServiceTag();
            HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> tempAppicableUpdatesMap
                    = getApplicableUpdates(multiSystemInventoryObj, systemIdentifier, manifestObj, inBundleType, inConsideration);
            if (null != tempAppicableUpdatesMap) {
                applicableUpdatesMap.putAll(tempAppicableUpdatesMap);
            }
        }

        logger.log(Level.INFO, "End of  getApplicableUpdatesByUpdatePackage method");
        return applicableUpdatesMap;
    }

    /**
     * Method for getting the applicable updates
     *
     * @param inMultiSystemInventoryObj specifies the multi inventory object
     * @param inCatalogFile specifies the catalog
     * @param inConsideration specifies whether to report updates/downgrades
     * while determining the applicable updates
     * @param bundleType specifies the interested bundle type
     * @return the applicable updates
     */
    @Override
    public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(DCMMultiSystemInventory inMultiSystemInventoryObj, File inCatalogFile, DCMBundleType bundleType, DCMConsiderationEnum inConsideration) {
        // adding inventoryCollection to multiSystemInventory obj with inSystemIdentifier as identifier.

        DCMCatalog catalogObj = new DCMCatalog();
        if (!inCatalogFile.exists() || !inCatalogFile.canRead()) {
            return null;
        }
        DCMManifest manifestObj = catalogObj.parseCatalog(inCatalogFile);
        DCMSystemInstanceCollection dcmSystemInstanceCollection = inMultiSystemInventoryObj.getSystemInstanceCollection();
        Collection<DCMSystemInstance> SystemInstanceCollection = dcmSystemInstanceCollection.getSystemInstances();

        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> applicableUpdatesMap = new HashMap<>();

        for (DCMSystemInstance systemInstance : SystemInstanceCollection) {
            String systemIdentifier = "";
            systemIdentifier = systemInstance.getServiceTag();
            HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> tempAppicableUpdatesMap = new HashMap<>();
            tempAppicableUpdatesMap = getApplicableUpdates(inMultiSystemInventoryObj, systemIdentifier, manifestObj, bundleType, inConsideration);
            if (null != tempAppicableUpdatesMap) {
                applicableUpdatesMap.putAll(tempAppicableUpdatesMap);
            }
        }
        return applicableUpdatesMap;
    }

    /**
     * Method for getting the applicable bundles
     *
     * @param inCatalogFile specifies the catalog
     * @param systemIdentifiers list of system identifiers
     * @param inBundleType specifies the interested bundle type
     * @return the applicable bundles
     */
    public Collection<DCMBundle> getApplicableBundles(File inCatalogFile, Collection<String> systemIdentifiers, DCMBundleType inBundleType) {
        if (null == systemIdentifiers || systemIdentifiers.isEmpty()) {
            return null;
        }
        Collection<DCMBundle> result = new HashSet<>();
        DCMCatalog catalog = new DCMCatalog();

        DCMManifest manifest = catalog.parseCatalog(inCatalogFile);
        DCMBundleCollection bundleCollection = manifest.getBundleCollection();
        Collection<DCMBundle> bundles = bundleCollection.getBundles();
        for (String systemIdentifier : systemIdentifiers) {
            for (DCMBundle bundle : bundles) {
                Collection<String> supportedSystemIds = bundle.getTargetSystemIdentifiers();
                if (inBundleType != DCMBundleType.BTALL && inBundleType != bundle.getType()) {
                    if (inBundleType == DCMBundleType.BTWMIX && (DCMBundleType.BTW32 != bundle.getType() || DCMBundleType.BTW64 != bundle.getType())) {
                        continue;
                    }
                }
                for (String supportedSystemId : supportedSystemIds) {
                    if (supportedSystemId.contains(systemIdentifier)) {
                        result.add(bundle);
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Getting the Package.xml information
     *
     * @param packageFilePath specifies path to package.xml
     * @return DCMUpdatePackageInformation object
     */
    public DCMUpdatePackageInformation getUpdatePackageInformation(String packageFilePath) {
        logger.log(Level.INFO, "Inside getUpdatePackageInformation");

        File exeFile = new File(packageFilePath);

        IDCMPackageExtractor pe = new DCMPackageExtractorLWXP(exeFile);
        if (exeFile.getName().toUpperCase().endsWith(".BIN")) {
            pe = new DCMPackageExtractorLLXP(exeFile);
        }
        logger.log(Level.INFO, "Getting package file by calling  extractPackage method");
        String extractedPackage = pe.extractPackageXML();

        if (extractedPackage != null) {
            DCMCatalog catalog = new DCMCatalog();
            DCMUpdatePackageInformation packageInformation = catalog.parseSoftwareComponent(extractedPackage, packageFilePath);
            if (packageInformation != null) {
                //Calculate md5 Digest
                MessageDigest md = null;
                try {
                    md = MessageDigest.getInstance("MD5");

                    FileInputStream fis = new FileInputStream(exeFile);
                    FileChannel fc = fis.getChannel();
                    ByteBuffer bbf = ByteBuffer.allocate(1024); // allocation in bytes

                    while (fc.read(bbf) != -1) {
                        md.update(bbf);
                        bbf.clear();
                    }
                    packageInformation.setMD5Hash(new BigInteger(1, md.digest()).toString(16));
                    packageInformation.setSize(exeFile.length());
                    packageInformation.setPath(packageFilePath);

                    fc.close();
                    fis.close();
                } catch (Exception ex) {
                    Logger.getLogger(DCMComparer.class.getName()).log(Level.SEVERE, null, ex);
                }

                return packageInformation;
            }
        }
        return null;
    }

    /**
     * Method to compare two catalogs
     *
     * @param inSourceCatalog Source catalog file
     * @param inLatestCatalog Latest catalog file
     * @return DCMCatalogComparisonReult
     */
    public DCMCatalogComparisonResult compareCatalogs(File inSourceCatalog, File inLatestCatalog) {
        DCMCatalog catalogObj = new DCMCatalog();
        DCMManifest sourceManifest = catalogObj.parseCatalog(inSourceCatalog);
        DCMManifest latestManifest = catalogObj.parseCatalog(inLatestCatalog);
        DCMCatalogComparisonResult result = compareCatalogs(sourceManifest, latestManifest);
        return result;
    }

    /**
     * Method to compare two catalogs
     *
     * @param sourceManifest source manifest
     * @param latestManifest latest manifest
     * @return DCMCatalogComparisonResult
     */
    public DCMCatalogComparisonResult compareCatalogs(DCMManifest sourceManifest, DCMManifest latestManifest) {
        DCMCatalogComparisonResult result = new DCMCatalogComparisonResult();
        // source manifest bundles
        Collection<DCMBundle> sourceBundles = sourceManifest.getBundleCollection().getBundles();

        //destination manifest bundles
        Collection<DCMBundle> latestBundles = latestManifest.getBundleCollection().getBundles();
        logger.log(Level.INFO, "Comparing catalogs");
        HashMap<String, DCMBundle> sourceBundleMap = new HashMap<>();
        HashMap<String, DCMBundle> latestBundleMap = new HashMap<>();
        List<String> mEqualBundleIdentifiers = new ArrayList<>();
        List<String> mExclusiveSourceBundleIdentifiers = new ArrayList<>();
        List<String> mExclusiveLatestBundleIndetifiers = new ArrayList<>();
        List<String> mExclusiveSourceComponentIdentifiers = new ArrayList<>();
        List<String> mExclusiveLatestComponentIdentifiers = new ArrayList<>();
        List<String> mEqualComponentIdentifiers = new ArrayList<>();
        HashMap<String, DCMComponentComparisonResult> mComponentDifferences = new HashMap<>();
        HashSet<String> checkList = new HashSet<>();
        HashMap<String, DCMBundleComparisonResult> mBundleDifferences = new HashMap<>();
        DCMComponentComparisonResult componentComparisonResult;
        DCMComparisonResultType comparisonResult;

        for (DCMBundle bundle : sourceBundles) {
            sourceBundleMap.put(bundle.getBundleID().split("\\.")[0], bundle);
        }
        for (DCMBundle bundle : latestBundles) {
            latestBundleMap.put(bundle.getBundleID().split("\\.")[0], bundle);
        }
        /**
         * Comparing bundles
         */
        logger.log(Level.INFO, "Comparing bundles");
        for (Entry<String, DCMBundle> entry : sourceBundleMap.entrySet()) {
            String key = entry.getKey();
            if (latestBundleMap.containsKey(key)) {
                checkList.add(key);
                DCMBundle sourceBundle = entry.getValue();
                DCMBundle latestBundle = latestBundleMap.get(key);
                // if packages in the bundles are equal
                if (sourceBundle.getUpdatePackageIdentifiers().containsAll(latestBundle.getUpdatePackageIdentifiers())) {
                    mEqualBundleIdentifiers.add(sourceBundle.getUniqueIdentifier());
                } //compare the bundles if the packages in the bundle are not equal
                else {
                    DCMBundleComparisonResult bundleRetVal = bundleComparison(sourceBundle, latestBundle, sourceManifest, latestManifest);
                    mBundleDifferences.put(key, bundleRetVal);
                }
            } // bundles exclusive to source catalog
            else {
                mExclusiveSourceBundleIdentifiers.add(key);
            }
        }
        //bundles exclusive to latest manifest
        for (String identifier : latestBundleMap.keySet()) {
            if (!checkList.contains(identifier)) {
                mExclusiveLatestBundleIndetifiers.add(identifier);
            }
        }
        logger.log(Level.INFO, "Equal Bundles: {0}", mEqualBundleIdentifiers.size());
        logger.log(Level.INFO, "Exlusive Bundle to source catalog: {0}", mExclusiveSourceBundleIdentifiers.size());
        logger.log(Level.INFO, "Exclusive bunndle to latest catalog: {0}", mExclusiveLatestBundleIndetifiers.size());
        logger.log(Level.INFO, "Different bundles: {0}", mBundleDifferences.size());
        /*
         To compare the the components
         */
        logger.log(Level.INFO, "Comparing components");
        //source catalog components
        Collection<DCMUpdatePackageInformation> srcUpdtPkgInfo = sourceManifest.getSoftwareComponents().getUpdatePackages();
        HashMap<String, DCMUpdatePackageInformation> srcComponentMap = new HashMap<>();
        for (DCMUpdatePackageInformation info : srcUpdtPkgInfo) {
            srcComponentMap.put(info.getUniqueIdentifier(), info);
        }
        //latest catalog components
        Collection<DCMUpdatePackageInformation> latestUpdtPkgInfo = latestManifest.getSoftwareComponents().getUpdatePackages();
        HashMap<String, DCMUpdatePackageInformation> latestComponentMap = new HashMap<>();
        checkList.clear();
        for (DCMUpdatePackageInformation info : latestUpdtPkgInfo) {
            latestComponentMap.put(info.getUniqueIdentifier(), info);
        }
        for (Entry<String, DCMUpdatePackageInformation> entry : srcComponentMap.entrySet()) {
            String key = entry.getKey();
            DCMUpdatePackageInformation srcInfo = entry.getValue();

            if (latestComponentMap.containsKey(key)) {
                checkList.add(key);
                DCMUpdatePackageInformation latestInfo = latestComponentMap.get(key);
                comparisonResult = new DCMVersionComparison(srcInfo.getVendorVersion(), latestInfo.getVendorVersion()).compare();
                if (comparisonResult == DCMComparisonResultType.EQUAL) {
                    mEqualComponentIdentifiers.add(srcInfo.getUniqueIdentifier());
                } else {
                    //components version are not equal
                    componentComparisonResult = componentComparison(srcInfo, latestInfo);
                    mComponentDifferences.put(srcInfo.getUniqueIdentifier(), componentComparisonResult);
                }
            } // components exclusive to source catalog
            else {
                mExclusiveSourceComponentIdentifiers.add(srcInfo.getUniqueIdentifier());
            }
        }
        //components exclusive to latest catalog
        for (String identifier : latestComponentMap.keySet()) {
            if (!checkList.contains(identifier)) {
                mExclusiveLatestComponentIdentifiers.add(identifier);
            }
        }
        logger.log(Level.INFO, "Equal Components: {0}", mEqualComponentIdentifiers.size());
        logger.log(Level.INFO, "Exclusive Components to source catalog: {0}", mExclusiveSourceComponentIdentifiers.size());
        logger.log(Level.INFO, "Exclusive Components to latest catalog: {0}", mExclusiveLatestComponentIdentifiers.size());
        logger.log(Level.INFO, "Different Components: {0}", mComponentDifferences.size());

        result.setEqualComponentIdentifiers(mEqualComponentIdentifiers);
        result.setExclusiveDestinationCcomponentIdentifiers(mExclusiveLatestComponentIdentifiers);
        result.setExclusiveSourceComponentIdentifiers(mExclusiveSourceComponentIdentifiers);
        result.setComponentDifferences(mComponentDifferences);
        result.setExclusiveDestinationBundleIdentifiers(mExclusiveLatestBundleIndetifiers);
        result.setBundleDifferences(mBundleDifferences);
        result.setEqualBundleIdentifiers(mEqualBundleIdentifiers);
        result.setExclusiveSourceBundleIdentifiers(mExclusiveSourceBundleIdentifiers);
        return result;
    }

    /**
     * Method to set values if the components have any difference
     *
     * @param srcUpdtPkgInfo
     * @param latestUpdtPkgInfo
     * @return DCMComponentComparisonResult
     */
    private DCMComponentComparisonResult componentComparison(DCMUpdatePackageInformation srcUpdtPkgInfo, DCMUpdatePackageInformation latestUpdtPkgInfo) {
        DCMComponentComparisonResult retVal = new DCMComponentComparisonResult();
        retVal.setSourceVersion(srcUpdtPkgInfo.getVendorVersion());
        retVal.setDestinationVersion(latestUpdtPkgInfo.getVendorVersion());
        retVal.setSourcePackage(srcUpdtPkgInfo);
        retVal.setDestinationPackage(latestUpdtPkgInfo);
        return retVal;
    }

    /**
     * Method to compare to two bundles
     *
     * @param sourceBundle
     * @param latestBundle
     * @param sourceManifest
     * @param latestManifest
     * @return DCMBundleComparisonResult
     */
    private DCMBundleComparisonResult bundleComparison(DCMBundle sourceBundle, DCMBundle latestBundle, DCMManifest sourceManifest, DCMManifest latestManifest) {
        DCMBundleComparisonResult retVal = new DCMBundleComparisonResult();
        List<String> mExclusiveSourceComponents = new ArrayList<>();
        List<String> mExclusiveLatestComponents = new ArrayList<>();
        HashMap<String, String> mUpgradedComponents = new HashMap<>();
        HashMap<String, String> mDowngradedComponents = new HashMap<>();
        HashMap<String, String> mEqualComponents = new HashMap<>();

        DCMComparisonResultType comparisonResult;
        HashSet<String> srcUpdtPkgIdentifiers = sourceBundle.getUpdatePackageIdentifiers();
        HashSet<String> latestUpdtPkgIdentifiers = latestBundle.getUpdatePackageIdentifiers();
        HashSet<String> checkIdentifier = new HashSet<>();

        for (String identifier : latestUpdtPkgIdentifiers) {
            DCMUpdatePackageInformation latestUpdateInfo = latestManifest.getSoftwareComponents().getUpdatePackageInformation(identifier);
            if (srcUpdtPkgIdentifiers.contains(identifier)) {
                checkIdentifier.add(identifier);
                DCMUpdatePackageInformation sourceUpdateInfo = sourceManifest.getSoftwareComponents().getUpdatePackageInformation(identifier);
                comparisonResult = new DCMVersionComparison(sourceUpdateInfo.getVendorVersion(), latestUpdateInfo.getVendorVersion()).compare();
                if (null != comparisonResult) {
                    switch (comparisonResult) {
                        case EQUAL:
                            mEqualComponents.put(sourceUpdateInfo.getUniqueIdentifier(), latestUpdateInfo.getUniqueIdentifier());
                            break;
                        case LOWER:
                            mUpgradedComponents.put(sourceUpdateInfo.getUniqueIdentifier(), latestUpdateInfo.getUniqueIdentifier());
                            break;
                        default:
                            mDowngradedComponents.put(sourceUpdateInfo.getUniqueIdentifier(), latestUpdateInfo.getUniqueIdentifier());
                            break;
                    }
                }
            } else {
                mExclusiveLatestComponents.add(identifier);
            }
        }
        for (String identifier : srcUpdtPkgIdentifiers) {
            if (!checkIdentifier.contains(identifier)) {
                mExclusiveSourceComponents.add(identifier);
            }
        }
        retVal.setDowngradedComponents(mDowngradedComponents);
        retVal.setUpgradedComponents(mUpgradedComponents);
        retVal.setEqualComponents(mEqualComponents);
        retVal.setExclusiveDestinationComponents(mExclusiveLatestComponents);
        retVal.setExclusiveSourceComponents(mExclusiveSourceComponents);
        retVal.setSourceVersion(sourceBundle.getVendorVersion());
        retVal.setDestinationVersion(latestBundle.getVendorVersion());

        return retVal;
    }

    private static final char FORWARD_SLASH = '/';
    private static final char BACKWARD_SLASH = '\\';

    private static Logger logger = Logger.getLogger(DCMComparer.class.getName());

    Collection<DCMVersionInformation> getNonCompliantUpdates(DCMMultiSystemInventory inInventory, String inBaseSystemIdentifier, String inSystemIdentifier, DCMConsiderationEnum dcmConsiderationEnum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
