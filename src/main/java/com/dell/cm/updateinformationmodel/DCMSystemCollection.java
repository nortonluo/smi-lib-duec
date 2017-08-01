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
 * class for representing the system collection
 *
 * @author Raveendra_Madala
 */
public class DCMSystemCollection {

    /**
     * Constructor
     */
    public DCMSystemCollection() {
        mSystemMap = new HashMap<>();
    }

    /**
     * Method for getting the system
     *
     * @param inIdentifier specifies the id of system being sought
     * @return the system
     */
    public DCMSystem getSystem(String inIdentifier) {
        DCMSystem retVal = null;
        if (mSystemMap.containsKey(inIdentifier)) {
            retVal = mSystemMap.get(inIdentifier);
        }
        return retVal;
    }

    /**
     * Method for adding system
     *
     * @param inSystem specifies the system being added
     * @return SUCCESS if the system could be added else appropriate error code
     * is returned
     */
    public int addSystem(DCMSystem inSystem) {
        if (null == inSystem) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mSystemMap.containsKey(inSystem.getUniqueIdentifier())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mSystemMap.put(inSystem.getUniqueIdentifier(), inSystem);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the system with the given identifier from the
     * collection
     *
     * @param inIdentifier specifies the identifier of the system being removed
     * @return SUCCESS if the system could be removed else appropriate error
     * code is returned
     */
    public int removeSystem(String inIdentifier) {
        if (null == inIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mSystemMap.containsKey(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMSystem removedSystem;
        removedSystem = mSystemMap.remove(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the systems in this collection
     *
     * @return the systems
     */
    public Collection<DCMSystem> getSystems() {
        return mSystemMap.values();
    }

    /**
     * Method for determining whether the specified system collection is a
     * subset of this collection or not
     *
     * @param inCollection specifies the collection to be compared
     * @return true if the specified collection is a subset else false is
     * returned
     */
    public boolean contains(DCMSystemCollection inCollection) {
        if (inCollection == null) {
            return true;
        }
        for (String id : inCollection.mSystemMap.keySet()) {
            if (!mSystemMap.containsKey(id)) {
                return false;
            }
        }
        return true;
    }

    private final HashMap<String, DCMSystem> mSystemMap;

}
