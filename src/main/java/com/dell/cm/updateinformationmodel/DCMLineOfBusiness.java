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
 * Class for representing Line of Business
 *
 * @author Raveendra_Madala
 */
public class DCMLineOfBusiness {

    public static DCMLineOfBusiness create(Element inLOB) {
        if (null == inLOB) {
            return null;
        }
        if (!inLOB.getNodeName().equals(DCMConstants.BRAND)) {
            return null;
        }
        // key
        String key = inLOB.getAttribute(DCMConstants.KEY_ATTRIBUTE);
        if (key.isEmpty()) {
            return null;
        }
        // prefix
        String prefix = inLOB.getAttribute(DCMConstants.PREFIX_ATTRIBUTE);
        if (prefix.isEmpty()) {
            return null;
        }
        DCMI18NString display = DCMI18NString.create(inLOB);

        return new DCMLineOfBusiness(Integer.parseInt(key), prefix, display);
    }

    /**
     * Default constructor
     */
    public DCMLineOfBusiness() {
    }

    /**
     * Constructor
     *
     * @param inKey specifies the key for the line of business
     * @param inPrefix specifies the prefix for the line of business
     * @param inName specifies the name of the line of business
     */
    public DCMLineOfBusiness(int inKey, String inPrefix, DCMI18NString inName) {
        mKey = inKey;
        mPrefix = inPrefix;
        if (inName != null) {
            mName = new DCMI18NString(inName);
        }
    }

    /**
     * Copy Constructor
     *
     * @param inLOB specifies the object from which this object is to be
     * constructed
     */
    public DCMLineOfBusiness(DCMLineOfBusiness inLOB) {
        if (inLOB != null) {
            mKey = inLOB.mKey;
            mPrefix = inLOB.mPrefix;
            if (inLOB.mName != null) {
                mName = new DCMI18NString(inLOB.mName);
            }
        }
    }

    /**
     * Method for getting the key
     *
     * @return key
     */
    public int getKey() {
        return mKey;
    }

    /**
     * Method for setting the key
     *
     * @param inKey specifies the value being set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned
     */
    public int setKey(int inKey) {
        mKey = inKey;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the prefix
     *
     * @return prefix
     */
    public String getPrefix() {
        return mPrefix;
    }

    /**
     * Method for setting prefix
     *
     * @param inPrefix specifies the value being set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned
     */
    public int setPrefix(String inPrefix) {
        mPrefix = inPrefix;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the name
     *
     * @return name
     */
    public DCMI18NString getName() {
        return mName;
    }

    /**
     * Method for setting the name
     *
     * @param inName specifies the name being set
     * @return SUCCESS if the name could be set else appropriate error code is
     * returned
     */
    public int setName(DCMI18NString inName) {
        mName = new DCMI18NString(inName);
        return DCMErrorCodes.SUCCESS;
    }

    public Node toXML(Document document) {
        Element brand = document.createElement(DCMConstants.BRAND);
        if (null == brand) {
            return null;
        }

        String lobKey = Integer.toString(getKey());
        if (null != lobKey) {
            brand.setAttribute(DCMConstants.KEY_ATTRIBUTE, lobKey);
        }

        String lobPrefix = getPrefix();
        if (null != lobPrefix) {
            brand.setAttribute(DCMConstants.PREFIX_ATTRIBUTE, lobPrefix);
        }

        if (mName != null) {
            Node display = mName.toXML(document);
            if (display != null) {
                brand.appendChild(display);
            }
        }
        return brand;
    }
    
    private int mKey;
    private String mPrefix;
    private DCMI18NString mName;
}
