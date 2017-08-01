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
public class DCMMSIApplicationTest {

    public DCMMSIApplicationTest() {
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
     * Test of getUpdateCode method, of class DCMMSIApplication.
     */
    @Test
    public void testGetUpdateCode() {
        System.out.println("getUpdateCode");
        DCMMSIApplication instance = new DCMMSIApplication(new DCMGUID());
        DCMGUID result = instance.getUpdateCode();
        assertNotNull(result);
    }

    /**
     * Test of getComponentID method, of class DCMMSIApplication.
     */
    @Test
    public void testGetComponentID() {
        System.out.println("getComponentID");
        DCMMSIApplication instance = new DCMMSIApplication(new DCMGUID());
        long expResult = -1;
        long result = instance.getComponentID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setComponentID method, of class DCMMSIApplication.
     */
    @Test
    public void testSetComponentID() {
        System.out.println("setComponentID");
        long inID = 0L;
        DCMMSIApplication instance = new DCMMSIApplication(new DCMGUID());
        int expResult = 0;
        int result = instance.setComponentID(inID);
        assertEquals(expResult, result);

    }

    /**
     * Test of getUniqueIdentifier method, of class DCMMSIApplication.
     */
    @Test
    public void testGetUniqueIdentifier() {
        System.out.println("getUniqueIdentifier");
        DCMMSIApplication instance = new DCMMSIApplication(new DCMGUID());
        String expResult = "01010101-0202-0303-0404-050505050505";
        instance.getUpdateCode().setID(expResult);
        String result = instance.getUniqueIdentifier();
        assertEquals(expResult, result);
    }

}
