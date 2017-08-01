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
public class DCMPnPInfoTest {

    public DCMPnPInfoTest() {
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
     * Test of getInfo method, of class DCMPnPInfo.
     */
    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        DCMPnPID X1 = new DCMPnPID();
        X1.setID("ABC");
        DCMPnPProductID X2 = new DCMPnPProductID();
        X2.setID("DCBA");
        DCMPnPInfo X3 = new DCMPnPInfo(X1, X2);
        DCMPnPDevice instance = new DCMPnPDevice(X3);
        String expResult = "ABCDCBA";
        String result = instance.getUniqueIdentifier();
        assertEquals(expResult, result);
    }

}
