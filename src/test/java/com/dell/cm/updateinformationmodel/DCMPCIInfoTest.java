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
public class DCMPCIInfoTest {

    public DCMPCIInfoTest() {
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
     * Test of getInfo method, of class DCMPCIInfo.
     */
    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        DCMPCIInfo instance = DCMPCIInfo.parse("PCI\\VEN_0001&DEV_0002&SUBSYS_00030004&REV_00\\3&11583659&0&B0");
        String expResult = "0001000200030004";
        String result = instance.getInfo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVendorID method, of class DCMPCIInfo.
     */
    @Test
    public void testGetVendorID() {
        System.out.println("getVendorID");
        DCMPCIInfo instance = DCMPCIInfo.parse("PCI\\VEN_0001&DEV_0002&SUBSYS_00030004&REV_00\\3&11583659&0&B0");
        String expResult = "0001";
        String result = instance.getVendorID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getDeviceID method, of class DCMPCIInfo.
     */
    @Test
    public void testGetDeviceID() {
        System.out.println("getDeviceID");
        DCMPCIInfo instance = DCMPCIInfo.parse("PCI\\VEN_0001&DEV_0002&SUBSYS_00030004&REV_00\\3&11583659&0&B0");
        String expResult = "0002";
        String result = instance.getDeviceID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSubVendorID method, of class DCMPCIInfo.
     */
    @Test
    public void testGetSubVendorID() {
        System.out.println("getSubVendorID");
        DCMPCIInfo instance = DCMPCIInfo.parse("PCI\\VEN_0001&DEV_0002&SUBSYS_00030004&REV_00\\3&11583659&0&B0");
        String expResult = "0003";
        String result = instance.getSubVendorID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSubDeviceID method, of class DCMPCIInfo.
     */
    @Test
    public void testGetSubDeviceID() {
        System.out.println("getSubDeviceID");
        DCMPCIInfo instance = DCMPCIInfo.parse("PCI\\VEN_0001&DEV_0002&SUBSYS_00030004&REV_00\\3&11583659&0&B0");
        String expResult = "0004";
        String result = instance.getSubDeviceID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
