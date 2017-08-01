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
public class DCMInventoryCollectorInformationCollectionTest {

    public DCMInventoryCollectorInformationCollectionTest() {
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
     * Test of getInventoryCollectorInformation method, of class
     * DCMInventoryCollectorInformationCollection.
     */
    @Test
    public void testGetInventoryCollectorInformation() {
        System.out.println("getInventoryCollectorInformation");
        String inIdentifier = "";
        DCMInventoryCollectorInformationCollection instance = new DCMInventoryCollectorInformationCollection();
        DCMInventoryCollectorInformation expResult = null;
        DCMInventoryCollectorInformation result = instance.getInventoryCollectorInformation(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addInventoryCollectorInformation method, of class
     * DCMInventoryCollectorInformationCollection.
     */
    @Test
    public void testAddInventoryCollectorInformation() {
        System.out.println("addInventoryCollectorInformation");
        DCMInventoryCollectorInformation inComponent = null;
        DCMInventoryCollectorInformationCollection instance = new DCMInventoryCollectorInformationCollection();
        int expResult = 3;
        int result = instance.addInventoryCollectorInformation(inComponent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeInventoryCollectorInformation method, of class
     * DCMInventoryCollectorInformationCollection.
     */
    @Test
    public void testRemoveInventoryCollectorInformation() {
        System.out.println("removeInventoryCollectorInformation");
        String inType = "";
        DCMInventoryCollectorInformationCollection instance = new DCMInventoryCollectorInformationCollection();
        int expResult = 5;
        int result = instance.removeInventoryCollectorInformation(inType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getInventoryCollectorsInformation method, of class
     * DCMInventoryCollectorInformationCollection.
     */
    @Test
    public void testGetInventoryCollectorsInformation() {
        System.out.println("getInventoryCollectorsInformation");
        DCMInventoryCollectorInformationCollection instance = new DCMInventoryCollectorInformationCollection();
        Collection<DCMInventoryCollectorInformation> expResult = null;
        Collection<DCMInventoryCollectorInformation> result = instance.getInventoryCollectorsInformation();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

}
