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
public class DCMSystemInstanceCollectionTest {

    public DCMSystemInstanceCollectionTest() {
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
     * Test of getSystemInstance method, of class DCMSystemInstanceCollection.
     */
    @Test
    public void testGetSystemInstance() {
        System.out.println("getSystemInstance");
        String inServiceTag = "";
        DCMSystemInstanceCollection instance = new DCMSystemInstanceCollection();
        DCMSystemInstance expResult = null;
        DCMSystemInstance result = instance.getSystemInstance(inServiceTag);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSystemInstance method, of class DCMSystemInstanceCollection.
     */
    @Test
    public void testAddSystemInstance() {
        System.out.println("addSystemInstance");
        DCMSystemInstance inInstance = null;
        DCMSystemInstanceCollection instance = new DCMSystemInstanceCollection();
        int expResult = 3;
        int result = instance.addSystemInstance(inInstance);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSystemInstance method, of class
     * DCMSystemInstanceCollection.
     */
    @Test
    public void testRemoveSystemInstance() {
        System.out.println("removeSystemInstance");
        String inServiceTag = "";
        DCMSystemInstanceCollection instance = new DCMSystemInstanceCollection();
        int expResult = 5;
        int result = instance.removeSystemInstance(inServiceTag);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSystemInstances method, of class DCMSystemInstanceCollection.
     */
    @Test
    public void testGetSystemInstances() {
        System.out.println("getSystemInstances");
        DCMSystemInstanceCollection instance = new DCMSystemInstanceCollection();
        Collection<DCMSystemInstance> expResult = null;
        Collection<DCMSystemInstance> result = instance.getSystemInstances();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of hasSystemInstance method, of class DCMSystemInstanceCollection.
     */
    @Test
    public void testHasSystemInstance() {
        System.out.println("hasSystemInstance");
        String inIdentifier = "";
        DCMSystemInstanceCollection instance = new DCMSystemInstanceCollection();
        boolean expResult = false;
        boolean result = instance.hasSystemInstance(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
