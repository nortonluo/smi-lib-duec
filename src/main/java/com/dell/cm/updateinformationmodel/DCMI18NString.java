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
package com.dell.cm.updateinformationmodel;

import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class for internationalized string
 *
 * @author Raveendra_Madala
 */
public class DCMI18NString {

    public static DCMI18NString create(Element inNode) {
        if (inNode == null) {
            return null;
        }
        NodeList displayNodeList = inNode.getElementsByTagName(DCMConstants.DISPLAY);
        if ((null != displayNodeList) && (displayNodeList.getLength() > 0)) {

            for (int displayIndex = 0; displayIndex < displayNodeList.getLength(); ++displayIndex) {
                // ignore none element nodes and non immediate children
                if (displayNodeList.item(displayIndex).getNodeType() != Node.ELEMENT_NODE
                        || displayNodeList.item(displayIndex).getParentNode() != inNode) {
                    continue;
                }
                DCMI18NString display = new DCMI18NString();
                Element nDisplay = (Element) displayNodeList.item(displayIndex);
                String text = nDisplay.getTextContent().trim();
                display.setDefaultLocaleString(text);
                return display;
            }
            //TODO add support for other languages here  
        }
        return null;
    }

    /**
     * Constructor
     */
    public DCMI18NString() {
        mLocaleStringMap = new HashMap<>();
    }

    /**
     * Copy Constructor
     *
     * @param inI18NString Internationalized string for DCM
     */
    public DCMI18NString(DCMI18NString inI18NString) {
        mLocaleStringMap = new HashMap<>();
        if (inI18NString != null) {
            for (Locale locale : inI18NString.getLocales()) {
                mLocaleStringMap.put(locale, inI18NString.getLocaleString(locale));
            }
        }
    }

    /**
     * Method for getting the default locale string
     *
     * @return the default locale string
     */
    public String getDefaultLocaleString() {
        String retVal = new String();
        if (mLocaleStringMap.containsKey(Locale.ENGLISH)) {
            retVal = mLocaleStringMap.get(Locale.ENGLISH);
        }
        return retVal;
    }

    /**
     * Method for setting the default locale string
     *
     * @param inString specifies the string being set If the string exists it
     * would be overwritten
     */
    public void setDefaultLocaleString(String inString) {
        mLocaleStringMap.put(Locale.ENGLISH, inString);
    }

    /**
     * Method for getting the string for the given locale
     *
     * @param inLocale specifies the locale in which the string is being sought
     * @return the string the in the given locale
     */
    public String getLocaleString(Locale inLocale) {
        String retVal = new String();
        if (mLocaleStringMap.containsKey(inLocale)) {
            retVal = mLocaleStringMap.get(inLocale);
        }
        return retVal;
    }

    /**
     * Method for setting the string in the given locale
     *
     * @param inLocale specifies the locale in which the string is being set
     * @param inString specifies the string being set If the string exists it
     * would be overwritten
     */
    public void setLocaleString(Locale inLocale, String inString) {
        mLocaleStringMap.put(inLocale, inString);
    }

    /**
     * Method for getting all the locales
     *
     * @return the locales
     */
    public Set<Locale> getLocales() {
        return mLocaleStringMap.keySet();
    }

    /**
     * Method for checking the value is empty or not
     *
     * @return true if no value in set
     */
    public boolean isEmpty() {
        return mLocaleStringMap.isEmpty();
    }

    public Node toXML(Document document) {
        Element display = document.createElement(DCMConstants.DISPLAY);
        String displayValue = "";
        if (this.getLocales().contains(Locale.ENGLISH)) {
            displayValue = this.getLocaleString(Locale.ENGLISH);
            display.setAttribute(DCMConstants.LANG_ATTRIBUTE, DCMConstants.EN_LANGUAGE);
            //ToDo for other Locale
        }
        display.appendChild(document.createCDATASection(displayValue));
        return display;
    }
    private final HashMap<Locale, String> mLocaleStringMap;
}
