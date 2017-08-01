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
package com.dell.cm.repository;

import com.dell.cm.comparer.DCMCatalog;
import com.dell.cm.comparer.DCMComparer;
import com.dell.cm.comparer.DCMConsiderationEnum;
import com.dell.cm.comparer.DCMUpdateInformation;
import com.dell.cm.inventory.DCMInventory;
import com.dell.cm.testParameters.TestInputs;
import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMSystemInstance;
import com.dell.cm.updateinformationmodel.DCMSystemInstanceCollection;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
public class DCMCatalogWriterIT {

    public DCMCatalogWriterIT() {
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
     * Test of dateToStringFormat method, of class DCMCatalogWriter.
     */
    @Test
    public void testDateToStringFormat() {
        System.out.println("dateToStringFormat");
        Date inDate = new Date();
        DCMCatalogWriter instance = new DCMCatalogWriter();
        String expResult = null;
        String result = instance.dateToStringFormat(inDate,DCMConstants.INVENTORY);
        assertEquals(result, result);
    }
    @Test
   public void testDateToStringFormatForReleaseDate(){
        System.out.println("dateToStringFormatForReleaseDate");
        Date inDate=new Date();
        DCMCatalogWriter instance=new DCMCatalogWriter();
        String result=instance.dateToStringFormatForReleaseDate(inDate);
        String expResult=null;
        assertEquals(result,result);
    }

    /**
     * Test of writeCatalog method, of class DCMCatalogWriter.
     */
    @Test
    public void testWriteCatalog() {
        System.out.println("writeCatalog");
        File catalogFile = new File(TestInputs.catalogFileXML);
        DCMCatalog catalogObj = new DCMCatalog();
        DCMManifest inManifest = catalogObj.parseCatalog(catalogFile);
        System.out.println(inManifest.getVersion());
        File outFile = new File("src\\test\\resources\\temp_catalog.xml");
        DCMCatalogWriter instance = new DCMCatalogWriter();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = instance.writeCatalog(inManifest, outFile);
        System.out.println("CatalogWriterTest Result: "+result);
        assertEquals(expResult, result);
    }

}
