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
package com.dell.sm.wsman.utility;

import java.util.Iterator;
import java.util.logging.Level;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

import java.util.logging.Logger;

public class PersonalNamespaceContext implements NamespaceContext {

    private String strNameSpace;
    private static final Logger logger = Logger.getLogger(PersonalNamespaceContext.class.getName());

    public PersonalNamespaceContext(String strNS) {
        super();
        logger.log(Level.INFO, "Entering constructor: PersonalNamespaceContext(String strNS - %s)");
        this.strNameSpace = strNS;
        logger.log(Level.INFO, "Exiting constructor: PersonalNamespaceContext()");
    }

    @Override
    public String getNamespaceURI(String prefix) {
        if (prefix == null) {
            throw new NullPointerException("Null prefix");
        } else if ("wsman".equals(prefix)) {
            return "http://schemas.dmtf.org/wbem/wsman/1/wsman.xsd";
        } else if ("wsa".equals(prefix)) {
            return "http://schemas.xmlsoap.org/ws/2004/08/addressing";
        } else if ("pre".equals(prefix)) {
            return this.strNameSpace;
        } else if ("xml".equals(prefix)) {
            return XMLConstants.XML_NS_URI;
        }
        return XMLConstants.NULL_NS_URI;
    }

    // This method isn't necessary for XPath processing.
    public String getPrefix(String uri) {
        throw new UnsupportedOperationException();
    }

    // This method isn't necessary for XPath processing either.
    public Iterator getPrefixes(String uri) {
        throw new UnsupportedOperationException();
    }

    public String getStrNameSpace() {
        return strNameSpace;
    }

    public void setStrNameSpace(String strNameSpace) {
        this.strNameSpace = strNameSpace;
    }
}
