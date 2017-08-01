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

import java.util.Date;
import java.util.HashSet;

/**
 * class for representing Software Bundle
 *
 * @author Raveendra_Madala
 */
public class DCMBundle {

    /**
     * Constructor
     *
     * @param inType specifies the bundle type
     */
    public DCMBundle(DCMBundleType inType) {
        mType = inType;
        mTargetSystems = new HashSet<>();
        mTargetOSes = new HashSet<>();
        mUpdatePackages = new HashSet<>();
    }

    /**
     * Constructor for DCM Bundle. Initializing attributes of DCMBundle from the
     * given bundle
     *
     * @param bundle specifies the DCMBundle
     */
    public DCMBundle(DCMBundle bundle) {
        this(bundle.getType());
        setBundleID(bundle.getBundleID());
        setCreationTime(bundle.getCreationTime());
        setSize(bundle.getSize());
        setIdentifier(bundle.getIdentifier());
        setVendorVersion(bundle.getVendorVersion());
        setPath(bundle.getPath());
        setReleaseID(bundle.getReleaseID());
        setPredecessorIdentifier(bundle.getPredecessorIdentifier());

        setCategoryCode(bundle.getCategoryCode());
        setComponentType(bundle.getComponentType());

        setDescription(bundle.getDescription());
        setImportantInformation(bundle.getImportantInformation());
        setName(bundle.getName());
        setRevisionHistory(bundle.getRevisionHistory());
        setSchemaVersion(bundle.getSchemaVersion());
        for (String osIdentifier : bundle.getTargetOperatingSystemIdentifiers()) {
            addTargetOperatingSystemIdentifier(osIdentifier);
        }
        for (String systemId : bundle.getTargetSystemIdentifiers()) {
            addTargetSystemIdentifier(systemId);
        }
    }

    /**
     * Method for getting the bundle type
     *
     * @return the bundle type
     */
    public DCMBundleType getType() {
        return mType;
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
     * Method for getting the target system identifiers
     *
     * @return the target system identifiers
     */
    public HashSet<String> getTargetSystemIdentifiers() {
        return mTargetSystems;
    }

    /**
     * Method for adding the target system identifier
     *
     * @param inIdentifier specifies the system identifier being added
     * @return SUCCESS if the system could be added else appropriate error code
     * is returned
     */
    public int addTargetSystemIdentifier(String inIdentifier) {
        if (null == inIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mTargetSystems.contains(inIdentifier)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mTargetSystems.add(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the target system identifier
     *
     * @param inIdentifier specifies the system identifier being removed
     * @return SUCCESS if the system could be removed else appropriate error
     * code is returned
     */
    public int removeTargetSystemIdentifier(String inIdentifier) {
        if (null == inIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mTargetSystems.contains(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mTargetSystems.remove(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for determining whether the specified system is supported by this
     * bundle or not
     *
     * @param inIdentifier specifies the system identifier
     * @return true if the bundle supports the system else false is returned
     */
    public boolean supportsSystem(String inIdentifier) {
        if (inIdentifier == null || inIdentifier.isEmpty()) {
            return false;
        }
        if (mTargetSystems.contains(inIdentifier)) {
            return true;
        }
        return false;
    }

    /**
     * Method for getting the target operating system identifiers
     *
     * @return the target operating system identifiers
     */
    public HashSet<String> getTargetOperatingSystemIdentifiers() {
        return mTargetOSes;
    }

    /**
     * Method for adding the target operating system identifier
     *
     * @param inIdentifier specifies the operating system identifier being added
     * @return SUCCESS if the operating system identifier could be added else
     * appropriate error code is returned
     */
    public int addTargetOperatingSystemIdentifier(String inIdentifier) {
        if (null == inIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mTargetOSes.contains(inIdentifier)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mTargetOSes.add(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the target operating system identifier
     *
     * @param inIdentifier specifies the operating system identifier being
     * removed
     * @return SUCCESS if the operating system could be removed else appropriate
     * error code is returned
     */
    public int removeTargetOperatingSystemIdentifier(String inIdentifier) {
        if (null == inIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mTargetOSes.contains(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mTargetOSes.remove(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for determining whether the specified operating system is
     * supported by this bundle or not
     *
     * @param inIdentifier specifies the operating system identifier
     * @return true if the bundle supports the operating system else false is
     * returned
     */
    public boolean supportsOperatingSystem(String inIdentifier) {
        if (inIdentifier == null || inIdentifier.isEmpty()) {
            return false;
        }
        if (mTargetOSes.contains(inIdentifier)) {
            return true;
        }
        return false;
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
     * @param inHistory specifies the revision history being set
     * @return SUCCESS if the revision history could be set else appropriate
     * error code is returned.
     */
    public int setRevisionHistory(DCMI18NString inHistory) {
        mRevisionHistory = new DCMI18NString(inHistory);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the important information
     *
     * @return important information
     */
    public DCMImportantInformation getImportantInformation() {
        return mImportantInformation;
    }

    /**
     * Method for setting the important information
     *
     * @param inInformation specifies the information being added
     * @return SUCCESS if the information could be set else appropriate error
     * code is returned
     */
    public int setImportantInformation(DCMImportantInformation inInformation) {
        mImportantInformation = new DCMImportantInformation(inInformation);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the update package identifiers
     *
     * @return the hash set of update package identifiers
     */
    public HashSet<String> getUpdatePackageIdentifiers() {
        return mUpdatePackages;
    }

    /**
     * Method for adding update package identifier
     *
     * @param inIdentifier specifies the identifier to be added
     * @return SUCCESS if the identifier could be added else appropriate error
     * code is returned
     */
    public int addUpdatePackageIdentifier(String inIdentifier) {
        if (null == inIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mUpdatePackages.contains(inIdentifier)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mUpdatePackages.add(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing update package identifier
     *
     * @param inIdentifier specifies the identifier to be removed
     * @return SUCCESS if the identifier could be removed else appropriate error
     * code is returned
     */
    public int removeUpdatePackageIdentifier(String inIdentifier) {
        if (null == inIdentifier) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mUpdatePackages.contains(inIdentifier)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mUpdatePackages.remove(inIdentifier);
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
     * @param inID specifies the release id being set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned.
     */
    public int setReleaseID(String inID) {
        mReleaseID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the bundle size
     *
     * @return the bundle size
     */
    public long getSize() {
        return mSize;
    }

    /**
     * Method for setting the bundle size
     *
     * @param inSize specifies the size being set
     * @return SUCCESS if the size could be set else appropriate error code is
     * returned
     */
    public int setSize(long inSize) {
        mSize = inSize;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the identifier
     *
     * @return the bundle identifier
     */
    public DCMGUID getIdentifier() {
        return mIdentifier;
    }

    /**
     * Method for setting the identifier
     *
     * @param inIdentifier specifies the identifier being set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned.
     */
    public int setIdentifier(DCMGUID inIdentifier) {
        if (inIdentifier != null) {
            mIdentifier = new DCMGUID(inIdentifier);
        } else {
            mIdentifier = null;
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the predecessor identifier
     *
     * @return the bundle identifier
     */
    public DCMGUID getPredecessorIdentifier() {
        return mPredecessorIdentifier;
    }

    /**
     * Method for setting the predecessor identifier
     *
     * @param inIdentifier specifies the predecessor identifier being set
     * @return SUCCESS if the predecessor identifier could be set else
     * appropriate error code is returned.
     */
    public int setPredecessorIdentifier(DCMGUID inIdentifier) {
        if (inIdentifier != null) {
            mPredecessorIdentifier = new DCMGUID(inIdentifier);
        } else {
            mPredecessorIdentifier = null;
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the bundle id
     *
     * @return the bundle id
     */
    public String getBundleID() {
        return mBundleID;
    }

    /**
     * Method for setting the bundle id
     *
     * @param inID specifies the bundle id to be set
     * @return SUCCCESS if the bundle id could be set else appropriate error
     * code is returned.
     */
    public int setBundleID(String inID) {
        mBundleID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the bundle path
     *
     * @return the bundle path
     */
    public String getPath() {
        return mPath;
    }

    /**
     * Method for setting the bundle path
     *
     * @param inPath specifies the bundle path to be set
     * @return SUCCCESS if the bundle path could be set else appropriate error
     * code is returned.
     */
    public int setPath(String inPath) {
        mPath = inPath;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the bundle vendor version
     *
     * @return the bundle vendor version
     */
    public String getVendorVersion() {
        return mVendorVersion;
    }

    /**
     * Method for setting the bundle vendor version
     *
     * @param inVersion specifies the bundle vendor version to be set
     * @return SUCCCESS if the bundle vendor version could be set else
     * appropriate error code is returned.
     */
    public int setVendorVersion(String inVersion) {
        mVendorVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the bundle schema version
     *
     * @return the bundle schema version
     */
    public String getSchemaVersion() {
        return mSchemaVersion;
    }

    /**
     * Method for setting the bundle schema version
     *
     * @param inVersion specifies the bundle schema version to be set
     * @return SUCCCESS if the bundle schema version could be set else
     * appropriate error code is returned.
     */
    public int setSchemaVersion(String inVersion) {
        mSchemaVersion = inVersion;
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
     * @param inComponentType specifies the component type to be set
     * @return SUCCESS if the component type could be set else appropriate error
     * code is returned.
     */
    public int setComponentType(DCMComponentType inComponentType) {
        mComponentType = inComponentType;
        return DCMErrorCodes.SUCCESS;
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
     * @param inCategoryCode specifies the code to be set
     * @return SUCCESS if the category code could be set else appropriate error
     * code is returned
     */
    public int setCategoryCode(String inCategoryCode) {
        mCategoryCode = inCategoryCode;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the creation time
     *
     * @return the creation time
     */
    public Date getCreationTime() {
        return mCreationTime;
    }

    /**
     * Method for setting the creation time
     *
     * @param inTime specifies the time being set
     * @return SUCCESS if the creation time could be set else appropriate error
     * code is returned.
     */
    public int setCreationTime(Date inTime) {
        mCreationTime = inTime;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the bundle unique identifier
     *
     * @return unique identifier value
     */
    public String getUniqueIdentifier() {
        if (null != mIdentifier) {
            return mIdentifier.getID();
        }
        return mBundleID;
    }

    private final DCMBundleType mType;
    private DCMComponentType mComponentType;
    private String mCategoryCode;
    private DCMI18NString mName;
    private DCMI18NString mDescription;
    private final HashSet<String> mTargetSystems;
    private final HashSet<String> mTargetOSes;
    private DCMI18NString mRevisionHistory;
    private DCMImportantInformation mImportantInformation;
    private final HashSet<String> mUpdatePackages;
    private String mReleaseID;
    private long mSize;
    private DCMGUID mIdentifier;
    private DCMGUID mPredecessorIdentifier;
    private String mBundleID;
    private Date mCreationTime;
    private String mPath;
    private String mVendorVersion;
    private String mSchemaVersion;
}
