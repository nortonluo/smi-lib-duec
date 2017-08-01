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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.dell.sm.downloader.DSMErrorCodes;

/**
 *
 * @author Rahul_Ranjan2
 */
public class DSMHTTPSDownloaderTest {
    
    /**
     * Default Constructor
     */
    public DSMHTTPSDownloaderTest(){
        
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
    public void setUp(){
        mProxy=new DSMProxy();
        mProxy.setURL("10.116.2.241");
        mProxy.setPortNumber(80);
        mProxy.setProtocol(DSMProtocolEnum.HTTP);
        //mProxy.setProxyUserName("");
        //mProxy.setProxyPassword("");
        mAuthenticationParameters =new DSMAuthenticationParameters();
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
     * Test of setProxyDetails method of DSMHTTPSDownloader Class
     */
    @Test
    public void testsetProxyDetails(){
        System.out.println("setProxyDetails");
        DSMProxy inProxy=null;
        DSMHTTPSDownloader instance=new DSMHTTPSDownloader();
        instance.setProxyDetails(inProxy);
    }
    /**
     * Test of downloadFile method of DSMHTTPSDownloader class
     * @throws Exception
     */
    @Test
    public void testdownloadFile() throws Exception{
        System.out.println("downloadFile");
        String inRelativePath = "catalog\\catalog.xml.gz";
        String inDestinationFolder = "test\\resources\\HTTPSDownload";
        String inBaseLocation = TestInputs.downloadURL;
        DSMHTTPSDownloader instance=new DSMHTTPSDownloader();
        //instance.setProxyDetails(null);
        int expResult=DSMErrorCodes.SUCCESS;
        int result=instance.downloadFile(inRelativePath, inDestinationFolder, inBaseLocation);
        assertEquals(expResult,result);
    }
    /**
     * Test of setAuthenticationParameters method of DSMHTTPSDownloader class
     */
    @Test
    public void testsetAuthenticationParameters(){
        System.out.println("setAuthenticationParameters");
        DSMHTTPSDownloader instance=new DSMHTTPSDownloader();
        instance.setAuthenticationParameters(mAuthenticationParameters);
    }
    private DSMAuthenticationParameters mAuthenticationParameters;
    private DSMProxy mProxy;

}