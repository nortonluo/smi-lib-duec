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

import java.util.HashSet;
import java.util.Locale;
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
public class DCMOperatingSystemTest {

    public DCMOperatingSystemTest() {
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
     * Test of getName method, of class DCMOperatingSystem.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setName method, of class DCMOperatingSystem.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        DCMI18NString inName = null;
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSupportForLocale method, of class DCMOperatingSystem.
     */
    @Test
    public void testAddSupportForLocale() {
        System.out.println("addSupportForLocale");
        Locale inLocale = null;
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.addSupportForLocale(inLocale);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSupportForLocale method, of class DCMOperatingSystem.
     */
    @Test
    public void testRemoveSupportForLocale() {
        System.out.println("removeSupportForLocale");
        Locale inLocale = null;
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 5;
        int result = instance.removeSupportForLocale(inLocale);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSupportedLocales method, of class DCMOperatingSystem.
     */
    @Test
    public void testGetSupportedLocales() {
        System.out.println("getSupportedLocales");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        HashSet<Locale> expResult = null;
        HashSet<Locale> result = instance.getSupportedLocales();
        assertNotNull(result);

    }

    /**
     * Test of getCode method, of class DCMOperatingSystem.
     */
    @Test
    public void testGetCode() {
        System.out.println("getCode");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        String expResult = "";
        String result = instance.getCode();
        assertNull(result);
    }

    /**
     * Test of setCode method, of class DCMOperatingSystem.
     */
    @Test
    public void testSetCode() {
        System.out.println("setCode");
        String inCode = "";
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.setCode(inCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVendor method, of class DCMOperatingSystem.
     */
    @Test
    public void testGetVendor() {
        System.out.println("getVendor");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        String expResult = "";
        String result = instance.getVendor();
        assertNull(result);
    }

    /**
     * Test of setVendor method, of class DCMOperatingSystem.
     */
    @Test
    public void testSetVendor() {
        System.out.println("setVendor");
        String inVendor = "";
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.setVendor(inVendor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getArchitecture method, of class DCMOperatingSystem.
     */
    @Test
    public void testGetArchitecture() {
        System.out.println("getArchitecture");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        DCMOSArchitecture expResult = null;
        DCMOSArchitecture result = instance.getArchitecture();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setArchitecture method, of class DCMOperatingSystem.
     */
    @Test
    public void testSetArchitecture() {
        System.out.println("setArchitecture");
        DCMOSArchitecture inArchitecture = null;
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.setArchitecture(inArchitecture);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getType method, of class DCMOperatingSystem.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        DCMOSType expResult = null;
        DCMOSType result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setType method, of class DCMOperatingSystem.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        DCMOSType inType = null;
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.setType(inType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getMajorVersion method, of class DCMOperatingSystem.
     */
    @Test
    public void testGetMajorVersion() {
        System.out.println("getMajorVersion");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        String expResult = "";
        String result = instance.getMajorVersion();
        assertEquals(expResult,result);
    }

    /**
     * Test of setMajorVersion method, of class DCMOperatingSystem.
     */
    @Test
    public void testSetMajorVersion() {
        System.out.println("setMajorVersion");
        String inVersion = "";
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.setMajorVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getMinorVersion method, of class DCMOperatingSystem.
     */
    @Test
    public void testGetMinorVersion() {
        System.out.println("getMinorVersion");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        String expResult = "";
        String result = instance.getMinorVersion();
        assertEquals(expResult,result);
    }

    /**
     * Test of setMinorVersion method, of class DCMOperatingSystem.
     */
    @Test
    public void testSetMinorVersion() {
        System.out.println("setMinorVersion");
        String inVersion = "";
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.setMinorVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getServicePackMajorVersion method, of class DCMOperatingSystem.
     */
    @Test
    public void testGetServicePackMajorVersion() {
        System.out.println("getServicePackMajorVersion");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        String expResult = "";
        String result = instance.getServicePackMajorVersion();
        assertEquals(expResult,result);
    }

    /**
     * Test of setServicePackMajorVersion method, of class DCMOperatingSystem.
     */
    @Test
    public void testSetServicePackMajorVersion() {
        System.out.println("setServicePackMajorVersion");
        String inVersion = "";
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.setServicePackMajorVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getServicePackMinorVersion method, of class DCMOperatingSystem.
     */
    @Test
    public void testGetServicePackMinorVersion() {
        System.out.println("getServicePackMinorVersion");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        String expResult = "";
        String result = instance.getServicePackMinorVersion();
        assertEquals(expResult,result);
    }

    /**
     * Test of setServicePackMinorVersion method, of class DCMOperatingSystem.
     */
    @Test
    public void testSetServicePackMinorVersion() {
        System.out.println("setServicePackMinorVersion");
        String inVersion = "";
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.setServicePackMinorVersion(inVersion);
        assertEquals(expResult, result);
    }

    /**
     * Test of isForPreInstallEnvironment method, of class DCMOperatingSystem.
     */
    @Test
    public void testIsForPreInstallEnvironment() {
        System.out.println("isForPreInstallEnvironment");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        boolean expResult = false;
        boolean result = instance.isForPreInstallEnvironment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setForPreInstallEnvironment method, of class DCMOperatingSystem.
     */
    @Test
    public void testSetForPreInstallEnvironment() {
        System.out.println("setForPreInstallEnvironment");
        boolean inValue = false;
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.setForPreInstallEnvironment(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getFlavorMask method, of class DCMOperatingSystem.
     */
    @Test
    public void testGetFlavorMask() {
        System.out.println("getFlavorMask");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.getFlavorMask();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setFlavorMask method, of class DCMOperatingSystem.
     */
    @Test
    public void testSetFlavorMask() {
        System.out.println("setFlavorMask");
        int inMask = 0;
        DCMOperatingSystem instance = new DCMOperatingSystem();
        int expResult = 0;
        int result = instance.setFlavorMask(inMask);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getUniqueIdentifier method, of class DCMOperatingSystem.
     */
    @Test
    public void testGetUniqueIdentifier() {
        System.out.println("getUniqueIdentifier");
        DCMOperatingSystem instance = new DCMOperatingSystem();
        String expResult = "00";
        String result = instance.getUniqueIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
