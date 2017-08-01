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
public class DCMRollbackInformationTest {

    public DCMRollbackInformationTest() {
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
     * Test of getRollbackIdentifier method, of class DCMRollbackInformation.
     */
    @Test
    public void testGetRollbackIdentifier() {
        System.out.println("getRollbackIdentifier");
        DCMRollbackInformation instance = new DCMRollbackInformation();
        DCMGUID expResult = null;
        DCMGUID result = instance.getRollbackIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setRollbackIdentifier method, of class DCMRollbackInformation.
     */
    @Test
    public void testSetRollbackIdentifier() {
        System.out.println("setRollbackIdentifier");
        DCMGUID inIdentifier = null;
        DCMRollbackInformation instance = new DCMRollbackInformation();
        int expResult = 0;
        int result = instance.setRollbackIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getRollbackVolume method, of class DCMRollbackInformation.
     */
    @Test
    public void testGetRollbackVolume() {
        System.out.println("getRollbackVolume");
        DCMRollbackInformation instance = new DCMRollbackInformation();
        String expResult = "";
        String result = instance.getRollbackVolume();
        assertNull(result);
    }

    /**
     * Test of setRollbackVolume method, of class DCMRollbackInformation.
     */
    @Test
    public void testSetRollbackVolume() {
        System.out.println("setRollbackVolume");
        String inVolume = "";
        DCMRollbackInformation instance = new DCMRollbackInformation();
        int expResult = 0;
        int result = instance.setRollbackVolume(inVolume);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getFMPWrapperIdentifier method, of class DCMRollbackInformation.
     */
    @Test
    public void testGetFMPWrapperIdentifier() {
        System.out.println("getFMPWrapperIdentifier");
        DCMRollbackInformation instance = new DCMRollbackInformation();
        DCMGUID expResult = null;
        DCMGUID result = instance.getFMPWrapperIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setFMPWrapperIdentifier method, of class DCMRollbackInformation.
     */
    @Test
    public void testSetFMPWrapperIdentifier() {
        System.out.println("setFMPWrapperIdentifier");
        DCMGUID inIdentifier = null;
        DCMRollbackInformation instance = new DCMRollbackInformation();
        int expResult = 0;
        int result = instance.setFMPWrapperIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getFMPWrapperVersion method, of class DCMRollbackInformation.
     */
    @Test
    public void testGetFMPWrapperVersion() {
        System.out.println("getFMPWrapperVersion");
        DCMRollbackInformation instance = new DCMRollbackInformation();
        String expResult = "";
        String result = instance.getFMPWrapperVersion();
        assertNull(result);

    }

    /**
     * Test of setFMPWrapperVersion method, of class DCMRollbackInformation.
     */
    @Test
    public void testSetFMPWrapperVersion() {
        System.out.println("setFMPWrapperVersion");
        String inVersion = "";
        DCMRollbackInformation instance = new DCMRollbackInformation();
        int expResult = 0;
        int result = instance.setFMPWrapperVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getFMPIdentifier method, of class DCMRollbackInformation.
     */
    @Test
    public void testGetFMPIdentifier() {
        System.out.println("getFMPIdentifier");
        DCMRollbackInformation instance = new DCMRollbackInformation();
        DCMGUID expResult = null;
        DCMGUID result = instance.getFMPIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setFMPIdentifier method, of class DCMRollbackInformation.
     */
    @Test
    public void testSetFMPIdentifier() {
        System.out.println("setFMPIdentifier");
        DCMGUID inIdentifier = null;
        DCMRollbackInformation instance = new DCMRollbackInformation();
        int expResult = 0;
        int result = instance.setFMPIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getRollbackTimeout method, of class DCMRollbackInformation.
     */
    @Test
    public void testGetRollbackTimeout() {
        System.out.println("getRollbackTimeout");
        DCMRollbackInformation instance = new DCMRollbackInformation();
        short expResult = 0;
        short result = instance.getRollbackTimeout();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setRollbackTimeout method, of class DCMRollbackInformation.
     */
    @Test
    public void testSetRollbackTimeout() {
        System.out.println("setRollbackTimeout");
        short inValue = 0;
        DCMRollbackInformation instance = new DCMRollbackInformation();
        int expResult = 0;
        int result = instance.setRollbackTimeout(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of impactsTPMMeasurements method, of class DCMRollbackInformation.
     */
    @Test
    public void testImpactsTPMMeasurements() {
        System.out.println("impactsTPMMeasurements");
        DCMRollbackInformation instance = new DCMRollbackInformation();
        boolean expResult = false;
        boolean result = instance.impactsTPMMeasurements();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setImpactsTPMMeasurements method, of class
     * DCMRollbackInformation.
     */
    @Test
    public void testSetImpactsTPMMeasurements() {
        System.out.println("setImpactsTPMMeasurements");
        boolean inValue = false;
        DCMRollbackInformation instance = new DCMRollbackInformation();
        int expResult = 0;
        int result = instance.setImpactsTPMMeasurements(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getFieldService method, of class DCMRollbackInformation.
     */
    @Test
    public void testGetFieldService() {
        System.out.println("getFieldService");
        DCMRollbackInformation instance = new DCMRollbackInformation();
        String expResult = "";
        String result = instance.getFieldService();
        assertNull(result);
    }

    /**
     * Test of setFieldService method, of class DCMRollbackInformation.
     */
    @Test
    public void testSetFieldService() {
        System.out.println("setFieldService");
        String inFieldService = "";
        DCMRollbackInformation instance = new DCMRollbackInformation();
        int expResult = 0;
        int result = instance.setFieldService(inFieldService);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
