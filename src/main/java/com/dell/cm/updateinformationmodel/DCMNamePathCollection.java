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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Ujjwal_Kumar_Gupta
 */
public class DCMNamePathCollection {

    /**
     * Constructor
     */
    public DCMNamePathCollection() {
        mGroupManifestNamePathMap = new HashMap<>();
    }

    /**
     * Method to add create Name Path Collection from Index Catalog
     *
     * @param inFile Index Catalog in xml format
     * @return SUCCESS code on successful creation else FAILURE code
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException SAXException
     * @throws IOException IOException
     */
    public int createNamePathCollection(File inFile) throws ParserConfigurationException, SAXException, IOException {
        if (inFile == null) {
            return DCMErrorCodes.FAILURE;
        }
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inFile);
            doc.getDocumentElement().normalize();
            Element documentRoot = doc.getDocumentElement();
            LOG.log(Level.FINER, "RootElement: " + doc.getDocumentElement().getNodeName());
            NodeList groupManifestList = documentRoot.getElementsByTagName(DCMConstants.GROUP_MANIFEST);
            if ((groupManifestList != null) && (groupManifestList.getLength() > 0)) {
                for (int i = 0; i < groupManifestList.getLength(); i++) {
                    Node groupManifestNode = groupManifestList.item(i);
                    Element groupManifestElement = (Element) groupManifestNode;
                    String displayGroupManifest = groupManifestElement.getElementsByTagName(DCMConstants.DISPLAY).item(0).getTextContent();
                    LOG.log(Level.FINER, "GroupManifest Element: " + groupManifestElement.getNodeName());
                    NodeList manifestInfoList = groupManifestElement.getElementsByTagName(DCMConstants.MANIFEST_INFORMATION);
                    HashMap<String, String> tempMap = new HashMap<String, String>();
                    if ((manifestInfoList.getLength() > 0) && (manifestInfoList != null)) {
                        for (int j = 0; j < manifestInfoList.getLength(); j++) {
                            Node manifestInfoNode = manifestInfoList.item(j);
                            Element manifestInfoElement = (Element) manifestInfoNode;
                            LOG.log(Level.FINER, "ManifestInformation Element: " + manifestInfoElement.getNodeName());
                            String path = manifestInfoElement.getAttribute(DCMConstants.PATH_ATTRIBUTE);
                            String displayManifestInfo = manifestInfoElement.getElementsByTagName(DCMConstants.DISPLAY).item(0).getTextContent();
                            tempMap.put(displayManifestInfo, path);
                        }
                    } else {
                        return DCMErrorCodes.FAILURE;
                    }
                    mGroupManifestNamePathMap.put(displayGroupManifest, tempMap);
                }
            } else {
                return DCMErrorCodes.FAILURE;
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method to get GroupManifest NamePathmap
     *
     * @return groupManifest NamePathmap
     */
    public HashMap<String, HashMap<String, String>> getGroupManifestNamePathMap() {
        return mGroupManifestNamePathMap;
    }

    /**
     * Method to set GroupManifest Name Path Map
     *
     * @param mGroupManifestNamePathMap GroupManifest NamePath Map to be set
     * @return SUCCESSS code if set else FAILURE code
     */
    public int setGroupManifestNamePathMap(HashMap<String, HashMap<String, String>> mGroupManifestNamePathMap) {
        this.mGroupManifestNamePathMap = mGroupManifestNamePathMap;
        return DCMErrorCodes.SUCCESS;
    }

    private static final Logger LOG = Logger.getLogger(DCMNamePathCollection.class.getName());
    private HashMap<String, HashMap<String, String>> mGroupManifestNamePathMap = new HashMap<String, HashMap<String, String>>();

}
