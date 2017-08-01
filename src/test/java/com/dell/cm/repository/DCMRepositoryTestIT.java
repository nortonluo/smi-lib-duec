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
import com.dell.cm.testParameters.TestInputs;
import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMSystemInstance;
import com.dell.cm.updateinformationmodel.DCMSystemInstanceCollection;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.downloader.DSMCIFSLocation;
import com.dell.sm.downloader.DSMProtocolEnum;
import com.dell.sm.downloader.DSMProxy;
import com.dell.sm.downloader.IDSMLocation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raveendra_Madala
 */
public class DCMRepositoryTestIT {

    public DCMRepositoryTestIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        catalogObj = new DCMCatalog();
        catalogFile = new File(TestInputs.catalogFileXML);
        inManifest = catalogObj.parseCatalog(catalogFile);
        inventoryObj = new DCMInventory();
        multiSystemInventoryObj = inventoryObj.createEmptyMultiSystemInventory();
        tempApplicableUpdatesMap = new HashMap();
        comparerObj = new DCMComparer();
    }

    @After
    public void tearDown() {
        File destroyDir = new File("src\\test\\output");
        destroyDir.delete();
    }

    /**
     * Test of createRepository method of class DCMRepository and Download the
     * applicable updates
     */
    @Test
    public void testCreateRepository() {

        System.out.println("createRepository");
        File inventoryFile = new File(TestInputs.inventoryFile);
        File outputFile = new File("src\\test\\output");
        inManifest.setBaseLocation(TestInputs.ftpURL);

        inventoryObj.addInventory(inventoryFile, "046F", multiSystemInventoryObj, Boolean.FALSE);
        DCMSystemInstanceCollection dcmSystemInstanceCollection = multiSystemInventoryObj.getSystemInstanceCollection();
        Collection<DCMSystemInstance> SystemInstanceCollection = dcmSystemInstanceCollection.getSystemInstances();

        String systemIdentifier = "";
        for (DCMSystemInstance systemInstance : SystemInstanceCollection) {
            systemIdentifier = systemInstance.getServiceTag();
            tempApplicableUpdatesMap
                    = comparerObj.getApplicableUpdates(multiSystemInventoryObj, systemIdentifier, inManifest, DCMBundleType.BTW64, DCMConsiderationEnum.REPORT_UPGRADES_ONLY);
        }
        Collection<DCMUpdateInformation> updates = new ArrayList<>();
        int i = 0;
        for (DCMUpdateInformation update : tempApplicableUpdatesMap.values().iterator().next().values()) {
            if (i++ > 5) {
                break;
            }
            updates.add(update);
        }
        DCMRepository instance = new DCMRepository();
        int expResult = 0;
        int result = instance.createRepository(updates, inManifest, outputFile, null, null, DSMProtocolEnum.HTTP);
        assertEquals(expResult, result);
    }

    /**
     * Test of createManifestFromUpdates method with 2 arguments of class
     * DCMRepository.
     */
    @Test
    public void testCreateManifestFromUpdates_2args() {
        System.out.println("createManifestFromUpdates");
        File inventoryFile = new File(TestInputs.inventoryFile);
        inManifest = catalogObj.parseCatalog(catalogFile);
        inventoryObj.addInventory(inventoryFile, "046F", multiSystemInventoryObj, Boolean.FALSE);
        dcmSystemInstanceCollection = multiSystemInventoryObj.getSystemInstanceCollection();
        Collection<DCMSystemInstance> SystemInstanceCollection = dcmSystemInstanceCollection.getSystemInstances();
        String systemIdentifier = "";
        for (DCMSystemInstance systemInstance : SystemInstanceCollection) {
            systemIdentifier = systemInstance.getServiceTag();
            tempApplicableUpdatesMap = new HashMap();
            tempApplicableUpdatesMap = comparerObj.getApplicableUpdates(multiSystemInventoryObj, systemIdentifier, inManifest, DCMBundleType.BTALL, DCMConsiderationEnum.REPORT_UPGRADES_N_DOWNGRADES);
        }
        DCMRepository instance = new DCMRepository();
        //DCMManifest expResult = null;
        Collection<DCMUpdateInformation> updates=tempApplicableUpdatesMap.values().iterator().next().values();
        DCMManifest result = instance.createManifestFromUpdates(updates, inManifest);
        assertNotNull(result);
    }

    /**
     * Test for CreatManifestFromUpdates with 4 arguments method of
     * DCMRespository
     */
    @Test
    public void testCreateManifestFromUpdates_4Args() {
        System.out.println("createManifestFromUpdates");
        File inventoryFile = new File(TestInputs.inventoryFile);
        inManifest = catalogObj.parseCatalog(catalogFile);
        inventoryObj.addInventory(inventoryFile, "046F", multiSystemInventoryObj, Boolean.FALSE);
        dcmSystemInstanceCollection = multiSystemInventoryObj.getSystemInstanceCollection();
        Collection<DCMSystemInstance> SystemInstanceCollection = dcmSystemInstanceCollection.getSystemInstances();
        String systemIdentifier = "";
        for (DCMSystemInstance systemInstance : SystemInstanceCollection) {
            systemIdentifier = systemInstance.getServiceTag();
            tempApplicableUpdatesMap = new HashMap();
            tempApplicableUpdatesMap = comparerObj.getApplicableUpdates(multiSystemInventoryObj, systemIdentifier, inManifest, DCMBundleType.BTLX, DCMConsiderationEnum.REPORT_UPGRADES_ONLY);
        }
        DCMRepository instance = new DCMRepository();

        DCMManifest result = instance.createManifestFromUpdates(tempApplicableUpdatesMap.values().iterator().next().values(), inManifest, DCMBundleType.BTLX, multiSystemInventoryObj.getSystemCollection());
        assertEquals(result.getBundleCollection().getBundles().size(), 1);
        assertEquals(result.getBundleCollection().getBundles().iterator().next().getSize(), 1697508324);
    }

    /**
     * Test for CreatManifestFromUpdates with 4 arguments method of
     * DCMRespository
     */
    @Test
    public void testCreateManifestFromUpdates_4Args_MIX() {
        System.out.println("createManifestFromUpdates");
        File inventoryFile = new File(TestInputs.inventoryFile);
        inManifest = catalogObj.parseCatalog(catalogFile);
        inventoryObj.addInventory(inventoryFile, "046F", multiSystemInventoryObj, Boolean.FALSE);
        dcmSystemInstanceCollection = multiSystemInventoryObj.getSystemInstanceCollection();
        Collection<DCMSystemInstance> SystemInstanceCollection = dcmSystemInstanceCollection.getSystemInstances();
        String systemIdentifier = "";
        for (DCMSystemInstance systemInstance : SystemInstanceCollection) {
            systemIdentifier = systemInstance.getServiceTag();
            tempApplicableUpdatesMap = new HashMap();
            tempApplicableUpdatesMap = comparerObj.getApplicableUpdates(multiSystemInventoryObj, systemIdentifier, inManifest, DCMBundleType.BTWMIX, DCMConsiderationEnum.REPORT_UPGRADES_ONLY);
        }
        DCMRepository instance = new DCMRepository();

        DCMManifest result = instance.createManifestFromUpdates(tempApplicableUpdatesMap.values().iterator().next().values(), inManifest, DCMBundleType.BTWMIX, multiSystemInventoryObj.getSystemCollection());
        assertEquals(result.getBundleCollection().getBundles().size(), 1);
        assertEquals(result.getBundleCollection().getBundles().iterator().next().getSize(), 709737616);        
    }

    /**
     * Test for CreatManifestFromUpdates with 4 arguments method of
     * DCMRespository
     */
    @Test
    public void testCreateManifestFromUpdates_4Args_ALL() {
        System.out.println("createManifestFromUpdates");
        File inventoryFile = new File(TestInputs.inventoryFile);
        inManifest = catalogObj.parseCatalog(catalogFile);
        inventoryObj.addInventory(inventoryFile, "046F", multiSystemInventoryObj, Boolean.FALSE);
        dcmSystemInstanceCollection = multiSystemInventoryObj.getSystemInstanceCollection();
        Collection<DCMSystemInstance> SystemInstanceCollection = dcmSystemInstanceCollection.getSystemInstances();
        String systemIdentifier = "";
        for (DCMSystemInstance systemInstance : SystemInstanceCollection) {
            systemIdentifier = systemInstance.getServiceTag();
            tempApplicableUpdatesMap = new HashMap();
            tempApplicableUpdatesMap = comparerObj.getApplicableUpdates(multiSystemInventoryObj, systemIdentifier, inManifest, DCMBundleType.BTALL, DCMConsiderationEnum.REPORT_UPGRADES_ONLY);
        }
        DCMRepository instance = new DCMRepository();

        DCMManifest result = instance.createManifestFromUpdates(tempApplicableUpdatesMap.values().iterator().next().values(), inManifest, DCMBundleType.BTALL, multiSystemInventoryObj.getSystemCollection());
        assertEquals(result.getBundleCollection().getBundles().size(), 3);
        assertEquals(result.getBundle("61a5a654-6b4b-11e6-832b-005056a677a8").getSize(), 1697508324);
        assertEquals(result.getBundle("6026b818-6b4b-11e6-832b-005056a677a8").getSize(), 433079288);
        assertEquals(result.getBundle("637fca0e-6b4b-11e6-832b-005056a677a8").getSize(), 709737616);
    }

    /**
     * Test of createSUURepository method, of class DCMRepository.
     */
    @Test
    public void testCreateSUURepository_8args_1() {
        System.out.println("createSUURepository");
        Collection<DCMUpdateInformation> inUpdates = null;
        File inventoryFile = new File(TestInputs.inventoryFile);
        DSMAuthenticationParameters inSourceAuthParameters = null;
        Collection<DSMProxy> inProxyCollection = null;
        DSMProtocolEnum inSrcProtocol = DSMProtocolEnum.HTTP;
        DCMBundleType inBundleType = null;
        String inBaseLocation = "ftp.dell.com";
        DSMAuthenticationParameters destinationAuthParameters = new DSMAuthenticationParameters();
        destinationAuthParameters.setUserName(TestInputs.shareUserName);
        destinationAuthParameters.setPassword(TestInputs.sharePassword);
        //destinationAuthParameters.setDomainName("Americas");

        String shareName = "\\\\" + TestInputs.shareIp + "\\" + TestInputs.shareLocation;
        String sharePath = "duec_test\\DUECArtifact";
        IDSMLocation dsmLocation = new DSMCIFSLocation(destinationAuthParameters, shareName, sharePath);

        inventoryObj.addInventory(inventoryFile, "046F", multiSystemInventoryObj, Boolean.FALSE);
        dcmSystemInstanceCollection = multiSystemInventoryObj.getSystemInstanceCollection();
        Collection<DCMSystemInstance> SystemInstanceCollection = dcmSystemInstanceCollection.getSystemInstances();
        String systemIdentifier = "";
        for (DCMSystemInstance systemInstance : SystemInstanceCollection) {
            systemIdentifier = systemInstance.getServiceTag();
            tempApplicableUpdatesMap = new HashMap();
            tempApplicableUpdatesMap = comparerObj.getApplicableUpdates(multiSystemInventoryObj, systemIdentifier, inManifest, DCMBundleType.BTW64, DCMConsiderationEnum.REPORT_UPGRADES_ONLY);
        }
        DCMRepository instance = new DCMRepository();
        int expResult = 0;
        Collection<DCMUpdateInformation> updates = new ArrayList<>();
        int i = 0;
        for (DCMUpdateInformation update : tempApplicableUpdatesMap.values().iterator().next().values()) {
            if (i++ > 5) {
                break;
            }
            updates.add(update);
        }
        int result = instance.createSUURepository(updates, inManifest, inSourceAuthParameters, inProxyCollection, inSrcProtocol, inBundleType, inBaseLocation, dsmLocation);
        if (result == 0) {
            assertEquals(expResult, result);
        } else {
            assertEquals(11, result); // for partial download
        }
    }

    /**
     * Test of createSUURepository method, of class DCMRepository.
     */
    @Test
    public void testCreateSUURepository_8args_2() {
        System.out.println("createSUURepository");
        File inventoryFile = new File(TestInputs.inventoryFile);
        Collection<String> inInventoryList = new HashSet<>();

        BufferedReader rd;
        try {
            rd = new BufferedReader(new FileReader(inventoryFile));
            String inputLine = null;
            StringBuilder builder = new StringBuilder();
            //Store the contents of the file to the StringBuilder.
            while ((inputLine = rd.readLine()) != null) {
                builder.append(inputLine);
            }

            String inventory = builder.toString();

            inInventoryList.add(inventory.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DCMRepositoryTestIT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DCMRepositoryTestIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collection<DSMProxy> inProxyCollection = null;
        DSMAuthenticationParameters inSrcAuthParameters = null;
        DSMAuthenticationParameters destinationAuthenticationParams = new DSMAuthenticationParameters();
        //destinationAuthenticationParams.setDomainName("Americas");
        destinationAuthenticationParams.setUserName(TestInputs.shareUserName);
        destinationAuthenticationParams.setPassword(TestInputs.sharePassword);
        DSMProtocolEnum inSrcProtocol = DSMProtocolEnum.HTTP;
        String inBaseLocation = "downloadfail.com";
        DCMBundleType inBundleType = DCMBundleType.BTW64;

        String shareName = "\\\\" + TestInputs.shareIp + "\\" + TestInputs.shareLocation;
        String sharePath = "duec_test\\DUECArtifact";
        IDSMLocation inDestinationDSMLocation = new DSMCIFSLocation(destinationAuthenticationParams, shareName, sharePath);
        DCMRepository instance = new DCMRepository();
        int expResult = 0;
        int result = instance.createSUURepository(inInventoryList, inProxyCollection, catalogFile.toString(), inSrcAuthParameters, inSrcProtocol, inBaseLocation, inBundleType, inDestinationDSMLocation);
        if (result == 0) {
            assertEquals(expResult, result);
        } else {
            assertEquals(11, result); //for partial download
        }
    }

    DCMManifest inManifest;
    DCMCatalog catalogObj;
    File catalogFile;
    DCMInventory inventoryObj;
    DCMMultiSystemInventory multiSystemInventoryObj;
    DCMComparer comparerObj;
    DCMSystemInstanceCollection dcmSystemInstanceCollection;
    HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> tempApplicableUpdatesMap;
}
