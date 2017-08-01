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

import java.util.Date;
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
public class DCMBundleTest {

    public DCMBundleTest() {
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
     * Test of getType method, of class DCMBundle.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        DCMBundleType expResult = DCMBundleType.BTW32;
        DCMBundleType result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getName method, of class DCMBundle.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setName method, of class DCMBundle.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        DCMI18NString inName = null;
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getDescription method, of class DCMBundle.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setDescription method, of class DCMBundle.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        DCMI18NString inDescription = null;
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setDescription(inDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getTargetSystemIdentifiers method, of class DCMBundle.
     */
    @Test
    public void testGetTargetSystemIdentifiers() {
        System.out.println("getTargetSystemIdentifiers");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        HashSet<String> expResult = new HashSet<String>();
        HashSet<String> result = instance.getTargetSystemIdentifiers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addTargetSystemIdentifier method, of class DCMBundle.
     */
    @Test
    public void testAddTargetSystemIdentifier() {
        System.out.println("addTargetSystemIdentifier");
        String inIdentifier = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.addTargetSystemIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeTargetSystemIdentifier method, of class DCMBundle.
     */
    @Test
    public void testRemoveTargetSystemIdentifier() {
        System.out.println("removeTargetSystemIdentifier");
        String inIdentifier = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 5;
        int result = instance.removeTargetSystemIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of supportsSystem method, of class DCMBundle.
     */
    @Test
    public void testSupportsSystem() {
        System.out.println("supportsSystem");
        String inIdentifier = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        boolean expResult = false;
        boolean result = instance.supportsSystem(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getTargetOperatingSystemIdentifiers method, of class DCMBundle.
     */
    @Test
    public void testGetTargetOperatingSystemIdentifiers() {
        System.out.println("getTargetOperatingSystemIdentifiers");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        HashSet<String> expResult = new HashSet<String>();
        HashSet<String> result = instance.getTargetOperatingSystemIdentifiers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addTargetOperatingSystemIdentifier method, of class DCMBundle.
     */
    @Test
    public void testAddTargetOperatingSystemIdentifier() {
        System.out.println("addTargetOperatingSystemIdentifier");
        String inIdentifier = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.addTargetOperatingSystemIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeTargetOperatingSystemIdentifier method, of class DCMBundle.
     */
    @Test
    public void testRemoveTargetOperatingSystemIdentifier() {
        System.out.println("removeTargetOperatingSystemIdentifier");
        String inIdentifier = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 5;
        int result = instance.removeTargetOperatingSystemIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of supportsOperatingSystem method, of class DCMBundle.
     */
    @Test
    public void testSupportsOperatingSystem() {
        System.out.println("supportsOperatingSystem");
        String inIdentifier = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        boolean expResult = false;
        boolean result = instance.supportsOperatingSystem(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getRevisionHistory method, of class DCMBundle.
     */
    @Test
    public void testGetRevisionHistory() {
        System.out.println("getRevisionHistory");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getRevisionHistory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setRevisionHistory method, of class DCMBundle.
     */
    @Test
    public void testSetRevisionHistory() {
        System.out.println("setRevisionHistory");
        DCMI18NString inHistory = null;
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setRevisionHistory(inHistory);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getImportantInformation method, of class DCMBundle.
     */
    @Test
    public void testGetImportantInformation() {
        System.out.println("getImportantInformation");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        DCMImportantInformation expResult = null;
        DCMImportantInformation result = instance.getImportantInformation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setImportantInformation method, of class DCMBundle.
     */
    @Test
    public void testSetImportantInformation() {
        System.out.println("setImportantInformation");
        DCMImportantInformation inInformation = null;
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setImportantInformation(inInformation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getUpdatePackageIdentifiers method, of class DCMBundle.
     */
    @Test
    public void testGetUpdatePackageIdentifiers() {
        System.out.println("getUpdatePackageIdentifiers");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        HashSet<String> expResult = new HashSet<String>();
        HashSet<String> result = instance.getUpdatePackageIdentifiers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addUpdatePackageIdentifier method, of class DCMBundle.
     */
    @Test
    public void testAddUpdatePackageIdentifier() {
        System.out.println("addUpdatePackageIdentifier");
        String inIdentifier = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.addUpdatePackageIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeUpdatePackageIdentifier method, of class DCMBundle.
     */
    @Test
    public void testRemoveUpdatePackageIdentifier() {
        System.out.println("removeUpdatePackageIdentifier");
        String inIdentifier = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 5;
        int result = instance.removeUpdatePackageIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getReleaseID method, of class DCMBundle.
     */
    @Test
    public void testGetReleaseID() {
        System.out.println("getReleaseID");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        String expResult = null;
        String result = instance.getReleaseID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setReleaseID method, of class DCMBundle.
     */
    @Test
    public void testSetReleaseID() {
        System.out.println("setReleaseID");
        String inID = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setReleaseID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSize method, of class DCMBundle.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        long expResult = 0L;
        long result = instance.getSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setSize method, of class DCMBundle.
     */
    @Test
    public void testSetSize() {
        System.out.println("setSize");
        long inSize = 0L;
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setSize(inSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getIdentifier method, of class DCMBundle.
     */
    @Test
    public void testGetIdentifier() {
        System.out.println("getIdentifier");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        DCMGUID expResult = null;
        DCMGUID result = instance.getIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setIdentifier method, of class DCMBundle.
     */
    @Test
    public void testSetIdentifier() {
        System.out.println("setIdentifier");
        DCMGUID inIdentifier = null;
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPredecessorIdentifier method, of class DCMBundle.
     */
    @Test
    public void testGetPredecessorIdentifier() {
        System.out.println("getPredecessorIdentifier");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        DCMGUID expResult = null;
        DCMGUID result = instance.getPredecessorIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setPredecessorIdentifier method, of class DCMBundle.
     */
    @Test
    public void testSetPredecessorIdentifier() {
        System.out.println("setPredecessorIdentifier");
        DCMGUID inIdentifier = null;
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setPredecessorIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getBundleID method, of class DCMBundle.
     */
    @Test
    public void testGetBundleID() {
        System.out.println("getBundleID");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        String expResult = null;
        String result = instance.getBundleID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setBundleID method, of class DCMBundle.
     */
    @Test
    public void testSetBundleID() {
        System.out.println("setBundleID");
        String inID = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setBundleID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPath method, of class DCMBundle.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        String expResult = null;
        String result = instance.getPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setPath method, of class DCMBundle.
     */
    @Test
    public void testSetPath() {
        System.out.println("setPath");
        String inPath = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setPath(inPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVendorVersion method, of class DCMBundle.
     */
    @Test
    public void testGetVendorVersion() {
        System.out.println("getVendorVersion");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        String expResult = null;
        String result = instance.getVendorVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setVendorVersion method, of class DCMBundle.
     */
    @Test
    public void testSetVendorVersion() {
        System.out.println("setVendorVersion");
        String inVersion = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setVendorVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSchemaVersion method, of class DCMBundle.
     */
    @Test
    public void testGetSchemaVersion() {
        System.out.println("getSchemaVersion");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        String expResult = null;
        String result = instance.getSchemaVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setSchemaVersion method, of class DCMBundle.
     */
    @Test
    public void testSetSchemaVersion() {
        System.out.println("setSchemaVersion");
        String inVersion = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setSchemaVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getComponentType method, of class DCMBundle.
     */
    @Test
    public void testGetComponentType() {
        System.out.println("getComponentType");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        DCMComponentType expResult = null;
        DCMComponentType result = instance.getComponentType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setComponentType method, of class DCMBundle.
     */
    @Test
    public void testSetComponentType() {
        System.out.println("setComponentType");
        DCMComponentType inComponentType = null;
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setComponentType(inComponentType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getCategoryCode method, of class DCMBundle.
     */
    @Test
    public void testGetCategoryCode() {
        System.out.println("getCategoryCode");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        String expResult = null;
        String result = instance.getCategoryCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setCategoryCode method, of class DCMBundle.
     */
    @Test
    public void testSetCategoryCode() {
        System.out.println("setCategoryCode");
        String inCategoryCode = "";
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setCategoryCode(inCategoryCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getCreationTime method, of class DCMBundle.
     */
    @Test
    public void testGetCreationTime() {
        System.out.println("getCreationTime");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        Date expResult = null;
        Date result = instance.getCreationTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setCreationTime method, of class DCMBundle.
     */
    @Test
    public void testSetCreationTime() {
        System.out.println("setCreationTime");
        Date inTime = null;
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        int expResult = 0;
        int result = instance.setCreationTime(inTime);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getUniqueIdentifier method, of class DCMBundle.
     */
    @Test
    public void testGetUniqueIdentifier() {
        System.out.println("getUniqueIdentifier");
        DCMBundle instance = new DCMBundle(DCMBundleType.BTW32);
        String expResult = null;
        String result = instance.getUniqueIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
