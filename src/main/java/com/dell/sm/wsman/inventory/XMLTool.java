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
package com.dell.sm.wsman.inventory;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.xml.transform.OutputKeys;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

/**
 * Utility class to aid with searching for nodes in a given DOM document.
 * Methods can return either text values or nodes depending on the type of
 * search requested.
 *
 *
 * @author Matthew_G_Stemen
 */
public class XMLTool {

    /**
     * Inner class to store a Node list, exposed as an Interface
     */
    public class XMLToolNodeList implements NodeList {

        private List<Node> nodeList = new ArrayList<Node>();

        @Override
        public Node item(int index) {
            // TODO Auto-generated method stub
            return nodeList.get(index);
        }

        @Override
        public int getLength() {
            // TODO Auto-generated method stub
            return nodeList.size();
        }

        private void add(Node nodeToAdd) {
            nodeList.add(nodeToAdd);
        }
    }

    /**
     * The search scope is used for criteria in matching a node name
     */
    public enum NameScope {

        /**
         * default, search scope is just the node name
         */
        Name,
        /**
         * set the search scope to the local name of the node
         */
        LocalName;
    }

    /**
     * Internal value to determine how to walk nodes, and what conditions will
     * terminate the recursive node walk.
     */
    private enum WalkType {

        FindNode, FindText, PairMatch, PairGroup, ElementName, NameSpaceURI, ElementValue, Leaf;
    }

    private boolean debugFlag = false;
    private boolean stopWalk = false;
    // private List<Node> nodeList = new ArrayList<Node>();
    private XMLToolNodeList nodeList = new XMLToolNodeList();
    private Node targetNode = null;
    private String targetValue = null;
    private String targetName = null;
    private Document sourceDocument = null;

    private NameScope searchScope = NameScope.Name;

    LinkedHashMap<String, String> namespaces = new LinkedHashMap<String, String>();

    /**
     *
     * @param docToLoad specifies the document to be loaded
     */
    public XMLTool(Document docToLoad) {
        this.sourceDocument = docToLoad;
    }

    /**
     *
     * @param newScope to specify the scope of the search
     */
    public void setSearchScope(NameScope newScope) {
        this.searchScope = newScope;
    }

    /**
     *
     * @param name - Node name to search for, name can be local if the scope is
     * set
     * @return NodeList of all Nodes that matched search
     */
    public NodeList getTargetNodes(String name) {
        targetName = name;
        stopWalk = false;
        walk(sourceDocument.getFirstChild(), WalkType.PairGroup);

        return nodeList;
    }

    private void put(String prefix, String uri) {
        this.namespaces.put(prefix, uri);
    }

    /*
     * Recursive walk that will set a targetNode or group of Nodes based on
     * search criteria
     */
    private void walk(Node node, WalkType walkType) {
        int type = node.getNodeType();
        String nameSpaceName = node.getNamespaceURI();
        String prefixName = node.getPrefix();
        String nodeValue = node.getNodeValue();
        String nodeName = node.getNodeName();
        String nodeLocalName = node.getLocalName();
        NamedNodeMap nnm = node.getAttributes();
        this.targetNode = node;

        String searchName = nodeName;

        switch (searchScope) {
            case Name:
                searchName = nodeName;
                break;
            case LocalName:
                if (nodeLocalName != null) {
                    searchName = nodeLocalName;
                }
                break;
        }

        if (debugFlag) {
            System.out.println("------------------------------");
            System.out.println("name: " + nodeName);
            System.out.println("value: " + nodeValue);
            System.out.println("localName: " + nodeLocalName);
            System.out.println("Prefix Name: " + prefixName);
            System.out.println("==============================");
        }

        switch (type) {
            case Node.DOCUMENT_NODE:
                break;
            case Node.ELEMENT_NODE:
                break;

            case Node.ENTITY_REFERENCE_NODE:
                break;

            case Node.CDATA_SECTION_NODE:
                break;

            case Node.TEXT_NODE:
                if (nodeValue == null) // nil case
                {
                    nodeValue = "0";
                }
                break;
            case Node.PROCESSING_INSTRUCTION_NODE:
                break;
        }// end of switch

        // based on walk type, do something
        switch (walkType) {
            case NameSpaceURI:
                put(nameSpaceName, prefixName);
                break;
            case ElementValue:
                if (nodeValue.equals(targetValue)) {
                    stopWalk = true;
                }
                break;
            case FindNode:
                if (searchName.equals(targetName)) {
                    stopWalk = true;
                }
                break;

            case PairGroup:
                if (searchName.equals(targetName)) {
                    this.nodeList.add(targetNode);
                }
                break;

            case FindText:
                if (nodeName != null && searchName.equals(targetName)) {
                    // walk the children until you find a text node
                    NodeList cl = node.getChildNodes();
                    for (int i = 0; i < cl.getLength(); i++) {
                        Node child = cl.item(i);
                        if (child != null && (child.getNodeType() == Node.TEXT_NODE || child.getNodeType() == Node.ELEMENT_NODE)) {
                            String childValue = child.getNodeValue();
                            if (childValue != null && !childValue.trim().isEmpty()) {
                                targetValue = childValue;
                                stopWalk = true;
                            }
                        }

                    }
                }

                break;

            case PairMatch:
                if (nodeValue != null && nodeValue.equals(targetValue)) {
				// now that we found the value, make sure the
                    // parent's name matches
                    Node parentNode = node.getParentNode();
                    String parentNodeName = parentNode.getNodeName();
                    if (parentNodeName == null) {
                        parentNodeName = "";
                    }
                    String parentLocalName = parentNode.getLocalName();
                    if (parentLocalName == null) {
                        parentLocalName = "";
                    }

                    if (parentNodeName.equals(targetName)
                            || parentLocalName.equals(targetName)) {
                        targetNode = parentNode;
                        stopWalk = true;
                    }
                } else if (nodeName.equals(targetName)) {
                    if (nodeValue.equals(targetValue)) {
                        stopWalk = true;
                    }
                }
                break;
        } // end of switch
        // since we are recursive, if we found what we want, stop looking
        // for it
        // recurse
        for (Node child = node.getFirstChild(); child != null; child = child
                .getNextSibling()) {
            if (!stopWalk) {
                walk(child, walkType);
            }
        }
    }// end of walk`

    public String getTextValueFromNode(Node parent, String valueToGet) {
        targetName = valueToGet;
        stopWalk = false;
        targetValue = "";
        walk(parent, WalkType.FindText);

        return targetValue;

    }

    public static String printDocToFormattedString(Document doc) {

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //initialize StreamResult with File object to save to file
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            return result.getWriter().toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static Document cloneDocument(Document docToClone) {
        TransformerFactory tfactory = TransformerFactory.newInstance();
        DOMSource source = new DOMSource(docToClone);
        Transformer tx = null;
        Document clonedDoc = null;
        try {
            tx = tfactory.newTransformer();
            source = new DOMSource(docToClone);
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DOMResult result = new DOMResult();
        if (tx != null) {
            try {
                tx.transform(source, result);
                if (result != null) {
                    clonedDoc = (Document) result.getNode();
                }
            } catch (TransformerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return clonedDoc;
    }
} // end of class
