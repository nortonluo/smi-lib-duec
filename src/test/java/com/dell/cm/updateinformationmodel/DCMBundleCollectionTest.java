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
public class DCMBundleCollectionTest {

    public DCMBundleCollectionTest() {
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
     * Test of getLineOfBusiness method, of class DCMBundleCollection.
     */
    @Test
    public void testGetLineOfBusiness() {
        System.out.println("getLineOfBusiness");
        String inIdentifier = "";
        DCMBundleCollection instance = new DCMBundleCollection();
        DCMBundle expResult = null;
        DCMBundle result = instance.getBundle(inIdentifier);
        assertEquals(expResult, result);
    }

    /**
     * Test of addBundle method, of class DCMBundleCollection.
     */
    @Test
    public void testAddBundle() {
        System.out.println("addBundle");
        DCMBundle inBundle = null;
        DCMBundleCollection instance = new DCMBundleCollection();
        int expResult = 3;
        int result = instance.addBundle(inBundle);
        assertEquals(expResult, result);

    }

    /**
     * Test of removeBundle method, of class DCMBundleCollection.
     */
    @Test
    public void testRemoveBundle() {
        System.out.println("removeBundle");
        String inIdentifier = "";
        DCMBundleCollection instance = new DCMBundleCollection();
        int expResult = 3;
        int result = instance.removeBundle(inIdentifier);
        assertEquals(expResult, result);

    }

    /**
     * Test of getBundles method, of class DCMBundleCollection.
     */
    @Test
    public void testGetBundles() {
        System.out.println("getBundles");
        DCMBundleCollection instance = new DCMBundleCollection();
        int expResult = 0;
        Collection<DCMBundle> result = instance.getBundles();
        assertEquals(expResult, result.size());

    }

    /**
     * Test of getBundleIdentifiers method, of class DCMBundleCollection.
     */
    @Test
    public void testGetBundleIdentifiers() {
        System.out.println("getBundleIdentifiers");
        String inSystemIdentifier = "";
        DCMBundleType inBundleType = null;
        DCMBundleCollection instance = new DCMBundleCollection();
        int expResult = 0;
        Collection<String> result = instance.getBundleIdentifiers(inSystemIdentifier, inBundleType);
        assertEquals(expResult, result.size());

    }

    /**
     * Test of getBundle method, of class DCMBundleCollection.
     */
    @Test
    public void testGetBundle() {
        System.out.println("getBundle");
        String inIdentifier = "";
        DCMBundleCollection instance = new DCMBundleCollection();
        DCMBundle expResult = null;
        DCMBundle result = instance.getBundle(inIdentifier);
        assertEquals(expResult, result);

    }

}
