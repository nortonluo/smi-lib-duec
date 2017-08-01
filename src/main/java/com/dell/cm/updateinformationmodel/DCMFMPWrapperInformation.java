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
 * class for representing the FMP wrapper information
 *
 * @author Raveendra_Madala
 */
public class DCMFMPWrapperInformation {

    /**
     * Constructor
     */
    public DCMFMPWrapperInformation() {
    }

    /**
     * Copy Constructor
     *
     * @param inInformation specifies the object from which this object is to be
     * constructed
     */
    public DCMFMPWrapperInformation(DCMFMPWrapperInformation inInformation) {
        if (inInformation != null) {
            mInventorySupported = inInformation.mInventorySupported;
            mInventorySource = inInformation.mInventorySource;
            mUpdateSupported = inInformation.mUpdateSupported;
            mRollbackSupported = inInformation.mRollbackSupported;
            if (inInformation.mIdentifier != null) {
                mIdentifier = new DCMGUID(inInformation.mIdentifier);
            }
            mName = inInformation.mName;
            mFilePathName = inInformation.mFilePathName;
            mDriverFileName = inInformation.mDriverFileName;
            mDigitallySigned = inInformation.mDigitallySigned;
        }
    }

    /**
     * Method for determining whether the inventory is supported
     *
     * @return true if the inventory is supported else false is returned.
     */
    public boolean isInventorySupported() {
        return mInventorySupported;
    }

    /**
     * Method for setting the inventory support
     *
     * @param inValue specifies the value to be set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned.
     */
    public int setInventorySupport(boolean inValue) {
        mInventorySupported = inValue;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the inventory source
     *
     * @return inventory source
     */
    public String getInventorySource() {
        return mInventorySource;
    }

    /**
     * Method for setting the inventory source
     *
     * @param inSource specifies the source to be set
     * @return SUCCESS if the source could be set else appropriate error code is
     * returned.
     */
    public int setInventorySource(String inSource) {
        mInventorySource = inSource;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for determining whether the update is supported
     *
     * @return true if the update is supported else false is returned.
     */
    public boolean isUpdateSupported() {
        return mUpdateSupported;
    }

    /**
     * Method for setting the update support
     *
     * @param inValue specifies the value to be set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned.
     */
    public int setUpdateSupport(boolean inValue) {
        mUpdateSupported = inValue;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for determining whether the rollback is supported
     *
     * @return true if the rollback is supported else false is returned.
     */
    public boolean isRollbackupported() {
        return mRollbackSupported;
    }

    /**
     * Method for setting the rollback support
     *
     * @param inValue specifies the value to be set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned.
     */
    public int setRollbackSupport(boolean inValue) {
        mRollbackSupported = inValue;
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
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned.
     */
    public int setIdentifier(DCMGUID inIdentifier) {
        mIdentifier = new DCMGUID(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the name
     *
     * @return the name
     */
    public String getName() {
        return mName;
    }

    /**
     * Method for setting the name
     *
     * @param inName specifies the name to be set
     * @return SUCCESS if the name could be set else appropriate error code is
     * returned.
     */
    public int setName(String inName) {
        mName = inName;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the file path
     *
     * @return the file path
     */
    public String getFilePath() {
        return mFilePathName;
    }

    /**
     * Method for setting the file path
     *
     * @param inPath specifies the file path to be set
     * @return SUCCESS if the file path could be set else appropriate error code
     * is returned.
     */
    public int setFilePath(String inPath) {
        mFilePathName = inPath;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the driver file name
     *
     * @return the driver file name
     */
    public String getDriverFileName() {
        return mDriverFileName;
    }

    /**
     * Method for setting the driver file name
     *
     * @param inName specifies the name to be set
     * @return SUCCESS if the driver file name could be set else appropriate
     * error code is returned.
     */
    public int setDriverFileName(String inName) {
        mDriverFileName = inName;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for determining whether the wrapper is digitally signed or not
     *
     * @return true if the wrapper is digitally signed else false is returned.
     */
    public boolean isDigitallySigned() {
        return mDigitallySigned;
    }

    /**
     * Method for setting the digitally signed value for this wrapper
     *
     * @param inValue specifies the value to be set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned.
     */
    public int setDigitallySigned(boolean inValue) {
        mDigitallySigned = inValue;
        return DCMErrorCodes.SUCCESS;
    }

    private boolean mInventorySupported;
    private String mInventorySource;
    private boolean mUpdateSupported;
    private boolean mRollbackSupported;
    private DCMGUID mIdentifier;
    private String mName;
    private String mFilePathName;
    private String mDriverFileName;
    private boolean mDigitallySigned;
}
