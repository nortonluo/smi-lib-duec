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

import com.dell.cm.comparer.DCMConsiderationEnum;
import com.dell.cm.comparer.DCMUpdateInformation;
import com.dell.cm.comparer.DCMUpdateType;
import com.dell.cm.updateinformationmodel.activationrule.DCMActionRule;
import com.dell.cm.updateinformationmodel.activationrule.DCMActivationRules;
import com.dell.cm.updateinformationmodel.activationrule.DCMMessage;
import com.dell.cm.updateinformationmodel.activationrule.DCMRuleData;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * class for representing the update package information
 *
 * @author Raveendra_Madala
 */
public class DCMUpdatePackageInformation {

    /**
     * Constructor
     *
     * @param inPackageID specifies the package id of the update package
     * @param inPackageType specifies the package type of the update package
     */
    public DCMUpdatePackageInformation(String inPackageID, DCMPackageType inPackageType) {
        mPackageID = inPackageID;
        mPackageType = inPackageType;
        mTargetIdentifierSet = new HashSet<>();
        mSystemIdentifierSet = new HashSet<>();
        mOperatingSystemIdentifierSet = new HashSet<>();
        mConstituents = new HashSet<>();
        mFMPWrappers = new HashSet<>();
        mDependencies = new HashMap<>();
        mSoftDependencies = new HashMap<>();
        mDependencyMap = new HashMap<>();
        mSoftDependencyMap = new HashMap<>();
        mTargetMap = new HashMap<>();
    }

    public DCMUpdatePackageInformation(DCMUpdatePackageInformation inUpdatePackageInformation) {
        mTargetIdentifierSet = new HashSet<>();
        mSystemIdentifierSet = new HashSet<>();
        mOperatingSystemIdentifierSet = new HashSet<>();
        mConstituents = new HashSet<>();
        mFMPWrappers = new HashSet<>();
        mDependencies = new HashMap<>();
        mSoftDependencies = new HashMap<>();
        mDependencyMap = new HashMap<>();
        mSoftDependencyMap = new HashMap<>();
        mTargetMap = new HashMap<>();

        if (inUpdatePackageInformation != null) {
            mPackageID = inUpdatePackageInformation.mPackageID;
            mPackageType = inUpdatePackageInformation.mPackageType;
            mCategoryCode = inUpdatePackageInformation.mCategoryCode;
            mLUCategoryCode = inUpdatePackageInformation.mLUCategoryCode;
            mSystemIdentifierSet.addAll(inUpdatePackageInformation.mSystemIdentifierSet);
            mOperatingSystemIdentifierSet.addAll(inUpdatePackageInformation.mOperatingSystemIdentifierSet);
            mTargetIdentifierSet.addAll(inUpdatePackageInformation.mTargetIdentifierSet);
            for (DCMConstituent constituent : inUpdatePackageInformation.mConstituents) {
                DCMConstituent copiedConstituent = new DCMConstituent(constituent);
                mConstituents.add(copiedConstituent);
            }
            for (DCMFMPWrapperInformation fmpWrapper : inUpdatePackageInformation.mFMPWrappers) {
                DCMFMPWrapperInformation copiedFmpWrapper = new DCMFMPWrapperInformation(fmpWrapper);
                mFMPWrappers.add(copiedFmpWrapper);
            }
            if (inUpdatePackageInformation.mInstallInstruction != null) {
                mInstallInstruction = new DCMInstallInstructions(inUpdatePackageInformation.mInstallInstruction);
            }
            if (inUpdatePackageInformation.mRevisionHistory != null) {
                mRevisionHistory = new DCMI18NString(inUpdatePackageInformation.mRevisionHistory);
            }
            if (inUpdatePackageInformation.mImportantInformation != null) {
                mImportantInformation = new DCMImportantInformation(inUpdatePackageInformation.mImportantInformation);
            }
            mCriticality = inUpdatePackageInformation.mCriticality;
            if (inUpdatePackageInformation.mCriticalityDescription != null) {
                mCriticalityDescription = new DCMI18NString(inUpdatePackageInformation.mCriticalityDescription);
            }
            if (inUpdatePackageInformation.mMSIID != null) {
                mMSIID = new DCMGUID(inUpdatePackageInformation.mMSIID);
            }
            if (inUpdatePackageInformation.mName != null) {
                mName = new DCMI18NString(inUpdatePackageInformation.mName);
            }
            mComponentType = inUpdatePackageInformation.mComponentType;
            if (inUpdatePackageInformation.mDescription != null) {
                mDescription = new DCMI18NString(inUpdatePackageInformation.mDescription);
            }
            mSchemaVersion = inUpdatePackageInformation.mSchemaVersion;
            mReleaseID = inUpdatePackageInformation.mReleaseID;
            if (inUpdatePackageInformation.mReleaesDate != null) {
                mReleaesDate = (Date) inUpdatePackageInformation.mReleaesDate.clone();
            }
            mVendorVersion = inUpdatePackageInformation.mVendorVersion;
            mDellVersion = inUpdatePackageInformation.mDellVersion;
            mXMLGenVersion = inUpdatePackageInformation.mXMLGenVersion;
            mPath = inUpdatePackageInformation.mPath;
            if (inUpdatePackageInformation.mDate != null) {
                mDate = (Date) inUpdatePackageInformation.mDate.clone();
            }
            mHashMD5 = inUpdatePackageInformation.mHashMD5;
            mSize = inUpdatePackageInformation.mSize;
            if (inUpdatePackageInformation.mIdentifier != null) {
                mIdentifier = new DCMGUID(inUpdatePackageInformation.mIdentifier);
            }
            if (inUpdatePackageInformation.mPredecessorID != null) {
                mPredecessorID = new DCMGUID(inUpdatePackageInformation.mPredecessorID);
            }
            mRebootRequired = inUpdatePackageInformation.mRebootRequired;
            mContainerPowerCycleRequired = inUpdatePackageInformation.mContainerPowerCycleRequired;
            for (Integer index : inUpdatePackageInformation.mDependencies.keySet()) {
                mDependencies.put(index, new DCMDependency(inUpdatePackageInformation.mDependencies.get(index)));
            }
            for (Integer index : inUpdatePackageInformation.mSoftDependencies.keySet()) {
                mSoftDependencies.put(index, new DCMSoftDependency(inUpdatePackageInformation.mSoftDependencies.get(index)));
            }
            for (String reference : inUpdatePackageInformation.mDependencyMap.keySet()) {
                HashSet<Integer> value = new HashSet<>();
                value.addAll(inUpdatePackageInformation.mDependencyMap.get(reference));
                mDependencyMap.put(reference, value);
            }
            for (String reference : inUpdatePackageInformation.mSoftDependencyMap.keySet()) {
                HashSet<Integer> value = new HashSet<>();
                value.addAll(inUpdatePackageInformation.mSoftDependencyMap.get(reference));
                mSoftDependencyMap.put(reference, value);
            }

            //initializing mTargetMap<String, DCMBaseTarget>
            for (String identifier : inUpdatePackageInformation.mTargetMap.keySet()) {
                DCMBaseTarget baseTarget = inUpdatePackageInformation.mTargetMap.get(identifier);
                mTargetMap.put(identifier, baseTarget);
            }

        } else {
            mPackageID = "";
            mPackageType = DCMPackageType.LWXP;
        }
    }

    /**
     * Method for getting the unique identifier
     *
     * @return the update package unique identifier
     */
    public String getUniqueIdentifier() {
        String retVal = new String();
        if (null != mIdentifier) {
            retVal += mIdentifier.getID();
        } else {
            retVal = mPackageID + mPackageType;
        }
        return retVal;
    }

    /**
     * Method for getting the category code
     *
     * @return the category code
     */
    public String getCategoryCode() {
        return mCategoryCode;
    }

    /**
     * Method for setting the category code
     *
     * @param inValue specifies the code to be set
     * @return SUCCESS if the category code could be set else appropriate error
     * code is returned
     */
    public int setCategoryCode(String inValue) {
        mCategoryCode = inValue;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the LUCategory code
     *
     * @return the category code
     */
    public String getLUCategoryCode() {
        return mLUCategoryCode;
    }

    /**
     * Method for setting the LUcategory code
     *
     * @param inValue specifies the code to be set
     * @return SUCCESS if the category code could be set else appropriate error
     * code is returned
     */
    public int setLUCategoryCode(String inValue) {
        mLUCategoryCode = inValue;
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
        if (mTargetIdentifierSet.contains(inTargetIdentifier)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mTargetIdentifierSet.add(inTargetIdentifier);
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
        if (!mTargetIdentifierSet.contains(inTargetIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mTargetIdentifierSet.remove(inTargetIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the supported target set
     *
     * @return the supported target set
     */
    public HashSet<String> getSupportedTargets() {
        return mTargetIdentifierSet;
    }

    /**
     * Method for adding supported OS
     *
     * @param inOSIdentifier specifies the identifier of the OS to be added
     * @return SUCCESS if the OS could be added else appropriate error code is
     * returned.
     */
    public int addSupportedOS(String inOSIdentifier) {
        if (null == inOSIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mOperatingSystemIdentifierSet.contains(inOSIdentifier)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mOperatingSystemIdentifierSet.add(inOSIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing support for the given operating system
     *
     * @param inOSIdentifier specifies the identifier of the operating system to
     * be removed
     * @return SUCCESS if the operating system could be removed else appropriate
     * error code is returned.
     */
    public int removeSupportedOS(String inOSIdentifier) {
        if (null == inOSIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mOperatingSystemIdentifierSet.contains(inOSIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mOperatingSystemIdentifierSet.remove(inOSIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the supported operating system set
     *
     * @return the supported operating system set
     */
    public HashSet<String> getSupportedOperatingSystems() {
        return mOperatingSystemIdentifierSet;
    }

    /**
     * Method for adding supported system
     *
     * @param inSystemIdentifier specifies the identifier of the system to be
     * added
     * @return SUCCESS if the system could be added else appropriate error code
     * is returned.
     */
    public int addSupportedSystem(String inSystemIdentifier) {
        if (null == inSystemIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mSystemIdentifierSet.contains(inSystemIdentifier)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mSystemIdentifierSet.add(inSystemIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing support for the given system
     *
     * @param inSystemIdentifier specifies the identifier of the system to be
     * removed
     * @return SUCCESS if the system could be removed else appropriate error
     * code is returned.
     */
    public int removeSupportedSystem(String inSystemIdentifier) {
        if (null == inSystemIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mSystemIdentifierSet.contains(inSystemIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mSystemIdentifierSet.remove(inSystemIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the supported operating system set
     *
     * @return the supported operating system set
     */
    public HashSet<String> getSupportedSystems() {
        return mSystemIdentifierSet;
    }

    /**
     * Method for adding constituent
     *
     * @param inConstituent to be added
     * @return SUCCESS if the constituent can be added else appropriate error
     * code is returned
     */
    public int addConstituent(DCMConstituent inConstituent) {
        mConstituents.add(inConstituent);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the constituents
     *
     * @return the constituents
     */
    public Collection<DCMConstituent> getConstituents() {
        return mConstituents;
    }

    /**
     * Method for getting the revision history
     *
     * @return the revision history
     */
    public DCMActivationRules getActivationRules() {
        return mActivationRules;
    }

    /**
     * Method for setting the revision history
     *
     * @param inRule specifies the revision history to be set
     * @return SUCCESS if the instruction could be set else appropriate error
     * code is returned
     */
    public int setActivationRules(DCMActivationRules inRule) {
        mActivationRules = inRule;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for adding wrapper
     *
     * @param inWrappper specifies the wrapper to be added
     * @return SUCCESS if the wrapper can be added else appropriate error code
     * is returned
     */
    public int addFMPWrapper(DCMFMPWrapperInformation inWrappper) {
        mFMPWrappers.add(inWrappper);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the FMP Wrappers
     *
     * @return the FMP Wrappers
     */
    public Collection<DCMFMPWrapperInformation> getFMPWrappers() {
        return mFMPWrappers;
    }

    /**
     * Method for getting the install instruction
     *
     * @return the install instruction
     */
    public DCMInstallInstructions getInstallInstruction() {
        return mInstallInstruction;
    }

    /**
     * Method for setting the install instruction
     *
     * @param inInstruction specifies the instruction to be set
     * @return SUCCESS if the instruction could be set else appropriate error
     * code is returned
     */
    public int setInstallInstruction(DCMInstallInstructions inInstruction) {
        mInstallInstruction = new DCMInstallInstructions(inInstruction);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the revision history
     *
     * @return the revision history
     */
    public DCMI18NString getRevisionHistory() {
        return mRevisionHistory;
    }

    /**
     * Method for setting the revision history
     *
     * @param inHistory specifies the revision history to be set
     * @return SUCCESS if the instruction could be set else appropriate error
     * code is returned
     */
    public int setRevisionHistory(DCMI18NString inHistory) {
        mRevisionHistory = new DCMI18NString(inHistory);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the important information
     *
     * @return the important information
     */
    public DCMImportantInformation getImportantInformation() {
        return mImportantInformation;
    }

    /**
     * Method for setting the important information
     *
     * @param inInformation specifies the information to be set
     * @return SUCCESS if the information could be set else appropriate error
     * code is returned.
     */
    public int setImportantInformation(DCMImportantInformation inInformation) {
        mImportantInformation = new DCMImportantInformation(inInformation);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the criticality
     *
     * @return the criticality
     */
    public DCMCriticality getCriticality() {
        return mCriticality;
    }

    /**
     * Method for setting the criticality
     *
     * @param inCriticality specifies the criticality to be set
     * @return SUCCESS if the criticality could be set else appropriate error
     * code is returned.
     */
    public int setCriticality(DCMCriticality inCriticality) {
        mCriticality = inCriticality;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the criticality description
     *
     * @return the criticality description
     */
    public DCMI18NString getCriticalityDescription() {
        return mCriticalityDescription;
    }

    /**
     * Method for setting the criticality description
     *
     * @param inDescription specifies the description to be set
     * @return SUCCESS if the criticality description could be set else
     * appropriate error code is returned.
     */
    public int setCriticalityDescription(DCMI18NString inDescription) {
        mCriticalityDescription = new DCMI18NString(inDescription);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the MSIID
     *
     * @return MSIID
     */
    public DCMGUID getMSIID() {
        return mMSIID;
    }

    /**
     * Method for setting the MSIID
     *
     * @param inID specifies the MSIID to be set
     * @return SUCCESS if the MSIID could be set else appropriate error code is
     * returned.
     */
    public int setMSIID(DCMGUID inID) {
        mMSIID = new DCMGUID(inID);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the identifier
     *
     * @return identifier
     */
    public DCMGUID getIdentifier() {
        return mIdentifier;
    }

    /**
     * Method for setting the identifier
     *
     * @param inID specifies the identifier to be set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned.
     */
    public int setIdentifier(DCMGUID inID) {
        mIdentifier = new DCMGUID(inID);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the predecessor identifier
     *
     * @return predecessor identifier
     */
    public DCMGUID getPredecessorIdentifier() {
        return mPredecessorID;
    }

    /**
     * Method for setting the predecessor identifier
     *
     * @param inID specifies the predecessor identifier to be set
     * @return SUCCESS if the predecessor identifier could be set else
     * appropriate error code is returned.
     */
    public int setPredecessorIdentifier(DCMGUID inID) {
        mPredecessorID = new DCMGUID(inID);
        return DCMErrorCodes.SUCCESS;
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
     * returned.
     */
    public int setName(DCMI18NString inName) {
        mName = new DCMI18NString(inName);
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
     * @param inType specifies the component type to be set
     * @return SUCCESS if the component type could be set else appropriate error
     * code is returned.
     */
    public int setComponentType(DCMComponentType inType) {
        mComponentType = inType;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the description
     *
     * @return the description
     */
    public DCMI18NString getDescription() {
        return mDescription;
    }

    /**
     * Method for setting the description
     *
     * @param inDescription specifies the description to be set
     * @return SUCCESS if the description could be set else appropriate error
     * code is returned.
     */
    public int setDescription(DCMI18NString inDescription) {
        mDescription = new DCMI18NString(inDescription);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the schema version
     *
     * @return the schema version
     */
    public String getSchemaVersion() {
        return mSchemaVersion;
    }

    /**
     * Method for setting the schema version
     *
     * @param inVersion specifies the schema version to be set
     * @return SUCCESS if the schema version could be set else appropriate error
     * code is returned.
     */
    public int setSchemaVersion(String inVersion) {
        mSchemaVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the release id
     *
     * @return the release id
     */
    public String getReleaseID() {
        return mReleaseID;
    }

    /**
     * Method for setting the release id
     *
     * @param inID specifies the release id
     * @return SUCCESS if the release id could be set else appropriate error
     * code is returned
     */
    public int setReleaseID(String inID) {
        mReleaseID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the release date
     *
     * @return the release date
     */
    public Date getReleaseDate() {
        return mReleaesDate;
    }

    /**
     * Method for setting the release date
     *
     * @param inDate specifies the release date to be set
     * @return SUCCESS if the release date could be set else appropriate error
     * code is returned.
     */
    public int setReleaseDate(Date inDate) {
        mReleaesDate = inDate;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the date
     *
     * @return the date
     */
    public Date getDate() {
        return mDate;
    }

    /**
     * Method for setting the date
     *
     * @param inDate specifies the date to be set
     * @return SUCCESS if the date could be set else appropriate error code is
     * returned.
     */
    public int setDate(Date inDate) {
        mDate = inDate;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the vendor version
     *
     * @return the vendor version
     */
    public String getVendorVersion() {
        return mVendorVersion;
    }

    /**
     * Method for setting the vendor version
     *
     * @param inVersion the vendor version to be set
     * @return SUCCESS if the vendor version could be set else appropriate error
     * code is returned.
     */
    public int setVendorVersion(String inVersion) {
        mVendorVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the dell version
     *
     * @return the dell version
     */
    public String getDellVersion() {
        return mDellVersion;
    }

    /**
     * Method for setting the dell version
     *
     * @param inVersion the dell version to be set
     * @return SUCCESS if the dell version could be set else appropriate error
     * code is returned.
     */
    public int setDellVersion(String inVersion) {
        mDellVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the XML gen version
     *
     * @return the XML gen version
     */
    public String getXMLGenVersion() {
        return mXMLGenVersion;
    }

    /**
     * Method for setting the XML gen version
     *
     * @param inVersion the XML gen version to be set
     * @return SUCCESS if the XML gen version could be set else appropriate
     * error code is returned.
     */
    public int setXMLGenVersion(String inVersion) {
        mXMLGenVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the package type
     *
     * @return the package type
     */
    public DCMPackageType getPackageType() {
        return mPackageType;
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
     * @param inPath the path to be set
     * @return SUCCESS if the path could be set else appropriate error code is
     * returned.
     */
    public int setPath(String inPath) {
        mPath = inPath;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the package id
     *
     * @return the package id
     */
    public String getPackageID() {
        return mPackageID;
    }

    /**
     * Method for getting the MD5 Hash
     *
     * @return the MD5 hash
     */
    public String getMD5Hash() {
        return mHashMD5;
    }

    /**
     * Method for setting the MD5 hash
     *
     * @param inHash specifies the hash to be set
     * @return SUCCESS if the hash could be set else appropriate error code is
     * returned
     */
    public int setMD5Hash(String inHash) {
        mHashMD5 = inHash;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the Hash of Hash
     *
     * @return the package Hash of Hash
     */
    public String getHashofHash() {
        return mHashofHash;
    }

    /**
     * Method for setting the Hash of Hash
     *
     * @param inHash specifies the Hash of Hash
     * @return SUCCESS if the release id could be set else appropriate error
     * code is returned
     */
    public int setHashofHash(String inHash) {
        mHashofHash = inHash;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the size
     *
     * @return the size
     */
    public long getSize() {
        return mSize;
    }

    /**
     * Method for setting the size
     *
     * @param inSize specifies the size to be set
     * @return SUCCESS if the size could be set else appropriate error code is
     * returned.
     */
    public int setSize(long inSize) {
        mSize = inSize;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for determining whether the update package requires reboot or not
     *
     * @return true if the update package requires reboot else false is returned
     */
    public boolean requiresReboot() {
        return mRebootRequired;
    }

    /**
     * Method for setting the reboot required value
     *
     * @param inValue specifies the value to be set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned
     */
    public int setRequiresReboot(boolean inValue) {
        mRebootRequired = inValue;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for determining whether the update package requires container
     * power cycle or not
     *
     * @return true if the update package requires container power cycle else
     * false is returned
     */
    public boolean requiresContainerPowerCycle() {
        return mContainerPowerCycleRequired;
    }

    /**
     * Method for setting the container power cycle required value
     *
     * @param inValue specifies the value to be set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned
     */
    public int setRequiresContainerPowerCycle(boolean inValue) {
        mContainerPowerCycleRequired = inValue;
        return DCMErrorCodes.SUCCESS;
    }

    public HashMap<String, DCMBaseTarget> getTargetMap() {
        return mTargetMap;
    }

    /**
     * Method for getting the update information
     *
     * @param inVersionInfoList specifies the version collection information
     * specified
     * @param inVersionInfo specifies the version information specified
     * @param inSystemTypeIdentifier specifies the system type
     * @param inOSIdentifier specifies the OS identifier
     * @param inConsideration specifies the operator to be used for getting the
     * update information
     * @return the update information if it is supported by this package else it
     * would be null
     */
    public DCMUpdateInformation getUpdateInformation(Collection<DCMVersionInformation> inVersionInfoList, DCMVersionInformation inVersionInfo, String inSystemTypeIdentifier, String inOSIdentifier, DCMConsiderationEnum inConsideration) {
        DCMUpdateInformation retVal = null;
        if (inVersionInfo == null) {
            return retVal;
        }
        // If the update package is OS specific and the given OS is not in its list then return null
        if (inOSIdentifier != null && !mOperatingSystemIdentifierSet.isEmpty() && !mOperatingSystemIdentifierSet.contains(inOSIdentifier)) {
            return retVal;
        }
        // If the update package is system specific and the given system is not in its list then return null
        if (inSystemTypeIdentifier != null && !mSystemIdentifierSet.isEmpty() && !mSystemIdentifierSet.contains(inSystemTypeIdentifier)) {
            return retVal;
        }
        String inTargetIdentifier = inVersionInfo.getTargetIdentifier();
        // If the update package does not support this target then return null
        if (!mTargetIdentifierSet.isEmpty() && !mTargetIdentifierSet.contains(inTargetIdentifier)) {
            return retVal;
        }
        DCMUpdateableComponent tempComponent = new DCMUpdateableComponent(mComponentType, inTargetIdentifier);
        String updateableComponentIdentifier = tempComponent.getUniqueIdentifier();
        // If the update package is not for the same component type as that of version info return null
        if (!inVersionInfo.getUpdateableComponentIdentifier().equals(updateableComponentIdentifier)) {
            return retVal;
        }
        // If the constituents are there determine whether there is any consitutnet for this component in this context.
        // If it is not there return null
        if (!mConstituents.isEmpty()) {
            for (DCMConstituent constituent : mConstituents) {
                if (constituent.supports(inVersionInfo, inSystemTypeIdentifier, inOSIdentifier)) {
                    String constituentVersion = constituent.getVersion();
                    DCMVersionComparison versionComparison = new DCMVersionComparison(constituentVersion, inVersionInfo.getVersion());
                    DCMComparisonResultType comparisonResult = versionComparison.compare();
                    //if the constituent version is higher than the source version then this update package is applicable else return null
                    if ((comparisonResult == DCMComparisonResultType.GREATER && (inConsideration != DCMConsiderationEnum.REPORT_DOWNGRADES_ONLY))
                            || (comparisonResult == DCMComparisonResultType.LOWER && (inConsideration != DCMConsiderationEnum.REPORT_UPGRADES_ONLY))
                            || (comparisonResult == DCMComparisonResultType.EQUAL && (inConsideration == DCMConsiderationEnum.REPORT_ALL))) {
                        retVal = new DCMUpdateInformation();
                        retVal.setCriticality(mCriticality);
                        retVal.setName(mName);
                        retVal.setPath(mPath);
                        retVal.setUniqueIdentifier(this.getUniqueIdentifier());
                        retVal.setVersion(mVendorVersion);
                        retVal.setRequiresReboot(mRebootRequired);
                        if (comparisonResult == DCMComparisonResultType.GREATER) {
                            retVal.setType(DCMUpdateType.UPGRADE);
                        } else if (comparisonResult == DCMComparisonResultType.LOWER) {
                            retVal.setType(DCMUpdateType.DOWNGRADE);
                        } else {
                            retVal.setType(DCMUpdateType.EQUAL);
                        }
                        return retVal;
                    }
                }
            }
        }
        DCMVersionComparison versionComparison = new DCMVersionComparison(mVendorVersion, inVersionInfo.getVersion());
        //if the vendor version is greater than the source version then this update package is applicable else return null
        DCMComparisonResultType comparisonResult = versionComparison.compare();
        if ((comparisonResult == DCMComparisonResultType.GREATER && (inConsideration != DCMConsiderationEnum.REPORT_DOWNGRADES_ONLY))
                || (comparisonResult == DCMComparisonResultType.LOWER && (inConsideration != DCMConsiderationEnum.REPORT_UPGRADES_ONLY))
                || (comparisonResult == DCMComparisonResultType.EQUAL && (inConsideration == DCMConsiderationEnum.REPORT_ALL))) {
            retVal = new DCMUpdateInformation();
            retVal.setCriticality(mCriticality);
            retVal.setName(mName);
            retVal.setPath(mPath);
            retVal.setUniqueIdentifier(this.getUniqueIdentifier());
            retVal.setVersion(mVendorVersion);
            retVal.setRequiresReboot(mRebootRequired);
            if (comparisonResult == DCMComparisonResultType.GREATER) {
                retVal.setType(DCMUpdateType.UPGRADE);
            } else if (comparisonResult == DCMComparisonResultType.LOWER) {
                retVal.setType(DCMUpdateType.DOWNGRADE);
            } else {
                retVal.setType(DCMUpdateType.EQUAL);
            }
        }
        if (this.mActivationRules != null) {
            DCMRuleData ruleData = new DCMRuleData(inVersionInfoList, inVersionInfo, inSystemTypeIdentifier, inOSIdentifier, mDellVersion, mVendorVersion);
            Map<DCMMessage, List<DCMActionRule>> activationrule = mActivationRules.getApplicableRules(ruleData);
            if (activationrule != null) {
                retVal.getActivationRules().putAll(activationrule);
            }
        }
        return retVal;
    }

    /**
     * Method for adding the dependency information
     *
     * @param inDependency specifies the dependency to be added
     * @return the integer with which the dependency is to be referred
     */
    public int addDependencyInformation(DCMDependency inDependency) {
        if (inDependency == null) {
            return -1;
        }
        if (mDependencies.containsValue(inDependency)) {
            for (Integer key : mDependencies.keySet()) {
                if (mDependencies.get(key) == inDependency) {
                    return key;
                }
            }
        }
        int candidateKey = 1;
        for (Integer key : mDependencies.keySet()) {
            if (key > candidateKey) {
                candidateKey = key;
            }
        }
        mDependencies.put(candidateKey + 1, inDependency);
        return candidateKey + 1;
    }

    /**
     * Method for removing the dependency information
     *
     * @param inDependency specifies the dependency to be removed
     * @return SUCCESS if the dependency could be removed else appropriate error
     * code is returned
     */
    public int removeDependencyInformation(DCMDependency inDependency) {
        if (inDependency == null) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mDependencies.containsValue(inDependency)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        Integer foundKey = -1;
        for (Integer key : mDependencies.keySet()) {
            if (mDependencies.get(key) == inDependency) {
                foundKey = -1;
            }
        }
        if (foundKey != -1) {
            mDependencies.remove(foundKey);
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the dependencies
     *
     * @return the dependencies
     */
    public Collection<DCMDependency> getDependencies() {
        return mDependencies.values();
    }

    /**
     * Method for adding the soft dependency information
     *
     * @param inDependency specifies the soft dependency to be added
     * @return the integer with which the soft dependency is to be referred
     */
    public int addSoftDependencyInformation(DCMSoftDependency inDependency) {
        if (inDependency == null) {
            return -1;
        }
        if (mSoftDependencies.containsValue(inDependency)) {
            for (Integer key : mSoftDependencies.keySet()) {
                if (mSoftDependencies.get(key) == inDependency) {
                    return key;
                }
            }
        }
        int candidateKey = 1;
        for (Integer key : mDependencies.keySet()) {
            if (key > candidateKey) {
                candidateKey = key;
            }
        }
        mSoftDependencies.put(candidateKey + 1, inDependency);
        return candidateKey + 1;
    }

    /**
     * Method for removing the soft dependency information
     *
     * @param inDependency specifies the soft dependency to be removed
     * @return SUCCESS if the soft dependency could be removed else appropriate
     * error code is returned
     */
    public int removeSoftDependencyInformation(DCMSoftDependency inDependency) {
        if (inDependency == null) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mSoftDependencies.containsValue(inDependency)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        Integer foundKey = -1;
        for (Integer key : mSoftDependencies.keySet()) {
            if (mSoftDependencies.get(key) == inDependency) {
                foundKey = -1;
            }
        }
        if (foundKey != -1) {
            mSoftDependencies.remove(foundKey);
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the soft dependencies
     *
     * @return the dependencies
     */
    public Collection<DCMSoftDependency> getSoftDependencies() {
        return mSoftDependencies.values();
    }

    /**
     * Method for adding dependency reference for a target
     *
     * @param inTargetIdentifier specifies the target for which the dependency
     * is to be associated
     * @param inDependency specifies the dependency reference to be associated
     * @return SUCCESS if the reference could be added else appropriate error
     * code is returned
     */
    public int addDependencyReference(String inTargetIdentifier, DCMDependency inDependency) {
        if (inDependency == null || inTargetIdentifier.isEmpty()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mDependencies.values().contains(inDependency)) {
            int dependencyReference = addDependencyInformation(inDependency);
            return addDependencyReference(inTargetIdentifier, dependencyReference);
        }
        for (Integer key : mDependencies.keySet()) {
            if (inDependency == mDependencies.get(key)) {
                return addDependencyReference(inTargetIdentifier, key);
            }
        }
        return DCMErrorCodes.FAILURE;
    }

    /**
     * Method for adding dependency reference for a target
     *
     * @param inTargetIdentifier specifies the target for which the dependency
     * is to be associated
     * @param inDependencyReference specifies the dependency reference to be
     * associated
     * @return SUCCESS if the reference could be added else appropriate error
     * code is returned.
     */
    public int addDependencyReference(String inTargetIdentifier, int inDependencyReference) {
        if (inTargetIdentifier.isEmpty() || inDependencyReference <= 0) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        HashSet<Integer> dependencySet = mDependencyMap.get(inTargetIdentifier);
        if (dependencySet == null) {
            dependencySet = new HashSet<>();
        }
        dependencySet.add(inDependencyReference);
        mDependencyMap.put(inTargetIdentifier, dependencySet);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing dependency reference for a target
     *
     * @param inTargetIdentifier specifies the target for which the dependency
     * is to be dissociated
     * @param inDependency specifies the dependency reference to be dissociated
     * @return SUCCESS if the reference could be removed else appropriate error
     * code is returned
     */
    public int removeDependencyReference(String inTargetIdentifier, DCMDependency inDependency) {
        if (inDependency == null || inTargetIdentifier.isEmpty()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mDependencies.values().contains(inDependency)) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        for (Integer key : mDependencies.keySet()) {
            if (inDependency == mDependencies.get(key)) {
                return removeDependencyReference(inTargetIdentifier, key);
            }
        }
        return DCMErrorCodes.FAILURE;
    }

    /**
     * Method for removing dependency association with the given target
     *
     * @param inTargetIdentifier specifies the target for which the dependency
     * is to be dissociated
     * @param inDependencyReference specifies the dependency is to be
     * dissociated
     * @return SUCCESS if the dependency could be dissociated with the target
     * else appropriate error code is returned
     */
    public int removeDependencyReference(String inTargetIdentifier, int inDependencyReference) {
        if (inTargetIdentifier.isEmpty() || inDependencyReference <= 0) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        HashSet<Integer> dependencySet = mDependencyMap.get(inTargetIdentifier);
        if (dependencySet == null || !dependencySet.contains(inDependencyReference)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        dependencySet.remove(inDependencyReference);
        mDependencyMap.put(inTargetIdentifier, dependencySet);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the dependencies for the given target
     *
     * @param inTargetIdentifier specifies the target for which the dependencies
     * are sought
     * @return the dependency collection
     */
    public Collection<DCMDependency> getDependenciesForTheGivenTarget(String inTargetIdentifier) {
        if (inTargetIdentifier.isEmpty()) {
            return null;
        }
        HashSet<Integer> dependencySet = mDependencyMap.get(inTargetIdentifier);
        if (dependencySet == null || dependencySet.isEmpty()) {
            return null;
        }
        HashSet<DCMDependency> retVal = new HashSet<>();
        for (Integer dependencyKey : dependencySet) {
            DCMDependency dependency = mDependencies.get(dependencyKey);
            if (dependency != null) {
                retVal.add(dependency);
            }
        }
        return retVal;
    }

    /**
     * Method for adding soft dependency reference for the given target
     *
     * @param inTargetIdentifier specifies the target identifier for which the
     * soft dependency reference is to be added
     * @param inDependency specifies the dependency that is to be associated
     * @return SUCCESS if the dependency could be associated else
     */
    public int addSoftDependencyReference(String inTargetIdentifier, DCMSoftDependency inDependency) {
        if (inDependency == null || inTargetIdentifier.isEmpty()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mSoftDependencies.values().contains(inDependency)) {
            int dependencyReference = addSoftDependencyInformation(inDependency);
            return addSoftDependencyReference(inTargetIdentifier, dependencyReference);
        }
        for (Integer key : mSoftDependencies.keySet()) {
            if (inDependency == mSoftDependencies.get(key)) {
                return addSoftDependencyReference(inTargetIdentifier, key);
            }
        }
        return DCMErrorCodes.FAILURE;
    }

    /**
     * Method for adding soft dependency reference for the given target
     *
     * @param inTargetIdentifier specifies the target identifier for which the
     * soft dependency reference is to be added
     * @param inDependencyReference specifies the dependency that is to be
     * associated
     * @return SUCCESS if the dependency could be associated else
     */
    public int addSoftDependencyReference(String inTargetIdentifier, int inDependencyReference) {
        if (inTargetIdentifier.isEmpty() || inDependencyReference <= 0) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        HashSet<Integer> dependencySet = mSoftDependencyMap.get(inTargetIdentifier);
        if (dependencySet == null) {
            dependencySet = new HashSet<>();
        }
        dependencySet.add(inDependencyReference);
        mSoftDependencyMap.put(inTargetIdentifier, dependencySet);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing soft dependency reference for a target
     *
     * @param inTargetIdentifier specifies the target for which the dependency
     * is to be dissociated
     * @param inDependency specifies the soft dependency reference to be
     * dissociated
     * @return SUCCESS if the reference could be removed else appropriate error
     * code is returned
     */
    public int removeSoftDependencyReference(String inTargetIdentifier, DCMSoftDependency inDependency) {
        if (inDependency == null || inTargetIdentifier.isEmpty()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mSoftDependencies.values().contains(inDependency)) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        for (Integer key : mSoftDependencies.keySet()) {
            if (inDependency == mSoftDependencies.get(key)) {
                return removeDependencyReference(inTargetIdentifier, key);
            }
        }
        return DCMErrorCodes.FAILURE;
    }

    /**
     * Method for removing soft dependency reference for a target
     *
     * @param inTargetIdentifier specifies the target for which the dependency
     * is to be dissociated
     * @param inDependencyReference specifies the soft dependency reference to
     * be dissociated
     * @return SUCCESS if the reference could be removed else appropriate error
     * code is returned
     */
    public int removeSoftDependencyReference(String inTargetIdentifier, int inDependencyReference) {
        if (inTargetIdentifier.isEmpty() || inDependencyReference <= 0) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        HashSet<Integer> dependencySet = mSoftDependencyMap.get(inTargetIdentifier);
        if (dependencySet == null || !dependencySet.contains(inDependencyReference)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        dependencySet.remove(inDependencyReference);
        mSoftDependencyMap.put(inTargetIdentifier, dependencySet);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the soft dependencies for the given target
     *
     * @param inTargetIdentifier target identifier for soft dependencies
     * @return soft dependencies for the given target.
     */
    public Collection<DCMSoftDependency> getSoftDependenciesForTheGivenTarget(String inTargetIdentifier) {
        if (inTargetIdentifier.isEmpty()) {
            return null;
        }
        HashSet<Integer> dependencySet = mSoftDependencyMap.get(inTargetIdentifier);
        if (dependencySet == null || dependencySet.isEmpty()) {
            return null;
        }
        HashSet<DCMSoftDependency> retVal = new HashSet<>();
        for (Integer dependencyKey : dependencySet) {
            DCMSoftDependency dependency = mSoftDependencies.get(dependencyKey);
            if (dependency != null) {
                retVal.add(dependency);
            }
        }
        return retVal;
    }

    /**
     * returns the DCMBaseTarget of the specified Identifier
     *
     * @param inIdentifier
     * @return
     */
    public DCMBaseTarget getTarget(String inIdentifier) {
        DCMBaseTarget retVal = null;
        if (mTargetMap.containsKey(inIdentifier)) {
            retVal = mTargetMap.get(inIdentifier);
        }
        return retVal;
    }

    /**
     * returns all the targets in mTargetMap
     *
     * @return
     */
    public Collection<DCMBaseTarget> getTargets() {
        return mTargetMap.values();
    }

    /**
     * adds the given target to mTargetMap
     *
     * @param inTarget
     * @return
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
     * removes the Target of given Identifier
     *
     * @param inIdentifier
     * @return
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

    private String mCategoryCode;
    private String mLUCategoryCode;
    private final HashSet<String> mSystemIdentifierSet;
    private final HashSet<String> mOperatingSystemIdentifierSet;
    private final HashSet<String> mTargetIdentifierSet;
    private final HashSet<DCMConstituent> mConstituents;
    private final HashSet<DCMFMPWrapperInformation> mFMPWrappers;
    private DCMInstallInstructions mInstallInstruction;
    private DCMI18NString mRevisionHistory;
    private DCMImportantInformation mImportantInformation;
    private DCMCriticality mCriticality;
    private DCMI18NString mCriticalityDescription;
    private DCMGUID mMSIID;
    private DCMI18NString mName;
    private DCMComponentType mComponentType;
    private DCMI18NString mDescription;
    private String mSchemaVersion;
    private String mReleaseID;
    private Date mReleaesDate;
    private String mVendorVersion;
    private String mDellVersion;
    private String mXMLGenVersion;
    private final DCMPackageType mPackageType;
    private String mPath;
    private final String mPackageID;
    private Date mDate;
    private String mHashMD5;
    private String mHashofHash;
    private long mSize;
    private DCMGUID mIdentifier;
    private DCMGUID mPredecessorID;
    private boolean mRebootRequired;
    private boolean mContainerPowerCycleRequired;
    private DCMActivationRules mActivationRules;
    private final HashMap<Integer, DCMDependency> mDependencies;
    private final HashMap<Integer, DCMSoftDependency> mSoftDependencies;
    private final HashMap<String, HashSet<Integer>> mDependencyMap;
    private final HashMap<String, HashSet<Integer>> mSoftDependencyMap;
    private final HashMap<String, DCMBaseTarget> mTargetMap;

}
