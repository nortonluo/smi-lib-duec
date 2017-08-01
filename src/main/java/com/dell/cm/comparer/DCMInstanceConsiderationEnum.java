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
package com.dell.cm.comparer;

/**
 * class for representing the enumeration for considering updates while
 * comparing
 *
 * @author Md_Shahbaz_Alam
 */
public enum DCMInstanceConsiderationEnum {

    /**
     * Take Base instance into consideration while comparing Target-Inventory
     * against Base-Inventory
     */
    /**
     * Take Base instance into consideration while comparing Target-Inventory
     * against Base-Inventory
     */
    /**
     * Take Base instance into consideration while comparing Target-Inventory
     * against Base-Inventory
     */
    /**
     * Take Base instance into consideration while comparing Target-Inventory
     * against Base-Inventory
     */
    CONSIDER_INSTANCE_STRICTLY,
    /**
     * Don't take Base instance into consideration while comparing
     * Target-Inventory against Base-Inventory and compare each instance in
     * target inventory against the max-version-instance in base-inventory.
     */
    CONSIDER_MAX_INSTANCE_VERSION,
    /**
     * Don't take Base instance into consideration while comparing
     * Target-Inventory against Base-Inventory and compare each instance in
     * target inventory against the min-version-instance in base-inventory.
     */
    CONSIDER_MIN_INSTANCE_VERSION,
}
