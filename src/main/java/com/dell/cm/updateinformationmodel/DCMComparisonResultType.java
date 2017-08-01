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
 * Enumeration for representing the comparison result
 *
 * @author Raveendra_Madala
 */
public enum DCMComparisonResultType {

    /**
     * Equal
     */
    EQUAL,
    /**
     * LOWER
     */
    LOWER,
    /**
     * GREATER
     */
    GREATER,
    /**
     * LOWER
     */
    DOWNGRADE,
    /**
     * GREATER
     */
    UPGRADE,
    /**
     * INVALID
     */
    INVALID,
    /**
     * SOURCE NOT PRESENT
     */
    NOT_PRESENT_IN_SOURCE,
    /**
     * DESTINATION NOT PRESENT
     */
    NOT_PRESENT_IN_DESTINATION;
    
    public static DCMComparisonResultType getEnumeration(String inValue) {
        if (inValue.equals(DCMConstants.EQUAL)) {
            return DCMComparisonResultType.EQUAL;
        } else if (inValue.equals(DCMConstants.DOWNGRADE)) {
            return DCMComparisonResultType.DOWNGRADE;
        } else if (inValue.equals(DCMConstants.UPGRADE)) {
            return DCMComparisonResultType.UPGRADE;
        } else if (inValue.equals(DCMConstants.INVALID)) {
            return DCMComparisonResultType.INVALID;
        } else if (inValue.equals(DCMConstants.NOT_PRESENT_IN_SOURCE)) {
            return DCMComparisonResultType.NOT_PRESENT_IN_SOURCE;
        } else if (inValue.equals(DCMConstants.NOT_PRESENT_IN_DESTINATION)) {
            return DCMComparisonResultType.NOT_PRESENT_IN_DESTINATION;
        }
        return DCMComparisonResultType.INVALID;
    }

    public static String toString(DCMComparisonResultType inType) {
        if (inType.equals(DCMComparisonResultType.EQUAL)) {
            return DCMConstants.EQUAL;
        } else if (inType.equals(DCMComparisonResultType.DOWNGRADE)) {
            return DCMConstants.DOWNGRADE;
        } else if (inType.equals(DCMComparisonResultType.UPGRADE)) {
            return DCMConstants.UPGRADE;
        } else if (inType.equals(DCMComparisonResultType.INVALID)) {
            return DCMConstants.INVALID;
        }
        if (inType.equals(DCMComparisonResultType.NOT_PRESENT_IN_SOURCE)) {
            return DCMConstants.NOT_PRESENT_IN_SOURCE;
        }
        if (inType.equals(DCMComparisonResultType.NOT_PRESENT_IN_DESTINATION)) {
            return DCMConstants.NOT_PRESENT_IN_DESTINATION;
        }
        return DCMConstants.INVALID;
    }
    

}
