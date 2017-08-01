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
package com.dell.cm.updateinformationmodel;

/*
 * Purpose	:  class for 
 *
 * Author	:  Raveendra Madala
 *
 * $LastChangedBy: raveendra_madala $
 *
 * $Date: 2015-02-05 09:46:39 +0530 (Thu, 05 Feb 2015) $
 *
 * $Revision: 2211 $
 *
 */
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class DCMSystemInventory {

    /**
     * method for creating system inventory per system
     *
     * @param inSystem specifies DCMSystem
     * @param inInstance specifies system instance
     */

    public DCMSystemInventory(DCMSystem inSystem, DCMSystemInstance inInstance) {
        mDevices = new HashSet<Object[]>();
        setSystem(inSystem);
        setSystemInstance(inInstance);
    }

    /**
     * Method for setting the system information
     *
     * @param inSystem specifies the name being set
     * @return the status
     */
    public int setSystem(DCMSystem inSystem) {
        mSystem = inSystem;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for setting the system information
     *
     * @return the system
     */
    public DCMSystem getSystem() {
        return mSystem;
    }

    /**
     * Method for setting the system instance information
     *
     * @param inInstance specifies the name being set
     * @return the status
     */
    public int setSystemInstance(DCMSystemInstance inInstance) {
        mSystemInstance = inInstance;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for setting the system information
     *
     * @return the system
     */
    public DCMSystemInstance getSystemInstance() {
        return mSystemInstance;
    }

    /**
     * Method for adding devices to the inventory objects, Call this multiple
     * time to add all devices to the system.
     *
     * @param inDevice specifies the object type.
     * @param inUpdateType specifies the update is of type (SBDL, FRMW, BIOS,
     * APAC, APP, DRVR)
     * @param inAppName Represents the name of the updateable component
     * @param inInstance specifies the devices instance, null if single
     * instance, can be port for pci
     * @param inVersion specifies the devices version information
     * @return the status
     */
    public int addDevice(DCMBaseTarget inDevice, DCMComponentType inUpdateType, String inAppName, String inInstance, String inVersion) {
        if (inDevice == null || inUpdateType == null || inVersion == null) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        DCMUpdateableComponent updatecomponent = new DCMUpdateableComponent(inUpdateType, inDevice.getUniqueIdentifier());
        if (inAppName != null && !inAppName.isEmpty()) {
            DCMI18NString displayI18NString = new DCMI18NString();
            displayI18NString.setDefaultLocaleString(inAppName);
            updatecomponent.setName(displayI18NString);
        }

        DCMVersionInformation version = new DCMVersionInformation();
        version.setTargetIdentifier(inDevice.getUniqueIdentifier());
        version.setVersion(inVersion);
        version.setTargetInstance(inInstance);
        version.setUpdateableComponentIdentifier(updatecomponent.getUniqueIdentifier());

        mDevices.add(new Object[]{inDevice, version, updatecomponent});
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for adding devices to the inventory objects, Call this multiple
     * time to add all devices to the system.
     *
     * @param inPCIId specifies the id of the PCI device.
     * @param inName specifies name of the PCI device.
     * @param inUpdateType specifies the update is of type (SBDL, FRMW, BIOS,
     * APAC, APP, DRVR)
     * @param inAppName Represents the name of the updateable component
     * @param inInstance specifies the devices instance, null if single
     * instance, can be port for pci
     * @param inVersion specifies the devices version information
     * @return the status
     */
    public int addPCIDevice(String inPCIId, String inName, DCMComponentType inUpdateType, String inAppName, String inInstance, String inVersion) {
        if (inPCIId == null || inUpdateType == null || inVersion == null) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        DCMPCIInfo pciInfo = DCMPCIInfo.parse(inPCIId);

        DCMPCIDevice pciDevice = new DCMPCIDevice(pciInfo);
        DCMI18NString name = new DCMI18NString();
        name.setDefaultLocaleString(inName);
        pciDevice.setName(name);

        return addDevice(pciDevice, inUpdateType, inAppName, inInstance, inVersion);
    }

    /**
     * Method for adding devices to the inventory objects, Call this multiple
     * time to add all devices to the system. Construct with vendor id, device
     * id, sub vendor id and sub device id
     *
     * @param inVendorID specifies the vendor id
     * @param inDeviceID specifies the device id
     * @param inSubVendorID specifies the sub vendor id
     * @param inSubDeviceID specifies the sub device id
     * @param inName specifies name of the PCI device.
     * @param inUpdateType specifies the update is of type (SBDL, FRMW, BIOS,
     * APAC, APP, DRVR)
     * @param inAppName Represents the name of the updateable component
     * @param inInstance specifies the devices instance, null if single
     * instance, can be port for pci
     * @param inVersion specifies the devices version information
     * @return the status
     */

    public int addPCIDevice(String inVendorID, String inDeviceID, String inSubVendorID, String inSubDeviceID, String inName, DCMComponentType inUpdateType, String inAppName, String inInstance, String inVersion) {
        if (inVendorID == null || inDeviceID == null || inSubVendorID == null || inSubDeviceID == null
                || inUpdateType == null || inVersion == null) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        DCMPCIInfo pciInfo = DCMPCIInfo.parse(inVendorID, inDeviceID, inSubVendorID, inSubDeviceID);

        DCMPCIDevice pciDevice = new DCMPCIDevice(pciInfo);
        DCMI18NString name = new DCMI18NString();
        name.setDefaultLocaleString(inName);
        pciDevice.setName(name);

        return addDevice(pciDevice, inUpdateType, inAppName, inInstance, inVersion);
    }

    /**
     * Method for adding devices to the inventory objects, Call this multiple
     * time to add all devices to the system.
     *
     * @param inComponentId specifies the component id of non pci device.
     * @param inName specifies name of the non pci device.
     * @param inUpdateType specifies the update is of type (SBDL, FRMW, BIOS,
     * APAC, APP, DRVR)
     * @param inAppName Represents the name of the updateable component
     * @param inInstance specifies the devices instance, null if single
     * instance, can be port for pci
     * @param inVersion specifies the devices version information
     * @return the status
     */
    public int addNonPCIDevice(int inComponentId, String inName, DCMComponentType inUpdateType, String inAppName, String inInstance, String inVersion) {
        if (inUpdateType == null || inVersion == null) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        DCMNonPCIPnPHardware nonPCIDevice = new DCMNonPCIPnPHardware(inComponentId);
        DCMI18NString name = new DCMI18NString();
        name.setDefaultLocaleString(inName);
        nonPCIDevice.setName(name);

        return addDevice(nonPCIDevice, inUpdateType, inAppName, inInstance, inVersion);
    }

    /**
     * Method for setting the system information
     *
     * @return the system
     */
    public Collection<Object[]> getDevices() {
        return mDevices;
    }

    // class members.
    DCMSystem mSystem;
    DCMSystemInstance mSystemInstance;
    DCMOperatingSystem mOS;
    Collection<Object[]> mDevices;
}
