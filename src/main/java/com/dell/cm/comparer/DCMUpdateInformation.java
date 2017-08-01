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

import com.dell.cm.updateinformationmodel.DCMCriticality;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMI18NString;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import com.dell.cm.updateinformationmodel.activationrule.DCMActionRule;
import com.dell.cm.updateinformationmodel.activationrule.DCMMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for representing the update information
 *
 * @author Raveendra_Madala
 */
public class DCMUpdateInformation {

    public DCMUpdateInformation() {
        this.mHardDependencies = new HashMap<>();
        this.mSoftDependencies = new HashMap<>();
    }

    /**
     * Method for getting the update version
     *
     * @return the version
     */
    public String getVersion() {
        return mVersion;
    }

    /**
     * Method for setting the update version
     *
     * @param inVersion specifies the version to be set
     * @return SUCCESS if the version could be set else appropriate error code
     * is returned
     */
    public int setVersion(String inVersion) {
        mVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the path
     *
     * @return the path
     */
    public String getPath() {
        return mPath;
    }

    /**
     * Method for setting the path
     *
     * @param inPath specifies the path to be set
     * @return SUCCESS if the path could be set else appropriate error code is
     * returned
     */
    public int setPath(String inPath) {
        mPath = inPath;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the name
     *
     * @return the name
     */
    public DCMI18NString getName() {
        return mName;
    }

    /**
     * Method for setting the name
     *
     * @param inName specifies the name to be set
     * @return SUCCESS if the name could be set else appropriate error code is
     * returned.
     */
    public int setName(DCMI18NString inName) {
        mName = new DCMI18NString(inName);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the criticality
     *
     * @return the criticality
     */
    public DCMCriticality getCriticality() {
        return mCriticality;
    }

    /**
     * Method for setting the criticality
     *
     * @param inCriticality specifies the criticality to be set
     * @return SUCCESS if the criticality could be set else appropriate error
     * code is returned.
     */
    public int setCriticality(DCMCriticality inCriticality) {
        mCriticality = inCriticality;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the unique identifier
     *
     * @return the unique identifier
     */
    public String getUniqueIdentifier() {
        return mUniqueIdentifier;
    }

    /**
     * Method for setting the unique identifier
     *
     * @param inIdentifier specifies the identifier being set
     * @return SUCCESS if the identifier could be set else appropriate error
     * code is returned.
     */
    public int setUniqueIdentifier(String inIdentifier) {
        mUniqueIdentifier = inIdentifier;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the update type
     *
     * @return the update type
     */
    public DCMUpdateType getType() {
        return mUpdateType;
    }

    /**
     * Method for setting the update type
     *
     * @param inType specifies the type to be set
     * @return SUCCESS if the type could be set else appropriate error code is
     * returned
     */
    public int setType(DCMUpdateType inType) {
        mUpdateType = inType;
        return DCMErrorCodes.SUCCESS;
    }

    public HashMap<DCMVersionInformation, DCMUpdateInformation> getSoftDependencies() {
        return mSoftDependencies;
    }

    /**
     * Method for determining whether the update package requires reboot or not
     *
     * @return true if the update package requires reboot else false is returned
     */
    public boolean isRebootRequired() {
        return mRebootRequired;
    }

    /**
     * Method for setting the reboot required value
     *
     * @param inValue specifies the value to be set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned
     */
    public int setRequiresReboot(boolean inValue) {
        mRebootRequired = inValue;
        return DCMErrorCodes.SUCCESS;
    }

    public int addSoftDependencyInformation(DCMVersionInformation inVersionInformation, DCMUpdateInformation inSoftDependency) {
        if (null == inVersionInformation || null == inSoftDependency) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        mSoftDependencies.put(inVersionInformation, inSoftDependency);
        return DCMErrorCodes.SUCCESS;
    }

    public HashMap<DCMVersionInformation, DCMUpdateInformation> getHardDependencies() {
        return mHardDependencies;
    }

    public int addHardDependency(DCMVersionInformation inVersionInformation, DCMUpdateInformation inHardDependency) {
        if (null == inVersionInformation || null == inHardDependency) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        mHardDependencies.put(inVersionInformation, inHardDependency);
        return DCMErrorCodes.SUCCESS;
    }
     /**
     * Gets the map of the applicable rules and messages.
     *
     * @return possible object is {@link Map }
     *
     */
    public Map<DCMMessage, List<DCMActionRule>> getActivationRules() {
        if (mApplicableRules == null) {
            mApplicableRules = new HashMap<>();
        }
        return this.mApplicableRules;
    }

    private String mVersion;
    private String mPath;
    private DCMI18NString mName;
    private DCMCriticality mCriticality;
    private String mUniqueIdentifier;
    private DCMUpdateType mUpdateType;
    private HashMap<DCMVersionInformation, DCMUpdateInformation> mSoftDependencies;
    private HashMap<DCMVersionInformation, DCMUpdateInformation> mHardDependencies;
    private boolean mRebootRequired;
    private Map<DCMMessage, List<DCMActionRule>> mApplicableRules;

}
