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
 * class for representing component kind collection
 *
 * @author Raveendra_Madala
 */
public class DCMComponentKindCollection {

    /**
     * Constructor
     */
    public DCMComponentKindCollection() {
        mComponentTypeMap = new HashMap<>();
    }

    /**
     * Method for getting the component kind with the given type
     *
     * @param inType specifies the type of the component kind being sought
     * @return the component kind with the given type
     */
    public DCMComponentKind getComponentKind(DCMComponentType inType) {
        DCMComponentKind retVal = null;
        if (mComponentTypeMap.containsKey(inType)) {
            retVal = mComponentTypeMap.get(inType);
        }
        return retVal;
    }

    /**
     * Method for adding a component kind to this collection
     *
     * @param inType specifies the key of the component kind being specified
     * @param inName specifies the name of the component type being specified
     * @return SUCCESS if the component type could be added else appropriate
     * error code is returned
     */
    public int addComponentKind(DCMComponentType inType, DCMI18NString inName) {
        if (mComponentTypeMap.containsKey(inType)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        DCMComponentKind componentKind = new DCMComponentKind(inType);
        componentKind.setName(inName);
        mComponentTypeMap.put(inType, componentKind);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for adding a component kind to this collection
     *
     * @param inComponentKind specifies component kind to be inserted in the
     * Component Kind Collection
     * @return SUCCESS if the component type could be added else appropriate
     * error code is returned
     */
    public int addComponentKind(DCMComponentKind inComponentKind) {
        if (null == inComponentKind) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        DCMComponentType componentType = inComponentKind.getType();
        if (mComponentTypeMap.containsKey(componentType)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        DCMComponentKind componentKind = new DCMComponentKind(componentType);
        componentKind.setName(inComponentKind.getName());
        mComponentTypeMap.put(componentType, componentKind);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing a component kind from this collection
     *
     * @param inType specifies the type of the component kind being removed
     * @return SUCCESS if the component kind could be removed else appropriate
     * error code is returned
     */
    public int removeComponentKind(DCMComponentType inType) {
        if (!mComponentTypeMap.containsKey(inType)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMComponentKind removedComponentKind;
        removedComponentKind = mComponentTypeMap.remove(inType);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting all the component kind
     *
     * @return the component kinds
     */
    public Collection<DCMComponentKind> getComponentKinds() {
        return mComponentTypeMap.values();
    }
    private final HashMap<DCMComponentType, DCMComponentKind> mComponentTypeMap;
}
