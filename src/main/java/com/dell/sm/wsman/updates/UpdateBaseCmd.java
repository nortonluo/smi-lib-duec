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
package com.dell.sm.wsman.updates;

import com.dell.sm.wsman.utility.WSManBaseCommand;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class UpdateBaseCmd extends WSManBaseCommand {

    private static final Logger logger = Logger.getLogger(UpdateBaseCmd.class.getName());

    protected UpdateBaseCmd(String ipAddr, int port, String userName, String passwd, boolean bCertCheck) {
        super(ipAddr, port + "", userName, passwd, bCertCheck);
        logger.log(Level.INFO, "Exiting constructor: UpdateBaseCmd()");
        // TODO Auto-generated constructor stub
    }

    protected LifeCycleJob getJobStatus(Node lifeCycleJobNode) {

        LifeCycleJob jobStatus = null;

        if (lifeCycleJobNode != null) {
            if (lifeCycleJobNode.getNodeName().contains("DCIM_LifecycleJob")) {
                jobStatus = new LifeCycleJob();

                NodeList childNodes = lifeCycleJobNode.getChildNodes();

                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node node = childNodes.item(j);

                    if (node.getLocalName().equals("InstanceID")) {
                        if (node.getFirstChild() != null) {
                            jobStatus.setInstanceID(node.getFirstChild().getNodeValue());
                        }
                    } else if (node.getLocalName().equals("JobStartTime")) {
                        if (node.getFirstChild() != null) {
                            jobStatus.setJobStartTime(node.getFirstChild().getNodeValue());
                        }
                    } else if (node.getLocalName().equals("JobStatus")) {
                        if (node.getFirstChild() != null) {
                            jobStatus.setJobStatus(node.getFirstChild().getNodeValue());
                        }
                    } else if (node.getLocalName().equals("JobUntilTime")) {
                        if (node.getFirstChild() != null) {
                            jobStatus.setJobUntilTime(node.getFirstChild().getNodeValue());
                        }
                    } else if (node.getLocalName().equals("Message")) {
                        if (node.getFirstChild() != null) {
                            jobStatus.setMessage(node.getFirstChild().getNodeValue());
                        }
                    } else if (node.getLocalName().equals("MessageID")) {
                        if (node.getFirstChild() != null) {
                            jobStatus.setMessageID(node.getFirstChild().getNodeValue());
                        }
                    } else if (node.getLocalName().equals("Name")) {
                        if (node.getFirstChild() != null) {
                            jobStatus.setJobName(node.getFirstChild().getNodeValue());
                        }
                    }
                }

                if (jobStatus.getJobStatus() != null && jobStatus.getJobStatus().equalsIgnoreCase("New")) {
                    jobStatus.setJobStatus("New Job");
                } //Changing the running status to scheuled to make 12G updates compatible with 11G
                else if (jobStatus.getJobStatus() != null && jobStatus.getJobStatus().equalsIgnoreCase("running")) {
                    jobStatus.setJobStatus("Scheduled");
                }

                if (jobStatus.getMessage() == null || jobStatus.getMessage().equals("") || jobStatus.getMessage().equalsIgnoreCase("na")) {
                    jobStatus.setMessage(jobStatus.getJobStatus());
                }

            }
        }

        return jobStatus;
    }

    private Object findObjectInDocument(Document doc, String xPathLocation, QName qname) throws XPathExpressionException {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr = xpath.compile(xPathLocation);
        Object result = expr.evaluate(doc, qname);
        return result;
    }

    protected Document convertStringToXMLDocument(String xmlSource) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xmlSource)));
            return document;
        } catch (Exception e) {
        }

        return null;
    }

    protected String getErrorMessage(String xmlSource) {

        if (xmlSource != null && !xmlSource.equals("")) {
            String failureMessage = "";
            String failureCode = "";
            try {
                Document doc = convertStringToXMLDocument(xmlSource);
                failureMessage = (String) findObjectInDocument(doc, "//*[local-name()='Message']/text()", XPathConstants.STRING);
                failureCode = (String) findObjectInDocument(doc, "//*[local-name()='MessageID']/text()", XPathConstants.STRING);
                failureMessage = failureMessage.trim();
                failureCode = failureCode.trim();
                return failureMessage + "@" + failureCode;
            } catch (XPathExpressionException e) {
            }
        }
        return "Unknown error occurred while processing current request on the iDRAC";
    }

    protected String getUpdateJobID(String xmlSource) {

        String jobId = "";
        if (xmlSource != null && !xmlSource.equals("")) {
            Document doc = convertStringToXMLDocument(xmlSource);
            if (doc != null) {
                NodeList selectors = doc.getElementsByTagName("wsman:Selector");
                if (selectors != null) {
                    for (int i = 0; i < selectors.getLength(); i++) {
                        Node selectorNode = selectors.item(i);

                        if (selectorNode.hasAttributes()) {
                            NamedNodeMap attribs = selectorNode.getAttributes();
                            Node attribNode = attribs.getNamedItem("Name");
                            if (attribNode != null) {
                                if (attribNode.hasChildNodes()) {
                                    Node instanceNode = attribNode.getChildNodes().item(0);
                                    if (instanceNode != null) {
                                        String instance = instanceNode.getNodeValue();
                                        if (instance.equals("InstanceID")) {
                                            jobId = selectorNode.getFirstChild().getNodeValue();
                                            break;
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        return jobId;
    }
}
