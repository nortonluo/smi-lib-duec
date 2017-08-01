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
public class DCMPayloadImageTest {

    public DCMPayloadImageTest() {
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
     * Test of getType method, of class DCMPayloadImage.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        DCMPayloadImage instance = new DCMPayloadImage();
        String expResult = "";
        String result = instance.getType();
        assertNull(result);

    }

    /**
     * Test of setType method, of class DCMPayloadImage.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String inType = "";
        DCMPayloadImage instance = new DCMPayloadImage();
        int expResult = 0;
        int result = instance.setType(inType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getID method, of class DCMPayloadImage.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        DCMPayloadImage instance = new DCMPayloadImage();
        DCMGUID expResult = null;
        DCMGUID result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setID method, of class DCMPayloadImage.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        DCMGUID inID = null;
        DCMPayloadImage instance = new DCMPayloadImage();
        int expResult = 0;
        int result = instance.setID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersion method, of class DCMPayloadImage.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        DCMPayloadImage instance = new DCMPayloadImage();
        String expResult = "";
        String result = instance.getVersion();
        assertNull(result);
    }

    /**
     * Test of setVersion method, of class DCMPayloadImage.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        String inVersion = "";
        DCMPayloadImage instance = new DCMPayloadImage();
        int expResult = 0;
        int result = instance.setVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getFileName method, of class DCMPayloadImage.
     */
    @Test
    public void testGetFileName() {
        System.out.println("getFileName");
        DCMPayloadImage instance = new DCMPayloadImage();
        String expResult = "";
        String result = instance.getFileName();
        assertNull(result);
    }

    /**
     * Test of setFileName method, of class DCMPayloadImage.
     */
    @Test
    public void testSetFileName() {
        System.out.println("setFileName");
        String inName = "";
        DCMPayloadImage instance = new DCMPayloadImage();
        int expResult = 0;
        int result = instance.setFileName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of ShouldSkip method, of class DCMPayloadImage.
     */
    @Test
    public void testShouldSkip() {
        System.out.println("ShouldSkip");
        DCMPayloadImage instance = new DCMPayloadImage();
        boolean expResult = false;
        boolean result = instance.ShouldSkip();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setShouldSkip method, of class DCMPayloadImage.
     */
    @Test
    public void testSetShouldSkip() {
        System.out.println("setShouldSkip");
        boolean inValue = false;
        DCMPayloadImage instance = new DCMPayloadImage();
        int expResult = 0;
        int result = instance.setShouldSkip(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
