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
 *
 * @author Ujjwal_Kumar_Gupta
 */
public class DCMManifestIndex {

    /**
     * Method for getting BaseLocation attribute
     *
     * @return Baselocation as string
     */
    public String getBaseLocation() {
        return mBaseLocation;
    }

    /**
     * Method for setting Baselocation attribute
     *
     * @param mBaseLocation baselocation to be set
     * @return SUCCESS if the baselocation could be set else appropriate error
     * code is returned
     */
    public int setBaseLocation(String mBaseLocation) {
        this.mBaseLocation = mBaseLocation;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting creationDateTime
     *
     * @return creationDateTime as string
     */
    public String getCeationDateTime() {
        return mCreationDateTime;
    }

    /**
     * Method for setting creationDateTime as ManifestIndex Attribute
     *
     * @param mCreationDateTime creationDateTime attribute to be set
     * @return SUCCESS if the creationDateTime could be set else appropriate
     * error code is returned
     */
    public int setCeationDateTime(String mCreationDateTime) {
        this.mCreationDateTime = mCreationDateTime;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting version
     *
     * @return version as string
     */
    public String getVersion() {
        return mVersion;
    }

    /**
     * Method for setting version attribute
     *
     * @param mVersion version to be set
     * @return SUCCESS if the version could be set else appropriate error code
     * is returned
     */
    public int setVersion(String mVersion) {
        this.mVersion = mVersion;
        return DCMErrorCodes.SUCCESS;
    }
    
    /**
     * Method for setting id attribute
     * @param mId
     * @return
     */
    public int setId(String mId){
        this.mId=mId;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     *Method to get id
     * @return
     */
    public String getId(){
        return mId;
    }

    /**
     *Method to set predecessor id
     * @param id
     * @return
     */
    public int setPredecessorId(String id){
        this.mPredecessorId = id;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     *Method to get predecessor id
     * @return
     */
    public String getPredecessorId(){
        return mPredecessorId;
    }

    /**
     * Method for getting Group Manifest Collection
     *
     * @return DCMGroupManifestCollection
     */
    public DCMGroupManifestCollection getGroupManifestCollection() {
        return mGroupManifestCollection;
    }

    /**
     * Method for setting GroupManifestCollection
     *
     * @param mGroupManifestCollection GRoupManifestInformation to be set
     * @return SUCCESS if the GroupManifestColletion could be set else
     * appropriate error code is returned
     */
    public int setGroupManifestCollection(DCMGroupManifestCollection mGroupManifestCollection) {
        this.mGroupManifestCollection = mGroupManifestCollection;
        return DCMErrorCodes.SUCCESS;
    }

    private String mBaseLocation;
    private String mCreationDateTime;
    private String mVersion;
    private String mId;
    private String mPredecessorId;
    private DCMGroupManifestCollection mGroupManifestCollection;

}
