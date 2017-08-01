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
 * class for representing the install instructions
 *
 * @author Raveendra_Madala
 */
public class DCMInstallInstructions {

    /**
     * Constructor
     */
    public DCMInstallInstructions() {
    }

    /**
     * Copy Constructor
     *
     * @param inInstructions specifies the object from which this object is to
     * be constructed
     */
    public DCMInstallInstructions(DCMInstallInstructions inInstructions) {
        if (inInstructions != null) {
            mName = new DCMI18NString(inInstructions.mName);
            mFileName = inInstructions.mFileName;
            mTypeCode = inInstructions.mTypeCode;
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
     * Method for getting the file name
     *
     * @return the file name
     */
    public String getFileName() {
        return mFileName;
    }

    /**
     * Method for setting the file name
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
     * Method for getting the type code
     *
     * @return the type code
     */
    public String getTypeCode() {
        return mTypeCode;
    }

    /**
     * Method for setting the type code
     *
     * @param inCode specifies the code to be set
     * @return SUCCESS if the code could be set else appropriate error code is
     * returned
     */
    public int setTypeCode(String inCode) {
        mTypeCode = inCode;
        return DCMErrorCodes.SUCCESS;
    }
    private DCMI18NString mName;
    private String mFileName;
    private String mTypeCode;
}
