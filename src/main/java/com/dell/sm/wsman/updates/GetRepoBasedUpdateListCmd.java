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

import com.dell.sm.wsman.utility.PersonalNamespaceContext;
import com.dell.sm.wsman.utility.WSManageSession;
import com.dell.sm.wsman.utility.WSManBaseCommand;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.util.logging.Logger;
import org.dmtf.schemas.wbem.wsman._1.wsman.SelectorSetType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dell.sm.wsman.utility.WSCommandRNDConstant;
import com.dell.sm.wsman.utility.WSManClassEnum;
import com.dell.sm.wsman.utility.WSManageSession.EnumReferenceParam;
import com.sun.ws.management.addressing.Addressing;
import com.sun.ws.management.enumeration.Enumeration;
import com.sun.ws.management.enumeration.EnumerationExtensions.Mode;
import java.util.logging.Level;

public class GetRepoBasedUpdateListCmd extends WSManBaseCommand {

    private static final Logger logger = Logger.getLogger(GetRepoBasedUpdateListCmd.class.getName());
    private WSManageSession session;

    /**
     * xPath expression to get JobId and Target (Component Instance Id)
     */
    private static final String xpathJobId = "./INSTANCENAME[@CLASSNAME=\"DCIM_RepoUpdateSWID\"]/PROPERTY[@NAME=\"JobID\"]/VALUE/text()";
    private static final String xpathTarget = "./INSTANCENAME[@CLASSNAME=\"DCIM_RepoUpdateSWID\"]/PROPERTY[@NAME=\"Target\"]/VALUE/text()";

    public GetRepoBasedUpdateListCmd(String ipAddr, String port, String userName, String passwd, boolean bCertCheck) {
        super(ipAddr, port, userName, passwd, bCertCheck);
        session = this.getSession();

    }

    private List<EnumReferenceParam> callEnumWithEPR() throws SOAPException, JAXBException,
            DatatypeConfigurationException, IOException, Exception {
        session.setResourceUri(getSoftwareInstallationServiceResourceURI());
        Enumeration responseEnum = this.sendRequestEnumeration(Mode.EnumerateEPR);
        List<EnumReferenceParam> items = session.extractRequestEnumerationRefParameters(responseEnum);
        return items;
    }

    @Override
    public Map<String, String> execute() throws Exception {
        /**
         * Return object, a map of Component Instance ID and Scheduled Job ID.
         */
        Map<String, String> targetJobMap = new HashMap<String, String>();

        try {
            // Enumerate on DCIM_SoftwareInstallationService with EPR
            List<EnumReferenceParam> items = callEnumWithEPR();
            SelectorSetType settype = items.get(0).getSelectorSetTypes();
            session.getSelectors().addAll(settype.getSelector());
            logger.log(Level.INFO, "Selectors " + settype.getSelector());

            session.setResourceUri(getSoftwareInstallationServiceResourceURI());
            session.setInvokeCommand("GetRepoBasedUpdateList");
            logger.log(Level.INFO, "calling GetRepoBasedUpdateList invoke request");
            Addressing response = session.sendInvokeRequest();

			// logger.debug("RESPONSE is" + response);
            /**
             * Replacing &lt; &gt; &quote; to ASCII character due to bug in
             * iDRAC < 1.66.66
             *
             * Removing XML tag because it causes problem to generate Document
             * object
             */
            String responseStr = response.toString().replace("&lt;", "<").replace("&gt;", ">").replace("&quote;", "&");
            responseStr = responseStr.replace("<?xml version=\"1.0\"?>", "");
            byte[] buf = responseStr.getBytes();

            /**
             * Generating XML document to apply xPath (This is required as
             * inherited extractContentAsDocument() will cause problem due to
             * above mentioned bug.
             */
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Document document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(buf));

			// logger.debug("document" + document);
            /**
             * GetRepoBasedUpdateList gives an XML node (VALUE.NAMEDINSTANCE)
             * per job.
             */
            NodeList nodeList = document.getElementsByTagName("VALUE.NAMEDINSTANCE");

            logger.log(Level.INFO, "NodeList size is " + nodeList.getLength());

            targetJobMap.clear();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

//				logger.debug("Node is :" + nodeToString(node));
                String jobId = (String) findObjectInDocument(node, xpathJobId, XPathConstants.STRING);
                String target = (String) findObjectInDocument(node, xpathTarget, XPathConstants.STRING);
                /**
                 * Commented out because started Job will give null job ID,
                 * handle this in manager class
                 */
//				 if (jobId == null || jobId.isEmpty()) {
//				 continue;
//				 }
                targetJobMap.put(target, jobId);
                logger.log(Level.INFO, "Job Id for target " + target + " is " + jobId);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "GetRepoBasedUpdateList throws exception ", e.getMessage());
            logger.log(Level.SEVERE, null, e);
        }
        logger.log(Level.INFO, "Return Object is " + targetJobMap);
        return targetJobMap;
    }

    /**
     *
     * @return EPR of SoftwareInstallationService
     */
    private String getSoftwareInstallationServiceResourceURI() {
        StringBuilder sb = new StringBuilder(WSCommandRNDConstant.WSMAN_BASE_URI);
        sb.append(WSCommandRNDConstant.WS_OS_SVC_NAMESPACE).append(WSManClassEnum.DCIM_SoftwareInstallationService);

        return sb.toString();

    }

    /**
     * returns the content of the corresponding xpath from the soap message
     *
     * @param doc
     * @param xPathLocation
     * @param qname
     * @return
     * @throws XPathExpressionException
     */
    private Object findObjectInDocument(Object doc, String xPathLocation, QName qname) throws XPathExpressionException {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        xpath.setNamespaceContext(new PersonalNamespaceContext(getSoftwareInstallationServiceResourceURI()));
        XPathExpression expr = xpath.compile(xPathLocation);
        Object result = expr.evaluate(doc, qname);
        logger.log(Level.INFO, "result is " + result);
        return result;
    }
}
