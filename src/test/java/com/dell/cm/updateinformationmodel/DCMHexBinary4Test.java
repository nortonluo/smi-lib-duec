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
public class DCMHexBinary4Test {

    public DCMHexBinary4Test() {
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
     * Test of getValue method, of class DCMHexBinary4.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        DCMHexBinary4 instance = new DCMHexBinary4();
        String expResult = null;
        String result = instance.getValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setValue method, of class DCMHexBinary4.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        String inValue = "0";
        DCMHexBinary4 instance = new DCMHexBinary4();
        int expResult = 3;
        int result = instance.setValue(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
