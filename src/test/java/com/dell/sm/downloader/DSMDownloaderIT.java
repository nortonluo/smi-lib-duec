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
package com.dell.sm.downloader;

import com.dell.cm.testParameters.TestInputs;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Downloader Interface
 *
 * @author VIVEKANANDH_N_R
 */
public class DSMDownloaderIT {

    /**
     * Default Constructor for DSMDownloaderTest
     */
    public DSMDownloaderIT() {
    }

    /**
     * Method to set up resources for class
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * Method to de allocate resources at class level
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Method to setup resources required for the tests
     */
    @Before
    public void setUp() {

        mParameters = null;
        mProxyCollection = null;
    }

    /**
     * Method to de allocate resources at for the tests
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of initialize method, of class DSMDownloader.
     */
    @org.junit.Test
    public void testInitialize() {
        System.out.println("Initialize");

        DSMDownloader instance = new DSMDownloader();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = instance.initialize(mParameters, mProxyCollection);
        assertEquals(expResult, result);
    }

    /**
     * Test of isInitialized method, of class DSMDownloader.
     */
    @org.junit.Test
    public void testIsInitialized() {
        System.out.println("IsInitialized");
        DSMDownloader instance = new DSMDownloader();
        Boolean expResult = false;
        Boolean result = instance.isInitialized();
        assertEquals(expResult, result);
    }

    /**
     * Test of downloadCatalog method, of class DSMDownloader.
     */
    @org.junit.Test
    public void testDownloadCatalog() {
        System.out.println("DownloadCatalog");
        DSMDownloader instance = new DSMDownloader();
        int expResult = DCMErrorCodes.SUCCESS;
        try {
            Files.deleteIfExists(new File("catalog/catalog.cab").toPath());
        } catch (IOException ex) {
            Logger.getLogger(DSMDownloaderIT.class.getName()).log(Level.SEVERE, null, ex);
        }

        int result = -1;
        try {
            result = instance.downloadCatalog();
        } catch (Exception ex) {
            Logger.getLogger(DSMDownloaderIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of downloadFile method, of class DSMDownloader.
     */
    @org.junit.Ignore
    public void testDownloadFile_String() {
        System.out.println("DownloadFile_String");
        String inRelativePath = "catalog/catalog.xml.gz";
        DSMDownloader instance = new DSMDownloader();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = -1;
        try {
            result = instance.downloadFile(inRelativePath);
        } catch (Exception ex) {
            Logger.getLogger(DSMDownloaderIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of downloadFile method, of class DSMDownloader.
     *
     * @throws java.lang.Exception
     */
    @org.junit.Ignore
    public void testDownloadFile_String_String() throws Exception {
        System.out.println("DownloadFile_String");
        String inRelativePath = "catalog/catalog.xml.gz";
        String inDestinationFolder = "src//test\\resources";
        DSMDownloader instance = new DSMDownloader();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = instance.downloadFile(inRelativePath, inDestinationFolder);
        assertEquals(expResult, result);
    }

    /**
     * Test of downloadFile method, of class DSMDownloader.
     *
     * @throws java.lang.Exception
     */
    @org.junit.Ignore
    public void testDownloadFile_3args() throws Exception {
        System.out.println("DownloadFile_3args");
        String inRelativePath = "catalog//catalog.xml.gz";
        String inDestinationFolder = "src//test//resources";
        String inBaseLocation = TestInputs.downloadURL;
        DSMDownloader instance = new DSMDownloader();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = instance.downloadFile(inRelativePath, inDestinationFolder, inBaseLocation);
        assertEquals(expResult, result);
    }

    /**
     * Test of downloadFiles method, of class DSMDownloader.
     */
    @org.junit.Ignore
    public void testDownloadFiles_Collection() {
        System.out.println("DownloadFiles");
        Collection<String> inRelativePaths = new HashSet<String>();
        inRelativePaths.add("catalog//catalog.xml.gz");
        inRelativePaths.add("catalog/Catalog.cab");

        DSMDownloader instance = new DSMDownloader();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = -1;
        try {
            result = instance.downloadFiles(inRelativePaths);
        } catch (Exception ex) {
            Logger.getLogger(DSMDownloaderIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of downloadFiles method, of class DSMDownloader.
     */
    @org.junit.Test
    public void testDownloadFiles_Collection_String() {
        System.out.println("DownloadFiles");
        Collection<String> inRelativePaths = new HashSet<String>();
        inRelativePaths.add("catalog/catalog.xml.gz.sign");
        inRelativePaths.add("catalog/catalog.xml.gz");
        String inDestinationFolder = "catalog/testcollection";
        for (String file : inRelativePaths) {
            try {
                Files.deleteIfExists(new File(inDestinationFolder + File.separator + file).toPath());
            } catch (IOException ex) {
                Logger.getLogger(DSMDownloaderIT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        DSMDownloader instance = new DSMDownloader();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = -1;
        try {
            result = instance.downloadFiles(inRelativePaths, inDestinationFolder);
        } catch (Exception ex) {
            Logger.getLogger(DSMDownloaderIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of downloadFiles method, of class DSMDownloader.
     */
    @org.junit.Ignore
    public void testDownloadFiles_3args() {
        System.out.println("DownloadFiles");
        Collection<String> inRelativePaths = new HashSet<String>();
        inRelativePaths.add("catalog//catalog.xml.gz");
        inRelativePaths.add("catalog/Catalog.cab");
        String inDestinationFolder = "src//test//resources";
        String inBaseLocation = TestInputs.downloadURL;
        DSMDownloader instance = new DSMDownloader();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = -1;
        try {
            result = instance.downloadFiles(inRelativePaths, inDestinationFolder, inBaseLocation);
        } catch (Exception ex) {
            Logger.getLogger(DSMDownloaderIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of downloadFiles method, of class DSMDownloader.
     *
     * @throws java.lang.Exception
     */
    @org.junit.Ignore
    public void testDownloadFiles_4args() throws Exception {
        System.out.println("DownloadFiles");
        Collection<String> inRelativePaths = new HashSet<String>();
        inRelativePaths.add("catalog//catalog.xml.gz");
        inRelativePaths.add("catalog/Catalog.cab");
        String inDestinationFolder = "catalog/test1";
        String inBaseLocation = TestInputs.downloadURL;
        DSMProtocolEnum inProtocol = DSMProtocolEnum.HTTP;
        DSMDownloader instance = new DSMDownloader();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = instance.downloadFiles(inRelativePaths, inDestinationFolder, inBaseLocation, inProtocol);
        assertEquals(expResult, result);
    }

    /**
     * Test of downloadFile method, of class DSMDownloader.
     *
     * @throws java.lang.Exception
     */
    @org.junit.Test
    public void testDownloadFile_4args() throws Exception {
        System.out.println("DownloadFile");
        String inRelativePath = "catalog/catalog.cab";
        String inDestinationFolder = "catalog/test2";
        String inBaseLocation = TestInputs.downloadURL;
        try {
            Files.deleteIfExists(new File(inDestinationFolder + File.separator + inRelativePath).toPath());
        } catch (IOException ex) {
            Logger.getLogger(DSMDownloaderIT.class.getName()).log(Level.SEVERE, null, ex);
        }

        DSMProtocolEnum inProtocol = DSMProtocolEnum.HTTP;
        DSMDownloader instance = new DSMDownloader();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = instance.downloadFile(inRelativePath, inDestinationFolder, inBaseLocation, inProtocol);
        assertEquals(expResult, result);
    }

    //private variables
    private DSMAuthenticationParameters mParameters;
    private Collection<DSMProxy> mProxyCollection;

}
