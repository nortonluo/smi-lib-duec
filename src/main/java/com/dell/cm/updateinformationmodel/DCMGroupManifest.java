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

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Ujjwal_Kumar_Gupta
 */
public class DCMGroupManifest {

    /**
     * Method for getting Group Manifest ID
     *
     * @return Group Manifest ID
     */
    public String getId() {
        return mId;
    }

    /**
     * Method for setting Group Manifest ID attribute
     *
     * @param mId Group Manifest ID attribute
     * @return SUCCESS if the id could be set else appropriate error code is
     * returned
     */
    public int setId(String mId) {
        this.mId = mId;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting Group Manifest Name
     *
     * @return Group Manifest Name
     */
    public String getName() {
        return mName;
    }

    /**
     * Method for setting Group Manifest Name attribute
     *
     * @param mName Group Manifest Name attribute
     * @return SUCCESS if the name could be set else appropriate error code is
     * returned
     */
    public int setName(String mName) {
        this.mName = mName;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting Group Manifest Type attribute
     *
     * @return Group Manifest Type attribute
     */
    public String getType() {
        return mType;
    }

    /**
     * Method for setting Group Manifest Type attribute
     *
     * @param mType Group Manifest Type attribute
     * @return SUCCESS if the type could be set else appropriate error code is
     * returned
     */
    public int setType(String mType) {
        this.mType = mType;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting Group Manifest CreationDateTime attribute
     *
     * @return Group Manifest creationDateTime attribute
     */
    public String getCreationDateTime() {
        return mCreationDateTime;
    }

    /**
     * Method for setting Group Manifest creationDateTime attribute
     *
     * @param mCreationDateTime Group Manifest creationDateTime attribute
     * @return SUCCESS if the creationDateTime could be set else appropriate
     * error code is returned
     */
    public int setCreationDateTime(String mCreationDateTime) {
        this.mCreationDateTime = mCreationDateTime;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting Group Manifest latest attribute
     *
     * @return Group Manifest latest attribute
     */
    public String getLatest() {
        return mLatest;
    }

    /**
     * Method for setting Group Manifest latest attribute
     *
     * @param mLatest Group Manifest latest attribute
     * @return SUCCESS if the Manifest latest could be set else appropriate
     * error code is returned
     */
    public int setLatest(String mLatest) {
        this.mLatest = mLatest;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting Display, Group Manifest child node
     *
     * @return Display, Group Manifest child node
     */
    public DCMI18NString getDisplayName() {
        return mDisplayName;
    }

    /**
     * Method for setting Display, Group Manifest child node
     *
     * @param mDisplayName: Display value, Group Manifest child node
     * @return SUCCESS if the creationDateTime could be set else appropriate
     * error code is returned
     */
    public int setDisplayName(DCMI18NString mDisplayName) {
        this.mDisplayName = mDisplayName;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting ManifestInformationCollection
     *
     * @return DCMManifestCollection
     */
    public DCMManifestInformationCollection getManifestInformationCollection() {
        return mManifestInformationCollection;
    }

    /**
     * Method for setting DCMManifestInformationCollection
     *
     * @param mManifestInformationCollection input ManifestInformationCollection
     * to be set
     * @return SUCCESS if the manifestInformationCollection could be set else
     * appropriate error code is returned
     */
    public int setManifestInformationCollection(DCMManifestInformationCollection mManifestInformationCollection) {
        this.mManifestInformationCollection = mManifestInformationCollection;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting LOB Set which is HashMap of LOB key and HashSet of
     * systemIDs
     *
     * @return LOBSet
     */
    public HashMap<Integer, HashSet<String>> getLOBSet() {
        return mLOBSet;
    }

    /**
     * Method for setting LOB Set which is HashMap of LOB key and HashSet of
     * systemIDs
     *
     * @param lobSet input LOBSet to be set
     * @return SUCCESS if the LOBSet could be set else appropriate error code is
     * returned
     */
    public int setLOBSet(HashMap<Integer, HashSet<String>> lobSet) {
        this.mLOBSet = lobSet;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting SystemCollection
     *
     * @return DCMSystemCollection
     */
    public DCMSystemCollection getSystemCollection() {
        return mSystemCollection;
    }

    /**
     * Method for setting SystemCollection
     *
     * @param mSystemCollection Input DCMSystemCollecction to be set
     * @return SUCCESS if the SystemCollection could be set else appropriate
     * error code is returned
     */
    public int setSystemCollection(DCMSystemCollection mSystemCollection) {
        this.mSystemCollection = mSystemCollection;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting LOBCollection
     *
     * @return DCMLineOfBusinessCollection
     */
    public DCMLineOfBusinessCollection getLOBcollection() {
        return mLOBcollection;
    }

    /**
     * Method for setting LOBCollection
     *
     * @param mLOBcollection Input LOBCollecction to be set
     * @return SUCCESS if the LOBCollecction could be set else appropriate error
     * code is returned
     */
    public int setLOBcollection(DCMLineOfBusinessCollection mLOBcollection) {
        this.mLOBcollection = mLOBcollection;
        return DCMErrorCodes.SUCCESS;
    }

    private String mId;
    private String mName;
    private String mType;
    private String mCreationDateTime;
    private String mLatest;
    private DCMI18NString mDisplayName;
    // Next 3 are used For retrieving Brand Information
    private HashMap<Integer, HashSet<String>> mLOBSet;
    private DCMSystemCollection mSystemCollection;
    private DCMLineOfBusinessCollection mLOBcollection;

    private DCMManifestInformationCollection mManifestInformationCollection;

}
