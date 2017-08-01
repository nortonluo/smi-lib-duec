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

import com.dell.cm.comparer.DCMUpdateInformation;
import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMManifest;
import com.dell.cm.updateinformationmodel.DCMSystemCollection;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.downloader.DSMProtocolEnum;
import com.dell.sm.downloader.DSMProxy;
import com.dell.sm.downloader.IDSMLocation;
import java.io.File;
import java.util.Collection;
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
public class IDCMRepositoryTest {

    public IDCMRepositoryTest() {
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
     * Test of createRepository method, of class IDCMRepository.
     */
    @Test
    public void testCreateRepository() {
        System.out.println("createRepository");
        Collection<DCMUpdateInformation> inUpdates = null;
        DCMManifest inManifest = null;
        File outFolder = null;
        IDCMRepository instance = new IDCMRepositoryImpl();
        int expResult = 0;
        int result = instance.createRepository(inUpdates, inManifest, outFolder, null, null, DSMProtocolEnum.FTP);
        assertEquals(expResult, result);
    }

    public class IDCMRepositoryImpl implements IDCMRepository {

        public int createRepository(Collection<DCMUpdateInformation> inUpdates,
                DCMManifest inManifest, File outFolder,
                DSMAuthenticationParameters inParameters,
                Collection<DSMProxy> inProxyCollection, DSMProtocolEnum inProtocol) {
            return 0;
        }

        @Override
        public DCMManifest createManifestFromUpdates(Collection<DCMUpdateInformation> inUpdates, DCMManifest inManifest) {
            return null;
        }

        public int createSUURepository(Collection<DCMUpdateInformation> inUpdates,
                DCMManifest inManifest, DSMAuthenticationParameters inSrcAuthenticationParameters,
                Collection<DSMProxy> inProxyCollection, DSMProtocolEnum inProtocol,
                DCMBundleType inBundleType, String inBaseLocation, IDSMLocation inDestinationDSMLocation) {
            return 0;
        }

        public int createSUURepository(Collection<String> inInventoryList, Collection<DSMProxy> inProxyCollection,
                String inCatalogAbsolutePath, DSMAuthenticationParameters inSrcAuthParameters,
                DSMProtocolEnum inSrcProtocol, String inBaseLocation, DCMBundleType inBundleType,
                IDSMLocation inDSMLocation) {
            return 0;
        }

        @Override
        public DCMManifest createManifestFromUpdates(Collection<DCMUpdateInformation> inUpdates, DCMManifest inManifest, DCMBundleType inBundleType, DCMSystemCollection systems) {
            return null; //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public DCMManifest createManifestFromUpdatePackages(Collection<DCMUpdatePackageInformation> inUpdates, DCMManifest inManifest, DCMBundleType inBundleType, DCMSystemCollection systems) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public DCMManifest createManifestFromUpdatePackages(Collection<DCMUpdatePackageInformation> inUpdates, DCMManifest inManifest, Collection<DCMBundleType> inBundleType, DCMSystemCollection systems) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
