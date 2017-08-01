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

/**
 * class for representing the inventory collector information
 *
 * @author Raveendra_Madala
 */
public class DCMInventoryCollectorInformation {

    /**
     * Method for getting the schema version
     *
     * @return the schema version
     */
    public String getSchemaVersion() {
        return mSchemaVersion;
    }

    /**
     * Method for getting the schema version
     *
     * @param inVersion specifies the schema version
     * @return SUCCESS if the schema version could be set else appropriate error
     * code is returned
     */
    public int setSchemaVersion(String inVersion) {
        mSchemaVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the release ID
     *
     * @return the release DI
     */
    public String getReleaseID() {
        return mReleaseID;
    }

    /**
     * Method for setting the release ID
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
     * @param inVersion specifies the version being set
     * @return SUCCESS if the version could be set else appropriate error code
     * is returned.
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
     * @param inVersion specifies the dell version to be set
     * @return SUCCESS if the version could be set else appropriate error code
     * is returned.
     */
    public int setDellVersion(String inVersion) {
        mDellVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the OS Code
     *
     * @return the OS Code
     */
    public String getOSCode() {
        return mOSCode;
    }

    /**
     * Method for setting the OS code
     *
     * @param inOSCode specifies the OS code to be set
     * @return SUCCESS if the code could be set else appropriate error code is
     * returned
     */
    public int setOSCode(String inOSCode) {
        mOSCode = inOSCode;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the hash md5 of inventory collector
     *
     * @return the hash md5 of inventory collector
     */
    public String getHashMD5() {
        return mHashMD5;
    }

    /**
     * Method for setting the hash md5 of inventory collector
     *
     * @param inHash specifies the hash to be set
     * @return SUCCESS if the hash could be set else appropriate error code is
     * returned
     */
    public int setHashMD5(String inHash) {
        mHashMD5 = inHash;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the path
     *
     * @return the pat
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
     * Method for getting the release date
     *
     * @return the release date
     */
    public Date getReleaseDate() {
        return mReleaseDate;
    }

    /**
     * Method for setting the release date
     *
     * @param inDate specifies the date to be set
     * @return SUCCESS if the date could be set else appropriate error code is
     * returned.
     */
    public int setReleaseDate(Date inDate) {
        mReleaseDate = inDate;
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
     * returned
     */
    public int setSize(long inSize) {
        mSize = inSize;
        return DCMErrorCodes.SUCCESS;
    }

    private String mSchemaVersion;
    private String mReleaseID;
    private Date mReleaseDate;
    private String mVendorVersion;
    private String mDellVersion;
    private String mOSCode;
    private String mHashMD5;
    private String mPath;
    private Date mDate;
    private long mSize;
}
