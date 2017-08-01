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
package com.dell.cm.repository;

import com.dell.cm.comparer.DCMCatalog;
import com.dell.cm.comparer.DCMComparer;
import com.dell.cm.comparer.DCMConsiderationEnum;
import com.dell.cm.comparer.DCMUpdateInformation;
import com.dell.cm.inventory.DCMInventory;
import com.dell.cm.updateinformationmodel.DCMBaseTarget;
import com.dell.cm.updateinformationmodel.DCMBundle;
import com.dell.cm.updateinformationmodel.DCMBundleCollection;
import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMCategory;
import com.dell.cm.updateinformationmodel.DCMComponentKind;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMConstituent;
import com.dell.cm.updateinformationmodel.DCMDependency;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMGUID;
import com.dell.cm.updateinformationmodel.DCMInventoryCollectorInformation;
import com.dell.cm.updateinformationmodel.DCMInventoryCollectorInformationCollection;
import com.dell.cm.updateinformationmodel.DCMLineOfBusiness;
import com.dell.cm.updateinformationmodel.DCMLineOfBusinessCollection;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMOperatingSystem;
import com.dell.cm.updateinformationmodel.DCMOperatingSystemCollection;
import com.dell.cm.updateinformationmodel.DCMPackageType;
import com.dell.cm.updateinformationmodel.DCMSoftDependency;
import com.dell.cm.updateinformationmodel.DCMSystem;
import com.dell.cm.updateinformationmodel.DCMSystemCollection;
import com.dell.cm.updateinformationmodel.DCMSystemInstance;
import com.dell.cm.updateinformationmodel.DCMSystemInstanceCollection;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformationCollection;
import com.dell.cm.updateinformationmodel.DCMUpdateableComponent;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.downloader.DSMDownloader;
import com.dell.sm.downloader.DSMErrorCodes;
import com.dell.sm.downloader.DSMProtocolEnum;
import com.dell.sm.downloader.DSMProxy;
import com.dell.sm.downloader.IDSMLocation;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class for representing repository interface
 *
 * @author Raveendra_Madala
 */
public class DCMRepository implements IDCMRepository {

    /**
     * Method for creating the repository for the selected updates
     *
     * @param inUpdates specifies the updates to be included in the repository
     * @param inManifest specifies the source catalog
     * @param outFolder specifies the output folder
     * @param inParameters specifies the authentication parameters to be used
     * for downloading
     * @param inProxyCollection specifies the collection of proxies to be used
     * @param inProtocol specifies the protocol for DSM
     * @return SUCCESS if the repository could be created else appropriate error
     * code is returned
     */
    @Override
    public int createRepository(Collection<DCMUpdateInformation> inUpdates,
            DCMManifest inManifest, File outFolder,
            DSMAuthenticationParameters inParameters,
            Collection<DSMProxy> inProxyCollection, DSMProtocolEnum inProtocol) {
        if (inManifest == null || outFolder == null) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (inUpdates == null || inUpdates.isEmpty()) {
            return DCMErrorCodes.SUCCESS;
        }
        boolean mkdirs = outFolder.mkdirs();
        if (!mkdirs && !outFolder.exists()) {
            return DCMErrorCodes.FAILURE;
        }

        // Get Base Location
        String baseLocation = inManifest.getBaseLocation();

        // Get Custom Manifest, e.g. repository
        DCMManifest repository = createManifestFromUpdates(inUpdates, inManifest);

        // Create Catalog
        DCMCatalogWriter catalogWriter = new DCMCatalogWriter();
        File custom_catalog = new File(outFolder + DCMConstants.FILE_PATH_SEPARATOR + DCMConstants.CATALOG_FILE);
        catalogWriter.writeCatalog(repository, custom_catalog);

        // Create Set of files to be downloaded
        HashSet<String> filePaths = new HashSet<>();
        for (DCMUpdatePackageInformation updatePackageInformation : repository.getSoftwareComponents().getUpdatePackages()) {
            filePaths.add(updatePackageInformation.getPath());
        }
        for (DCMUpdatePackageInformation updatePackageInformation : repository.getPrerequisites().getUpdatePackages()) {
            filePaths.add(updatePackageInformation.getPath());
        }

        // Well, download now
        DSMDownloader downloader = new DSMDownloader();
        downloader.initialize(inParameters, inProxyCollection);
        try {
            return downloader.downloadFiles(filePaths, outFolder.getAbsolutePath(), baseLocation, inProtocol);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error in downloading files", ex);
            return DCMErrorCodes.FAILURE;
        }
    }

    /**
     * Method to create custom manifest from master manifest
     *
     * @author punit_ghodasara
     * @param inUpdates specifies the updates to be included in the output
     * manifest
     * @param inManifest specifies the source manifest
     * @return custom manifest that includes only inUpdates
     */
    @Override
    public DCMManifest createManifestFromUpdates(Collection<DCMUpdateInformation> inUpdates, DCMManifest inManifest) {
        /**
         * Method to create custom manifest from master manifest
         *
         * @author punit_ghodasara
         * @param inUpdates specifies the updates to be included in the output
         * manifest
         * @param inManifest specifies the source manifest
         * @return custom manifest that includes only inUpdates
         */
        return createManifestFromUpdates(inUpdates, inManifest, DCMBundleType.BTALL, null);
    }

    /**
     * Method to create custom manifest from master manifest and updates
     *
     * @param inUpdates specifies the updates to be included in the output
     * manifest
     * @param inManifest specifies the source manifest
     * @param inBundleType specifies the Chosen Bundle Types
     * @param systems specifies the required target systems
     * @return custom manifest that includes only inUpdates
     */
    @Override
    public DCMManifest createManifestFromUpdates(Collection<DCMUpdateInformation> inUpdates, DCMManifest inManifest, DCMBundleType inBundleType, DCMSystemCollection systems) {
        if (null == inManifest) {
            return null;
        }
        DCMManifest repository = new DCMManifest(inManifest);
        HashMap<String, DCMBundle> bundleMap = new HashMap<>();

        DCMUpdatePackageInformationCollection manifestSoftwareComponents = inManifest.getSoftwareComponents();
        for (DCMUpdateInformation updateInformation : inUpdates) {
            DCMUpdatePackageInformation updatePackage = manifestSoftwareComponents.getUpdatePackageInformation(updateInformation.getUniqueIdentifier());
            handleUpdatePackageReferences(updatePackage, inManifest, repository);
            addUpdatePackageDependencies(updatePackage, inManifest, repository);
            DCMUpdatePackageInformation createdUpdatePackageInformation = new DCMUpdatePackageInformation(updatePackage);
            repository.getSoftwareComponents().addUpdatePackageInformation(createdUpdatePackageInformation);

            for (String systemIdentifier : updatePackage.getSupportedSystems()) {

                // List of systems given by the inventory if any.
                if (systems != null && systems.getSystem(systemIdentifier) == null) {
                    continue;
                }
                Collection<String> bundleIdentifiers = inManifest.getBundleCollection().getBundleIdentifiers(systemIdentifier, inBundleType);
                for (String bundleIdentifier : bundleIdentifiers) {
                    DCMBundle existingBundle = inManifest.getBundleCollection().getBundle(bundleIdentifier);
                    if (null == existingBundle) {
                        continue; // Bundle not found for the said component.
                    }
                    DCMBundleType newBundleType = existingBundle.getType();
                    if (inBundleType == DCMBundleType.BTWMIX && updatePackage.getPackageType() == DCMPackageType.LW64 && newBundleType == DCMBundleType.BTW32) {
                        continue; // Ignore adding LW64 Component into 32 bit bundle.
                    }
                    if (updatePackage.getPackageType() == DCMPackageType.LW64 && newBundleType != DCMBundleType.BTW64) {
                        continue; // Ignore adding non LWXP Component into BTW64 bundle.
                    }
                    if (updatePackage.getPackageType() == DCMPackageType.LWXP && newBundleType != DCMBundleType.BTW32) {
                        continue; // Ignore adding non LWXP Component into BTW32 bit bundle.
                    }
                    if (updatePackage.getPackageType() == DCMPackageType.LLXP && newBundleType != DCMBundleType.BTLX) {
                        continue; // Ignore adding non LLXP Component into BTLX bit bundle.
                    }

                    DCMBundle bundle = bundleMap.get(systemIdentifier + newBundleType);
                    if (null == bundle) {
                        bundle = new DCMBundle(existingBundle);
                        bundle.setSize(0);
                        bundleMap.put(systemIdentifier + newBundleType, bundle);

                    }
                    if (bundle.addUpdatePackageIdentifier(updatePackage.getUniqueIdentifier()) == DCMErrorCodes.SUCCESS) {
                        // Update Bundle Size
                        bundle.setSize(bundle.getSize() + updatePackage.getSize());
                    }
                    repository.getBundleCollection().addBundle(bundle);
                }
            }
        }

        return repository;
    }

    private void addTarget(String inTargetIdentifier, DCMManifest inManifest, DCMManifest inRepository, DCMUpdatePackageInformation inUpdatePackage) {
        if (inTargetIdentifier == null || inTargetIdentifier.isEmpty() || inManifest == null || inRepository == null) {
            return;
        }
        DCMBaseTarget target = inUpdatePackage.getTarget(inTargetIdentifier);
        String updatePackageIdentifier = inUpdatePackage.getUniqueIdentifier();

        if (target != null) {
            inRepository.getUpdatePackageWithGivenIdentifier(updatePackageIdentifier).addTarget(target);
        }
    }

    private void handleUpdatePackageReferences(DCMUpdatePackageInformation inUpdatePackage, DCMManifest inManifest, DCMManifest inRepository) {
        if (inUpdatePackage == null || inManifest == null || inRepository == null) {
            return;
        }
        DCMLineOfBusinessCollection lineOfBusinessCollection = inManifest.getLineOfBusinessCollection();
        DCMOperatingSystemCollection operatingSystemCollection = inManifest.getOperatingSystemCollection();
        DCMSystemCollection systemCollection = inManifest.getSystemCollection();
        // Add System and LOB types referred by this update package to the repository
        for (String systemIdentifier : inUpdatePackage.getSupportedSystems()) {
            DCMSystem system = new DCMSystem(systemCollection.getSystem(systemIdentifier));
            DCMLineOfBusiness lob = new DCMLineOfBusiness(lineOfBusinessCollection.getLineOfBusiness(system.getLOBKey()));
            inRepository.getLineOfBusinessCollection().addLineOfBusiness(lob);
            inRepository.getSystemCollection().addSystem(system);
        }
        // Add operating system types referred by this update package to the repository
        for (String osIdentifier : inUpdatePackage.getSupportedOperatingSystems()) {
            DCMOperatingSystem os = new DCMOperatingSystem(operatingSystemCollection.getSystem(osIdentifier));
            inRepository.getOperatingSystemCollection().addOperatingSystem(os);
        }
        // Add the operating systems referred by the constituents to the repository
        for (DCMConstituent constituent : inUpdatePackage.getConstituents()) {
            for (String osIdentifier : constituent.getSupportedOperatingSystems()) {
                DCMOperatingSystem os = new DCMOperatingSystem(operatingSystemCollection.getSystem(osIdentifier));
                inRepository.getOperatingSystemCollection().addOperatingSystem(os);
            }
        }

        if (!inUpdatePackage.getCategoryCode().isEmpty()) {
            DCMCategory category = inManifest.getCategoryCollection().getCategory(inUpdatePackage.getCategoryCode());
            inRepository.getCategoryCollection().addCategory(category.getCode(), category.getName());
        }
        DCMComponentKind componentKind = inManifest.getComponentKindCollection().getComponentKind(inUpdatePackage.getComponentType());
        if (componentKind != null) {
            inRepository.getComponentKindCollection().addComponentKind(componentKind.getType(), componentKind.getName());
        }
    }

    private void addUpdatePackageDependencies(DCMUpdatePackageInformation inUpdatePackage, DCMManifest inManifest, DCMManifest inRepository) {
        if (inUpdatePackage == null || inManifest == null || inRepository == null) {
            return;
        }
        // Handle dependencies
        for (DCMDependency dependency : inUpdatePackage.getDependencies()) {
            DCMGUID prerequisite = dependency.getPrerequisite();
            if (prerequisite != null) {
                DCMUpdatePackageInformation prerequisiteUpdate = inManifest.getSoftwareComponents().getUpdatePackageInformation(prerequisite.toString());
                if (prerequisiteUpdate == null) {
                    prerequisiteUpdate = inManifest.getPrerequisites().getUpdatePackageInformation(prerequisite.toString());
                    handleUpdatePackageReferences(prerequisiteUpdate, inManifest, inRepository);
                    DCMUpdatePackageInformation createdPackage = new DCMUpdatePackageInformation(prerequisiteUpdate);
                    addUpdatePackageDependencies(createdPackage, inManifest, inRepository);
                    inRepository.getPrerequisites().addUpdatePackageInformation(createdPackage);
                }
            }
        }
        // Handle soft dependencies
        for (DCMSoftDependency dependency : inUpdatePackage.getSoftDependencies()) {
            DCMGUID prerequisite = dependency.getPrerequisite();
            if (prerequisite != null) {
                DCMUpdatePackageInformation prerequisiteUpdate = inManifest.getSoftwareComponents().getUpdatePackageInformation(prerequisite.toString());
                if (prerequisiteUpdate == null) {
                    prerequisiteUpdate = inManifest.getPrerequisites().getUpdatePackageInformation(prerequisite.toString());
                    handleUpdatePackageReferences(prerequisiteUpdate, inManifest, inRepository);
                    DCMUpdatePackageInformation createdPackage = new DCMUpdatePackageInformation(prerequisiteUpdate);
                    addUpdatePackageDependencies(createdPackage, inManifest, inRepository);
                    inRepository.getPrerequisites().addUpdatePackageInformation(createdPackage);
                }
            }
        }
    }

    private Collection<String> addDependenDUPPaths(DCMUpdatePackageInformation inUpdatePackageInformation, DCMManifest inManifest) {
        if (null == inUpdatePackageInformation) {
            return null;
        }
        Collection<String> retVal = new Stack<>();
        retVal.add(inUpdatePackageInformation.getPath());
        Collection<DCMDependency> dependencies = inUpdatePackageInformation.getDependencies();
        if (null == dependencies || dependencies.isEmpty()) {
            return retVal;
        }
        for (DCMDependency dependency : dependencies) {
            //retVal.add(dependency.getPath());
            DCMGUID prerequisiteID = dependency.getPrerequisite();
            if (null != prerequisiteID) {
                DCMUpdatePackageInformation updatePackageInformation = inManifest.getUpdatePackageWithGivenIdentifier(prerequisiteID.toString());
                Collection<String> tempDUPPaths = addDependenDUPPaths(updatePackageInformation, inManifest);
                if (null != tempDUPPaths && !tempDUPPaths.isEmpty()) {
                    retVal.addAll(tempDUPPaths);
                }
            }
        }
        return retVal;
    }

    private int suuContentCheck(String osCode, IDSMLocation inDSMLocation) {
        LOG.log(Level.INFO, "Entering suuContentCheck method");
       
        String invCollectorPath = "";
        if (osCode.equalsIgnoreCase(DCMConstants.WIN) || osCode.equalsIgnoreCase(DCMConstants.WIN_64)) {
            invCollectorPath = DCMConstants.FILE_PATH_SEPARATOR + DCMConstants.BIN + DCMConstants.FILE_PATH_SEPARATOR + DCMConstants.WINDOWS + DCMConstants.FILE_PATH_SEPARATOR + DCMConstants.INVCOL + "." + DCMConstants.EXE;
        } else if (osCode.equalsIgnoreCase(DCMConstants.LIN) || osCode.equalsIgnoreCase(DCMConstants.LIN_64)) {
            invCollectorPath = DCMConstants.FILE_PATH_SEPARATOR + DCMConstants.BIN + DCMConstants.FILE_PATH_SEPARATOR + DCMConstants.LINUX + DCMConstants.FILE_PATH_SEPARATOR + DCMConstants.INVCOL;
        }
        if (!inDSMLocation.exists(invCollectorPath)) {
            LOG.log(Level.SEVERE, "Sanity Check Failed. Inventory-Collector={0} File doesn't exist", new Object[]{invCollectorPath});
            return DSMErrorCodes.FAILURE;
        }
        LOG.log(Level.INFO, "suuContentCheck Successful. Exiting");
        return DSMErrorCodes.SUCCESS;
    }

    /**
     * Method for creating the custom SUU repository for the selected updates
     * and the corresponding dependent DUPs
     *
     * @param inUpdates specifies the updates to be included in the repository
     * @param inManifest specifies the source catalog
     * @param inSourceAuthParameters specifies the authentication parameters to
     * be used for downloading
     * @param inProxyCollection specifies the collection of proxies to be used
     * @param inSrcProtocol specifies the protocols to be used for downloading
     * @param inBaseLocation specifies the base location for the SUU
     * @return SUCCESS if the repository could be created else appropriate error
     * code is returned
     */
    @Override
    public int createSUURepository(Collection<DCMUpdateInformation> inUpdates,
            DCMManifest inManifest, DSMAuthenticationParameters inSourceAuthParameters,
            Collection<DSMProxy> inProxyCollection, DSMProtocolEnum inSrcProtocol,
            DCMBundleType inBundleType, String inBaseLocation, IDSMLocation inDestinationDSMLocation) {
        LOG.log(Level.INFO, "Entering createSUURepository method");

        if (inManifest == null || inDestinationDSMLocation == null) {
            LOG.log(Level.SEVERE, "Invalid arguements to createSUURepository");
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (inUpdates == null || inUpdates.isEmpty()) {
            LOG.log(Level.INFO, "Empty updates, returning from createSUURepository with SUCCESS");
            return DCMErrorCodes.SUCCESS;
        }
        int returnStatus = DCMErrorCodes.SUCCESS;
        Stack<String> downloadableDUPsPath = new Stack<>();
        Collection<String> downloadableInventoryCollectorPath = new Stack<>();
        Collection<DCMInventoryCollectorInformation> inventoryCollectionInformations = new HashSet<>();

        String baseLocation = (null != inBaseLocation && !inBaseLocation.isEmpty()) ? inBaseLocation : inManifest.getBaseLocation();

        String osCode = DCMConstants.WIN;
        if (inBundleType == DCMBundleType.BTW32) {
            osCode = DCMConstants.WIN;
        } else if (inBundleType == DCMBundleType.BTW64 || inBundleType == DCMBundleType.BTWMIX) {
            osCode = DCMConstants.WIN_64;
        } else if (inBundleType == DCMBundleType.BTLX) {
            osCode = DCMConstants.LIN_64;
        }
        if (suuContentCheck(osCode, inDestinationDSMLocation) == DCMErrorCodes.FAILURE) {
            return DCMErrorCodes.FAILURE;
        }

        DCMInventoryCollectorInformationCollection inventoryCollectionInformationCollection = inManifest.getInventoryCollectorInformationCollection();
        if (null != inventoryCollectionInformationCollection) {
            inventoryCollectionInformations = inventoryCollectionInformationCollection.getInventoryCollectorsInformation();
        }
        for (DCMInventoryCollectorInformation inventoryCollectorInfo : inventoryCollectionInformations) {
            String inventoryPath = inventoryCollectorInfo.getPath();
            String inventoryOS = inventoryCollectorInfo.getOSCode();
            if (null != inventoryPath && !inventoryPath.isEmpty() && inventoryOS.equalsIgnoreCase(osCode)) {
                LOG.log(Level.INFO, "Inventory Collector path={0} for osCode={1}", new Object[]{inventoryPath, osCode});
                downloadableInventoryCollectorPath.add(inventoryPath);
            }
        }

        for (DCMUpdateInformation update : inUpdates) {
            DCMUpdatePackageInformation updatePackageInformation = inManifest.getUpdatePackageWithGivenIdentifier(update.getUniqueIdentifier());
            Collection<String> dependentDUPPaths = addDependenDUPPaths(updatePackageInformation, inManifest);
            System.out.println(updatePackageInformation);
            if (null != dependentDUPPaths && !dependentDUPPaths.isEmpty()) {
                LOG.log(Level.INFO, "Adding DUP path={0}", new Object[]{dependentDUPPaths});
                downloadableDUPsPath.addAll(dependentDUPPaths);
            }
        }
        String targetRepositoryPath = DCMConstants.REPOSITORY;
        DSMDownloader downloader = new DSMDownloader();
        downloader.initialize(inSourceAuthParameters, inProxyCollection);
        downloader.setEnableAbsolutePathDownload(true);

        try {
            while (!downloadableDUPsPath.isEmpty()) {
                String sourceRelativePath = downloadableDUPsPath.pop();
                String targetFileName = sourceRelativePath.substring(sourceRelativePath.lastIndexOf("/"), sourceRelativePath.length());
                LOG.log(Level.INFO, "Calling downloader to download DUP File={0}", new Object[]{sourceRelativePath});
                int dupDownloadResult = downloader.downloadFile(sourceRelativePath, inDestinationDSMLocation, targetRepositoryPath, targetFileName, baseLocation, inSrcProtocol);
                if (DSMErrorCodes.SUCCESS != dupDownloadResult) {
                    returnStatus = DCMErrorCodes.PARTIAL_DOWNLOAD;
                }
            }

            Iterator<String> inventoryCollectorIterator = downloadableInventoryCollectorPath.iterator();
            while (inventoryCollectorIterator.hasNext()) {
                String sourceRelativePath = inventoryCollectorIterator.next();
                String targetFolderPath = "";
                String targetFileName = "";
                if (osCode.equalsIgnoreCase(DCMConstants.WIN) || osCode.equalsIgnoreCase(DCMConstants.WIN_64)) {
                    targetFolderPath = DCMConstants.BIN + DCMConstants.FILE_PATH_SEPARATOR + DCMConstants.WINDOWS;
                    targetFileName = DCMConstants.INVCOL + "." + DCMConstants.EXE;
                } else if (osCode.equalsIgnoreCase(DCMConstants.LIN) || osCode.equalsIgnoreCase(DCMConstants.LIN_64)) {
                    targetFolderPath = DCMConstants.BIN + DCMConstants.FILE_PATH_SEPARATOR + DCMConstants.LINUX;
                    targetFileName = DCMConstants.INVCOL;
                }
                LOG.log(Level.INFO, "Calling downloader to download Inventory Collector File={0}", new Object[]{sourceRelativePath});
                int invColDownloadResult = downloader.downloadFile(sourceRelativePath, inDestinationDSMLocation, targetFolderPath, targetFileName, baseLocation, inSrcProtocol);
                if (DSMErrorCodes.SUCCESS != invColDownloadResult) {
                    LOG.log(Level.WARNING, "Not updating InventoryCollector File={0}, Since download failed", new Object[]{sourceRelativePath});
                }
            }

            inManifest.setBaseLocation("");
            DCMUpdatePackageInformationCollection updatePackageInformationCollection = inManifest.getSoftwareComponents();
            Collection<DCMUpdatePackageInformation> updatePackageInformations = updatePackageInformationCollection.getUpdatePackages();

            LOG.log(Level.INFO, "Resetting each Software Component Path to normal form");
            // resetting each Software Component path to normal form
            for (DCMUpdatePackageInformation updatePackageInformation : updatePackageInformations) {
                String packagePath = updatePackageInformation.getPath();
                LOG.log(Level.INFO, "SWB Package path ", packagePath);
                packagePath = packagePath.replace("/", "\\");
                LOG.log(Level.INFO, "SWB Package After replacing forwardslash to backSlash = {0} ", packagePath);
                String[] packagePathParts = packagePath.split("\\\\");
                LOG.log(Level.INFO, "SWB Array After Split = " + packagePathParts);
                if (packagePathParts.length > 0) {
                    String packageName = packagePathParts[packagePathParts.length - 1];
                    LOG.log(Level.INFO, "SWB Package name = {0} ", packageName);
                    updatePackageInformation.setPath(packageName);
                }
            }

            LOG.log(Level.INFO, "Filtering out extra packages from catalog so that the catalog keeps only updateable packages");
            // Filtering out the extra packages from catalog so that the catalog keeps only updateable packages.
            DCMBundleCollection bundleCollection = inManifest.getBundleCollection();
            for (DCMBundle bundle : bundleCollection.getBundles()) {
                HashSet<String> updatePackageIdentifiers = new HashSet<String>();
                updatePackageIdentifiers.addAll(bundle.getUpdatePackageIdentifiers());
                for (String upPkg : updatePackageIdentifiers) {
                    String packagePath = inManifest.getSoftwareComponents().getUpdatePackageInformation(upPkg).getPath();
                    boolean updatePackageFound = false;
                    for (DCMUpdateInformation updateInformation : inUpdates) {
                        // First path is full path and second path is just filename, this will work even if they are same.
                        if (updateInformation.getPath().toLowerCase().endsWith(packagePath.toLowerCase())) {
                            updatePackageFound = true;
                            break;
                        }
                    }
                    if (updatePackageFound == false) {
                        bundle.removeUpdatePackageIdentifier(upPkg);
                    }
                }
            }

            for (DCMBundle bundle : bundleCollection.getBundles()) {
                String swbPath = bundle.getPath();
                if (swbPath != null && !swbPath.isEmpty()) {
                    swbPath = new File(swbPath).getName();
                    LOG.log(Level.INFO, "SWB Package name = {0} ", swbPath);
                    bundle.setPath(swbPath);
                }
            }
            // Replacing all the slash in the swb Path with "_"
            for (DCMBundle bundle : bundleCollection.getBundles()) {
                String swbPath = bundle.getPath();
                if (swbPath != null && !swbPath.isEmpty()) {
                    swbPath = swbPath.replace("/", "_");
                    swbPath = swbPath.replace("\\", "/");
                    String[] swbParts = swbPath.split("/");
                    if (swbParts.length > 0) {
                        String swbNameWithTrimmedBackSlash = swbParts[swbParts.length - 1];
                        LOG.log(Level.INFO, "SWB Package name = {0} ", swbNameWithTrimmedBackSlash);
                        bundle.setPath(swbNameWithTrimmedBackSlash);
                    }
                }
            }

            DCMCatalogWriter catalogWriter = new DCMCatalogWriter();
            File tempCatalogFile = File.createTempFile("DUEC_Catalog", ".xml");
            LOG.log(Level.INFO, "Serializing manifest object to tempCatalogFile={0}", new Object[]{tempCatalogFile.getAbsolutePath()});
            catalogWriter.writeCatalog(inManifest, tempCatalogFile);
            LOG.log(Level.INFO, "Call to upload tempCatalog File to destination location");
            int catalogUploadResult = inDestinationDSMLocation.uploadFile(tempCatalogFile.toString(), DCMConstants.REPOSITORY + "/" + DCMConstants.CATALOG_FILE);
            if (DSMErrorCodes.SUCCESS != catalogUploadResult) {
                returnStatus = DCMErrorCodes.FAILURE;
            }

            LOG.log(Level.INFO, "Deleting temp Catalog File");
            if (!tempCatalogFile.delete()) {
                LOG.log(Level.WARNING, "Error in Deleting temp Catalog File");
            }

            catalogWriter = new DCMCatalogWriter();
            for (DCMBundle bundle : bundleCollection.getBundles()) {
                File tempSWB = File.createTempFile("DUEC_SWB", "xml");
                catalogWriter.writeSoftwareBundles(inManifest, bundle, tempSWB);
                LOG.log(Level.INFO, "Call to upload tempSWBFile={0} to destination Location={1}", new Object[]{tempSWB.getAbsolutePath(), bundle.getPath()});
                int bundleUploadResult = inDestinationDSMLocation.uploadFile(tempSWB.toString(), DCMConstants.REPOSITORY + "/" + bundle.getPath());
                if (DSMErrorCodes.SUCCESS != bundleUploadResult) {
                    returnStatus = DCMErrorCodes.PARTIAL_DOWNLOAD;
                }
                LOG.log(Level.INFO, "Deleting temp SWB File = {0}", tempSWB.getAbsolutePath());
                if (!tempSWB.delete()) {
                    LOG.log(Level.WARNING, "Error in deleting tempSWBFile=", new Object[]{tempSWB.getAbsolutePath()});
                }
            }

            return returnStatus;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error in downloading files", ex);
            return DCMErrorCodes.FAILURE;
        }
    }

    @Override
    public int createSUURepository(Collection<String> inInventoryList, Collection<DSMProxy> inProxyCollection,
            String inCatalogAbsolutePath, DSMAuthenticationParameters inSrcAuthParameters,
            DSMProtocolEnum inSrcProtocol, String inBaseLocation, DCMBundleType inBundleType,
            IDSMLocation inDestinationDSMLocation) {
        LOG.log(Level.INFO, "Entering createSUURepository method");

        File catalogFile = new File(inCatalogAbsolutePath);
        if (!catalogFile.exists() || !catalogFile.canRead()) {
            LOG.log(Level.SEVERE, " Catalog.xml file doesn't exists or Can read the catalog file");
        }
        DCMCatalog catalogObj = new DCMCatalog();

        LOG.log(Level.INFO, "parsing Catalog");
        DCMManifest manifestObj = catalogObj.parseCatalog(catalogFile);

        DCMInventory inventoryObj = new DCMInventory();
        DCMMultiSystemInventory multiSystemInventoryObj = inventoryObj.createEmptyMultiSystemInventory();
        for (String currentInventory : inInventoryList) {
            if (currentInventory.length() == 0) {
                LOG.log(Level.WARNING, "Inventory File is null. Cotinuing to add remaining inventories");
                continue;
            }
            LOG.log(Level.INFO, "Adding inventory to multiSystemInventory");
            int retVal = inventoryObj.addInventory(new StringReader(currentInventory), "", multiSystemInventoryObj, Boolean.FALSE);
            if (DCMErrorCodes.SUCCESS != retVal) {
                LOG.log(Level.SEVERE, "Error in adding inventory File=", new Object[]{currentInventory});
                return retVal;
            }
        }

        LOG.log(Level.INFO, "Creating Output Mainfest object for SUU creation: " + manifestObj.getIdentifier());
        DCMManifest outManifest = new DCMManifest(manifestObj);

        DCMSystemInstanceCollection dcmSystemInstanceCollection = multiSystemInventoryObj.getSystemInstanceCollection();
        Collection<DCMSystemInstance> SystemInstanceCollection = dcmSystemInstanceCollection.getSystemInstances();
        String systemIdentifier = "";
        DCMComparer comparerObj = new DCMComparer();
        LOG.log(Level.INFO, "System Instance Collection obtained: " + SystemInstanceCollection.size());

        //considering default type as BTW32 when Bundle type not specified
        if (null == inBundleType) {
            inBundleType = DCMBundleType.BTW32;
        }

        DCMUpdatePackageInformationCollection manifestSoftwareComponents = manifestObj.getSoftwareComponents();

        //map of Bundles against System Type Identifer
        HashMap<String, DCMBundle> bundleMap = new HashMap<String, DCMBundle>();
        HashMap<String, DCMUpdateInformation> updateMap = new HashMap<String, DCMUpdateInformation>();

        for (DCMSystemInstance systemInstance : SystemInstanceCollection) {
            systemIdentifier = systemInstance.getServiceTag();
            LOG.log(Level.INFO, "Getting Applicable Updates for SystemIdentifier={0}", new Object[]{systemIdentifier});
            HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> updates
                    = comparerObj.getApplicableUpdates(multiSystemInventoryObj, systemIdentifier, manifestObj, inBundleType, DCMConsiderationEnum.REPORT_UPGRADES_N_DOWNGRADES);
            LOG.log(Level.INFO, "Updates: " + updates.size());
            if (updates != null && !updates.isEmpty()) {

                String systemTypeIdentifier = multiSystemInventoryObj.getSystemIdentifier(systemIdentifier);
                DCMBundle bundle = bundleMap.get(systemTypeIdentifier);
                LOG.log(Level.INFO, "Bundle Map keys: " + bundleMap.keySet());
                if (null == bundle) {
                    Collection<String> bundleIdentifiers = manifestObj.getBundleCollection().getBundleIdentifiers(systemTypeIdentifier, inBundleType);
                    DCMBundle existingBundle = manifestObj.getBundleCollection().getBundle(bundleIdentifiers.iterator().next());

                    LOG.log(Level.INFO, "Existing Bundle is: " + existingBundle.getBundleID());
                    if (null == existingBundle) {
                        // No bundle in source repo
                        LOG.log(Level.SEVERE, "No Bundle found in source repo against bundle identifier ");
                        continue;
                    }

                    //Choose a source 64 bit bundle in case the bundle is MIXED type and if available.
                    if (existingBundle.getType() == DCMBundleType.BTW32 && inBundleType == DCMBundleType.BTWMIX) {
                        for (String bundleIdentifier : bundleIdentifiers) {
                            DCMBundle nextBundle = manifestObj.getBundleCollection().getBundle(bundleIdentifier);
                            if (nextBundle.getType() == DCMBundleType.BTW64) {
                                existingBundle = nextBundle;
                                break;
                            }
                        }
                    }

                    //Constructing DCMBundle object to put in bundleMap
                    bundle = new DCMBundle(existingBundle);
                    bundle.setSize(0);

                    outManifest.getCategoryCollection().addCategory((manifestObj.getCategoryCollection()).getCategory(existingBundle.getCategoryCode()));
                    outManifest.getComponentKindCollection().addComponentKind(manifestObj.getComponentKindCollection().getComponentKind(existingBundle.getComponentType()));

                    bundleMap.put(systemTypeIdentifier, bundle);
                    outManifest.getBundleCollection().addBundle(bundle);
                    LOG.log(Level.INFO, "Bundle added to Outputput Manifest: " + bundle.getBundleID());
                }
                //iterating thorugh applicable updates obtained for SystemIdentifier
                for (HashMap<DCMVersionInformation, DCMUpdateInformation> infoMap : updates.values()) {
                    for (DCMUpdateInformation updateInfo : infoMap.values()) {
                        String id = updateInfo.getUniqueIdentifier();

                        DCMUpdatePackageInformation updatePackageInformation = manifestSoftwareComponents.getUpdatePackageInformation(id);
                        handleUpdatePackageReferences(updatePackageInformation, manifestObj, outManifest);
                        addUpdatePackageDependencies(updatePackageInformation, manifestObj, outManifest);
                        DCMUpdatePackageInformation createdUpdatePackageInformation = new DCMUpdatePackageInformation(updatePackageInformation);
                        outManifest.getSoftwareComponents().addUpdatePackageInformation(createdUpdatePackageInformation);
                        if (bundle.addUpdatePackageIdentifier(id) == DCMErrorCodes.SUCCESS) {
                            bundle.setSize(bundle.getSize() + updatePackageInformation.getSize());
                        }
                        updateMap.put(id, updateInfo);
                        LOG.log(Level.INFO, "Update Information added to Update Map: " + updateInfo.getName());
                    }
                }
            } else {
                // no updates for this system instance
                LOG.log(Level.SEVERE, "No updates found against this instance " + systemIdentifier);
            }
        }

        // populating outManifest manifest operating systems list with the list of OSes in SWBs from input Manifest Object.
        DCMBundleCollection bundleCollection = outManifest.getBundleCollection();
        LOG.log(Level.INFO, "List of available updates obtained with size: " + updateMap.size());
        if (bundleCollection.getBundles().isEmpty()) {
            LOG.log(Level.SEVERE, "No Bundles found: ");
            return DSMErrorCodes.EMPTY;
        }
        return this.createSUURepository(updateMap.values(), outManifest, inSrcAuthParameters, inProxyCollection, inSrcProtocol, inBundleType, inBaseLocation, inDestinationDSMLocation);
    }

    @Override
    public DCMManifest createManifestFromUpdatePackages(Collection<DCMUpdatePackageInformation> inUpdates, DCMManifest inManifest,
            Collection<DCMBundleType> inBundleTypeCollection, DCMSystemCollection systems) {
        if (null == inManifest) {
            return null;
        }
        DCMManifest customManifest = new DCMManifest();
        Collection<DCMManifest> collectionManifest = new ArrayList<>();
        for (DCMBundleType inBundleType : inBundleTypeCollection) {
            collectionManifest.add(createManifestFromUpdatePackages(inUpdates, inManifest, inBundleType, systems));
        }
        for (DCMManifest tempManifest : collectionManifest) {
            customManifest = mergeManifest(customManifest, tempManifest);
        }
        return customManifest;
    }

    public DCMManifest mergeManifest(DCMManifest parentManifest, DCMManifest childManifest) {
        LOG.info("In mergeManifest");
        Collection<DCMInventoryCollectorInformation> inventoryCollectorInfo = childManifest.getInventoryCollectorInformationCollection().getInventoryCollectorsInformation();
        if (null != inventoryCollectorInfo && !inventoryCollectorInfo.isEmpty()) {
            for (DCMInventoryCollectorInformation info : inventoryCollectorInfo) {
                parentManifest.getInventoryCollectorInformationCollection().addInventoryCollectorInformation(info);
            }
        }
        Collection<DCMCategory> categoryCollection = childManifest.getCategoryCollection().getCategories();
        if (!categoryCollection.isEmpty()) {
            for (DCMCategory category : categoryCollection) {
                parentManifest.getCategoryCollection().addCategory(category);
            }
        }
        Collection<DCMOperatingSystem> osCollection = childManifest.getOperatingSystemCollection().getOperatingSystems();
        if (!osCollection.isEmpty()) {
            for (DCMOperatingSystem os : osCollection) {
                parentManifest.getOperatingSystemCollection().addOperatingSystem(os);
            }
        }
        Collection<DCMUpdateableComponent> updateableComponentCollection = childManifest.getUpdateableComponentCollection().getUpdateableComponents();
        if (!updateableComponentCollection.isEmpty()) {
            for (DCMUpdateableComponent res : updateableComponentCollection) {
                parentManifest.getUpdateableComponentCollection().addUpdateableComponent(res);
            }
        }

        Collection<DCMComponentKind> componentKindCollection = childManifest.getComponentKindCollection().getComponentKinds();
        if (!componentKindCollection.isEmpty()) {
            for (DCMComponentKind componentKind : componentKindCollection) {
                parentManifest.getComponentKindCollection().addComponentKind(componentKind);
            }
        }

        Collection<DCMSystem> systemCollection = childManifest.getSystemCollection().getSystems();
        if (!systemCollection.isEmpty()) {
            for (DCMSystem system : systemCollection) {
                parentManifest.getSystemCollection().addSystem(system);
            }
        }
        Collection<DCMLineOfBusiness> lobCollection = childManifest.getLineOfBusinessCollection().getLinesOfBusiness();
        if (!lobCollection.isEmpty()) {
            for (DCMLineOfBusiness lob : lobCollection) {
                parentManifest.getLineOfBusinessCollection().addLineOfBusiness(lob);
            }
        }
        Collection<DCMBundle> childBundleCollection = childManifest.getBundleCollection().getBundles();
        if (!childBundleCollection.isEmpty()) {
            for (DCMBundle bundle : childBundleCollection) {
                HashSet<String> childUpdatePackageIdentifiers = bundle.getUpdatePackageIdentifiers();

                String childBundleId;
                if (bundle.getIdentifier() != null) {
                    childBundleId = bundle.getIdentifier().getID();
                } else {
                    childBundleId = bundle.getBundleID();
                }

                DCMBundle parentBundle = parentManifest.getBundle(childBundleId);
                if (null == parentBundle) {
                    parentManifest.getBundleCollection().addBundle(bundle);
                } else {
                    HashSet<String> parentUpdatePackageIdentifiers = parentBundle.getUpdatePackageIdentifiers();
                    for (String id : childUpdatePackageIdentifiers) {
                        if (!parentUpdatePackageIdentifiers.contains(id)) {
                            parentBundle.addUpdatePackageIdentifier(id);
                        }
                    }
                }
            }
        }
        Collection<DCMUpdatePackageInformation> childUpdatePackageCollection = childManifest.getSoftwareComponents().getUpdatePackages();
        if (!childUpdatePackageCollection.isEmpty()) {
            for (DCMUpdatePackageInformation updatePackage : childUpdatePackageCollection) {
                String identifier = updatePackage.getName().getDefaultLocaleString();
                String info = parentManifest.getSoftwareComponents().getUpdatePackageIdentifier(identifier);
                if (null == info || info.isEmpty()) {
                    parentManifest.getSoftwareComponents().addUpdatePackageInformation(updatePackage);
                }
            }
        }

        return parentManifest;
    }

    @Override
    public DCMManifest createManifestFromUpdatePackages(Collection<DCMUpdatePackageInformation> inUpdates,
            DCMManifest inManifest, DCMBundleType inBundleType, DCMSystemCollection systems) {

        if (null == inManifest) {
            return null;
        }
        DCMManifest customManifest = new DCMManifest(inManifest);
        HashMap<String, DCMBundle> bundleMap = new HashMap<>();

        for (DCMUpdatePackageInformation updatePackage : inUpdates) {

            handleUpdatePackageReferences(updatePackage, inManifest, customManifest);
            addUpdatePackageDependencies(updatePackage, inManifest, customManifest);

            DCMUpdatePackageInformation createdUpdatePackageInformation = new DCMUpdatePackageInformation(updatePackage);
            customManifest.getSoftwareComponents().addUpdatePackageInformation(createdUpdatePackageInformation);

            for (String systemIdentifier : updatePackage.getSupportedSystems()) {

                // List of systems given by the inventory if any.
                if (systems != null && systems.getSystem(systemIdentifier) == null) {
                    continue;
                }
                Collection<String> bundleIdentifiers = inManifest.getBundleCollection().getBundleIdentifiers(systemIdentifier, inBundleType);
                for (String bundleIdentifier : bundleIdentifiers) {
                    DCMBundle existingBundle = inManifest.getBundleCollection().getBundle(bundleIdentifier);
                    if (null == existingBundle) {
                        continue; // Bundle not found for the said component and type.
                    }

                    DCMBundleType newBundleType = existingBundle.getType();
                    if (updatePackage.getPackageType() == DCMPackageType.MCP && newBundleType != DCMBundleType.BTOSIND) {
                        continue;
                    }
                    if (inBundleType == DCMBundleType.BTWMIX && updatePackage.getPackageType() == DCMPackageType.LW64 && newBundleType == DCMBundleType.BTW32) {
                        continue; // Ignore adding LW64 Component into 32 bit bundle.
                    }
                    if (updatePackage.getPackageType() == DCMPackageType.LW64 && newBundleType != DCMBundleType.BTW64) {
                        continue; // Ignore adding non LWXP Component into BTW64 bundle.
                    }
                    if (updatePackage.getPackageType() == DCMPackageType.LWXP && newBundleType != DCMBundleType.BTW32) {
                        continue; // Ignore adding non LWXP Component into BTW32 bit bundle.
                    }
                    if (updatePackage.getPackageType() == DCMPackageType.LLXP && newBundleType != DCMBundleType.BTLX) {
                        continue; // Ignore adding non LLXP Component into BTLX bit bundle.
                    }

                    DCMBundle bundle = bundleMap.get(systemIdentifier + newBundleType);
                    if (null == bundle) {
                        bundle = new DCMBundle(existingBundle);
                        bundle.setSize(0);
                        bundleMap.put(systemIdentifier + newBundleType, bundle);
                        //customManifest.getBundleCollection().addBundle(bundle);
                    }
                    bundle.addUpdatePackageIdentifier(updatePackage.getUniqueIdentifier());
                    // Update Bundle Size
                    bundle.setSize(bundle.getSize() + updatePackage.getSize());
                    customManifest.getBundleCollection().addBundle(bundle);
                }
            }
        }

        return customManifest;
    }

    private Logger LOG = Logger.getLogger(DCMRepository.class.getName());
}
