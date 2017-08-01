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
public class DSMLocationTest {
    /**
     * Default Constructor
     */
    public DSMLocationTest(){
        
    }
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
        
    }
    /**
     * Method to allocate resources at the test
     */
    @After
    public void tearDown(){
        
    }
    /**
     * Method to test uploadFile method of DSMLocation class
     * @throws Exception 
     */
    @Test
    public void testuploadFile() throws Exception{
        System.out.println("Upload File");
        String inSourceLocation=TestInputs.catalogFileXML;
        inTargetRelativePath="test\\resources\\DSMLocationTest";
        String targetFile="Catalog.xml";
        int expResult=DSMErrorCodes.SUCCESS;
        DSMLocation instance=new DSMLocation(inTargetRelativePath);
        int result=instance.uploadFile(inSourceLocation, targetFile);
        assertEquals(expResult,result);
    }
    private String inTargetRelativePath;
}
