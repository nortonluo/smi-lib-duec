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
 * Class for representing a hardware device that is neither a PCI device nor a
 * PnP device
 *
 * @author Raveendra_Madala
 */
public class DCMNonPCIPnPHardware extends DCMBaseTarget {

    /**
     * Constructor
     *
     * @param inID specifies the component ID
     */
    public DCMNonPCIPnPHardware(long inID) {
        mComponentID = inID;
        mType = DCMTargetEntity.HARDWARE;
    }

    public DCMNonPCIPnPHardware(DCMNonPCIPnPHardware inHardware) {
        super(inHardware);
        mType = DCMTargetEntity.HARDWARE;
        if (inHardware != null) {
            mComponentID = inHardware.mComponentID;
        } else {
            mComponentID = -1;
        }
    }

    /**
     *
     * @return newly created object
     */
    @Override
    public DCMBaseTarget Copy() {
        return new DCMNonPCIPnPHardware(this);
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
     * Method for getting the unique identifier
     *
     * @return the unique identifier
     */
    @Override
    public String getUniqueIdentifier() {
        String retVal = new String();
        retVal = retVal.concat(Long.toString(mComponentID));
        return retVal;
    }

    @Override
    public Node toXML(Document document) {
        Element device = (Element) super.toXML(document);

        device.setAttribute(DCMConstants.COMPONENT_ID_ATTRIBUTE, Long.toString(mComponentID));

        return device;
    }

    private final long mComponentID;
}
