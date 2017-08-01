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
public class DCMVersionInformationCollectionOfCollectionTest {

    public DCMVersionInformationCollectionOfCollectionTest() {
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
     * Test of getVersionInformationCollectio method, of class
     * DCMVersionInformationCollectionOfCollection.
     */
    @Test
    public void testGetVersionInformationCollectio() {
        System.out.println("getVersionInformationCollectio");
        int inIdentifier = 0;
        DCMVersionInformationCollectionOfCollection instance = new DCMVersionInformationCollectionOfCollection();
        DCMVersionInformationCollection expResult = null;
        DCMVersionInformationCollection result = instance.getVersionInformationCollectio(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addVersionInformationCollection method, of class
     * DCMVersionInformationCollectionOfCollection.
     */
    @Test
    public void testAddVersionInformationCollection() {
        System.out.println("addVersionInformationCollection");
        DCMVersionInformationCollection inCollection = null;
        DCMVersionInformationCollectionOfCollection instance = new DCMVersionInformationCollectionOfCollection();
        int expResult = 3;
        int result = instance.addVersionInformationCollection(inCollection);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeLineOfBusiness method, of class
     * DCMVersionInformationCollectionOfCollection.
     */
    @Test
    public void testRemoveLineOfBusiness() {
        System.out.println("removeLineOfBusiness");
        int inIdentifier = 0;
        DCMVersionInformationCollectionOfCollection instance = new DCMVersionInformationCollectionOfCollection();
        int expResult = 5;
        int result = instance.removeLineOfBusiness(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersionInformationCollections method, of class
     * DCMVersionInformationCollectionOfCollection.
     */
    @Test
    public void testGetVersionInformationCollections() {
        System.out.println("getVersionInformationCollections");
        DCMVersionInformationCollectionOfCollection instance = new DCMVersionInformationCollectionOfCollection();
        Collection<DCMVersionInformationCollection> expResult = null;
        Collection<DCMVersionInformationCollection> result = instance.getVersionInformationCollections();
        assertEquals(0, result.size());
    }

    /**
     * Test of getGroupSystemInstanceIdentifiers method, of class
     * DCMVersionInformationCollectionOfCollection.
     */
    @Test
    public void testGetGroupSystemInstanceIdentifiers() {
        System.out.println("getGroupSystemInstanceIdentifiers");
        String inIdentifier = "";
        DCMVersionInformationCollectionOfCollection instance = new DCMVersionInformationCollectionOfCollection();
        Collection<String> expResult = null;
        Collection<String> result = instance.getGroupSystemInstanceIdentifiers(inIdentifier);
        assertEquals(0, result.size());
    }

    /**
     * Test of getVersionInformationCollection method, of class
     * DCMVersionInformationCollectionOfCollection.
     */
    @Test
    public void testGetVersionInformationCollection() {
        System.out.println("getVersionInformationCollection");
        String inSystemInstnaceIdentifier = "";
        DCMVersionInformationCollectionOfCollection instance = new DCMVersionInformationCollectionOfCollection();
        DCMVersionInformationCollection expResult = null;
        DCMVersionInformationCollection result = instance.getVersionInformationCollection(inSystemInstnaceIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
