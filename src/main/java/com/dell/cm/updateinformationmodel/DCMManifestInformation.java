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
public class DCMManifestInformation {

    /**
     * Method for getting id attribute for ManifestInformation
     *
     * @return id attribute as String
     */
    public String getId() {
        return mId;
    }

    /**
     * Method for setting id attribute for ManifestInformation
     *
     * @param mId id attribute to be set
     * @return SUCCESS if the id could be set else appropriate error code is
     * returned
     */
    public int setId(String mId) {
        this.mId = mId;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting releaseID
     *
     * @return releaseID
     */
    public String getReleaseID() {
        return mReleaseID;
    }

    /**
     * Method for setting releaseID attribute
     *
     * @param mReleaseID releaseID to be set
     * @return SUCCESS if the releaseID could be set else appropriate error code
     * is returned
     */
    public int setReleaseID(String mReleaseID) {
        this.mReleaseID = mReleaseID;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting Path attribute
     *
     * @return Path as string
     */
    public String getPath() {
        return mPath;
    }

    /**
     * Method for setting path attribute
     *
     * @param mPath path to be set
     * @return SUCCESS if the path could be set else appropriate error code is
     * returned
     */
    public int setPath(String mPath) {
        this.mPath = mPath;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting creationDateatime
     *
     * @return creationDateTime as String
     */
    public String getCreationDateTime() {
        return mCreationDateTime;
    }

    /**
     * Method for setting creationDateTIme
     *
     * @param mCreationDateTime creationDateTime to be set
     * @return SUCCESS if the creationDateTime could be set else appropriate
     * error code is returned
     */
    public int setCreationDateTime(String mCreationDateTime) {
        this.mCreationDateTime = mCreationDateTime;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting version
     *
     * @return version
     */
    public String getVersion() {
        return mVersion;
    }

    /**
     * Method for setting version
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
     * Method for getting HashMD5
     *
     * @return HashMD5
     */
    public String getHashMD5() {
        return mHashMD5;
    }

    /**
     * Method for setting HashMD5
     *
     * @param mHashMD5 hashMD5 to be set
     * @return SUCCESS if the HashMD5 could be set else appropriate error code
     * is returned
     */
    public int setHashMD5(String mHashMD5) {
        this.mHashMD5 = mHashMD5;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting DisplayName
     *
     * @return DisplayName as DCM18NString
     */
    public DCMI18NString getDisplayName() {
        return mDisplayName;
    }

    /**
     * Method for setting display Name
     *
     * @param mDisplayName display name to be set
     * @return SUCCESS if the displayName could be set else appropriate error
     * code is returned
     */
    public int setDisplayName(DCMI18NString mDisplayName) {
        this.mDisplayName = mDisplayName;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting SignPath
     *
     * @return SignPath as String
     */
    public String getSignPath() {
        return mSignPath;
    }

    /**
     * Method for setting SignPath
     *
     * @param mSignPath signPath to be set
     * @return SUCCESS if the signPath could be set else appropriate error code
     * is returned
     */
    public int setSignPath(String mSignPath) {
        this.mSignPath = mSignPath;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for setting SignPath as null string
     *
     * @return SUCCESS if the signPath could be set else appropriate error code
     * is returned
     */
    public int setSignPath() {
        this.mSignPath = null;
        return DCMErrorCodes.SUCCESS;
    }
    
    /**
     *Method to set the name
     * @param name
     * @return
     */
    public int setName(String name){
        this.name = name;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     *Method to get Name
     * @return
     */
    public String getName(){
        return name;
    }

    private String mId;
    private String mReleaseID;
    private String mPath;
    private String mCreationDateTime;
    private String mVersion;
    private String mHashMD5;
    private String mSignPath;
    private DCMI18NString mDisplayName;
    private String name;

}
