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
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for FTP Download
 *
 * @author VIVEKANANDH_N_R
 */
public class DSMFTPDownloaderIT {

    /**
     * Default Constructor for DSMFTPDownloaderTest
     */
    public DSMFTPDownloaderIT() {
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

        mProxy = new DSMProxy();
        mProxy.setURL(TestInputs.ftpURL);
        mProxy.setPortNumber(22);
        mProxy.setProtocol(DSMProtocolEnum.FTP);
        mAuthenticationParameters = new DSMAuthenticationParameters();
        mAuthenticationParameters.setUserName("anonymous");
        mAuthenticationParameters.setPassword(" ");
    }

    /**
     * Method to de allocate resources at for the tests
     */
    @After
    public void tearDown() {

    }

    /**
     * Test of setProxyDetails method, of class DSMFTPDownloader.
     */
    @Test
    public void testSetProxyDetails() {
        System.out.println("setProxyDetails");
        DSMFTPDownloader instance = new DSMFTPDownloader();
        instance.setProxyDetails(this.mProxy);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of downloadFile method, of class DSMFTPDownloader.
     */
    @Test
    public void testDownloadFile() throws Exception {
        System.out.println("downloadFile");
        String inRelativePath = "Catalog\\Catalog.xml.gz";
        String inDestinationFolder = "src\\test\\resources\\run";
        String inBaseLocation = TestInputs.ftpURL;
        DSMFTPDownloader instance = new DSMFTPDownloader();
        //instance.setProxyDetails(mProxy);
        //  instance.setAuthenticationParameters(inAuthenticationParameters);
        int expResult = DCMErrorCodes.SUCCESS;
        int result = instance.downloadFile(inRelativePath, inDestinationFolder, inBaseLocation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of setAuthenticationParameters method, of class DSMFTPDownloader.
     */
    @Test
    public void testSetAuthenticationParameters() {
        System.out.println("setAuthenticationParameters");
        DSMFTPDownloader instance = new DSMFTPDownloader();
        instance.setAuthenticationParameters(this.mAuthenticationParameters);
        // TODO review the generated test code and remove the default call to fail.
        //   fail("The test case is a prototype.");
    }

    //private variables
    private DSMAuthenticationParameters mAuthenticationParameters;
    private DSMProxy mProxy;
}
