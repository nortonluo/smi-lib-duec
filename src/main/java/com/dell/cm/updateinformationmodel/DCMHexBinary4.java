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
 * Class for HexBinary4
 *
 * @author Raveendra_Madala
 */
public class DCMHexBinary4 {

    /**
     * Constructor
     */
    public DCMHexBinary4() {

    }

    /**
     * Copy Constructor
     *
     * @param inHexBinary4 specifies the object from which this object is to be
     * constructed
     */
    public DCMHexBinary4(DCMHexBinary4 inHexBinary4) {
        if (inHexBinary4 != null) {
            try {
                mValue = inHexBinary4.mValue.toUpperCase();
            } catch (Exception ex) {
                mValue = "";
            }
        }
    }

    /**
     * Method for getting the value
     *
     * @return the value
     */
    public String getValue() {
        return mValue;
    }

    /**
     * Method for setting the value
     *
     * @param inValue specifies value being set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned.
     */
    public int setValue(String inValue) {
        if (inValue.length() == 3) {
            inValue = "0".concat(inValue);
        }
        Pattern guidPattern = Pattern.compile(mHexB4Pattern);
        Matcher matcher = guidPattern.matcher(inValue);
        if (!matcher.matches()) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        mValue = inValue.toUpperCase();
        return DCMErrorCodes.SUCCESS;
    }

    private String mValue;
    private static final String mHexB4Pattern = "[0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f]";
    private static final String mHexB3Pattern = "[0-9A-Fa-f][0-9A-Fa-f][0-9A-Fa-f]";
}
