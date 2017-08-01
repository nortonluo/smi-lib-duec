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
public class DCMVersionInformationCollectionTest {

    public DCMVersionInformationCollectionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getVersionInformation method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testGetVersionInformation() {
        System.out.println("getVersionInformation");
        String inIdentifier = "";
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        DCMVersionInformation expResult = null;
        DCMVersionInformation result = instance.getVersionInformation(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addVersionInformation method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testAddVersionInformation() {
        System.out.println("addVersionInformation");
        DCMVersionInformation inVersionInformation = null;
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        int expResult = 3;
        int result = instance.addVersionInformation(inVersionInformation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeVersionInformation method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testRemoveVersionInformation() {
        System.out.println("removeVersionInformation");
        String inIdentifier = "";
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        int expResult = 5;
        int result = instance.removeVersionInformation(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersionInformationObjects method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testGetVersionInformationObjects() {
        System.out.println("getVersionInformationObjects");
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        Collection<DCMVersionInformation> expResult = null;
        Collection<DCMVersionInformation> result = instance.getVersionInformationObjects();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSystemInstance method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testAddSystemInstance() {
        System.out.println("addSystemInstance");
        String inInstanceIdentifier = "";
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        int expResult = 0;
        int result = instance.addSystemInstance(inInstanceIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSystemInstance method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testRemoveSystemInstance() {
        System.out.println("removeSystemInstance");
        String inInstanceIdentifier = "";
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        int expResult = 5;
        int result = instance.removeSystemInstance(inInstanceIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSystemInstanceIdentifiers method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testGetSystemInstanceIdentifiers() {
        System.out.println("getSystemInstanceIdentifiers");
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        Collection<String> expResult = null;
        Collection<String> result = instance.getSystemInstanceIdentifiers();
        assertEquals(0, result.size());
    }

    /**
     * Test of supportsSystemInstance method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testSupportsSystemInstance() {
        System.out.println("supportsSystemInstance");
        String inSystemInstanceIdentifier = "";
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        boolean expResult = false;
        boolean result = instance.supportsSystemInstance(inSystemInstanceIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersionInformationCollection method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testGetVersionInformationCollection() {
        System.out.println("getVersionInformationCollection");
        String inSystemInstanceIdentifier = "";
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        DCMVersionInformationCollection expResult = null;
        DCMVersionInformationCollection result = instance.getVersionInformationCollection(inSystemInstanceIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addVersionInformationCollection method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testAddVersionInformationCollection() {
        System.out.println("addVersionInformationCollection");
        DCMVersionInformationCollection inInformationCollectionIdentifier = null;
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        int expResult = 3;
        int result = instance.addVersionInformationCollection(inInformationCollectionIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeVersionInformationCollection method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testRemoveVersionInformationCollection() {
        System.out.println("removeVersionInformationCollection");
        DCMVersionInformationCollection inInformationCollectionIdentifier = null;
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        int expResult = 3;
        int result = instance.removeVersionInformationCollection(inInformationCollectionIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersionInformationCollections method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testGetVersionInformationCollections() {
        System.out.println("getVersionInformationCollections");
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        Collection<DCMVersionInformationCollection> expResult = null;
        Collection<DCMVersionInformationCollection> result = instance.getVersionInformationCollections();
        assertEquals(0, result.size());
    }

    /**
     * Test of getUniqueIdentifier method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testGetUniqueIdentifier() {
        System.out.println("getUniqueIdentifier");
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        int expResult = 0;
        int result = instance.getUniqueIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setUniqueIdentifier method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testSetUniqueIdentifier() {
        System.out.println("setUniqueIdentifier");
        int inIdentifier = 0;
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        int expResult = 0;
        int result = instance.setUniqueIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getGroupIdentifier method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testGetGroupIdentifier() {
        System.out.println("getGroupIdentifier");
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        String expResult = null;
        String result = instance.getGroupIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setGroupIdentifier method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testSetGroupIdentifier() {
        System.out.println("setGroupIdentifier");
        String inIdentifier = "";
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        int expResult = 3;
        int result = instance.setGroupIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getAllSystemInstanceIdentifiers method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testGetAllSystemInstanceIdentifiers() {
        System.out.println("getAllSystemInstanceIdentifiers");
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        Collection<String> expResult = null;
        Collection<String> result = instance.getAllSystemInstanceIdentifiers();
        assertEquals(0, result.size());
    }

    /**
     * Test of areSameCollections method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testAreSameCollections() {
        System.out.println("areSameCollections");
        DCMVersionInformationCollection versionInfoCollection = new DCMVersionInformationCollection();
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        boolean expResult = true;
        boolean result = instance.areSameCollections(versionInfoCollection);
        assertEquals(expResult, result);

    }

    /**
     * Test of GetMaxInstanceValue method, of class
     * DCMVersionInformationCollection.
     */
    @Test
    public void testGetMaxInstanceValue() {
        System.out.println("GetMaxInstanceValue");
        DCMVersionInformation inVersionInfo = null;
        DCMVersionInformationCollection instance = new DCMVersionInformationCollection();
        int expResult = -1;
        int result = instance.getMaxInstanceValue(inVersionInfo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
