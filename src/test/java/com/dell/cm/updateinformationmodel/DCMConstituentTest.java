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
public class DCMConstituentTest {

    public DCMConstituentTest() {
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
     * Test of getComponentType method, of class DCMConstituent.
     */
    @Test
    public void testGetComponentType() {
        System.out.println("getComponentType");
        DCMConstituent instance = new DCMConstituent();
        DCMComponentType expResult = null;
        DCMComponentType result = instance.getComponentType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setComponentType method, of class DCMConstituent.
     */
    @Test
    public void testSetComponentType() {
        System.out.println("setComponentType");
        DCMComponentType inType = null;
        DCMConstituent instance = new DCMConstituent();
        int expResult = 0;
        int result = instance.setComponentType(inType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSupportedTarget method, of class DCMConstituent.
     */
    @Test
    public void testAddSupportedTarget() {
        System.out.println("addSupportedTarget");
        String inTargetIdentifier = "";
        DCMConstituent instance = new DCMConstituent();
        int expResult = 0;
        int result = instance.addSupportedTarget(inTargetIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSupportedTarget method, of class DCMConstituent.
     */
    @Test
    public void testRemoveSupportedTarget() {
        System.out.println("removeSupportedTarget");
        String inTargetIdentifier = "";
        DCMConstituent instance = new DCMConstituent();
        int expResult = 5;
        int result = instance.removeSupportedTarget(inTargetIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSupportedTargets method, of class DCMConstituent.
     */
    @Test
    public void testGetSupportedTargets() {
        System.out.println("getSupportedTargets");
        DCMConstituent instance = new DCMConstituent();
        HashSet<String> expResult = null;
        HashSet<String> result = instance.getSupportedTargets();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSupportedOS method, of class DCMConstituent.
     */
    @Test
    public void testAddSupportedOS() {
        System.out.println("addSupportedOS");
        String inOSIdentifier = "";
        DCMConstituent instance = new DCMConstituent();
        int expResult = 0;
        int result = instance.addSupportedOS(inOSIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSupportedOS method, of class DCMConstituent.
     */
    @Test
    public void testRemoveSupportedOS() {
        System.out.println("removeSupportedOS");
        String inOSIdentifier = "";
        DCMConstituent instance = new DCMConstituent();
        int expResult = 5;
        int result = instance.removeSupportedOS(inOSIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSupportedOperatingSystems method, of class DCMConstituent.
     */
    @Test
    public void testGetSupportedOperatingSystems() {
        System.out.println("getSupportedOperatingSystems");
        DCMConstituent instance = new DCMConstituent();
        HashSet<String> result = instance.getSupportedOperatingSystems();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersion method, of class DCMConstituent.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        DCMConstituent instance = new DCMConstituent();
        String expResult = null;
        String result = instance.getVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setVersion method, of class DCMConstituent.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        String inVersion = "";
        DCMConstituent instance = new DCMConstituent();
        int expResult = 0;
        int result = instance.setVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPath method, of class DCMConstituent.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        DCMConstituent instance = new DCMConstituent();
        String expResult = null;
        String result = instance.getPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setPath method, of class DCMConstituent.
     */
    @Test
    public void testSetPath() {
        System.out.println("setPath");
        String inPath = "";
        DCMConstituent instance = new DCMConstituent();
        int expResult = 0;
        int result = instance.setPath(inPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of supports method, of class DCMConstituent.
     */
    @Test
    public void testSupports() {
        System.out.println("supports");
        DCMVersionInformation inVersionInfo = null;
        String inSystemTypeIdentifier = "";
        String inOSIdentifier = "";
        DCMConstituent instance = new DCMConstituent();
        boolean expResult = false;
        boolean result = instance.supports(inVersionInfo, inSystemTypeIdentifier, inOSIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addDependencyInformation method, of class DCMConstituent.
     */
    @Test
    public void testAddDependencyInformation() {
        System.out.println("addDependencyInformation");
        DCMDependency inDependency = null;
        DCMConstituent instance = new DCMConstituent();
        int expResult = -1;
        int result = instance.addDependencyInformation(inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeDependencyInformation method, of class DCMConstituent.
     */
    @Test
    public void testRemoveDependencyInformation() {
        System.out.println("removeDependencyInformation");
        DCMDependency inDependency = null;
        DCMConstituent instance = new DCMConstituent();
        int expResult = 3;
        int result = instance.removeDependencyInformation(inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getDependencies method, of class DCMConstituent.
     */
    @Test
    public void testGetDependencies() {
        System.out.println("getDependencies");
        DCMConstituent instance = new DCMConstituent();
        Collection<DCMDependency> expResult = null;
        Collection<DCMDependency> result = instance.getDependencies();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSoftDependencyInformation method, of class DCMConstituent.
     */
    @Test
    public void testAddSoftDependencyInformation() {
        System.out.println("addSoftDependencyInformation");
        DCMSoftDependency inDependency = null;
        DCMConstituent instance = new DCMConstituent();
        int expResult = -1;
        int result = instance.addSoftDependencyInformation(inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSoftDependencyInformation method, of class DCMConstituent.
     */
    @Test
    public void testRemoveSoftDependencyInformation() {
        System.out.println("removeSoftDependencyInformation");
        DCMSoftDependency inDependency = null;
        DCMConstituent instance = new DCMConstituent();
        int expResult = 3;
        int result = instance.removeSoftDependencyInformation(inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSoftDependencies method, of class DCMConstituent.
     */
    @Test
    public void testGetSoftDependencies() {
        System.out.println("getSoftDependencies");
        DCMConstituent instance = new DCMConstituent();
        Collection<DCMSoftDependency> expResult = null;
        Collection<DCMSoftDependency> result = instance.getSoftDependencies();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addDependencyReference method, of class DCMConstituent.
     */
    @Test
    public void testAddDependencyReference_String_DCMDependency() {
        System.out.println("addDependencyReference");
        String inTargetIdentifier = "";
        DCMDependency inDependency = null;
        DCMConstituent instance = new DCMConstituent();
        int expResult = 3;
        int result = instance.addDependencyReference(inTargetIdentifier, inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addDependencyReference method, of class DCMConstituent.
     */
    @Test
    public void testAddDependencyReference_String_int() {
        System.out.println("addDependencyReference");
        String inTargetIdentifier = "";
        int inDependencyReference = 0;
        DCMConstituent instance = new DCMConstituent();
        int expResult = 3;
        int result = instance.addDependencyReference(inTargetIdentifier, inDependencyReference);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeDependencyReference method, of class DCMConstituent.
     */
    @Test
    public void testRemoveDependencyReference_String_DCMDependency() {
        System.out.println("removeDependencyReference");
        String inTargetIdentifier = "";
        DCMDependency inDependency = null;
        DCMConstituent instance = new DCMConstituent();
        int expResult = 3;
        int result = instance.removeDependencyReference(inTargetIdentifier, inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeDependencyReference method, of class DCMConstituent.
     */
    @Test
    public void testRemoveDependencyReference_String_int() {
        System.out.println("removeDependencyReference");
        String inTargetIdentifier = "";
        int inDependencyReference = 0;
        DCMConstituent instance = new DCMConstituent();
        int expResult = 3;
        int result = instance.removeDependencyReference(inTargetIdentifier, inDependencyReference);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getDependenciesForTheGivenTarget method, of class DCMConstituent.
     */
    @Test
    public void testGetDependenciesForTheGivenTarget() {
        System.out.println("getDependenciesForTheGivenTarget");
        String inTargetIdentifier = "";
        DCMConstituent instance = new DCMConstituent();
        Collection<DCMDependency> expResult = null;
        Collection<DCMDependency> result = instance.getDependenciesForTheGivenTarget(inTargetIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSoftDependencyReference method, of class DCMConstituent.
     */
    @Test
    public void testAddSoftDependencyReference_String_DCMSoftDependency() {
        System.out.println("addSoftDependencyReference");
        String inTargetIdentifier = "";
        DCMSoftDependency inDependency = null;
        DCMConstituent instance = new DCMConstituent();
        int expResult = 3;
        int result = instance.addSoftDependencyReference(inTargetIdentifier, inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSoftDependencyReference method, of class DCMConstituent.
     */
    @Test
    public void testAddSoftDependencyReference_String_int() {
        System.out.println("addSoftDependencyReference");
        String inTargetIdentifier = "";
        int inDependencyReference = 0;
        DCMConstituent instance = new DCMConstituent();
        int expResult = 3;
        int result = instance.addSoftDependencyReference(inTargetIdentifier, inDependencyReference);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSoftDependencyReference method, of class DCMConstituent.
     */
    @Test
    public void testRemoveSoftDependencyReference_String_DCMSoftDependency() {
        System.out.println("removeSoftDependencyReference");
        String inTargetIdentifier = "";
        DCMSoftDependency inDependency = null;
        DCMConstituent instance = new DCMConstituent();
        int expResult = 3;
        int result = instance.removeSoftDependencyReference(inTargetIdentifier, inDependency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSoftDependencyReference method, of class DCMConstituent.
     */
    @Test
    public void testRemoveSoftDependencyReference_String_int() {
        System.out.println("removeSoftDependencyReference");
        String inTargetIdentifier = "";
        int inDependencyReference = 0;
        DCMConstituent instance = new DCMConstituent();
        int expResult = 3;
        int result = instance.removeSoftDependencyReference(inTargetIdentifier, inDependencyReference);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSoftDependenciesForTheGivenTarget method, of class
     * DCMConstituent.
     */
    @Test
    public void testGetSoftDependenciesForTheGivenTarget() {
        System.out.println("getSoftDependenciesForTheGivenTarget");
        String inTargetIdentifier = "";
        DCMConstituent instance = new DCMConstituent();
        Collection<DCMSoftDependency> expResult = null;
        Collection<DCMSoftDependency> result = instance.getSoftDependenciesForTheGivenTarget(inTargetIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
