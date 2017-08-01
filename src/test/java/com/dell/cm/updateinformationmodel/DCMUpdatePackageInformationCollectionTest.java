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
public class DCMUpdatePackageInformationCollectionTest {

    public DCMUpdatePackageInformationCollectionTest() {
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
     * Test of getUpdatePackageInformation method, of class
     * DCMUpdatePackageInformationCollection.
     */
    @Test
    public void testGetUpdatePackageInformation() {
        System.out.println("getUpdatePackageInformation");
        String inIdentifier = "";
        DCMUpdatePackageInformationCollection instance = new DCMUpdatePackageInformationCollection();
        DCMUpdatePackageInformation expResult = null;
        DCMUpdatePackageInformation result = instance.getUpdatePackageInformation(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addUpdatePackageInformation method, of class
     * DCMUpdatePackageInformationCollection.
     */
    @Test
    public void testAddUpdatePackageInformation() {
        System.out.println("addUpdatePackageInformation");
        DCMUpdatePackageInformation inInformation = null;
        DCMUpdatePackageInformationCollection instance = new DCMUpdatePackageInformationCollection();
        int expResult = 3;
        int result = instance.addUpdatePackageInformation(inInformation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeUpdatePackageInformation method, of class
     * DCMUpdatePackageInformationCollection.
     */
    @Test
    public void testRemoveUpdatePackageInformation() {
        System.out.println("removeUpdatePackageInformation");
        String inIdentifier = "";
        DCMUpdatePackageInformationCollection instance = new DCMUpdatePackageInformationCollection();
        int expResult = 5;
        int result = instance.removeUpdatePackageInformation(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getUpdatePackageIdentifier method, of class
     * DCMUpdatePackageInformationCollection.
     */
    @Test
    public void testGetUpdatePackageIdentifier() {
        System.out.println("getUpdatePackageIdentifier");
        String inUpdatePackageFileName = "";
        DCMUpdatePackageInformationCollection instance = new DCMUpdatePackageInformationCollection();
        String expResult = null;
        String result = instance.getUpdatePackageIdentifier(inUpdatePackageFileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getUpdatePackages method, of class
     * DCMUpdatePackageInformationCollection.
     */
    @Test
    public void testGetUpdatePackages() {
        System.out.println("getUpdatePackages");
        DCMUpdatePackageInformationCollection instance = new DCMUpdatePackageInformationCollection();
        Collection<DCMUpdatePackageInformation> expResult = null;
        Collection<DCMUpdatePackageInformation> result = instance.getUpdatePackages();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

}
