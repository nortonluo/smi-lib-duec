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

import java.util.Locale;
import java.util.Set;
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
public class DCMI18NStringTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    public DCMI18NStringTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDefaultLocaleString method, of class DCMI18NString.
     */
    @Test
    public void testGetDefaultLocaleString() {
        System.out.println("getDefaultLocaleString");
        DCMI18NString instance = new DCMI18NString();
        String expResult = "";
        String result = instance.getDefaultLocaleString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDefaultLocaleString method, of class DCMI18NString.
     */
    @Test
    public void testSetDefaultLocaleString() {
        System.out.println("setDefaultLocaleString");
        String inString = "";
        DCMI18NString instance = new DCMI18NString();
        instance.setDefaultLocaleString(inString);
    }

    /**
     * Test of getLocaleString method, of class DCMI18NString.
     */
    @Test
    public void testGetLocaleString() {
        System.out.println("getLocaleString");
        Locale inLocale = null;
        DCMI18NString instance = new DCMI18NString();
        String expResult = "";
        String result = instance.getLocaleString(inLocale);
        assertEquals(expResult, result);
    }

    /**
     * Test of setLocaleString method, of class DCMI18NString.
     */
    @Test
    public void testSetLocaleString() {
        System.out.println("setLocaleString");
        Locale inLocale = null;
        String inString = "";
        DCMI18NString instance = new DCMI18NString();
        instance.setLocaleString(inLocale, inString);
    }

    /**
     * Test of getLocales method, of class DCMI18NString.
     */
    @Test
    public void testGetLocales() {
        System.out.println("getLocales");
        DCMI18NString instance = new DCMI18NString();
        Set<Locale> result = instance.getLocales();
        assertEquals(0, result.size());
    }

}
