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
public class DCMTargetCollectionTest {

    public DCMTargetCollectionTest() {
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
     * Test of getTarget method, of class DCMTargetCollection.
     */
    @Test
    public void testGetTarget() {
        System.out.println("getTarget");
        String inIdentifier = "";
        DCMTargetCollection instance = new DCMTargetCollection();
        DCMBaseTarget expResult = null;
        DCMBaseTarget result = instance.getTarget(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addTarget method, of class DCMTargetCollection.
     */
    @Test
    public void testAddTarget() {
        System.out.println("addTarget");
        DCMBaseTarget inTarget = null;
        DCMTargetCollection instance = new DCMTargetCollection();
        int expResult = 3;
        int result = instance.addTarget(inTarget);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeTarget method, of class DCMTargetCollection.
     */
    @Test
    public void testRemoveTarget() {
        System.out.println("removeTarget");
        String inIdentifier = "";
        DCMTargetCollection instance = new DCMTargetCollection();
        int expResult = 5;
        int result = instance.removeTarget(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getTargets method, of class DCMTargetCollection.
     */
    @Test
    public void testGetTargets() {
        System.out.println("getTargets");
        DCMTargetCollection instance = new DCMTargetCollection();
        Collection<DCMBaseTarget> expResult = null;
        Collection<DCMBaseTarget> result = instance.getTargets();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

}
