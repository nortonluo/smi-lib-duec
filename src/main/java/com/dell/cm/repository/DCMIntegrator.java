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
import com.dell.cm.repository.DCMCatalogWriter;
import com.dell.cm.updateinformationmodel.DCMBundle;
import com.dell.cm.updateinformationmodel.DCMBundleCollection;
import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMNamePathCollection;
import com.dell.cm.updateinformationmodel.DCMSystemInstance;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import com.dell.cm.updateinformationmodel.DCMVersionInformationCollection;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.downloader.DSMDownloader;
import com.dell.sm.downloader.DSMErrorCodes;
import com.dell.sm.downloader.DSMLocation;
import static com.dell.sm.downloader.DSMLocation.LOG;
import com.dell.sm.downloader.DSMProtocolEnum;
import com.dell.sm.downloader.DSMProxy;
import com.dell.sm.extracter.DSMGZipFileExtracter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * class for representing the DCMBaseLine information and operations
 *
 */
public class DCMIntegrator {

    /**
     * constructor to create object with no proxy and a baseline storage
     * location
     *
     * @param inLocation Location to set storage path of baselines
     */
    public DCMIntegrator(String inLocation) {
        mLocation = new DSMLocation(inLocation);
    }

    /**
     * constructor to create object with default proxy information
     *
     * @param inProxy Proxy information
     * @param inLocation Location to set storage path of baselines
     */
    public DCMIntegrator(DSMProxy inProxy, String inLocation) {
        this(inLocation);
        mProxies.add(inProxy);
    }

    /**
     * constructor to create object with default proxy information
     *
     * @param protocol specifies the protocol to be set for proxy
     * @param portnumber specifies the port number to be set for proxy
     * @param url specifies the URL for proxy
     * @param user Proxy user name for proxy
     * @param pass Proxy password for proxy
     * @param inLocation Location to set storage path of baselines
     */
    public DCMIntegrator(DSMProtocolEnum protocol, int portnumber, String url, String user, String pass, String inLocation) {
        this(new DSMProxy(protocol, portnumber, url, user, pass), inLocation);
    }

    /**
     * Method for getting the list of available catalog list
     *
     * @param url specifies the URL for proxy
     * @param groupName group name of the catalog type
     * @return the list of available catalogs hosted in the form of list of data
     * pair (catalog name, URL)
     */
    public HashMap<String, String> getCatalogList(String url, String groupName) {
        DCMNamePathCollection namePath = new DCMNamePathCollection();
        File indexCatalog = new File(url);
        try {
            if (namePath.createNamePathCollection(indexCatalog) == DCMErrorCodes.SUCCESS) {
                HashMap<String, String> catalogList = new HashMap<String, String>();
                HashMap<String, HashMap<String, String>> group = namePath.getGroupManifestNamePathMap();
                if (group.containsKey(groupName)) {
                    catalogList = group.get(groupName);
                }
                return catalogList;
            }
        } catch (Exception ex) {
            Logger.getLogger(DCMIntegrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Method for downloading the catalog from URL
     *
     * @param fromLocation URL path for the required Catalog to be downloaded
     * @param baselineName Name of the catalog to be downloaded
     * @return the success status
     */
    public int getCatalog(String fromLocation, String baselineName) {
        DSMDownloader instance = new DSMDownloader();
        instance.initialize(null, mProxies);

        int result = DCMErrorCodes.FAILURE;
        try {
            URL targetURL = new URL(fromLocation);
            instance.setEnableAbsolutePathDownload(true);
            String targetFile = baselineName + ".xml";
            if (fromLocation.endsWith("gz")) {
                targetFile = baselineName + ".xml.gz";
            }

            result = instance.downloadFile(targetURL.getFile(), mLocation, "", targetFile, targetURL.getHost(), DSMProtocolEnum.HTTP);
            if (targetFile.endsWith("gz")) {
                DSMGZipFileExtracter extract = new DSMGZipFileExtracter();
                extract.extractGZipFile(mLocation.getTargetPath() + File.separator + targetFile, mLocation.getTargetPath());
                Files.deleteIfExists(new File(mLocation.getTargetPath() + File.separator + targetFile).toPath());
            }

        } catch (Exception ex) {
            Logger.getLogger(DCMIntegrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Method for creating repository from local catalog
     *
     * @param baselineName Name of the baseline.
     * @throws IOException handles the IOException
     * @return the success status
     */
    public int createSystemBaseline(String baselineName) throws IOException {

        File target_catalog = new File(mLocation.getTargetPath() + File.separator + baselineName + ".xml");
        if (!target_catalog.exists()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        DCMManifest catalog = new DCMCatalog().parseCatalog(target_catalog);
        if (catalog == null) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        // Create Set of files to be downloaded
        HashSet<String> filePaths = new HashSet<>();
        for (DCMUpdatePackageInformation updatePackageInformation : catalog.getSoftwareComponents().getUpdatePackages()) {
            if (!filePaths.contains(updatePackageInformation.getPath())) {
                filePaths.add(updatePackageInformation.getPath());
            }
        }
        for (DCMUpdatePackageInformation updatePackageInformation : catalog.getPrerequisites().getUpdatePackages()) {
            if (!filePaths.contains(updatePackageInformation.getPath())) {
                filePaths.add(updatePackageInformation.getPath());
            }
        }

        // Well, download now
        DSMDownloader downloader = new DSMDownloader();
        downloader.initialize(null, mProxies);
        try {
            return downloader.downloadFiles(filePaths, target_catalog.getParent(), catalog.getBaseLocation(), DSMProtocolEnum.HTTP);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error in downloading files", ex);
            return DCMErrorCodes.FAILURE;
        }
    }

    /**
     * Method for creating repository from local catalog
     *
     * @param systemBaseline Name of the system baseline.
     * @param customBaseline Name of the target custom baseline.
     * @param systemIDs Collection of systems to be kept in custom baseline.
     * @param bundleType specifies the bundle type
     * @throws IOException handles the IOException
     * @return the success status
     */
    public int createCustomBaseline(String systemBaseline, String customBaseline, Collection<String> systemIDs, DCMBundleType bundleType) throws IOException {

        File systemCatalog = new File(mLocation.getTargetPath() + File.separator + systemBaseline + ".xml");
        File customCatalog = new File(mLocation.getTargetPath() + File.separator + customBaseline + ".xml");
        if (!systemCatalog.exists() || customCatalog.exists() || systemIDs.isEmpty()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        DCMManifest catalog = new DCMCatalog().parseCatalog(systemCatalog);
        Collection<String> packageEntries = new HashSet<String>();
        Collection<String> excludeBundleEntries = new HashSet<String>();

        for (DCMBundle bundle : catalog.getBundleCollection().getBundles()) {
            boolean foundBundle = false;
            if (bundle.getType() == bundleType) {
                for (String sysid : bundle.getTargetSystemIdentifiers()) {
                    for (String sysidvalue : systemIDs) {
                        if (sysid.contains(sysidvalue)) {
                            foundBundle = true;
                            break;
                        }
                    }
                    if (foundBundle) {
                        break;
                    }
                }
            }
            if (!foundBundle) {
                excludeBundleEntries.add(bundle.getUniqueIdentifier());
            } else {
                packageEntries.addAll(bundle.getUpdatePackageIdentifiers());
            }
        }
        for (String id : excludeBundleEntries) {
            catalog.getBundleCollection().removeBundle(id);
        }

        Collection<String> excludePackageEntries = new HashSet<String>();
        Collection<String> packageEntryPaths = new HashSet<String>();
        for (DCMUpdatePackageInformation updatePackageInformation : catalog.getSoftwareComponents().getUpdatePackages()) {
            String id = updatePackageInformation.getUniqueIdentifier();
            if (!packageEntries.contains(id)) {
                excludePackageEntries.add(id);
            } else {
                packageEntryPaths.add(updatePackageInformation.getPath());
            }
        }

        for (String id : excludePackageEntries) {
            catalog.getSoftwareComponents().removeUpdatePackageInformation(id);
        }

        DCMCatalogWriter catalogWriter = new DCMCatalogWriter();
        catalogWriter.writeCatalog(catalog, customCatalog);

        // Create Set of files to be downloaded
        for (DCMUpdatePackageInformation updatePackageInformation : catalog.getPrerequisites().getUpdatePackages()) {
            packageEntryPaths.add(updatePackageInformation.getPath());
        }

        // Well, download now
        DSMDownloader downloader = new DSMDownloader();
        downloader.initialize(null, mProxies);
        try {
            return downloader.downloadFiles(packageEntryPaths, systemCatalog.getParent(), catalog.getBaseLocation(), DSMProtocolEnum.HTTP);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error in downloading files", ex);
            return DCMErrorCodes.FAILURE;
        }
    }

    /**
     * Method for finding the catalog path by name
     *
     * @param name Name of the baseline.
     * @return the catalog path
     */
    public String getSystemBaseline(String name) {
        File target_catalog = new File(mLocation.getTargetPath() + File.separator + name + ".xml");
        if (target_catalog.exists()) {
            return target_catalog.toString();
        }
        return null;
    }

    /**
     * Method for getting the list of available baseline list
     *
     * @return the list of available catalogs hosted in the form of list of data
     * pair (catalog name, URL)
     */
    public Collection<String> getAllSystemBaselines() {

        HashSet<String> systembaselines = new HashSet<String>();
        File dir = new File(mLocation.getTargetPath());
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                String baseName = file.getName();
                if (baseName.toLowerCase().endsWith(".xml")) {
                    systembaselines.add(baseName.substring(0, baseName.length() - 4));
                }
            }
        }
        return systembaselines;
    }

    /**
     * Method for deleting the baseline by name
     *
     * @param name Name of the baseline.
     * @return the success status
     */
    public boolean deleteSystemBaseline(String name) {
        String baseline = getSystemBaseline(name);
        if (baseline != null) {
            try {
                return Files.deleteIfExists(new File(baseline).toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Method for Checking compliance of an existing system
     *
     * @param DeviceIpaddress IP of the system, iDRAC/CMC IP.
     * @param username user name to access the system
     * @param password password to access the system
     * @param baselineCatalogPath path of the baseline catalog
     * @return the list of non compliant devices and update information
     */
    public HashMap<DCMVersionInformation, DCMUpdateInformation> getCompliance(String DeviceIpaddress, String username, String password, String baselineCatalogPath) {
        //HashMap<String, HashMap<DCMVersionInformation,DCMUpdateInformation>> getApplicableUpdates(DCMMultiSystemInventory inInventory,String inIdentifier,DCMManifest inManifest,DCMBundleType inBundleType,DCMConsiderationEnum inConsideration) {
        if (DeviceIpaddress == null || username == null || baselineCatalogPath == null
                || DeviceIpaddress.isEmpty() || username.isEmpty() || baselineCatalogPath.isEmpty()) {
            return null;
        }

        DCMInventory inventoryObj = new DCMInventory();
        DCMMultiSystemInventory multiSystemInventoryObj = inventoryObj.createEmptyMultiSystemInventory();
        // adding inventory to multiSystemInventory obj 
        DSMAuthenticationParameters auth = new DSMAuthenticationParameters();
        auth.setUserName(username);
        auth.setPassword(password);

        inventoryObj.addWSInventory(DeviceIpaddress, auth, multiSystemInventoryObj);

        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> retVal = new DCMComparer().getApplicableUpdates(multiSystemInventoryObj, new File(baselineCatalogPath), DCMBundleType.BTW32, DCMConsiderationEnum.REPORT_UPGRADES_ONLY);
        if (retVal != null) {
            if (retVal.isEmpty()) {
                return new HashMap<DCMVersionInformation, DCMUpdateInformation>();
            }
            return retVal.values().iterator().next();
        }
        return null;
    }
    // private members.

    HashSet<DSMProxy> mProxies = new HashSet<>();
    DSMLocation mLocation;
}
