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

/*
 * Purpose	:  class for 
 *
 * Author	:  Raveendra Madala
 *
 * $LastChangedBy: raveendra_madala $
 *
 * $Date: 2015-02-05 09:46:39 +0530 (Thu, 05 Feb 2015) $
 *
 * $Revision: 2211 $
 *
 */
/**
 *
 * @author Ujjwal_Kumar_Gupta
 */
import com.dell.cm.updateinformationmodel.DCMErrorCodes;

public class DCMInventoryComponent {

    public String getSystemID() {
        return mSystemID;
    }

    public int setSystemID(String systemID) {
        mSystemID = systemID;
        return DCMErrorCodes.SUCCESS;
    }

    public String getSystemIDType() {
        return mSystemIDType;
    }

    public int setSystemIDType(String systemIDType) {
        mSystemIDType = systemIDType;
        return DCMErrorCodes.SUCCESS;
    }

    public String getSystemName() {
        return mSystemDisplay;
    }

    public int setSystemName(String systemDisplay) {
        mSystemDisplay = systemDisplay;
        return DCMErrorCodes.SUCCESS;
    }

    public int getLOB() {
        return mLOB;
    }

    public int setLOB(int lob) {
        mLOB = lob;
        return DCMErrorCodes.SUCCESS;
    }

    public String getInvTime() {
        return mTime;
    }

    public String setInvTime(String time) {
        mTime = time;
        return mTime;
    }

    public String getServiceTag() {
        return mServiceTag;
    }

    public int setServiceTag(String serviceTag) {
        mServiceTag = serviceTag;
        return DCMErrorCodes.SUCCESS;
    }

    public String getInventoryCollectorName() {
        return mInventoryCollectorName;
    }

    public int setInventoryCollectorName(String inventoryCollectorName) {
        mInventoryCollectorName = inventoryCollectorName;
        return DCMErrorCodes.SUCCESS;
    }

    public String getInventoryCollectorVersion() {
        return mInventoryCollectorVersion;
    }

    public int setInventoryCollectorVersion(String inventoryCollectorVersion) {
        mInventoryCollectorVersion = inventoryCollectorVersion;
        return DCMErrorCodes.SUCCESS;
    }
    // -------------- Device Node --------------------

    public String getComponentID() {
        return mComponentID;
    }

    /**
     * Method for setting the component id
     *
     * @param inID specifies the component id being set
     * @return the component id
     */
    public int setComponentID(String inID) {
        mComponentID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    public String getVendorID() {
        return mVendorID;
    }

    public int setVendorID(String inID) {
        mVendorID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    public String getDeviceID() {
        return mDeviceID;
    }

    public int setDeviceID(String inID) {
        mDeviceID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    public String getSubVendorID() {
        return mSubVendorID;
    }

    public int setSubVendorID(String inID) {
        mSubVendorID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    public String getSubDeviceID() {
        return mSubDeviceID;
    }

    public int setSubDeviceID(String inID) {
        mSubDeviceID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    public String getDeviceDisplay() {
        return mDeviceDisplay;
    }

    public int setDeviceDisplay(String deviceDisplay) {
        mDeviceDisplay = deviceDisplay;
        return DCMErrorCodes.SUCCESS;
    }

    public String getDeviceType() {
        return mEnum;
    }

    public int setDeviceType(String enm) {
        mEnum = enm;
        return DCMErrorCodes.SUCCESS;
    }

    public String getComponentInstance() {
        return mComponentInstance;
    }

    public int setComponentInstance(String componentInstance) {
        mComponentInstance = componentInstance;
        return DCMErrorCodes.SUCCESS;
    }

    // ---------- Application Nodes -----------
    public String getComponentType() {
        return mComponentType;
    }

    public int setComponentType(String componentType) {
        mComponentType = componentType;
        return DCMErrorCodes.SUCCESS;
    }

    public String getVersion() {
        return mVersion;
    }

    public int setVersion(String version) {
        mVersion = version;
        return DCMErrorCodes.SUCCESS;
    }

    public String getDeviceName() {
        return mApplicationDisplay;
    }

    public int setDeviceName(String applicationDisplay) {
        mApplicationDisplay = applicationDisplay;
        return DCMErrorCodes.SUCCESS;
    }

    // Document Node Attributes
    String mInventoryCollectorName;
    String mInventoryCollectorVersion;
    String mServiceTag;

    // System Node Attributes
    String mSystemID;
    String mSystemIDType;
    String mSystemDisplay;
    int mLOB;

    // inventory time
    String mTime;

    //Device Node Attributes
    String mVendorID;
    String mDeviceID;
    String mSubVendorID;
    String mSubDeviceID;
    String mDeviceDisplay;
    String mComponentID;
    String mEnum;   // ENUM_ATTRIBUTE
    String mComponentInstance;  // COMPONENT_INSTANCE_ATTRIBUTE

    // Application Node Attributes
    String mComponentType;
    String mVersion;
    String mApplicationDisplay;

}
