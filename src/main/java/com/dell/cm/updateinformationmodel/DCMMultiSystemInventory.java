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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class for representing multi system inventory
 *
 * @author Raveendra_Madala
 */
public class DCMMultiSystemInventory {

    /**
     * Constructor
     */
    public DCMMultiSystemInventory() {
        mLOBCollection = new DCMLineOfBusinessCollection();
        mSystemCollection = new DCMSystemCollection();
        mSystemInstanceCollection = new DCMSystemInstanceCollection();
        mOSCollection = new DCMOperatingSystemCollection();
        mComponentKindCollection = new DCMComponentKindCollection();
        mTargetCollection = new HashMap<>();
        mUpdateableComponentCollection = new DCMUpdateableComponentCollection();
        mVersionInformationCollectionOfCollection = new DCMVersionInformationCollectionOfCollection();
    }

    /**
     * Method for getting the line of business collection
     *
     * @return the line of business collection
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
     * Method for getting the system instance collection
     *
     * @return the system instance collection
     */
    public DCMSystemInstanceCollection getSystemInstanceCollection() {
        return mSystemInstanceCollection;
    }

    /**
     * Method for getting the operating system collection
     *
     * @return the operating system collection;
     */
    public DCMOperatingSystemCollection getOSCollection() {
        return mOSCollection;
    }

    /**
     * Method for getting the component kind collection
     *
     * @return the component kind collection;
     */
    public DCMComponentKindCollection getComponentKindCollection() {
        return mComponentKindCollection;
    }

    /**
     * Method for getting the target collection
     *
     * @return the target collection;
     */
    public HashMap<String, DCMBaseTarget> getTargetCollection() {
        return mTargetCollection;
    }

    /**
     * Method for getting the updateable component collection
     *
     * @return the updateable component collection;
     */
    public DCMUpdateableComponentCollection getUpdateableComponentCollection() {
        return mUpdateableComponentCollection;
    }

    /**
     * Method for getting the collection of version information collection
     * objects
     *
     * @return the collection of version information collections;
     */
    public DCMVersionInformationCollectionOfCollection getCollectionOfVersionInformationCollections() {
        return mVersionInformationCollectionOfCollection;
    }

    /**
     * Method for getting all system instance identifiers in the given group
     *
     * @param inIdentifier specifies the group/system identifier for which the
     * system instances are being sought
     * @return the system instance identifier set
     */
    public Collection<String> getSystemInstanceIdentifiers(String inIdentifier) {
        if (inIdentifier == null || inIdentifier.isEmpty()) {
            return null;
        }
        if (mSystemInstanceCollection.hasSystemInstance(inIdentifier)) {
            Collection<String> retVal = new ArrayList<String>();
            retVal.add(inIdentifier);
            return retVal;
        }
        HashSet<String> systemIdentifierSet = new HashSet<String>();
        systemIdentifierSet.addAll(mVersionInformationCollectionOfCollection.getGroupSystemInstanceIdentifiers(inIdentifier));
        return systemIdentifierSet;
    }

    /**
     * Method for getting the system identifiers for the specified the group or
     * system identifier
     *
     * @param inIdentifier specifies the group or system identifier
     * @return the system identifiers in the group specified or the system
     * identifier specified
     */
    public Collection<String> getSystemIdentifiers(String inIdentifier) {
        HashSet<String> systemIdentifierSet = new HashSet<String>();
        for (String instanceIdentifier : getSystemInstanceIdentifiers(inIdentifier)) {
            DCMSystemInstance systemInstance = mSystemInstanceCollection.getSystemInstance(instanceIdentifier);
            if (systemInstance != null) {
                systemIdentifierSet.add(systemInstance.getSystemTypeIdentifier());
            }
        }
        return systemIdentifierSet;
    }

    /**
     * Method for getting the system identifier for the system instance
     * identifier
     *
     * @param inSystemInstanceIdentifier specifies the system instance
     * identifier
     * @return the system type identifier for this system instance
     */
    public String getSystemIdentifier(String inSystemInstanceIdentifier) {
        if (inSystemInstanceIdentifier == null || inSystemInstanceIdentifier.isEmpty()) {
            return null;
        }
        DCMSystemInstance systemInstance = mSystemInstanceCollection.getSystemInstance(inSystemInstanceIdentifier);
        if (systemInstance != null) {
            return systemInstance.getSystemTypeIdentifier();
        }
        return null;
    }

    /**
     * Method for getting the version information collection for the
     *
     * @param inSystemInstanceIdentifier specifies the system identifier for the version information
     * @return DSM version Information Collection object
     */
    public DCMVersionInformationCollection getVersionInformationCollection(String inSystemInstanceIdentifier) {
        if (inSystemInstanceIdentifier == null || inSystemInstanceIdentifier.isEmpty()) {
            LOG.log(Level.INFO,"inSystemInstanceIdentifier is null");
            return null;
        }
        return mVersionInformationCollectionOfCollection.getVersionInformationCollection(inSystemInstanceIdentifier);
    }

    /**
     * Method for getting the host OS identifier for the given version
     * information collection
     *
     * @param inVersionInformationCollection specifies the version information
     * collection
     * @return the OS identifier
     */
    public String getOSIdentifier(DCMVersionInformationCollection inVersionInformationCollection) {
        if (inVersionInformationCollection == null) {
            return null;
        }
        Collection<String> systemInstanceIdentifiers = inVersionInformationCollection.getSystemInstanceIdentifiers();
        if (systemInstanceIdentifiers == null || systemInstanceIdentifiers.isEmpty()) {
            return null;
        }
        String systemInstanceIdentifier = systemInstanceIdentifiers.iterator().next();
        DCMSystemInstance systemInstance = mSystemInstanceCollection.getSystemInstance(systemInstanceIdentifier);
        if (systemInstance == null) {
            return null;
        }
        return systemInstance.getHostOperatingSystemIdentifier();
    }
    
    /**
     * adds the given target to mTargetMap
     * @param inTarget
     * @return 
     */
     public int addTarget(DCMBaseTarget inTarget) {
        if (null == inTarget) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mTargetCollection.containsKey(inTarget.getUniqueIdentifier())) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mTargetCollection.put(inTarget.getUniqueIdentifier(), inTarget);
        return DCMErrorCodes.SUCCESS;
    }


    private final DCMLineOfBusinessCollection mLOBCollection;
    private final DCMSystemCollection mSystemCollection;
    private final DCMSystemInstanceCollection mSystemInstanceCollection;
    private final DCMOperatingSystemCollection mOSCollection;
    private final DCMComponentKindCollection mComponentKindCollection;
    //private final DCMTargetCollection mTargetCollection;
    private final DCMUpdateableComponentCollection mUpdateableComponentCollection;
    private final DCMVersionInformationCollectionOfCollection mVersionInformationCollectionOfCollection;
    private final HashMap<String, DCMBaseTarget> mTargetCollection;
    private Logger LOG = Logger.getLogger(DCMMultiSystemInventory.class.getName());
}
