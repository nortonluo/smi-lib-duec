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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for representing GUID
 *
 * @author Raveendra_Madala
 */
public class DCMGUID {

    /**
     * Constructor
     */
    public DCMGUID() {
    }

    /**
     * Copy Constructor
     *
     * @param inID specifies the object from which this object is to be
     * constructed
     */
    public DCMGUID(DCMGUID inID) {
        if (inID != null) {
            mGUID = inID.mGUID;
        }
    }

    /**
     * Method for getting the identifier
     *
     * @return the identifier
     */
    public String getID() {
        return mGUID;
    }

    /**
     * Method for setting the identifier
     *
     * @param inID specifies identifier being set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned.
     */
    public int setID(String inID) {
        Pattern guidPattern = Pattern.compile(mGUIDPattern);
        Matcher matcher = guidPattern.matcher(inID);
        if (!matcher.matches()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        mGUID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    private String mGUID;
    private static final String mGUIDPattern = "[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}";
}
