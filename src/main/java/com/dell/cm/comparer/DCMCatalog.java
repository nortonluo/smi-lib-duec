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

import com.dell.cm.updateinformationmodel.DCMACPIID;
import com.dell.cm.updateinformationmodel.DCMBaseTarget;
import com.dell.cm.updateinformationmodel.DCMBundle;
import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMComponentType;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMConstituent;
import com.dell.cm.updateinformationmodel.DCMCriticality;
import com.dell.cm.updateinformationmodel.DCMDependency;
import com.dell.cm.updateinformationmodel.DCMDependencyContext;
import com.dell.cm.updateinformationmodel.DCMDeviceApplicability;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMFMPWrapperInformation;
import com.dell.cm.updateinformationmodel.DCMGUID;
import com.dell.cm.updateinformationmodel.DCMHexBinary4;
import com.dell.cm.updateinformationmodel.DCMI18NString;
import com.dell.cm.updateinformationmodel.DCMImportantInformation;
import com.dell.cm.updateinformationmodel.DCMInstallInstructions;
import com.dell.cm.updateinformationmodel.DCMInventoryCollectorInformation;
import com.dell.cm.updateinformationmodel.DCMLineOfBusiness;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMNonPCIPnPHardware;
import com.dell.cm.updateinformationmodel.DCMOSArchitecture;
import com.dell.cm.updateinformationmodel.DCMOSType;
import com.dell.cm.updateinformationmodel.DCMOperatingSystem;
import com.dell.cm.updateinformationmodel.DCMPCIDevice;
import com.dell.cm.updateinformationmodel.DCMPCIInfo;
import com.dell.cm.updateinformationmodel.DCMPackageType;
import com.dell.cm.updateinformationmodel.DCMPayloadConfiguration;
import com.dell.cm.updateinformationmodel.DCMPayloadImage;
import com.dell.cm.updateinformationmodel.DCMPayloadUpdateDriver;
import com.dell.cm.updateinformationmodel.DCMPnPDevice;
import com.dell.cm.updateinformationmodel.DCMPnPID;
import com.dell.cm.updateinformationmodel.DCMPnPInfo;
import com.dell.cm.updateinformationmodel.DCMPnPProductID;
import com.dell.cm.updateinformationmodel.DCMRollbackInformation;
import com.dell.cm.updateinformationmodel.DCMSoftDependency;
import com.dell.cm.updateinformationmodel.DCMSubComponent;
import com.dell.cm.updateinformationmodel.DCMSystem;
import com.dell.cm.updateinformationmodel.DCMSystemIDType;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import com.dell.cm.updateinformationmodel.activationrule.DCMActivationRules;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.FilenameFilter;
import java.util.List;
import com.dell.sm.extracter.*;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import org.xml.sax.InputSource;

/**
 * class for facilitating the catalog parser
 *
 * @author Raveendra_Madala
 */
public class DCMCatalog implements IDCMCatalog {

    /**
     * Method for parsing the repository given base location for repository
     *
     * @param inBaseLocation specifies the location of repository
     * @param inSystemCollection specifies collection of DCMSystems
     * @return the manifest of the parsed repository
     */
    @Override
    public DCMManifest parseRepository(String inBaseLocation, List<DCMSystem> inSystemCollection) {
        List<File> listExeBinFile = parseRepositoryUtility(inBaseLocation);
        mManifest = parseRepository(inBaseLocation, listExeBinFile, inSystemCollection);
        return mManifest;
    }

    /**
     * Method for parsing the catalog
     *
     * @param inCatalogFile specifies the catalog file to be parsed
     * @return the manifest of the parsed catalog
     */
    @Override
    public DCMManifest parseCatalog(File inCatalogFile) {
        if (null == inCatalogFile || !inCatalogFile.exists()) {
            return null;
        }
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            if (null == docBuilderFactory) {
                return null;
            }
            DocumentBuilder docBuilder;
            docBuilder = docBuilderFactory.newDocumentBuilder();
            if (null == docBuilder) {
                return null;
            }
            Document givenDocument = docBuilder.parse(validateCatalog(inCatalogFile));
            if (null == givenDocument) {
                return null;
            }
            Element givenDocumentRoot = givenDocument.getDocumentElement();
            if (null == givenDocumentRoot) {
                return null;
            }
            mManifest = new DCMManifest();
            // baseLocation
            String baseLocation = givenDocumentRoot.getAttribute(DCMConstants.BASE_LOCATION_ATTRIBUTE);
            mManifest.setBaseLocation(baseLocation);
            // dateTime
            String dateTime = givenDocumentRoot.getAttribute(DCMConstants.DATE_TIME_ATTRIBUTE);
            if (!dateTime.isEmpty()) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DCMConstants.DATE_FORMAT_STRING);
                Date date = null;
                try {
                    date = simpleDateFormat.parse(dateTime);
                } catch (ParseException ex) {
                    Logger.getLogger(DCMCatalog.class.getName()).log(Level.WARNING, null, ex);
                }
                mManifest.setCreationDate(date);
            }
            // identifier
            String identifier = givenDocumentRoot.getAttribute(DCMConstants.IDENTIFIER_ATTRIBUTE);
            if (!identifier.isEmpty()) {
                DCMGUID id = new DCMGUID();
                id.setID(identifier);
                mManifest.setIdentifier(id);
            }
            // predecessorID
            String predecessorID = givenDocumentRoot.getAttribute(DCMConstants.PREDECESSOR_ID_ATTRIBUTE);
            if (!predecessorID.isEmpty()) {
                DCMGUID predID = new DCMGUID();
                predID.setID(predecessorID);
                mManifest.setPredecessorIdentifier(predID);
            }
            // releaseID
            String releaseID = givenDocumentRoot.getAttribute(DCMConstants.RELEASE_ID_ATTRIBUTE);
            if (!releaseID.isEmpty()) {
                mManifest.setReleaseID(releaseID);
            }
            // version
            String version = givenDocumentRoot.getAttribute(DCMConstants.VERSION_ATTRIBUTE);
            if (!version.isEmpty()) {
                mManifest.setVersion(version);
            }

            Element releaseNoteElement = getFirstLevelChildWithGivenName(givenDocumentRoot, DCMConstants.RELEASE_NOTES);
            if (releaseNoteElement != null) {
                DCMI18NString name = getDisplay(releaseNoteElement);
                if (name != null) {
                    mManifest.setReleaseNotes(name);
                }
            }
            // Inventory Component nodes
            NodeList inventoryComponentNodeList = givenDocumentRoot.getElementsByTagName(DCMConstants.INVENTORY_COMPONENT);
            if ((inventoryComponentNodeList != null) && (inventoryComponentNodeList.getLength() > 0)) {
                int numberOfInventoryComponentNodes = inventoryComponentNodeList.getLength();
                for (int inventoryComponentIndex = 0; inventoryComponentIndex < numberOfInventoryComponentNodes; ++inventoryComponentIndex) {
                    Element inventoryComponentElement = (Element) inventoryComponentNodeList.item(inventoryComponentIndex);
                    parseInventoryComponent(inventoryComponentElement);
                }
            }
            // Prerequisites
            parsePrerequisites(getFirstLevelChildWithGivenName(givenDocumentRoot, DCMConstants.PREREQUISITES));
            // Software Components
            for (Element softwareComponentElement : getFirstLevelChildrenWithGivenName(givenDocumentRoot, DCMConstants.SOFTWARE_COMPONENT)) {
                DCMUpdatePackageInformation softwareComponent = parseSoftwareComponent(softwareComponentElement, "");
                if (null != softwareComponent) {
                    mManifest.getSoftwareComponents().addUpdatePackageInformation(softwareComponent);
                }
            }
            // Software Bundles
            for (Element softwareBundleElement : getFirstLevelChildrenWithGivenName(givenDocumentRoot, DCMConstants.SOFTWARE_BUNDLE)) {
                parseSoftwareBundle(softwareBundleElement);
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DCMCatalog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mManifest;
    }

    /**
     * Method for getting the latest updates in the destination catalog
     *
     * @param inSourceCatalog specifies the source catalog for comparing
     * @param inLatestCatalog specifies the destination catalog
     * @return DCMCatalogComparisonResult
     */
    @Override
    public Collection<DCMUpdateInformation> getLatestUpdates(File inSourceCatalog, File inLatestCatalog) {

        return null;
    }

    /**
     * Method for parsing inventory component
     *
     * @param inInventoryNode specifies the inventory component node
     * @return SUCCESS if the node could be parsed else appropriate error code
     * is returned
     */
    private int parseInventoryComponent(Element inInventoryNode) {
        if (null == inInventoryNode) {
            return DCMErrorCodes.FAILURE;
        }
        if (!inInventoryNode.getNodeName().equals(DCMConstants.INVENTORY_COMPONENT)) {
            return DCMErrorCodes.FAILURE;
        }
        DCMInventoryCollectorInformation invColInfo = new DCMInventoryCollectorInformation();
        // schemaVersion
        String schemaVersion = inInventoryNode.getAttribute(DCMConstants.SCHEMA_VERSION_ATTRIBUTE);
        if (!schemaVersion.isEmpty()) {
            invColInfo.setSchemaVersion(schemaVersion);
        }
        // releaseID
        String releaseID = inInventoryNode.getAttribute(DCMConstants.RELEASE_ID_ATTRIBUTE);
        if (!releaseID.isEmpty()) {
            invColInfo.setReleaseID(releaseID);
        }
        // releaseDate
        String releaseDateValue = inInventoryNode.getAttribute(DCMConstants.RELEASE_DATE_ATTRIBUTE);
        if (!releaseDateValue.isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DCMConstants.INVENTORY_COMPONENT_DATE_FORMAT_STRING);
            Date date = null;
            try {
                date = simpleDateFormat.parse(releaseDateValue);
            } catch (ParseException ex) {
                try {
                    simpleDateFormat = new SimpleDateFormat(DCMConstants.DATE_FORMAT_STRING);
                    date = simpleDateFormat.parse(releaseDateValue);
                } catch (ParseException ex3) {
                    try {
                        simpleDateFormat = new SimpleDateFormat(DCMConstants.INVENTORY_COMPONENT_RELEASE_DATE_FORMAT_STRING);
                        date = simpleDateFormat.parse(releaseDateValue);
                    } catch (ParseException ex2) {
                        Logger.getLogger(DCMCatalog.class.getName()).log(Level.WARNING, null, ex2);
                    }
                }
            }
            invColInfo.setReleaseDate(date);
        }
        // vendorVersion
        String vendorVersion = inInventoryNode.getAttribute(DCMConstants.VENDOR_VERSION_ATTRIBUTE);
        if (!vendorVersion.isEmpty()) {
            invColInfo.setVendorVersion(vendorVersion);
        }
        // dellVersion
        String dellVersion = inInventoryNode.getAttribute(DCMConstants.DELL_VERSION_ATTRIBUTE);
        if (!dellVersion.isEmpty()) {
            invColInfo.setDellVersion(dellVersion);
        }
        // osCode
        String osCode = inInventoryNode.getAttribute(DCMConstants.OS_CODE_ATTRIBUTE);
        if (!osCode.isEmpty()) {
            invColInfo.setOSCode(osCode);
        }
        // hashMD5
        String hashMD5 = inInventoryNode.getAttribute(DCMConstants.HASH_MD5_ATTRIBUTE);
        if (!hashMD5.isEmpty()) {
            invColInfo.setHashMD5(hashMD5);
        }
        // path
        String path = inInventoryNode.getAttribute(DCMConstants.PATH_ATTRIBUTE);
        if (!path.isEmpty()) {
            invColInfo.setPath(path);
        }
        // dateTime
        String dateValue = inInventoryNode.getAttribute(DCMConstants.DATE_TIME_ATTRIBUTE);
        if (!dateValue.isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DCMConstants.INVENTORY_COMPONENT_DATE_FORMAT_STRING);
            Date date = null;
            try {
                date = simpleDateFormat.parse(dateValue);
            } catch (ParseException ex) {
                Logger.getLogger(DCMCatalog.class.getName()).log(Level.WARNING, null, ex);
            }
            invColInfo.setDate(date);
        }
        // size
        String size = inInventoryNode.getAttribute(DCMConstants.SIZE_ATTRIBUTE);
        if (!size.isEmpty()) {
            invColInfo.setSize(Long.parseLong(size));
        }
        mManifest.getInventoryCollectorInformationCollection().addInventoryCollectorInformation(invColInfo);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the display
     *
     * @param inNode specifies the node
     * @return display string
     */
    private DCMI18NString getDisplay(Element inNode) {
        if (inNode == null) {
            return null;
        }
        DCMI18NString retVal = new DCMI18NString();
        for (Element displayElement : getFirstLevelChildrenWithGivenName(inNode, DCMConstants.DISPLAY)) {
            String langValue = displayElement.getAttribute(DCMConstants.LANG_ATTRIBUTE);
            if (langValue.equals(DCMConstants.EN_LANGUAGE)) {
                retVal.setLocaleString(Locale.ENGLISH, displayElement.getTextContent());
            }
            //TODO add support for other languages here  
        }
        return retVal;
    }

    /**
     * Method for parsing the software component node
     *
     * @param inSoftwareComponentNode specifies the software component node to
     * be parsed
     * @return update package parsed
     */
    private DCMUpdatePackageInformation parseSoftwareComponent(Element inSoftwareComponentNode, String packagePath) {
        if (null == inSoftwareComponentNode) {
            return null;
        }
        if (!inSoftwareComponentNode.getNodeName().equals(DCMConstants.SOFTWARE_COMPONENT)) {
            return null;
        }
        String packageID = inSoftwareComponentNode.getAttribute(DCMConstants.PACKAGE_ID_ATTRIBUTE);
        if (packageID == null || packageID.isEmpty()) {
            return null;
        }
        String packageType = inSoftwareComponentNode.getAttribute(DCMConstants.PACKAGE_TYPE_ATTRIBUTE);
        if (packageType.isEmpty()) {
            return null;
        }
        DCMUpdatePackageInformation updatePackageInformation = new DCMUpdatePackageInformation(packageID, DCMPackageType.getEnumeration(packageType));
        // Schema Version
        String schemaVersion = inSoftwareComponentNode.getAttribute(DCMConstants.SCHEMA_VERSION_ATTRIBUTE);
        if (!schemaVersion.isEmpty()) {
            updatePackageInformation.setSchemaVersion(schemaVersion);
        }
        // Release ID
        String releaseID = inSoftwareComponentNode.getAttribute(DCMConstants.RELEASE_ID_ATTRIBUTE);
        if (!releaseID.isEmpty()) {
            updatePackageInformation.setReleaseID(releaseID);
        }
        // Release ID
        String hash = inSoftwareComponentNode.getAttribute(DCMConstants.PACKAGE_HASH);
        if (!hash.isEmpty()) {
            updatePackageInformation.setHashofHash(hash);
        }
        // Release Date
        String releaseDate = inSoftwareComponentNode.getAttribute(DCMConstants.RELEASE_DATE_ATTRIBUTE);
        if (!releaseDate.isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DCMConstants.INVENTORY_COMPONENT_RELEASE_DATE_FORMAT_STRING);
            Date relDate = null;
            try {
                relDate = simpleDateFormat.parse(releaseDate);
            } catch (ParseException ex) {
                Logger.getLogger(DCMCatalog.class.getName()).log(Level.WARNING, null, ex);
                simpleDateFormat = new SimpleDateFormat(DCMConstants.DATE_FORMAT_STRING);
                try {
                    relDate = simpleDateFormat.parse(releaseDate);
                } catch (ParseException ex1) {
                    Logger.getLogger(DCMCatalog.class.getName()).log(Level.WARNING, null, ex1);
                }
            }
            updatePackageInformation.setReleaseDate(relDate);
        }
        // Vendor Version
        String vendorVersion = inSoftwareComponentNode.getAttribute(DCMConstants.VENDOR_VERSION_ATTRIBUTE);
        if (!vendorVersion.isEmpty()) {
            updatePackageInformation.setVendorVersion(vendorVersion);
        }
        // Dell Version
        String dellVersion = inSoftwareComponentNode.getAttribute(DCMConstants.DELL_VERSION_ATTRIBUTE);
        if (!dellVersion.isEmpty()) {
            updatePackageInformation.setDellVersion(dellVersion);
        }
        // XML Gen Version
        String xmlGenVersion = inSoftwareComponentNode.getAttribute(DCMConstants.XML_GEN_VERSION_ATTRIBUTE);
        if (!xmlGenVersion.isEmpty()) {
            updatePackageInformation.setXMLGenVersion(xmlGenVersion);
        }
        // Path
        String path = inSoftwareComponentNode.getAttribute(DCMConstants.PATH_ATTRIBUTE);
        if (!path.isEmpty()) {
            updatePackageInformation.setPath(path);
        } else if (packagePath != null && !packagePath.isEmpty()) {
            updatePackageInformation.setPath(packagePath);
        }
        // dateTime
        String dateTime = inSoftwareComponentNode.getAttribute(DCMConstants.DATE_TIME_ATTRIBUTE);
        if (!dateTime.isEmpty()) {
            SimpleDateFormat simpleDatFormat = new SimpleDateFormat(DCMConstants.DATE_FORMAT_STRING);
            Date date = null;
            try {
                date = simpleDatFormat.parse(dateTime);
            } catch (ParseException ex) {
                Logger.getLogger(DCMCatalog.class.getName()).log(Level.WARNING, null, ex);
            }
            updatePackageInformation.setDate(date);
        }
        // hash MD5
        String hashMD5 = inSoftwareComponentNode.getAttribute(DCMConstants.HASH_MD5_ATTRIBUTE);
        if (!hashMD5.isEmpty()) {
            updatePackageInformation.setMD5Hash(hashMD5);
        }
        // Size
        String size = inSoftwareComponentNode.getAttribute(DCMConstants.SIZE_ATTRIBUTE);
        if (!size.isEmpty()) {
            updatePackageInformation.setSize(Long.parseLong(size));
        }
        else{
            updatePackageInformation.setSize(0L);
        }
        
        // Identifier
        String identifier = inSoftwareComponentNode.getAttribute(DCMConstants.IDENTIFIER_ATTRIBUTE);
        if (!identifier.isEmpty()) {
            DCMGUID id = new DCMGUID();
            id.setID(identifier);
            updatePackageInformation.setIdentifier(id);
        }
        // Predecessor ID
        String predecessorID = inSoftwareComponentNode.getAttribute(DCMConstants.PREDECESSOR_ID_ATTRIBUTE);
        if (!predecessorID.isEmpty()) {
            DCMGUID predID = new DCMGUID();
            predID.setID(predecessorID);
            updatePackageInformation.setPredecessorIdentifier(predID);
        }
        // Reboot Required
        String rebootRequired = inSoftwareComponentNode.getAttribute(DCMConstants.REBOOT_REQUIRED_ATTRIBUTE);
        if (!rebootRequired.isEmpty()) {
            updatePackageInformation.setRequiresReboot(Boolean.parseBoolean(rebootRequired));
        }
        // Container Power Cycle Required
        String containerPowerCycleRequired = inSoftwareComponentNode.getAttribute(DCMConstants.CONTAINER_POWER_CYCLE_REQUIRED_ATTRIBUTE);
        if (!containerPowerCycleRequired.isEmpty()) {
            updatePackageInformation.setRequiresContainerPowerCycle(Boolean.parseBoolean(containerPowerCycleRequired));
        }
        // Name
        Element nameNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.NAME);
        if (nameNode != null) {
            DCMI18NString name = getDisplay(nameNode);
            if (name != null) {
                updatePackageInformation.setName(name);
            }
        }
        // Component Type
        Element componentTypeNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.COMPONENT_TYPE);
        if (componentTypeNode != null) {
            DCMComponentType compType = DCMComponentType.getEnumeration(componentTypeNode.getAttribute(DCMConstants.VALUE_ATTRIBUTE));
            if (mManifest.getComponentKindCollection().getComponentKind(compType) == null) {
                DCMI18NString name = getDisplay(componentTypeNode);
                mManifest.getComponentKindCollection().addComponentKind(compType, name);
            }
            updatePackageInformation.setComponentType(compType);
        }
        // Description
        Element descriptionNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.DESCRIPTION);
        if (descriptionNode != null) {
            DCMI18NString description = getDisplay(descriptionNode);
            updatePackageInformation.setDescription(description);
        }
        // LUCategory
        Element luCategory = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.LU_CATEGORY);
        if (luCategory != null) {
            String luCategoryCode = luCategory.getAttribute(DCMConstants.VALUE_ATTRIBUTE);
            if (mManifest.getCategoryCollection().getCategory(luCategoryCode) == null) {
                DCMI18NString name = getDisplay(luCategory);
                mManifest.getCategoryCollection().addCategory(luCategoryCode, name);
            }
            updatePackageInformation.setLUCategoryCode(luCategoryCode);
        }
        // Category
        Element category = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.CATEGORY);
        if (category != null) {
            String categoryCode = category.getAttribute(DCMConstants.VALUE_ATTRIBUTE);
            if (mManifest.getCategoryCollection().getCategory(categoryCode) == null) {
                DCMI18NString name = getDisplay(category);
                mManifest.getCategoryCollection().addCategory(categoryCode, name);
            }
            updatePackageInformation.setCategoryCode(categoryCode);
        }
        // Supported Devices
        Element supportedDevicesNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.SUPPORTED_DEVICES);
        if (supportedDevicesNode != null) {
            for (Element device : getFirstLevelChildrenWithGivenName(supportedDevicesNode, DCMConstants.DEVICE)) {
                for (String deviceIdentifier : parseDevice(device, updatePackageInformation)) {
                    updatePackageInformation.addSupportedTarget(deviceIdentifier);
                }
            }
        }
        // Supported Systems
        Element supportedSystemsNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.SUPPORTED_SYSTEMS);
        if (supportedSystemsNode != null) {
            for (Element brand : getFirstLevelChildrenWithGivenName(supportedSystemsNode, DCMConstants.BRAND)) {
                Set<String> systemIdentifiers = parseBrand(brand);
                for (String systemIdentifier : systemIdentifiers) {
                    updatePackageInformation.addSupportedSystem(systemIdentifier);
                }
            }
        }
        // Supported Operating Systems
        Element supportedOperatingSystemsNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.SUPPORTED_OPERATING_SYSTEMS);
        if (supportedOperatingSystemsNode != null) {
            for (Element osElement : getFirstLevelChildrenWithGivenName(supportedOperatingSystemsNode, DCMConstants.OPERATING_SYSTEM)) {
                DCMOperatingSystem os = parseOperatingSystem(osElement);
                if (null != os) {
                    mManifest.getOperatingSystemCollection().addOperatingSystem(os);
                    updatePackageInformation.addSupportedOS(os.getUniqueIdentifier());
                }
            }
        }
        // Install Instructions
        Element installInstructionsNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.INSTALL_INSTRUCTIONS);
        if (installInstructionsNode != null) {
            DCMInstallInstructions installInstructions = new DCMInstallInstructions();
            installInstructions.setName(getDisplay(installInstructionsNode));
            installInstructions.setFileName(installInstructionsNode.getAttribute(DCMConstants.FILE_NAME_ATTRIBUTE));
            installInstructions.setTypeCode(installInstructionsNode.getAttribute(DCMConstants.TYPE_CODE_ATTRIBUTE));
            updatePackageInformation.setInstallInstruction(installInstructions);
        }
        // Revision History
        Element revisionHistoryNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.REVISION_HISTORY);
        if (revisionHistoryNode != null) {
            DCMI18NString revisionHistory = getDisplay(revisionHistoryNode);
            updatePackageInformation.setRevisionHistory(revisionHistory);
        }
        // Important Information
        Element importantInfoNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.IMPORTANT_INFO);
        if (importantInfoNode != null) {
            DCMImportantInformation importantInformation = new DCMImportantInformation();
            try {
                importantInformation.setURL(new URL(importantInfoNode.getAttribute(DCMConstants.URL_ATTRIBUTE)));
            } catch (MalformedURLException ex) {
                Logger.getLogger(DCMCatalog.class.getName()).log(Level.WARNING, null, ex);
            }
            importantInformation.setInformation(getDisplay(importantInfoNode));
            updatePackageInformation.setImportantInformation(importantInformation);
        }
        // Criticality
        Element criticalityNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.CRITICALITY);
        if (criticalityNode != null) {
            updatePackageInformation.setCriticality(DCMCriticality.getEnumeration(criticalityNode.getAttribute(DCMConstants.VALUE_ATTRIBUTE)));
            updatePackageInformation.setCriticalityDescription(getDisplay(criticalityNode));
        }
        // MSIID
        Element msiIDNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.MSIID);
        if (msiIDNode != null) {
            DCMGUID id = new DCMGUID();
            id.setID(msiIDNode.getTextContent());
            updatePackageInformation.setMSIID(id);
        }
        // FMPWrappers
        Element fmpWrappersNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.FMP_WRAPPERS);
        if (fmpWrappersNode != null) {
            for (Element fmpWrapper : getFirstLevelChildrenWithGivenName(fmpWrappersNode, DCMConstants.FMP_WRAPPER_INFORMATION)) {
                DCMFMPWrapperInformation fmpWrapperInformation = new DCMFMPWrapperInformation();
                Element inventory = getFirstLevelChildWithGivenName(fmpWrapper, DCMConstants.INVENTORY);
                if (inventory != null) {
                    fmpWrapperInformation.setInventorySupport(Boolean.parseBoolean(inventory.getAttribute(DCMConstants.SUPPORTED_ATTRIBUTE)));
                    fmpWrapperInformation.setInventorySource(inventory.getAttribute(DCMConstants.SOURCE_ATTRIBUTE));
                }
                Element update = getFirstLevelChildWithGivenName(fmpWrapper, DCMConstants.UPDATE);
                if (update != null) {
                    fmpWrapperInformation.setUpdateSupport(Boolean.parseBoolean(update.getAttribute(DCMConstants.SUPPORTED_ATTRIBUTE)));
                    fmpWrapperInformation.setRollbackSupport(Boolean.parseBoolean(update.getAttribute(DCMConstants.ROLL_BACK_ATTRIBUTE)));
                }
                DCMGUID id = new DCMGUID();
                id.setID(fmpWrapper.getAttribute(DCMConstants.IDENTIFIER_ATTRIBUTE));
                fmpWrapperInformation.setName(fmpWrapper.getAttribute(DCMConstants.NAME_ATTRIBUTE));
                fmpWrapperInformation.setIdentifier(id);
                fmpWrapperInformation.setFilePath(fmpWrapper.getAttribute(DCMConstants.FILE_PATH_ATTRIBUTE));
                fmpWrapperInformation.setDriverFileName(fmpWrapper.getAttribute(DCMConstants.DRIVER_FILE_NAME_ATTRIBUTE));
                fmpWrapperInformation.setDigitallySigned(Boolean.parseBoolean(fmpWrapper.getAttribute(DCMConstants.DIGITAL_SIGNATURE_ATTRIBUTE)));
                updatePackageInformation.addFMPWrapper(fmpWrapperInformation);
            }
        }
        // Constituenets
        Element constituentsNode = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.CONSTITUENTS);
        if (constituentsNode != null) {
            for (Element constituentElement : getFirstLevelChildrenWithGivenName(constituentsNode, DCMConstants.CONSTITUENT)) {
                DCMConstituent constituent = new DCMConstituent();
                constituent.setVersion(constituentElement.getAttribute(DCMConstants.VERSION_ATTRIBUTE));
                constituent.setPath(constituentElement.getAttribute(DCMConstants.PATH_ATTRIBUTE));
                Element constituentComponentTypeNode = getFirstLevelChildWithGivenName(constituentElement, DCMConstants.COMPONENT_TYPE);
                if (constituentComponentTypeNode != null) {
                    DCMComponentType constituentCompType = DCMComponentType.getEnumeration(constituentComponentTypeNode.getAttribute(DCMConstants.VALUE_ATTRIBUTE));
                    if (mManifest.getComponentKindCollection().getComponentKind(constituentCompType) == null) {
                        DCMI18NString name = getDisplay(componentTypeNode);
                        mManifest.getComponentKindCollection().addComponentKind(constituentCompType, name);
                    }
                    constituent.setComponentType(constituentCompType);
                }
                // Supported Devices
                Element constituentSupportedDevicesNode = getFirstLevelChildWithGivenName(constituentElement, DCMConstants.SUPPORTED_DEVICES);
                if (constituentSupportedDevicesNode != null) {
                    for (Element device : getFirstLevelChildrenWithGivenName(constituentSupportedDevicesNode, DCMConstants.DEVICE)) {
                        for (String deviceIdentifier : parseDevice(device, constituent)) {
                            constituent.addSupportedTarget(deviceIdentifier);
                        }
                    }
                }
                // Supported Operating Systems
                Element constituentSupportedOperatingSystemsNode = getFirstLevelChildWithGivenName(constituentElement, DCMConstants.SUPPORTED_OPERATING_SYSTEMS);
                if (constituentSupportedOperatingSystemsNode != null) {
                    for (Element osElement : getFirstLevelChildrenWithGivenName(constituentSupportedOperatingSystemsNode, DCMConstants.OPERATING_SYSTEM)) {
                        DCMOperatingSystem os = parseOperatingSystem(osElement);
                        if (null != os) {
                            mManifest.getOperatingSystemCollection().addOperatingSystem(os);
                            constituent.addSupportedOS(os.getUniqueIdentifier());
                        }
                    }
                }
                updatePackageInformation.addConstituent(constituent);
            }
        }
        // Activation Rules
        Element activationRules = getFirstLevelChildWithGivenName(inSoftwareComponentNode, DCMConstants.ACTIVATIONRULES);
        if (activationRules != null) {
            DCMActivationRules activation = DCMActivationRules.create(activationRules);
            updatePackageInformation.setActivationRules(activation);
        }
        return updatePackageInformation;
    }

    /**
     * Method for parsing software bundle
     *
     * @param inSoftwareBundleNode specifies the software bundle to be parsed
     * @return SUCCESS if the bundle could be parsed else appropriate error code
     * is returned
     */
    private int parseSoftwareBundle(Element inSoftwareBundleNode) {
        if (null == inSoftwareBundleNode) {
            return DCMErrorCodes.FAILURE;
        }
        if (!inSoftwareBundleNode.getNodeName().equals(DCMConstants.SOFTWARE_BUNDLE)) {
            return DCMErrorCodes.FAILURE;
        }
        DCMBundleType bundleType = DCMBundleType.getEnumeration(inSoftwareBundleNode.getAttribute(DCMConstants.BUNDLE_TYPE_ATTRIBUTE));
        DCMBundle bundle = new DCMBundle(bundleType);
        // Schema Version
        String schemaVersion = inSoftwareBundleNode.getAttribute(DCMConstants.SCHEMA_VERSION_ATTRIBUTE);
        if (!schemaVersion.isEmpty()) {
            bundle.setSchemaVersion(schemaVersion);
        }
        // Release ID
        String releaseID = inSoftwareBundleNode.getAttribute(DCMConstants.RELEASE_ID_ATTRIBUTE);
        if (!releaseID.isEmpty()) {
            bundle.setReleaseID(releaseID);
        }
        // dateTime
        String dateTime = inSoftwareBundleNode.getAttribute(DCMConstants.DATE_TIME_ATTRIBUTE);
        if (!dateTime.isEmpty()) {
            SimpleDateFormat simpleDatFormat = new SimpleDateFormat(DCMConstants.DATE_FORMAT_STRING);
            Date date = null;
            try {
                date = simpleDatFormat.parse(dateTime);
            } catch (ParseException ex) {
                Logger.getLogger(DCMCatalog.class.getName()).log(Level.WARNING, null, ex);
            }
            bundle.setCreationTime(date);
        }
        // Vendor Version
        String vendorVersion = inSoftwareBundleNode.getAttribute(DCMConstants.VENDOR_VERSION_ATTRIBUTE);
        if (!vendorVersion.isEmpty()) {
            bundle.setVendorVersion(vendorVersion);
        }
        // Path
        String path = inSoftwareBundleNode.getAttribute(DCMConstants.PATH_ATTRIBUTE);
        if (!path.isEmpty()) {
            bundle.setPath(path);
        }
        // Bundle ID
        String bundleID = inSoftwareBundleNode.getAttribute(DCMConstants.BUNDLE_ID_ATTRIBUTE);
        if (!bundleID.isEmpty()) {
            bundle.setBundleID(bundleID);
        }
        // identifier
        String identifier = inSoftwareBundleNode.getAttribute(DCMConstants.IDENTIFIER_ATTRIBUTE);
        if (!identifier.isEmpty()) {
            DCMGUID id = new DCMGUID();
            id.setID(identifier);
            bundle.setIdentifier(id);
        }
        // Predecessor ID
        String predecessorID = inSoftwareBundleNode.getAttribute(DCMConstants.PREDECESSOR_ID_ATTRIBUTE);
        if (!predecessorID.isEmpty()) {
            DCMGUID id = new DCMGUID();
            id.setID(identifier);
            bundle.setPredecessorIdentifier(id);
        }
        // Size
        String size = inSoftwareBundleNode.getAttribute(DCMConstants.SIZE_ATTRIBUTE);
        if (!size.isEmpty()) {
            bundle.setSize(Long.parseLong(size));
        }
        else{
            bundle.setSize(0L);
        }
            
        // Name
        Element nameNode = getFirstLevelChildWithGivenName(inSoftwareBundleNode, DCMConstants.NAME);
        if (nameNode != null) {
            DCMI18NString name = getDisplay(nameNode);
            if (name != null) {
                bundle.setName(name);
            }
        }
        // Component Type
        Element componentTypeNode = getFirstLevelChildWithGivenName(inSoftwareBundleNode, DCMConstants.COMPONENT_TYPE);
        if (componentTypeNode != null) {
            DCMComponentType compType = DCMComponentType.getEnumeration(componentTypeNode.getAttribute(DCMConstants.VALUE_ATTRIBUTE));
            if (mManifest.getComponentKindCollection().getComponentKind(compType) == null) {
                DCMI18NString name = getDisplay(componentTypeNode);
                mManifest.getComponentKindCollection().addComponentKind(compType, name);
            }
            bundle.setComponentType(compType);
        }
        // Description
        Element descriptionNode = getFirstLevelChildWithGivenName(inSoftwareBundleNode, DCMConstants.DESCRIPTION);
        if (descriptionNode != null) {
            DCMI18NString description = getDisplay(descriptionNode);
            bundle.setDescription(description);
        }
        // Category
        Element categoryNode = getFirstLevelChildWithGivenName(inSoftwareBundleNode, DCMConstants.CATEGORY);
        if (categoryNode != null) {
            String categoryCode = categoryNode.getAttribute(DCMConstants.VALUE_ATTRIBUTE);
            if (mManifest.getCategoryCollection().getCategory(categoryCode) == null) {
                DCMI18NString name = getDisplay(categoryNode);
                mManifest.getCategoryCollection().addCategory(categoryCode, name);
            }
            bundle.setCategoryCode(categoryCode);
        }
        // Target OSes
        Element targetOSesNode = getFirstLevelChildWithGivenName(inSoftwareBundleNode, DCMConstants.TARGET_OSES);
        if (targetOSesNode != null) {
            for (Element osElement : getFirstLevelChildrenWithGivenName(targetOSesNode, DCMConstants.OPERATING_SYSTEM)) {
                DCMOperatingSystem os = parseOperatingSystem(osElement);
                if (null != os) {
                    mManifest.getOperatingSystemCollection().addOperatingSystem(os);
                    bundle.addTargetOperatingSystemIdentifier(os.getUniqueIdentifier());
                }
            }
        }
        // Target Systems
        Element targetSystemsNode = getFirstLevelChildWithGivenName(inSoftwareBundleNode, DCMConstants.TARGET_SYSTEMS);
        if (targetSystemsNode != null) {
            for (Element brandElement : getFirstLevelChildrenWithGivenName(targetSystemsNode, DCMConstants.BRAND)) {
                Set<String> systemIdentifiers = parseBrand(brandElement);
                for (String systemIdentifier : systemIdentifiers) {
                    bundle.addTargetSystemIdentifier(systemIdentifier);
                }
            }
        }
        // Revision History
        Element revisionHistoryNode = getFirstLevelChildWithGivenName(inSoftwareBundleNode, DCMConstants.REVISION_HISTORY);
        if (revisionHistoryNode != null) {
            DCMI18NString revisionHistory = getDisplay(revisionHistoryNode);
            bundle.setRevisionHistory(revisionHistory);
        }
        // Contents
        Element contentsNode = getFirstLevelChildWithGivenName(inSoftwareBundleNode, DCMConstants.CONTENTS);
        if (contentsNode != null) {
            for (Element packageElement : getFirstLevelChildrenWithGivenName(contentsNode, DCMConstants.PACKAGE)) {
                String pathValue = packageElement.getAttribute(DCMConstants.PATH_ATTRIBUTE);
                if (!pathValue.isEmpty()) {
                    bundle.addUpdatePackageIdentifier(mManifest.getSoftwareComponents().getUpdatePackageIdentifier(pathValue));
                }
                String packageIDValue = packageElement.getAttribute(DCMConstants.PACKAGE_ID_ATTRIBUTE);
                if (!packageIDValue.isEmpty()) {

                }
                String packID = packageElement.getAttribute(DCMConstants.IDENTIFIER_ATTRIBUTE);
                if (!packID.isEmpty()) {
                    bundle.addUpdatePackageIdentifier(packID);
                }
            }
        }
        // Important Information
        Element importantInfoNode = getFirstLevelChildWithGivenName(inSoftwareBundleNode, DCMConstants.IMPORTANT_INFO);
        if (importantInfoNode != null) {
            DCMImportantInformation importantInformation = new DCMImportantInformation();
            String importantInfoNodeAttributeValue = importantInfoNode.getAttribute(DCMConstants.URL_ATTRIBUTE);

            try {

                if (importantInfoNodeAttributeValue != null && !importantInfoNodeAttributeValue.isEmpty()) {
                    importantInformation.setURL(new URL(importantInfoNode.getAttribute(DCMConstants.URL_ATTRIBUTE)));
                    importantInformation.setInformation(getDisplay(importantInfoNode));
                    bundle.setImportantInformation(importantInformation);
                }

            } catch (MalformedURLException ex) {
                Logger.getLogger(DCMCatalog.class.getName()).log(Level.WARNING, null, ex);
            }

        }
        mManifest.getBundleCollection().addBundle(bundle);
        return DCMErrorCodes.SUCCESS;
    }

    private int parsePrerequisites(Element inPrerequisites) {
        if (null == inPrerequisites) {
            return DCMErrorCodes.FAILURE;
        }
        if (!inPrerequisites.getNodeName().equals(DCMConstants.PREREQUISITES)) {
            return DCMErrorCodes.FAILURE;
        }
        for (Element softwareComponentElement : getFirstLevelChildrenWithGivenName(inPrerequisites, DCMConstants.SOFTWARE_COMPONENT)) {
            DCMUpdatePackageInformation softwareComponent = parseSoftwareComponent(softwareComponentElement, "");
            if (null != softwareComponent) {
                mManifest.getPrerequisites().addUpdatePackageInformation(softwareComponent);
            }
        }
        return DCMErrorCodes.SUCCESS;
    }

    private Set<String> parseBrand(Element inLOB) {
        if (null == inLOB) {
            return null;
        }
        if (!inLOB.getNodeName().equals(DCMConstants.BRAND)) {
            return null;
        }
        // key
        String key = inLOB.getAttribute(DCMConstants.KEY_ATTRIBUTE);
        if (key.isEmpty()) {
            return null;
        }
        // prefix
        String prefix = inLOB.getAttribute(DCMConstants.PREFIX_ATTRIBUTE);
        if (prefix.isEmpty()) {
            return null;
        }
        DCMLineOfBusiness lob = new DCMLineOfBusiness(Integer.parseInt(key), prefix, getDisplay(inLOB));
        mManifest.getLineOfBusinessCollection().addLineOfBusiness(lob);
        Set<String> retVal = new HashSet<>();
        for (Element modelElement : getFirstLevelChildrenWithGivenName(inLOB, DCMConstants.MODEL)) {
            Set<String> systemList = parseModel(modelElement, lob.getKey());
            retVal.addAll(systemList);
        }
        return retVal;
    }

    private Set<String> parseDevice(Element inDevice, Object inParent) {
        if (inDevice == inParent || inParent == null) {
            return null;
        }
        if (!inDevice.getNodeName().equals(DCMConstants.DEVICE)) {
            return null;
        }
        // componentID
        long componentID = Long.parseLong(inDevice.getAttribute(DCMConstants.COMPONENT_ID_ATTRIBUTE));
        // embedded
        boolean embedded = Boolean.parseBoolean(inDevice.getAttribute(DCMConstants.EMBEDDED));
        DCMI18NString name = getDisplay(inDevice);
        DCMUpdatePackageInformation updatePackage = null;
        if (inParent instanceof DCMUpdatePackageInformation) {
            updatePackage = (DCMUpdatePackageInformation) inParent;
        }
        DCMConstituent constituent = null;
        if (inParent instanceof DCMConstituent) {
            constituent = (DCMConstituent) inParent;
        }
        HashSet<DCMDependency> dependencies = new HashSet<>();
        HashSet<DCMSoftDependency> softDependencies = new HashSet<>();
        for (Element dependencyElement : getFirstLevelChildrenWithGivenName(inDevice, DCMConstants.DEPENDENCY)) {
            DCMDependency dependency = new DCMDependency();
            dependency.setName(getDisplay(dependencyElement));
            Collection<Element> pciInfoCollection = getFirstLevelChildrenWithGivenName(dependencyElement, DCMConstants.PCI_INFO);
            Collection<Element> pnpInfoCollection = getFirstLevelChildrenWithGivenName(dependencyElement, DCMConstants.PNP_INFO);
            for (Element pciInfo : pciInfoCollection) {
                DCMPCIInfo pciInformation = parsePCIInfo(pciInfo);
                dependency.addSupportedTarget(pciInformation.getInfo());
            }
            for (Element pnpInfo : pnpInfoCollection) {
                DCMPnPInfo pnpInformation = parsePnPInfo(pnpInfo);
                dependency.addSupportedTarget(pnpInformation.getInfo());
            }
            dependency.setComponentType(DCMComponentType.getEnumeration(dependencyElement.getAttribute(DCMConstants.COMPONENT_TYPE_ATTRIBUTE)));
            dependency.setComponentID(Long.parseLong(dependencyElement.getAttribute(DCMConstants.COMPONENT_ID_ATTRIBUTE)));
            dependency.setVersion(dependencyElement.getAttribute(DCMConstants.VERSION_ATTRIBUTE));
            DCMGUID prerequisite = new DCMGUID();
            prerequisite.setID(dependencyElement.getAttribute(DCMConstants.PREREQUISITE_ATTRIBUTE));
            dependency.setPrerequisite(prerequisite);
            dependency.setPath(dependencyElement.getAttribute(DCMConstants.PATH_ATTRIBUTE));
            dependency.setReleaseID(dependencyElement.getAttribute(DCMConstants.RELEASE_ID_ATTRIBUTE));
            dependency.setContext(DCMDependencyContext.getEnumeration(dependencyElement.getAttribute(DCMConstants.CONTEXT_ATTRIBUTE)));
            dependencies.add(dependency);
            if (updatePackage != null) {
                updatePackage.addDependencyInformation(dependency);
            } else if (constituent != null) {
                constituent.addDependencyInformation(dependency);
            }
        }
        for (Element softDependencyElement : getFirstLevelChildrenWithGivenName(inDevice, DCMConstants.SOFT_DEPENDENCY)) {
            DCMSoftDependency softDependency = new DCMSoftDependency();
            softDependency.setName(getDisplay(softDependencyElement));
            Collection<Element> pciInfoCollection = getFirstLevelChildrenWithGivenName(softDependencyElement, DCMConstants.PCI_INFO);
            Collection<Element> pnpInfoCollection = getFirstLevelChildrenWithGivenName(softDependencyElement, DCMConstants.PNP_INFO);
            for (Element pciInfo : pciInfoCollection) {
                DCMPCIInfo pciInformation = parsePCIInfo(pciInfo);
            }
            for (Element pnpInfo : pnpInfoCollection) {
                DCMPnPInfo pnpInformation = parsePnPInfo(pnpInfo);
            }
            softDependency.setComponentType(DCMComponentType.getEnumeration(softDependencyElement.getAttribute(DCMConstants.COMPONENT_TYPE_ATTRIBUTE)));
            softDependency.setComponentID(Long.parseLong(softDependencyElement.getAttribute(DCMConstants.COMPONENT_ID_ATTRIBUTE)));
            softDependency.setVersion(softDependencyElement.getAttribute(DCMConstants.VERSION_ATTRIBUTE));
            DCMGUID prerequisite = new DCMGUID();
            prerequisite.setID(softDependencyElement.getAttribute(DCMConstants.PREREQUISITE_ATTRIBUTE));
            softDependency.setPrerequisite(prerequisite);
            softDependency.setPath(softDependencyElement.getAttribute(DCMConstants.PATH_ATTRIBUTE));
            softDependency.setReleaseID(softDependencyElement.getAttribute(DCMConstants.RELEASE_ID_ATTRIBUTE));
            softDependency.setContext(DCMDependencyContext.getEnumeration(softDependencyElement.getAttribute(DCMConstants.CONTEXT_ATTRIBUTE)));
            DCMI18NString detail = new DCMI18NString();
            for (Element detailElement : getFirstLevelChildrenWithGivenName(softDependencyElement, DCMConstants.DETAIL_ATTRIBUTE)) {
                String langValue = detailElement.getAttribute(DCMConstants.LANG_ATTRIBUTE);
                if (langValue.equals(DCMConstants.EN_LANGUAGE)) {
                    detail.setLocaleString(Locale.ENGLISH, detailElement.getTextContent());
                }
                //TODO add support for other languages here  
            }
            softDependency.setDetail(detail);
            softDependencies.add(softDependency);
            if (updatePackage != null) {
                updatePackage.addSoftDependencyInformation(softDependency);
            } else if (constituent != null) {
                constituent.addSoftDependencyInformation(softDependency);
            }
        }
        Set<String> retVal = new HashSet<>();
        Collection<Element> pciInfoCollection = getFirstLevelChildrenWithGivenName(inDevice, DCMConstants.PCI_INFO);
        Collection<Element> pnpInfoCollection = getFirstLevelChildrenWithGivenName(inDevice, DCMConstants.PNP_INFO);
        DCMBaseTarget baseTarget = null;
        for (Element pciInfo : pciInfoCollection) {
            DCMPCIInfo pciInformation = parsePCIInfo(pciInfo);
            DCMPCIDevice pciDevice = new DCMPCIDevice(pciInformation);
            pciDevice.setComponentID(componentID);
            pciDevice.setName(name);
            pciDevice.setEmbedded(embedded);

            //adding target map to updatepackage
            updatePackage.getTargetMap().put(pciDevice.getUniqueIdentifier(), pciDevice);

            retVal.add(pciDevice.getUniqueIdentifier());
            for (DCMDependency dependency : dependencies) {
                if (updatePackage != null) {
                    updatePackage.addDependencyReference(pciDevice.getUniqueIdentifier(), dependency);
                } else if (constituent != null) {
                    constituent.addDependencyReference(pciDevice.getUniqueIdentifier(), dependency);
                }
            }
            for (DCMSoftDependency softDependency : softDependencies) {
                if (updatePackage != null) {
                    updatePackage.addSoftDependencyReference(pciDevice.getUniqueIdentifier(), softDependency);
                } else if (constituent != null) {
                    constituent.addSoftDependencyReference(pciDevice.getUniqueIdentifier(), softDependency);
                }
            }
            // PayloadConfiguration
            Element payloadConfigurationElement = getFirstLevelChildWithGivenName(inDevice, DCMConstants.PAYLOAD_CONFIGURATION);
            if (payloadConfigurationElement != null) {
                DCMPayloadConfiguration payloadConfiguration = new DCMPayloadConfiguration();
                for (Element imageElement : getFirstLevelChildrenWithGivenName(payloadConfigurationElement, DCMConstants.IMAGE)) {
                    DCMPayloadImage image = new DCMPayloadImage();
                    image.setType(imageElement.getAttribute(DCMConstants.TYPE_ATTRIBUTE));
                    DCMGUID id = new DCMGUID();
                    id.setID(imageElement.getAttribute(DCMConstants.ID_ATTRIBUTE));
                    image.setID(id);
                    image.setVersion(imageElement.getAttribute(DCMConstants.VERSION_ATTRIBUTE));
                    image.setFileName(imageElement.getAttribute(DCMConstants.FILE_NAME_ATTRIBUTE));
                    image.setShouldSkip(Boolean.parseBoolean(imageElement.getAttribute(DCMConstants.SKIP_ATTRIBUTE)));
                    payloadConfiguration.addImage(image);
                }
                Element updateDriverElement = getFirstLevelChildWithGivenName(payloadConfigurationElement, DCMConstants.UPDATE_DRIVER);
                if (updateDriverElement != null) {
                    DCMPayloadUpdateDriver updateDriver = new DCMPayloadUpdateDriver();
                    updateDriver.setName(updateDriverElement.getAttribute(DCMConstants.NAME_ATTRIBUTE));
                    updateDriver.setPath(updateDriverElement.getAttribute(DCMConstants.PATH_ATTRIBUTE));
                    updateDriver.setVersion(updateDriverElement.getAttribute(DCMConstants.VERSION_ATTRIBUTE));
                    payloadConfiguration.setUpdateDriver(updateDriver);
                }
                pciDevice.setPayloadConfiguration(payloadConfiguration);
            }
        }
        for (Element pnpInfo : pnpInfoCollection) {
            DCMPnPInfo pnpInformation = parsePnPInfo(pnpInfo);
            DCMPnPDevice pnpDevice = new DCMPnPDevice(pnpInformation);
            pnpDevice.setComponentID(componentID);
            pnpDevice.setName(name);
            pnpDevice.setEmbedded(embedded);

            // adding Target map to Update Package
            updatePackage.getTargetMap().put(pnpDevice.getUniqueIdentifier(), pnpDevice);

            retVal.add(pnpDevice.getUniqueIdentifier());
            for (DCMDependency dependency : dependencies) {
                if (updatePackage != null) {
                    updatePackage.addDependencyReference(pnpDevice.getUniqueIdentifier(), dependency);
                } else if (constituent != null) {
                    constituent.addDependencyReference(pnpDevice.getUniqueIdentifier(), dependency);
                }
            }
            for (DCMSoftDependency softDependency : softDependencies) {
                if (updatePackage != null) {
                    updatePackage.addSoftDependencyReference(pnpDevice.getUniqueIdentifier(), softDependency);
                } else if (constituent != null) {
                    constituent.addSoftDependencyReference(pnpDevice.getUniqueIdentifier(), softDependency);
                }
            }
        }
        if ((pciInfoCollection == null || pciInfoCollection.isEmpty()) && (pnpInfoCollection == null || pnpInfoCollection.isEmpty())) {
            DCMNonPCIPnPHardware hardware = new DCMNonPCIPnPHardware(componentID);
            hardware.setName(name);
            hardware.setEmbedded(embedded);

            //add target map to Update Package
            updatePackage.getTargetMap().put(hardware.getUniqueIdentifier(), hardware);
            mManifest.getSoftwareComponents().addUpdatePackageInformation(updatePackage);

            retVal.add(hardware.getUniqueIdentifier());
            baseTarget = hardware;
            for (DCMDependency dependency : dependencies) {
                if (updatePackage != null) {
                    updatePackage.addDependencyReference(hardware.getUniqueIdentifier(), dependency);
                } else if (constituent != null) {
                    constituent.addDependencyReference(hardware.getUniqueIdentifier(), dependency);
                }
            }
            for (DCMSoftDependency softDependency : softDependencies) {
                if (updatePackage != null) {
                    updatePackage.addSoftDependencyReference(hardware.getUniqueIdentifier(), softDependency);
                } else if (constituent != null) {
                    constituent.addSoftDependencyReference(hardware.getUniqueIdentifier(), softDependency);
                }
            }

        }

        //SubComponent
        for (Element subComponentElement : getFirstLevelChildrenWithGivenName(inDevice, DCMConstants.SUB_COMPONENT)) {
            DCMSubComponent subComponent = new DCMSubComponent();
            subComponent.setName(getDisplay(subComponentElement));
            subComponent.setSubComponentID(subComponentElement.getAttribute(DCMConstants.SUB_COMPONENT_ID_ATTRIBUTE));
            subComponent.setVersion(subComponentElement.getAttribute(DCMConstants.VERSION_ATTRIBUTE));
            if (baseTarget != null) {
                baseTarget.addSubComponent(subComponent);
            }
        }
        //Applicability
        for (Element applicability : getFirstLevelChildrenWithGivenName(inDevice, DCMConstants.APPLICABILITY)) {
            DCMDeviceApplicability applicabilityElement = new DCMDeviceApplicability();
            applicabilityElement.setMinimumVersion(applicability.getAttribute(DCMConstants.MIN_VERSION_ATTRIBUTE));
            applicabilityElement.setMaximumVersion(applicability.getAttribute(DCMConstants.MAX_VERSION_ATTRIBUTE));
            if (baseTarget != null) {
                baseTarget.addApplicabilityElement(applicabilityElement);
            }
        }
        //RollbackInformation
        Element rollbackElement = getFirstLevelChildWithGivenName(inDevice, DCMConstants.ROLLBACK_INFORMATION);
        if (rollbackElement != null) {
            DCMRollbackInformation rollbackInformation = new DCMRollbackInformation();
            DCMGUID identifier = new DCMGUID();
            identifier.setID(rollbackElement.getAttribute(DCMConstants.ROLL_BACK_IDENTIFIER_ATTRIBUTE));
            rollbackInformation.setRollbackIdentifier(identifier);
            rollbackInformation.setRollbackVolume(rollbackElement.getAttribute(DCMConstants.ROLL_BACK_VOLUME_ATTRIBUTE));
            identifier.setID(rollbackElement.getAttribute(DCMConstants.FMP_WRAPPER_IDENTIFIER_ATTRIBUTE));
            rollbackInformation.setFMPWrapperIdentifier(identifier);
            rollbackInformation.setFMPWrapperVersion(rollbackElement.getAttribute(DCMConstants.FMP_WRAPPER_VERSION_ATTRIBUTE));
            identifier.setID(rollbackElement.getAttribute(DCMConstants.FMP_IDENTIFIER_ATTRIBUTE));
            rollbackInformation.setFMPIdentifier(identifier);
            rollbackInformation.setRollbackTimeout(Short.parseShort(rollbackElement.getAttribute(DCMConstants.ROLL_BACK_TIME_OUT_ATTRIBUTE)));
            rollbackInformation.setImpactsTPMMeasurements(Boolean.parseBoolean(rollbackElement.getAttribute(DCMConstants.IMPACTS_TP_MEASUREMENTS_ATTRIBUTE)));
            rollbackInformation.setFieldService(rollbackElement.getAttribute(DCMConstants.FIELD_SERVICE_ATTRIBUTE));
            if (baseTarget != null) {
                baseTarget.setRollbackInformaton(rollbackInformation);
            }
        }
        return retVal;
    }

    private Set<String> parseModel(Element inSystem, int inLOBKey) {
        if (null == inSystem) {
            return null;
        }
        if (!inSystem.getNodeName().equals(DCMConstants.MODEL)) {
            return null;
        }
        // systemID
        String systemID = inSystem.getAttribute(DCMConstants.SYSTEM_ID_ATTRIBUTE);
        if (systemID.isEmpty()) {
            return null;
        }
        // systemIDType
        String systemIDType = inSystem.getAttribute(DCMConstants.SYSTEM_ID_TYPE_ATTRIBUTE);
        // systemContext
        String systemContext = inSystem.getAttribute(DCMConstants.SYSTEM_CONTEXT_ATTRIBUTE);
        DCMSystem system = new DCMSystem();
        system.setID(systemID);
        system.setIDType(DCMSystemIDType.getEnumeration(systemIDType));
        system.setLOBKey(inLOBKey);
        system.setName(getDisplay(inSystem));
        mManifest.getSystemCollection().addSystem(system);
        Set<String> retVal = new HashSet<String>();
        retVal.add(system.getUniqueIdentifier());
        for (Element brandElement : getFirstLevelChildrenWithGivenName(inSystem, DCMConstants.BRAND)) {
            Set<String> systemList = parseBrand(brandElement);
            retVal.addAll(systemList);
        }
        return retVal;
    }

    /**
     * Method for getting the first level child with given name
     *
     * @param inParent specifies the parent
     * @param inName specifies the name to be searched
     * @return child element with the given name. could be null
     */
    private Element getFirstLevelChildWithGivenName(Element inParent, String inName) {
        if (inParent == null) {
            return null;
        }
        NodeList childNodeList = inParent.getChildNodes();
        if ((null != childNodeList) && (childNodeList.getLength() > 0)) {
            int numberOfChildren = childNodeList.getLength();
            for (int childIndex = 0; childIndex < numberOfChildren; ++childIndex) {
                Node childNode = childNodeList.item(childIndex);
                short nodeType = childNode.getNodeType();
                if (nodeType == Node.ELEMENT_NODE) {
                    Element childElement = (Element) childNode;
                    if (childElement.getTagName().equals(inName)) {
                        return childElement;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method for getting the first level children with the given name
     *
     * @param inParent specifies the parent element
     * @param inName specifies the name of the child to be filtered
     * @return first level child elements with the specified name
     */
    private Collection<Element> getFirstLevelChildrenWithGivenName(Element inParent, String inName) {
        Collection<Element> retVal = new ArrayList<>();
        if (inParent != null) {
            NodeList childNodeList = inParent.getChildNodes();
            if ((null != childNodeList) && (childNodeList.getLength() > 0)) {
                int numberOfChildren = childNodeList.getLength();

                for (int childIndex = 0; childIndex < numberOfChildren; ++childIndex) {
                    Node childNode = childNodeList.item(childIndex);
                    short nodeType = childNode.getNodeType();
                    if (nodeType == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNode;
                        if (childElement.getTagName().equals(inName)) {
                            retVal.add(childElement);
                        }
                    }
                }
            }
        }
        return retVal;
    }

    /**
     * Method for parsing operating system
     *
     * @param inOS specifies the operating system element to be parsed
     * @return operating system
     */
    private DCMOperatingSystem parseOperatingSystem(Element inOS) {
        if (null == inOS) {
            return null;
        }
        if (!inOS.getNodeName().equals(DCMConstants.OPERATING_SYSTEM)) {
            return null;
        }
        DCMOperatingSystem retVal = new DCMOperatingSystem();
        // osCode
        String osCode = inOS.getAttribute(DCMConstants.OS_CODE_ATTRIBUTE);
        if (!osCode.isEmpty()) {
            retVal.setCode(osCode);
        }
        // osVendor
        String osVendor = inOS.getAttribute(DCMConstants.OS_VENDOR_ATTRIBUTE);
        if (!osVendor.isEmpty()) {
            retVal.setVendor(osVendor);
        }
        // osArch
        String osArch = inOS.getAttribute(DCMConstants.OS_ARCH_ATTRIBUTE);
        if (!osArch.isEmpty()) {
            DCMOSArchitecture architectureEnum = DCMOSArchitecture.getEnumeration(osArch);
            retVal.setArchitecture(architectureEnum);
        }
        // osType
        String osType = inOS.getAttribute(DCMConstants.OS_TYPE_ATTRIBUTE);
        if (!osType.isEmpty()) {
            DCMOSType type = DCMOSType.getEnumeration(osType);
            retVal.setType(type);
        }
        // majorVersion
        String majorVersion = inOS.getAttribute(DCMConstants.MAJOR_VERSION_ATTRIBUTE);
        if (!majorVersion.isEmpty()) {
            retVal.setMajorVersion(majorVersion);
        }
        // minorVersion
        String minorVersion = inOS.getAttribute(DCMConstants.MINOR_VERSION_ATTRIBUTE);
        if (!minorVersion.isEmpty()) {
            retVal.setMinorVersion(minorVersion);
        }
        // spMajorVersion
        String spMajorVersion = inOS.getAttribute(DCMConstants.SP_MAJOR_VERSION_ATTRIBUTE);
        if (!spMajorVersion.isEmpty()) {
            retVal.setServicePackMajorVersion(spMajorVersion);
        }
        // spMinorVersion
        String spMinorVersion = inOS.getAttribute(DCMConstants.SP_MINOR_VERSION_ATTRIBUTE);
        if (!spMinorVersion.isEmpty()) {
            retVal.setServicePackMinorVersion(spMinorVersion);
        }
        // preinstallationEnvironment
        String preEnvironment = inOS.getAttribute(DCMConstants.PRE_INSTALLATION_ENVIRONMENT_ATTRIBUTE);
        if (!preEnvironment.isEmpty()) {
            retVal.setForPreInstallEnvironment(Boolean.parseBoolean(preEnvironment));
        }
        // suiteMask
        String suiteMask = inOS.getAttribute(DCMConstants.SUITE_MASK_ATTRIBUTE);
        if (!suiteMask.isEmpty()) {
            retVal.setFlavorMask(Integer.parseInt(suiteMask));
        }
        DCMI18NString display = getDisplay(inOS);
        retVal.setName(display);
        NodeList supportedLanguagesNodeList = inOS.getElementsByTagName(DCMConstants.SUPPORTED_LANGUAGES);
        if ((supportedLanguagesNodeList != null) && (supportedLanguagesNodeList.getLength() > 0)) {
            //TODO add languages here
        }
        return retVal;
    }

    /**
     * Method for parsing the PCI Information
     *
     * @param inPCI specifies the PCI information element to be parsed
     * @return PCI Information
     */
    DCMPCIInfo parsePCIInfo(Element inPCI) {
        if (inPCI == null || !inPCI.getNodeName().equals(DCMConstants.PCI_INFO)) {
            return null;
        }
        // deviceID
        DCMHexBinary4 deviceID = new DCMHexBinary4();
        deviceID.setValue(inPCI.getAttribute(DCMConstants.DEVICE_ID_ATTRIBUTE));
        // vendorID
        DCMHexBinary4 vendorID = new DCMHexBinary4();
        vendorID.setValue(inPCI.getAttribute(DCMConstants.VENDOR_ID_ATTRIBUTE));
        // subDeviceID
        DCMHexBinary4 subDeviceID = new DCMHexBinary4();
        subDeviceID.setValue(inPCI.getAttribute(DCMConstants.SUB_DEVICE_ID_ATTRIBUTE));
        // subVendorID
        DCMHexBinary4 subVendorID = new DCMHexBinary4();
        subVendorID.setValue(inPCI.getAttribute(DCMConstants.SUB_VENDOR_ID_ATTRIBUTE));
        DCMPCIInfo pciInformation = new DCMPCIInfo(vendorID, deviceID, subVendorID, subDeviceID);
        return pciInformation;
    }

    /**
     * Method for parsing the PnP Information
     *
     * @param inPnP specifies the PnP information element to be parsed
     * @return PnP Information
     */
    DCMPnPInfo parsePnPInfo(Element inPnP) {
        if (inPnP == null || !inPnP.getNodeName().equals(DCMConstants.PNP_INFO)) {
            return null;
        }
        Element pnpID = getFirstLevelChildWithGivenName(inPnP, DCMConstants.PNPID);
        Element acpiID = getFirstLevelChildWithGivenName(inPnP, DCMConstants.ACPIID);
        Element pnpProductID = getFirstLevelChildWithGivenName(inPnP, DCMConstants.PNP_PRODUCT_ID);
        DCMPnPProductID pnpProdID = new DCMPnPProductID();
        pnpProdID.setID(pnpProductID.getTextContent());
        DCMPnPInfo pnpInformation = null;
        if (pnpID != null) {
            DCMPnPID pID = new DCMPnPID();
            pID.setID(pnpID.getTextContent());
            pnpInformation = new DCMPnPInfo(pID, pnpProdID);
        } else if (acpiID != null) {
            DCMACPIID aID = new DCMACPIID();
            aID.setID(acpiID.getTextContent());
            pnpInformation = new DCMPnPInfo(aID, pnpProdID);
        }
        return pnpInformation;
    }

    private File validateCatalog(File inputFile) {

        if (inputFile.toString().toLowerCase().endsWith(".gz")) {
            try {
                File tempFile = File.createTempFile("catalog", ".xml");
                FileOutputStream out = new FileOutputStream(tempFile);

                GZIPInputStream gzin = new GZIPInputStream(new FileInputStream(inputFile));
                int len;
                byte[] buffer = new byte[1024];
                while ((len = gzin.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                gzin.close();
                out.close();
                return tempFile;

            } catch (IOException ex) {
                Logger.getLogger(DCMCatalog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return inputFile;
    }

    /**
     * Method for searching repositories recursively ( All .EXE and .BIN Files)
     *
     * @param inBaseLocation specifies path for root directory
     * @return list of repository paths
     */
    private List<File> parseRepositoryUtility(String inBaseLocation) {
        if (null == inBaseLocation) {
            return null;
        }
        File file = new File(inBaseLocation);
        List<File> listFile = new ArrayList<File>();
        // File Array of all .EXE and .BIN files in inBaselocation
        File[] fileArray = file.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return (filename.endsWith(".EXE") || filename.endsWith(".BIN"));
            }
        });
        // File Array of all directories in inBaselocation 
        File[] directories = file.listFiles(new FilenameFilter() {
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        if (file.isDirectory()) {
            if ((fileArray.length == 0) && (directories.length == 0)) {
                return null;
            } else if ((fileArray.length > 0) && (directories.length == 0)) {
                for (int index = 0; index < fileArray.length; index++) {
                    listFile.add(fileArray[index]);
                }
            } else if ((fileArray.length == 0) && (directories.length > 0)) {
                for (File f : directories) {
                    List<File> tempListFile = parseRepositoryUtility(f.getPath());
                    if (null != tempListFile) {
                        listFile.addAll(tempListFile);
                    }
                }
            } else if ((fileArray.length > 0) && (directories.length > 0)) {
                for (int index = 0; index < fileArray.length; index++) {
                    listFile.add(fileArray[index]);
                }
                for (File f : directories) {
                    List<File> tempListFile = parseRepositoryUtility(f.getPath());
                    if (null != tempListFile) {
                        listFile.addAll(tempListFile);
                    }
                }
            } else {
                return null;
            }
        }
        return listFile;
    }

    /**
     * Method for parsing the repository
     *
     * @param listExeBinFile specifies the list of locations of repository
     * @param inSystemCollection specifies collection of DCMSystems
     * @param inBaseLocation specifies base location
     * @return manifest of the parsed repository
     */
    public DCMManifest parseRepository(String inBaseLocation, List<File> listExeBinFile, List<DCMSystem> inSystemCollection) {
        Logger.getLogger(DCMCatalog.class.getName()).log(Level.INFO, "Parsing Repository");

        if (null == listExeBinFile) {
            return null;
        }

        Logger.getLogger(DCMCatalog.class.getName()).log(Level.INFO, "Extraction Started for all files.");
        mManifest = new DCMManifest();

        //HashMap<File, DCMUpdatePackageInformation> mapPackage = new HashMap<File, DCMUpdatePackageInformation>();
        for (File file : listExeBinFile) {
            Logger.getLogger(DCMCatalog.class.getName()).log(Level.INFO, "Extracting " + file.getName());
            IDCMPackageExtractor pe = new DCMPackageExtractorLWXP(file);
            if (file.getName().toUpperCase().endsWith(".BIN")) {
                pe = new DCMPackageExtractorLLXP(file);
            }
            String extractedPackage = pe.extractPackageXML();
            if (extractedPackage != null) {
                DCMUpdatePackageInformation softwareComponent = parseSoftwareComponent(extractedPackage, file.getAbsolutePath().substring(inBaseLocation.length() + 1).replace(BACKWARD_SLASH, FORWARD_SLASH));
                if (softwareComponent != null) {
                    softwareComponent.setInstallInstruction(null);
                    softwareComponent.setRevisionHistory(null);
                    softwareComponent.setSize(file.length());
                    mManifest.getSoftwareComponents().addUpdatePackageInformation(softwareComponent);
                }
            } else {
                Logger.getLogger(DCMCatalog.class.getName()).log(Level.WARNING, "Extraction Failed for " + file.getName());
            }
        }
        Collection<DCMSystem> systemCollection = mManifest.getSystemCollection().getSystems();
        if (inSystemCollection != null && !inSystemCollection.isEmpty()) {
            systemCollection = inSystemCollection;
        }

        Collection<String> sysIdCollection = new HashSet<String>();

        for (DCMSystem inSys : systemCollection) {
            sysIdCollection.add(inSys.getUniqueIdentifier());
        }

        for (DCMSystem inSys : systemCollection) {
            for (DCMUpdatePackageInformation softwareComponent : mManifest.getSoftwareComponents().getUpdatePackages()) {
                if (softwareComponent.getSupportedSystems().isEmpty()) {
                    softwareComponent.getSupportedSystems().addAll(sysIdCollection);
                }
                if (softwareComponent.getSupportedSystems().contains(inSys.getUniqueIdentifier())) {
                    String bundleID = inSys.getID() + softwareComponent.getPackageType();
                    DCMBundle inBundle = mManifest.getBundle(bundleID);
                    if (inBundle == null) {
                        if (softwareComponent.getPackageType().equals(DCMPackageType.LWXP)) {
                            inBundle = new DCMBundle(DCMBundleType.BTW32);
                        } else if (softwareComponent.getPackageType().equals(DCMPackageType.LLXP)) {
                            inBundle = new DCMBundle(DCMBundleType.BTLX);
                        } else if (softwareComponent.getPackageType().equals(DCMPackageType.LW64)) {
                            inBundle = new DCMBundle(DCMBundleType.BTW64);
                        }
                        if (inBundle == null) {
                            continue;
                        }
                        inBundle.setBundleID(bundleID);
                        String id = inSys.getUniqueIdentifier();
                        String retVal = new String();
                        retVal = retVal.concat(Integer.toString(inSys.getLOBKey()));
                        retVal = retVal.concat(DCMSystemIDType.toString(inSys.getIDType()));
                        retVal = retVal.concat(inSys.getID());
                        inBundle.addTargetSystemIdentifier(retVal);
                        mManifest.getBundleCollection().addBundle(inBundle);
                    }
                    String filename = new File(softwareComponent.getPath()).getName();
                    inBundle.addUpdatePackageIdentifier(filename);
                }
            }
        }
        Logger.getLogger(DCMCatalog.class.getName()).log(Level.INFO, "Manifest Creation Completed");
        return mManifest;
    }

    /**
     * Delete file recursively
     *
     * @param file
     */
    private void deleteFile(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                //call recursively
                deleteFile(f);
            }
        }
        //call delete to delete files and empty directory
        if (!file.delete()) {
            try {
                Files.deleteIfExists(file.toPath());
            } catch (IOException ex) {
                Logger.getLogger(DCMCatalog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Getting the Package.xml information
     *
     * @param packageString specifies content to package xml
     * @param dupFilePath specifies path to DUP file
     * @return DCMUpdatePackageInformation object
     */
    public DCMUpdatePackageInformation parseSoftwareComponent(String packageXML, String dupFilePath) {

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            if (null == docBuilderFactory) {
                return null;
            }
            DocumentBuilder docBuilder;
            docBuilder = docBuilderFactory.newDocumentBuilder();
            if (null == docBuilder) {
                return null;
            }

            //Document givenDocument = docBuilder.parse(new InputSource(new StringReader(packageXML)));
            String xml = packageXML.substring(packageXML.indexOf("<SoftwareComponent"));
            Document givenDocument = docBuilder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes())));
            if (null == givenDocument) {
                return null;
            }
            Element givenDocumentRoot = givenDocument.getDocumentElement();
            if (null == givenDocumentRoot) {
                return null;
            }
            if (mManifest == null) {
                mManifest = new DCMManifest();
            }
            return parseSoftwareComponent(givenDocumentRoot, dupFilePath);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DCMCatalog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Getting the catalog information
     *
     * @param catalogFilePath specifies path to Catalog.xml
     * @return DCMManifest object
     */
    public DCMManifest getCatalogInformation(String catalogFilePath) {
        File f = new File(catalogFilePath);
        mManifest = parseCatalog(f);
        return mManifest;
    }

    private DCMManifest mManifest;
    private static final char FORWARD_SLASH = '/';
    private static final char BACKWARD_SLASH = '\\';
}
