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

/**
 * class for representing the applicability
 *
 * @author Raveendra_Madala
 */
public class DCMDeviceApplicability {

    /**
     * Constructor
     */
    public DCMDeviceApplicability() {
    }

    /**
     * Copy constructor
     *
     * @param inApplicability specifies the object from which this object is to
     * be constructed
     */
    public DCMDeviceApplicability(DCMDeviceApplicability inApplicability) {
        if (inApplicability != null) {
            mMinVersion = inApplicability.mMinVersion;
            mMaxVersion = inApplicability.mMaxVersion;
        }
    }

    /**
     * Method for getting the minimum version
     *
     * @return the minimum version
     */
    public String getMinimumVersion() {
        return mMinVersion;
    }

    /**
     * Method for setting the minimum version
     *
     * @param inVersion specifies the version to be set
     * @return SUCCESS if the minimum version could be set else appropriate
     * error code is returned.
     */
    public int setMinimumVersion(String inVersion) {
        mMinVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the maximum version
     *
     * @return the maximum version
     */
    public String getMaximumVersion() {
        return mMaxVersion;
    }

    /**
     * Method for setting the maximum version
     *
     * @param inVersion specifies the version to be set
     * @return SUCCESS if the maximum version could be set else appropriate
     * error code is returned.
     */
    public int setMaximumVersion(String inVersion) {
        mMaxVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    private String mMinVersion;
    private String mMaxVersion;
}
