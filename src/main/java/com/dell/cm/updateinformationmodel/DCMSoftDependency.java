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

import java.util.HashSet;
import java.util.Set;

/**
 * class for representing soft dependency
 *
 * @author Rathish_Das
 */
public class DCMSoftDependency {

    /**
     * Constructor
     */
    public DCMSoftDependency() {
        mTargetIdentifiers = new HashSet<>();
    }

    /**
     * Copy Constructor
     *
     * @param inDependency specifies the object from which this object is to be
     * constructed
     */
    public DCMSoftDependency(DCMSoftDependency inDependency) {
        mTargetIdentifiers = new HashSet<>();
        if (inDependency != null) {
            mTargetIdentifiers.addAll(inDependency.mTargetIdentifiers);
            if (inDependency.mName != null) {
                mName = new DCMI18NString(inDependency.mName);
            }
            if (inDependency.mDetail != null) {
                mDetail = new DCMI18NString(inDependency.mDetail);
            }
            mComponentType = inDependency.mComponentType;
            mComponentID = inDependency.mComponentID;
            mVersion = inDependency.mVersion;
            if (inDependency.mPrerequisite != null) {
                mPrerequisite = new DCMGUID(inDependency.mPrerequisite);
            }
            mPath = inDependency.mPath;
            mReleaseID = inDependency.mReleaseID;
            mContext = inDependency.mContext;
        }
    }

    /**
     * Method for getting the name
     *
     * @return the name
     */
    public DCMI18NString getName() {
        return mName;
    }

    /**
     * Method for setting the name
     *
     * @param inName specifies the name to be set
     * @return SUCCESS if the name could be set else appropriate error code is
     * returned
     */
    public int setName(DCMI18NString inName) {
        mName = new DCMI18NString(inName);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the detail
     *
     * @return the detail
     */
    public DCMI18NString getDetail() {
        return mDetail;
    }

    /**
     * Method for setting the detail
     *
     * @param inDetail specifies the detail to be set
     * @return SUCCESS if the detail could be set else appropriate error code is
     * returned
     */
    public int setDetail(DCMI18NString inDetail) {
        mDetail = new DCMI18NString(inDetail);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the component type
     *
     * @return the component type
     */
    public DCMComponentType getComponentType() {
        return mComponentType;
    }

    /**
     * Method for setting the component type
     *
     * @param inType specifies the type to be set
     * @return SUCCESS if the type could be set else appropriate error code is
     * returned
     */
    public int setComponentType(DCMComponentType inType) {
        mComponentType = inType;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the component id
     *
     * @return the component id
     */
    public long getComponentID() {
        return mComponentID;
    }

    /**
     * Method for setting the component id
     *
     * @param inID specifies the id to be set
     * @return SUCCESS if the id could be set else appropriate error code is
     * returned
     */
    public int setComponentID(long inID) {
        mComponentID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the version
     *
     * @return the version
     */
    public String getVersion() {
        return mVersion;
    }

    /**
     * Method for setting the version
     *
     * @param inVersion specifies the version to be set
     * @return SUCCESS if the version could be set else appropriate error code
     * is returned
     */
    public int setVersion(String inVersion) {
        mVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the prerequisite identifier
     *
     * @return returns the prerequisite identifier
     */
    public DCMGUID getPrerequisite() {
        return mPrerequisite;
    }

    /**
     * Method for setting the prerequisite identifier
     *
     * @param inID specifies the identifier to be set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned
     */
    public int setPrerequisite(DCMGUID inID) {
        mPrerequisite = new DCMGUID(inID);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the path
     *
     * @return the path
     */
    public String getPath() {
        return mPath;
    }

    /**
     * Method for setting the path
     *
     * @param inPath specifies the path to be set
     * @return SUCCESS if the path could be set else appropriate error code is
     * returned.
     */
    public int setPath(String inPath) {
        mPath = inPath;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the releaseID
     *
     * @return the releaseID
     */
    public String getReleaseID() {
        return mReleaseID;
    }

    /**
     * Method for setting the release ID
     *
     * @param inID specifies the release ID to be set
     * @return SUCCESS if the release ID could be set else appropriate error
     * code is returned.
     */
    public int setReleaseID(String inID) {
        mReleaseID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the context
     *
     * @return the context
     */
    public DCMDependencyContext getContext() {
        return mContext;
    }

    /**
     * Method for setting the context
     *
     * @param inContext specifies the context to be set
     * @return SUCCESS if the context could be set else appropriate error code
     * is returned
     */
    public int setContext(DCMDependencyContext inContext) {
        mContext = inContext;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for adding supported target
     *
     * @param inTargetIdentifier specifies the identifier of the target to be
     * added
     * @return SUCCESS if the target could be added else appropriate error code
     * is returned.
     */
    public int addSupportedTarget(String inTargetIdentifier) {
        if (null == inTargetIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mTargetIdentifiers.contains(inTargetIdentifier)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mTargetIdentifiers.add(inTargetIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing support for the given target
     *
     * @param inTargetIdentifier specifies the identifier of the target to be
     * removed
     * @return SUCCESS if the target could be removed else appropriate error
     * code is returned.
     */
    public int removeSupportedTarget(String inTargetIdentifier) {
        if (null == inTargetIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mTargetIdentifiers.contains(inTargetIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mTargetIdentifiers.remove(inTargetIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the supported target set
     *
     * @return the supported target set
     */
    public Set<String> getSupportedTargets() {
        return mTargetIdentifiers;
    }

    private DCMI18NString mName;
    private DCMI18NString mDetail;
    private final Set<String> mTargetIdentifiers;
    private DCMComponentType mComponentType;
    private long mComponentID;
    private String mVersion;
    private DCMGUID mPrerequisite;
    private String mPath;
    private String mReleaseID;
    private DCMDependencyContext mContext;
}
