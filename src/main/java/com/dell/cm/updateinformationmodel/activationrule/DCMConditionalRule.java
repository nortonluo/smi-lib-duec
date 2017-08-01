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
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Operating System Type enumeration
 *
 * @author Raveendra_Madala
 */
public class DCMConditionalRule implements IDCMValidationRule {

    public enum DCMConditionalRuleType {

        /**
         * Enumeration for equal to operation
         *
         */
        AND("AND"),
        /**
         * Enumeration for not equal to operation
         *
         */
        OR("OR"),
        /**
         * Enumeration for Greater Than operation
         *
         */
        NOT("NOT");

        private final String value;

        DCMConditionalRuleType(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static DCMConditionalRuleType fromValue(String v) {
            for (DCMConditionalRuleType c : DCMConditionalRuleType.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }

    }

    public static DCMConditionalRule create(Element inRule) {
        try {
            DCMConditionalRuleType type = DCMConditionalRuleType.fromValue(inRule.getTagName());
            DCMConditionalRule rule = new DCMConditionalRule(type);
            NodeList childNodeList = inRule.getChildNodes();
            if ((null != childNodeList) && (childNodeList.getLength() > 0)) {
                for (int childIndex = 0; childIndex < childNodeList.getLength(); ++childIndex) {
                    if (childNodeList.item(childIndex).getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    Element inChildRule = (Element) childNodeList.item(childIndex);
                    try {
                        DCMActionRule action = DCMActionRule.fromValue(inChildRule.getTagName());
                        rule.getRuleActions().add(action);
                        continue;
                    } catch (IllegalArgumentException ex) {
                    }
                    IDCMValidationRule validationRule = null;
                    if (inChildRule.getTagName().equals(DCMConstants.COMPARISONRULE)) {
                        validationRule = DCMComparisonRule.create(inChildRule);
                    } else if (inChildRule.getTagName().equals(DCMConstants.VERSIONRULE)) {
                        validationRule = DCMVersionRule.create(inChildRule);
                    } else if (inChildRule.getTagName().equals(DCMConstants.COMPONENTRULE)) {
                        validationRule = DCMComponentRule.create(inChildRule);
                    } else if (inChildRule.getTagName().equals(DCMConstants.CONTEXTLABEL)) {
                        validationRule = DCMContextLabel.create(inChildRule);
                    } else if (inChildRule.getTagName().equals(DCMConstants.OSRULE)) {
                        validationRule = DCMOSRule.create(inChildRule);
                    } else if (inChildRule.getTagName().equals(DCMConstants.SYSTEMRULE)) {
                        validationRule = DCMSystemRule.create(inChildRule);
                    } else {
                        validationRule = DCMConditionalRule.create(inChildRule);
                    }

                    if (validationRule != null) {
                        rule.getRuleElement().add(validationRule);
                    }
                }
            }

            return rule;
        } catch (IllegalArgumentException ex) {
            return null;
        }
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
        if (ruleType.equals(DCMConditionalRuleType.AND)) {
            for (IDCMValidationRule rule : getRuleElement()) {
                if (rule.checkRule(ruleData) == false) {
                    return false;
                }
            }
            return true;
        } else if (ruleType.equals(DCMConditionalRuleType.OR)) {
            for (IDCMValidationRule rule : getRuleElement()) {
                if (rule.checkRule(ruleData) == true) {
                    return true;
                }
            }
            return false;
        } else if (ruleType.equals(DCMConditionalRuleType.NOT)) {
            for (IDCMValidationRule rule : getRuleElement()) {
                return !(rule.checkRule(ruleData));
            }
        }
        return false;

    }

    public DCMConditionalRule(DCMConditionalRuleType type) {
        ruleType = type;
    }

    public List<IDCMValidationRule> getRuleElement() {
        if (ruleElements == null) {
            ruleElements = new ArrayList<>();
        }
        return this.ruleElements;
    }

    public List<DCMActionRule> getRuleActions() {
        if (ruleActions == null) {
            ruleActions = new ArrayList<>();
        }
        if (ruleActions.isEmpty() && ruleType.equals(DCMConditionalRuleType.NOT)) {
            DCMConditionalRule childRule = (DCMConditionalRule) ruleElements.get(0);
            if (childRule != null) {
                return childRule.getRuleActions();
            }
        }
        return this.ruleActions;
    }

    /**
     * Get the object as XML Node.
     *
     * @param document document root element
     * @return Node created
     */
    @Override
    public Node toXML(Document document) {
        Element crule = document.createElement(ruleType.value());
        for (IDCMValidationRule rule : getRuleElement()) {
            crule.appendChild(rule.toXML(document));
        }
        for (DCMActionRule rule : getRuleActions()) {
            crule.appendChild(rule.toXML(document));
        }
        return crule;
    }

    List<IDCMValidationRule> ruleElements;
    List<DCMActionRule> ruleActions;
    DCMConditionalRuleType ruleType;
}
