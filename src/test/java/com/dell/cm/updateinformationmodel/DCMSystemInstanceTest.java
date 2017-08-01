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

import java.net.InetAddress;
import java.util.Date;
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
public class DCMSystemInstanceTest {

    public DCMSystemInstanceTest() {
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
     * Test of getCollectionTime method, of class DCMSystemInstance.
     */
    @Test
    public void testGetCollectionTime() {
        System.out.println("getCollectionTime");
        DCMSystemInstance instance = new DCMSystemInstance();
        Date expResult = null;
        Date result = instance.getCollectionTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setCollectionTime method, of class DCMSystemInstance.
     */
    @Test
    public void testSetCollectionTime() {
        System.out.println("setCollectionTime");
        Date inTime = null;
        DCMSystemInstance instance = new DCMSystemInstance();
        int expResult = 0;
        int result = instance.setCollectionTime(inTime);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSystemTypeIdentifier method, of class DCMSystemInstance.
     */
    @Test
    public void testGetSystemTypeIdentifier() {
        System.out.println("getSystemTypeIdentifier");
        DCMSystemInstance instance = new DCMSystemInstance();
        String expResult = "";
        String result = instance.getSystemTypeIdentifier();
        assertNull(result);
    }

    /**
     * Test of setSystemTypeIdentifier method, of class DCMSystemInstance.
     */
    @Test
    public void testSetSystemTypeIdentifier() {
        System.out.println("setSystemTypeIdentifier");
        String inIdentifier = "";
        DCMSystemInstance instance = new DCMSystemInstance();
        int expResult = 0;
        int result = instance.setSystemTypeIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getServiceTag method, of class DCMSystemInstance.
     */
    @Test
    public void testGetServiceTag() {
        System.out.println("getServiceTag");
        DCMSystemInstance instance = new DCMSystemInstance();
        String expResult = "";
        String result = instance.getServiceTag();
        assertNull(result);
    }

    /**
     * Test of setServiceTag method, of class DCMSystemInstance.
     */
    @Test
    public void testSetServiceTag() {
        System.out.println("setServiceTag");
        String inServiceTag = "";
        DCMSystemInstance instance = new DCMSystemInstance();
        int expResult = 0;
        int result = instance.setServiceTag(inServiceTag);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getHostOperatingSystemIdentifier method, of class
     * DCMSystemInstance.
     */
    @Test
    public void testGetHostOperatingSystemIdentifier() {
        System.out.println("getHostOperatingSystemIdentifier");
        DCMSystemInstance instance = new DCMSystemInstance();
        String expResult = "";
        String result = instance.getHostOperatingSystemIdentifier();
        assertNull(result);
    }

    /**
     * Test of setHostOperatingSystemIdentifier method, of class
     * DCMSystemInstance.
     */
    @Test
    public void testSetHostOperatingSystemIdentifier() {
        System.out.println("setHostOperatingSystemIdentifier");
        String inIdentifier = "";
        DCMSystemInstance instance = new DCMSystemInstance();
        int expResult = 0;
        int result = instance.setHostOperatingSystemIdentifier(inIdentifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getHostName method, of class DCMSystemInstance.
     */
    @Test
    public void testGetHostName() {
        System.out.println("getHostName");
        DCMSystemInstance instance = new DCMSystemInstance();
        String expResult = "";
        String result = instance.getHostName();
        assertNull(result);
    }

    /**
     * Test of setHostName method, of class DCMSystemInstance.
     */
    @Test
    public void testSetHostName() {
        System.out.println("setHostName");
        String inName = "";
        DCMSystemInstance instance = new DCMSystemInstance();
        int expResult = 0;
        int result = instance.setHostName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getHostIP method, of class DCMSystemInstance.
     */
    @Test
    public void testGetHostIP() {
        System.out.println("getHostIP");
        DCMSystemInstance instance = new DCMSystemInstance();
        InetAddress expResult = null;
        InetAddress result = instance.getHostIP();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setHostIP method, of class DCMSystemInstance.
     */
    @Test
    public void testSetHostIP() {
        System.out.println("setHostIP");
        InetAddress inIP = null;
        DCMSystemInstance instance = new DCMSystemInstance();
        int expResult = 0;
        int result = instance.setHostIP(inIP);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getIDRACIP method, of class DCMSystemInstance.
     */
    @Test
    public void testGetIDRACIP() {
        System.out.println("getIDRACIP");
        DCMSystemInstance instance = new DCMSystemInstance();
        InetAddress expResult = null;
        InetAddress result = instance.getIDRACIP();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setIDRACIP method, of class DCMSystemInstance.
     */
    @Test
    public void testSetIDRACIP() {
        System.out.println("setIDRACIP");
        InetAddress inIP = null;
        DCMSystemInstance instance = new DCMSystemInstance();
        int expResult = 0;
        int result = instance.setIDRACIP(inIP);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getAgentName method, of class DCMSystemInstance.
     */
    @Test
    public void testGetAgentName() {
        System.out.println("getAgentName");
        DCMSystemInstance instance = new DCMSystemInstance();
        String expResult = "";
        String result = instance.getAgentName();
        assertNull(result);
    }

    /**
     * Test of setAgentName method, of class DCMSystemInstance.
     */
    @Test
    public void testSetAgentName() {
        System.out.println("setAgentName");
        String inName = "";
        DCMSystemInstance instance = new DCMSystemInstance();
        int expResult = 0;
        int result = instance.setAgentName(inName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getAgentVersion method, of class DCMSystemInstance.
     */
    @Test
    public void testGetAgentVersion() {
        System.out.println("getAgentVersion");
        DCMSystemInstance instance = new DCMSystemInstance();
        String expResult = "";
        String result = instance.getAgentVersion();
        assertNull(result);
    }

    /**
     * Test of setAgentVersion method, of class DCMSystemInstance.
     */
    @Test
    public void testSetAgentVersion() {
        System.out.println("setAgentVersion");
        String inVersion = "";
        DCMSystemInstance instance = new DCMSystemInstance();
        int expResult = 0;
        int result = instance.setAgentVersion(inVersion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSystemContext method, of class DCMSystemInstance.
     */
    @Test
    public void testGetSystemContext() {
        System.out.println("getSystemContext");
        DCMSystemInstance instance = new DCMSystemInstance();
        DCMSystemContext expResult = null;
        DCMSystemContext result = instance.getSystemContext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setSystemContext method, of class DCMSystemInstance.
     */
    @Test
    public void testSetSystemContext() {
        System.out.println("setSystemContext");
        DCMSystemContext inContext = null;
        DCMSystemInstance instance = new DCMSystemInstance();
        int expResult = 0;
        int result = instance.setSystemContext(inContext);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
