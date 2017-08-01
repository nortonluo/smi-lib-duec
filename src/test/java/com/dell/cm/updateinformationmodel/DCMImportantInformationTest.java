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

import java.net.URL;
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
public class DCMImportantInformationTest {

    public DCMImportantInformationTest() {
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
     * Test of getInformation method, of class DCMImportantInformation.
     */
    @Test
    public void testGetInformation() {
        System.out.println("getInformation");
        DCMImportantInformation instance = new DCMImportantInformation();
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getInformation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setInformation method, of class DCMImportantInformation.
     */
    @Test
    public void testSetInformation() {
        System.out.println("setInformation");
        DCMI18NString inInformation = null;
        DCMImportantInformation instance = new DCMImportantInformation();
        int expResult = 0;
        int result = instance.setInformation(inInformation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getURL method, of class DCMImportantInformation.
     */
    @Test
    public void testGetURL() {
        System.out.println("getURL");
        DCMImportantInformation instance = new DCMImportantInformation();
        URL expResult = null;
        URL result = instance.getURL();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setURL method, of class DCMImportantInformation.
     */
    @Test
    public void testSetURL() {
        System.out.println("setURL");
        URL inURL = null;
        DCMImportantInformation instance = new DCMImportantInformation();
        int expResult = 0;
        int result = instance.setURL(inURL);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
