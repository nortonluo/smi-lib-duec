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
import java.util.HashSet;

/**
 * class for representing the collection of version information collections
 *
 * @author Raveendra_Madala
 */
public class DCMVersionInformationCollectionOfCollection {

    /**
     * Constructor
     */
    public DCMVersionInformationCollectionOfCollection() {
        mVersionInformationCollectionMap = new HashMap<>();
    }

    /**
     * Method for getting the version information collection with the given key
     *
     * @param inIdentifier specifies the identifier of the version information
     * collection being sought
     * @return the version information collection with the given identifier
     */
    public DCMVersionInformationCollection getVersionInformationCollectio(int inIdentifier) {
        DCMVersionInformationCollection retVal = null;
        if (mVersionInformationCollectionMap.containsKey(inIdentifier)) {
            retVal = mVersionInformationCollectionMap.get(inIdentifier);
        }
        return retVal;
    }

    /**
     * Method for adding a version information collection
     *
     * @param inCollection specifies the collection being added
     * @return SUCCESS if the lob could be added else appropriate error code is
     * returned
     */
    public int addVersionInformationCollection(DCMVersionInformationCollection inCollection) {
        if (null == inCollection) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mVersionInformationCollectionMap.containsKey(inCollection.getUniqueIdentifier())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mVersionInformationCollectionMap.put(inCollection.getUniqueIdentifier(), inCollection);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the version information collection
     *
     * @param inIdentifier specifies the identifier of the version information
     * collection being removed
     * @return SUCCESS if the version information collection could be removed
     * else appropriate error code is returned
     */
    public int removeLineOfBusiness(int inIdentifier) {
        if (!mVersionInformationCollectionMap.containsKey(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMVersionInformationCollection removedVersionInformationCollection;
        removedVersionInformationCollection = mVersionInformationCollectionMap.remove(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting all the version information collection
     *
     * @return the version information collections
     */
    public Collection<DCMVersionInformationCollection> getVersionInformationCollections() {
        return mVersionInformationCollectionMap.values();
    }

    /**
     * Method for getting the system identifiers with the specified identifier
     * or in the group specified by the identifier
     *
     * @param inIdentifier specifies either the system or group identifier
     * @return the collection of system identifiers
     */
    public Collection<String> getGroupSystemInstanceIdentifiers(String inIdentifier) {
        HashSet<String> retVal = new HashSet<String>();
        for (DCMVersionInformationCollection versionInfoCollection : mVersionInformationCollectionMap.values()) {
            if (versionInfoCollection.getGroupIdentifier() != null && versionInfoCollection.getGroupIdentifier().equals(inIdentifier)) {
                Collection<String> systemInstanceIdentifiers = versionInfoCollection.getAllSystemInstanceIdentifiers();
                retVal.addAll(systemInstanceIdentifiers);
            }
        }
        return retVal;
    }

    /**
     * Method for getting the version information collection for the given
     * system instance identifier
     *
     * @param inSystemInstnaceIdentifier specifies the system instance
     * identifier for which the version information collection is being sought
     * @return the version information collection
     */
    public DCMVersionInformationCollection getVersionInformationCollection(String inSystemInstnaceIdentifier) {
        if (inSystemInstnaceIdentifier == null || inSystemInstnaceIdentifier.isEmpty()) {
            return null;
        }
        for (DCMVersionInformationCollection versionInfoCollection : mVersionInformationCollectionMap.values()) {
            DCMVersionInformationCollection verInfoCollection = versionInfoCollection.getVersionInformationCollection(inSystemInstnaceIdentifier);
            if (verInfoCollection != null) {
                return verInfoCollection;
            }
        }
        return null;
    }

    private final HashMap<Integer, DCMVersionInformationCollection> mVersionInformationCollectionMap;
}
