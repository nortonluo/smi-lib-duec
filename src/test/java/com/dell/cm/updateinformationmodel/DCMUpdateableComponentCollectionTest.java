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
public class DCMUpdateableComponentCollectionTest {

    public DCMUpdateableComponentCollectionTest() {
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
     * Test of getUpdateableComponent method, of class
     * DCMUpdateableComponentCollection.
     */
    @Test
    public void testGetUpdateableComponent() {
        System.out.println("getUpdateableComponent");
        String inIdentifier = "";
        DCMUpdateableComponentCollection instance = new DCMUpdateableComponentCollection();
        DCMUpdateableComponent expResult = null;
        DCMUpdateableComponent result = instance.getUpdateableComponent(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addUpdateableComponent method, of class
     * DCMUpdateableComponentCollection.
     */
    @Test
    public void testAddUpdateableComponent() {
        System.out.println("addUpdateableComponent");
        DCMUpdateableComponent inComponent = null;
        DCMUpdateableComponentCollection instance = new DCMUpdateableComponentCollection();
        int expResult = 3;
        int result = instance.addUpdateableComponent(inComponent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeUpdateableComponent method, of class
     * DCMUpdateableComponentCollection.
     */
    @Test
    public void testRemoveUpdateableComponent() {
        System.out.println("removeUpdateableComponent");
        String inIdentifier = "";
        DCMUpdateableComponentCollection instance = new DCMUpdateableComponentCollection();
        int expResult = 5;
        int result = instance.removeUpdateableComponent(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getUpdateableComponents method, of class
     * DCMUpdateableComponentCollection.
     */
    @Test
    public void testGetUpdateableComponents_0args() {
        System.out.println("getUpdateableComponents");
        DCMUpdateableComponentCollection instance = new DCMUpdateableComponentCollection();
        Collection<DCMUpdateableComponent> expResult = null;
        Collection<DCMUpdateableComponent> result = instance.getUpdateableComponents();
        assertEquals(0, result.size());
    }

    /**
     * Test of getUpdateableComponents method, of class
     * DCMUpdateableComponentCollection.
     */
    @Test
    public void testGetUpdateableComponents_String() {
        System.out.println("getUpdateableComponents");
        String inTargetIdentifier = "";
        DCMUpdateableComponentCollection instance = new DCMUpdateableComponentCollection();
        Collection<DCMUpdateableComponent> expResult = null;
        Collection<DCMUpdateableComponent> result = instance.getUpdateableComponents(inTargetIdentifier);
        assertEquals(0, result.size());
    }

}
