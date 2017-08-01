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
public class DCMPnPDeviceTest {

    public DCMPnPDeviceTest() {
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
     * Test of getPnPInfo method, of class DCMPnPDevice.
     */
    @Test
    public void testGetPnPInfo() {
        System.out.println("getPnPInfo");
        DCMPnPDevice instance = new DCMPnPDevice(new DCMPnPInfo(new DCMACPIID(), new DCMPnPProductID()));
        DCMPnPInfo expResult = null;
        DCMPnPInfo result = instance.getPnPInfo();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getComponentID method, of class DCMPnPDevice.
     */
    @Test
    public void testGetComponentID() {
        System.out.println("getComponentID");
        long expResult = -1;
        DCMPnPDevice instance = new DCMPnPDevice(new DCMPnPInfo(new DCMACPIID(), new DCMPnPProductID()));
        long result = instance.getComponentID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setComponentID method, of class DCMPnPDevice.
     */
    @Test
    public void testSetComponentID() {
        System.out.println("setComponentID");
        long inID = 0L;
        DCMPnPDevice instance = new DCMPnPDevice(new DCMPnPInfo(new DCMACPIID(), new DCMPnPProductID()));
        int expResult = 0;
        int result = instance.setComponentID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getUniqueIdentifier method, of class DCMPnPDevice.
     */
    @Test
    public void testGetUniqueIdentifier() {
        System.out.println("getUniqueIdentifier");
        DCMACPIID X1 = new DCMACPIID();
        X1.setID("ABCD");
        DCMPnPProductID X2 = new DCMPnPProductID();
        X2.setID("DCBA");
        DCMPnPInfo X3 = new DCMPnPInfo(X1, X2);
        DCMPnPDevice instance = new DCMPnPDevice(X3);
        String expResult = "ABCDDCBA";
        String result = instance.getUniqueIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
