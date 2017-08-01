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
public class DCMComponentKindCollectionTest {

    public DCMComponentKindCollectionTest() {
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
     * Test of getComponentKind method, of class DCMComponentKindCollection.
     */
    @Test
    public void testGetComponentKind() {
        System.out.println("getComponentKind");
        DCMComponentType inType = null;
        DCMComponentKindCollection instance = new DCMComponentKindCollection();
        DCMComponentKind expResult = null;
        DCMComponentKind result = instance.getComponentKind(inType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addComponentKind method, of class DCMComponentKindCollection.
     */
    @Test
    public void testAddComponentKind() {
        System.out.println("addComponentKind");
        DCMComponentType inType = null;
        DCMI18NString inName = null;
        DCMComponentKindCollection instance = new DCMComponentKindCollection();
        int expResult = 0;
        int result = instance.addComponentKind(inType, inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeComponentKind method, of class DCMComponentKindCollection.
     */
    @Test
    public void testRemoveComponentKind() {
        System.out.println("removeComponentKind");
        DCMComponentType inType = null;
        DCMComponentKindCollection instance = new DCMComponentKindCollection();
        int expResult = 5;
        int result = instance.removeComponentKind(inType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getComponentKinds method, of class DCMComponentKindCollection.
     */
    @Test
    public void testGetComponentKinds() {
        System.out.println("getComponentKinds");
        DCMComponentKindCollection instance = new DCMComponentKindCollection();
        Collection<DCMComponentKind> result = instance.getComponentKinds();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

}
