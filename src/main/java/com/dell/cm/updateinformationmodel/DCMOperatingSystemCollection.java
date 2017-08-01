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
 * class for representing the operating system collection
 *
 * @author Raveendra_Madala
 */
public class DCMOperatingSystemCollection {

    /**
     * Constructor
     */
    public DCMOperatingSystemCollection() {
        mOSMap = new HashMap<>();
    }

    /**
     * Method for getting the operating system
     *
     * @param inIdentifier specifies the id of operating system being sought
     * @return the operating system
     */
    public DCMOperatingSystem getSystem(String inIdentifier) {
        DCMOperatingSystem retVal = null;
        if (mOSMap.containsKey(inIdentifier)) {
            retVal = mOSMap.get(inIdentifier);
        }
        return retVal;
    }

    /**
     * Method for adding operating system
     *
     * @param inOperatingSystem specifies the operating system being added
     * @return SUCCESS if the operating system could be added else appropriate
     * error code is returned
     */
    public int addOperatingSystem(DCMOperatingSystem inOperatingSystem) {
        if (null == inOperatingSystem) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mOSMap.containsKey(inOperatingSystem.getUniqueIdentifier())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mOSMap.put(inOperatingSystem.getUniqueIdentifier(), inOperatingSystem);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the operating system with the given identifier from
     * the collection
     *
     * @param inIdentifier specifies the identifier of the operating system
     * being removed
     * @return SUCCESS if the operating system could be removed else appropriate
     * error code is returned
     */
    public int removeSystem(String inIdentifier) {
        if (null == inIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mOSMap.containsKey(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMOperatingSystem removedOperatingSystem;
        removedOperatingSystem = mOSMap.remove(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the operating systems in this collection
     *
     * @return the systems
     */
    public Collection<DCMOperatingSystem> getOperatingSystems() {
        return mOSMap.values();
    }

    /**
     * Method for determining whether the specified operating system collection
     * is a subset of this collection or not
     *
     * @param inCollection specifies the operating system collection being
     * compared
     * @return true if the specified collection is a subset else false is
     * returned
     */
    public boolean contains(DCMOperatingSystemCollection inCollection) {
        if (inCollection == null) {
            return true;
        }
        for (String id : inCollection.mOSMap.keySet()) {
            if (!mOSMap.containsKey(id)) {
                return false;
            }
        }
        return true;
    }

    private final HashMap<String, DCMOperatingSystem> mOSMap;

}
