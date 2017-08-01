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
 * PnPInfo class
 *
 * @author Raveendra_Madala
 */
public class DCMPnPInfo {

    /**
     * Constructor
     *
     * @param inACPIID specifies ACPIID
     * @param inProductID specifies product id
     */
    public DCMPnPInfo(DCMACPIID inACPIID, DCMPnPProductID inProductID) {
        mACPIID = new DCMACPIID(inACPIID);
        mPnPProductID = new DCMPnPProductID(inProductID);
    }

    /**
     * Constructor
     *
     * @param inPnPID specifies PnPID
     * @param inProductID specifies product id
     */
    public DCMPnPInfo(DCMPnPID inPnPID, DCMPnPProductID inProductID) {
        mPnPID = new DCMPnPID(inPnPID);
        mPnPProductID = new DCMPnPProductID(inProductID);
    }

    /**
     * Copy constructor
     *
     * @param inInfo specifies the object from which this object is to be
     * constructed
     */
    public DCMPnPInfo(DCMPnPInfo inInfo) {
        if (inInfo != null) {
            if (inInfo.mACPIID != null) {
                mACPIID = new DCMACPIID(inInfo.mACPIID);
            }
            if (inInfo.mPnPID != null) {
                mPnPID = new DCMPnPID(inInfo.mPnPID);
            }
            if (inInfo.mPnPProductID != null) {
                mPnPProductID = new DCMPnPProductID(inInfo.mPnPProductID);
            }
        }
    }

    public String getInfo() {
        if (mPnPProductID != null) {
            if (null != mPnPID) {
                return mPnPID.getID() + mPnPProductID.getID();
            }
            if (null != mACPIID) {
                return mACPIID.getID() + mPnPProductID.getID();
            }
        }
        return null;
    }

    private DCMACPIID mACPIID;
    private DCMPnPID mPnPID;
    private DCMPnPProductID mPnPProductID;
}
