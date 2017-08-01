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
public class DCMBaseTargetTest {

    public DCMBaseTargetTest() {
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
     * Test of getName method, of class DCMBaseTarget.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        DCMI18NString expResult = null;
        DCMI18NString result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class DCMBaseTarget.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        DCMI18NString inName = null;
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        int expResult = 0;
        int result = instance.setName(inName);
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class DCMBaseTarget.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        DCMTargetEntity expResult = null;
        DCMTargetEntity result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmbedded method, of class DCMBaseTarget.
     */
    @Test
    public void testIsEmbedded() {
        System.out.println("isEmbedded");
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        boolean expResult = false;
        boolean result = instance.isEmbedded();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmbedded method, of class DCMBaseTarget.
     */
    @Test
    public void testSetEmbedded() {
        System.out.println("setEmbedded");
        boolean inValue = false;
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        int expResult = 0;
        int result = instance.setEmbedded(inValue);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUniqueIdentifier method, of class DCMBaseTarget.
     */
    @Test
    public void testGetUniqueIdentifier() {
        System.out.println("getUniqueIdentifier");
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        String expResult = "";
        String result = instance.getUniqueIdentifier();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRollbackInformation method, of class DCMBaseTarget.
     */
    @Test
    public void testGetRollbackInformation() {
        System.out.println("getRollbackInformation");
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        DCMRollbackInformation expResult = null;
        DCMRollbackInformation result = instance.getRollbackInformation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRollbackInformaton method, of class DCMBaseTarget.
     */
    @Test
    public void testSetRollbackInformaton() {
        System.out.println("setRollbackInformaton");
        DCMRollbackInformation inRollbackInformation = null;
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        int expResult = 0;
        int result = instance.setRollbackInformaton(inRollbackInformation);
        assertEquals(expResult, result);
    }

    /**
     * Test of addSubComponent method, of class DCMBaseTarget.
     */
    @Test
    public void testAddSubComponent() {
        System.out.println("addSubComponent");
        DCMSubComponent inComponent = null;
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        int expResult = 3;
        int result = instance.addSubComponent(inComponent);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeSubComponent method, of class DCMBaseTarget.
     */
    @Test
    public void testRemoveSubComponent() {
        System.out.println("removeSubComponent");
        DCMSubComponent inComponent = null;
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        int expResult = 3;
        int result = instance.removeSubComponent(inComponent);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSubComponents method, of class DCMBaseTarget.
     */
    @Test
    public void testGetSubComponents() {
        System.out.println("getSubComponents");
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        int expResult = 0;
        Set<DCMSubComponent> result = instance.getSubComponents();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of addApplicabilityElement method, of class DCMBaseTarget.
     */
    @Test
    public void testAddApplicabilityElement() {
        System.out.println("addApplicabilityElement");
        DCMDeviceApplicability inElement = null;
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        int expResult = 3;
        int result = instance.addApplicabilityElement(inElement);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeApplicabilityElement method, of class DCMBaseTarget.
     */
    @Test
    public void testRemoveApplicabilityElement() {
        System.out.println("removeApplicabilityElement");
        DCMDeviceApplicability inElement = null;
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        int expResult = 3;
        int result = instance.removeApplicabilityElement(inElement);
        assertEquals(expResult, result);
    }

    /**
     * Test of getApplicabilityElements method, of class DCMBaseTarget.
     */
    @Test
    public void testGetApplicabilityElements() {
        System.out.println("getApplicabilityElements");
        DCMBaseTarget instance = new DCMBaseTargetImpl();
        int expResult = 0;
        Set<DCMDeviceApplicability> result = instance.getApplicabilityElements();
        assertEquals(expResult, result.size());
    }

    public class DCMBaseTargetImpl extends DCMBaseTarget {

        public String getUniqueIdentifier() {
            return "";
        }

        @Override
        public DCMBaseTarget Copy() {
            return null; //To change body of generated methods, choose Tools | Templates.
        }
    }

}
