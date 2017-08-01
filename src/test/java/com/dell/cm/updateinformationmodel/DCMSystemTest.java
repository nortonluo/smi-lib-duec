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
public class DCMSystemTest {

    public DCMSystemTest() {
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
     * Test of getName method, of class DCMSystem.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DCMSystem instance = new DCMSystem();
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setName method, of class DCMSystem.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        DCMI18NString inName = null;
        DCMSystem instance = new DCMSystem();
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getID method, of class DCMSystem.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        DCMSystem instance = new DCMSystem();
        instance.setID("ABCD");
        String expResult = "ABCD";
        String result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setID method, of class DCMSystem.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        String inID = "AAEE";
        DCMSystem instance = new DCMSystem();
        int expResult = 0;
        int result = instance.setID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getIDType method, of class DCMSystem.
     */
    @Test
    public void testGetIDType() {
        System.out.println("getIDType");
        DCMSystem instance = new DCMSystem();
        DCMSystemIDType expResult = DCMSystemIDType.BIOS;
        DCMSystemIDType result = instance.getIDType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setIDType method, of class DCMSystem.
     */
    @Test
    public void testSetIDType() {
        System.out.println("setIDType");
        DCMSystemIDType inIDType = null;
        DCMSystem instance = new DCMSystem();
        int expResult = 0;
        int result = instance.setIDType(inIDType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getLOBKey method, of class DCMSystem.
     */
    @Test
    public void testGetLOBKey() {
        System.out.println("getLOBKey");
        DCMSystem instance = new DCMSystem();
        int expResult = 3;
        int result = instance.getLOBKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setLOBKey method, of class DCMSystem.
     */
    @Test
    public void testSetLOBKey() {
        System.out.println("setLOBKey");
        int inKey = 0;
        DCMSystem instance = new DCMSystem();
        int expResult = 0;
        int result = instance.setLOBKey(inKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getUniqueIdentifier method, of class DCMSystem.
     */
    @Test
    public void testGetUniqueIdentifier() {
        System.out.println("getUniqueIdentifier");
        DCMSystem instance = new DCMSystem();
        instance.setID("ABCD");
        String expResult = "3BIOSABCD";
        String result = instance.getUniqueIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
