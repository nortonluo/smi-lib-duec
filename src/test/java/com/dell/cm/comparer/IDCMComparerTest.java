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
package com.dell.cm.comparer;

import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMCatalogComparisonResult;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMSystemInventory;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
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
public class IDCMComparerTest {

    public IDCMComparerTest() {
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
     * Test of getApplicableUpdates method, of class IDCMComparer.
     */
    @org.junit.Test
    public void testGetApplicableUpdates_3args() {
        System.out.println("getApplicableUpdates");
        DCMMultiSystemInventory inInventory = null;
        String inIdentifier = "";
        DCMManifest inManifest = null;
        IDCMComparer instance = new IDCMComparerImpl();
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> expResult = null;
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> result = instance.getApplicableUpdates(inInventory, inIdentifier, inManifest, DCMConsiderationEnum.REPORT_ALL);
        assertEquals(expResult, result);
    }

    /**
     * Test of getApplicableUpdates method, of class IDCMComparer.
     */
    @org.junit.Test
    public void testGetApplicableUpdates_4args() {
        System.out.println("getApplicableUpdates");
        DCMMultiSystemInventory inInventory = null;
        String inIdentifier = "";
        DCMManifest inManifest = null;
        DCMBundleType inBundleType = null;
        IDCMComparer instance = new IDCMComparerImpl();
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> expResult = null;
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> result = instance.getApplicableUpdates(inInventory, inIdentifier, inManifest, inBundleType, DCMConsiderationEnum.REPORT_ALL);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNonCompliantUpdates method, of class IDCMComparer.
     */
    @org.junit.Test
    public void testGetNonCompliantUpdates() {
        System.out.println("getNonCompliantUpdates");
        DCMMultiSystemInventory inInventory = null;
        String inBaseSystemIdentifier = "";
        String inSystemIdentifier = "";
        IDCMComparer instance = new IDCMComparerImpl();
        HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> expResult = null;
        HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> result = instance.getNonCompliantUpdates(inInventory,
                inBaseSystemIdentifier, inSystemIdentifier, DCMInstanceConsiderationEnum.CONSIDER_MAX_INSTANCE_VERSION);
        assertEquals(expResult, result);
    }

    public class IDCMComparerImpl implements IDCMComparer {

        public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(DCMMultiSystemInventory inInventory, String inIdentifier, DCMManifest inManifest, DCMConsiderationEnum inConsideration) {
            return null;
        }

        public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(DCMMultiSystemInventory inInventory, String inIdentifier, DCMManifest inManifest, DCMBundleType inBundleType, DCMConsiderationEnum inConsideration) {
            return null;
        }

        public HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> getNonCompliantUpdates(DCMMultiSystemInventory inInventory, String inBaseSystemIdentifier, String inSystemIdentifier, DCMInstanceConsiderationEnum inConsideration) {
            return null;
        }

        public HashMap<String, HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>>> getNonCompliantUpdates(DCMMultiSystemInventory inTargetInventory, DCMMultiSystemInventory inBaseInventory, DCMInstanceConsiderationEnum inConsideration) {
            return null;
        }

        public HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> getNonCompliantUpdates(File inBaseInventory, File inInventoryToCompare, DCMInstanceConsiderationEnum inConsideration) {
            return null;
        }

        public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(HashSet<File> inInventoryCollection, String inSystemIdentifier, File inCatalogFile, DCMConsiderationEnum inConsideration) {
            return null;
        }

        public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(File inMultiInventoryFile, File inCatalogFile, DCMConsiderationEnum inConsideration) {
            return null;
        }

        public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(HashSet<DCMSystemInventory> inHashSetDCMSystemInventory, File inCatalogFile, DCMBundleType inBundleType, DCMConsiderationEnum inConsideration) {
            return null;
        }

        public HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> getApplicableUpdates(DCMMultiSystemInventory inMultiSystemInventoryObj, File inCatalogFile, DCMBundleType bundleType, DCMConsiderationEnum inConsideration) {
            return null;
        }

        
        public DCMCatalogComparisonResult compareCatalogs(File inSourceCatalog, File inLatestCatalog) {
            return null; //To change body of generated methods, choose Tools | Templates.
        }

        
        public DCMCatalogComparisonResult compareCatalogs(DCMManifest inSourceManifest, DCMManifest inLatestManifest) {
            return null; //To change body of generated methods, choose Tools | Templates.
        }
    }
}
