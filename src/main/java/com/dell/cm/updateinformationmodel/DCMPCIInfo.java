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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PCIInfo class
 *
 * @author Raveendra_Madala
 */
public class DCMPCIInfo {

    /**
     * Construct with vendor id and device id
     *
     * @param inVendorID specifies the vendor id
     * @param inDeviceID specifies the device id
     */
    public DCMPCIInfo(DCMHexBinary4 inVendorID, DCMHexBinary4 inDeviceID) {
        mVendorID = inVendorID;
        mDeviceID = inDeviceID;
    }

    /**
     * Construct with vendor id, device id and sub vendor id
     *
     * @param inVendorID specifies the vendor id
     * @param inDeviceID specifies the device id
     * @param inSubVendorID specifies the sub vendor id
     */
    public DCMPCIInfo(DCMHexBinary4 inVendorID, DCMHexBinary4 inDeviceID, DCMHexBinary4 inSubVendorID) {
        mVendorID = inVendorID;
        mDeviceID = inDeviceID;
        mSubVendorID = inSubVendorID;
    }

    /**
     * Construct with vendor id, device id, sub vendor id and sub device id
     *
     * @param inVendorID specifies the vendor id
     * @param inDeviceID specifies the device id
     * @param inSubVendorID specifies the sub vendor id
     * @param inSubDeviceID specifies the sub device id
     */
    public DCMPCIInfo(DCMHexBinary4 inVendorID, DCMHexBinary4 inDeviceID, DCMHexBinary4 inSubVendorID, DCMHexBinary4 inSubDeviceID) {
        mVendorID = inVendorID;
        mDeviceID = inDeviceID;
        mSubVendorID = inSubVendorID;
        mSubDeviceID = inSubDeviceID;
    }

    /**
     * Construct with vendor id, device id, sub vendor id and sub device id
     *
     * @param pciID specifies the complete PCI ID
     * @return parsed object
     */
    public static DCMPCIInfo parse(String pciID) {
        String pnpReg = ".*([A-Z]+\\\\VEN_([0-9A-F]+)&DEV_([0-9A-F]+)(&SUBSYS_([0-9A-F]+))?(&REV_([0-9]+))?).*";
        Pattern guidPattern = Pattern.compile(pnpReg);
        Matcher matcher = guidPattern.matcher(pciID.toUpperCase());
        if (matcher.matches()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                System.out.println(i + " " + matcher.group(i));
            }
            DCMHexBinary4 vendorID = new DCMHexBinary4();
            vendorID.setValue(matcher.group(2));
            DCMHexBinary4 deviceID = new DCMHexBinary4();
            deviceID.setValue(matcher.group(3));
            DCMHexBinary4 subVendorID = new DCMHexBinary4();
            subVendorID.setValue(matcher.group(5).substring(0, 4));
            DCMHexBinary4 subDeviceID = new DCMHexBinary4();
            subDeviceID.setValue(matcher.group(5).substring(4));
            return new DCMPCIInfo(vendorID, deviceID, subVendorID, subDeviceID);
        }
        return null;
    }

    /**
     * Construct with vendor id, device id, sub vendor id and sub device id
     *
     * @param inVendorID specifies the vendor id
     * @param inDeviceID specifies the device id
     * @param inSubVendorID specifies the sub vendor id
     * @param inSubDeviceID specifies the sub device id
     * @return parsed object
     */
    public static DCMPCIInfo parse(String inVendorID, String inDeviceID, String inSubVendorID, String inSubDeviceID) {
        DCMHexBinary4 vendorID = new DCMHexBinary4();
        vendorID.setValue(inVendorID);
        DCMHexBinary4 deviceID = new DCMHexBinary4();
        deviceID.setValue(inDeviceID);
        DCMHexBinary4 subVendorID = new DCMHexBinary4();
        subVendorID.setValue(inSubVendorID);
        DCMHexBinary4 subDeviceID = new DCMHexBinary4();
        subDeviceID.setValue(inSubDeviceID);
        return new DCMPCIInfo(vendorID, deviceID, subVendorID, subDeviceID);
    }

    /**
     * Copy Constructor
     *
     * @param inInfo specifies
     */
    public DCMPCIInfo(DCMPCIInfo inInfo) {
        if (inInfo.mVendorID != null) {
            mVendorID = new DCMHexBinary4(inInfo.mVendorID);
        }
        if (inInfo.mDeviceID != null) {
            mDeviceID = new DCMHexBinary4(inInfo.mDeviceID);
        }
        if (inInfo.mSubVendorID != null) {
            mSubVendorID = new DCMHexBinary4(inInfo.mSubVendorID);
        }
        if (inInfo.mSubDeviceID != null) {
            mSubDeviceID = new DCMHexBinary4(inInfo.mSubDeviceID);
        }
    }

    /**
     * Method for getting information as string
     *
     * @return the string
     */
    public String getInfo() {
        String retVal = new String();
        if (null != mVendorID) {
            retVal += mVendorID.getValue();
        }
        if (null != mDeviceID) {
            retVal += mDeviceID.getValue();
        }
        if (null != mSubVendorID) {
            retVal += mSubVendorID.getValue();
        }
        if (null != mSubDeviceID) {
            retVal += mSubDeviceID.getValue();
        }
        if (retVal.isEmpty()) {
            return null;
        }
        return retVal;
    }

    /**
     * Method for getting vendor ID information as string
     *
     * @return the string
     */
    public String getVendorID() {
        return mVendorID.getValue();
    }

    /**
     * Method for getting device ID information as string
     *
     * @return the string
     */
    public String getDeviceID() {
        return mDeviceID.getValue();
    }

    /**
     * Method for getting Sub vendor ID information as string
     *
     * @return the string
     */
    public String getSubVendorID() {
        return mSubVendorID.getValue();
    }

    /**
     * Method for getting Sub Device ID information as string
     *
     * @return the string
     */
    public String getSubDeviceID() {
        return mSubDeviceID.getValue();
    }

    private DCMHexBinary4 mVendorID;
    private DCMHexBinary4 mDeviceID;
    private DCMHexBinary4 mSubVendorID;
    private DCMHexBinary4 mSubDeviceID;
}
