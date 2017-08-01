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
public class DCMUpdatePackageInformationTest {

    public DCMUpdatePackageInformationTest() {
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
     * Test of getUniqueIdentifier method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetUniqueIdentifier() {
        System.out.println("getUniqueIdentifier");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        String expResult = "typeLWXP";
        String result = instance.getUniqueIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getCategoryCode method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetCategoryCode() {
        System.out.println("getCategoryCode");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        String expResult = "";
        String result = instance.getCategoryCode();
        assertNull(result);
    }

    /**
     * Test of setCategoryCode method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetCategoryCode() {
        System.out.println("setCategoryCode");
        String inValue = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setCategoryCode(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getLUCategoryCode method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetLUCategoryCode() {
        System.out.println("getLUCategoryCode");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        String expResult = "";
        String result = instance.getLUCategoryCode();
        assertNull(result);
    }

    /**
     * Test of setLUCategoryCode method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetLUCategoryCode() {
        System.out.println("setLUCategoryCode");
        String inValue = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setLUCategoryCode(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSupportedTarget method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testAddSupportedTarget() {
        System.out.println("addSupportedTarget");
        String inTargetIdentifier = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.addSupportedTarget(inTargetIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSupportedTarget method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testRemoveSupportedTarget() {
        System.out.println("removeSupportedTarget");
        String inTargetIdentifier = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 5;
        int result = instance.removeSupportedTarget(inTargetIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSupportedTargets method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetSupportedTargets() {
        System.out.println("getSupportedTargets");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        HashSet<String> expResult = null;
        HashSet<String> result = instance.getSupportedTargets();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSupportedOS method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testAddSupportedOS() {
        System.out.println("addSupportedOS");
        String inOSIdentifier = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.addSupportedOS(inOSIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSupportedOS method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testRemoveSupportedOS() {
        System.out.println("removeSupportedOS");
        String inOSIdentifier = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 5;
        int result = instance.removeSupportedOS(inOSIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSupportedOperatingSystems method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testGetSupportedOperatingSystems() {
        System.out.println("getSupportedOperatingSystems");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        HashSet<String> expResult = null;
        HashSet<String> result = instance.getSupportedOperatingSystems();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSupportedSystem method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testAddSupportedSystem() {
        System.out.println("addSupportedSystem");
        String inSystemIdentifier = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.addSupportedSystem(inSystemIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSupportedSystem method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testRemoveSupportedSystem() {
        System.out.println("removeSupportedSystem");
        String inSystemIdentifier = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 5;
        int result = instance.removeSupportedSystem(inSystemIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSupportedSystems method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetSupportedSystems() {
        System.out.println("getSupportedSystems");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        HashSet<String> expResult = null;
        HashSet<String> result = instance.getSupportedSystems();
        assertEquals(0, result.size());
    }

    /**
     * Test of addConstituent method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testAddConstituent() {
        System.out.println("addConstituent");
        DCMConstituent inConstituent = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.addConstituent(inConstituent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getConstituents method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetConstituents() {
        System.out.println("getConstituents");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        Collection<DCMConstituent> expResult = null;
        Collection<DCMConstituent> result = instance.getConstituents();
        assertEquals(0, result.size());
    }

    /**
     * Test of addFMPWrapper method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testAddFMPWrapper() {
        System.out.println("addFMPWrapper");
        DCMFMPWrapperInformation inWrappper = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.addFMPWrapper(inWrappper);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getFMPWrappers method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetFMPWrappers() {
        System.out.println("getFMPWrappers");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        Collection<DCMFMPWrapperInformation> expResult = null;
        Collection<DCMFMPWrapperInformation> result = instance.getFMPWrappers();
        assertEquals(0, result.size());
    }

    /**
     * Test of getInstallInstruction method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testGetInstallInstruction() {
        System.out.println("getInstallInstruction");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMInstallInstructions expResult = null;
        DCMInstallInstructions result = instance.getInstallInstruction();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setInstallInstruction method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testSetInstallInstruction() {
        System.out.println("setInstallInstruction");
        DCMInstallInstructions inInstruction = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setInstallInstruction(inInstruction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getRevisionHistory method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetRevisionHistory() {
        System.out.println("getRevisionHistory");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getRevisionHistory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setRevisionHistory method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetRevisionHistory() {
        System.out.println("setRevisionHistory");
        DCMI18NString inHistory = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setRevisionHistory(inHistory);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getImportantInformation method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testGetImportantInformation() {
        System.out.println("getImportantInformation");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMImportantInformation expResult = null;
        DCMImportantInformation result = instance.getImportantInformation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setImportantInformation method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testSetImportantInformation() {
        System.out.println("setImportantInformation");
        DCMImportantInformation inInformation = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setImportantInformation(inInformation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getCriticality method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetCriticality() {
        System.out.println("getCriticality");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMCriticality expResult = null;
        DCMCriticality result = instance.getCriticality();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setCriticality method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetCriticality() {
        System.out.println("setCriticality");
        DCMCriticality inCriticality = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setCriticality(inCriticality);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getCriticalityDescription method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testGetCriticalityDescription() {
        System.out.println("getCriticalityDescription");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getCriticalityDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setCriticalityDescription method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testSetCriticalityDescription() {
        System.out.println("setCriticalityDescription");
        DCMI18NString inDescription = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setCriticalityDescription(inDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getMSIID method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetMSIID() {
        System.out.println("getMSIID");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMGUID expResult = null;
        DCMGUID result = instance.getMSIID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setMSIID method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetMSIID() {
        System.out.println("setMSIID");
        DCMGUID inID = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setMSIID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getIdentifier method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetIdentifier() {
        System.out.println("getIdentifier");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMGUID expResult = null;
        DCMGUID result = instance.getIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setIdentifier method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetIdentifier() {
        System.out.println("setIdentifier");
        DCMGUID inID = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setIdentifier(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPredecessorIdentifier method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testGetPredecessorIdentifier() {
        System.out.println("getPredecessorIdentifier");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMGUID expResult = null;
        DCMGUID result = instance.getPredecessorIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setPredecessorIdentifier method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testSetPredecessorIdentifier() {
        System.out.println("setPredecessorIdentifier");
        DCMGUID inID = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setPredecessorIdentifier(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getName method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setName method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        DCMI18NString inName = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getComponentType method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetComponentType() {
        System.out.println("getComponentType");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMComponentType expResult = null;
        DCMComponentType result = instance.getComponentType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setComponentType method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetComponentType() {
        System.out.println("setComponentType");
        DCMComponentType inType = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setComponentType(inType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getDescription method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setDescription method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        DCMI18NString inDescription = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setDescription(inDescription);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSchemaVersion method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetSchemaVersion() {
        System.out.println("getSchemaVersion");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        String expResult = "";
        String result = instance.getSchemaVersion();
        assertNull(result);
    }

    /**
     * Test of setSchemaVersion method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetSchemaVersion() {
        System.out.println("setSchemaVersion");
        String inVersion = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setSchemaVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getReleaseID method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetReleaseID() {
        System.out.println("getReleaseID");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        String expResult = "";
        String result = instance.getReleaseID();
        assertNull(result);
    }

    /**
     * Test of setReleaseID method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetReleaseID() {
        System.out.println("setReleaseID");
        String inID = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setReleaseID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getReleaseDate method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetReleaseDate() {
        System.out.println("getReleaseDate");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        Date expResult = null;
        Date result = instance.getReleaseDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setReleaseDate method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetReleaseDate() {
        System.out.println("setReleaseDate");
        Date inDate = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setReleaseDate(inDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getDate method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setDate method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date inDate = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setDate(inDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVendorVersion method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetVendorVersion() {
        System.out.println("getVendorVersion");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        String expResult = "";
        String result = instance.getVendorVersion();
        assertNull(result);
    }

    /**
     * Test of setVendorVersion method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetVendorVersion() {
        System.out.println("setVendorVersion");
        String inVersion = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setVendorVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getDellVersion method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetDellVersion() {
        System.out.println("getDellVersion");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        String expResult = "";
        String result = instance.getDellVersion();
        assertNull(result);
    }

    /**
     * Test of setDellVersion method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetDellVersion() {
        System.out.println("setDellVersion");
        String inVersion = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setDellVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getXMLGenVersion method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetXMLGenVersion() {
        System.out.println("getXMLGenVersion");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        String expResult = "";
        String result = instance.getXMLGenVersion();
        assertNull(result);
    }

    /**
     * Test of setXMLGenVersion method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetXMLGenVersion() {
        System.out.println("setXMLGenVersion");
        String inVersion = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setXMLGenVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPackageType method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetPackageType() {
        System.out.println("getPackageType");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMPackageType expResult = DCMPackageType.LWXP;
        DCMPackageType result = instance.getPackageType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPath method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        String expResult = null;
        String result = instance.getPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setPath method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetPath() {
        System.out.println("setPath");
        String inPath = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setPath(inPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPackageID method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetPackageID() {
        System.out.println("getPackageID");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        String expResult = "type";
        String result = instance.getPackageID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getMD5Hash method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetMD5Hash() {
        System.out.println("getMD5Hash");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        String expResult = "";
        String result = instance.getMD5Hash();
        assertNull(result);
    }

    /**
     * Test of setMD5Hash method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetMD5Hash() {
        System.out.println("setMD5Hash");
        String inHash = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setMD5Hash(inHash);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSize method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        long expResult = 0L;
        long result = instance.getSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setSize method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetSize() {
        System.out.println("setSize");
        long inSize = 0L;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setSize(inSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of requiresReboot method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testRequiresReboot() {
        System.out.println("requiresReboot");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        boolean expResult = false;
        boolean result = instance.requiresReboot();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setRequiresReboot method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testSetRequiresReboot() {
        System.out.println("setRequiresReboot");
        boolean inValue = false;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setRequiresReboot(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of requiresContainerPowerCycle method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testRequiresContainerPowerCycle() {
        System.out.println("requiresContainerPowerCycle");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        boolean expResult = false;
        boolean result = instance.requiresContainerPowerCycle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setRequiresContainerPowerCycle method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testSetRequiresContainerPowerCycle() {
        System.out.println("setRequiresContainerPowerCycle");
        boolean inValue = false;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 0;
        int result = instance.setRequiresContainerPowerCycle(inValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getUpdateInformation method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testGetUpdateInformation() {
        System.out.println("getUpdateInformation");
        DCMVersionInformation inVersionInfo = null;
        String inSystemTypeIdentifier = "";
        String inOSIdentifier = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        DCMUpdateInformation expResult = null;
        DCMUpdateInformation result = instance.getUpdateInformation(null, inVersionInfo, inSystemTypeIdentifier, inOSIdentifier, DCMConsiderationEnum.REPORT_ALL);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addDependencyInformation method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testAddDependencyInformation() {
        System.out.println("addDependencyInformation");
        DCMDependency inDependency = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = -1;
        int result = instance.addDependencyInformation(inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeDependencyInformation method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testRemoveDependencyInformation() {
        System.out.println("removeDependencyInformation");
        DCMDependency inDependency = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 3;
        int result = instance.removeDependencyInformation(inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getDependencies method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetDependencies() {
        System.out.println("getDependencies");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        Collection<DCMDependency> expResult = null;
        Collection<DCMDependency> result = instance.getDependencies();
        assertEquals(0, result.size());
    }

    /**
     * Test of addSoftDependencyInformation method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testAddSoftDependencyInformation() {
        System.out.println("addSoftDependencyInformation");
        DCMSoftDependency inDependency = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = -1;
        int result = instance.addSoftDependencyInformation(inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSoftDependencyInformation method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testRemoveSoftDependencyInformation() {
        System.out.println("removeSoftDependencyInformation");
        DCMSoftDependency inDependency = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 3;
        int result = instance.removeSoftDependencyInformation(inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSoftDependencies method, of class DCMUpdatePackageInformation.
     */
    @Test
    public void testGetSoftDependencies() {
        System.out.println("getSoftDependencies");
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        Collection<DCMSoftDependency> expResult = null;
        Collection<DCMSoftDependency> result = instance.getSoftDependencies();
        assertEquals(0, result.size());
    }

    /**
     * Test of addDependencyReference method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testAddDependencyReference_String_DCMDependency() {
        System.out.println("addDependencyReference");
        String inTargetIdentifier = "";
        DCMDependency inDependency = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 3;
        int result = instance.addDependencyReference(inTargetIdentifier, inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addDependencyReference method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testAddDependencyReference_String_int() {
        System.out.println("addDependencyReference");
        String inTargetIdentifier = "";
        int inDependencyReference = 0;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 3;
        int result = instance.addDependencyReference(inTargetIdentifier, inDependencyReference);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeDependencyReference method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testRemoveDependencyReference_String_DCMDependency() {
        System.out.println("removeDependencyReference");
        String inTargetIdentifier = "";
        DCMDependency inDependency = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 3;
        int result = instance.removeDependencyReference(inTargetIdentifier, inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeDependencyReference method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testRemoveDependencyReference_String_int() {
        System.out.println("removeDependencyReference");
        String inTargetIdentifier = "";
        int inDependencyReference = 0;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 3;
        int result = instance.removeDependencyReference(inTargetIdentifier, inDependencyReference);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getDependenciesForTheGivenTarget method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testGetDependenciesForTheGivenTarget() {
        System.out.println("getDependenciesForTheGivenTarget");
        String inTargetIdentifier = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        Collection<DCMDependency> expResult = null;
        Collection<DCMDependency> result = instance.getDependenciesForTheGivenTarget(inTargetIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSoftDependencyReference method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testAddSoftDependencyReference_String_DCMSoftDependency() {
        System.out.println("addSoftDependencyReference");
        String inTargetIdentifier = "";
        DCMSoftDependency inDependency = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 3;
        int result = instance.addSoftDependencyReference(inTargetIdentifier, inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSoftDependencyReference method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testAddSoftDependencyReference_String_int() {
        System.out.println("addSoftDependencyReference");
        String inTargetIdentifier = "";
        int inDependencyReference = 0;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 3;
        int result = instance.addSoftDependencyReference(inTargetIdentifier, inDependencyReference);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSoftDependencyReference method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testRemoveSoftDependencyReference_String_DCMSoftDependency() {
        System.out.println("removeSoftDependencyReference");
        String inTargetIdentifier = "";
        DCMSoftDependency inDependency = null;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 3;
        int result = instance.removeSoftDependencyReference(inTargetIdentifier, inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSoftDependencyReference method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testRemoveSoftDependencyReference_String_int() {
        System.out.println("removeSoftDependencyReference");
        String inTargetIdentifier = "";
        int inDependencyReference = 0;
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        int expResult = 3;
        int result = instance.removeSoftDependencyReference(inTargetIdentifier, inDependencyReference);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSoftDependenciesForTheGivenTarget method, of class
     * DCMUpdatePackageInformation.
     */
    @Test
    public void testGetSoftDependenciesForTheGivenTarget() {
        System.out.println("getSoftDependenciesForTheGivenTarget");
        String inTargetIdentifier = "";
        DCMUpdatePackageInformation instance = new DCMUpdatePackageInformation("type", DCMPackageType.LWXP);
        Collection<DCMSoftDependency> expResult = null;
        Collection<DCMSoftDependency> result = instance.getSoftDependenciesForTheGivenTarget(inTargetIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
