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

import com.dell.cm.updateinformationmodel.DCMBaseTarget;
import com.dell.cm.updateinformationmodel.DCMBundle;
import com.dell.cm.updateinformationmodel.DCMBundleCollection;
import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMCategory;
import com.dell.cm.updateinformationmodel.DCMComponentKind;
import com.dell.cm.updateinformationmodel.DCMComponentType;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMConstituent;
import com.dell.cm.updateinformationmodel.DCMCriticality;
import com.dell.cm.updateinformationmodel.DCMDependency;
import com.dell.cm.updateinformationmodel.DCMDeviceApplicability;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMFMPWrapperInformation;
import com.dell.cm.updateinformationmodel.DCMGUID;
import com.dell.cm.updateinformationmodel.DCMI18NString;
import com.dell.cm.updateinformationmodel.DCMImportantInformation;
import com.dell.cm.updateinformationmodel.DCMInstallInstructions;
import com.dell.cm.updateinformationmodel.DCMInventoryCollectorInformation;
import com.dell.cm.updateinformationmodel.DCMInventoryCollectorInformationCollection;
import com.dell.cm.updateinformationmodel.DCMLineOfBusiness;
import com.dell.cm.updateinformationmodel.DCMLineOfBusinessCollection;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMNonPCIPnPHardware;
import com.dell.cm.updateinformationmodel.DCMOSArchitecture;
import com.dell.cm.updateinformationmodel.DCMOSType;
import com.dell.cm.updateinformationmodel.DCMOperatingSystem;
import com.dell.cm.updateinformationmodel.DCMPCIDevice;
import com.dell.cm.updateinformationmodel.DCMPCIInfo;
import com.dell.cm.updateinformationmodel.DCMPackageType;
import com.dell.cm.updateinformationmodel.DCMPayloadConfiguration;
import com.dell.cm.updateinformationmodel.DCMPnPDevice;
import com.dell.cm.updateinformationmodel.DCMRollbackInformation;
import com.dell.cm.updateinformationmodel.DCMSoftDependency;
import com.dell.cm.updateinformationmodel.DCMSubComponent;
import com.dell.cm.updateinformationmodel.DCMSystem;
import com.dell.cm.updateinformationmodel.DCMSystemCollection;
import com.dell.cm.updateinformationmodel.DCMTargetEntity;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformationCollection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Rathish_Das
 */
public class DCMCatalogWriter {

    //Boolean to indicate whether the CDATA is to be written or not
    private int addDisplay(Element inParentElement, DCMI18NString inDisplayValue) {
        if (null == inParentElement || null == inDisplayValue) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        Element display = mDocument.createElement(DCMConstants.DISPLAY);
        if (null == display) {
            return DCMErrorCodes.FAILURE;
        }

        String displayValue = "";

        if (inDisplayValue.getLocales().contains(Locale.ENGLISH)) {
            displayValue = inDisplayValue.getLocaleString(Locale.ENGLISH);
            display.setAttribute(DCMConstants.LANG_ATTRIBUTE, DCMConstants.EN_LANGUAGE);
            //ToDo for other Locale
        }

        if (mToIncludeCDATA) {
            display.appendChild(mDocument.createCDATASection(displayValue));
        } else {
            display.appendChild(mDocument.createTextNode(displayValue));
        }

        inParentElement.appendChild(display);
        return DCMErrorCodes.SUCCESS;
    }

    private int addName(Element inParentElement, DCMI18NString inDisplayValue) {
        int retCode = DCMErrorCodes.FAILURE;
        if (null == inParentElement || null == inDisplayValue) {
            return retCode;
        }
        Element name = mDocument.createElement(DCMConstants.NAME);
        if (null != name) {
            retCode = addDisplay(name, inDisplayValue);
            inParentElement.appendChild(name);
        }
        return retCode;
    }

    private int addDescription(Element inParentElement, DCMI18NString inDisplayValue) {
        int retCode = DCMErrorCodes.FAILURE;
        if (null == inParentElement || null == inDisplayValue) {
            return retCode;
        }
        Element description = mDocument.createElement(DCMConstants.DESCRIPTION);

        if (null != description) {
            retCode = addDisplay(description, inDisplayValue);
            inParentElement.appendChild(description);
        }
        return retCode;
    }

    private int addCategory(Element inParentElement, DCMCategory inCategory) {
        int retCode = DCMErrorCodes.FAILURE;
        if (null == inParentElement || null == inCategory) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        DCMI18NString displayValue = inCategory.getName();

        Element category = mDocument.createElement(DCMConstants.CATEGORY);
        if (null == category) {
            return retCode;
        }
        String categoryCode = inCategory.getCode();
        if (null != categoryCode) {
            category.setAttribute(DCMConstants.VALUE_ATTRIBUTE, categoryCode);
        }

        retCode = addDisplay(category, displayValue);
        inParentElement.appendChild(category);

        return retCode;
    }

    private int addLUCategory(Element inParentElement, DCMCategory inLUCategory) {
        int retCode = DCMErrorCodes.FAILURE;
        if (null == inParentElement || null == inLUCategory) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        DCMI18NString displayValue = inLUCategory.getName();

        Element luCategory = mDocument.createElement(DCMConstants.LU_CATEGORY);
        if (null == luCategory) {
            return retCode;
        }
        String categoryCode = inLUCategory.getCode();
        luCategory.setAttribute(DCMConstants.VALUE_ATTRIBUTE, categoryCode);

        retCode = addDisplay(luCategory, displayValue);
        inParentElement.appendChild(luCategory);

        return retCode;
    }

    private int addComponentType(Element inParentElement, DCMComponentKind inComponentKind) {
        int retCode = DCMErrorCodes.FAILURE;
        if (null == inParentElement || null == inComponentKind) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        DCMI18NString displayValue = inComponentKind.getName();
        Element componentType = mDocument.createElement(DCMConstants.COMPONENT_TYPE);
        if (null == componentType) {
            return retCode;
        }
        DCMComponentType componentTypeValue = inComponentKind.getType();
        String compType = DCMComponentType.toString(componentTypeValue);
        componentType.setAttribute(DCMConstants.VALUE_ATTRIBUTE, compType);

        retCode = addDisplay(componentType, displayValue);
        inParentElement.appendChild(componentType);

        return retCode;
    }

    private int addCriticality(Element inParentElement, DCMCriticality inCriticality, DCMI18NString inCriticalityDescription) {
        int retCode = DCMErrorCodes.FAILURE;
        if (null == inParentElement || null == inCriticality) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        String criticalityValue = DCMCriticality.toString(inCriticality);
        Element criticality = mDocument.createElement(DCMConstants.CRITICALITY);
        if (null == criticality) {
            return retCode;
        }
        if (null != criticalityValue) {
            criticality.setAttribute(DCMConstants.VALUE_ATTRIBUTE, criticalityValue);
        }
        retCode = addDisplay(criticality, inCriticalityDescription);
        inParentElement.appendChild(criticality);

        return retCode;
    }

    private int addInstallInstruction(Element inParentElement, DCMInstallInstructions inInstallInstructions) {
        int retCode = DCMErrorCodes.FAILURE;
        if (null == inParentElement || null == inInstallInstructions) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        Element installInstruction = mDocument.createElement(DCMConstants.INSTALL_INSTRUCTIONS);
        if (null == installInstruction) {
            return retCode;
        }

        String fileName = inInstallInstructions.getFileName();
        if (null != fileName) {
            installInstruction.setAttribute(DCMConstants.FILE_NAME_ATTRIBUTE, fileName);
        }

        String typeCode = inInstallInstructions.getTypeCode();
        if (null != typeCode) {
            installInstruction.setAttribute(DCMConstants.TYPE_CODE_ATTRIBUTE, typeCode);
        }

        DCMI18NString displayValue = inInstallInstructions.getName();
        retCode = addDisplay(installInstruction, displayValue);
        inParentElement.appendChild(installInstruction);

        return retCode;
    }

    private int addRevisionHistory(Element inParentElement, DCMI18NString inRevisionHistory) {
        int retCode = DCMErrorCodes.FAILURE;
        if (null == inParentElement || null == inRevisionHistory) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        Element revisionHistory = mDocument.createElement(DCMConstants.REVISION_HISTORY);
        if (null == revisionHistory) {
            return retCode;
        }
        retCode = addDisplay(revisionHistory, inRevisionHistory);
        inParentElement.appendChild(revisionHistory);
        return retCode;
    }

    private int addImportantInformation(Element inParentElement, DCMImportantInformation inImportantInformation) {
        int retCode = DCMErrorCodes.FAILURE;
        if (null == inParentElement || null == inImportantInformation) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        Element impInfo = mDocument.createElement(DCMConstants.IMPORTANT_INFO);
        if (null == impInfo) {
            return retCode;
        }
        URL impInfoURL = inImportantInformation.getURL();
        if (null != impInfoURL) {
            impInfo.setAttribute(DCMConstants.URL_ATTRIBUTE, impInfoURL.toString());
        }

        DCMI18NString impInfoDisplay = inImportantInformation.getInformation();

        retCode = addDisplay(impInfo, impInfoDisplay);
        inParentElement.appendChild(impInfo);
        return retCode;
    }

    private int addMSIID(Element inParentElement, DCMGUID inMSIID) {
        //To do

        return DCMErrorCodes.SUCCESS;

    }

    private int addContents(Element inParentElement, DCMManifest inManifest, HashSet<String> inUpdatePackageIdentifiers) {
        if (null == inParentElement || null == inManifest || inUpdatePackageIdentifiers.isEmpty()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        Element contents = mDocument.createElement(DCMConstants.CONTENTS);
        if (null == contents) {
            return DCMErrorCodes.FAILURE;
        }
        for (String upPkg : inUpdatePackageIdentifiers) {
            String packagePath = inManifest.getSoftwareComponents().getUpdatePackageInformation(upPkg).getPath();
            String[] packagePathParts = packagePath.split("/");
            if (packagePathParts.length > 0) {
                String packageName = packagePathParts[packagePathParts.length - 1];
                addPackage(contents, packageName);
            }
        }
        inParentElement.appendChild(contents);
        return DCMErrorCodes.SUCCESS;
    }

    private int addPackage(Element inParentElement, String inPackageName) {
        if (null == inParentElement || null == inPackageName) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        Element upPkg = mDocument.createElement(DCMConstants.PACKAGE);
        if (null == upPkg) {
            return DCMErrorCodes.FAILURE;
        }
        upPkg.setAttribute(DCMConstants.PATH_ATTRIBUTE, inPackageName);
        inParentElement.appendChild(upPkg);
        return DCMErrorCodes.SUCCESS;
    }

    private HashMap<Integer, HashSet<String>> groupSystemsAsPerLineOfBusiness(DCMManifest inManifest, HashSet<String> inSupportedSystemList) {
        if (null == inManifest || inSupportedSystemList.isEmpty()) {
            return null;
        }

        HashMap<Integer, HashSet<String>> lobSet = new HashMap<>();
        DCMSystemCollection systemCollection = inManifest.getSystemCollection();

        for (String systemIdentifier : inSupportedSystemList) {
            DCMSystem system = systemCollection.getSystem(systemIdentifier);
            if (system == null) {
                continue;
            }
            int lobKey = system.getLOBKey();

            HashSet<String> systemSetFromMap;
            if (lobSet.containsKey(lobKey)) {
                systemSetFromMap = lobSet.get(lobKey);
            } else {
                systemSetFromMap = new HashSet<>();
            }
            systemSetFromMap.add(systemIdentifier);
            lobSet.put(lobKey, systemSetFromMap);
        }
        return lobSet;
    }

    private int addBrand(Element inParentElement, DCMLineOfBusiness inLOB, HashSet<String> inSystemSet, DCMSystemCollection inSystemCollection) {
        int retCode = DCMErrorCodes.FAILURE;
        if (null == inParentElement || null == inLOB || inSystemSet.isEmpty() || null == inSystemCollection) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        HashSet<Element> systemSet = new HashSet<>();

        Element brand = mDocument.createElement(DCMConstants.BRAND);
        if (null == brand) {
            return retCode;
        }

        DCMI18NString lobName = inLOB.getName();
        addDisplay(brand, lobName);

        String lobKey = Integer.toString(inLOB.getKey());
        if (null != lobKey) {
            brand.setAttribute(DCMConstants.KEY_ATTRIBUTE, lobKey);
        }

        String lobPrefix = inLOB.getPrefix();
        if (null != lobPrefix) {
            brand.setAttribute(DCMConstants.PREFIX_ATTRIBUTE, lobPrefix);
        }

        for (String systemID : inSystemSet) {
            // Changed to meet MSM requirements
            DCMSystem system = inSystemCollection.getSystem(systemID);
            addModel(brand, system);
        }
        inParentElement.appendChild(brand);
        return DCMErrorCodes.SUCCESS;
    }

    private int addModel(Element inParentElement, DCMSystem inSystem) {
        int retCode = DCMErrorCodes.FAILURE;
        if (null == inSystem) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        Element model = mDocument.createElement(DCMConstants.MODEL);
        if (null == model) {
            return retCode;
        }
        String systemID = inSystem.getID();
        if (null != systemID) {
            model.setAttribute(DCMConstants.SYSTEM_ID_ATTRIBUTE, systemID);
        }
        DCMI18NString displayValue = inSystem.getName();

        retCode = addDisplay(model, displayValue);
        inParentElement.appendChild(model);

        return retCode;

    }

    private int addSystemCollectionWithGivenName(Element inParentElement, String inSystemCollectionName, DCMManifest inManifest, HashSet<String> inSupportedSystemList) {
        int retCode = DCMErrorCodes.FAILURE;
        if (null == inParentElement || null == inManifest || inSupportedSystemList.isEmpty()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        Element targetOrSupportedSystems = mDocument.createElement(inSystemCollectionName);
        if (null == targetOrSupportedSystems) {
            return retCode;
        } else {
            retCode = DCMErrorCodes.SUCCESS;
        }
        //targetOrSupportedSystems.setAttribute(DCMConstants.DISPLAY, DCMConstants.ONE);

        DCMLineOfBusinessCollection lobCollection = inManifest.getLineOfBusinessCollection();
        DCMSystemCollection systemCollection = inManifest.getSystemCollection();
        HashMap<Integer, HashSet<String>> lobSet = groupSystemsAsPerLineOfBusiness(inManifest, inSupportedSystemList);

        for (int lobKey : lobSet.keySet()) {
            DCMLineOfBusiness lob = lobCollection.getLineOfBusiness(lobKey);
            HashSet<String> systemSet = lobSet.get(lobKey);
            retCode = addBrand(targetOrSupportedSystems, lob, systemSet, systemCollection);

        }
        inParentElement.appendChild(targetOrSupportedSystems);
        return retCode;
    }

    private int addOperatingSystem(Element inParentElement, DCMOperatingSystem inOS) {
        if (null == inParentElement || null == inOS) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        DCMI18NString display = inOS.getName();
        Element os = mDocument.createElement(DCMConstants.OPERATING_SYSTEM);
        if (null == os) {
            return DCMErrorCodes.FAILURE;
        }
        addDisplay(os, display);
        String osCode = inOS.getCode();
        if (!osCode.isEmpty()) {
            os.setAttribute(DCMConstants.OS_CODE_ATTRIBUTE, osCode);
        }
        // osVendor
        String osVendor = inOS.getVendor();
        if (!osVendor.isEmpty()) {
            os.setAttribute(DCMConstants.OS_VENDOR_ATTRIBUTE, osVendor);
        }
        // osArch
        DCMOSArchitecture osArch = inOS.getArchitecture();
        if (null != osArch) {
            String arch = osArch.toString();
            switch (osArch) {
                case X8664:
                    arch = DCMConstants.X64_ENUM;
                    break;
                case X8632:
                    arch = DCMConstants.X86_ENUM;
                    break;
                case IA64:
                    arch = DCMConstants.IA64_ENUM;
                    break;
                case IA32:
                    arch = DCMConstants.IA32_ENUM;
                    break;
                default:
                    break;
            }
            
            os.setAttribute(DCMConstants.OS_ARCH_ATTRIBUTE, arch);
        }
        // osType
        DCMOSType osType = inOS.getType();
        if (null != osType) {

            os.setAttribute(DCMConstants.OS_TYPE_ATTRIBUTE, osType.toString());
        }
        // majorVersion
        String majorVersion = inOS.getMajorVersion();
        if (null != majorVersion) {
            os.setAttribute(DCMConstants.MAJOR_VERSION_ATTRIBUTE, majorVersion);
        }
        // minorVersion
        String minorVersion = inOS.getMinorVersion();
        if (null != minorVersion) {
            os.setAttribute(DCMConstants.MINOR_VERSION_ATTRIBUTE, minorVersion);
        }
        // spMajorVersion
        String spMajorVersion = inOS.getServicePackMajorVersion();
        if (null != spMajorVersion) {
            os.setAttribute(DCMConstants.SP_MAJOR_VERSION_ATTRIBUTE, spMajorVersion);
        }
        // spMinorVersion
        String spMinorVersion = inOS.getServicePackMinorVersion();
        if (null != spMinorVersion) {
            os.setAttribute(DCMConstants.SP_MINOR_VERSION_ATTRIBUTE, spMinorVersion);
        }
        // preinstallationEnvironment
        Boolean preEnvironment = inOS.isForPreInstallEnvironment();
        os.setAttribute(DCMConstants.PRE_INSTALLATION_ENVIRONMENT_ATTRIBUTE, preEnvironment.toString());
        // suiteMask
        String suiteMask = Integer.toString(inOS.getFlavorMask());
        if (null != suiteMask) {
            os.setAttribute(DCMConstants.SUITE_MASK_ATTRIBUTE, suiteMask);
        }

        //To do Supported Language
        inParentElement.appendChild(os);
        return DCMErrorCodes.SUCCESS;
    }

    private int addSupportedLanguages(Element inParentElement) {
        //To do
        // 1) Need to create DCMLanguage and DCMLanguageCollection Class and they need to be populated at DCMCatalog class
        // if swBundle without cdata display, else with cdata display

        return DCMErrorCodes.SUCCESS;
    }

    /**
     * adds the supported devices to the update package
     *
     * @param inParentElement
     * @param inManifest
     * @param inPackage
     * @return
     */
    private int addSupportedDevices(Element inParentElement, DCMManifest inManifest, DCMUpdatePackageInformation inPackage) {

        if (null == inParentElement || null == inManifest || null == inPackage) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        Element supportedDeviceSet = mDocument.createElement(DCMConstants.SUPPORTED_DEVICES);

        HashMap<Long, HashSet<String>> deviceTypeMap = groupDeviceAsPerDeviceType(inManifest, inPackage);
        for (long componentID : deviceTypeMap.keySet()) {
            HashSet<String> deviceSet = deviceTypeMap.get(componentID);
            addDevice(supportedDeviceSet, componentID, inManifest, deviceSet, inPackage);
        }
        inParentElement.appendChild(supportedDeviceSet);
        return DCMErrorCodes.SUCCESS;
    }

    private int addDevice(Element inParentElement, long componentID, DCMManifest inManifest, HashSet<String> inDeviceSet, DCMUpdatePackageInformation inPackage) {
        if (null == inParentElement || null == inManifest || inDeviceSet.isEmpty() || null == inPackage) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        Element device = mDocument.createElement(DCMConstants.DEVICE);
        if (null == device) {
            return DCMErrorCodes.FAILURE;
        }
        String embedded = "";
        device.setAttribute(DCMConstants.COMPONENT_ID_ATTRIBUTE, Long.toString(componentID));

        String inDevice = inDeviceSet.iterator().next();
        DCMBaseTarget firstDevice = inPackage.getTarget(inDevice);

        if (firstDevice.isEmbedded()) {
            embedded = DCMConstants.ONE;
        } else {
            embedded = DCMConstants.ZERO;
        }
        device.setAttribute(DCMConstants.EMBEDDED, embedded);
        DCMI18NString displayName = firstDevice.getName();
        for (String targetID : inDeviceSet) {

            DCMBaseTarget target = inPackage.getTarget(targetID);

            DCMTargetEntity entityType = target.getType();
            if (DCMTargetEntity.PCI == entityType) {
                DCMPCIDevice pciDevice = (DCMPCIDevice) target;
                addPCIInfo(device, pciDevice.getPCIInfo());
            }
            if (DCMTargetEntity.PNP == entityType) {
                DCMPnPDevice pnpDevice = (DCMPnPDevice) target;
                addPnPInfo(device, pnpDevice);
            }
            /*u
             To do -
             1) SubComponent
             2) Dependency
             3) SoftDependency
             4) Applicability
             5) RollbackInformation
             */
        }
        if (null != displayName) {
            addDisplay(device, displayName);
        }

        inParentElement.appendChild(device);
        return DCMErrorCodes.SUCCESS;
    }

    private HashMap<Long, HashSet<String>> groupDeviceAsPerDeviceType(DCMManifest inManifest, DCMUpdatePackageInformation inPackage) {
        if (null == inManifest || null == inPackage) {
            return null;
        }
        HashSet<String> inSupportedTargetList = inPackage.getSupportedTargets();
        HashMap<Long, HashSet<String>> deviceTypeMap = new HashMap<>();

        for (String targetID : inSupportedTargetList) {
            DCMBaseTarget target = inPackage.getTarget(targetID);
            long componentID = 0;
            DCMTargetEntity entityType = target.getType();

            if (DCMTargetEntity.PNP == entityType) {
                DCMPnPDevice pnpDevice = (DCMPnPDevice) target;
                componentID = pnpDevice.getComponentID();
            }

            if (DCMTargetEntity.PCI == entityType) {
                DCMPCIDevice pciDevice = (DCMPCIDevice) target;
                componentID = pciDevice.getComponentID();
            }

            if (DCMTargetEntity.HARDWARE == entityType) {
                DCMNonPCIPnPHardware hwDevice = (DCMNonPCIPnPHardware) target;
                componentID = hwDevice.getComponentID();
            }

            HashSet<String> deviceSet;
            if (deviceTypeMap.containsKey(componentID)) {
                deviceSet = deviceTypeMap.get(componentID);
            } else {
                deviceSet = new HashSet<>();
            }

            deviceSet.add(targetID);
            deviceTypeMap.put(componentID, deviceSet);

        }
        return deviceTypeMap;
    }

    private int addConstituents(Element inParentElement, Collection<DCMConstituent> inConstituentCollection) {
        //To do

        return DCMErrorCodes.SUCCESS;
    }

    private int addConstituent(Element inParentElement, DCMConstituent inConstituent) {
        //To do

        return DCMErrorCodes.SUCCESS;
    }

    private int addFMPWrappers(Element inParentElement, Collection<DCMFMPWrapperInformation> inFMPWrapperCollection) {
        //To do

        return DCMErrorCodes.SUCCESS;
    }

    private int FMPWrapperInformation(Element inParentElement, DCMFMPWrapperInformation inFMPWrapperInformation) {
        //To do

        return DCMErrorCodes.SUCCESS;
    }

    private int addPCIInfo(Element inParentElement, DCMPCIInfo inPCIInfo) {
        if (null == inParentElement || null == inPCIInfo) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        Element pci = mDocument.createElement(DCMConstants.PCI_INFO);
        if (null == pci) {
            return DCMErrorCodes.FAILURE;
        }
        pci.setAttribute(DCMConstants.DEVICE_ID_ATTRIBUTE, inPCIInfo.getDeviceID());
        pci.setAttribute(DCMConstants.VENDOR_ID_ATTRIBUTE, inPCIInfo.getVendorID());
        pci.setAttribute(DCMConstants.SUB_DEVICE_ID_ATTRIBUTE, inPCIInfo.getSubDeviceID());
        pci.setAttribute(DCMConstants.SUB_VENDOR_ID_ATTRIBUTE, inPCIInfo.getSubVendorID());

        inParentElement.appendChild(pci);
        return DCMErrorCodes.SUCCESS;

    }

    private int addPnPInfo(Element inParentElement, DCMPnPDevice inPnPDevice) {
        //To Do
        return DCMErrorCodes.SUCCESS;
    }

    private int addPayloadConfiguration(Element inParentElement, DCMPayloadConfiguration inPayloadConfiguration) {
        //To Do
        return DCMErrorCodes.SUCCESS;
    }

    private int addDependency(Element inParentElement, DCMDependency inDependency) {
        //To do
        //PCIdevice class has to add dependency in DCMCatalog class
        Element element = mDocument.createElement(null);
        return DCMErrorCodes.SUCCESS;
    }

    private int addSoftDependency(Element inParentElement, DCMSoftDependency inSoftDependency) {
        //To do

        Element element = mDocument.createElement(null);
        return DCMErrorCodes.SUCCESS;
    }

    private int addApplicability(Element inParentElement, DCMDeviceApplicability inApplicabilityElement) {
        //To do

        return DCMErrorCodes.SUCCESS;
    }

    private int addRollbackInformation(Element inParentElement, DCMRollbackInformation inRollbackInformation) {
        //To do

        return DCMErrorCodes.SUCCESS;
    }

    private int addSubComponent(Element inParentElement, DCMSubComponent inSubComponent) {
        //To do

        return DCMErrorCodes.SUCCESS;
    }

    private int addInventoryComponent(Element inParentElement, DCMInventoryCollectorInformation inInvCompInfo) {
        if (null == inParentElement || null == inInvCompInfo) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        Element invElement = mDocument.createElement(DCMConstants.INVENTORY_COMPONENT);
        if (null == invElement) {
            return DCMErrorCodes.FAILURE;
        }
        invElement.setAttribute(DCMConstants.SCHEMA_VERSION_ATTRIBUTE, inInvCompInfo.getSchemaVersion());
        invElement.setAttribute(DCMConstants.RELEASE_ID_ATTRIBUTE, inInvCompInfo.getReleaseID());
        invElement.setAttribute(DCMConstants.RELEASE_DATE_ATTRIBUTE, dateToStringFormatForReleaseDate(inInvCompInfo.getReleaseDate()));
        invElement.setAttribute(DCMConstants.VENDOR_VERSION_ATTRIBUTE, inInvCompInfo.getVendorVersion());
        invElement.setAttribute(DCMConstants.DELL_VERSION_ATTRIBUTE, inInvCompInfo.getDellVersion());
        invElement.setAttribute(DCMConstants.OS_CODE_ATTRIBUTE, inInvCompInfo.getOSCode());
        invElement.setAttribute(DCMConstants.HASH_MD5_ATTRIBUTE, inInvCompInfo.getHashMD5());
        invElement.setAttribute(DCMConstants.PATH_ATTRIBUTE, inInvCompInfo.getPath());
        invElement.setAttribute(DCMConstants.DATE_TIME_ATTRIBUTE, dateToStringFormat(inInvCompInfo.getDate(), DCMConstants.INVENTORY));
        if (inInvCompInfo.getSize() > 0) {
            invElement.setAttribute(DCMConstants.SIZE_ATTRIBUTE, Long.toString(inInvCompInfo.getSize()));
        }

        inParentElement.appendChild(invElement);
        return DCMErrorCodes.SUCCESS;
    }

    private int addPrerequisites(Element inParentElement, DCMManifest inManifest, DCMUpdatePackageInformationCollection inPreReqCollection) {
        if (null == inParentElement || null == inManifest || null == inPreReqCollection) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        Element prerequisites = mDocument.createElement(DCMConstants.PREREQUISITES);
        if (null == prerequisites) {
            return DCMErrorCodes.FAILURE;
        }
        Collection<DCMUpdatePackageInformation> preReqCollection = inPreReqCollection.getUpdatePackages();
        for (DCMUpdatePackageInformation preReq : preReqCollection) {
            addSoftwareComponent(prerequisites, inManifest, preReq);
        }
        inParentElement.appendChild(prerequisites);
        return DCMErrorCodes.SUCCESS;
    }

    String dateToStringFormatForReleaseDate(Date inDate) {
        if (null == inDate) {
            return null;
        }
        String dateString = "";
        DateFormat timeStampDateFormat = new SimpleDateFormat(DCMConstants.INVENTORY_COMPONENT_RELEASE_DATE_FORMAT_STRING);
        try {
            dateString = timeStampDateFormat.format(inDate);
        } catch (Exception ex) {
            Logger.getLogger(DCMCatalogWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dateString;
    }

    String dateToStringFormat(Date inDate, String type) {
        if (null == inDate) {
            return null;
        }
        String dateString = "";
        DateFormat timeStampDateFormat;
        if (type.equals(DCMConstants.INVENTORY)) {
            timeStampDateFormat = new SimpleDateFormat(DCMConstants.INVENTORY_COMPONENT_DATE_FORMAT_STRING);
        } else {
            timeStampDateFormat = new SimpleDateFormat(DCMConstants.DATE_FORMAT_STRING);
        }
        try {
            dateString = timeStampDateFormat.format(inDate);

        } catch (Exception ex) {
            Logger.getLogger(DCMCatalogWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dateString;
    }

    private Element addSoftwareBundleHelper(DCMManifest inManifest, DCMBundle inBundle) {
        if (null == inManifest || null == inBundle) {
            return null;
        }

        Element bundleElement = mDocument.createElement(DCMConstants.SOFTWARE_BUNDLE);
        if (null == bundleElement) {
            return null;
        }
        mToIncludeCDATA = false;

        //Adding attributes
        bundleElement.setAttribute(DCMConstants.SCHEMA_VERSION_ATTRIBUTE, inBundle.getSchemaVersion());
        bundleElement.setAttribute(DCMConstants.RELEASE_ID_ATTRIBUTE, inBundle.getReleaseID());
        if (null == inBundle.getCreationTime()) {
            inBundle.setCreationTime(new Date());
        }
        bundleElement.setAttribute(DCMConstants.DATE_TIME_ATTRIBUTE, dateToStringFormat(inBundle.getCreationTime(), DCMConstants.SOFTWARE_BUNDLE));
        bundleElement.setAttribute(DCMConstants.VENDOR_VERSION_ATTRIBUTE, inBundle.getVendorVersion());
        bundleElement.setAttribute(DCMConstants.PATH_ATTRIBUTE, inBundle.getPath());
        bundleElement.setAttribute(DCMConstants.BUNDLE_ID_ATTRIBUTE, inBundle.getBundleID());
        if (null != inBundle.getIdentifier()) {
            bundleElement.setAttribute(DCMConstants.IDENTIFIER_ATTRIBUTE, inBundle.getIdentifier().getID());
        }
        if (null != inBundle.getPredecessorIdentifier()) {
            bundleElement.setAttribute(DCMConstants.PREDECESSOR_ID_ATTRIBUTE, inBundle.getPredecessorIdentifier().getID());
        }
        bundleElement.setAttribute(DCMConstants.BUNDLE_TYPE_ATTRIBUTE, DCMBundleType.toString(inBundle.getType()));
        bundleElement.setAttribute(DCMConstants.SIZE_ATTRIBUTE, Long.toString(inBundle.getSize()));

        //Adding child nodes
        //Name
        addName(bundleElement, inBundle.getName());

        //componentType
        addComponentType(bundleElement, inManifest.getComponentKindCollection().getComponentKind(inBundle.getComponentType()));

        //description
        addDescription(bundleElement, inBundle.getDescription());

        //Category
        addCategory(bundleElement, inManifest.getCategoryCollection().getCategory(inBundle.getCategoryCode()));

        //TargetOSes
        if (!inBundle.getTargetOperatingSystemIdentifiers().isEmpty()) {
            Element targetOSesElement = mDocument.createElement(DCMConstants.TARGET_OSES);
            for (String osIdentifier : inBundle.getTargetOperatingSystemIdentifiers()) {
                DCMOperatingSystem operatingSystem = inManifest.getOperatingSystemCollection().getSystem(osIdentifier);
                addOperatingSystem(targetOSesElement, operatingSystem);
            }
            bundleElement.appendChild(targetOSesElement);
        }

        //TargetSystems
        addSystemCollectionWithGivenName(bundleElement, DCMConstants.TARGET_SYSTEMS, inManifest, inBundle.getTargetSystemIdentifiers());

        // Revision History
        addRevisionHistory(bundleElement, inBundle.getRevisionHistory());

        //ImportantInfo
        addImportantInformation(bundleElement, inBundle.getImportantInformation());

        //Contents
        addContents(bundleElement, inManifest, inBundle.getUpdatePackageIdentifiers());

        return bundleElement;

    }

    public int writeSoftwareBundles(DCMManifest inManifest, DCMBundle inBundle, File inTargetFile) {
        if (null == inManifest || null == inBundle || null == inTargetFile) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            mDocument = docBuilder.newDocument();

            Element bundleElement = addSoftwareBundleHelper(inManifest, inBundle);
            if (null == bundleElement) {
                return DCMErrorCodes.FAILURE;
            }

            mDocument.appendChild(bundleElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource domSource = new DOMSource(mDocument);
            StreamResult streamResult = new StreamResult(inTargetFile);
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DCMCatalogWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DCMCatalogWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DCMCatalogWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DCMErrorCodes.SUCCESS;
    }

    private int addReleaseNotes(Element inParentElement, DCMManifest inManifest) {
        if (null == inParentElement || null == inManifest) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        Element releaseNote = mDocument.createElement(DCMConstants.RELEASE_NOTES);
        int retCode = addDisplay(releaseNote,inManifest.getReleaseNotes());
        inParentElement.appendChild(releaseNote);
        return DCMErrorCodes.SUCCESS;
    }

    private int addSoftwareBundle(Element inParentElement, DCMManifest inManifest, DCMBundle inBundle) {

        if (null == inParentElement || null == inManifest || null == inBundle) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        Element bundleElement = addSoftwareBundleHelper(inManifest, inBundle);
        if (null == bundleElement) {
            return DCMErrorCodes.FAILURE;
        }
        inParentElement.appendChild(bundleElement);
        return DCMErrorCodes.SUCCESS;
    }

    private int addSoftwareComponent(Element inParentElement, DCMManifest inManifest, DCMUpdatePackageInformation inPackage) {
        if (null == inParentElement || null == inManifest || null == inPackage) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        Element swComponentElement = mDocument.createElement(DCMConstants.SOFTWARE_COMPONENT);
        if (null == swComponentElement) {
            return DCMErrorCodes.FAILURE;
        }
        mToIncludeCDATA = true;

        //Adding attributes
        swComponentElement.setAttribute(DCMConstants.SCHEMA_VERSION_ATTRIBUTE, inPackage.getSchemaVersion());
        swComponentElement.setAttribute(DCMConstants.RELEASE_ID_ATTRIBUTE, inPackage.getReleaseID());
        swComponentElement.setAttribute(DCMConstants.RELEASE_DATE_ATTRIBUTE, dateToStringFormatForReleaseDate(inPackage.getReleaseDate()));
        swComponentElement.setAttribute(DCMConstants.VENDOR_VERSION_ATTRIBUTE, inPackage.getVendorVersion());
        swComponentElement.setAttribute(DCMConstants.DELL_VERSION_ATTRIBUTE, inPackage.getDellVersion());
        swComponentElement.setAttribute(DCMConstants.PACKAGE_TYPE_ATTRIBUTE, DCMPackageType.toString(inPackage.getPackageType()));
        swComponentElement.setAttribute(DCMConstants.XML_GEN_VERSION_ATTRIBUTE, inPackage.getXMLGenVersion());
        swComponentElement.setAttribute(DCMConstants.PATH_ATTRIBUTE, inPackage.getPath());
        swComponentElement.setAttribute(DCMConstants.PACKAGE_ID_ATTRIBUTE, inPackage.getPackageID());
        swComponentElement.setAttribute(DCMConstants.DATE_TIME_ATTRIBUTE, dateToStringFormat(inPackage.getDate(), DCMConstants.SOFTWARE_COMPONENT));
        swComponentElement.setAttribute(DCMConstants.HASH_MD5_ATTRIBUTE, inPackage.getMD5Hash());
        swComponentElement.setAttribute(DCMConstants.SIZE_ATTRIBUTE, Long.toString(inPackage.getSize()));
        if (null != inPackage.getIdentifier()) {
            swComponentElement.setAttribute(DCMConstants.IDENTIFIER_ATTRIBUTE, inPackage.getIdentifier().getID());
        }
        if (null != inPackage.getPredecessorIdentifier()) {
            swComponentElement.setAttribute(DCMConstants.PREDECESSOR_ID_ATTRIBUTE, inPackage.getPredecessorIdentifier().getID());
        }
        if (inPackage.requiresReboot()) {
            swComponentElement.setAttribute(DCMConstants.REBOOT_REQUIRED_ATTRIBUTE, DCMConstants.TRUE);
        } else {
            swComponentElement.setAttribute(DCMConstants.REBOOT_REQUIRED_ATTRIBUTE, DCMConstants.FALSE);
        }

        if (inPackage.requiresContainerPowerCycle()) {
            swComponentElement.setAttribute(DCMConstants.CONTAINER_POWER_CYCLE_REQUIRED_ATTRIBUTE, DCMConstants.ONE);
        } else {
            swComponentElement.setAttribute(DCMConstants.CONTAINER_POWER_CYCLE_REQUIRED_ATTRIBUTE, DCMConstants.ZERO);
        }

        //Adding child nodes
        //Name
        addName(swComponentElement, inPackage.getName());

        //componentType
        addComponentType(swComponentElement, inManifest.getComponentKindCollection().getComponentKind(inPackage.getComponentType()));

        //description
        addDescription(swComponentElement, inPackage.getDescription());

        //LUCategory
        addLUCategory(swComponentElement, inManifest.getCategoryCollection().getCategory(inPackage.getLUCategoryCode()));

        //Category
        addCategory(swComponentElement, inManifest.getCategoryCollection().getCategory(inPackage.getCategoryCode()));

        //Supported Device
        addSupportedDevices(swComponentElement, inManifest, inPackage);

        //Supported Systems
        addSystemCollectionWithGivenName(swComponentElement, DCMConstants.SUPPORTED_SYSTEMS, inManifest, inPackage.getSupportedSystems());

        //Supported OSes
        if (!inPackage.getSupportedOperatingSystems().isEmpty()) {
            Element targetOSesElement = mDocument.createElement(DCMConstants.SUPPORTED_OPERATING_SYSTEMS);
            for (String osIdentifier : inPackage.getSupportedOperatingSystems()) {
                addOperatingSystem(targetOSesElement, inManifest.getOperatingSystemCollection().getSystem(osIdentifier));
            }
            swComponentElement.appendChild(targetOSesElement);
        }

        //Install Instruction
        addInstallInstruction(swComponentElement, inPackage.getInstallInstruction());

        // Revision History
        addRevisionHistory(swComponentElement, inPackage.getRevisionHistory());

        //ImportantInfo
        addImportantInformation(swComponentElement, inPackage.getImportantInformation());

        //Criticality
        addCriticality(swComponentElement, inPackage.getCriticality(), inPackage.getCriticalityDescription());

        //MSIID
        addMSIID(swComponentElement, inPackage.getMSIID());

        //FMPWrappers
        addFMPWrappers(swComponentElement, inPackage.getFMPWrappers());

        //Constituents
        addConstituents(swComponentElement, inPackage.getConstituents());

        if (inPackage.getActivationRules() != null) {
            swComponentElement.appendChild(inPackage.getActivationRules().toXML(mDocument));
        }
        inParentElement.appendChild(swComponentElement);

        return DCMErrorCodes.SUCCESS;
    }

    public int writeCatalog(DCMManifest inManifest, File outFile) {
        if (inManifest == null || outFile == null) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        if (!outFile.exists()) {
            try {
                boolean retCreateFile = outFile.createNewFile();
            } catch (IOException ioEx) {
                return DCMErrorCodes.FAILURE;
            }
        }
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            mDocument = docBuilder.newDocument();
            Element rootElement = mDocument.createElement(DCMConstants.MANIFEST);
            rootElement.setAttribute(DCMConstants.BASE_LOCATION_ATTRIBUTE, inManifest.getBaseLocation());
            if(null != inManifest.getIdentifier() && !inManifest.getIdentifier().getID().isEmpty()){
                rootElement.setAttribute(DCMConstants.IDENTIFIER_ATTRIBUTE, inManifest.getIdentifier().getID());
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DCMConstants.DATE_FORMAT_STRING);
            Date manifestDateTime = inManifest.getCreationDate();
            if (null == manifestDateTime) {
                manifestDateTime = new Date();
            }
            try {
                String time = simpleDateFormat.format(manifestDateTime);

                rootElement.setAttribute(DCMConstants.DATE_TIME_ATTRIBUTE, time);
            } catch (Exception ex) {
            }

            rootElement.setAttribute(DCMConstants.RELEASE_ID_ATTRIBUTE, inManifest.getReleaseID());
            rootElement.setAttribute(DCMConstants.VERSION_ATTRIBUTE, inManifest.getVersion());

            addReleaseNotes(rootElement, inManifest);

            DCMInventoryCollectorInformationCollection dcmInvInfoCollection = inManifest.getInventoryCollectorInformationCollection();
            Collection<DCMInventoryCollectorInformation> invInfoCollection = dcmInvInfoCollection.getInventoryCollectorsInformation();
            for (DCMInventoryCollectorInformation invInfo : invInfoCollection) {
                addInventoryComponent(rootElement, invInfo);
            }
            //addInventoryCollector(Element inParentElement, DCMInventoryCollectorInformation inInvColInfo);

            DCMBundleCollection dcmBundleCollection = inManifest.getBundleCollection();
            Collection<DCMBundle> bundleCollection = dcmBundleCollection.getBundles();

            for (DCMBundle bundle : bundleCollection) {
                addSoftwareBundle(rootElement, inManifest, bundle);
            }
            DCMUpdatePackageInformationCollection dcmUpPkgCollection = inManifest.getSoftwareComponents();
            Collection<DCMUpdatePackageInformation> upPkgCollection = dcmUpPkgCollection.getUpdatePackages();
            for (DCMUpdatePackageInformation upPkg : upPkgCollection) {
                addSoftwareComponent(rootElement, inManifest, upPkg);

            }

            DCMUpdatePackageInformationCollection preReqCollection = inManifest.getPrerequisites();
            addPrerequisites(rootElement, inManifest, preReqCollection);

            if (null != rootElement) {
                mDocument.appendChild(rootElement);
            }

            // Writing mDocument to XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            //   transformerFactory.setAttribute("indent-number", new Integer(2)); 
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource domSource = new DOMSource(mDocument);
            StreamResult streamResult = new StreamResult(outFile);
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //TODO
        return DCMErrorCodes.SUCCESS;
    }

    Document mDocument;
    boolean mToIncludeCDATA;
}
