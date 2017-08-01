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
package com.dell.cm.comparer;

import com.dell.cm.updateinformationmodel.DCMCriticality;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMI18NString;
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
public class DCMUpdateInformationTest {

    public DCMUpdateInformationTest() {
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
     * Test of getVersion method, of class DCMUpdateInformation.
     */
    @org.junit.Test
    public void testGetVersion() {
        System.out.println("getVersion");
        DCMUpdateInformation instance = new DCMUpdateInformation();
        instance.setVersion("1.1.1");
        String expResult = "1.1.1";
        String result = instance.getVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setVersion method, of class DCMUpdateInformation.
     */
    @org.junit.Test
    public void testSetVersion() {
        System.out.println("setVersion");
        String inVersion = "0";
        DCMUpdateInformation instance = new DCMUpdateInformation();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = instance.setVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPath method, of class DCMUpdateInformation.
     */
    @org.junit.Test
    public void testGetPath() {
        System.out.println("getPath");
        DCMUpdateInformation instance = new DCMUpdateInformation();
        String expResult = null;
        String result = instance.getPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPath method, of class DCMUpdateInformation.
     */
    @org.junit.Test
    public void testSetPath() {
        System.out.println("setPath");
        String inPath = "";
        DCMUpdateInformation instance = new DCMUpdateInformation();
        int expResult = 0;
        int result = instance.setPath(inPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class DCMUpdateInformation.
     */
    @org.junit.Test
    public void testGetName() {
        System.out.println("getName");
        DCMUpdateInformation instance = new DCMUpdateInformation();
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class DCMUpdateInformation.
     */
    @org.junit.Test
    public void testSetName() {
        System.out.println("setName");
        DCMI18NString inName = null;
        DCMUpdateInformation instance = new DCMUpdateInformation();
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCriticality method, of class DCMUpdateInformation.
     */
    @org.junit.Test
    public void testGetCriticality() {
        System.out.println("getCriticality");
        DCMUpdateInformation instance = new DCMUpdateInformation();
        DCMCriticality expResult = null;
        DCMCriticality result = instance.getCriticality();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCriticality method, of class DCMUpdateInformation.
     */
    @org.junit.Test
    public void testSetCriticality() {
        System.out.println("setCriticality");
        DCMCriticality inCriticality = null;
        DCMUpdateInformation instance = new DCMUpdateInformation();
        int expResult = 0;
        int result = instance.setCriticality(inCriticality);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUniqueIdentifier method, of class DCMUpdateInformation.
     */
    @org.junit.Test
    public void testGetUniqueIdentifier() {
        System.out.println("getUniqueIdentifier");
        DCMUpdateInformation instance = new DCMUpdateInformation();
        String expResult = null;
        String result = instance.getUniqueIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setUniqueIdentifier method, of class DCMUpdateInformation.
     */
    @org.junit.Test
    public void testSetUniqueIdentifier() {
        System.out.println("setUniqueIdentifier");
        String inIdentifier = "";
        DCMUpdateInformation instance = new DCMUpdateInformation();
        int expResult = 0;
        int result = instance.setUniqueIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
