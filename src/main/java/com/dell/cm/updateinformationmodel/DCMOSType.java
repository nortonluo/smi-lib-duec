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
 * Operating System Type enumeration
 *
 * @author Raveendra_Madala
 */
public enum DCMOSType {

    /**
     * client
     */
    CLIENT,
    /**
     * server
     */
    SERVER,
    /**
     * Controller
     */
    CONTROLLER,
    /**
     * Switch
     */
    SWITCH;

    public static DCMOSType getEnumeration(String inValue) {
        if (inValue.equals(DCMConstants.CLIENT_ENUM)) {
            return DCMOSType.CLIENT;
        } else if (inValue.equals(DCMConstants.SERVER_ENUM)) {
            return DCMOSType.SERVER;
        } else if (inValue.equals(DCMConstants.CONTROLLER_ENUM)) {
            return DCMOSType.CONTROLLER;
        } else if (inValue.equals(DCMConstants.SWITCH_ENUM)) {
            return DCMOSType.SWITCH;
        }
        return DCMOSType.SERVER;
    }

    public static String toString(DCMOSType inType) {
        if (inType.equals(DCMOSType.CLIENT)) {
            return DCMConstants.CLIENT_ENUM;
        } else if (inType.equals(DCMOSType.SERVER)) {
            return DCMConstants.SERVER_ENUM;
        } else if (inType.equals(DCMOSType.CONTROLLER)) {
            return DCMConstants.CONTROLLER_ENUM;
        } else if (inType.equals(DCMOSType.SWITCH)) {
            return DCMConstants.SWITCH_ENUM;
        }
        return DCMConstants.SERVER_ENUM;
    }

}
