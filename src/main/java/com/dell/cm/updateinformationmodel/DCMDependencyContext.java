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
 * Enumeration for Context enumeration
 *
 * @author Raveendra_Madala
 */
public enum DCMDependencyContext {

    /**
     * Operating System
     */
    OS,
    /**
     * LifeCycle Controller
     */
    LC,
    /**
     * iDRAC
     */
    iDRAC,
    /**
     * Console
     */
    Console,
    /**
     * All
     */
    All;

    /**
     * Method for getting the enumeration for the string
     *
     * @param inValue specifies the value for which the enumeration is sought
     * @return the enumeration
     */
    public static DCMDependencyContext getEnumeration(String inValue) {
        if (inValue.equals(DCMConstants.OS_ENUM)) {
            return DCMDependencyContext.OS;
        } else if (inValue.equals(DCMConstants.LC_ENUM)) {
            return DCMDependencyContext.LC;
        } else if (inValue.equals(DCMConstants.IDRAC_ENUM)) {
            return DCMDependencyContext.iDRAC;
        } else if (inValue.equals(DCMConstants.CONSOLE_ENUM)) {
            return DCMDependencyContext.Console;
        }
        return DCMDependencyContext.All;
    }

    /**
     * Method for getting the string for the given dependency context type
     *
     * @param inType specifies the dependency context type being converted
     * @return the string
     */
    public static String toString(DCMDependencyContext inType) {
        if (inType.equals(DCMDependencyContext.OS)) {
            return DCMConstants.OS_ENUM;
        } else if (inType.equals(DCMDependencyContext.LC)) {
            return DCMConstants.LC_ENUM;
        } else if (inType.equals(DCMDependencyContext.iDRAC)) {
            return DCMConstants.IDRAC_ENUM;
        } else if (inType.equals(DCMDependencyContext.Console)) {
            return DCMConstants.CONSOLE_ENUM;
        }
        return DCMConstants.ALL_ENUM;
    }
}
