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
 * class for representing the version information collection
 *
 * @author Raveendra_Madala
 */
public class DCMVersionInformationCollection {

    /**
     * Constructor
     */
    public DCMVersionInformationCollection() {
        mVersionInformationMap = new HashMap<>();
        mSystemInstanceSet = new HashSet<>();
        mVersionInformationCollectionSet = new HashSet<>();
    }

    /**
     * Method for getting the version information
     *
     * @param inIdentifier specifies the id whose version is being sought
     * @return the updateable component
     */
    public DCMVersionInformation getVersionInformation(String inIdentifier) {
        DCMVersionInformation retVal = null;
        if (mVersionInformationMap.containsKey(inIdentifier)) {
            retVal = mVersionInformationMap.get(inIdentifier);
        }
        return retVal;
    }

    /**
     * Method for adding version information
     *
     * @param inVersionInformation specifies the version information being added
     * @return SUCCESS if the version information could be added else
     * appropriate error code is returned
     */
    public int addVersionInformation(DCMVersionInformation inVersionInformation) {
        if (null == inVersionInformation) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mVersionInformationMap.containsKey(inVersionInformation.getUniqueIdentifier())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mVersionInformationMap.put(inVersionInformation.getUniqueIdentifier(), inVersionInformation);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the version information with the given identifier
     * from the collection
     *
     * @param inIdentifier specifies the identifier of the version information
     * being removed
     * @return SUCCESS if the version information could be removed else
     * appropriate error code is returned
     */
    public int removeVersionInformation(String inIdentifier) {
        if (null == inIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mVersionInformationMap.containsKey(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMVersionInformation removedVersionInformation;
        removedVersionInformation = mVersionInformationMap.remove(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the version informations in this collection
     *
     * @return the version informations
     */
    public Collection<DCMVersionInformation> getVersionInformationObjects() {
        return mVersionInformationMap.values();
    }

    /**
     * Method for adding the system instance identifier
     *
     * @param inInstanceIdentifier specifies the instance identifier being added
     * @return SUCCESS if the instance could be added else appropriate error
     * code is returned.
     */
    public int addSystemInstance(String inInstanceIdentifier) {
        if (null == inInstanceIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mSystemInstanceSet.contains(inInstanceIdentifier)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mSystemInstanceSet.add(inInstanceIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the system instance identifier
     *
     * @param inInstanceIdentifier specifies the instance identifier being
     * removed
     * @return SUCCESS if the instance could be removed else appropriate error
     * code is returned.
     */
    public int removeSystemInstance(String inInstanceIdentifier) {
        if (null == inInstanceIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mSystemInstanceSet.contains(inInstanceIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mSystemInstanceSet.remove(inInstanceIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the system instance set
     *
     * @return the system instance set
     */
    public Collection<String> getSystemInstanceIdentifiers() {
        return mSystemInstanceSet;
    }

    /**
     * Method for determining whether the version information collection
     * supports the specified system instance or not
     *
     * @param inSystemInstanceIdentifier specifies the system instance being
     * compared
     * @return true if the system instance is supported else false is returned
     */
    public boolean supportsSystemInstance(String inSystemInstanceIdentifier) {
        if (inSystemInstanceIdentifier == null || inSystemInstanceIdentifier.isEmpty()) {
            return false;
        }
        if (mSystemInstanceSet.contains(inSystemInstanceIdentifier)) {
            return true;
        }
        return false;
    }

    /**
     * Method for getting the version information collection for the given
     * system instance. returns null if the system instance is not part of this
     * group
     *
     * @param inSystemInstanceIdentifier specifies the system instance being
     * compared
     * @return version information collection. could be null
     */
    DCMVersionInformationCollection getVersionInformationCollection(String inSystemInstanceIdentifier) {
        if (supportsSystemInstance(inSystemInstanceIdentifier)) {
            return this;
        }
        for (DCMVersionInformationCollection versionInfoCollection : mVersionInformationCollectionSet) {
            DCMVersionInformationCollection verInfoCollection = versionInfoCollection.getVersionInformationCollection(inSystemInstanceIdentifier);
            if (verInfoCollection != null) {
                return verInfoCollection;
            }
        }
        return null;
    }

    /**
     * Method for adding the version information collection
     *
     * @param inInformationCollectionIdentifier specifies the instance being
     * added
     * @return SUCCESS if the instance could be added else appropriate error
     * code is returned.
     */
    public int addVersionInformationCollection(DCMVersionInformationCollection inInformationCollectionIdentifier) {
        if (null == inInformationCollectionIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mVersionInformationCollectionSet.contains(inInformationCollectionIdentifier)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mVersionInformationCollectionSet.add(inInformationCollectionIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the version information collection
     *
     * @param inInformationCollectionIdentifier specifies the instance being
     * removed
     * @return SUCCESS if the instance could be removed else appropriate error
     * code is returned.
     */
    public int removeVersionInformationCollection(DCMVersionInformationCollection inInformationCollectionIdentifier) {
        if (null == inInformationCollectionIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mVersionInformationCollectionSet.contains(inInformationCollectionIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mVersionInformationCollectionSet.remove(inInformationCollectionIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the version information collection set
     *
     * @return the version information collection set
     */
    public Collection<DCMVersionInformationCollection> getVersionInformationCollections() {
        return mVersionInformationCollectionSet;
    }

    /**
     * Method for getting the unique identifier
     *
     * @return the unique identifier
     */
    public int getUniqueIdentifier() {
        return mIdentifier;
    }

    /**
     * Method for setting the unique identifier
     *
     * @param inIdentifier specifies the identifier being set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned
     */
    public int setUniqueIdentifier(int inIdentifier) {
        mIdentifier = inIdentifier;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the group identifier for which the version information
     * is being represented
     *
     * @return the group identifier
     */
    public String getGroupIdentifier() {
        return mGroupIdentifier;
    }

    /**
     * Method for setting the group identifier for which the version information
     * is being represented
     *
     * @param inIdentifier specifies the group identifier to be set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned.
     */
    public int setGroupIdentifier(String inIdentifier) {
        if (inIdentifier == null || inIdentifier.isEmpty()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        mGroupIdentifier = inIdentifier;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting all system instances in this group
     *
     * @return the system instance identifiers in this group
     */
    public Collection<String> getAllSystemInstanceIdentifiers() {
        HashSet<String> retVal = new HashSet<String>();
        retVal.addAll(mSystemInstanceSet);
        for (DCMVersionInformationCollection versionInfoCollection : mVersionInformationCollectionSet) {
            retVal.addAll(versionInfoCollection.getAllSystemInstanceIdentifiers());
        }
        return retVal;
    }

    public boolean areSameCollections(DCMVersionInformationCollection versionInfoCollection) {
        for (DCMVersionInformation versionInfo : versionInfoCollection.getVersionInformationObjects()) {
            if (mVersionInformationMap.containsKey(versionInfo.getUniqueIdentifier())) {
                if (versionInfo.getVersion() != null && !versionInfo.getVersion().equals(mVersionInformationMap.get(versionInfo.getUniqueIdentifier()).getVersion())) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public int getMaxInstanceValue(DCMVersionInformation inVersionInfo) {
        int maxInstanceValue = -1;
        int instanceValue;
        if (null != inVersionInfo && null != inVersionInfo.getUpdateableComponentIdentifier()) {
            for (DCMVersionInformation versionInfo : this.getVersionInformationObjects()) {
                if (null != versionInfo.getUpdateableComponentIdentifier() && versionInfo.getUpdateableComponentIdentifier().equals(inVersionInfo.getUpdateableComponentIdentifier())) {
                    instanceValue = Integer.valueOf(versionInfo.getTargetInstance());
                    if (instanceValue > maxInstanceValue) {
                        maxInstanceValue = instanceValue;
                    }
                }
            }
        }
        return maxInstanceValue;
    }

    private final HashMap<String, DCMVersionInformation> mVersionInformationMap;
    private final HashSet<String> mSystemInstanceSet;
    private final HashSet<DCMVersionInformationCollection> mVersionInformationCollectionSet;
    private String mGroupIdentifier;
    private int mIdentifier;

}
