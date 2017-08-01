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
import java.util.Iterator;

/**
 * Class for representing the updateable component collection
 *
 * @author Raveendra_Madala
 */
public class DCMUpdateableComponentCollection {

    /**
     * Constructor
     */
    public DCMUpdateableComponentCollection() {
        mComponentMap = new HashMap<>();
    }

    /**
     * Method for getting the updateable component
     *
     * @param inIdentifier specifies the id of component being sought
     * @return the updateable component
     */
    public DCMUpdateableComponent getUpdateableComponent(String inIdentifier) {
        DCMUpdateableComponent retVal = null;
        if (mComponentMap.containsKey(inIdentifier)) {
            retVal = mComponentMap.get(inIdentifier);
        }
        return retVal;
    }

    /**
     * Method for adding updateable component
     *
     * @param inComponent specifies the updateable component being added
     * @return SUCCESS if the component could be added else appropriate error
     * code is returned
     */
    public int addUpdateableComponent(DCMUpdateableComponent inComponent) {
        if (null == inComponent) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mComponentMap.containsKey(inComponent.getUniqueIdentifier())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mComponentMap.put(inComponent.getUniqueIdentifier(), inComponent);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the updateable component with the given identifier
     * from the collection
     *
     * @param inIdentifier specifies the identifier of the component being
     * removed
     * @return SUCCESS if the component could be removed else appropriate error
     * code is returned
     */
    public int removeUpdateableComponent(String inIdentifier) {
        if (null == inIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mComponentMap.containsKey(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMUpdateableComponent removedComponent;
        removedComponent = mComponentMap.remove(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the updateable components in this collection
     *
     * @return the updateable components
     */
    public Collection<DCMUpdateableComponent> getUpdateableComponents() {
        return mComponentMap.values();
    }

    /**
     * Method for getting the updateable components corresponding to the given
     * target
     *
     * @param inTargetIdentifier specifies the target identifier of the input
     * target
     * @return the updateable components related to the specified target
     */
    public Collection<DCMUpdateableComponent> getUpdateableComponents(String inTargetIdentifier) {
        Collection<DCMUpdateableComponent> retVal = new HashSet<>();
        for (Iterator<DCMUpdateableComponent> componentIterator = mComponentMap.values().iterator(); componentIterator.hasNext();) {
            DCMUpdateableComponent component = componentIterator.next();
            if (component.getTargetIdentifier().equals(inTargetIdentifier)) {
                retVal.add(component);
            }
        }
        return retVal;
    }

    private final HashMap<String, DCMUpdateableComponent> mComponentMap;
}
