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
 * class for representing collection of bundles
 *
 * @author Raveendra_Madala
 */
public class DCMBundleCollection {

    /**
     * Constructor
     */
    public DCMBundleCollection() {
        mBundleMap = new HashMap<>();
    }

    /**
     * Method for adding a bundle to this collection
     *
     * @param inBundle specifies the name of the component type being specified
     * @return SUCCESS if the component type could be added else appropriate
     * error code is returned
     */
    public int addBundle(DCMBundle inBundle) {
        if (inBundle == null || null == inBundle.getUniqueIdentifier()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mBundleMap.containsKey(inBundle.getUniqueIdentifier())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mBundleMap.put(inBundle.getUniqueIdentifier(), inBundle);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing a bundle from this collection
     *
     * @param inIdentifier specifies the identifier of the bundle being removed
     * @return SUCCESS if the bundle could be removed else appropriate error
     * code is returned
     */
    public int removeBundle(String inIdentifier) {
        if (inIdentifier == null || inIdentifier.isEmpty()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mBundleMap.containsKey(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMBundle removedBundle;
        removedBundle = mBundleMap.remove(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting all the bundles
     *
     * @return the bundles
     */
    public Collection<DCMBundle> getBundles() {
        return mBundleMap.values();
    }

    /**
     * Method for getting the bundle identifiers for the given system identifier
     * and bundle type
     *
     * @param inSystemIdentifier specifies the system identifier for which the
     * bundle identifier is being sought
     * @param inBundleType specifies the bundle type
     * @return bundle identifiers
     */
    public Collection<String> getBundleIdentifiers(String inSystemIdentifier, DCMBundleType inBundleType) {
        HashSet<String> retVal = new HashSet<>();
        if (inSystemIdentifier == null || inSystemIdentifier.isEmpty() || inBundleType == null) {
            return retVal;
        }
        for (DCMBundle bundle : mBundleMap.values()) {
            if (bundle.supportsSystem(inSystemIdentifier)) {
                if (inBundleType == DCMBundleType.BTALL || bundle.getType().equals(inBundleType)) {
                    retVal.add(bundle.getUniqueIdentifier());
                } else if (inBundleType == DCMBundleType.BTWMIX) {
                    if (bundle.getType().equals(DCMBundleType.BTW32) || bundle.getType().equals(DCMBundleType.BTW64)) {
                        retVal.add(bundle.getUniqueIdentifier());
                    }
                }
            }

        }
        return retVal;
    }

    /**
     * Method for getting the bundle with the given identifier
     *
     * @param inIdentifier specifies the identifier being sought
     * @return bundle
     */
    public DCMBundle getBundle(String inIdentifier) {
        if (inIdentifier == null || inIdentifier.isEmpty()) {
            return null;
        }
        return mBundleMap.get(inIdentifier);
    }

    private final HashMap<String, DCMBundle> mBundleMap;
}
