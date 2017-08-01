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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * class for representing the Manifest
 *
 * @author Raveendra_Madala
 */
public class DCMManifest {

    /**
     * Constructor
     */
    public DCMManifest() {
        mLOBCollection = new DCMLineOfBusinessCollection();
        mSystemCollection = new DCMSystemCollection();
        mOSCollection = new DCMOperatingSystemCollection();
        mComponentKindCollection = new DCMComponentKindCollection();
        mCategoryCollection = new DCMCategoryCollection();
        mUpdateableComponentCollection = new DCMUpdateableComponentCollection();
        mBundleCollection = new DCMBundleCollection();
        mComponentCollection = new DCMUpdatePackageInformationCollection();
        mPrerequisites = new DCMUpdatePackageInformationCollection();
        mInventoryCollectorInformationCollection = new DCMInventoryCollectorInformationCollection();
    }

    /**
     * Constructor
     */
    public DCMManifest(DCMManifest manifestObj) {
        this();
        if(null != manifestObj.getIdentifier() && !manifestObj.getIdentifier().getID().isEmpty()){
            setIdentifier(manifestObj.getIdentifier());
        }
        setCreationDate((Date)manifestObj.getCreationDate().clone());

        setReleaseID(manifestObj.getReleaseID());

        setVersion(manifestObj.getVersion());

        // Populating inventory Collector information
        DCMInventoryCollectorInformationCollection manifestInvCollectorInformationCollection = manifestObj.getInventoryCollectorInformationCollection();
        if (null != manifestInvCollectorInformationCollection) {
            for (DCMInventoryCollectorInformation invCollectorInformation : manifestInvCollectorInformationCollection.getInventoryCollectorsInformation()) {
                getInventoryCollectorInformationCollection().addInventoryCollectorInformation(invCollectorInformation);
            }
        }
        if(null != manifestObj.getReleaseNotes() && !manifestObj.getReleaseNotes().isEmpty()){
            setReleaseNotes(manifestObj.getReleaseNotes());
        }
        for (DCMCategory category:manifestObj.mCategoryCollection.getCategories()) {
            mCategoryCollection.addCategory(category);
        }

        for (DCMComponentKind componentKind:manifestObj.mComponentKindCollection.getComponentKinds()) {
            mComponentKindCollection.addComponentKind(componentKind);
        }

        for (DCMOperatingSystem operatingsystem:manifestObj.mOSCollection.getOperatingSystems()) {
            mOSCollection.addOperatingSystem(operatingsystem);
        }
    }

    /**
     * Method for getting the release notes
     *
     * @return the release notes
     */
    public DCMI18NString getReleaseNotes() {
        return mReleaseNotes;
    }

    /**
     * Method for setting the release notes
     *
     * @param inReleaseNotes specifies the notes to be set
     * @return SUCCESS if the release notes could be set else appropriate error
     * code is returned.
     */
    public int setReleaseNotes(DCMI18NString inReleaseNotes) {
        mReleaseNotes = new DCMI18NString(inReleaseNotes);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the base location
     *
     * @return the base location
     */
    public String getBaseLocation() {
        return mBaseLocation;
    }

    /**
     * Method for setting the base location
     *
     * @param inLocation specifies the base location to be set
     * @return SUCCESS if the base location could be set else appropriate error
     * code is returned
     */
    public int setBaseLocation(String inLocation) {
        mBaseLocation = inLocation;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the creation date
     *
     * @return the creation date
     */
    public Date getCreationDate() {
        return mCreationDate;
    }

    /**
     * Method for setting the creation date
     *
     * @param inDate specifies the creation date to be set
     * @return SUCCESS if the creation date could be set else appropriate error
     * code is returned
     */
    public int setCreationDate(Date inDate) {
        mCreationDate = inDate;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the identifier
     *
     * @return the identifier
     */
    public DCMGUID getIdentifier() {
        return mIdentifier;
    }

    /**
     * Method for setting the identifier
     *
     * @param inIdentifier specifies the identifier to be set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned
     */
    public int setIdentifier(DCMGUID inIdentifier) {
        mIdentifier = new DCMGUID(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting predecessor identifier
     *
     * @return predecessor identifier
     */
    public DCMGUID getPredecessorIdentifier() {
        return mPredecessorIdentifier;
    }

    /**
     * Method for setting the predecessor identifier
     *
     * @param inIdentifier specifies the identifier to be set
     * @return SUCCESS if the predecessor identifier could be set else
     * appropriate error code is returned.
     */
    public int setPredecessorIdentifier(DCMGUID inIdentifier) {
        mPredecessorIdentifier = new DCMGUID(inIdentifier);
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
     * @param inReleaseID specifies the release id to be set
     * @return SUCCESS if the release id could be set else appropriate error
     * code is returned
     */
    public int setReleaseID(String inReleaseID) {
        mReleaseID = inReleaseID;
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
     * Method for getting the Line of business collection
     *
     * @return line of business collection
     */
    public DCMLineOfBusinessCollection getLineOfBusinessCollection() {
        return mLOBCollection;
    }

    /**
     * Method for getting the system collection
     *
     * @return the system collection
     */
    public DCMSystemCollection getSystemCollection() {
        return mSystemCollection;
    }

    /**
     * Method for getting the operating system collection
     *
     * @return the operating system collection
     */
    public DCMOperatingSystemCollection getOperatingSystemCollection() {
        return mOSCollection;
    }

    /**
     * Method for getting the component kind collection
     *
     * @return the component kind collection
     */
    public DCMComponentKindCollection getComponentKindCollection() {
        return mComponentKindCollection;
    }

    /**
     * Method for getting the category collection
     *
     * @return the category collection
     */
    public DCMCategoryCollection getCategoryCollection() {
        return mCategoryCollection;
    }

    /**
     * Method for getting the bundle collection
     *
     * @return the bundle collection
     */
    public DCMBundleCollection getBundleCollection() {
        return mBundleCollection;
    }

    /**
     * Method for getting the component collection
     *
     * @return the component collection
     */
    public DCMUpdateableComponentCollection getUpdateableComponentCollection() {
        return mUpdateableComponentCollection;
    }

    /**
     * Method for getting the software components
     *
     * @return the software components
     */
    public DCMUpdatePackageInformationCollection getSoftwareComponents() {
        return mComponentCollection;
    }

    /**
     * Method for getting the prerequisites
     *
     * @return the prerequisites
     */
    public DCMUpdatePackageInformationCollection getPrerequisites() {
        return mPrerequisites;
    }

    /**
     * Method for getting the inventory collector information
     *
     * @return the inventory collectors
     */
    public DCMInventoryCollectorInformationCollection getInventoryCollectorInformationCollection() {
        return mInventoryCollectorInformationCollection;
    }

    /**
     * Method for getting the relevant bundle identifiers for the specified
     * identifier
     *
     * @param inInventory specifies the inventory
     * @param inIdentifier specifies the identifier being queried
     * @param inBundleType specifies the bundle type being sought
     * @return collection of bundle identifiers
     */
    public HashMap<String, Collection<String>> getRelevantBundleIdentifiers(DCMMultiSystemInventory inInventory, String inIdentifier, DCMBundleType inBundleType) {
        HashMap<String, Collection<String>> retVal = new HashMap<>();
        if (inInventory == null || inIdentifier == null || inIdentifier.isEmpty() || inBundleType == null) {
            return retVal;
        }
        for (String systemInstanceIdentifier : inInventory.getSystemInstanceIdentifiers(inIdentifier)) {
            String systemTypeIdentifier = inInventory.getSystemIdentifier(systemInstanceIdentifier);
            Collection<String> bundleIdentifiers = mBundleCollection.getBundleIdentifiers(systemTypeIdentifier, inBundleType);
            retVal.put(systemInstanceIdentifier, bundleIdentifiers);
        }
        return retVal;
    }

    /**
     * Method for getting the bundle with the specified bundle identifier
     *
     * @param inBundleIdentifier specifies the identifier of the bundle being
     * sought
     * @return bundle with the specified bundle identifier. could be null
     */
    public DCMBundle getBundle(String inBundleIdentifier) {
        return mBundleCollection.getBundle(inBundleIdentifier);
    }

    private HashMap<DCMVersionInformation, DCMUpdateInformation> addDependentUpdateInformation(DCMUpdatePackageInformation inParentUpdatePackageInformation, DCMUpdatePackageInformation inDependentUpdatePackageInformation, Collection<DCMVersionInformation> inVersionInformationObjects, String inSystemTypeIdentifier, String inOSIdentifier, DCMConsiderationEnum inConsideration, String inDependency) {
        if (null == inParentUpdatePackageInformation || null == inVersionInformationObjects || inVersionInformationObjects.isEmpty()) {
            return null;
        }
        DCMUpdateInformation parentUpdateInfo = null;
        DCMUpdateInformation dependentUpdateInfo = null;
        DCMVersionInformation parentVersionInfo = null;
        DCMVersionInformation dependentVersionInfo = null;

        HashMap<DCMVersionInformation, DCMUpdateInformation> retVal = new HashMap<>();
        for (DCMVersionInformation versionInfo : inVersionInformationObjects) {
            DCMUpdateInformation tempParentUpdateInfo = null;
            DCMUpdateInformation tempDependentUpdateInfo = null;
            if (null != parentUpdateInfo && null != parentVersionInfo && null != dependentUpdateInfo) {
                break;
            }
            if (null == tempDependentUpdateInfo) {
                tempParentUpdateInfo = inParentUpdatePackageInformation.getUpdateInformation(inVersionInformationObjects, versionInfo, inSystemTypeIdentifier, inOSIdentifier, inConsideration);
            }
            if (null != inDependentUpdatePackageInformation && null == tempDependentUpdateInfo) {
                tempDependentUpdateInfo = inDependentUpdatePackageInformation.getUpdateInformation(inVersionInformationObjects, versionInfo, inSystemTypeIdentifier, inOSIdentifier, inConsideration);
            }
            if (null != tempParentUpdateInfo) {
                parentUpdateInfo = tempParentUpdateInfo;
                parentVersionInfo = versionInfo;
            }
            if (null != tempDependentUpdateInfo) {
                dependentUpdateInfo = tempDependentUpdateInfo;
                dependentVersionInfo = versionInfo;
            }
        }
        if (null != parentUpdateInfo && null != parentVersionInfo) {
            if (null != dependentUpdateInfo && inDependency.equals(DCMConstants.DEPENDENCY)) {
                parentUpdateInfo.addHardDependency(dependentVersionInfo, dependentUpdateInfo);
            } else if (null != dependentUpdateInfo && inDependency.equals(DCMConstants.SOFT_DEPENDENCY)) {
                parentUpdateInfo.addSoftDependencyInformation(dependentVersionInfo, dependentUpdateInfo);
            }
            retVal.put(parentVersionInfo, parentUpdateInfo);
        }
        return retVal;
    }

    /**
     * Method for getting the applicable updates
     *
     * @param inBundleIdentifier specifies the bundle in which to look for
     * applicable updates
     * @param inVersionInformationCollection specifies the inventory information
     * @param inSystemTypeIdentifier specifies the system type identifier
     * @param inOSIdentifier specifies the OS identifier
     * @param inConsideration specifies the operator to be used for determining
     * the applicable updates
     * @return the applicable updates
     */
    public HashMap<DCMVersionInformation, DCMUpdateInformation> getApplicableUpdates(String inBundleIdentifier, DCMVersionInformationCollection inVersionInformationCollection, String inSystemTypeIdentifier, String inOSIdentifier, DCMConsiderationEnum inConsideration) {
        if (inBundleIdentifier == null || inBundleIdentifier.isEmpty() || inVersionInformationCollection == null) {
            return null;
        }
        DCMBundle bundle = mBundleCollection.getBundle(inBundleIdentifier);
        if (bundle == null) {
            return null;
        }
        Collection<DCMVersionInformation> versionInformationObjects = inVersionInformationCollection.getVersionInformationObjects();
        HashSet<String> updatePackageIdentifiers = bundle.getUpdatePackageIdentifiers();
        HashMap<DCMVersionInformation, DCMUpdateInformation> retVal = new HashMap<>();
        Collection<DCMUpdatePackageInformation> dependentUPI = new HashSet<>();
        Boolean dependenciesAdded = false;

        for (String updatePackageIdentifier : updatePackageIdentifiers) {
            dependenciesAdded = false;
            DCMUpdatePackageInformation updatePackageInformation = mComponentCollection.getUpdatePackageInformation(updatePackageIdentifier);
            if (updatePackageInformation == null) {
                continue;
            }

            // Adding hard Dependencies to each updateInformation object and then add the corresponding component (versionInformation) to the resultant map.
            Collection<DCMDependency> hardDependencies = updatePackageInformation.getDependencies();
            DCMUpdatePackageInformation dependentUpdatePackageInformation = null;
            if (null != hardDependencies && !hardDependencies.isEmpty()) {
                for (DCMDependency dependency : hardDependencies) {
                    DCMGUID prerequisiteID = dependency.getPrerequisite();
                    if (null != prerequisiteID) {
                        dependentUpdatePackageInformation = this.getUpdatePackageWithGivenIdentifier(prerequisiteID.getID());
                        HashMap<DCMVersionInformation, DCMUpdateInformation> tempUpdateInfoMap = addDependentUpdateInformation(updatePackageInformation, dependentUpdatePackageInformation, versionInformationObjects, inSystemTypeIdentifier, inOSIdentifier, inConsideration, DCMConstants.DEPENDENCY);
                        if (null != tempUpdateInfoMap && !tempUpdateInfoMap.isEmpty()) {
                            retVal.putAll(tempUpdateInfoMap);
                            dependenciesAdded = true;
                        }
                    }
                }
            }

            // Adding softDependencies to each updateInformation Object and then add the corresponding component (versionInformation) to the resultant map.
            Collection<DCMSoftDependency> softDependencies = updatePackageInformation.getSoftDependencies();
            dependentUpdatePackageInformation = null;
            if (null != softDependencies && !softDependencies.isEmpty()) {
                for (DCMSoftDependency softDependency : softDependencies) {
                    DCMGUID prerequisiteID = softDependency.getPrerequisite();
                    if (null != prerequisiteID) {
                        dependentUpdatePackageInformation = this.getUpdatePackageWithGivenIdentifier(prerequisiteID.getID());
                        HashMap<DCMVersionInformation, DCMUpdateInformation> tempUpdateInfoMap = addDependentUpdateInformation(updatePackageInformation, dependentUpdatePackageInformation, versionInformationObjects, inSystemTypeIdentifier, inOSIdentifier, inConsideration, DCMConstants.SOFT_DEPENDENCY);
                        if (null != tempUpdateInfoMap && !tempUpdateInfoMap.isEmpty()) {
                            retVal.putAll(tempUpdateInfoMap);
                            dependenciesAdded = true;
                        }
                    }
                }
            }

            // If the Component(versionInformation) does'not have dependencies then add the component and the corresponding updateInformation in the resultant map.
            if (false == dependenciesAdded) {
                for (DCMVersionInformation versionInfo : versionInformationObjects) {
                    DCMUpdateInformation updateInformation = updatePackageInformation.getUpdateInformation(versionInformationObjects, versionInfo, inSystemTypeIdentifier, inOSIdentifier, inConsideration);
                    if (updateInformation != null) {
                        //retrieve the target from the target map of update package
                        DCMBaseTarget target = mComponentCollection.getUpdatePackageInformation(updateInformation.getUniqueIdentifier()).
                                                        getTarget(versionInfo.getTargetIdentifier());
                        if (target != null && !target.getName().isEmpty()) {
                            updateInformation.setName(target.getName());
                        }
                        retVal.put(new DCMVersionInformation(versionInfo), updateInformation);
                    }
                }
            }
        }
        return retVal;
    }

    /**
     * Method for getting the update package information with the given
     * identifier
     *
     * @param inIdentifier specifies the update package identifier
     * @return the update package information
     */
    public DCMUpdatePackageInformation getUpdatePackageWithGivenIdentifier(String inIdentifier) {
        if (inIdentifier == null || inIdentifier.isEmpty()) {
            return null;
        }
        DCMUpdatePackageInformation retVal = mComponentCollection.getUpdatePackageInformation(inIdentifier);
        if (retVal == null) {
            retVal = mPrerequisites.getUpdatePackageInformation(inIdentifier);
        }
        return retVal;
    }

    private DCMI18NString mReleaseNotes;
    private String mBaseLocation;
    private Date mCreationDate;
    private DCMGUID mIdentifier;
    private DCMGUID mPredecessorIdentifier;
    private String mReleaseID;
    private String mVersion;
    private final DCMLineOfBusinessCollection mLOBCollection;
    private final DCMSystemCollection mSystemCollection;
    private final DCMOperatingSystemCollection mOSCollection;
    private final DCMComponentKindCollection mComponentKindCollection;
    //private final DCMTargetCollection mTargetCollection;
    private final DCMUpdateableComponentCollection mUpdateableComponentCollection;
    private final DCMCategoryCollection mCategoryCollection;
    private final DCMBundleCollection mBundleCollection;
    private final DCMUpdatePackageInformationCollection mComponentCollection;
    private final DCMUpdatePackageInformationCollection mPrerequisites;
    private final DCMInventoryCollectorInformationCollection mInventoryCollectorInformationCollection;
}
