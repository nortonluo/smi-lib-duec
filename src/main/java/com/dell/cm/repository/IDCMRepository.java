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

/**
 * Interface for repository creation
 *
 * @author Raveendra_Madala
 */
public interface IDCMRepository {

    /**
     * Method for creating the repository for the selected updates
     *
     * @param inUpdates specifies the updates to be included in the repository
     * @param inManifest specifies the source catalog
     * @param outFolder specifies the output folder
     * @param inParameters specifies the authentication parameters to be used
     * for downloading
     * @param inProxyCollection specifies the collection of proxies to be used
     * @param inProtocol specifies the protocol
     * @return SUCCESS if the repository could be created else appropriate error
     * code is returned
     */
    public int createRepository(Collection<DCMUpdateInformation> inUpdates,
            DCMManifest inManifest, File outFolder,
            DSMAuthenticationParameters inParameters,
            Collection<DSMProxy> inProxyCollection, DSMProtocolEnum inProtocol);

    /**
     * Method to create custom manifest from master manifest
     *
     * @author punit_ghodasara
     * @param inUpdates specifies the updates to be included in the output
     * manifest
     * @param inManifest specifies the source manifest
     * @return custom manifest that includes only inUpdates
     */
    public DCMManifest createManifestFromUpdates(Collection<DCMUpdateInformation> inUpdates, DCMManifest inManifest);

    /**
     * Method to create custom manifest from master manifest and updates
     *
     * @param inUpdates specifies the updates to be included in the output
     * manifest
     * @param inManifest specifies the source manifest
     * @param inBundleType specifies the Chosen Bundle Types
     * @param systems specifies the required target systems
     * @return custom manifest that includes only inUpdates
     */
    public DCMManifest createManifestFromUpdates(Collection<DCMUpdateInformation> inUpdates, DCMManifest inManifest, DCMBundleType inBundleType, DCMSystemCollection systems);

    /**
     * Method for creating the custom SUU repository for the selected updates
     * and the corresponding dependent DUPs
     *
     * @param inUpdates specifies the updates to be included in the repository
     * @param inManifest specifies the source catalog
     * @param inSrcAuthenticationParameters specifies the authentication
     * parameters to be used for downloading
     * @param inProxyCollection specifies the collection of proxies to be used
     * @param inProtocol specifies the Protocol
     * @param inBundleType specifies the bundle type
     * @param inDestinationDSMLocation specifies the destination location
     * @param inBaseLocation specifies the base location
     * @return SUCCESS if the repository could be created else appropriate error
     * code is returned
     */
    public int createSUURepository(Collection<DCMUpdateInformation> inUpdates,
            DCMManifest inManifest, DSMAuthenticationParameters inSrcAuthenticationParameters,
            Collection<DSMProxy> inProxyCollection, DSMProtocolEnum inProtocol,
            DCMBundleType inBundleType, String inBaseLocation, IDSMLocation inDestinationDSMLocation);

    /**
     * Method for creating the custom SUU repository for the selected updates
     * and the corresponding dependent DUPs
     *
     * @param inInventoryList specifies the list of inventories
     * @param inProxyCollection specifies the collection of proxies to be used
     * @param inCatalogAbsolutePath specifies the source catalog
     * @param inSrcAuthParameters specifies the authentication parameters to be
     * used by source for downloading
     * @param inSrcProtocol specifies the protocols used by source
     * @param inBaseLocation specifies the base location
     * @param inBundleType specifies the bundle type
     * @param inDSMLocation specifies the DSM location
     * @return SUCCESS if the repository could be created else appropriate error
     * code is returned
     */
    public int createSUURepository(Collection<String> inInventoryList, Collection<DSMProxy> inProxyCollection,
            String inCatalogAbsolutePath, DSMAuthenticationParameters inSrcAuthParameters,
            DSMProtocolEnum inSrcProtocol, String inBaseLocation, DCMBundleType inBundleType,
            IDSMLocation inDSMLocation);

    /**
     * Method to create custom manifest from master manifest and selected update
     * packages
     *
     * @param inUpdates specifies the update packages to be included in the
     * output manifest
     * @param inManifest specifies the source manifest
     * @param inBundleType specifies the Chosen Bundle Types
     * @param systems specifies the required target systems
     * @return custom manifest that includes only inUpdates
     */
    public DCMManifest createManifestFromUpdatePackages(Collection<DCMUpdatePackageInformation> inUpdates,
            DCMManifest inManifest, DCMBundleType inBundleType, DCMSystemCollection systems);

    /**
     * Method to create custom manifest from master manifest and updates
     *
     * @param inUpdates specifies the updates to be included in the output
     * manifest
     * @param inManifest specifies the source manifest
     * @param inBundleCollection specifies the collection of Chosen Bundle Types
     * @param systems specifies the required target systems
     * @return custom manifest that includes only inUpdates
     */
    public DCMManifest createManifestFromUpdatePackages(Collection<DCMUpdatePackageInformation> inUpdates, DCMManifest inManifest,
            Collection<DCMBundleType> inBundleCollection, DCMSystemCollection systems);

}
