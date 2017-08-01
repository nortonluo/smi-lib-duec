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
package com.dell.cm.updateinformationmodel;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Ujjwal_Kumar_Gupta
 */
public class DCMGroupManifestCollection {

    /**
     * Constructor
     */
    public DCMGroupManifestCollection() {
        mGroupManifestMap = new HashMap<>();
    }

    /**
     * Method for adding a group manifest to this collection
     *
     * @param inGroupManifest specifies Group Manifest object
     * @return SUCCESS if the group manifest could be added else appropriate
     * error code is returned
     */
    public int addGroupManifest(DCMGroupManifest inGroupManifest) {
        if (inGroupManifest == null || null == inGroupManifest.getId()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mGroupManifestMap.containsKey(inGroupManifest.getId())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mGroupManifestMap.put(inGroupManifest.getId(), inGroupManifest);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing a group manifest from this collection
     *
     * @param inIdentifier specifies the identifier of the group manifest being
     * removed
     * @return SUCCESS if the group manifest could be removed else appropriate
     * error code is returned
     */
    public int removeGroupManifest(String inIdentifier) {
        if (inIdentifier == null || inIdentifier.isEmpty()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mGroupManifestMap.containsKey(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMGroupManifest removedGroupManifest;
        removedGroupManifest = mGroupManifestMap.remove(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting all the group manifests
     *
     * @return the bundles
     */
    public Collection<DCMGroupManifest> getGroupManifests() {
        return mGroupManifestMap.values();
    }

    /**
     * Method for getting the group manifest with the given identifier
     *
     * @param inIdentifier specifies the identifier being sought
     * @return group manifest
     */
    public DCMGroupManifest getGroupManifest(String inIdentifier) {
        if (inIdentifier == null || inIdentifier.isEmpty()) {
            return null;
        }
        return mGroupManifestMap.get(inIdentifier);
    }

    private final HashMap<String, DCMGroupManifest> mGroupManifestMap;
}
