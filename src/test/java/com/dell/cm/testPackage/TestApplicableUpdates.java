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

import com.dell.cm.comparer.DCMCatalog;
import com.dell.cm.comparer.DCMComparer;
import com.dell.cm.comparer.DCMConsiderationEnum;
import com.dell.cm.comparer.DCMUpdateInformation;
import com.dell.cm.inventory.DCMInventory;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMSystemInstance;
import com.dell.cm.updateinformationmodel.DCMSystemInstanceCollection;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author Md_Shahbaz_Alam Test Class to compare an inventory/inventories
 * against a catalog and get compliance report as the set of applicable updates.
 * Required File attached in the resources package
 */
public class TestApplicableUpdates {

    public static void main(String[] args) {
        TestApplicableUpdates applicableUpdates = new TestApplicableUpdates();
        applicableUpdates.inventoryFile = args[0];
        applicableUpdates.catalogFile = args[1];
        applicableUpdates.outputFile = args[2];

        // HashMap to store the output of getApplicableUpdates
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> applicableUpdatesMap = new HashMap<>();
        File inventoryFile = new File(applicableUpdates.inventoryFile);
        File catalogFile = new File(applicableUpdates.catalogFile);
        File outputFile = new File(applicableUpdates.outputFile);

        if (!inventoryFile.exists() || !inventoryFile.canRead()) {
            System.out.println(" Inventory File : " + inventoryFile.getAbsolutePath() + "  doesn't exist or insufficient previledge to access the File");
        } else {
            System.out.println(" Inventory File : " + inventoryFile.getAbsolutePath() + " Exist");
        }

        if (!catalogFile.exists() || !catalogFile.canRead()) {
            System.out.println(" Catalog File : " + catalogFile.getAbsolutePath() + "  doesn't exist or insufficient previledge to access the File");
        } else {
            System.out.println(" Catalog File : " + catalogFile.getAbsolutePath() + " Exist");
        }

        DCMInventory inventoryObj = new DCMInventory();
        DCMCatalog catalogObj = new DCMCatalog();
        DCMManifest manifestObj = catalogObj.parseCatalog(catalogFile);
        DCMMultiSystemInventory multiSystemInventoryObj = inventoryObj.createEmptyMultiSystemInventory();

        inventoryObj.addInventory(inventoryFile, "", multiSystemInventoryObj, Boolean.FALSE);

        // Serializing multiSystemInventory Object to outputFile
        inventoryObj.serializeToFile(multiSystemInventoryObj, outputFile);
        DCMComparer comparerObj = new DCMComparer();

        DCMSystemInstanceCollection dcmSystemInstanceCollection = multiSystemInventoryObj.getSystemInstanceCollection();
        Collection<DCMSystemInstance> SystemInstanceCollection = dcmSystemInstanceCollection.getSystemInstances();
        String systemIdentifier = "";
        for (DCMSystemInstance systemInstance : SystemInstanceCollection) {
            systemIdentifier = systemInstance.getServiceTag();
            HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> tempAppicableUpdatesMap = new HashMap<>();
            tempAppicableUpdatesMap = comparerObj.getApplicableUpdates(multiSystemInventoryObj, systemIdentifier, manifestObj, DCMConsiderationEnum.REPORT_ALL);
            if (null != tempAppicableUpdatesMap) {
                applicableUpdatesMap.putAll(tempAppicableUpdatesMap);
            }
        }

        for (String systemId : applicableUpdatesMap.keySet()) {
            HashMap<DCMVersionInformation, DCMUpdateInformation> tempApplicableUpdatesMap = applicableUpdatesMap.get(systemId);
            int updatesCount = tempApplicableUpdatesMap.size();
            System.out.println(" SystemID : " + systemId + "        Found  " + updatesCount + "  updates");
        }
        System.out.println("getApplicableUpdates Test with single inventory over");
    }

    public String inventoryFile;
    public String catalogFile;
    public String outputFile;

}
