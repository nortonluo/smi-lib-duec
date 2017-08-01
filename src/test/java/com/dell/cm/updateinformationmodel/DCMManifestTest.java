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

import com.dell.cm.comparer.DCMConsiderationEnum;
import com.dell.cm.comparer.DCMUpdateInformation;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
public class DCMManifestTest {

    public DCMManifestTest() {
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
     * Test of getReleaseNotes method, of class DCMManifest.
     */
    @Test
    public void testGetReleaseNotes() {
        System.out.println("getReleaseNotes");
        DCMManifest instance = new DCMManifest();
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getReleaseNotes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setReleaseNotes method, of class DCMManifest.
     */
    @Test
    public void testSetReleaseNotes() {
        System.out.println("setReleaseNotes");
        DCMI18NString inReleaseNotes = null;
        DCMManifest instance = new DCMManifest();
        int expResult = 0;
        int result = instance.setReleaseNotes(inReleaseNotes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getBaseLocation method, of class DCMManifest.
     */
    @Test
    public void testGetBaseLocation() {
        System.out.println("getBaseLocation");
        DCMManifest instance = new DCMManifest();
        String result = instance.getBaseLocation();
        assertNull(result);

    }

    /**
     * Test of setBaseLocation method, of class DCMManifest.
     */
    @Test
    public void testSetBaseLocation() {
        System.out.println("setBaseLocation");
        String inLocation = "";
        DCMManifest instance = new DCMManifest();
        int expResult = 0;
        int result = instance.setBaseLocation(inLocation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getCreationDate method, of class DCMManifest.
     */
    @Test
    public void testGetCreationDate() {
        System.out.println("getCreationDate");
        DCMManifest instance = new DCMManifest();
        Date expResult = null;
        Date result = instance.getCreationDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setCreationDate method, of class DCMManifest.
     */
    @Test
    public void testSetCreationDate() {
        System.out.println("setCreationDate");
        Date inDate = null;
        DCMManifest instance = new DCMManifest();
        int expResult = 0;
        int result = instance.setCreationDate(inDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getIdentifier method, of class DCMManifest.
     */
    @Test
    public void testGetIdentifier() {
        System.out.println("getIdentifier");
        DCMManifest instance = new DCMManifest();
        DCMGUID expResult = null;
        DCMGUID result = instance.getIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setIdentifier method, of class DCMManifest.
     */
    @Test
    public void testSetIdentifier() {
        System.out.println("setIdentifier");
        DCMGUID inIdentifier = null;
        DCMManifest instance = new DCMManifest();
        int expResult = 0;
        int result = instance.setIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPredecessorIdentifier method, of class DCMManifest.
     */
    @Test
    public void testGetPredecessorIdentifier() {
        System.out.println("getPredecessorIdentifier");
        DCMManifest instance = new DCMManifest();
        DCMGUID expResult = null;
        DCMGUID result = instance.getPredecessorIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setPredecessorIdentifier method, of class DCMManifest.
     */
    @Test
    public void testSetPredecessorIdentifier() {
        System.out.println("setPredecessorIdentifier");
        DCMGUID inIdentifier = null;
        DCMManifest instance = new DCMManifest();
        int expResult = 0;
        int result = instance.setPredecessorIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getReleaseID method, of class DCMManifest.
     */
    @Test
    public void testGetReleaseID() {
        System.out.println("getReleaseID");
        DCMManifest instance = new DCMManifest();
        String expResult = null;
        String result = instance.getReleaseID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setReleaseID method, of class DCMManifest.
     */
    @Test
    public void testSetReleaseID() {
        System.out.println("setReleaseID");
        String inReleaseID = "";
        DCMManifest instance = new DCMManifest();
        int expResult = 0;
        int result = instance.setReleaseID(inReleaseID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersion method, of class DCMManifest.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        DCMManifest instance = new DCMManifest();
        String expResult = null;
        String result = instance.getVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setVersion method, of class DCMManifest.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        String inVersion = "";
        DCMManifest instance = new DCMManifest();
        int expResult = 0;
        int result = instance.setVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getLineOfBusinessCollection method, of class DCMManifest.
     */
    @Test
    public void testGetLineOfBusinessCollection() {
        System.out.println("getLineOfBusinessCollection");
        DCMManifest instance = new DCMManifest();
        DCMLineOfBusinessCollection expResult = null;
        DCMLineOfBusinessCollection result = instance.getLineOfBusinessCollection();
        assertNotNull(result);

    }

    /**
     * Test of getSystemCollection method, of class DCMManifest.
     */
    @Test
    public void testGetSystemCollection() {
        System.out.println("getSystemCollection");
        DCMManifest instance = new DCMManifest();
        DCMSystemCollection expResult = null;
        DCMSystemCollection result = instance.getSystemCollection();
        assertNotNull(result);

    }

    /**
     * Test of getOperatingSystemCollection method, of class DCMManifest.
     */
    @Test
    public void testGetOperatingSystemCollection() {
        System.out.println("getOperatingSystemCollection");
        DCMManifest instance = new DCMManifest();
        DCMOperatingSystemCollection expResult = null;
        DCMOperatingSystemCollection result = instance.getOperatingSystemCollection();
        assertNotNull(result);

    }

    /**
     * Test of getComponentKindCollection method, of class DCMManifest.
     */
    @Test
    public void testGetComponentKindCollection() {
        System.out.println("getComponentKindCollection");
        DCMManifest instance = new DCMManifest();
        DCMComponentKindCollection expResult = null;
        DCMComponentKindCollection result = instance.getComponentKindCollection();
        assertNotNull(result);

    }

    /**
     * Test of getTargetCollection method, of class DCMManifest.
     */
    @Test
    public void testGetTargetCollection() {
        System.out.println("getTargetCollection");
        DCMManifest instance = new DCMManifest();
        DCMManifest result = instance;
        
        /**DCMTargetCollection*/
        //DCMTargetCollection expResult = null;
        //DCMTargetCollection result = instance.getTargetCollection();
        assertNotNull(result);

    }

    /**
     * Test of getCategoryCollection method, of class DCMManifest.
     */
    @Test
    public void testGetCategoryCollection() {
        System.out.println("getCategoryCollection");
        DCMManifest instance = new DCMManifest();
        DCMCategoryCollection expResult = null;
        DCMCategoryCollection result = instance.getCategoryCollection();
        assertNotNull(result);

    }

    /**
     * Test of getBundleCollection method, of class DCMManifest.
     */
    @Test
    public void testGetBundleCollection() {
        System.out.println("getBundleCollection");
        DCMManifest instance = new DCMManifest();
        DCMBundleCollection expResult = null;
        DCMBundleCollection result = instance.getBundleCollection();
        assertNotNull(result);

    }

    /**
     * Test of getUpdateableComponentCollection method, of class DCMManifest.
     */
    @Test
    public void testGetUpdateableComponentCollection() {
        System.out.println("getUpdateableComponentCollection");
        DCMManifest instance = new DCMManifest();
        DCMUpdateableComponentCollection expResult = null;
        DCMUpdateableComponentCollection result = instance.getUpdateableComponentCollection();
        assertNotNull(result);
    }

    /**
     * Test of getSoftwareComponents method, of class DCMManifest.
     */
    @Test
    public void testGetSoftwareComponents() {
        System.out.println("getSoftwareComponents");
        DCMManifest instance = new DCMManifest();
        DCMUpdatePackageInformationCollection expResult = null;
        DCMUpdatePackageInformationCollection result = instance.getSoftwareComponents();
        assertNotNull(result);

    }

    /**
     * Test of getPrerequisites method, of class DCMManifest.
     */
    @Test
    public void testGetPrerequisites() {
        System.out.println("getPrerequisites");
        DCMManifest instance = new DCMManifest();
        DCMUpdatePackageInformationCollection expResult = null;
        DCMUpdatePackageInformationCollection result = instance.getPrerequisites();
        assertNotNull(result);

    }

    /**
     * Test of getInventoryCollectorInformationCollection method, of class
     * DCMManifest.
     */
    @Test
    public void testGetInventoryCollectorInformationCollection() {
        System.out.println("getInventoryCollectorInformationCollection");
        DCMManifest instance = new DCMManifest();
        DCMInventoryCollectorInformationCollection expResult = null;
        DCMInventoryCollectorInformationCollection result = instance.getInventoryCollectorInformationCollection();
        assertNotNull(result);

    }

    /**
     * Test of getRelevantBundleIdentifiers method, of class DCMManifest.
     */
    @Test
    public void testGetRelevantBundleIdentifiers() {
        System.out.println("getRelevantBundleIdentifiers");
        DCMMultiSystemInventory inInventory = null;
        String inIdentifier = "";
        DCMBundleType inBundleType = null;
        DCMManifest instance = new DCMManifest();
        HashMap<String, Collection<String>> expResult = null;
        HashMap<String, Collection<String>> result = instance.getRelevantBundleIdentifiers(inInventory, inIdentifier, inBundleType);
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getBundle method, of class DCMManifest.
     */
    @Test
    public void testGetBundle() {
        System.out.println("getBundle");
        String inBundleIdentifier = "";
        DCMManifest instance = new DCMManifest();
        DCMBundle expResult = null;
        DCMBundle result = instance.getBundle(inBundleIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getApplicableUpdates method, of class DCMManifest.
     */
    @Test
    public void testGetApplicableUpdates() {
        System.out.println("getApplicableUpdates");
        String inBundleIdentifier = "";
        DCMVersionInformationCollection inVersionInformationCollection = null;
        String inSystemTypeIdentifier = "";
        String inOSIdentiifer = "";
        DCMManifest instance = new DCMManifest();
        HashMap<DCMVersionInformation, DCMUpdateInformation> expResult = null;
        HashMap<DCMVersionInformation, DCMUpdateInformation> result = instance.getApplicableUpdates(inBundleIdentifier, inVersionInformationCollection, inSystemTypeIdentifier, inOSIdentiifer, DCMConsiderationEnum.REPORT_ALL);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
