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
 * @author Rahul_Ranjan2
 */
public class DSMCIFSDownloaderIT {
    
    private DSMAuthenticationParameters mAuthenticationParameters;
    /*
    * Default Constructor
    */
    public DSMCIFSDownloaderIT(){
        
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
        mAuthenticationParameters=new DSMAuthenticationParameters();
        //mAuthenticationParameters.setDomainName("tejd");
        mAuthenticationParameters.setUserName(TestInputs.shareUserName);
        mAuthenticationParameters.setPassword(TestInputs.sharePassword);
    }
    
    /**
     * Method to allocate resources at the test
     */
    @After
    public void tearDown(){
        
    }
    /**
     * Method to test the DownloadFile method of DSMCIFSDownload Class
     */
    @Test
    public void testDownloadFile() throws Exception{
        System.out.println("DownloadFile");
        //String inRelativePath="SAS-Drive_Firmware_TPF6G_WN32_D906_A02.EXE";
        //String inRelativePath="FOLDER00232516M\\9\\Firmware_681JN_WN32_1.00_A00.EXE";
        String inRelativePath="duec_test\\catalog.xml.gz";
        String inDestinationFolder="src\\test\\resources\\CIFSDownload";
        String inBaseLocation=TestInputs.shareIp+"\\"+TestInputs.shareLocation;
        int expResult=DCMErrorCodes.SUCCESS;
        DSMCIFSDownloader instance=new DSMCIFSDownloader();
        instance.setAuthenticationParameters(mAuthenticationParameters);
        int result=instance.downloadFile(inRelativePath, inDestinationFolder, inBaseLocation);
        assertEquals(expResult,result);
    }
    /**
     * Method to test the setAuthenticationParameters method of DSMCIFSDownload Class
     */
    @Test
    public void testsetAuthenticationParameters(){
        System.out.println("setAuthenticationParamteres");
        DSMCIFSDownloader instance=new DSMCIFSDownloader();
        instance.setAuthenticationParameters(mAuthenticationParameters);
    }
}
