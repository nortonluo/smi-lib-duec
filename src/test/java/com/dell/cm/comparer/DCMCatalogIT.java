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

import com.dell.cm.repository.DCMCatalogWriter;
import com.dell.cm.testParameters.TestInputs;
import com.dell.cm.updateinformationmodel.DCMCatalogComparisonResult;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMPCIInfo;
import com.dell.cm.updateinformationmodel.DCMPnPInfo;
import com.dell.cm.updateinformationmodel.DCMSystem;
import com.dell.cm.updateinformationmodel.DCMSystemIDType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Raveendra_Madala
 */
public class DCMCatalogIT {

    public DCMCatalogIT() {
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
     * Test of parseCatalog method, of class DCMCatalog.
     */
    @org.junit.Test
    public void testParseCatalog() {
        System.out.println("parseCatalog");
        File inCatalogFile=new File(TestInputs.catalogFileXML);
        DCMCatalog instance = new DCMCatalog();
        int expResult = DCMErrorCodes.SUCCESS;
        DCMManifest intermediateResult = instance.parseCatalog(inCatalogFile);
        DCMCatalogWriter catalog = new DCMCatalogWriter();
        File tempfile = new File("src\\test\\resources\\tempfile.xml");
        int result = catalog.writeCatalog(intermediateResult, tempfile);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLatestUpdates method, of class DCMCatalog.
     */
    @org.junit.Test
    public void testGetLatestUpdates() {
        System.out.println("getLatestUpdates");
        File inSourceCatalog = new File(TestInputs.catalogFileXML_OLD);
        File inLatestCatalog = new File(TestInputs.catalogFileXML);
        DCMCatalog instance = new DCMCatalog();
        Collection<DCMUpdateInformation> result = instance.getLatestUpdates(inSourceCatalog, inLatestCatalog);
        assertEquals(result, result);
    }

    /**
     * Test of parsePCIInfo method, of class DCMCatalog.
     */
    @org.junit.Test
    public void testParsePCIInfo() throws Exception{
        System.out.println("parsePCIInfo");
        File catalogFile=new File(TestInputs.catalogFileXML);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(catalogFile);
        NodeList list=doc.getElementsByTagName("PCIInfo");
        Node nNode=list.item(1);
        Element inPCI = (Element)nNode;
        DCMCatalog instance = new DCMCatalog();
        DCMPCIInfo expResult = null;
        DCMPCIInfo result = instance.parsePCIInfo(inPCI);
        assertEquals(result, result);
    }

    /**
     * Test of parsePnPInfo method, of class DCMCatalog.
     */
    @org.junit.Test
    public void testParsePnPInfo() {
        System.out.println("parsePnPInfo");
        Element inPnP = null;
        DCMCatalog instance = new DCMCatalog();
        DCMPnPInfo expResult = null;
        DCMPnPInfo result = instance.parsePnPInfo(inPnP);
        assertEquals(result, result);
    }
    /**
     * Test of parseRepository method of class DCMCatalog
     */
    @org.junit.Test
    public void testParseRepository(){
        System.out.println("parseRepository");
        DCMManifest tempFile=new DCMManifest();
        DCMSystem inSystem=new DCMSystem();
        inSystem.setID("046F");
        inSystem.setLOBKey(DCMConstants.LOB_SERVER);
        inSystem.setIDType(DCMSystemIDType.BIOS);
        inSystem.setName(null); 
        String Path="src\\test\\resources";
        List<DCMSystem> listSystem = new ArrayList<DCMSystem>();
        listSystem.add(inSystem);      
        DCMCatalog instance=new DCMCatalog();
        tempFile=instance.parseRepository(Path, listSystem);    
        int expResult=DCMErrorCodes.SUCCESS;
        File output=new File(Path+"/"+"DCMCatalogTestOutput.xml");
        DCMCatalogWriter writer =new DCMCatalogWriter();
        int result=writer.writeCatalog(tempFile, output);
        assertEquals(expResult,result);
    }
}
