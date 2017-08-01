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
package com.dell.cm.comparer;

import com.dell.cm.updateinformationmodel.DCMConstants;

/**
 *
 * @author Md_Shahbaz_Alam
 */
/**
 * System ID Type Enumeration
 *
 * @author Md_Shahbaz_Alam
 */
public enum DCMComparerResultType {

    /**
     * Equal
     */
    /**
     * Equal
     */
    /**
     * Equal
     */
    /**
     * Equal
     */
    EQUAL,
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

    public static DCMComparerResultType getEnumeration(String inValue) {
        if (inValue.equals(DCMConstants.EQUAL)) {
            return DCMComparerResultType.EQUAL;
        } else if (inValue.equals(DCMConstants.DOWNGRADE)) {
            return DCMComparerResultType.DOWNGRADE;
        } else if (inValue.equals(DCMConstants.UPGRADE)) {
            return DCMComparerResultType.UPGRADE;
        } else if (inValue.equals(DCMConstants.INVALID)) {
            return DCMComparerResultType.INVALID;
        } else if (inValue.equals(DCMConstants.NOT_PRESENT_IN_SOURCE)) {
            return DCMComparerResultType.NOT_PRESENT_IN_SOURCE;
        } else if (inValue.equals(DCMConstants.NOT_PRESENT_IN_DESTINATION)) {
            return DCMComparerResultType.NOT_PRESENT_IN_DESTINATION;
        }
        return DCMComparerResultType.INVALID;
    }

    public static String toString(DCMComparerResultType inType) {
        if (inType.equals(DCMComparerResultType.EQUAL)) {
            return DCMConstants.EQUAL;
        } else if (inType.equals(DCMComparerResultType.DOWNGRADE)) {
            return DCMConstants.DOWNGRADE;
        } else if (inType.equals(DCMComparerResultType.UPGRADE)) {
            return DCMConstants.UPGRADE;
        } else if (inType.equals(DCMComparerResultType.INVALID)) {
            return DCMConstants.INVALID;
        }
        if (inType.equals(DCMComparerResultType.NOT_PRESENT_IN_SOURCE)) {
            return DCMConstants.NOT_PRESENT_IN_SOURCE;
        }
        if (inType.equals(DCMComparerResultType.NOT_PRESENT_IN_DESTINATION)) {
            return DCMConstants.NOT_PRESENT_IN_DESTINATION;
        }
        return DCMConstants.INVALID;
    }
}
