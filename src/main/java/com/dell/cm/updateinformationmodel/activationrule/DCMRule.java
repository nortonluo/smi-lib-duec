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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Defines the rule for activating the updated software.
 *
 */
public class DCMRule {

    public static DCMRule create(Element inRule) {
        if (null == inRule || !inRule.hasChildNodes()) {
            return null;
        }
        String xl = inRule.toString();
        NodeList msgNodeList = inRule.getElementsByTagName(DCMConstants.MESSAGE);
        if ((null == msgNodeList) || (msgNodeList.getLength() == 0)) {
            return null;
        }
        DCMRule rule = new DCMRule();
        for (int childIndex = 0; childIndex < msgNodeList.getLength(); ++childIndex) {
            if (msgNodeList.item(childIndex).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element inMessage = (Element) msgNodeList.item(childIndex);
            DCMMessage message = DCMMessage.create(inMessage);
            if (message != null) {
                rule.setMessage(message);
                break;
            }
        }

        rule.setIndex(Long.parseLong(inRule.getAttribute(DCMConstants.INDEX)));
        rule.setName(inRule.getAttribute(DCMConstants.NAME_ATTRIBUTE));

        return rule;
    }

    /**
     * Checks if this rule is applicable for this comparison.
     *
     * @param ruleData All Comparison Operation data.
     * @return the rule applicable after valuation
     *
     */
    public List<DCMActionRule> getApplicableRules(DCMRuleData ruleData) {
        List<DCMActionRule> actions = new ArrayList<>();
        if (ruleElement == null) {
            return null;
        }
        for (DCMConditionalRule rule : getRuleElement()) {
            if (rule.checkRule(ruleData)) {
                actions.addAll(rule.getRuleActions());
            }
        }
        return actions;
    }

    /**
     * The elements constituting the rule.Gets the value of the ruleElement
     * property.
     *
     *
     *
     * @return
     */
    public List<DCMConditionalRule> getRuleElement() {
        if (ruleElement == null) {
            ruleElement = new ArrayList<>();
        }
        return this.ruleElement;
    }

    /**
     * Gets the value of the message property.
     *
     * @return possible object is {@link Message }
     *
     */
    public DCMMessage getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     *
     * @param value allowed object is {@link Message }
     *
     */
    public void setMessage(DCMMessage value) {
        this.message = value;
    }

    /**
     * Gets the value of the index property.
     *
     * @return possible object is {@link BigInteger }
     *
     */
    public Long getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     *
     * @param value allowed object is {@link BigInteger }
     *
     */
    public void setIndex(Long value) {
        this.index = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Get the object as XML Node.
     *
     * @param document document root element
     * @return Node created
     */

    public Node toXML(Document document) {
        Element arule = document.createElement(DCMConstants.RULE);
        for (DCMConditionalRule rule : getRuleElement()) {
            arule.appendChild(rule.toXML(document));
        }
        arule.appendChild(message.toXML(document));
        arule.setAttribute(DCMConstants.NAME_ATTRIBUTE, name);
        arule.setAttribute(DCMConstants.INDEX, index.toString());

        return arule;
    }
    /**
     *
     */
    protected List<DCMConditionalRule> ruleElement;
    protected DCMMessage message;
    protected Long index;
    protected String name;

}
