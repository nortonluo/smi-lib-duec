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

import com.dell.cm.updateinformationmodel.DCMComparisonResultType;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMVersionComparison;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.util.Collection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Shabu_VC
 */
public class DCMComparisonRule implements IDCMValidationRule {

    public static IDCMValidationRule create(Element inRule) {

        if (null == inRule) {
            return null;
        }
        DCMComparisonRule rule = new DCMComparisonRule();
        rule.setAttributeName(inRule.getAttribute(DCMConstants.ATTRIBUTENAME));
        rule.setFormat(inRule.getAttribute(DCMConstants.FORMAT));
        rule.setFormatPartForComparison(inRule.getAttribute(DCMConstants.FORMATPARTFORCOMPARISON));
        rule.setOperator(DCMComparisonOperatorType.fromValue(inRule.getAttribute(DCMConstants.OPERATOR)));
        return rule;
    }

    /**
     * Gets the value of the attributeName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Sets the value of the attributeName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setAttributeName(String value) {
        this.attributeName = value;
    }

    /**
     * Gets the value of the format property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFormat(String value) {
        this.format = value;
    }

    /**
     * Gets the value of the operator property.
     *
     * @return possible object is {@link ComparisonOperatorType }
     *
     */
    public DCMComparisonOperatorType getOperator() {
        return operator;
    }

    /**
     * Sets the value of the operator property.
     *
     * @param value allowed object is {@link ComparisonOperatorType }
     *
     */
    public void setOperator(DCMComparisonOperatorType value) {
        this.operator = value;
    }

    /**
     * Gets the value of the formatPartForComparison property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFormatPartForComparison() {
        return formatPartForComparison;
    }

    /**
     * Sets the value of the formatPartForComparison property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFormatPartForComparison(String value) {
        this.formatPartForComparison = value;
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
        String compVersion = ruleData.mPackageVersion;
        if (attributeName != null && attributeName.toUpperCase().contains("dell")) {
            compVersion = ruleData.mDellVersion;
        }

        if (compVersion == null) {
            return false;
        }
        if (formatPartForComparison == null || formatPartForComparison.isEmpty()) {
            /* TODO
             DCMVersionComparison versionComparison = new DCMVersionComparison(compVersion, ruleData.mVersionInfo.getVersion());
             //if the vendor version is greater than the source version then this update package is applicable else return null
             DCMComparisonResultType result = versionComparison.compare();
             if(result.equals(DCMComparisonResultType.EQUAL) && (operator.equals(DCMComparisonOperatorType.EQUAL)))
             return true;
             if(result.equals(DCMComparisonResultType.GREATER) && (operator.equals(DCMComparisonOperatorType.GREATER_THAN)))
             return true;
             */
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
        Element crule = document.createElement(DCMConstants.COMPARISONRULE);
        crule.setAttribute(DCMConstants.ATTRIBUTENAME, attributeName);
        crule.setAttribute(DCMConstants.FORMAT, format);
        crule.setAttribute(DCMConstants.FORMATPARTFORCOMPARISON, formatPartForComparison);
        crule.setAttribute(DCMConstants.OPERATOR, operator.value());
        return crule;
    }

    protected String attributeName;
    protected String format;
    protected DCMComparisonOperatorType operator;
    protected String formatPartForComparison;

}
