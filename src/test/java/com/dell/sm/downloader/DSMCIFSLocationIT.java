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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Rahul_Ranjan2
 */
public class DSMCIFSLocationIT {
    
    /**
     * Default Constructor
     */
    public DSMCIFSLocationIT(){
        
    }
    /**
     * Method to set up the resources for class
     */
    @BeforeClass
    public static void setUpClass(){
        
    }
    /**
     * Method to allocate resources at class level
     */
    @AfterClass
    public static void tearDownClass(){
        
    }
    /**
     * Method to set up resources required for the tests.
     */
    @Before
    public void setUp(){
        mAuthenticationParameters=new DSMAuthenticationParameters();
        mAuthenticationParameters.setUserName(TestInputs.shareUserName);
        mAuthenticationParameters.setPassword(TestInputs.sharePassword);
        inShareName=TestInputs.shareIp;
        inSharePath=TestInputs.shareLocation;
    }
    /**
     * Method to allocate resources at the test
     */
    @After
    public void tearDown(){
        
    }
    @Test
    public void testExists(){
        //System.out.println("Path Exists");
        String inPath="duec_test";
        DSMCIFSLocation instance=new DSMCIFSLocation(mAuthenticationParameters,inShareName,inSharePath);
        System.out.println("Path Exists: "+instance.exists(inPath));
    }
    @Test
    public void testUploadFile(){
        System.out.println("Uploading File");
        String inTargetRelativePath="duec_test\\catalog.xml.gz";
        String inSourceLocation="src\\test\\resources\\CIFSDownload\\cache\\Catalog\\catalog.xml.gz";
        DSMCIFSLocation instance=new DSMCIFSLocation(mAuthenticationParameters,inShareName,inSharePath);
        int expResult=DSMErrorCodes.SUCCESS;
        int result=-1;
        try{
            result=instance.uploadFile(inSourceLocation, inTargetRelativePath);
        }
        catch(Exception ex){
             Logger.getLogger(DSMCIFSLocationIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult,result);
    }
    private DSMAuthenticationParameters mAuthenticationParameters;
    private String inShareName,inSharePath;
}
