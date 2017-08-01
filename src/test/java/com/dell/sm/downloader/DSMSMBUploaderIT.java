/* DELL PROPRIETARY INFORMATION
 *
 * This software is confidential.  Dell Inc., or one of its subsidiaries, has
 * supplied this software to you under the terms of a license agreement,
 * nondisclosure agreement or both.  You may not copy, disclose, or use this 
 * software except in accordance with those terms.
 *
 * Copyright 2015 Dell Inc.  All Rights Reserved.
 * 
 * DELL INC. MAKES NO REPRESENTATIONS OR WARRANTIES
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. DELL SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */
 /*
 * Purpose	:  class for 
 *
 * Author	:  Raveendra Madala
 *
 * $LastChangedBy: raveendra_madala $
 *
 * $Date: 2015-02-05 09:46:39 +0530 (Thu, 05 Feb 2015) $
 *
 * $Revision: 2211 $
 *
 */
package com.dell.sm.downloader;

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
public class DSMSMBUploaderIT {

    public DSMSMBUploaderIT() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        inShareName = "100.96.16.180";
        inSharePath = "testShare";
        mAuthenticationParameters = new DSMAuthenticationParameters();
        mAuthenticationParameters.setUserName("Administrator");
        mAuthenticationParameters.setPassword("Dell_123$");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of uploadFile method, of class DSMSMBUploader.
     */
    @Test
    public void testUploadFile() {
        System.out.println("uploadFile");
        String inSourceLocation = "C:\\Users\\Rahul_Ranjan2\\Desktop\\share\\FOLDER02347711M\\2\\SAS-Drive_Firmware_57G3N_WN32_YS0C_A08.EXE";
        String inTargetRelativePath = "duec_test\\FOLDER02347711M\\2\\SAS-Drive_Firmware_57G3N_WN32_YS0C_A08.EXE";
        DSMSMBUploader instance = new DSMSMBUploader(mAuthenticationParameters,inShareName,inSharePath);
        int expResult = 0;
        int result = instance.uploadFile(inSourceLocation, inTargetRelativePath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of exists method, of class DSMSMBUploader.
     */
    //@Test
    public void testExists() {
        System.out.println("exists");
        String inPath = "duec_test";
        DSMSMBUploader instance = new DSMSMBUploader(mAuthenticationParameters,inShareName,inSharePath);
        boolean expResult = true;
        boolean result = instance.exists(inPath);
        assertEquals(expResult, result);
    }
    private DSMAuthenticationParameters mAuthenticationParameters;
    private String inShareName, inSharePath;

}
