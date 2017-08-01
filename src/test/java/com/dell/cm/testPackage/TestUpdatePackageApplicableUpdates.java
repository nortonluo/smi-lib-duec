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
package com.dell.cm.testPackage;

import com.dell.cm.comparer.DCMComparer;
import com.dell.cm.comparer.DCMConsiderationEnum;
import com.dell.cm.inventory.DCMInventory;
import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMComponentType;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMI18NString;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMSystem;
import com.dell.cm.updateinformationmodel.DCMSystemIDType;
import com.dell.cm.updateinformationmodel.DCMSystemInstance;
import com.dell.cm.updateinformationmodel.DCMSystemInventory;
import java.util.Calendar;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shilpa_Pv
 */
public class TestUpdatePackageApplicableUpdates {

    public static void main(String[] args) {
        DCMSystem system = new DCMSystem();
        system.setID("0445");   // Mandatory system ID
        system.setLOBKey(DCMConstants.LOB_SERVER);    // Mandatory LOB Key 
        system.setIDType(DCMSystemIDType.BIOS);

        // Optional Value
        DCMI18NString displayI18NString = new DCMI18NString();
        displayI18NString.setDefaultLocaleString("R715");
        system.setName(displayI18NString);

        DCMSystemInstance sysinstance = new DCMSystemInstance();
        sysinstance.setServiceTag("GZ0V5Q1");

        // Optional values
        sysinstance.setAgentName("MSM");
        sysinstance.setAgentVersion("1.0");
        sysinstance.setCollectionTime(Calendar.getInstance().getTime());

        DCMSystemInventory sysInv = new DCMSystemInventory(system, sysinstance);

        sysInv.addPCIDevice("1000", "0072", "1028", "1f1c", "6Gbps SAS HBA", DCMComponentType.FIRMWARE, null, null, "07.03.06.00");
        Logger.getLogger(DCMInventory.class.getName()).log(Level.INFO, "Successfully Added PCI Device 1");

        sysInv.addNonPCIDevice(159, "System BIOS", DCMComponentType.BIOS, "BIOS", null, "3.2.1");

        Logger.getLogger(DCMInventory.class.getName()).log(Level.INFO, "Successfully Added Non PCI Device");

        DCMInventory inventory = new DCMInventory();

        String xml = "C:\\Users\\shilpa_pv\\Downloads\\R715_BIOS_XT83D_WN32_3.2.2.EXE";

        DCMMultiSystemInventory multiInventory = inventory.createEmptyMultiSystemInventory();
        HashSet<DCMSystemInventory> set = new HashSet<DCMSystemInventory>();
        set.add(sysInv);

        new DCMComparer().getApplicableUpdatesByUpdatePackage(set, xml, DCMBundleType.BTW32, DCMConsiderationEnum.REPORT_ALL);

    }

}
