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
 * Enumeration for OS Architecture
 *
 * @author Raveendra_Madala
 */
public enum DCMOSArchitecture {

    /**
     * x86 32-bit
     */
    X8632,
    /**
     * x86 64-bit
     */
    X8664,
    /**
     * Itanium 32-bit
     */
    IA32,
    /**
     * Itanium 64-bit
     */
    IA64;

    public static DCMOSArchitecture getEnumeration(String inValue) {
        if (inValue.equals(DCMConstants.X86_ENUM)) {
            return DCMOSArchitecture.X8632;
        } else if (inValue.equals(DCMConstants.X64_ENUM)) {
            return DCMOSArchitecture.X8664;
        } else if (inValue.equals(DCMConstants.IA32_ENUM)) {
            return DCMOSArchitecture.IA32;
        } else if (inValue.equals(DCMConstants.IA64_ENUM)) {
            return DCMOSArchitecture.IA64;
        }
        return DCMOSArchitecture.X8632;
    }

    public static String toString(DCMOSArchitecture inArchitecture) {
        if (inArchitecture.equals(DCMOSArchitecture.X8632)) {
            return DCMConstants.X86_ENUM;
        } else if (inArchitecture.equals(DCMOSArchitecture.X8664)) {
            return DCMConstants.X64_ENUM;
        } else if (inArchitecture.equals(DCMOSArchitecture.IA32)) {
            return DCMConstants.IA32_ENUM;
        } else if (inArchitecture.equals(DCMOSArchitecture.IA64)) {
            return DCMConstants.IA64_ENUM;
        }
        return DCMConstants.X86_ENUM;
    }
}
