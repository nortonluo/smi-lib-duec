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
 * User displayable message
 *
 * <p>
 * Java class for Message complex type.
 *
 */
public class DCMMessage {

    public static DCMMessage create(Element inMessage) {
        if (null == inMessage) {
            return null;
        }

        DCMMessage message = new DCMMessage();
        message.setId(Long.parseLong(inMessage.getAttribute(DCMConstants.ID_ATTRIBUTE)));
        message.setLanguage(inMessage.getAttribute(DCMConstants.LANGUAGE_2));
        message.setText(inMessage.getAttribute(DCMConstants.TEXT));
        return message;
    }

    /**
     * Gets the value of the text property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the language property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link Long }
     *
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is {@link Long }
     *
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Get the object as XML Node.
     *
     * @param document document root element
     * @return Node created
     */
    public Node toXML(Document document) {
        Element message = document.createElement(DCMConstants.MESSAGE);
        message.setAttribute(DCMConstants.TEXT, text);
        message.setAttribute(DCMConstants.LANGUAGE_2, language);
        message.setAttribute(DCMConstants.ID_ATTRIBUTE, id.toString());

        return message;
    }

    protected String text;
    protected String language;
    protected Long id;

}
