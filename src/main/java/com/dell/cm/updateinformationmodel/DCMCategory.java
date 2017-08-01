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
 * class for representing the category
 *
 * @author Raveendra_Madala
 */
public class DCMCategory {

    /**
     * Constructor
     *
     * @param inCode specifies the category code
     */
    public DCMCategory(String inCode) {
        mCode = inCode;
    }

    /**
     * Method for getting the category code
     *
     * @return the category code
     */
    public String getCode() {
        return mCode;
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
        if (inName != null) {
            mName = new DCMI18NString(inName);
        }
        return DCMErrorCodes.SUCCESS;
    }

    private final String mCode;
    private DCMI18NString mName;
}
