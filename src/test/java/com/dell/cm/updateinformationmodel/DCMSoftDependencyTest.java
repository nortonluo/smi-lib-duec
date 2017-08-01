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
public class DCMSoftDependencyTest {

    public DCMSoftDependencyTest() {
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
     * Test of getName method, of class DCMSoftDependency.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DCMSoftDependency instance = new DCMSoftDependency();
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setName method, of class DCMSoftDependency.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        DCMI18NString inName = null;
        DCMSoftDependency instance = new DCMSoftDependency();
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getDetail method, of class DCMSoftDependency.
     */
    @Test
    public void testGetDetail() {
        System.out.println("getDetail");
        DCMSoftDependency instance = new DCMSoftDependency();
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getDetail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setDetail method, of class DCMSoftDependency.
     */
    @Test
    public void testSetDetail() {
        System.out.println("setDetail");
        DCMI18NString inDetail = null;
        DCMSoftDependency instance = new DCMSoftDependency();
        int expResult = 0;
        int result = instance.setDetail(inDetail);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getComponentType method, of class DCMSoftDependency.
     */
    @Test
    public void testGetComponentType() {
        System.out.println("getComponentType");
        DCMSoftDependency instance = new DCMSoftDependency();
        DCMComponentType expResult = null;
        DCMComponentType result = instance.getComponentType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setComponentType method, of class DCMSoftDependency.
     */
    @Test
    public void testSetComponentType() {
        System.out.println("setComponentType");
        DCMComponentType inType = null;
        DCMSoftDependency instance = new DCMSoftDependency();
        int expResult = 0;
        int result = instance.setComponentType(inType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getComponentID method, of class DCMSoftDependency.
     */
    @Test
    public void testGetComponentID() {
        System.out.println("getComponentID");
        DCMSoftDependency instance = new DCMSoftDependency();
        long expResult = 0L;
        long result = instance.getComponentID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setComponentID method, of class DCMSoftDependency.
     */
    @Test
    public void testSetComponentID() {
        System.out.println("setComponentID");
        long inID = 0L;
        DCMSoftDependency instance = new DCMSoftDependency();
        int expResult = 0;
        int result = instance.setComponentID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVersion method, of class DCMSoftDependency.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        DCMSoftDependency instance = new DCMSoftDependency();
        String expResult = "";
        String result = instance.getVersion();
        assertNull(result);
    }

    /**
     * Test of setVersion method, of class DCMSoftDependency.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        String inVersion = "";
        DCMSoftDependency instance = new DCMSoftDependency();
        int expResult = 0;
        int result = instance.setVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPrerequisite method, of class DCMSoftDependency.
     */
    @Test
    public void testGetPrerequisite() {
        System.out.println("getPrerequisite");
        DCMSoftDependency instance = new DCMSoftDependency();
        DCMGUID expResult = null;
        DCMGUID result = instance.getPrerequisite();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setPrerequisite method, of class DCMSoftDependency.
     */
    @Test
    public void testSetPrerequisite() {
        System.out.println("setPrerequisite");
        DCMGUID inID = null;
        DCMSoftDependency instance = new DCMSoftDependency();
        int expResult = 0;
        int result = instance.setPrerequisite(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPath method, of class DCMSoftDependency.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        DCMSoftDependency instance = new DCMSoftDependency();
        String expResult = "";
        String result = instance.getPath();
        assertNull(result);
    }

    /**
     * Test of setPath method, of class DCMSoftDependency.
     */
    @Test
    public void testSetPath() {
        System.out.println("setPath");
        String inPath = "";
        DCMSoftDependency instance = new DCMSoftDependency();
        int expResult = 0;
        int result = instance.setPath(inPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getReleaseID method, of class DCMSoftDependency.
     */
    @Test
    public void testGetReleaseID() {
        System.out.println("getReleaseID");
        DCMSoftDependency instance = new DCMSoftDependency();
        String expResult = "";
        String result = instance.getReleaseID();
        assertNull(result);
    }

    /**
     * Test of setReleaseID method, of class DCMSoftDependency.
     */
    @Test
    public void testSetReleaseID() {
        System.out.println("setReleaseID");
        String inID = "";
        DCMSoftDependency instance = new DCMSoftDependency();
        int expResult = 0;
        int result = instance.setReleaseID(inID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getContext method, of class DCMSoftDependency.
     */
    @Test
    public void testGetContext() {
        System.out.println("getContext");
        DCMSoftDependency instance = new DCMSoftDependency();
        DCMDependencyContext expResult = null;
        DCMDependencyContext result = instance.getContext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setContext method, of class DCMSoftDependency.
     */
    @Test
    public void testSetContext() {
        System.out.println("setContext");
        DCMDependencyContext inContext = null;
        DCMSoftDependency instance = new DCMSoftDependency();
        int expResult = 0;
        int result = instance.setContext(inContext);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addSupportedTarget method, of class DCMSoftDependency.
     */
    @Test
    public void testAddSupportedTarget() {
        System.out.println("addSupportedTarget");
        String inTargetIdentifier = "";
        DCMSoftDependency instance = new DCMSoftDependency();
        int expResult = 0;
        int result = instance.addSupportedTarget(inTargetIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of removeSupportedTarget method, of class DCMSoftDependency.
     */
    @Test
    public void testRemoveSupportedTarget() {
        System.out.println("removeSupportedTarget");
        String inTargetIdentifier = "";
        DCMSoftDependency instance = new DCMSoftDependency();
        int expResult = 5;
        int result = instance.removeSupportedTarget(inTargetIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSupportedTargets method, of class DCMSoftDependency.
     */
    @Test
    public void testGetSupportedTargets() {
        System.out.println("getSupportedTargets");
        DCMSoftDependency instance = new DCMSoftDependency();
        Set<String> expResult = null;
        Set<String> result = instance.getSupportedTargets();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.

    }

}
