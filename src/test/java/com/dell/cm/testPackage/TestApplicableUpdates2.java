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
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Md_Shahbaz_Alam Purpose : To get the set of updates for an
 * inventory/inventories by comparing against a catalog. Required File attached
 * in the resources package
 */
public class TestApplicableUpdates2 {

    public static void main(String[] args) {
        TestApplicableUpdates2 applicableUpdates = new TestApplicableUpdates2();

        // HashMap to store the output of getApplicableUpdates
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> applicableUpdatesMap = new HashMap<>();

        // Creating the inventories set that contains the list of all inventories against which the update is expected.
        HashSet<File> inventories = new HashSet<>();

        applicableUpdates.catalogFile = args[0];
        applicableUpdates.inventorieFiles = new String[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            applicableUpdates.inventorieFiles[i - 1] = args[i];
        }
        for (int i = 0; i < applicableUpdates.inventorieFiles.length; i++) {
            String inventory = applicableUpdates.inventorieFiles[i];
            File inventoryFile = new File(inventory);
            if (!inventoryFile.exists() || !inventoryFile.canRead()) {
                System.out.println(" Inventory File : " + inventoryFile.getAbsolutePath() + "  doesn't exist or insufficient previledge to access the File");
            } else {
                System.out.println(" Inventory File : " + inventoryFile.getAbsolutePath() + " Exist");
            }
            inventories.add(inventoryFile);
        }

        // creating manifest object by parsing the catalog File
        File catalogFile = new File(applicableUpdates.catalogFile);
        if (!catalogFile.exists() || !catalogFile.canRead()) {
            System.out.println(" Catalog File : " + catalogFile.getAbsolutePath() + "  doesn't exist or insufficient previledge to access the File");
        } else {
            System.out.println(" Catalog File : " + catalogFile.getAbsolutePath() + " Exist");
        }

        DCMCatalog catalogObj = new DCMCatalog();
        DCMManifest manifestObj = catalogObj.parseCatalog(catalogFile);

        DCMComparer comparerObj = new DCMComparer();
        String systemIdentifier = "";
        // Getting the set of updates for all inventories by comparing against the catalog. 
        applicableUpdatesMap = comparerObj.getApplicableUpdates(inventories, systemIdentifier, catalogFile, DCMConsiderationEnum.REPORT_ALL);

        for (String systemId : applicableUpdatesMap.keySet()) {
            HashMap<DCMVersionInformation, DCMUpdateInformation> tempApplicableUpdatesMap = applicableUpdatesMap.get(systemId);
            int updatesCount = tempApplicableUpdatesMap.size();
            System.out.println(" SystemID : " + systemId + "        Found  " + updatesCount + "  updates");
        }
        System.out.println("getApplicableUpdates Test with multiple inventories over");

    }
    public String catalogFile;
    public String inventorieFiles[];

}
