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
public class DCMPayloadUpdateDriverTest {

    public DCMPayloadUpdateDriverTest() {
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
     * Test of getName method, of class DCMPayloadUpdateDriver.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DCMPayloadUpdateDriver instance = new DCMPayloadUpdateDriver();
        String expResult = "";
        String result = instance.getName();
        assertNull(result);
    }

    /**
     * Test of setName method, of class DCMPayloadUpdateDriver.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String inName = "";
        DCMPayloadUpdateDriver instance = new DCMPayloadUpdateDriver();
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPath method, of class DCMPayloadUpdateDriver.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        DCMPayloadUpdateDriver instance = new DCMPayloadUpdateDriver();
        String expResult = "";
        String result = instance.getPath();
        assertNull(result);
    }

    /**
     * Test of setPath method, of class DCMPayloadUpdateDriver.
     */
    @Test
    public void testSetPath() {
        System.out.println("setPath");
        String inPath = "";
        DCMPayloadUpdateDriver instance = new DCMPayloadUpdateDriver();
        int expResult = 0;
        int result = instance.setPath(inPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersion method, of class DCMPayloadUpdateDriver.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        DCMPayloadUpdateDriver instance = new DCMPayloadUpdateDriver();
        String expResult = "";
        String result = instance.getVersion();
        assertNull(result);
    }

    /**
     * Test of setVersion method, of class DCMPayloadUpdateDriver.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        String inVersion = "";
        DCMPayloadUpdateDriver instance = new DCMPayloadUpdateDriver();
        int expResult = 0;
        int result = instance.setVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
