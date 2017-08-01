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
public class DCMManifestInformationCollection {

    /**
     * Constructor
     */
    public DCMManifestInformationCollection() {
        mManifestInformationMap = new HashMap<>();
    }

    /**
     * Method for adding a group manifest information to this collection
     *
     * @param inManifestInformation specifies Group Manifest information object
     * @return SUCCESS if the group manifest information could be added else
     * appropriate error code is returned
     */
    public int addManifestInformation(DCMManifestInformation inManifestInformation) {
        if (inManifestInformation == null || null == inManifestInformation.getReleaseID()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mManifestInformationMap.containsKey(inManifestInformation.getReleaseID())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mManifestInformationMap.put(inManifestInformation.getReleaseID(), inManifestInformation);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing a group manifest information from this collection
     *
     * @param inIdentifier specifies the identifier of the group manifest
     * information being removed
     * @return SUCCESS if the group manifest information could be removed else
     * appropriate error code is returned
     */
    public int removeManifestInformation(String inIdentifier) {
        if (inIdentifier == null || inIdentifier.isEmpty()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mManifestInformationMap.containsKey(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMManifestInformation removedManifestInformation;
        removedManifestInformation = mManifestInformationMap.remove(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting all the group manifest informations
     *
     * @return the group manifest informations
     */
    public Collection<DCMManifestInformation> getManifestInformations() {
        return mManifestInformationMap.values();
    }

    /**
     * Method for getting the group manifest information with the given
     * identifier
     *
     * @param inIdentifier specifies the identifier being sought
     * @return group manifest information
     */
    public DCMManifestInformation getManifestInformation(String inIdentifier) {
        if (inIdentifier == null || inIdentifier.isEmpty()) {
            return null;
        }
        return mManifestInformationMap.get(inIdentifier);
    }
    private final HashMap<String, DCMManifestInformation> mManifestInformationMap;
}
