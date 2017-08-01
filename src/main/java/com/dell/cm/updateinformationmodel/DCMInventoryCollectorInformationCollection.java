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
 * class for representing inventory collector information collection
 *
 * @author Raveendra_Madala
 */
public class DCMInventoryCollectorInformationCollection {

    /**
     * Constructor
     */
    DCMInventoryCollectorInformationCollection() {
        mInventoryCollectorMap = new HashMap<>();
    }

    /**
     * Method for getting the inventory collector information
     *
     * @param inIdentifier specifies the type of inventory collector being
     * sought
     * @return the inventory collector information
     */
    public DCMInventoryCollectorInformation getInventoryCollectorInformation(String inIdentifier) {
        DCMInventoryCollectorInformation retVal = null;
        if (mInventoryCollectorMap.containsKey(inIdentifier)) {
            retVal = mInventoryCollectorMap.get(inIdentifier);
        }
        return retVal;
    }

    /**
     * Method for adding inventory collector information
     *
     * @param inComponent specifies the inventory collector component being
     * added
     * @return SUCCESS if the component could be added else appropriate error
     * code is returned
     */
    public int addInventoryCollectorInformation(DCMInventoryCollectorInformation inComponent) {
        if (null == inComponent) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mInventoryCollectorMap.containsKey(inComponent.getOSCode())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mInventoryCollectorMap.put(inComponent.getOSCode(), inComponent);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the inventory collector information component with
     * the given type from the collection
     *
     * @param inType specifies the type of the inventory collector information
     * being removed
     * @return SUCCESS if the component could be removed else appropriate error
     * code is returned
     */
    public int removeInventoryCollectorInformation(String inType) {
        if (null == inType) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mInventoryCollectorMap.containsKey(inType)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMInventoryCollectorInformation removedComponent;
        removedComponent = mInventoryCollectorMap.remove(inType);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the inventory collectors information in this
     * collection
     *
     * @return the inventory collectors information
     */
    public Collection<DCMInventoryCollectorInformation> getInventoryCollectorsInformation() {
        return mInventoryCollectorMap.values();
    }

    private final HashMap<String, DCMInventoryCollectorInformation> mInventoryCollectorMap;
}
