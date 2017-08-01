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
 * Class for representing System
 *
 * @author Raveendra_Madala
 */
public class DCMSystem {

    /**
     * Default constructor
     */
    public DCMSystem() {
        mIDType = DCMSystemIDType.BIOS;
        mLOBKey = DCMConstants.LOB_SERVER;
    }

    /**
     * Copy Constructor
     *
     * @param inSystem specifies the object from which this object is to be
     * constructed
     */
    public DCMSystem(DCMSystem inSystem) {
        if (inSystem != null) {
            if (inSystem.mName != null) {
                mName = new DCMI18NString(inSystem.mName);
            }
            if (inSystem.mID != null) {
                mID = new DCMHexBinary4(inSystem.mID);
            }
            mIDType = inSystem.mIDType;
            mLOBKey = inSystem.mLOBKey;
        }
    }

    /**
     * Method for getting the system name
     *
     * @return the system name
     */
    public DCMI18NString getName() {
        return mName;
    }

    /**
     * Method for setting the system name
     *
     * @param inName specifies the name being set
     * @return SUCCESS if the name could be set else appropriate error code is
     * returned.
     */
    public int setName(DCMI18NString inName) {
        mName = new DCMI18NString(inName);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the system id
     *
     * @return system id
     */
    public String getID() {
        return mID.getValue();
    }

    /**
     * Method for setting the system id
     *
     * @param inID specifies the id being set
     * @return SUCCESS if the id could be set else appropriate error code is
     * returned.
     */
    public int setID(String inID) {
        if (null == mID) {
            mID = new DCMHexBinary4();
        }
        return mID.setValue(inID);
    }

    /**
     * Method for getting the system id type
     *
     * @return system id type
     */
    public DCMSystemIDType getIDType() {
        return mIDType;
    }

    /**
     * Method for setting the system id type
     *
     * @param inIDType specifies the id type being set
     * @return SUCCESS if the id type could be set else appropriate error code
     * is returned.
     */
    public int setIDType(DCMSystemIDType inIDType) {
        mIDType = inIDType;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the system's lob key
     *
     * @return the system's lob key
     */
    public int getLOBKey() {
        return mLOBKey;
    }

    /**
     * Method for setting the system's lob key
     *
     * @param inKey specifies the key being set
     * @return SUCCESS if the key could be set else appropriate error code is
     * returned
     */
    public int setLOBKey(int inKey) {
        mLOBKey = inKey;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the unique identifier
     *
     * @return the unique identifier for the system
     */
    public String getUniqueIdentifier() {
        String retVal = new String();
        retVal = retVal.concat(Integer.toString(mLOBKey));
        switch (mIDType) {
            case MULTISYSTEMCHASSIS:
                retVal = retVal.concat("CHASSIS");
                break;
            case RAIDCHASSIS:
                retVal = retVal.concat("RAIDCHASSIS");
                break;
            case NETWORKSWITCH:
                retVal = retVal.concat("NETWORKSWITCH");
                break;
            case ROUTER:
                retVal = retVal.concat("ROUTER");
                break;
            case BIOS:
            default:
                retVal = retVal.concat("BIOS");
                break;
        }
        retVal = retVal.concat(mID.getValue());
        return retVal;
    }

    public Node toXML(Document document) {
        Element model = document.createElement(DCMConstants.MODEL);
        if (null != mID) {
            model.setAttribute(DCMConstants.SYSTEM_ID_ATTRIBUTE, mID.getValue());
        }
        if (mName != null) {
            model.appendChild(mName.toXML(document));
        }
        if (mIDType != null) {
            model.setAttribute(DCMConstants.SYSTEM_ID_TYPE_ATTRIBUTE, mIDType.name());
        }
        return model;
    }

    private DCMI18NString mName;
    private DCMHexBinary4 mID;
    private DCMSystemIDType mIDType;
    private int mLOBKey;
}
