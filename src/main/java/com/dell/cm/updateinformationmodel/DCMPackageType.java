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
 * class for representing the package type enumeration
 *
 * @author Raveendra_Madala
 */
public enum DCMPackageType {

    /**
     * Windows 32-bit DUP
     */
    LWXP,
    /**
     * Windows 64-bit DUP
     */
    LW64,
    /**
     * Linux DUP
     */
    LLXP,
    
    MCP;

    /**
     * Method for getting the package type enumeration for the given value
     *
     * @param inValue specifies the value for which the enumeration is being
     * sought
     * @return the package type enumeration
     */
    public static DCMPackageType getEnumeration(String inValue) {
        if (inValue.equals(DCMConstants.LWXP_ENUM)) {
            return DCMPackageType.LWXP;
        } else if (inValue.equals(DCMConstants.LW64_ENUM)) {
            return DCMPackageType.LW64;
        } else if (inValue.equals(DCMConstants.LLXP_ENUM)) {
            return DCMPackageType.LLXP;
        }
        return DCMPackageType.MCP;
    }

    /**
     * Method for getting the package type enumeration to the string
     *
     * @param inType the package type being converted to the string
     * @return the string
     */
    public static String toString(DCMPackageType inType) {
        if (inType.equals(DCMPackageType.LWXP)) {
            return DCMConstants.LWXP_ENUM;
        } else if (inType.equals(DCMPackageType.LW64)) {
            return DCMConstants.LW64_ENUM;
        } else if (inType.equals(DCMPackageType.LLXP)) {
            return DCMConstants.LLXP_ENUM;
        }
        return DCMConstants.MCP_ENUM;
    }
}
