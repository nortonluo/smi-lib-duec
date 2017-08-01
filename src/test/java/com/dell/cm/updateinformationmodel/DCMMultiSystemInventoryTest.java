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

import com.dell.cm.inventory.DCMInventory;
import java.io.File;
import java.util.Collection;
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
public class DCMMultiSystemInventoryTest {

    DCMMultiSystemInventory instance = null;

    public DCMMultiSystemInventoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        DCMInventory inventoryObj = new DCMInventory();
        instance = inventoryObj.createEmptyMultiSystemInventory();
        inventoryObj.addInventory(new File("test\\resources\\outputPowerEdgeVRTX.xml"), "mytest", instance, Boolean.FALSE);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getLineOfBusinessCollection method, of class
     * DCMMultiSystemInventory.
     */
    @Test
    public void testGetLineOfBusinessCollection() {
        System.out.println("getLineOfBusinessCollection");

        DCMLineOfBusinessCollection expResult = null;
        DCMLineOfBusinessCollection result = instance.getLineOfBusinessCollection();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSystemCollection method, of class DCMMultiSystemInventory.
     */
    @Test
    public void testGetSystemCollection() {
        System.out.println("getSystemCollection");

        DCMSystemCollection expResult = null;
        DCMSystemCollection result = instance.getSystemCollection();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSystemInstanceCollection method, of class
     * DCMMultiSystemInventory.
     */
    @Test
    public void testGetSystemInstanceCollection() {
        System.out.println("getSystemInstanceCollection");

        DCMSystemInstanceCollection expResult = null;
        DCMSystemInstanceCollection result = instance.getSystemInstanceCollection();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getOSCollection method, of class DCMMultiSystemInventory.
     */
    @Test
    public void testGetOSCollection() {
        System.out.println("getOSCollection");

        DCMOperatingSystemCollection expResult = null;
        DCMOperatingSystemCollection result = instance.getOSCollection();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getComponentKindCollection method, of class
     * DCMMultiSystemInventory.
     */
    @Test
    public void testGetComponentKindCollection() {
        System.out.println("getComponentKindCollection");

        DCMComponentKindCollection expResult = null;
        DCMComponentKindCollection result = instance.getComponentKindCollection();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    
    /**
     * Test of getUpdateableComponentCollection method, of class
     * DCMMultiSystemInventory.
     */
    @Test
    public void testGetUpdateableComponentCollection() {
        System.out.println("getUpdateableComponentCollection");

        DCMUpdateableComponentCollection expResult = null;
        DCMUpdateableComponentCollection result = instance.getUpdateableComponentCollection();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getCollectionOfVersionInformationCollections method, of class
     * DCMMultiSystemInventory.
     */
    @Test
    public void testGetCollectionOfVersionInformationCollections() {
        System.out.println("getCollectionOfVersionInformationCollections");

        DCMVersionInformationCollectionOfCollection expResult = null;
        DCMVersionInformationCollectionOfCollection result = instance.getCollectionOfVersionInformationCollections();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSystemInstanceIdentifiers method, of class
     * DCMMultiSystemInventory.
     */
    @Test
    public void testGetSystemInstanceIdentifiers() {
        System.out.println("getSystemInstanceIdentifiers");
        String inIdentifier = "";

        Collection<String> expResult = null;
        Collection<String> result = instance.getSystemInstanceIdentifiers(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSystemIdentifiers method, of class DCMMultiSystemInventory.
     */
    @Test
    public void testGetSystemIdentifiers() {
        System.out.println("getSystemIdentifiers");
        String inIdentifier = "sdf";

        Collection<String> expResult = null;
        Collection<String> result = instance.getSystemIdentifiers(inIdentifier);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSystemIdentifier method, of class DCMMultiSystemInventory.
     */
    @Test
    public void testGetSystemIdentifier() {
        System.out.println("getSystemIdentifier");
        String inSystemInstanceIdentifier = "";

        String expResult = null;
        String result = instance.getSystemIdentifier("");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersionInformationCollection method, of class
     * DCMMultiSystemInventory.
     */
    @Test
    public void testGetVersionInformationCollection() {
        System.out.println("getVersionInformationCollection");
        String inSystemInstanceIdentifier = "";

        DCMVersionInformationCollection expResult = null;
        DCMVersionInformationCollection result = instance.getVersionInformationCollection(inSystemInstanceIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getOSIdentifier method, of class DCMMultiSystemInventory.
     */
    @Test
    public void testGetOSIdentifier() {
        System.out.println("getOSIdentifier");
        DCMVersionInformationCollection inVersionInformationCollection = null;

        String expResult = "";
        String result = instance.getOSIdentifier(inVersionInformationCollection);
        assertNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
