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
public class DCMInstallInstructionsTest {

    public DCMInstallInstructionsTest() {
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
     * Test of getName method, of class DCMInstallInstructions.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DCMInstallInstructions instance = new DCMInstallInstructions();
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setName method, of class DCMInstallInstructions.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        DCMI18NString inName = null;
        DCMInstallInstructions instance = new DCMInstallInstructions();
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getFileName method, of class DCMInstallInstructions.
     */
    @Test
    public void testGetFileName() {
        System.out.println("getFileName");
        DCMInstallInstructions instance = new DCMInstallInstructions();
        String expResult = null;
        String result = instance.getFileName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setFileName method, of class DCMInstallInstructions.
     */
    @Test
    public void testSetFileName() {
        System.out.println("setFileName");
        String inName = "";
        DCMInstallInstructions instance = new DCMInstallInstructions();
        int expResult = 0;
        int result = instance.setFileName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getTypeCode method, of class DCMInstallInstructions.
     */
    @Test
    public void testGetTypeCode() {
        System.out.println("getTypeCode");
        DCMInstallInstructions instance = new DCMInstallInstructions();
        String expResult = null;
        String result = instance.getTypeCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTypeCode method, of class DCMInstallInstructions.
     */
    @Test
    public void testSetTypeCode() {
        System.out.println("setTypeCode");
        String inCode = "";
        DCMInstallInstructions instance = new DCMInstallInstructions();
        int expResult = 0;
        int result = instance.setTypeCode(inCode);
        assertEquals(expResult, result);
    }

}
