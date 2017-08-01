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
 * class for representing the sub component
 *
 * @author Raveendra_Madala
 */
public class DCMSubComponent {

    /**
     * Constructor
     */
    public DCMSubComponent() {
    }

    /**
     * Copy constructor
     *
     * @param inComponent specifies the object from which this object is to be
     * constructed
     */
    public DCMSubComponent(DCMSubComponent inComponent) {
        if (inComponent != null) {
            if (inComponent.mName != null) {
                mName = new DCMI18NString(inComponent.mName);
            }
            mSubComponentID = inComponent.mSubComponentID;
            mVersion = inComponent.mVersion;
        }
    }

    /**
     * Method for getting the name
     *
     * @return the name
     */
    public DCMI18NString getName() {
        return mName;
    }

    /**
     * Method for setting the name
     *
     * @param inName specifies the name to be set
     * @return SUCCESS if the name could be set else appropriate error code is
     * returned
     */
    public int setName(DCMI18NString inName) {
        mName = new DCMI18NString(inName);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the sub component id
     *
     * @return the sub component id
     */
    public String getSubComponentID() {
        return mSubComponentID;
    }

    /**
     * Method for setting the sub component id
     *
     * @param inID specifies the id to be set
     * @return SUCCESS if the sub component id could be set else appropriate
     * error code is returned
     */
    public int setSubComponentID(String inID) {
        mSubComponentID = inID;
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
    private DCMI18NString mName;
    private String mSubComponentID;
    private String mVersion;
}
