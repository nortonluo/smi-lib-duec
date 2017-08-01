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
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMSystemInstance;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import com.dell.cm.updateinformationmodel.DCMUpdateableComponent;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.util.Collection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 *
 */
public class DCMContextLabel implements IDCMValidationRule {

    public static IDCMValidationRule create(Element inRule) {
        DCMContextLabel rule = new DCMContextLabel();
        rule.setLabel(inRule.getAttribute(DCMConstants.LABEL));
        return rule;
    }

    /**
     * Gets the value of the label property.
     *
     * @return 
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the value of the label property.
     *
     * @param value
     */
    public void setLabel(String value) {
        label = value;
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
        if (label != null && ruleData.mVersionInfo.getContext() != null) {
            return ruleData.mVersionInfo.getContext().toLowerCase().contains(label.toLowerCase());
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
        Element crule = document.createElement(DCMConstants.CONTEXTLABEL);
        crule.setAttribute(DCMConstants.LABEL, label);
        return crule;
    }
    
    protected String label;

}
