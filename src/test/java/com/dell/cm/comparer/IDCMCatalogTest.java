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
package com.dell.cm.comparer;

import com.dell.cm.updateinformationmodel.DCMCatalogComparisonResult;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMSystem;
import java.io.File;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raveendra_Madala
 */
public class IDCMCatalogTest {

    public IDCMCatalogTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of parseCatalog method, of class IDCMCatalog.
     */
    @org.junit.Test
    public void testParseCatalog() {
        System.out.println("parseCatalog");
        File inCatalogFile = null;
        IDCMCatalog instance = new IDCMCatalogImpl();
        DCMManifest expResult = null;
        DCMManifest result = instance.parseCatalog(inCatalogFile);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLatestUpdates method, of class IDCMCatalog.
     */
    @org.junit.Test
    public void testGetLatestUpdates() {
        System.out.println("getLatestUpdates");
        File inSourceCatalog = null;
        File inLatestCatalog = null;
        IDCMCatalog instance = new IDCMCatalogImpl();
        DCMCatalogComparisonResult expResult = null;
        Collection<DCMUpdateInformation> result = instance.getLatestUpdates(inSourceCatalog, inLatestCatalog);
        assertEquals(expResult, result);
    }

    public class IDCMCatalogImpl implements IDCMCatalog {

        public DCMManifest parseCatalog(File inCatalogFile) {
            return null;
        }

        public Collection<DCMUpdateInformation> getLatestUpdates(File inSourceCatalog, File inLatestCatalog) {
            return null;
        }

        public DCMManifest parseRepository(String inBaseLocation, List<DCMSystem> inSystemCollection) {
            return null;
        }

    }
}
