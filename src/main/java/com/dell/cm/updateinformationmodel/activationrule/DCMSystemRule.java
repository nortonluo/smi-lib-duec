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

import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMI18NString;
import com.dell.cm.updateinformationmodel.DCMLineOfBusiness;
import com.dell.cm.updateinformationmodel.DCMLineOfBusinessCollection;
import com.dell.cm.updateinformationmodel.DCMSystem;
import com.dell.cm.updateinformationmodel.DCMSystemIDType;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 *
 *
 */
public class DCMSystemRule implements IDCMValidationRule {

    public static IDCMValidationRule create(Element inRule) {
        DCMSystemRule rule = new DCMSystemRule();

        if (null == inRule || !inRule.hasChildNodes()) {
            return null;
        }
        NodeList childNodeList = inRule.getElementsByTagName(DCMConstants.BRAND);
        if ((null != childNodeList) && (childNodeList.getLength() > 0)) {

            for (int childIndex = 0; childIndex < childNodeList.getLength(); ++childIndex) {
                if (childNodeList.item(childIndex).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element inLOB = (Element) childNodeList.item(childIndex);

                DCMLineOfBusiness lob = DCMLineOfBusiness.create(inLOB);
                if (lob != null) {
                    rule.getLobs().addLineOfBusiness(lob);
                }
                // key
                String key = inLOB.getAttribute(DCMConstants.KEY_ATTRIBUTE);
                if (key.isEmpty()) {
                    continue;
                }
                int inLOBKey = Integer.parseInt(key);
                NodeList sysNodeList = inRule.getElementsByTagName(DCMConstants.MODEL);
                for (int sysIndex = 0; sysIndex < sysNodeList.getLength(); ++sysIndex) {
                    if (sysNodeList.item(childIndex).getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    Element inSystem = (Element) sysNodeList.item(sysIndex);
                    // systemID
                    String systemID = inSystem.getAttribute(DCMConstants.SYSTEM_ID_ATTRIBUTE);
                    if (systemID.isEmpty()) {
                        continue;
                    }
                    // systemIDType
                    String systemIDType = inSystem.getAttribute(DCMConstants.SYSTEM_ID_TYPE_ATTRIBUTE);
                    DCMSystem system = new DCMSystem();
                    system.setID(systemID);
                    system.setIDType(DCMSystemIDType.getEnumeration(systemIDType));
                    system.setLOBKey(inLOBKey);
                    DCMI18NString display = DCMI18NString.create(inSystem);
                    if (display != null) {
                        system.setName(display);
                    }
                    rule.getBrand().add(system);
                }
            }
        }
        return rule;
    }

    /**
     * Gets the value of the brand property.
     *
     * @return possible object is {@link Brand }
     *
     */
    public List<DCMSystem> getBrand() {
        if (brand == null) {
            brand = new ArrayList<>();
        }
        return this.brand;
    }

    /**
     * Gets the value of the brand property.
     *
     * @return possible object is {@link Brand }
     *
     */
    public DCMLineOfBusinessCollection getLobs() {
        if (lobs == null) {
            lobs = new DCMLineOfBusinessCollection();
        }
        return this.lobs;
    }

    /**
     * Checks if this rule is applicable for this comparison.
     *
     * @param ruleData All Comparison Operation data.
     *
     * @return the boolean
     *
     */
    @Override
    public boolean checkRule(DCMRuleData ruleData) {
        for (DCMSystem system : getBrand()) {
            if (system.getUniqueIdentifier().equals(ruleData.mSystemTypeIdentifier)) {
                return true;
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
        Element crule = document.createElement(DCMConstants.SYSTEMRULE);
        Map<String, Element> mapLOB = new HashMap<>();
        for (DCMSystem sys : brand) {
            Node system = sys.toXML(document);
            Element lob = null;
            String key = Long.toString(sys.getLOBKey());
            if (mapLOB.containsKey(key)) {
                lob = mapLOB.get(key);
            } else {
                DCMLineOfBusiness lobentry = lobs.getLineOfBusiness(sys.getLOBKey());
                if (lobentry != null) {
                    lob = (Element) lobentry.toXML(document);
                }
                if (lob == null) {
                    lob = document.createElement(DCMConstants.BRAND);
                    lob.setAttribute(DCMConstants.KEY_ATTRIBUTE, key);
                }
                mapLOB.put(key, lob);
                crule.appendChild(lob);
            }
            lob.appendChild(system);
        }
        return crule;
    }

    protected List<DCMSystem> brand;
    protected DCMLineOfBusinessCollection lobs;

}
