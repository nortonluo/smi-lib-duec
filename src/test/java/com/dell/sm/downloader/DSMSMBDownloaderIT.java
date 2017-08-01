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
public class DSMSMBDownloaderIT {
    
    public DSMSMBDownloaderIT() {
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
     * Test of downloadFile method, of class DSMSMBDownloader.
     */
    //@Test
    public void testDownloadFile_3args() throws Exception {
        System.out.println("downloadFile");
        String inRelativePath = "";
        String inDestinationFolder = "";
        String inBaseLocation = "";
        DSMSMBDownloader instance = new DSMSMBDownloader();
        int expResult = 0;
        int result = instance.downloadFile(inRelativePath, inDestinationFolder, inBaseLocation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadFile method, of class DSMSMBDownloader.
     */
    @Test
    public void testDownloadFile_String_String() throws Exception {
        System.out.println("downloadFile");
        String inURL = inShareName+"/"+inSharePath+"\\FOLDER04175008M\\1\\SAS-RAID_Firmware_NH55C_LN_25.5.2.0001_A09.BIN";
        String inDestinationFile = "C:\\Users\\Rahul_Ranjan2\\Desktop\\share\\SMB";
        DSMSMBDownloader instance = new DSMSMBDownloader();
        int expResult = 0;
        instance.setAuthenticationParameters(mAuthenticationParameters);
        int result = instance.downloadFile(inURL, inDestinationFile);
        assertEquals(expResult, result);    }

    /**
     * Test of setProxyDetails method, of class DSMSMBDownloader.
     */
    //@Test
    public void testSetProxyDetails() {
        System.out.println("setProxyDetails");
        DSMProxy inProxy = null;
        DSMSMBDownloader instance = new DSMSMBDownloader();
        instance.setProxyDetails(inProxy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAuthenticationParameters method, of class DSMSMBDownloader.
     */
    //@Test
    public void testSetAuthenticationParameters() {
        System.out.println("setAuthenticationParameters");
        DSMAuthenticationParameters inAuthenticationParameters = null;
        DSMSMBDownloader instance = new DSMSMBDownloader();
        instance.setAuthenticationParameters(inAuthenticationParameters);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     private DSMAuthenticationParameters mAuthenticationParameters;
    private String inShareName, inSharePath;
}
