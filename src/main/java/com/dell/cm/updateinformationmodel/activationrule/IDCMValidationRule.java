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

import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.util.Collection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Shabu_VC
 */
public interface IDCMValidationRule {

    /**
     * Checks if this rule is applicable for this comparison.
     *
     * @param ruleData All Comparison Operation data.
     * @return the rule applicable after valuation
     *
     */
    public boolean checkRule(DCMRuleData ruleData);
   /**
     * Get the object as XML Node.
     *
     * @param document document root element
     * @return Node created
     */
    public Node toXML(Document document);

}
