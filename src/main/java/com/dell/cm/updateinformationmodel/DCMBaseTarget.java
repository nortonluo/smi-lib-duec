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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * class for representing the base target
 *
 * @author Raveendra_Madala
 */
public abstract class DCMBaseTarget {

    protected DCMBaseTarget() {
        mSubComponentSet = new HashSet<>();
        mApplicabilitySet = new HashSet<>();
    }

    protected DCMBaseTarget(DCMBaseTarget inTarget) {
        mSubComponentSet = new HashSet<>();
        mApplicabilitySet = new HashSet<>();
        if (inTarget != null) {
            if (inTarget.mName != null) {
                mName = new DCMI18NString(inTarget.mName);
            }
            mType = inTarget.mType;
            mEmbedded = inTarget.mEmbedded;
            for (DCMSubComponent subComponent : inTarget.mSubComponentSet) {
                mSubComponentSet.add(new DCMSubComponent(subComponent));
            }
            for (DCMDeviceApplicability applicability : inTarget.mApplicabilitySet) {
                mApplicabilitySet.add(new DCMDeviceApplicability(applicability));
            }
            if (inTarget.mRollbackInformation != null) {
                mRollbackInformation = new DCMRollbackInformation(inTarget.mRollbackInformation);
            }
        }
    }

    public abstract DCMBaseTarget Copy();

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
     * @param inName specifies the name being set
     * @return the name of the target
     */
    public int setName(DCMI18NString inName) {
        mName = new DCMI18NString(inName);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the type of the target
     *
     * @return the target type
     */
    public DCMTargetEntity getType() {
        return mType;
    }

    /**
     * Method for knowing whether the target is embedded or not
     *
     * @return true if the target is embedded else false is returned
     */
    public boolean isEmbedded() {
        return mEmbedded;
    }

    /**
     * Method for setting the embedded value
     *
     * @param inValue specifies the value to be set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned
     */
    public int setEmbedded(boolean inValue) {
        mEmbedded = inValue;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the unique identifier for this target
     *
     * @return the unique identifier for this target
     */
    public abstract String getUniqueIdentifier();

    /**
     * Method for getting the rollback information
     *
     * @return the rollback information
     */
    public DCMRollbackInformation getRollbackInformation() {
        return mRollbackInformation;
    }

    /**
     * Method for setting the rollback information
     *
     * @param inRollbackInformation specifies the rollback information to be set
     * @return SUCCESS if the information could eb set else appropriate error
     * code is returned
     */
    public int setRollbackInformaton(DCMRollbackInformation inRollbackInformation) {
        mRollbackInformation = new DCMRollbackInformation(inRollbackInformation);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for adding sub component
     *
     * @param inComponent specifies the sub component to be added
     * @return SUCCESS if the sub component could be added else appropriate
     * error code is returned.
     */
    public int addSubComponent(DCMSubComponent inComponent) {
        if (null == inComponent) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mSubComponentSet.contains(inComponent)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mSubComponentSet.add(inComponent);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the given sub component
     *
     * @param inComponent specifies the sub component to be removed
     * @return SUCCESS if the sub component could be removed else appropriate
     * error code is returned.
     */
    public int removeSubComponent(DCMSubComponent inComponent) {
        if (null == inComponent) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mSubComponentSet.contains(inComponent)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mSubComponentSet.remove(inComponent);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the sub component set
     *
     * @return the sub component set
     */
    public Set<DCMSubComponent> getSubComponents() {
        return mSubComponentSet;
    }

    /**
     * Method for adding applicability element
     *
     * @param inElement specifies the applicability element to be added
     * @return SUCCESS if the applicability element could be added else
     * appropriate error code is returned.
     */
    public int addApplicabilityElement(DCMDeviceApplicability inElement) {
        if (null == inElement) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mApplicabilitySet.contains(inElement)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mApplicabilitySet.add(inElement);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the given applicability element
     *
     * @param inElement specifies the applicability element to be removed
     * @return SUCCESS if the applicability element could be removed else
     * appropriate error code is returned.
     */
    public int removeApplicabilityElement(DCMDeviceApplicability inElement) {
        if (null == inElement) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mApplicabilitySet.contains(inElement)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mApplicabilitySet.remove(inElement);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the applicability element set
     *
     * @return the applicability element set
     */
    public Set<DCMDeviceApplicability> getApplicabilityElements() {
        return mApplicabilitySet;
    }

    public Node toXML(Document document) {// int addDevice(Element inParentElement, long componentID, DCMManifest inManifest, HashSet<String> inDeviceSet, DCMUpdatePackageInformation inPackage) {

        Element device = document.createElement(DCMConstants.DEVICE);

        String embedded = isEmbedded() ? DCMConstants.ONE : DCMConstants.ZERO;

        device.setAttribute(DCMConstants.EMBEDDED, embedded);


        /*u
         To do -
         1) SubComponent
         2) Dependency
         3) SoftDependency
         4) Applicability
         5) RollbackInformation
         */
        if (mName != null) {
            device.appendChild(mName.toXML(document));
        }

        return device;
    }

    protected DCMI18NString mName;
    protected DCMTargetEntity mType;
    protected boolean mEmbedded;
    protected final Set<DCMSubComponent> mSubComponentSet;
    protected final Set<DCMDeviceApplicability> mApplicabilitySet;
    protected DCMRollbackInformation mRollbackInformation;
}
