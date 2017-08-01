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
 * System ID Type Enumeration
 *
 * @author Raveendra_Madala
 */
public enum DCMSystemIDType {

    /**
     * BIOS
     */
    BIOS,
    /**
     * Multi System Chassis
     */
    MULTISYSTEMCHASSIS,
    /**
     * RAID Chassis
     */
    RAIDCHASSIS,
    /**
     * Router
     */
    ROUTER,
    /**
     * Network Switch
     */
    NETWORKSWITCH;

    public static DCMSystemIDType getEnumeration(String inValue) {
        if (inValue.equals(DCMConstants.BIOS_ENUM)) {
            return DCMSystemIDType.BIOS;
        } else if (inValue.equals(DCMConstants.MULTI_SYSTEM_CHASSIS_ENUM)) {
            return DCMSystemIDType.MULTISYSTEMCHASSIS;
        } else if (inValue.equals(DCMConstants.RAID_CHASSIS_ENUM)) {
            return DCMSystemIDType.RAIDCHASSIS;
        } else if (inValue.equals(DCMConstants.ROUTER_ENUM)) {
            return DCMSystemIDType.ROUTER;
        } else if (inValue.equals(DCMConstants.NETWORK_SWITCH_ENUM)) {
            return DCMSystemIDType.NETWORKSWITCH;
        }
        return DCMSystemIDType.BIOS;
    }

    public static String toString(DCMSystemIDType inType) {
        if (inType.equals(DCMSystemIDType.BIOS)) {
            return DCMConstants.BIOS_ENUM;
        } else if (inType.equals(DCMSystemIDType.MULTISYSTEMCHASSIS)) {
            return DCMConstants.MULTI_SYSTEM_CHASSIS_ENUM;
        } else if (inType.equals(DCMSystemIDType.RAIDCHASSIS)) {
            return DCMConstants.RAID_CHASSIS_ENUM;
        } else if (inType.equals(DCMSystemIDType.ROUTER)) {
            return DCMConstants.ROUTER_ENUM;
        } else if (inType.equals(DCMSystemIDType.NETWORKSWITCH)) {
            return DCMConstants.NETWORK_SWITCH_ENUM;
        }
        return DCMConstants.BIOS_ENUM;
    }
}
