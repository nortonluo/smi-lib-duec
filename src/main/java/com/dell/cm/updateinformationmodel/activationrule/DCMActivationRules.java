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
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Shabu_VC
 */
public class DCMActivationRules {

    public static DCMActivationRules create(Element inActivationRule) {
        if (null == inActivationRule || !inActivationRule.hasChildNodes()) {
            return null;
        }
        DCMActivationRules activationRule = new DCMActivationRules();

        NodeList childNodeList = inActivationRule.getElementsByTagName(DCMConstants.RULE);
        if ((null != childNodeList) && (childNodeList.getLength() > 0)) {
            for (int childIndex = 0; childIndex < childNodeList.getLength(); ++childIndex) {
                if (childNodeList.item(childIndex).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element inRule = (Element) childNodeList.item(childIndex);
                DCMRule rule = DCMRule.create(inRule);
                if (rule == null) {
                    continue;
                }

                for (int ruleIndex = 0; ruleIndex < inRule.getChildNodes().getLength(); ++ruleIndex) {
                    if (inRule.getChildNodes().item(ruleIndex).getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    Element inNode = (Element) inRule.getChildNodes().item(ruleIndex);
                    DCMConditionalRule condition = DCMConditionalRule.create(inNode);
                    if (condition != null) {
                        rule.getRuleElement().add(condition);
                    }
                }
                activationRule.getRules().add(rule);
            }
        }
        return activationRule;
    }

    /**
     * Get the object as XML Node.
     *
     * @param document document root element
     * @return Node created
     */
    public Node toXML(Document document) {
        Element activationrule = document.createElement(DCMConstants.ACTIVATIONRULES);
        if (null != activationrule) {
            for (DCMRule rule : getRules()) {
                try {
                    activationrule.appendChild(rule.toXML(document));
                } catch (Exception ex) {
                    Logger.getLogger(DCMActivationRules.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return activationrule;
    }

    /**
     * Gets the value of the operatingSystem property.
     *
     * @return possible object is {@link OperatingSystem }
     *
     */
    public List<DCMRule> getRules() {
        if (rules == null) {
            rules = new ArrayList<>();
        }
        return this.rules;
    }

    /**
     * Gets the map of the messages and rules applicable.
     *
     * @param ruleData All Comparison Operation data.
     * @return the rule applicable after valuation
     *
     */
    public Map<DCMMessage, List<DCMActionRule>> getApplicableRules(DCMRuleData ruleData) {
        if (rules == null) {
            return null;
        }
        Map<DCMMessage, List<DCMActionRule>> applicableRules = new HashMap<>();
        for (DCMRule rule : getRules()) {
            List<DCMActionRule> actions = rule.getApplicableRules(ruleData);
            applicableRules.put(rule.getMessage(), actions);

        }
        return applicableRules;
    }

    protected List<DCMRule> rules;

}
