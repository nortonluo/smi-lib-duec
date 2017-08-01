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
package com.dell.cm.inventory;

import com.dell.cm.testParameters.TestInputs;
import com.dell.cm.updateinformationmodel.DCMComponentKind;
import com.dell.cm.updateinformationmodel.DCMComponentKindCollection;
import com.dell.cm.updateinformationmodel.DCMComponentType;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMOperatingSystem;
import com.dell.cm.updateinformationmodel.DCMOperatingSystemCollection;
import com.dell.cm.updateinformationmodel.DCMPCIInfo;
import com.dell.cm.updateinformationmodel.DCMSystem;
import com.dell.cm.updateinformationmodel.DCMSystemCollection;
import com.dell.cm.updateinformationmodel.DCMSystemInstanceCollection;
//import com.dell.cm.updateinformationmodel.DCMTargetCollection;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.downloader.DSMProtocolEnum;
import com.dell.sm.downloader.DSMProxy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.HashSet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assume;
import org.w3c.dom.Element;

/**
 *
 * @author shilpa_pv
 */
public class DCMInventoryIT {

    public DCMInventoryIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of createEmptyMultiSystemInventory method, of class DCMInventory.
     */
    @Test
    public void testCreateEmptyMultiSystemInventory() {
        System.out.println("createEmptyMultiSystemInventory");

        DCMInventory instance = new DCMInventory();
        DCMMultiSystemInventory result = instance.createEmptyMultiSystemInventory();
        assertSame(result, result);

    }

    /**
     * Test of addInventory method, of class DCMInventory.
     */
    @Test
    public void testAddInventory_3args_1() {
        System.out.println("addInventory");
        File file = new File(TestInputs.multiSystemInventory);
        HashSet<File> inFileSet = new HashSet<File>();
        inFileSet.add(file);
        String inIdentifier = "045F";
        DCMMultiSystemInventory inRoot = new DCMMultiSystemInventory();
        DCMInventory instance = new DCMInventory();
        int expResult = 0;
        int result = instance.addInventory(inFileSet, inIdentifier, inRoot);
        assertEquals(expResult, result);
    }

    /**
     * Test of addInventory method, of class DCMInventory.
     */
    @Test
    public void testAddInventory_4args_1() {
        System.out.println("addInventory");
        File inFile = new File(TestInputs.multiSystemInventory);
        String inIdentifier = "045F";
        DCMMultiSystemInventory inRoot = new DCMMultiSystemInventory();
        Boolean inAddAsChild = Boolean.FALSE;
        DCMInventory instance = new DCMInventory();
        int expResult = 0;
        int result = instance.addInventory(inFile, inIdentifier, inRoot, inAddAsChild);
        assertEquals(expResult, result);

    }

    /**
     * Test of addInventory method, of class DCMInventory.
     */
    @Test
    public void testAddInventory_4args_2() throws FileNotFoundException, IOException {
        System.out.println("addInventory");
        BufferedReader rd = new BufferedReader(new FileReader(TestInputs.inventoryFile));
        String inputLine = null;
        StringBuilder builder = new StringBuilder();
        //Store the contents of the file to the StringBuilder.
        while ((inputLine = rd.readLine()) != null) {
            builder.append(inputLine);
        }
        StringReader inventory = new StringReader(builder.toString());
        String inIdentifier = "";
        DCMMultiSystemInventory inRoot = new DCMMultiSystemInventory();
        Boolean inAddAsChild = Boolean.FALSE;
        DCMInventory instance = new DCMInventory();
        int expResult = 0;
        int result = instance.addInventory(inventory, inIdentifier, inRoot, inAddAsChild);
        assertEquals(expResult, result);
    }

    /**
     * Test of addWSInventory method, of class DCMInventory.
     */
    @Test
    public void testAddWSInventory_File_DCMMultiSystemInventory_Proxy() {
        System.out.println("addWSInventoryProxy");
        DSMProxy mProxy = new DSMProxy();
        mProxy.setURL("100.96.16.164");
        mProxy.setProxyUserName("creator");
        mProxy.setProxyPassword("Dell_123$");
        mProxy.setProtocol(DSMProtocolEnum.HTTP);
        mProxy.setPortNumber(3128);
        System.out.println("addWSInventory");
        String ipAddress = TestInputs.iDracIpAddr;
        String userName = TestInputs.userName;
        String password = TestInputs.password;
        int noOfSystems = 1;
        int noOfComponents = 3; // assign value 1 in case of CMC

        DSMAuthenticationParameters auth = new DSMAuthenticationParameters();
        auth.setUserName(userName);
        auth.setPassword(password);
        DCMInventory instance = new DCMInventory();

        DCMMultiSystemInventory inRoot = instance.createEmptyMultiSystemInventory();
        int result = instance.addWSInventory(ipAddress, "443", auth, inRoot, mProxy);
        assertEquals(result, DCMErrorCodes.SUCCESS);
    }

    /**
     * Test of addWSInventory method, of class DCMInventory.
     */
    //@Test
    public void testAddWSInventory_File_DCMMultiSystemInventory() {
        System.out.println("addWSInventory");
        String ipAddress = TestInputs.iDracIpAddr;
        String userName = TestInputs.userName;
        String password = "rahul";
        int noOfSystems = 1;
        int noOfComponents = 3; // assign value 1 in case of CMC

        DSMAuthenticationParameters auth = new DSMAuthenticationParameters();
        auth.setUserName(userName);
        auth.setPassword(password);
        DCMInventory instance = new DCMInventory();

        DCMMultiSystemInventory inRoot = instance.createEmptyMultiSystemInventory();

        int result = instance.addWSInventory(ipAddress, auth, inRoot);
        assertEquals(DCMErrorCodes.SUCCESS, result);
        //Assume.assumeTrue(result != DCMErrorCodes.AUTH_FAILURE);

        DCMSystemCollection systemCollection = inRoot.getSystemCollection();
        Collection<DCMSystem> collection = systemCollection.getSystems();
        assertEquals(noOfSystems, collection.size());

        DCMComponentKindCollection componentCollection = inRoot.getComponentKindCollection();
        Collection<DCMComponentKind> col = componentCollection.getComponentKinds();
        Object[] componentCollectionArray = col.toArray();
        assertEquals(noOfComponents, componentCollectionArray.length);
        DCMSystemInstanceCollection systemInstanceColl = inRoot.getSystemInstanceCollection();
        //boolean hasSystemInstance = systemInstanceColl.hasSystemInstance("12D5082");
        /**
         * Comment the below the statement in case of CMC and use the above
         * statement
         */
        boolean hasSystemInstance = systemInstanceColl.hasSystemInstance("2P5M082");
        assertTrue(hasSystemInstance);
    }

    /**
     * Test of addRedFishInventory method, of class DCMInventory.
     */
    @Test
    public void testAddRedFishInventory_File_DCMMultiSystemInventory() {
        System.out.println("addRedFishInventory");
        File inFile = null;
        DCMMultiSystemInventory inRoot = null;
        DCMInventory instance = new DCMInventory();
        int expResult = 0;
        int result = instance.addRedFishInventory(inFile, inRoot);
        assertEquals(expResult, result);
    }

    /**
     * Test of addInventory method, of class DCMInventory.
     */
    @Test
    public void testAddInventory_3args_2() {
        System.out.println("addInventory");
        File file = new File(TestInputs.multiSystemInventory);
        HashSet<File> inFileSet = new HashSet<File>();
        inFileSet.add(file);
        String inIdentifier = "045F";
        DCMMultiSystemInventory inRoot = new DCMMultiSystemInventory();
        DCMInventory instance = new DCMInventory();
        int expResult = 0;
        int result = instance.addInventory(inFileSet, inIdentifier, inRoot);
        assertEquals(expResult, result);

    }

    /**
     * Test of addWSInventory method, of class DCMInventory.
     */
    @Test
    public void testAddWSInventory_HashSet_DCMMultiSystemInventory() {
        System.out.println("addWSInventory");
        HashSet<File> inFileSet = new HashSet<File>();
        File file = new File(TestInputs.multiSystemInventory);
        inFileSet.add(file);
        DCMMultiSystemInventory inRoot = null;
        DCMInventory instance = new DCMInventory();
        int expResult = 0;
        int result = instance.addWSInventory(inFileSet, inRoot);
        assertEquals(expResult, result);
    }

    /**
     * Test of addRedFishInventory method, of class DCMInventory.
     */
    @Test
    public void testAddRedFishInventory_HashSet_DCMMultiSystemInventory() {
        System.out.println("addRedFishInventory");
        HashSet<File> inFileSet = new HashSet<File>();
        DCMMultiSystemInventory inRoot = null;
        DCMInventory instance = new DCMInventory();
        int expResult = 0;
        int result = instance.addRedFishInventory(inFileSet, inRoot);
        assertEquals(expResult, result);
    }

    /**
     * Test of deserializeXMLFile method, of class DCMInventory.
     */
    @Test
    public void testDeserializeXMLFile() {
        System.out.println("deserializeXMLFile");
        File inMultiInventoryFile = new File(TestInputs.multiSystemInventory);
        DCMMultiSystemInventory inRoot = new DCMMultiSystemInventory();
        DCMInventory instance = new DCMInventory();
        int expResult = 0;
        int result = instance.deserializeXMLFile(inMultiInventoryFile, inRoot);
        assertEquals(expResult, result);

    }

    /**
     * Test of serializeToFile method, of class DCMInventory.
     */
    @Test
    public void testSerializeToFile() {
        System.out.println("serializeToFile");
        File inMultiInventoryFile = new File(TestInputs.multiSystemInventory);
        File inOutFile = new File("src\\test\\resources\\output.xml");
        DCMInventory instance = new DCMInventory();
        int expResult = 0;
        int noOfSystems = 6;
        int noOfOSCollections = 3;

        DCMMultiSystemInventory inRoot = instance.createEmptyMultiSystemInventory();
        assertSame(inRoot, inRoot);

        int result = instance.deserializeXMLFile(inMultiInventoryFile, inRoot);
        assertEquals(expResult, result);
        DCMSystemCollection systemCollection = inRoot.getSystemCollection();
        Collection<DCMSystem> collection = systemCollection.getSystems();
        Object[] systemCollectionArray = collection.toArray();
        assertEquals(noOfSystems, systemCollectionArray.length);

        DCMOperatingSystemCollection osSystemCollection = inRoot.getOSCollection();
        Collection<DCMOperatingSystem> osCollection = osSystemCollection.getOperatingSystems();
        Object[] osCollectionArray = osCollection.toArray();
        assertEquals(noOfOSCollections, osCollectionArray.length);

        int outResult = instance.serializeToFile(inRoot, inOutFile);
        assertEquals(expResult, outResult);
    }

    /**
     * Test of serializeToString method, of class DCMInventory.
     */
    @Test
    public void testSerializeToString() {
        System.out.println("serializeToString");
        File file = new File(TestInputs.multiSystemInventory);
        DCMMultiSystemInventory inRoot = new DCMMultiSystemInventory();
        DCMInventory instance = new DCMInventory();
        int expResult = 0;
        instance.deserializeXMLFile(file, inRoot);
        String result = instance.serializeToString(inRoot);
        File resultFile = new File(result);
        int finalResult = instance.serializeToFile(inRoot, resultFile);
        assertEquals(expResult, finalResult);

    }

}
