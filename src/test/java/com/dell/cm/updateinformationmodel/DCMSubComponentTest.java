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
public class DCMSubComponentTest {

    public DCMSubComponentTest() {
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
     * Test of getName method, of class DCMSubComponent.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DCMSubComponent instance = new DCMSubComponent();
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setName method, of class DCMSubComponent.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        DCMI18NString inName = null;
        DCMSubComponent instance = new DCMSubComponent();
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSubComponentID method, of class DCMSubComponent.
     */
    @Test
    public void testGetSubComponentID() {
        System.out.println("getSubComponentID");
        DCMSubComponent instance = new DCMSubComponent();
        String expResult = "";
        String result = instance.getSubComponentID();
        assertNull(result);
    }

    /**
     * Test of setSubComponentID method, of class DCMSubComponent.
     */
    @Test
    public void testSetSubComponentID() {
        System.out.println("setSubComponentID");
        String inID = "";
        DCMSubComponent instance = new DCMSubComponent();
        int expResult = 0;
        int result = instance.setSubComponentID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersion method, of class DCMSubComponent.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        DCMSubComponent instance = new DCMSubComponent();
        String expResult = "";
        String result = instance.getVersion();
        assertNull(result);
    }

    /**
     * Test of setVersion method, of class DCMSubComponent.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        String inVersion = "";
        DCMSubComponent instance = new DCMSubComponent();
        int expResult = 0;
        int result = instance.setVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
