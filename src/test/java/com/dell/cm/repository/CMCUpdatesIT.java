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

import com.dell.cm.testParameters.TestInputs;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMJobStatus;
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
public class CMCUpdatesIT {
    
    public CMCUpdatesIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cmcIpAddr=TestInputs.CMCIp;
        userName=TestInputs.userName;
        password=TestInputs.password;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of updateCMC method, of class CMCUpdates.
     */
    //@Test
    public void testUpdateCMC() {
        System.out.println("updateCMC");
        String racadmPath = TestInputs.racadmPath;
        String firmwareFileIP=TestInputs.ftpURL;
        String firmwareFilePath="FOLDER03541812M\\1\\fx2_cmc_1.32_A00.bin";
        String ftpUsername=null;
        String ftpPassword=null;
        CMCUpdates instance = new CMCUpdates();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = instance.updateCMC(racadmPath,firmwareFileIP,firmwareFilePath,ftpUsername,ftpPassword , cmcIpAddr, userName, password,TestInputs.shareUserName,TestInputs.sharePassword);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStatus method, of class CMCUpdates.
     */
    //@Test
    public void testGetStatus() throws Exception {
        System.out.println("getStatus");
        CMCUpdates instance = new CMCUpdates();
        DCMJobStatus expResult = DCMJobStatus.COMPLETED;
        instance.copyIntoLocal(TestInputs.racadmPath,TestInputs.shareUserName,TestInputs.sharePassword);
        DCMJobStatus result = instance.getStatus(cmcIpAddr, userName, password);
        assertEquals(expResult, result);
    }
    /**
     * Test of copyIntoLocal method
     * @throws Exception 
     */
    @Test
    public void testCopyIntoLocal() throws Exception{
        CMCUpdates instance = new CMCUpdates();
        int expResult = DCMErrorCodes.SUCCESS;
        int result = instance.copyIntoLocal(TestInputs.racadmPath, TestInputs.shareUserName, TestInputs.sharePassword);
        assertEquals(expResult,result);
    }
    private String cmcIpAddr;
    private String userName;
    private String password;
    
}
