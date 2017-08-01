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
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class. *
 *
 */
public class DCMVersionRule implements IDCMValidationRule {
    
    public static IDCMValidationRule create(Element inRule) {
        DCMVersionRule rule = new DCMVersionRule();
        rule.setVersion(inRule.getAttribute(DCMConstants.VERSION_ATTRIBUTE));
        return rule;
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
    * @return the boolean
     *
     */
    @Override
    public boolean checkRule(DCMRuleData ruleData) {
        DCMVersionComparison versionComparison = new DCMVersionComparison(version, ruleData.mVersionInfo.getVersion());
        //if the vendor version is greater than the source version then this update package is applicable else return null
        return (versionComparison.compare().equals(DCMComparisonResultType.EQUAL));
    }

    /**
     * Get the object as XML Node.
     *
     * @param document document root element
     * @return Node created
     */
    @Override
    public Node toXML(Document document) {
        Element crule = document.createElement(DCMConstants.VERSIONRULE);
        crule.setAttribute(DCMConstants.VERSION_ATTRIBUTE, version);
        return crule;
    }
    
    protected String version;
    
}
