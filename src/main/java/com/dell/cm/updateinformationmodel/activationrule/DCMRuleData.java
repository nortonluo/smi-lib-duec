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
package com.dell.cm.updateinformationmodel.activationrule;

import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.util.Collection;

/**
 *
 * @author Shabu_VC
 */
public class DCMRuleData {
   /**
     * Gets the map of the messages and rules applicable.
     *
     * @param inVersionInfoList all components in the inventory
     * @param inVersionInfo Version in comparison
     * @param inSystemTypeIdentifier System of the System
     * @param inOSIdentifier OS of the system
     * @param inDellVersion OS of the system
     * @param inVendorVersion OS of the system
     *
     */
    public DCMRuleData(Collection<DCMVersionInformation> inVersionInfoList, DCMVersionInformation inVersionInfo,
            String inSystemTypeIdentifier, String inOSIdentifier, String inDellVersion, String inVendorVersion) {

        mVersionInfoList = inVersionInfoList;
        mVersionInfo = inVersionInfo;
        mSystemTypeIdentifier = inSystemTypeIdentifier;
        mOSIdentifier = inOSIdentifier;
        mDellVersion = inDellVersion;
        mPackageVersion = inVendorVersion;

    }

    protected Collection<DCMVersionInformation> mVersionInfoList;
    protected DCMVersionInformation mVersionInfo;
    protected String mSystemTypeIdentifier;
    protected String mOSIdentifier;
    protected String mDellVersion;
    protected String mPackageVersion;
}
