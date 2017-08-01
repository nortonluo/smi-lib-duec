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
 * Enumeration for representing Component Type
 *
 * @author Raveendra_Madala
 */
public enum DCMComponentType {

    /**
     * System Bundle
     */
    SYSTEM_BUNDLE,
    /**
     * BIOS
     */
    BIOS,
    /**
     * Firmware
     */
    FIRMWARE,
    /**
     * Application
     */
    APPLICATION,
    /**
     * DRIVER
     */
    DRIVER;

    public static DCMComponentType getEnumeration(String inValue) {
        if (inValue.equals(DCMConstants.FRMW_ENUM)) {
            return DCMComponentType.FIRMWARE;
        } else if (inValue.equals(DCMConstants.BIOS_ENUM)) {
            return DCMComponentType.BIOS;
        } else if (inValue.equals(DCMConstants.DRVR_ENUM)) {
            return DCMComponentType.DRIVER;
        } else if (inValue.equals(DCMConstants.APP_ENUM) || inValue.equals(DCMConstants.APAC_ENUM)) {
            return DCMComponentType.APPLICATION;
        } else if (inValue.equals(DCMConstants.SBDL_ENUM)) {
            return DCMComponentType.SYSTEM_BUNDLE;
        }
        return DCMComponentType.FIRMWARE;
    }

    public static String toString(DCMComponentType inType) {
        if (inType.equals(DCMComponentType.FIRMWARE)) {
            return DCMConstants.FRMW_ENUM;
        } else if (inType.equals(DCMComponentType.BIOS)) {
            return DCMConstants.BIOS_ENUM;
        } else if (inType.equals(DCMComponentType.DRIVER)) {
            return DCMConstants.DRVR_ENUM;
        } else if (inType.equals(DCMComponentType.APPLICATION)) {
            return DCMConstants.APAC_ENUM;
        } else if (inType.equals(DCMComponentType.SYSTEM_BUNDLE)) {
            return DCMConstants.SBDL_ENUM;
        }
        return DCMConstants.FRMW_ENUM;
    }
}
