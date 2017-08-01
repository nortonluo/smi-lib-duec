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
 * class for representing the rollback information
 *
 * @author Raveendra_Madala
 */
public class DCMRollbackInformation {

    /**
     * Constructor
     */
    public DCMRollbackInformation() {
    }

    /**
     * Copy constructor
     *
     * @param inInformation specifies the object from which this object is to be
     * constructed
     */
    public DCMRollbackInformation(DCMRollbackInformation inInformation) {
        if (inInformation != null) {
            if (inInformation.mRollbackIdentifier != null) {
                mRollbackIdentifier = new DCMGUID(inInformation.mRollbackIdentifier);
            }
            mRollbackVolume = inInformation.mRollbackVolume;
            if (inInformation.mFMPWrapperIdentifier != null) {
                mFMPWrapperIdentifier = new DCMGUID(inInformation.mFMPWrapperIdentifier);
            }
            mFMPWrapperVersion = inInformation.mFMPWrapperVersion;
            if (inInformation.mFMPIdentifier != null) {
                mFMPIdentifier = new DCMGUID(inInformation.mFMPIdentifier);
            }
            mRollbackTimeout = inInformation.mRollbackTimeout;
            mImpactsTPMmeasurements = inInformation.mImpactsTPMmeasurements;
            mFieldService = inInformation.mFieldService;
        }
    }

    /**
     * Method for getting the rollback identifier
     *
     * @return the rollback identifier
     */
    public DCMGUID getRollbackIdentifier() {
        return mRollbackIdentifier;
    }

    /**
     * Method for setting the rollback identifier
     *
     * @param inIdentifier specifies the identifier to be set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned
     */
    public int setRollbackIdentifier(DCMGUID inIdentifier) {
        mRollbackIdentifier = new DCMGUID(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the rollback volume
     *
     * @return the rollback volume
     */
    public String getRollbackVolume() {
        return mRollbackVolume;
    }

    /**
     * Method for setting the rollback volume
     *
     * @param inVolume specifies the volume to be set
     * @return SUCCESS if the volume could be set else appropriate error code is
     * returned
     */
    public int setRollbackVolume(String inVolume) {
        mRollbackVolume = inVolume;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for FMP Wrapper Identifier
     *
     * @return the FMP WRapper Identifier
     */
    public DCMGUID getFMPWrapperIdentifier() {
        return mFMPWrapperIdentifier;
    }

    /**
     * Method for setting the FMP Wrapper Identifier
     *
     * @param inIdentifier specifies the identifier to be set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned
     */
    public int setFMPWrapperIdentifier(DCMGUID inIdentifier) {
        mFMPWrapperIdentifier = new DCMGUID(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the FMP Wrapper version
     *
     * @return the FMP Wrapper version
     */
    public String getFMPWrapperVersion() {
        return mFMPWrapperVersion;
    }

    /**
     * Method for setting the FMP Wrapper version
     *
     * @param inVersion specifies the version to be set
     * @return SUCCESS if the version could be set else appropriate error code
     * is returned
     */
    public int setFMPWrapperVersion(String inVersion) {
        mFMPWrapperVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the FMP Identifier
     *
     * @return the FMP Identifier
     */
    public DCMGUID getFMPIdentifier() {
        return mFMPIdentifier;
    }

    /**
     * Method for setting the FMP IDentifier
     *
     * @param inIdentifier specifies the identifier to be set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned
     */
    public int setFMPIdentifier(DCMGUID inIdentifier) {
        mFMPIdentifier = new DCMGUID(inIdentifier);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the rollback timeout
     *
     * @return the rollback timeout
     */
    public short getRollbackTimeout() {
        return mRollbackTimeout;
    }

    /**
     * Method for setting the rollback timeout
     *
     * @param inValue the value to be set
     * @return SUCCESS if the timeout could be set else appropriate error code
     * is returned
     */
    public int setRollbackTimeout(short inValue) {
        mRollbackTimeout = inValue;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for determining whether the TPM Measurements are impacted or not
     *
     * @return true if the measurements are impacted else false is returned
     */
    public boolean impactsTPMMeasurements() {
        return mImpactsTPMmeasurements;
    }

    /**
     * Method for setting the TPM measurements impact or not
     *
     * @param inValue specifies the value to be set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned
     */
    public int setImpactsTPMMeasurements(boolean inValue) {
        mImpactsTPMmeasurements = inValue;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the field service
     *
     * @return the field service
     */
    public String getFieldService() {
        return mFieldService;
    }

    /**
     * Method for setting the field service
     *
     * @param inFieldService specifies the value to be set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned
     */
    public int setFieldService(String inFieldService) {
        mFieldService = inFieldService;
        return DCMErrorCodes.SUCCESS;
    }

    private DCMGUID mRollbackIdentifier;
    private String mRollbackVolume;
    private DCMGUID mFMPWrapperIdentifier;
    private String mFMPWrapperVersion;
    private DCMGUID mFMPIdentifier;
    private short mRollbackTimeout;
    private boolean mImpactsTPMmeasurements;
    private String mFieldService;
}
