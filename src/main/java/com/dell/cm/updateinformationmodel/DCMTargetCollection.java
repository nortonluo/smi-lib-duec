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
import java.util.Locale;

/**
 * class for representing the target collection
 *
 * @author Raveendra_Madala
 */
public class DCMTargetCollection {

    /**
     * Constructor
     */
    public DCMTargetCollection() {
        mTargetMap = new HashMap<>();
    }

    /**
     * Method for getting the target
     *
     * @param inIdentifier specifies the id of target being sought
     * @return the target
     */
    public DCMBaseTarget getTarget(String inIdentifier) {
        DCMBaseTarget retVal = null;
        if (mTargetMap.containsKey(inIdentifier)) {
            retVal = mTargetMap.get(inIdentifier);
        }
        return retVal;
    }

    /**
     * Method for adding target
     *
     * @param inTarget specifies the target being added
     * @return SUCCESS if the component could be added else appropriate error
     * code is returned
     */
    public int addTarget(DCMBaseTarget inTarget) {
        if (null == inTarget) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mTargetMap.containsKey(inTarget.getUniqueIdentifier())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mTargetMap.put(inTarget.getUniqueIdentifier(), inTarget);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the target with the given identifier from the
     * collection
     *
     * @param inIdentifier specifies the identifier of the target being removed
     * @return SUCCESS if the target could be removed else appropriate error
     * code is returned
     */
    public int removeTarget(String inIdentifier) {
        if (null == inIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mTargetMap.containsKey(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMBaseTarget removedTarget;
        removedTarget = mTargetMap.remove(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the targets in this collection
     *
     * @return the targets
     */
    public Collection<DCMBaseTarget> getTargets() {
        return mTargetMap.values();
    }

    public DCMI18NString getName(String inID) {
        DCMI18NString targetName = new DCMI18NString();
        if (null == inID || inID.isEmpty()) {
            return targetName;
        }
        DCMBaseTarget target = this.getTarget(inID);
        if (null != target) {
            targetName = target.getName();
        }
        return targetName;
    }
    private final HashMap<String, DCMBaseTarget> mTargetMap;
}
