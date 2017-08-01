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
 * Class for Line of Business Collection
 *
 * @author Raveendra_Madala
 */
public class DCMLineOfBusinessCollection {

    /**
     * Constructor
     */
    public DCMLineOfBusinessCollection() {
        mLOBMap = new HashMap<>();
    }

    /**
     * Method for getting the line of business with the given key
     *
     * @param inKey specifies the key of the line of business being sought
     * @return the line of business with the given key
     */
    public DCMLineOfBusiness getLineOfBusiness(int inKey) {
        DCMLineOfBusiness retVal = null;
        if (mLOBMap.containsKey(inKey)) {
            retVal = mLOBMap.get(inKey);
        }
        return retVal;
    }

    /**
     * Method for adding a line of business to the collection
     *
     * @param inLOB specifies the line of business to be added
     * @return SUCCESS if the addition could be done else appropriate error code
     * is returned
     */
    public int addLineOfBusiness(DCMLineOfBusiness inLOB) {
        if (inLOB == null) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mLOBMap.containsKey(inLOB.getKey())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mLOBMap.put(inLOB.getKey(), inLOB);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for adding a line of business to this collection
     *
     * @param inKey specifies the key of the line of business being specified
     * @param inPrefix specifies the prefix of the lob being specified
     * @param inName specifies the name of the lob being specified
     * @return SUCCESS if the lob could be added else appropriate error code is
     * returned
     */
    public int addLineOfBusiness(int inKey, String inPrefix, DCMI18NString inName) {
        if (mLOBMap.containsKey(inKey)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        DCMLineOfBusiness lob = new DCMLineOfBusiness(inKey, inPrefix, inName);
        mLOBMap.put(inKey, lob);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing a line of business from this collection
     *
     * @param inKey specifies the key of the line of business being removed
     * @return SUCCESS if the lob could be removed else appropriate error code
     * is returned
     */
    public int removeLineOfBusiness(int inKey) {
        if (!mLOBMap.containsKey(inKey)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMLineOfBusiness removedLOB;
        removedLOB = mLOBMap.remove(inKey);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting all the lines of business
     *
     * @return the lines of businesses
     */
    public Collection<DCMLineOfBusiness> getLinesOfBusiness() {
        return mLOBMap.values();
    }

    /**
     * Method for determining whether this line of business collection contains
     * all the lines of business specified in the specified collection
     *
     * @param inCollection specifies the collection to be compared with this
     * @return true if the supplied collection is contained else false is
     * returned
     */
    public boolean contains(DCMLineOfBusinessCollection inCollection) {
        if (inCollection == null) {
            return true;
        }
        for (Integer key : inCollection.mLOBMap.keySet()) {
            if (!mLOBMap.containsKey(key)) {
                return false;
            }
        }
        return true;
    }

    private final HashMap<Integer, DCMLineOfBusiness> mLOBMap;
}
