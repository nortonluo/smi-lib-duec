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
public class DCMOSTypeTest {

    public DCMOSTypeTest() {
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
     * Test of values method, of class DCMOSType.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        DCMOSType[] result = DCMOSType.values();
        assertEquals(4, result.length);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getEnumeration method, of class DCMOSType.
     */
    @Test
    public void testGetEnumeration() {
        System.out.println("getEnumeration");
        String inValue = DCMConstants.CLIENT_ENUM;
        DCMOSType expResult = DCMOSType.CLIENT;
        DCMOSType result = DCMOSType.getEnumeration(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of toString method, of class DCMOSType.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DCMOSType inType = DCMOSType.CLIENT;
        String expResult = DCMConstants.CLIENT_ENUM;
        String result = DCMOSType.toString(inType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
