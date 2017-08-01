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
import java.util.HashMap;
import java.util.HashSet;
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
public class DCMOperatingSystemCollectionTest {

    public DCMOperatingSystemCollectionTest() {
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
     * Test of getSystem method, of class DCMOperatingSystemCollection.
     */
    @Test
    public void testGetSystem() {
        System.out.println("getSystem");
        String inIdentifier = "";
        DCMOperatingSystemCollection instance = new DCMOperatingSystemCollection();
        DCMOperatingSystem expResult = null;
        DCMOperatingSystem result = instance.getSystem(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addOperatingSystem method, of class DCMOperatingSystemCollection.
     */
    @Test
    public void testAddOperatingSystem() {
        System.out.println("addOperatingSystem");
        DCMOperatingSystem inOperatingSystem = null;
        DCMOperatingSystemCollection instance = new DCMOperatingSystemCollection();
        int expResult = 3;
        int result = instance.addOperatingSystem(inOperatingSystem);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSystem method, of class DCMOperatingSystemCollection.
     */
    @Test
    public void testRemoveSystem() {
        System.out.println("removeSystem");
        String inIdentifier = "";
        DCMOperatingSystemCollection instance = new DCMOperatingSystemCollection();
        int expResult = 5;
        int result = instance.removeSystem(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getOperatingSystems method, of class
     * DCMOperatingSystemCollection.
     */
    @Test
    public void testGetOperatingSystems() {
        System.out.println("getOperatingSystems");
        DCMOperatingSystemCollection instance = new DCMOperatingSystemCollection();
        Collection<DCMOperatingSystem> result = instance.getOperatingSystems();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

}
