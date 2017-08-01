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

import com.dell.cm.updateinformationmodel.DCMBaseTarget;
import com.dell.cm.updateinformationmodel.DCMComparisonResultType;
import com.dell.cm.updateinformationmodel.DCMComponentType;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMI18NString;
import com.dell.cm.updateinformationmodel.DCMNonPCIPnPHardware;
import com.dell.cm.updateinformationmodel.DCMVersionComparison;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.util.Collection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 *
 */
public class DCMComponentRule implements IDCMValidationRule {

    public static IDCMValidationRule create(Element inRule) {
        DCMComponentRule rule = new DCMComponentRule();
        rule.setVersion(inRule.getAttribute(DCMConstants.VERSION_ATTRIBUTE));
        if (null == inRule || !inRule.hasChildNodes()) {
            return null;
        }
        NodeList childNodeList = inRule.getElementsByTagName(DCMConstants.DEVICE);
        if ((null != childNodeList) && (childNodeList.getLength() > 0)) {
            for (int childIndex = 0; childIndex < childNodeList.getLength(); ++childIndex) {
                if (childNodeList.item(childIndex).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element inDevice = (Element) childNodeList.item(childIndex);
                DCMNonPCIPnPHardware hardware = new DCMNonPCIPnPHardware(Long.parseLong(inDevice.getAttribute(DCMConstants.COMPONENT_ID_ATTRIBUTE)));
                hardware.setEmbedded(inDevice.getAttribute(DCMConstants.EMBEDDED).equals("1"));
                DCMI18NString display = DCMI18NString.create(inDevice);
                if (display != null) {
                    hardware.setName(display);
                }
                rule.setDevice(hardware);
            }
        }
        childNodeList = inRule.getElementsByTagName(DCMConstants.COMPONENT_TYPE);
        if ((null != childNodeList) && (childNodeList.getLength() > 0)) {
            for (int childIndex = 0; childIndex < childNodeList.getLength(); ++childIndex) {
                if (childNodeList.item(childIndex).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element inDevice = (Element) childNodeList.item(childIndex);
                rule.componentName = DCMI18NString.create(inDevice);
                rule.setComponentType(DCMComponentType.getEnumeration(inDevice.getAttribute(DCMConstants.VALUE_ATTRIBUTE)));
            }
        }
        return rule;
    }

    /**
     * Gets the value of the device property.
     *
     * @return possible object is {@link Device }
     *
     */
    public DCMBaseTarget getDevice() {
        return device;
    }

    /**
     * Sets the value of the device property.
     *
     * @param value allowed object is {@link Device }
     *
     */
    public void setDevice(DCMBaseTarget value) {
        this.device = value;
    }

    /**
     * Gets the value of the componentType property.
     *
     * @return possible object is {@link ComponentType }
     *
     */
    public DCMComponentType getComponentType() {
        return componentType;
    }

    /**
     * Sets the value of the componentType property.
     *
     * @param value allowed object is {@link ComponentType }
     *
     */
    public void setComponentType(DCMComponentType value) {
        this.componentType = value;
    }

    /**
     * Gets the value of the version property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Checks if this rule is applicable for this comparison.
     *
     * @param ruleData All Comparison Operation data.
     * @return the rule applicable after valuation
     *
     */
    @Override
    public boolean checkRule(DCMRuleData ruleData) {
        if (ruleData.mVersionInfoList != null) {
            for (DCMVersionInformation invComp : ruleData.mVersionInfoList) {
                if (invComp.getUpdateableComponentIdentifier().equals(invComp.getUpdateableComponentIdentifier())) {
                    DCMVersionComparison versionComparison = new DCMVersionComparison(version, ruleData.mVersionInfo.getVersion());
                    //if the vendor version is greater than the source version then this update package is applicable else return null
                    if (versionComparison.compare().equals(DCMComparisonResultType.EQUAL)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Get the object as XML Node.
     *
     * @param document document root element
     * @return Node created
     */
    @Override
    public Node toXML(Document document) {
        Element crule = document.createElement(DCMConstants.COMPONENTRULE);
        crule.appendChild(device.toXML(document));
        Element type = document.createElement(DCMConstants.COMPONENT_TYPE);
        type.setAttribute(DCMConstants.VALUE_ATTRIBUTE, DCMComponentType.toString(componentType));
        if (componentName != null) {
            type.appendChild(componentName.toXML(document));
        }
        crule.appendChild(type);
        crule.setAttribute(DCMConstants.VERSION_ATTRIBUTE, version);

        return crule;
    }

    protected String version;
    protected DCMBaseTarget device;
    protected DCMComponentType componentType;
    protected DCMI18NString componentName;
}
