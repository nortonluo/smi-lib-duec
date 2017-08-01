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
 * class for representing the criticality
 *
 * @author Raveendra_Madala
 */
public enum DCMCriticality {

    /**
     * Recommended
     */
    Recommended,
    /**
     * Urgent
     */
    Urgent,
    /**
     * Optional
     */
    Optional;

    /**
     * Method for getting the criticality enumeration for the given string
     *
     * @param inValue specifies the string for which the criticality enumeration
     * being sought
     * @return criticality enumeration
     */
    public static DCMCriticality getEnumeration(String inValue) {
        if (inValue.equals(DCMConstants.ONE)) {
            return DCMCriticality.Recommended;
        } else if (inValue.equals(DCMConstants.TWO)) {
            return DCMCriticality.Urgent;
        } else if (inValue.equals(DCMConstants.THREE)) {
            return DCMCriticality.Optional;
        }
        return DCMCriticality.Recommended;
    }

    /**
     * Method for getting the string for the given criticality enumeration
     *
     * @param inType specifies the enumeration for which the string is sought
     * @return the string
     */
    public static String toString(DCMCriticality inType) {
        if (inType.equals(DCMCriticality.Recommended)) {
            return DCMConstants.ONE;
        } else if (inType.equals(DCMCriticality.Urgent)) {
            return DCMConstants.TWO;
        } else if (inType.equals(DCMCriticality.Optional)) {
            return DCMConstants.THREE;
        }
        return DCMConstants.ONE;
    }
}
