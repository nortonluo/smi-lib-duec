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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shabu_VC
 */
public class DCMPackageExtractorIT {

    public DCMPackageExtractorIT() {
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            Files.createDirectory(Paths.get("src/test/resources/RDC79"));
        } catch (IOException ex) {
            //Logger.getLogger(DCMPackageExtractorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterClass
    public static void tearDownClass() {
        delete(new File("src/test/resources/RDC79"));

    }

    static void delete(File f) {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
        }
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of extractPackageXML method, of class DCMPackageExtractor.
     */
    @Test
    public void testExtractPackageXML() {
        System.out.println("extractPackageXML");
        File inDupFile = new File("src\\test\\resources\\Network_Firmware_RDC79_WN32_20.6.16.EXE");
        String result = DCMPackageExtractor.extractPackageXML(inDupFile);
        assertNotNull(result);
    }

    /**
     * Test of extractPayloadFile method, of class DCMPackageExtractor.
     */
    @Test
    public void testExtractPayloadFile() {
        System.out.println("extractPayloadFile");
        File inDupFile = new File("src\\test\\resources\\Network_Firmware_RDC79_WN32_20.6.16.EXE");
        String inDestination = "src\\test\\resources\\RDC79";
        int expResult = 0;
        int result = DCMPackageExtractor.extractPayloadFile(inDupFile, inDestination);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPayloadHash method, of class DCMPackageExtractor.
     */
    @Test
    public void testGetPayloadHash() {
        System.out.println("getPayloadHash");
        File inDupFile = new File("src\\test\\resources\\Network_Firmware_RDC79_LN_20.6.16.BIN");
        String expResult = "7c381c1f99f139a478068a6ef056fe26a67e18fe8c8b915e35ece1ab1e703815";
        String result = DCMPackageExtractor.getPayloadHash(inDupFile);
        assertEquals(expResult, result);
    }

    /**
     * Test of verifyPayLoadHash method, of class DCMPackageExtractor.
     */
    @Test
    public void testVerifyPayLoadHash() {
        System.out.println("verifyPayLoadHash");
        File inDupFile = new File("src\\test\\resources\\Network_Firmware_RDC79_LN_20.6.16.BIN");
        File inGPGExePath = new File("src\\test\\GPG\\gpg.exe");
        int expResult = 0;
        int result = DCMPackageExtractor.verifyPayLoadHash(inDupFile, inGPGExePath);
        assertEquals(expResult, result);

    }

    /**
     * Test of getExtractor method, of class DCMPackageExtractor.
     */
    @Test
    public void testGetExtractor() {
        System.out.println("getExtractor");
        File inDupFile = new File("src\\test\\resources\\Network_Firmware_RDC79_WN32_20.6.16.EXE");
        IDCMPackageExtractor result = DCMPackageExtractor.getExtractor(inDupFile);
        assertNotNull(result);

    }

}
