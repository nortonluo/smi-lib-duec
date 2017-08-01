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

import com.dell.cm.inventory.DCMInventory;
import com.dell.cm.testParameters.TestInputs;
import com.dell.cm.updateinformationmodel.DCMBundle;
import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMCatalogComparisonResult;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMSystemInstance;
import com.dell.cm.updateinformationmodel.DCMSystemInstanceCollection;
import com.dell.cm.updateinformationmodel.DCMSystemInventory;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
public class DCMComparerIT {

    static DCMManifest inManifest;
    static String inIdentifier;
    static File catalogFile;
    static DCMMultiSystemInventory inInventory;

    public DCMComparerIT() {
    }

    @BeforeClass
    public static void setUpClass() {
        catalogFile = new File(TestInputs.catalogFileXML);
        File inventoryFile = new File("src\\test\\resources\\outputPowerEdgeVRTX.xml");

        DCMInventory inventoryObj = new DCMInventory();
        inInventory = inventoryObj.createEmptyMultiSystemInventory();

        inventoryObj.addInventory(inventoryFile, "", inInventory, Boolean.FALSE);

        DCMCatalog catalogObj = new DCMCatalog();
        inManifest = catalogObj.parseCatalog(catalogFile);

        inIdentifier = inInventory.getSystemInstanceCollection().getSystemInstances().iterator().next().getUniqueID();
    }

    @AfterClass
    public static void tearDownClass() {
        inManifest = null;
        inIdentifier = null;
        catalogFile = null;
        inInventory = null;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getApplicableUpdates method, of class DCMComparer.
     */
    @org.junit.Test
    public void testGetApplicableUpdates_4args() {
        System.out.println("getApplicableUpdates");
        DCMComparer instance = new DCMComparer();
        int expResult = 7;
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> applicableUpdateMap = instance.getApplicableUpdates(inInventory, inIdentifier, inManifest, DCMConsiderationEnum.REPORT_ALL);
        int result = applicableUpdateMap.get(inIdentifier).size();
        assertEquals(expResult, result);
        for (Map.Entry<DCMVersionInformation, DCMUpdateInformation> entrySet : applicableUpdateMap.get(inIdentifier).entrySet()) {
            //DCMVersionInformation key = entrySet.getKey();
            DCMUpdateInformation value = entrySet.getValue();
            String id = value.getUniqueIdentifier();
            assertTrue(id.contains("LWXP"));
        }
    }

    /**
     * Test of getApplicableUpdates method, of class DCMComparer.
     */
    @org.junit.Test
    public void testGetApplicableUpdates_BTWMIX() {
        System.out.println("getApplicableUpdates");
        DCMBundleType inBundleType = DCMBundleType.BTWMIX;
        DCMComparer instance = new DCMComparer();
        int expResult = 7;
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> applicableUpdateMap = 
                instance.getApplicableUpdates(inInventory, inIdentifier, inManifest, inBundleType, DCMConsiderationEnum.REPORT_ALL);
        int result = applicableUpdateMap.get(inIdentifier).size();
        assertEquals(expResult, result);
        int expResultlwxp = 2;
        int expResultlw64 = 5;
        int expResultOther = 0;

        for (Map.Entry<DCMVersionInformation, DCMUpdateInformation> entrySet : applicableUpdateMap.get(inIdentifier).entrySet()) {
            //DCMVersionInformation key = entrySet.getKey();
            DCMUpdateInformation value = entrySet.getValue();
            String id = value.getUniqueIdentifier();
            if (id.contains("LWXP")) {
                expResultlwxp--;
            } else if (id.contains("LW64")) {
                expResultlw64--;
            } else {
                expResultOther--;
            }
        }
        assertEquals(0, expResultlwxp);
        assertEquals(0, expResultlw64);
        assertEquals(0, expResultOther);
    }

    /**
     * Test of getApplicableUpdates method, of class DCMComparer.
     */
    @org.junit.Test
    public void testGetApplicableUpdates_BTALL() {
        System.out.println("getApplicableUpdates");
        DCMBundleType inBundleType = DCMBundleType.BTALL;
        DCMComparer instance = new DCMComparer();
        int expResult = 19;
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> applicableUpdateMap = 
                instance.getApplicableUpdates(inInventory, inIdentifier, inManifest, inBundleType, DCMConsiderationEnum.REPORT_ALL);
        int result = applicableUpdateMap.get(inIdentifier).size();
        
        assertEquals(expResult, result);
        
        int expResultlwxp = 7;
        int expResultlw64 = 5;        
        int expResultllxp = 7;
        int expResultOther = 0;

        for (Map.Entry<DCMVersionInformation, DCMUpdateInformation> entrySet : applicableUpdateMap.get(inIdentifier).entrySet()) {
            //DCMVersionInformation key = entrySet.getKey();
            DCMUpdateInformation value = entrySet.getValue();
            String id = value.getUniqueIdentifier();
            if (id.contains("LWXP")) {
                expResultlwxp--;
            } else if (id.contains("LW64")) {
                expResultlw64--;
            } else if (id.contains("LLXP") || id.contains("LI64")) {
                expResultllxp--;
            } else {
                expResultOther--;
            }
        }
        assertEquals(0, expResultlwxp);
        assertEquals(0, expResultlw64);
        assertEquals(0, expResultllxp);
        assertEquals(0, expResultOther);
    }
    /**
     * Test of getApplicableUpdates method, of class DCMComparer.
     */
    @org.junit.Test
    public void testGetApplicableUpdates_BTLX() {
        System.out.println("getApplicableUpdates");
        DCMBundleType inBundleType = DCMBundleType.BTLX;
        DCMComparer instance = new DCMComparer();
        int expResult = 7;
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> applicableUpdateMap = 
                instance.getApplicableUpdates(inInventory, inIdentifier, inManifest, inBundleType, DCMConsiderationEnum.REPORT_ALL);
        int result = applicableUpdateMap.get(inIdentifier).size();
        
        assertEquals(expResult, result);
        
        int expResultllxp = 7;
        int expResultOther = 0;

        for (Map.Entry<DCMVersionInformation, DCMUpdateInformation> entrySet : applicableUpdateMap.get(inIdentifier).entrySet()) {
            //DCMVersionInformation key = entrySet.getKey();
            DCMUpdateInformation value = entrySet.getValue();
            String id = value.getUniqueIdentifier();
            if (id.contains("LLXP") || id.contains("LI64")) {
                expResultllxp--;
            } else {
                expResultOther--;
            }
        }
        assertEquals(0, expResultllxp);
        assertEquals(0, expResultOther);
    }

    /**
     * Test of getNonCompliantUpdates method, of class DCMComparer. Test of
     * Method for getting the non-compliant updates with respect to the base
     * system
     */
    @org.junit.Test
    public void testGetNonCompliantUpdates() {
        System.out.println("getNonCompliantUpdates");
        File baseFile = new File("src\\test\\resources\\inv_base.xml");
        File targetFile = new File("src\\test\\resources\\inv_target.xml");

        DCMInventory inventoryObj = new DCMInventory();


        File multiSystemInventoryFile = new File("src\\test\\resources\\invoutput.xml");
        DCMMultiSystemInventory multiSystemInventory = inventoryObj.createEmptyMultiSystemInventory();
        inventoryObj.addInventory(targetFile, null, multiSystemInventory, Boolean.FALSE);
        inventoryObj.addInventory(baseFile, null, multiSystemInventory, Boolean.FALSE);
        inventoryObj.serializeToFile(multiSystemInventory, multiSystemInventoryFile);

        DCMComparer instance = new DCMComparer();
        DCMMultiSystemInventory targetMultiSystemInventory = inventoryObj.createEmptyMultiSystemInventory();
        inventoryObj.addInventory(targetFile, null, targetMultiSystemInventory, Boolean.FALSE);
        DCMMultiSystemInventory baseMultiSystemInventory = inventoryObj.createEmptyMultiSystemInventory();
        inventoryObj.addInventory(baseFile, null, baseMultiSystemInventory, Boolean.FALSE);

        HashMap<String, HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>>> result = 
            instance.getNonCompliantUpdates(targetMultiSystemInventory, baseMultiSystemInventory, DCMInstanceConsiderationEnum.CONSIDER_INSTANCE_STRICTLY);
        assertEquals(1, result.size());
        Map<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> mapCompare=result.get("1");
        assertEquals(3, mapCompare.size());
        assertEquals(1,mapCompare.get(DCMComparerResultType.DOWNGRADE).size());
        assertEquals(34,mapCompare.get(DCMComparerResultType.EQUAL).size());
        assertEquals(1,mapCompare.get(DCMComparerResultType.NOT_PRESENT_IN_DESTINATION).size());
    }

    /**
     * Test of getNonCompliantUpdates method of DCMComparer Method for getting
     * the non-compliant updates with respect to the base inventory
     */
    @org.junit.Test
    public void testGetNonComplaintUpdate_2() {
        System.out.println("getNonComplaintUpdates");
        File inBaseInventory = new File("src\\test\\resources\\inv_base.xml");
        File inInventoryToCompare = new File("src\\test\\resources\\inv_target.xml");
        HashMap<DCMComparerResultType, Collection<DCMVersionInformationComparisonResult>> result = new HashMap<>();
        DCMComparer instance = new DCMComparer();
        result = instance.getNonCompliantUpdates(inBaseInventory, inInventoryToCompare, DCMInstanceConsiderationEnum.CONSIDER_INSTANCE_STRICTLY);
        int expResult = 0;
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getApplicableBundles method of class DCMComparer
     */
    @org.junit.Test
    public void testGetApplicableBundles() {
        System.out.println("getApplicableBundles");
        File catalogFile = new File(TestInputs.catalogFileXML);
        Collection<String> systemIdentifier = new HashSet<String>();
        systemIdentifier.add("046F");
        DCMComparer instance = new DCMComparer();
        int expResult = 0;
        Collection<DCMBundle> result = instance.getApplicableBundles(catalogFile, systemIdentifier, DCMBundleType.BTRPM);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getUpdatePackageInformation method of DCMComparer class
     */
    @org.junit.Test
    public void testGetUpdatePackageInformation() {
        System.out.println("getUpdatePackageInformation");
        String packagePath = "src\\test\\resources\\PE1955_ESM_FRMW_LX_R158506.BIN";
        //String packagePath="test\\resources\\PE1955_ESM_FRMW_WIN_R158506.EXE";
        DCMComparer instance = new DCMComparer();
        DCMUpdatePackageInformation result = instance.getUpdatePackageInformation(packagePath);
        String expResult = "R158506";
        assertEquals(expResult, result.getPackageID());
    }

    /**
     * Test of getApplicableUpdates method, of class DCMComparer.
     */
    @Test
    public void testGetApplicableUpdates_3args() {
        System.out.println("getApplicableUpdates");
        File inMultiInventoryFile = new File(TestInputs.inventoryFile);
        File inCatalogFile = new File(TestInputs.catalogFileXML);
        DCMConsiderationEnum inConsideration = DCMConsiderationEnum.REPORT_ALL;
        DCMComparer instance = new DCMComparer();
        int expResult = 0;
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> result = instance.getApplicableUpdates(inMultiInventoryFile, inCatalogFile, inConsideration);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getApplicableUpdates method, of class DCMComparer.
     */
    @Test
    public void testGetApplicableUpdates_4args_4() {
        System.out.println("getApplicableUpdates");
        File inventoryFile = new File("src\\test\\resources\\testfile_MSM.xml");
        DCMInventory inventoryObj = new DCMInventory();
        DCMMultiSystemInventory inMultiSystemInventoryObj = inventoryObj.createEmptyMultiSystemInventory();
        inventoryObj.addInventory(inventoryFile, "", inMultiSystemInventoryObj, Boolean.FALSE);
        File inCatalogFile = new File(TestInputs.catalogFileXML);
        DCMBundleType bundleType = DCMBundleType.BTWMIX;
        DCMConsiderationEnum inConsideration = DCMConsiderationEnum.REPORT_ALL;
        DCMComparer instance = new DCMComparer();
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> expResult = new HashMap<>();
        HashMap<String, HashMap<DCMVersionInformation, DCMUpdateInformation>> result = instance.getApplicableUpdates(inMultiSystemInventoryObj, inCatalogFile, bundleType, inConsideration);
        assertEquals(expResult, result);
    }

    @Test
    public void testCompareCatalogs() {
        System.out.println("compareCatalogs");
        File inSourceFile = new File(TestInputs.catalogFileXML_OLD);
        File inLatestFile = new File(TestInputs.catalogFileXML);
        DCMComparer instance = new DCMComparer();
        DCMCatalogComparisonResult result = instance.compareCatalogs(inSourceFile, inLatestFile);
        assertEquals(result, result);
    }

}
