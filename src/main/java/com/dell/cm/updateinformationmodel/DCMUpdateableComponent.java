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
 * class for representing the updateable component
 *
 * @author Raveendra_Madala
 */
public class DCMUpdateableComponent {

    /**
     * Constructor
     *
     * @param inType specifies the type of the component
     * @param inTargetIdentifier specifies the target identifier
     */
    public DCMUpdateableComponent(DCMComponentType inType, String inTargetIdentifier) {
        mType = inType;
        mTargetIdentifier = inTargetIdentifier;
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
     * @param inName specifies the name being set
     * @return SUCCESS if the name could be set else appropriate error code is
     * returned
     */
    public int setName(DCMI18NString inName) {
        mName = new DCMI18NString(inName);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the target identifier
     *
     * @return the target identifier
     */
    public String getTargetIdentifier() {
        return mTargetIdentifier;
    }

    /**
     * Method for getting the component type
     *
     * @return the component type
     */
    public DCMComponentType getComponentType() {
        return mType;
    }

    /**
     * Method for getting the unique identifier
     *
     * @return the unique identifier for this updateable component
     */
    public String getUniqueIdentifier() {
        String retVal = new String();
        retVal += mTargetIdentifier;
        retVal += DCMComponentType.toString(mType);
        return retVal;
    }

    private DCMI18NString mName;
    private final DCMComponentType mType;
    private final String mTargetIdentifier;
}
