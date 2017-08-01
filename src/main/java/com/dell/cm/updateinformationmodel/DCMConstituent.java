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
 * class for representing the constituent node
 *
 * @author Raveendra_Madala
 */
public class DCMConstituent {

    /**
     * Default constructor
     */
    public DCMConstituent() {
        mTargetIdentifierSet = new HashSet<>();
        mOperatingSystemIdentifierSet = new HashSet<>();
        mDependencies = new HashMap<>();
        mSoftDependencies = new HashMap<>();
        mDependencyMap = new HashMap<>();
        mSoftDependencyMap = new HashMap<>();
    }

    /**
     * Copy Constructor
     *
     * @param inConstituent specifies the constituent from which this object is
     * to be constructed
     */
    public DCMConstituent(DCMConstituent inConstituent) {
        mTargetIdentifierSet = new HashSet<>();
        mOperatingSystemIdentifierSet = new HashSet<>();
        mDependencies = new HashMap<>();
        mSoftDependencies = new HashMap<>();
        mDependencyMap = new HashMap<>();
        mSoftDependencyMap = new HashMap<>();
        if (inConstituent != null) {
            mComponentType = inConstituent.mComponentType;
            mTargetIdentifierSet.addAll(inConstituent.mTargetIdentifierSet);
            mVersion = inConstituent.mVersion;
            mPath = inConstituent.mPath;
            for (Integer index : inConstituent.mDependencies.keySet()) {
                mDependencies.put(index, new DCMDependency(inConstituent.mDependencies.get(index)));
            }
            for (Integer index : inConstituent.mSoftDependencies.keySet()) {
                mSoftDependencies.put(index, new DCMSoftDependency(inConstituent.mSoftDependencies.get(index)));
            }
            for (String reference : inConstituent.mDependencyMap.keySet()) {
                HashSet<Integer> value = new HashSet<>();
                value.addAll(inConstituent.mDependencyMap.get(reference));
                mDependencyMap.put(reference, value);
            }
            for (String reference : inConstituent.mSoftDependencyMap.keySet()) {
                HashSet<Integer> value = new HashSet<>();
                value.addAll(inConstituent.mSoftDependencyMap.get(reference));
                mSoftDependencyMap.put(reference, value);
            }
        }
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
     * @param inType specifies the type being set
     * @return SUCCESS if the component type could be set else appropriate error
     * code is set
     */
    public int setComponentType(DCMComponentType inType) {
        mComponentType = inType;
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
     * is returned.
     */
    public int setVersion(String inVersion) {
        mVersion = inVersion;
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
     * Method for determining whether the constituent supports the updateable
     * component or not
     *
     * @param inVersionInfo specifies the version information being compared
     * @param inSystemTypeIdentifier specifies the system type identifier
     * @param inOSIdentifier specifies the OS identifier
     * @return true if the constituent supports the updateable component in the
     * given context else false is returned
     */
    public boolean supports(DCMVersionInformation inVersionInfo, String inSystemTypeIdentifier, String inOSIdentifier) {
        if (inVersionInfo == null || inSystemTypeIdentifier == null || !inSystemTypeIdentifier.isEmpty() || inOSIdentifier == null || !inOSIdentifier.isEmpty()) {
            return false;
        }
        if (!mTargetIdentifierSet.isEmpty() && !mTargetIdentifierSet.contains(inVersionInfo.getTargetIdentifier())) {
            return false;
        }
        if (!mOperatingSystemIdentifierSet.isEmpty() && !mOperatingSystemIdentifierSet.contains(inOSIdentifier)) {
            return false;
        }
        DCMUpdateableComponent tempComponent = new DCMUpdateableComponent(mComponentType, inVersionInfo.getTargetIdentifier());
        String updateableComponentIdentifier = tempComponent.getUniqueIdentifier();
        return inVersionInfo.getUpdateableComponentIdentifier().equals(updateableComponentIdentifier);
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
        if (inTargetIdentifier == null || inTargetIdentifier.isEmpty()) {
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
        if (inDependency == null || inTargetIdentifier == null || inTargetIdentifier.isEmpty()) {
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
        if (inDependency == null || inTargetIdentifier == null || inTargetIdentifier.isEmpty()) {
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
     * @param inTargetIdentifier target identifier for the soft dependencies
     * @return soft dependencies
     */
    public Collection<DCMSoftDependency> getSoftDependenciesForTheGivenTarget(String inTargetIdentifier) {
        if (inTargetIdentifier == null || inTargetIdentifier.isEmpty()) {
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

    private DCMComponentType mComponentType;
    private final HashSet<String> mTargetIdentifierSet;
    private final HashSet<String> mOperatingSystemIdentifierSet;
    private String mVersion;
    private String mPath;
    private final HashMap<Integer, DCMDependency> mDependencies;
    private final HashMap<Integer, DCMSoftDependency> mSoftDependencies;
    private final HashMap<String, HashSet<Integer>> mDependencyMap;
    private final HashMap<String, HashSet<Integer>> mSoftDependencyMap;
}
