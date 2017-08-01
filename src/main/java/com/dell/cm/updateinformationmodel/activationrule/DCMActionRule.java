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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Operating System Type enumeration
 *
 * @author Raveendra_Madala
 */
public enum DCMActionRule {

    /**
     * client
     */
    TrueRule(DCMConstants.TRUERULE),
    /**
     * server
     */
    FalseRule(DCMConstants.FALSERULE),
    /**
     * Controller
     */
    RebootRequiredRule(DCMConstants.REBOOTREQUIREDRULE),
    /**
     * Switch
     */
    ContainerPowerCycleRequiredRule(DCMConstants.CONTAINERPOWERCYCLEREQUIREDRULE);

    private final String value;

    DCMActionRule(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DCMActionRule fromValue(String v) {
        for (DCMActionRule c : DCMActionRule.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    /**
     * Get the object as XML Node.
     *
     * @param document document root element
     * @return Node created
     */
    public Node toXML(Document document) {
        return document.createElement(value());
    }
}
