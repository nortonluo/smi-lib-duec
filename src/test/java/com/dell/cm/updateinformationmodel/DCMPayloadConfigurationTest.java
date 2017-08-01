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

import java.util.Set;
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
public class DCMPayloadConfigurationTest {

    public DCMPayloadConfigurationTest() {
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
     * Test of addImage method, of class DCMPayloadConfiguration.
     */
    @Test
    public void testAddImage() {
        System.out.println("addImage");
        DCMPayloadImage inImage = null;
        DCMPayloadConfiguration instance = new DCMPayloadConfiguration();
        int expResult = 3;
        int result = instance.addImage(inImage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeImage method, of class DCMPayloadConfiguration.
     */
    @Test
    public void testRemoveImage() {
        System.out.println("removeImage");
        DCMPayloadImage inImage = null;
        DCMPayloadConfiguration instance = new DCMPayloadConfiguration();
        int expResult = 3;
        int result = instance.removeImage(inImage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getImages method, of class DCMPayloadConfiguration.
     */
    @Test
    public void testGetImages() {
        System.out.println("getImages");
        DCMPayloadConfiguration instance = new DCMPayloadConfiguration();
        Set<DCMPayloadImage> expResult = null;
        Set<DCMPayloadImage> result = instance.getImages();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getUpdateDriver method, of class DCMPayloadConfiguration.
     */
    @Test
    public void testGetUpdateDriver() {
        System.out.println("getUpdateDriver");
        DCMPayloadConfiguration instance = new DCMPayloadConfiguration();
        DCMPayloadUpdateDriver expResult = null;
        DCMPayloadUpdateDriver result = instance.getUpdateDriver();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setUpdateDriver method, of class DCMPayloadConfiguration.
     */
    @Test
    public void testSetUpdateDriver() {
        System.out.println("setUpdateDriver");
        DCMPayloadUpdateDriver inDriver = null;
        DCMPayloadConfiguration instance = new DCMPayloadConfiguration();
        int expResult = 0;
        int result = instance.setUpdateDriver(inDriver);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
