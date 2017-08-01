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
public class DCMCategoryCollectionTest {

    public DCMCategoryCollectionTest() {
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
     * Test of getCategory method, of class DCMCategoryCollection.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        String inCode = "";
        DCMCategoryCollection instance = new DCMCategoryCollection();
        DCMCategory expResult = null;
        DCMCategory result = instance.getCategory(inCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addCategory method, of class DCMCategoryCollection.
     */
    @Test
    public void testAddCategory() {
        System.out.println("addCategory");
        String inCode = "";
        DCMI18NString inName = null;
        DCMCategoryCollection instance = new DCMCategoryCollection();
        int expResult = 0;
        int result = instance.addCategory(inCode, inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeCategory method, of class DCMCategoryCollection.
     */
    @Test
    public void testRemoveCategory() {
        System.out.println("removeCategory");
        String inCode = "";
        DCMCategoryCollection instance = new DCMCategoryCollection();
        int expResult = 5;
        int result = instance.removeCategory(inCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getCategories method, of class DCMCategoryCollection.
     */
    @Test
    public void testGetCategories() {
        System.out.println("getCategories");
        DCMCategoryCollection instance = new DCMCategoryCollection();
        int expResult = 0;
        Collection<DCMCategory> result = instance.getCategories();
        assertEquals(expResult, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

}
