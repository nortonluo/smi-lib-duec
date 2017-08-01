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
 * Enumeration for representing Target Entity Enumeration
 *
 * @author Raveendra_Madala
 */
public enum DCMTargetEntity {

    /**
     * PCI Device
     */
    PCI,
    /**
     * PnP Device
     */
    PNP,
    /**
     * Non-PCI/PnP Hardware
     */
    HARDWARE,
    /**
     * Application
     */
    APPLICATION,
    /**
     * Application Part
     */
    APPLICATION_PART,
    /**
     * MSI based Application
     */
    MSI;

    public static DCMTargetEntity getEnumeration(String inValue) {
        if (inValue.equals(DCMConstants.PCI_ATTRIBUTE)) {
            return DCMTargetEntity.PCI;
        } else if (inValue.equals(DCMConstants.PNP_ATTRIBUTE)) {
            return DCMTargetEntity.PNP;
        } else if (inValue.equals(DCMConstants.HARDWARE_ATTRIBUTE)) {
            return DCMTargetEntity.HARDWARE;
        } else if (inValue.equals(DCMConstants.APP_ATTRIBUTE)) {
            return DCMTargetEntity.APPLICATION;
        } else if (inValue.equals(DCMConstants.APP_PART_ATTRIBUTE)) {
            return DCMTargetEntity.APPLICATION_PART;
        } else if (inValue.equals(DCMConstants.MSI_ATTRIBUTE)) {
            return DCMTargetEntity.MSI;
        }
        return DCMTargetEntity.HARDWARE;
    }

    public static String toString(DCMTargetEntity inType) {
        if (inType.equals(DCMTargetEntity.PCI)) {
            return DCMConstants.PCI_ATTRIBUTE;
        } else if (inType.equals(DCMTargetEntity.PNP)) {
            return DCMConstants.PNP_ATTRIBUTE;
        } else if (inType.equals(DCMTargetEntity.HARDWARE)) {
            return DCMConstants.HARDWARE_ATTRIBUTE;
        } else if (inType.equals(DCMTargetEntity.APPLICATION)) {
            return DCMConstants.APP_ATTRIBUTE;
        } else if (inType.equals(DCMTargetEntity.APPLICATION_PART)) {
            return DCMConstants.APP_PART_ATTRIBUTE;
        } else if (inType.equals(DCMTargetEntity.MSI)) {
            return DCMConstants.MSI_ATTRIBUTE;
        }
        return DCMConstants.HARDWARE_ATTRIBUTE;
    }
}
