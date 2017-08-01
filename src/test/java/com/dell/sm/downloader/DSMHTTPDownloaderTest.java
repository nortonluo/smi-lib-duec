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
 *
 * @author VIVEKANANDH_N_R
 */
public class DSMHTTPDownloaderTest {

    /**
     * Default Constructor for DSMHTTPDownloaderTest
     */
    public DSMHTTPDownloaderTest() {
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
        mProxy.setURL("10.116.2.241");
        mProxy.setPortNumber(80);
        mProxy.setProtocol(DSMProtocolEnum.HTTP);
        mAuthenticationParameters = new DSMAuthenticationParameters();
        mAuthenticationParameters.setUserName("");
        mAuthenticationParameters.setPassword("");
    }

    /**
     * Method to de allocate resources at for the tests
     */
    @After
    public void tearDown() {

    }

    /**
     * Test of setProxyDetails method, of class DSMHTTPDownloader.
     */
    @Test
    public void testSetProxyDetails() {
        System.out.println("setProxyDetails");
        DSMProxy dsmProxy = null;
        DSMHTTPDownloader instance = new DSMHTTPDownloader();
        instance.setProxyDetails(dsmProxy);

    }

    /**
     * Test of downloadFile method, of class DSMHTTPDownloader.
     */
    @Test
    public void testDownloadFile() throws Exception {
        System.out.println("downloadFile");
        String inRelativePath = "Catalog\\Catalog.xml.gz";
        String inDestinationFolder = "src\\test\\resources\\run";
        String inBaseLocation = TestInputs.downloadURL;
        DSMHTTPDownloader instance = new DSMHTTPDownloader();
        //instance.setAuthenticationParameters(mAuthenticationParameters);
        //instance.setProxyDetails(mProxy);
        int expResult = DCMErrorCodes.SUCCESS;
        int result = instance.downloadFile(inRelativePath, inDestinationFolder, inBaseLocation);
        assertEquals(expResult, result);

    }

    /**
     * Test of setAuthenticationParameters method, of class DSMHTTPDownloader.
     */
    @Test
    public void testSetAuthenticationParameters() {
        System.out.println("setAuthenticationParameters");
        DSMHTTPDownloader instance = new DSMHTTPDownloader();
        instance.setAuthenticationParameters(mAuthenticationParameters);

    }

//private variables
    private DSMAuthenticationParameters mAuthenticationParameters;
    private DSMProxy mProxy;
}
