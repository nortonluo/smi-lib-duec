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
package com.dell.sm.extracter;

import com.dell.cm.comparer.DCMCatalog;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.security.MessageDigest;

/**
 *
 * @author Rahul_Ranjan2
 */
public class DCMPackageExtractorLLXPIT {

    /**
     * Default Constructor
     */
    public DCMPackageExtractorLLXPIT() {

    }

    /**
     * Method to setup resources for the class
     */
    @BeforeClass
    public static void setUpClass() {

    }

    /**
     * Method to allocate resources at the class level
     */
    @AfterClass
    public static void tearDownClass() {

    }

    /**
     * Method to setup resources required by the test
     */
    @Before
    public void setUp() {

    }

    /**
     * Method to allocate resources at the test
     */
    @After
    public void tearDown() {

    }

    /**
     * Method to test the extraxtPackage method of class
     */
    @Test
    public void testextractPackage() {
        System.out.println("Extract Package");

        File file = new File("src\\test\\resources\\ESM_Firmware_9WH0P_LN32_1.05_A01.BIN");
        DCMPackageExtractorLLXP instance = new DCMPackageExtractorLLXP(file);
        String result = instance.extractPackageXML();
        assertNotNull(result);
        assertNotSame(result.indexOf("SoftwareComponent"), -1);
    }

    /**
     * Method to test the extraxtPackage method of class
     */
    @Test
    public void testextractPackage_2() {
        System.out.println("Extract Package");
        File file = new File("src\\test\\resources\\PE1955_ESM_FRMW_LX_R158506.BIN");

        DCMPackageExtractorLLXP instance = new DCMPackageExtractorLLXP(file);
        String expResult = null;
        String result = instance.extractPackageXML();
        assertNotNull(result);
        assertNotSame(result.indexOf("SoftwareComponent"), -1);
    }

    @Test
    public void testExtractPayload() {
        System.out.println("Extract Payload");
        File inputFile = new File("src\\test\\resources\\PE1955_ESM_FRMW_LX_R158506.BIN");
        String outputFile = "src\\test\\resources\\payloadLLXP";
        int expResult = DCMErrorCodes.SUCCESS;
        DCMPackageExtractorLLXP instance = new DCMPackageExtractorLLXP(inputFile);
        int result = instance.extractPayloadFile(outputFile);
        assertEquals(expResult, result);
    }

    @Test
    public void testHashofHash() {
        File filename = new File("src\\test\\resources\\Network_Firmware_RDC79_LN_20.6.16.BIN");
        DCMPackageExtractorLLXP XC = new DCMPackageExtractorLLXP(filename);
        String xml = XC.extractPackageXML();
        DCMUpdatePackageInformation package1 = new DCMCatalog().parseSoftwareComponent(xml, filename.toString());
        String xmlhash = package1.getHashofHash();
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            XC.getPayloadHash(digest);
        } catch (Exception ex) {
        }
        String calculatedhash = DCMPackageHashofHash.convertByteArrayToHexString(digest.digest());
        System.out.println(filename + ":" + xmlhash + "-" + calculatedhash);
        assertEquals(xmlhash, calculatedhash);
    }
}
