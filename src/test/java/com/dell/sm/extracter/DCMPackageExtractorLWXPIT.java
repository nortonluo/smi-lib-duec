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
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import com.dell.sm.downloader.DSMErrorCodes;
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
public class DCMPackageExtractorLWXPIT {

    /**
     * Default Constructor
     */
    public DCMPackageExtractorLWXPIT() {

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
        File file = new File("src\\test\\resources\\ESM_Firmware_9WH0P_WN64_1.05_A01.EXE");
        DCMPackageExtractorLWXP instance = new DCMPackageExtractorLWXP(file);
        String result = instance.extractPackageXML();
        assertNotNull(result);
        assertNotSame(result.indexOf("SoftwareComponent"), -1);
    }

    @Test
    public void testextractPackage2() {
        System.out.println("Extract Package");
        File file = new File("src\\test\\resources\\PE1955_ESM_FRMW_WIN_R158506.EXE");
        DCMPackageExtractorLWXP instance = new DCMPackageExtractorLWXP(file);
        String result = instance.extractPackageXML();
        assertNotNull(result);
        assertNotSame(result.indexOf("SoftwareComponent"), -1);
    }

    @Test
    public void testExtractPayload() {
        System.out.println("Extract Payload");
        File inputFile = new File("test\\resources\\PE1955_ESM_FRMW_WIN_R158506.EXE");
        String outFile = "src\\test\\resources\\payloadLWXP";
        int expResult = DSMErrorCodes.SUCCESS;
        DCMPackageExtractorLWXP instance = new DCMPackageExtractorLWXP(inputFile);
        int result = instance.extractPayloadFile(outFile);
        assertEquals(expResult, result);

    }

    @Test
    public void testHashofHash32() {
        testHashofHash(new File("src\\test\\resources\\Network_Firmware_RDC79_WN32_20.6.16.EXE"));
    }

    @Test
    public void testHashofHash64() {
        testHashofHash(new File("src\\test\\resources\\Network_Firmware_RDC79_WN64_20.6.16.EXE"));
    }

    public void testHashofHash(File filename) {
        IDCMPackageExtractor XC = new DCMPackageExtractorLWXP(filename);
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
