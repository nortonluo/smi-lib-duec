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
 * class for representing the update package collection
 *
 * @author Raveendra_Madala
 */
public class DCMUpdatePackageInformationCollection {

    /**
     * Constructor
     */
    public DCMUpdatePackageInformationCollection() {
        mUpdatePackageInformationMap = new HashMap<>();
        mUpdatePackageFileNameToIdentifierMap = new HashMap<>();
    }

    /**
     * Method for getting the update package information with the given
     * identifier
     *
     * @param inIdentifier specifies the identifier of the update package
     * information being sought
     * @return the component kind with the given type
     */
    public DCMUpdatePackageInformation getUpdatePackageInformation(String inIdentifier) {
        DCMUpdatePackageInformation retVal = null;
        String key=inIdentifier;
        if(mUpdatePackageFileNameToIdentifierMap.containsKey(key)){
            key=mUpdatePackageFileNameToIdentifierMap.get(key);
        }
        if (mUpdatePackageInformationMap.containsKey(key)) {
            retVal = mUpdatePackageInformationMap.get(key);
        }
        return retVal;
    }

    /**
     * Method for adding an update package information to this collection
     *
     * @param inInformation specifies the update package information being
     * specified
     * @return SUCCESS if the update package information could be added else
     * appropriate error code is returned
     */
    public int addUpdatePackageInformation(DCMUpdatePackageInformation inInformation) {
        if (null == inInformation) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mUpdatePackageInformationMap.containsKey(inInformation.getUniqueIdentifier())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mUpdatePackageInformationMap.put(inInformation.getUniqueIdentifier(), inInformation);

        String packageName = new java.io.File(inInformation.getPath()).getName();
        mUpdatePackageFileNameToIdentifierMap.put(packageName, inInformation.getUniqueIdentifier());
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing a update package information from this collection
     *
     * @param inIdentifier specifies the type of the update package information
     * being removed
     * @return SUCCESS if the update package information could be removed else
     * appropriate error code is returned
     */
    public int removeUpdatePackageInformation(String inIdentifier) {
        if (!mUpdatePackageInformationMap.containsKey(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMUpdatePackageInformation removedUpdatePackage;
        removedUpdatePackage = mUpdatePackageInformationMap.remove(inIdentifier);
        String[] pathList = removedUpdatePackage.getPath().split("/");
        String packageName = pathList[pathList.length - 1];
        mUpdatePackageFileNameToIdentifierMap.remove(packageName);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the update package identifier for the given package
     * file name
     *
     * @param inUpdatePackageFileName specifies the file name of the update
     * package
     * @return update package identifier for the given update package file name
     */
    public String getUpdatePackageIdentifier(String inUpdatePackageFileName) {
        if (inUpdatePackageFileName == null || inUpdatePackageFileName.isEmpty()) {
            return null;
        }
        return mUpdatePackageFileNameToIdentifierMap.get(inUpdatePackageFileName);
    }

    /**
     * Method for getting all the update packages
     *
     * @return the update packages
     */
    public Collection<DCMUpdatePackageInformation> getUpdatePackages() {
        return mUpdatePackageInformationMap.values();
    }
    private final HashMap<String, DCMUpdatePackageInformation> mUpdatePackageInformationMap;
    private final HashMap<String, String> mUpdatePackageFileNameToIdentifierMap;
}
