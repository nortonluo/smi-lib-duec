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
public class DCMFMPWrapperInformationTest {

    public DCMFMPWrapperInformationTest() {
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
     * Test of isInventorySupported method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testIsInventorySupported() {
        System.out.println("isInventorySupported");
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        boolean expResult = false;
        boolean result = instance.isInventorySupported();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setInventorySupport method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testSetInventorySupport() {
        System.out.println("setInventorySupport");
        boolean inValue = false;
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        int expResult = 0;
        int result = instance.setInventorySupport(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getInventorySource method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testGetInventorySource() {
        System.out.println("getInventorySource");
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        String expResult = null;
        String result = instance.getInventorySource();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setInventorySource method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testSetInventorySource() {
        System.out.println("setInventorySource");
        String inSource = "";
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        int expResult = 0;
        int result = instance.setInventorySource(inSource);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of isUpdateSupported method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testIsUpdateSupported() {
        System.out.println("isUpdateSupported");
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        boolean expResult = false;
        boolean result = instance.isUpdateSupported();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setUpdateSupport method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testSetUpdateSupport() {
        System.out.println("setUpdateSupport");
        boolean inValue = false;
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        int expResult = 0;
        int result = instance.setUpdateSupport(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of isRollbackupported method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testIsRollbackupported() {
        System.out.println("isRollbackupported");
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        boolean expResult = false;
        boolean result = instance.isRollbackupported();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setRollbackSupport method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testSetRollbackSupport() {
        System.out.println("setRollbackSupport");
        boolean inValue = false;
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        int expResult = 0;
        int result = instance.setRollbackSupport(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getIdentifier method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testGetIdentifier() {
        System.out.println("getIdentifier");
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        DCMGUID expResult = null;
        DCMGUID result = instance.getIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setIdentifier method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testSetIdentifier() {
        System.out.println("setIdentifier");
        DCMGUID inIdentifier = null;
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        int expResult = 0;
        int result = instance.setIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getName method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setName method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String inName = "";
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getFilePath method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testGetFilePath() {
        System.out.println("getFilePath");
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        String expResult = null;
        String result = instance.getFilePath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setFilePath method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testSetFilePath() {
        System.out.println("setFilePath");
        String inPath = "";
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        int expResult = 0;
        int result = instance.setFilePath(inPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getDriverFileName method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testGetDriverFileName() {
        System.out.println("getDriverFileName");
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        String expResult = null;
        String result = instance.getDriverFileName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setDriverFileName method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testSetDriverFileName() {
        System.out.println("setDriverFileName");
        String inName = "";
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        int expResult = 0;
        int result = instance.setDriverFileName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of isDigitallySigned method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testIsDigitallySigned() {
        System.out.println("isDigitallySigned");
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        boolean expResult = false;
        boolean result = instance.isDigitallySigned();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setDigitallySigned method, of class DCMFMPWrapperInformation.
     */
    @Test
    public void testSetDigitallySigned() {
        System.out.println("setDigitallySigned");
        boolean inValue = false;
        DCMFMPWrapperInformation instance = new DCMFMPWrapperInformation();
        int expResult = 0;
        int result = instance.setDigitallySigned(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
