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
public class DCMDeviceApplicabilityTest {

    public DCMDeviceApplicabilityTest() {
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
     * Test of getMinimumVersion method, of class DCMDeviceApplicability.
     */
    @Test
    public void testGetMinimumVersion() {
        System.out.println("getMinimumVersion");
        DCMDeviceApplicability instance = new DCMDeviceApplicability();
        String expResult = null;
        String result = instance.getMinimumVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setMinimumVersion method, of class DCMDeviceApplicability.
     */
    @Test
    public void testSetMinimumVersion() {
        System.out.println("setMinimumVersion");
        String inVersion = "";
        DCMDeviceApplicability instance = new DCMDeviceApplicability();
        int expResult = 0;
        int result = instance.setMinimumVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getMaximumVersion method, of class DCMDeviceApplicability.
     */
    @Test
    public void testGetMaximumVersion() {
        System.out.println("getMaximumVersion");
        DCMDeviceApplicability instance = new DCMDeviceApplicability();
        String expResult = null;
        String result = instance.getMaximumVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setMaximumVersion method, of class DCMDeviceApplicability.
     */
    @Test
    public void testSetMaximumVersion() {
        System.out.println("setMaximumVersion");
        String inVersion = "";
        DCMDeviceApplicability instance = new DCMDeviceApplicability();
        int expResult = 0;
        int result = instance.setMaximumVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
