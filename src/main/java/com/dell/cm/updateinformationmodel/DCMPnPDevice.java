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
 * Class for representing the PnP device
 *
 * @author Raveendra_Madala
 */
public class DCMPnPDevice extends DCMBaseTarget {

    /**
     * Constructor
     *
     * @param inInfo specifies the PnP information for this device
     */
    public DCMPnPDevice(DCMPnPInfo inInfo) {
        mPnPInfo = new DCMPnPInfo(inInfo);
        mType = DCMTargetEntity.PNP;
        mComponentID = DCMConstants.INVALID_COMPONENT_ID;
    }

    /**
     * Copy constructor
     *
     * @param inDevice specifies the object from which this object is to be
     * constructed
     */
    public DCMPnPDevice(DCMPnPDevice inDevice) {
        super(inDevice);
        mType = DCMTargetEntity.PNP;
        if (inDevice != null) {
            if (inDevice.mPnPInfo != null) {
                mPnPInfo = inDevice.mPnPInfo;
            } else {
                mPnPInfo = new DCMPnPInfo(new DCMPnPID(), new DCMPnPProductID());
            }
            mComponentID = inDevice.mComponentID;
        } else {
            mPnPInfo = new DCMPnPInfo(new DCMPnPID(), new DCMPnPProductID());
        }
    }

    /**
     *
     * @return newly created object
     */
    @Override
    public DCMBaseTarget Copy() {
        return new DCMPnPDevice(this);
    }

    /**
     * Method for getting the PnP Information
     *
     * @return PnP Information
     */
    public DCMPnPInfo getPnPInfo() {
        return mPnPInfo;
    }

    /**
     * Method for getting the component ID
     *
     * @return the component ID
     */
    public long getComponentID() {
        return mComponentID;
    }

    /**
     * Method for setting the component ID
     *
     * @param inID specifies the component ID being set
     * @return SUCCESS if the component ID could be set else appropriate error
     * code is returned
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
        retVal += mPnPInfo.getInfo();
        return retVal;
    }

    @Override
    public Node toXML(Document document) {
        Element device = (Element) super.toXML(document);

        device.setAttribute(DCMConstants.COMPONENT_ID_ATTRIBUTE, Long.toString(mComponentID));

        /* TODO
         Element pci = document.createElement(DCMConstants.PNP_INFO);
         pci.setAttribute(DCMConstants.DEVICE_ID_ATTRIBUTE, mPnPInfo
         device.appendChild(pci);
         */
        return device;
    }
    private final DCMPnPInfo mPnPInfo;
    private long mComponentID;
}
