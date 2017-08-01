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
public class DCMPCIDeviceTest {

    public DCMPCIDeviceTest() {
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
     * Test of getPCIInfo method, of class DCMPCIDevice.
     */
    @Test
    public void testGetPCIInfo() {
        System.out.println("getPCIInfo");
        DCMPCIDevice instance = new DCMPCIDevice(DCMPCIInfo.parse("PCI\\VEN_8086&DEV_8CBA&SUBSYS_85341043&REV_00\\3&11583659&0&B0"));
        DCMPCIInfo expResult = null;
        DCMPCIInfo result = instance.getPCIInfo();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getComponentID method, of class DCMPCIDevice.
     */
    @Test
    public void testGetComponentID() {
        System.out.println("getComponentID");
        DCMPCIDevice instance = new DCMPCIDevice(DCMPCIInfo.parse("PCI\\VEN_8086&DEV_8CBA&SUBSYS_85341043&REV_00\\3&11583659&0&B0"));
        long expResult = -1;
        long result = instance.getComponentID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setComponentID method, of class DCMPCIDevice.
     */
    @Test
    public void testSetComponentID() {
        System.out.println("setComponentID");
        long inID = 0L;
        DCMPCIDevice instance = new DCMPCIDevice(DCMPCIInfo.parse("PCI\\VEN_8086&DEV_8CBA&SUBSYS_85341043&REV_00\\3&11583659&0&B0"));
        int expResult = 0;
        int result = instance.setComponentID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getUniqueIdentifier method, of class DCMPCIDevice.
     */
    @Test
    public void testGetUniqueIdentifier() {
        System.out.println("getUniqueIdentifier");
        DCMPCIDevice instance = new DCMPCIDevice(DCMPCIInfo.parse("PCI\\VEN_0001&DEV_0002&SUBSYS_00030004&REV_00\\3&11583659&0&B0"));
        String expResult = "0001000200030004";
        String result = instance.getUniqueIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPayloadConfiguration method, of class DCMPCIDevice.
     */
    @Test
    public void testGetPayloadConfiguration() {
        System.out.println("getPayloadConfiguration");
        DCMPCIDevice instance = new DCMPCIDevice(DCMPCIInfo.parse("PCI\\VEN_8086&DEV_8CBA&SUBSYS_85341043&REV_00\\3&11583659&0&B0"));
        DCMPayloadConfiguration expResult = null;
        DCMPayloadConfiguration result = instance.getPayloadConfiguration();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setPayloadConfiguration method, of class DCMPCIDevice.
     */
    @Test
    public void testSetPayloadConfiguration() {
        System.out.println("setPayloadConfiguration");
        DCMPayloadConfiguration inConfiguration = null;
        DCMPCIDevice instance = new DCMPCIDevice(DCMPCIInfo.parse("PCI\\VEN_8086&DEV_8CBA&SUBSYS_85341043&REV_00\\3&11583659&0&B0"));
        int expResult = 0;
        int result = instance.setPayloadConfiguration(inConfiguration);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
