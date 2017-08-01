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
 * class for representing Bundle type enumeration
 *
 * @author Raveendra_Madala
 */
public enum DCMBundleType {

    /**
     * 32-bit Windows Bundle
     */
    BTW32,
    /**
     * 64-bit Windows Bundle
     */
    BTW64,
    /**
     * Linux Bundle
     */
    BTLX,
    /**
     * RPM Bundle
     */
    BTRPM,
    /**
     * DEB Bundle
     */
    BTDEB,
    /**
     * All Bundles
     */
    BTALL,
    /**
     * Operating System Independent
     */
    BTOSIND,
    /**
     * 64-bit over 32-bit Windows Bundle
     */
    BTWMIX;

    /**
     * Method for getting the enumeration for the string
     *
     * @param inValue specifies the value for which the enumeration is sought
     * @return the enumeration
     */
    public static DCMBundleType getEnumeration(String inValue) {
        if (inValue == null) {
            return DCMBundleType.BTW32;
        }
        if (inValue.equals(DCMConstants.BTW32_ENUM)) {
            return DCMBundleType.BTW32;
        } else if (inValue.equals(DCMConstants.BTW64_ENUM)) {
            return DCMBundleType.BTW64;
        } else if (inValue.equals(DCMConstants.BTLX_ENUM)) {
            return DCMBundleType.BTLX;
        } else if (inValue.equals(DCMConstants.BTRPM_ENUM)) {
            return DCMBundleType.BTRPM;
        } else if (inValue.equals(DCMConstants.BTDEB_ENUM)) {
            return DCMBundleType.BTDEB;
        } else if (inValue.equals(DCMConstants.BTALL_ENUM)) {
            return DCMBundleType.BTALL;
        } else if (inValue.equals(DCMConstants.BIOSIND_ENUM)) {
            return DCMBundleType.BTOSIND;
        } else if (inValue.equals(DCMConstants.BTWMIX_ENUM)) {
            return DCMBundleType.BTWMIX;
        }
        return DCMBundleType.BTW32;
    }

    /**
     * Method for getting the string for the given bundle type
     *
     * @param inType specifies the bundle type being converted
     * @return the string
     */
    public static String toString(DCMBundleType inType) {
        if (inType == null) {
            return DCMConstants.BTW32_ENUM;
        }
        if (inType.equals(DCMBundleType.BTW32)) {
            return DCMConstants.BTW32_ENUM;
        } else if (inType.equals(DCMBundleType.BTW64)) {
            return DCMConstants.BTW64_ENUM;
        } else if (inType.equals(DCMBundleType.BTLX)) {
            return DCMConstants.BTLX_ENUM;
        } else if (inType.equals(DCMBundleType.BTRPM)) {
            return DCMConstants.BTRPM_ENUM;
        } else if (inType.equals(DCMBundleType.BTDEB)) {
            return DCMConstants.BTDEB_ENUM;
        } else if (inType.equals(DCMBundleType.BTALL)) {
            return DCMConstants.BTALL_ENUM;
        } else if (inType.equals(DCMBundleType.BTOSIND)) {
            return DCMConstants.BIOSIND_ENUM;
        } else if (inType.equals(DCMBundleType.BTWMIX)) {
            return DCMConstants.BTWMIX_ENUM;
        }
        return DCMConstants.BTW32_ENUM;
    }
}
