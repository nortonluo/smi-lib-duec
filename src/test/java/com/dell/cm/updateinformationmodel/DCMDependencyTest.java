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
public class DCMDependencyTest {

    public DCMDependencyTest() {
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
     * Test of getName method, of class DCMDependency.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DCMDependency instance = new DCMDependency();
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setName method, of class DCMDependency.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        DCMI18NString inName = null;
        DCMDependency instance = new DCMDependency();
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getComponentType method, of class DCMDependency.
     */
    @Test
    public void testGetComponentType() {
        System.out.println("getComponentType");
        DCMDependency instance = new DCMDependency();
        DCMComponentType expResult = null;
        DCMComponentType result = instance.getComponentType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setComponentType method, of class DCMDependency.
     */
    @Test
    public void testSetComponentType() {
        System.out.println("setComponentType");
        DCMComponentType inType = null;
        DCMDependency instance = new DCMDependency();
        int expResult = 0;
        int result = instance.setComponentType(inType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getComponentID method, of class DCMDependency.
     */
    @Test
    public void testGetComponentID() {
        System.out.println("getComponentID");
        DCMDependency instance = new DCMDependency();
        long expResult = 0L;
        long result = instance.getComponentID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setComponentID method, of class DCMDependency.
     */
    @Test
    public void testSetComponentID() {
        System.out.println("setComponentID");
        long inID = 0L;
        DCMDependency instance = new DCMDependency();
        int expResult = 0;
        int result = instance.setComponentID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersion method, of class DCMDependency.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        DCMDependency instance = new DCMDependency();
        String expResult = null;
        String result = instance.getVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setVersion method, of class DCMDependency.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        String inVersion = "";
        DCMDependency instance = new DCMDependency();
        int expResult = 0;
        int result = instance.setVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPrerequisite method, of class DCMDependency.
     */
    @Test
    public void testGetPrerequisite() {
        System.out.println("getPrerequisite");
        DCMDependency instance = new DCMDependency();
        DCMGUID expResult = null;
        DCMGUID result = instance.getPrerequisite();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setPrerequisite method, of class DCMDependency.
     */
    @Test
    public void testSetPrerequisite() {
        System.out.println("setPrerequisite");
        DCMGUID inID = null;
        DCMDependency instance = new DCMDependency();
        int expResult = 0;
        int result = instance.setPrerequisite(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPath method, of class DCMDependency.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        DCMDependency instance = new DCMDependency();
        String expResult = null;
        String result = instance.getPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setPath method, of class DCMDependency.
     */
    @Test
    public void testSetPath() {
        System.out.println("setPath");
        String inPath = "";
        DCMDependency instance = new DCMDependency();
        int expResult = 0;
        int result = instance.setPath(inPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getReleaseID method, of class DCMDependency.
     */
    @Test
    public void testGetReleaseID() {
        System.out.println("getReleaseID");
        DCMDependency instance = new DCMDependency();
        String expResult = null;
        String result = instance.getReleaseID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setReleaseID method, of class DCMDependency.
     */
    @Test
    public void testSetReleaseID() {
        System.out.println("setReleaseID");
        String inID = "";
        DCMDependency instance = new DCMDependency();
        int expResult = 0;
        int result = instance.setReleaseID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getContext method, of class DCMDependency.
     */
    @Test
    public void testGetContext() {
        System.out.println("getContext");
        DCMDependency instance = new DCMDependency();
        DCMDependencyContext expResult = null;
        DCMDependencyContext result = instance.getContext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setContext method, of class DCMDependency.
     */
    @Test
    public void testSetContext() {
        System.out.println("setContext");
        DCMDependencyContext inContext = null;
        DCMDependency instance = new DCMDependency();
        int expResult = 0;
        int result = instance.setContext(inContext);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSupportedTarget method, of class DCMDependency.
     */
    @Test
    public void testAddSupportedTarget() {
        System.out.println("addSupportedTarget");
        String inTargetIdentifier = "";
        DCMDependency instance = new DCMDependency();
        int expResult = 0;
        int result = instance.addSupportedTarget(inTargetIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSupportedTarget method, of class DCMDependency.
     */
    @Test
    public void testRemoveSupportedTarget() {
        System.out.println("removeSupportedTarget");
        String inTargetIdentifier = "";
        DCMDependency instance = new DCMDependency();
        int expResult = 5;
        int result = instance.removeSupportedTarget(inTargetIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSupportedTargets method, of class DCMDependency.
     */
    @Test
    public void testGetSupportedTargets() {
        System.out.println("getSupportedTargets");
        DCMDependency instance = new DCMDependency();
        Set<String> expResult = null;
        Set<String> result = instance.getSupportedTargets();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

}
