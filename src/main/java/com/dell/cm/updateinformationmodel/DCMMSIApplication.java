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
 * Class for representing the application that is to be identified by MSI update
 * code
 *
 * @author Raveendra_Madala
 */
public class DCMMSIApplication extends DCMBaseTarget {

    /**
     * Constructor
     *
     * @param inID specifies the
     */
    public DCMMSIApplication(DCMGUID inID) {
        mMSIUpdateCode = new DCMGUID(inID);
        mType = DCMTargetEntity.MSI;
        mComponentID = DCMConstants.INVALID_COMPONENT_ID;
    }

    /**
     * Constructor
     *
     * @param in specifies the copy object
     */
    public DCMMSIApplication(DCMMSIApplication in) {
        super(in);
        mMSIUpdateCode = new DCMGUID(in.mMSIUpdateCode);
        mType = in.mType;
        mComponentID = in.mComponentID;
    }

    /**
     *
     * @return newly created object
     */
    @Override
    public DCMBaseTarget Copy() {
        return new DCMMSIApplication(this);
    }

    /**
     * Method for getting the update code
     *
     * @return the MSI update code
     */
    public DCMGUID getUpdateCode() {
        return mMSIUpdateCode;
    }

    /**
     * Method for getting the component id
     *
     * @return the component id
     */
    public long getComponentID() {
        return mComponentID;
    }

    /**
     * Method for setting the component id
     *
     * @param inID specifies the component id
     * @return SUCCESS if the component id could be set else appropriate error
     * code is returned
     */
    public int setComponentID(long inID) {
        mComponentID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the unique identifier
     *
     * @return the unique identifier
     */
    @Override
    public String getUniqueIdentifier() {
        String retVal = new String();
        retVal += mMSIUpdateCode.getID();
        return retVal;
    }
    private final DCMGUID mMSIUpdateCode;
    private long mComponentID;
}
