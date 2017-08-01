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
public class DCMVersionInformationTest {

    public DCMVersionInformationTest() {
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
     * Test of getUpdateableComponentIdentifier method, of class
     * DCMVersionInformation.
     */
    @Test
    public void testGetUpdateableComponentIdentifier() {
        System.out.println("getUpdateableComponentIdentifier");
        DCMVersionInformation instance = new DCMVersionInformation();
        String expResult = "";
        String result = instance.getUpdateableComponentIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setUpdateableComponentIdentifier method, of class
     * DCMVersionInformation.
     */
    @Test
    public void testSetUpdateableComponentIdentifier() {
        System.out.println("setUpdateableComponentIdentifier");
        String inIdentifier = "";
        DCMVersionInformation instance = new DCMVersionInformation();
        int expResult = 0;
        int result = instance.setUpdateableComponentIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getTargetIdentifier method, of class DCMVersionInformation.
     */
    @Test
    public void testGetTargetIdentifier() {
        System.out.println("getTargetIdentifier");
        DCMVersionInformation instance = new DCMVersionInformation();
        String expResult = "";
        String result = instance.getTargetIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setTargetIdentifier method, of class DCMVersionInformation.
     */
    @Test
    public void testSetTargetIdentifier() {
        System.out.println("setTargetIdentifier");
        String inIdentifier = "";
        DCMVersionInformation instance = new DCMVersionInformation();
        int expResult = 0;
        int result = instance.setTargetIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getTargetInstance method, of class DCMVersionInformation.
     */
    @Test
    public void testGetTargetInstance() {
        System.out.println("getTargetInstance");
        DCMVersionInformation instance = new DCMVersionInformation();
        String expResult = "";
        String result = instance.getTargetInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTargetInstance method, of class DCMVersionInformation.
     */
    @Test
    public void testSetTargetInstance() {
        System.out.println("setTargetInstance");
        String inInstance = "";
        DCMVersionInformation instance = new DCMVersionInformation();
        int expResult = 0;
        int result = instance.setTargetInstance(inInstance);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersion method, of class DCMVersionInformation.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        DCMVersionInformation instance = new DCMVersionInformation();
        String expResult = null;
        String result = instance.getVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setVersion method, of class DCMVersionInformation.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        String inVersion = "";
        DCMVersionInformation instance = new DCMVersionInformation();
        int expResult = 0;
        int result = instance.setVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of isForSameUpdateableComponent method, of class
     * DCMVersionInformation.
     */
    @Test
    public void testIsForSameUpdateableComponent() {
        System.out.println("isForSameUpdateableComponent");
        DCMVersionInformation inVersion = new DCMVersionInformation();
        DCMVersionInformation instance = new DCMVersionInformation();
        boolean expResult = true;
        boolean result = instance.isForSameUpdateableComponent(inVersion);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUniqueIdentifier method, of class DCMVersionInformation.
     */
    @Test
    public void testGetUniqueIdentifier() {
        System.out.println("getUniqueIdentifier");
        DCMVersionInformation instance = new DCMVersionInformation();
        String expResult = "";
        String result = instance.getUniqueIdentifier();
        assertEquals(expResult, result);
    }

}
