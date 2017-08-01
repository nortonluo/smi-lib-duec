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
 * class for representing the version information
 *
 * @author Raveendra_Madala
 */
public class DCMVersionInformation {

    public DCMVersionInformation() {
        mUpdateableComponentIdentifier = "";
        mTargetIdentifier = "";
        mTargetInstance = "";
    }

    public DCMVersionInformation(DCMVersionInformation object) {
        mUpdateableComponentIdentifier = object.mUpdateableComponentIdentifier;
        mTargetIdentifier = object.mTargetIdentifier;
        mTargetInstance = object.mTargetInstance;
        mVersion = object.mVersion;
        mContext = object.mContext;
    }

    /**
     * Method for getting the updateable component whose version is represented
     * by this object
     *
     * @return the updateable component identifier
     */
    public String getUpdateableComponentIdentifier() {
        return mUpdateableComponentIdentifier;
    }

    /**
     * Method for setting the updateable component identifier
     *
     * @param inIdentifier specifies the identifier being set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned
     */
    public int setUpdateableComponentIdentifier(String inIdentifier) {
        if (inIdentifier != null) {
            mUpdateableComponentIdentifier = inIdentifier.toUpperCase();
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the target corresponding to this object
     *
     * @return the target identifier
     */
    public String getTargetIdentifier() {
        return mTargetIdentifier;
    }

    /**
     * Method for setting the target identifier
     *
     * @param inIdentifier specifies the identifier being set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned
     */
    public int setTargetIdentifier(String inIdentifier) {
        if (inIdentifier != null) {
            mTargetIdentifier = inIdentifier.toUpperCase();
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the target instance of the updateable component whose
     * version is represented by this object
     *
     * @return the target instance
     */
    public String getTargetInstance() {
        return mTargetInstance;
    }

    /**
     * Method for setting the target instance
     *
     * @param inInstance specifies the instance being set
     * @return SUCCESS if the instance could be set else appropriate error code
     * is returned
     */
    public int setTargetInstance(String inInstance) {
        mTargetInstance = inInstance;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the version is represented by this object
     *
     * @return the version
     */
    public String getVersion() {
        return mVersion;
    }

    /**
     * Method for setting the version
     *
     * @param inVersion specifies the version being set
     * @return SUCCESS if the version could be set else appropriate error code
     * is returned
     */
    public int setVersion(String inVersion) {
        mVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the version is represented by this object
     *
     * @return the Context
     */
    public String getContext() {
        return mContext;
    }

    /**
     * Method for setting the version
     *
     * @param inContext specifies the version being set
     * @return SUCCESS if the version could be set else appropriate error code
     * is returned
     */
    public int setContext(String inContext) {
        mContext = inContext;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for determining whether the given version is also for the same
     * updateable component instance or not
     *
     * @param inVersion specifies the version being compared
     * @return true if the given version is also for the same updateable
     * component else false is returned.
     */
    public boolean isForSameUpdateableComponent(DCMVersionInformation inVersion) {
        if (!mUpdateableComponentIdentifier.equals(inVersion.mUpdateableComponentIdentifier)) {
            return false;
        }
        if (!mTargetIdentifier.equals(inVersion.mTargetIdentifier)) {
            return false;
        }
        if (!mTargetInstance.equals(inVersion.mTargetInstance)) {
            return false;
        }
        return true;
    }

    /**
     * Method for getting the unique identifier
     *
     * @return the unique identifier
     */
    public String getUniqueIdentifier() {
        String retVal = new String();
        retVal += mUpdateableComponentIdentifier;
        retVal += mTargetInstance;
        return retVal;
    }

    private String mUpdateableComponentIdentifier;
    private String mTargetIdentifier;
    private String mTargetInstance;
    private String mVersion;
    private String mContext;
}
