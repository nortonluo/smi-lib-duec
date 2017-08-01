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

import java.util.Collection;
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
public class DCMLineOfBusinessCollectionTest {

    public DCMLineOfBusinessCollectionTest() {
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
     * Test of getLineOfBusiness method, of class DCMLineOfBusinessCollection.
     */
    @Test
    public void testGetLineOfBusiness() {
        System.out.println("getLineOfBusiness");
        int inKey = 0;
        DCMLineOfBusinessCollection instance = new DCMLineOfBusinessCollection();
        DCMLineOfBusiness expResult = null;
        DCMLineOfBusiness result = instance.getLineOfBusiness(inKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addLineOfBusiness method, of class DCMLineOfBusinessCollection.
     */
    @Test
    public void testAddLineOfBusiness_DCMLineOfBusiness() {
        System.out.println("addLineOfBusiness");
        DCMLineOfBusiness inLOB = null;
        DCMLineOfBusinessCollection instance = new DCMLineOfBusinessCollection();
        int expResult = 3;
        int result = instance.addLineOfBusiness(inLOB);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addLineOfBusiness method, of class DCMLineOfBusinessCollection.
     */
    @Test
    public void testAddLineOfBusiness_3args() {
        System.out.println("addLineOfBusiness");
        int inKey = 0;
        String inPrefix = "";
        DCMI18NString inName = null;
        DCMLineOfBusinessCollection instance = new DCMLineOfBusinessCollection();
        int expResult = 0;
        int result = instance.addLineOfBusiness(inKey, inPrefix, inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeLineOfBusiness method, of class
     * DCMLineOfBusinessCollection.
     */
    @Test
    public void testRemoveLineOfBusiness() {
        System.out.println("removeLineOfBusiness");
        int inKey = 0;
        DCMLineOfBusinessCollection instance = new DCMLineOfBusinessCollection();
        int expResult = 5;
        int result = instance.removeLineOfBusiness(inKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getLinesOfBusiness method, of class DCMLineOfBusinessCollection.
     */
    @Test
    public void testGetLinesOfBusiness() {
        System.out.println("getLinesOfBusiness");
        DCMLineOfBusinessCollection instance = new DCMLineOfBusinessCollection();
        Collection<DCMLineOfBusiness> expResult = null;
        Collection<DCMLineOfBusiness> result = instance.getLinesOfBusiness();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

}
