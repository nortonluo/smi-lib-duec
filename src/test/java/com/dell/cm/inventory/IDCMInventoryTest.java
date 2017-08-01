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
package com.dell.cm.inventory;

import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMSystemInventory;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.downloader.DSMProxy;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.xpath.XPathExpressionException;
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
public class IDCMInventoryTest {

    public IDCMInventoryTest() {
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
     * Test of createEmptyMultiSystemInventory method, of class IDCMInventory.
     */
    @Test
    public void testCreateEmptyMultiSystemInventory() {
        System.out.println("createEmptyMultiSystemInventory");
        IDCMInventory instance = new IDCMInventoryImpl();
        DCMMultiSystemInventory expResult = null;
        DCMMultiSystemInventory result = instance.createEmptyMultiSystemInventory();
        assertEquals(expResult, result);
    }

    /**
     * Test of addInventory method, of class IDCMInventory.
     */
    @Test
    public void testAddInventory_4args() {
        System.out.println("addInventory");
        File inFile = null;
        String inIdentifier = "";
        DCMMultiSystemInventory inRoot = null;
        Boolean inAddAsChild = null;
        IDCMInventory instance = new IDCMInventoryImpl();
        int expResult = 0;
        int result = instance.addInventory(inFile, inIdentifier, inRoot, inAddAsChild);
        assertEquals(expResult, result);
    }

    /**
     * Test of addWSInventory method, of class IDCMInventory.
     */
    @Test
    public void testAddWSInventory_File_DCMMultiSystemInventory() {
        System.out.println("addWSInventory");
        File inFile = null;
        DCMMultiSystemInventory inRoot = null;
        IDCMInventory instance = new IDCMInventoryImpl();
        int expResult = 0;
        HashSet<File> inFiles = new HashSet<File>();
        inFiles.add(inFile);
        int result = instance.addWSInventory(inFiles, inRoot);
        assertEquals(expResult, result);
    }

    /**
     * Test of addRedFishInventory method, of class IDCMInventory.
     */
    @Test
    public void testAddRedFishInventory_File_DCMMultiSystemInventory() {
        System.out.println("addRedFishInventory");
        File inFile = null;
        DCMMultiSystemInventory inRoot = null;
        IDCMInventory instance = new IDCMInventoryImpl();
        int expResult = 0;
        int result = instance.addRedFishInventory(inFile, inRoot);
        assertEquals(expResult, result);
    }

    /**
     * Test of addInventory method, of class IDCMInventory.
     */
    @Test
    public void testAddInventory_3args() {
        System.out.println("addInventory");
        HashSet<File> inFileSet = null;
        String inIdentifier = "";
        DCMMultiSystemInventory inRoot = null;
        IDCMInventory instance = new IDCMInventoryImpl();
        int expResult = 0;
        int result = instance.addInventory(inFileSet, inIdentifier, inRoot);
        assertEquals(expResult, result);
    }

    /**
     * Test of addWSInventory method, of class IDCMInventory.
     */
    @Test
    public void testAddWSInventory_HashSet_DCMMultiSystemInventory() {
        System.out.println("addWSInventory");
        HashSet<File> inFileSet = null;
        DCMMultiSystemInventory inRoot = null;
        IDCMInventory instance = new IDCMInventoryImpl();
        int expResult = 0;
        int result = instance.addWSInventory(inFileSet, inRoot);
        assertEquals(expResult, result);
    }

    /**
     * Test of addRedFishInventory method, of class IDCMInventory.
     */
    @Test
    public void testAddRedFishInventory_HashSet_DCMMultiSystemInventory() {
        System.out.println("addRedFishInventory");
        HashSet<File> inFileSet = null;
        DCMMultiSystemInventory inRoot = null;
        IDCMInventory instance = new IDCMInventoryImpl();
        int expResult = 0;
        int result = instance.addRedFishInventory(inFileSet, inRoot);
        assertEquals(expResult, result);
    }

    public class IDCMInventoryImpl implements IDCMInventory {

        public DCMMultiSystemInventory createEmptyMultiSystemInventory() {
            return null;
        }

        public int addInventory(File inFile, String inIdentifier, DCMMultiSystemInventory inRoot, Boolean inAddAsChild) {
            return 0;
        }

        public int addWSInventory(String iDRACipAddress, DSMAuthenticationParameters authentication, DCMMultiSystemInventory inRoot) throws SOAPException, JAXBException, DatatypeConfigurationException, IOException, XPathExpressionException, Exception {
            return 0;
        }

        public int addRedFishInventory(File inFile, DCMMultiSystemInventory inRoot) {
            return 0;
        }

        public int addInventory(HashSet<File> inFileSet, String inIdentifier, DCMMultiSystemInventory inRoot) {
            return 0;
        }

        public int addWSInventory(HashSet<File> inFileSet, DCMMultiSystemInventory inRoot) {
            return 0;
        }

        public int addRedFishInventory(HashSet<File> inFileSet, DCMMultiSystemInventory inRoot) {
            return 0;
        }

        public int serializeToFile(DCMMultiSystemInventory inRoot, File outFile) {
            return 0;
        }

        public String serializeToString(DCMMultiSystemInventory inRoot) {
            return "";
        }

        public int addInventory(StringReader inInventoryAsString, String inIdentifier, DCMMultiSystemInventory inRoot, Boolean inAddAsChild) {
            return 0;
        }

        public int deserializeXMLFile(File inMultiInventoryFile, DCMMultiSystemInventory inRoot) {
            return 0;
        }

        public int addInventory(DCMSystemInventory inSystem, DCMMultiSystemInventory inRoot) {
            return 0;
        }

        @Override
        public int addWSInventory(String iDRACipAddress, String iDRACport, DSMAuthenticationParameters authentication, DCMMultiSystemInventory inRoot, DSMProxy mProxy) {
            return 0;
        }
    }
}
