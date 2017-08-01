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
 * class for system instance collection
 *
 * @author Raveendra_Madala
 */
public class DCMSystemInstanceCollection {

    /**
     * Constructor
     */
    public DCMSystemInstanceCollection() {
        mSystemInstanceMap = new HashMap<>();
    }

    /**
     * Method for getting the system instance with the given service tag
     *
     * @param inServiceTag specifies the service tag of the system being sought
     * @return the system instance with the given service tag
     */
    public DCMSystemInstance getSystemInstance(String inServiceTag) {
        DCMSystemInstance retVal = null;
        if (mSystemInstanceMap.containsKey(inServiceTag)) {
            retVal = mSystemInstanceMap.get(inServiceTag);
        }
        return retVal;
    }

    /**
     * Method for adding a system instance to this collection
     *
     * @param inInstance specifies the instance of the system being specified
     * @return SUCCESS if the system instance could be added else appropriate
     * error code is returned
     */
    public int addSystemInstance(DCMSystemInstance inInstance) {
        if (null == inInstance || null == inInstance.getServiceTag()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mSystemInstanceMap.containsKey(inInstance.getServiceTag())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mSystemInstanceMap.put(inInstance.getServiceTag(), inInstance);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing a system instance from this collection
     *
     * @param inServiceTag specifies the service tag of the system instance
     * being removed
     * @return SUCCESS if the system instance could be removed else appropriate
     * error code is returned
     */
    public int removeSystemInstance(String inServiceTag) {
        if (!mSystemInstanceMap.containsKey(inServiceTag)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMSystemInstance removedSystemInstance;
        removedSystemInstance = mSystemInstanceMap.remove(inServiceTag);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting all the system instances
     *
     * @return the system instances
     */
    public Collection<DCMSystemInstance> getSystemInstances() {
        return mSystemInstanceMap.values();
    }

    /**
     * Method for determining whether the given system instance is present or
     * not
     *
     * @param inIdentifier specifies the system instance identifier
     * @return true if the system instance is present else false is returned.
     */
    public boolean hasSystemInstance(String inIdentifier) {
        if (inIdentifier == null || inIdentifier.isEmpty()) {
            return false;
        }
        if (mSystemInstanceMap.containsKey(inIdentifier)) {
            return true;
        }
        return false;
    }

    private final HashMap<String, DCMSystemInstance> mSystemInstanceMap;
}
