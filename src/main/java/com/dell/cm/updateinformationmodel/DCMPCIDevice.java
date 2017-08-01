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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Class for representing the PCI device
 *
 * @author Raveendra_Madala
 */
public class DCMPCIDevice extends DCMBaseTarget {

    /**
     * Constructor
     *
     * @param inInfo specifies the PCI information for this device
     */
    public DCMPCIDevice(DCMPCIInfo inInfo) {
        mPCIInfo = new DCMPCIInfo(inInfo);
        mType = DCMTargetEntity.PCI;
        mComponentID = DCMConstants.INVALID_COMPONENT_ID;
    }

    /**
     * Copy Constructor
     *
     * @param inDevice specifies the object from which this object is to be
     * constructed
     */
    public DCMPCIDevice(DCMPCIDevice inDevice) {
        super(inDevice);
        if (inDevice != null) {
            mPCIInfo = new DCMPCIInfo(inDevice.mPCIInfo);
            mComponentID = inDevice.mComponentID;
            if (inDevice.mPayloadConfiguration != null) {
                mPayloadConfiguration = new DCMPayloadConfiguration(inDevice.mPayloadConfiguration);
            }
        } else {
            mPCIInfo = new DCMPCIInfo(new DCMHexBinary4(), new DCMHexBinary4());
        }
    }

    
    /**
     *
     * @return newly created object
     */
    @Override
    public DCMBaseTarget Copy() {
        return new DCMPCIDevice(this);
    }

    /**
     * Method for getting the PCI information
     *
     * @return PCI information
     */
    public DCMPCIInfo getPCIInfo() {
        return mPCIInfo;
    }

    /**
     * Method for getting the component id
     *
     * @return the component id
     */
    public long getComponentID() {
        return mComponentID;
    }

    /**
     * Method for setting the component id
     *
     * @param inID specifies the component id being set
     * @return the component id
     */
    public int setComponentID(long inID) {
        mComponentID = inID;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the unique identifier
     *
     * @return the unique identifier
     */
    @Override
    public String getUniqueIdentifier() {
        String retVal = new String();
        retVal += mPCIInfo.getInfo();
        return retVal;
    }

    /**
     * Method for getting the payload configuration
     *
     * @return the payload configuration
     */
    public DCMPayloadConfiguration getPayloadConfiguration() {
        return mPayloadConfiguration;
    }

    /**
     * Method for setting the payload configuration
     *
     * @param inConfiguration specifies the configuration to be set
     * @return SUCCESS if the configuration could be set else appropriate error
     * code is returned
     */
    public int setPayloadConfiguration(DCMPayloadConfiguration inConfiguration) {
        mPayloadConfiguration = new DCMPayloadConfiguration(inConfiguration);
        return DCMErrorCodes.SUCCESS;
    }

    @Override
    public Node toXML(Document document) {
        Element device = (Element)super.toXML(document);
        
        device.setAttribute(DCMConstants.COMPONENT_ID_ATTRIBUTE, Long.toString(mComponentID));
        
        Element pci = document.createElement(DCMConstants.PCI_INFO);
        pci.setAttribute(DCMConstants.DEVICE_ID_ATTRIBUTE, mPCIInfo.getDeviceID());
        pci.setAttribute(DCMConstants.VENDOR_ID_ATTRIBUTE, mPCIInfo.getVendorID());
        pci.setAttribute(DCMConstants.SUB_DEVICE_ID_ATTRIBUTE, mPCIInfo.getSubDeviceID());
        pci.setAttribute(DCMConstants.SUB_VENDOR_ID_ATTRIBUTE, mPCIInfo.getSubVendorID());
        device.appendChild(pci);
        
        return device;
    }
    private final DCMPCIInfo mPCIInfo;
    private long mComponentID;
    private DCMPayloadConfiguration mPayloadConfiguration;
}
