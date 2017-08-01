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
public class DCMSystemCollectionTest {

    public DCMSystemCollectionTest() {
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
     * Test of getSystem method, of class DCMSystemCollection.
     */
    @Test
    public void testGetSystem() {
        System.out.println("getSystem");
        String inIdentifier = "";
        DCMSystemCollection instance = new DCMSystemCollection();
        DCMSystem expResult = null;
        DCMSystem result = instance.getSystem(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSystem method, of class DCMSystemCollection.
     */
    @Test
    public void testAddSystem() {
        System.out.println("addSystem");
        DCMSystem inSystem = null;
        DCMSystemCollection instance = new DCMSystemCollection();
        int expResult = 3;
        int result = instance.addSystem(inSystem);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSystem method, of class DCMSystemCollection.
     */
    @Test
    public void testRemoveSystem() {
        System.out.println("removeSystem");
        String inIdentifier = "";
        DCMSystemCollection instance = new DCMSystemCollection();
        int expResult = 5;
        int result = instance.removeSystem(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSystems method, of class DCMSystemCollection.
     */
    @Test
    public void testGetSystems() {
        System.out.println("getSystems");
        DCMSystemCollection instance = new DCMSystemCollection();
        Collection<DCMSystem> expResult = null;
        Collection<DCMSystem> result = instance.getSystems();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

}
