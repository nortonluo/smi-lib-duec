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
public class DCMLineOfBusinessTest {

    public DCMLineOfBusinessTest() {
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
     * Test of getKey method, of class DCMLineOfBusiness.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        DCMLineOfBusiness instance = new DCMLineOfBusiness();
        int expResult = 0;
        int result = instance.getKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setKey method, of class DCMLineOfBusiness.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        int inKey = 0;
        DCMLineOfBusiness instance = new DCMLineOfBusiness();
        int expResult = 0;
        int result = instance.setKey(inKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPrefix method, of class DCMLineOfBusiness.
     */
    @Test
    public void testGetPrefix() {
        System.out.println("getPrefix");
        DCMLineOfBusiness instance = new DCMLineOfBusiness();
        String expResult = null;
        String result = instance.getPrefix();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setPrefix method, of class DCMLineOfBusiness.
     */
    @Test
    public void testSetPrefix() {
        System.out.println("setPrefix");
        String inPrefix = "";
        DCMLineOfBusiness instance = new DCMLineOfBusiness();
        int expResult = 0;
        int result = instance.setPrefix(inPrefix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getName method, of class DCMLineOfBusiness.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DCMLineOfBusiness instance = new DCMLineOfBusiness();
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setName method, of class DCMLineOfBusiness.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        DCMI18NString inName = null;
        DCMLineOfBusiness instance = new DCMLineOfBusiness();
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
