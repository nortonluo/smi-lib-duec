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
package com.dell.sm.wsman.utility;

/**
 * Enumeration class for WSMan Method Parameters
 * @author GARGI_PRIYADARSHINI
 */
public enum WSManMethodParamEnum {

        INSTANCE_ID("InstanceID"),
        SOURCE("source"),
        ENABLED_STATE("EnabledState"),
        REBOOT_JOB_TYPE("RebootJobType"),
        SCHEDULED_START_TIME("ScheduledStartTime"),
        SYSTEM_CLASS_NAME("SystemCreationClassName"),
        CREATION_CLASS_NAME("CreationClassName"),
        SYSTEM_NAME("SystemName"),
        NAME("Name"),
        TARGET("Target"),
        REBOOT_IF_REQUIRED("RebootIfRequired"),
        REQUESTED_STATE("RequestedState"),
        TIME_OUT_PERIOD("TimeoutPeriod"),
        ATTRIBUTE_NAME("AttributeName"),
        ATTRIBUTE_VALUE("AttributeValue"),
        PROVISIONING_SERVER("ProvisioningServer"),
        RESET_TO_FACTORY_DEFAULTS("ResetToFactoryDefaults"),
        PERFORM_AUTO_DISCOVERY("PerformAutoDiscovery");

        String enumValue;

        WSManMethodParamEnum(String value) {
            enumValue = value;
        }

        @Override
        public String toString() {
            return enumValue;
        }
    
}
