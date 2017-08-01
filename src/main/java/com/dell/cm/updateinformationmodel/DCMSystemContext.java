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
 * Enumeration for System Context
 *
 * @author Raveendra_Madala
 */
public enum DCMSystemContext {

    /**
     * Independent
     */
    INDEPENDENT,
    /**
     * Chassis
     */
    CHASSIS,
    /**
     * Sub Chassis
     */
    SUBCHASSIS,
    /**
     * Rack
     */
    RACK;

    public static DCMSystemContext getEnumeration(String inValue) {
        if (inValue.equals(DCMConstants.INDEPENDENT)) {
            return DCMSystemContext.INDEPENDENT;
        } else if (inValue.equals(DCMConstants.CHASSIS_ENUM)) {
            return DCMSystemContext.CHASSIS;
        } else if (inValue.equals(DCMConstants.SUB_CHASSIS_ENUM)) {
            return DCMSystemContext.SUBCHASSIS;
        } else if (inValue.equals(DCMConstants.RACK_ENUM)) {
            return DCMSystemContext.RACK;
        }
        return DCMSystemContext.INDEPENDENT;
    }

    public static String toString(DCMSystemContext inType) {
        if (inType.equals(DCMSystemContext.INDEPENDENT)) {
            return DCMConstants.INDEPENDENT;
        } else if (inType.equals(DCMSystemContext.CHASSIS)) {
            return DCMConstants.CHASSIS_ENUM;
        } else if (inType.equals(DCMSystemContext.SUBCHASSIS)) {
            return DCMConstants.CHASSIS_ENUM;
        } else if (inType.equals(DCMSystemContext.RACK)) {
            return DCMConstants.RACK_ENUM;
        }
        return DCMConstants.INDEPENDENT;
    }
}
