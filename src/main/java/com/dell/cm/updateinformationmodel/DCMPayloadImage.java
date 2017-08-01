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
 * class for representing the payload image
 *
 * @author Raveendra_Madala
 */
public class DCMPayloadImage {

    /**
     * Constructor
     */
    public DCMPayloadImage() {
    }

    /**
     * Copy Constructor
     *
     * @param inImage specifies the image from which this object is to be
     * constructed
     */
    public DCMPayloadImage(DCMPayloadImage inImage) {
        if (inImage != null) {
            mType = inImage.mType;
            if (inImage.mID != null) {
                mID = new DCMGUID(inImage.mID);
            }
            mVersion = inImage.mVersion;
            mFileName = inImage.mFileName;
            mSkip = inImage.mSkip;
        }
    }

    /**
     * Method for getting the type
     *
     * @return the type
     */
    public String getType() {
        return mType;
    }

    /**
     * Method for setting the type
     *
     * @param inType specifies the type to be set
     * @return SUCCESS if the type could be set else appropriate error code is
     * returned
     */
    public int setType(String inType) {
        mType = inType;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the id
     *
     * @return the id
     */
    public DCMGUID getID() {
        return mID;
    }

    /**
     * Method for setting the id
     *
     * @param inID specifies the id to be set
     * @return SUCCESS if the id could be set else appropriate error code is
     * returned
     */
    public int setID(DCMGUID inID) {
        mID = new DCMGUID(inID);
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
     * Method for getting the File Name
     *
     * @return the File Name
     */
    public String getFileName() {
        return mFileName;
    }

    /**
     * Method for setting the File Name
     *
     * @param inName specifies the file name to be set
     * @return SUCCESS if the file name could be set else appropriate error code
     * is returned
     */
    public int setFileName(String inName) {
        mFileName = inName;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for knowing whether to skip or not
     *
     * @return true if to skip else false is returned
     */
    public boolean ShouldSkip() {
        return mSkip;
    }

    /**
     * Method for setting the skip value
     *
     * @param inValue specifies the value to be set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned
     */
    public int setShouldSkip(boolean inValue) {
        mSkip = inValue;
        return DCMErrorCodes.SUCCESS;
    }

    private String mType;
    private DCMGUID mID;
    private String mVersion;
    private String mFileName;
    private boolean mSkip;
}
