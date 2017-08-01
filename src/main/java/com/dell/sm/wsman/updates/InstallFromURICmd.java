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
import java.io.IOException;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

//import org.apache.commons.lang.StringUtils;
import java.util.logging.Logger;
import org.dmtf.schemas.wbem.wsman._1.wsman.SelectorSetType;
import org.w3c.dom.NodeList;

//import com.dell.pg.spectre.common.protocol.wsman.WSCommandRNDConstant;
import com.dell.sm.wsman.utility.WSCommandRNDConstant;
import com.dell.sm.wsman.utility.WSManClassEnum;
import com.dell.sm.wsman.utility.WSManMethodEnum;
import com.dell.sm.wsman.utility.WSManageSession.EnumReferenceParam;
//import com.dell.pg.spectre.common.protocol.wsman.WSManBaseCommand;
//import com.dell.pg.spectre.common.protocol.wsman.WSManageSession;
//import com.dell.pg.spectre.common.protocol.wsman.WSManBaseCommand.WSManClassEnum;
//import com.dell.pg.spectre.common.protocol.wsman.WSManBaseCommand.WSManMethodEnum;
//import com.dell.pg.tetris.business.common.entity.CommandResponse;
//import com.dell.pg.tetris.utilities.XMLTool;
import com.sun.ws.management.addressing.Addressing;
import com.sun.ws.management.enumeration.Enumeration;
import com.sun.ws.management.enumeration.EnumerationExtensions.Mode;
import java.util.logging.Level;

public class InstallFromURICmd extends WSManBaseCommand {

    private static final Logger logger = Logger.getLogger(InstallFromURICmd.class.getName());

    private WSManageSession session = null;
    private String exeURI = null;
    private String instanceID = null;

    public InstallFromURICmd(String iDracIpAddr, String port, String iDracUserName,
            String iDracPasswd, String exeURI, String instanceID) {
        super(iDracIpAddr, port, iDracUserName, iDracPasswd);
        logger.log(Level.INFO, "Entering the InstallFromURICmd constructor");
        this.exeURI = exeURI;
        this.instanceID = instanceID;
        session = this.getSession();

        logger.log(Level.INFO, "Exiting the InstallFromURICmd constructor");
    }

    /**
     * Enumerates and returns selector set for SoftwareInstallationService
     *
     * @return
     * @throws SOAPException
     * @throws DatatypeConfigurationException
     * @throws IOException
     * @throws Exception
     */
    private List<EnumReferenceParam> callEnumWithEndPointReference()
            throws Exception {
        session.setResourceUri(getSoftwareInstallationServiceResourceURI());
        Enumeration responseEnum = this
                .sendRequestEnumeration(Mode.EnumerateEPR);
        List<EnumReferenceParam> items = session
                .extractRequestEnumerationRefParameters(responseEnum);
        return items;
    }

    @Override
    public Object execute() throws Exception {
        logger.log(Level.INFO, "Entering the method: execute for InstallFromURICmd");
        session.setResourceUri(getSoftwareInstallationServiceResourceURI());
		// Enumerate on DCIM_SoftwareInstallationService with End Point
        // Reference
        List<EnumReferenceParam> items = callEnumWithEndPointReference();
        SelectorSetType settype = items.get(0).getSelectorSetTypes();
        session.getSelectors().addAll(settype.getSelector());
        session.setInvokeCommand(WSManMethodEnum.INSTALL_FROM_URI.toString());
        initCommand(exeURI, instanceID);
        Addressing response = session.sendInvokeRequest();
        //String responseContent =  XMLTool.convertAddressingToString(response);
        logger.info("response recieved is :" + response);

        String retValue = (String) findObjectInDocument(response.getBody(),
                "//pre:InstallFromURI_OUTPUT/pre:ReturnValue/text()",
                XPathConstants.STRING);
        logger.log(Level.INFO, "returnValue corresponding to xpath //pre:InstallFromURI_OUTPUT/pre:ReturnValue/text() is: "
                + retValue);
        CommandResponse resCmd = new CommandResponse();

        if (("4096").equals(retValue)) {
            // Get JobID
            logger.log(Level.INFO, "extracting command response");
            return extractCommandResponse(response);
        } else {
            String msgVal = (String) findObjectInDocument(response.getBody(),
                    "//pre:InstallFromURI_OUTPUT/pre:Message/text()",
                    XPathConstants.STRING);
            logger.log(Level.INFO, "message returned from LC: " + msgVal);
            resCmd.setDetails(msgVal);
            resCmd.setLCErrorCode(this.getLCErrorCode());
            resCmd.setLCErrorStr(this.getLCErrorStr());
        }

        //resCmd.setbSuccess(Boolean.FALSE);				
        logger.log(Level.INFO, "Exiting the method: execute for InstallFromURICmd");
        return resCmd;
    }

    /**
     *
     * @return End Point Reference of SoftwareInstallationService
     */
    private String getSoftwareInstallationServiceResourceURI() {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder(
                WSCommandRNDConstant.WSMAN_BASE_URI);

        sb.append(WSCommandRNDConstant.WS_OS_SVC_NAMESPACE).append(
                WSManClassEnum.DCIM_SoftwareInstallationService);

        return sb.toString();

    }

    private void initCommand(String exeURI, String instanceID) throws Exception {
        SOAPFactory factory = SOAPFactory.newInstance();
        SOAPElement elementTarget = factory.createElement("Target", "p", session.getResourceUri());
        elementTarget.addNamespaceDeclaration("a", "http://schemas.xmlsoap.org/ws/2004/08/addressing");
        elementTarget.addNamespaceDeclaration("w", "http://schemas.dmtf.org/wbem/wsman/1/wsman.xsd");

        SOAPElement elementAddress = elementTarget.addChildElement("Address", "a");
        elementAddress.setValue("http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous");

        SOAPElement params = elementTarget.addChildElement("ReferenceParameters", "a");
        params.addChildElement("ResourceURI", "w").setValue("http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity");

        SOAPElement selectorSet = params.addChildElement("SelectorSet", "w");
        SOAPElement selector = selectorSet.addChildElement("Selector", "w");
        selector.addAttribute(new QName("Name"), "InstanceID");

        selector.setValue(instanceID);
        session.addUserParam("URI", exeURI);
        session.addUserParam("Target", elementTarget);

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
    private Object findObjectInDocument(SOAPElement doc, String xPathLocation,
            QName qname) throws XPathExpressionException {
        logger.log(Level.INFO, "Entering the method findObjectInDocument()");
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        xpath.setNamespaceContext(new PersonalNamespaceContext(
                "http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/root/dcim/DCIM_SoftwareInstallationService"));
        XPathExpression expr = xpath.compile(xPathLocation);
        Object result = expr.evaluate(doc, qname);
        logger.log(Level.INFO, "Exiting the method findObjectInDocument()");
        return result;
    }

    /**
     * Extracts and returns the Job Id if command is successful or error in case
     * of failure
     *
     * @param addressing
     * @return
     * @throws SOAPException
     * @throws XPathExpressionException
     */
    private CommandResponse extractCommandResponse(Addressing addressing)
            throws SOAPException, XPathExpressionException {
        SOAPElement doc = addressing.getBody();
        String resourceURI = null;
        String jobID = null;
        CommandResponse response = new CommandResponse();

        // Get ResourceURI
        Object result = findObjectInDocument(doc, "//wsman:ResourceURI/text()",
                XPathConstants.STRING);
        if (result == null || ((String) result).length() == 0) {
            logger.log(Level.INFO, "Unable to get ResourceURI from response");
            response.setbSuccess(Boolean.FALSE);
            response.setDetails("Unable to get ResourceURI from response");
            return response;
        } else {

            resourceURI = (String) result;
            logger.log(Level.INFO, "Successfully got the resource URI :" + resourceURI);
        }

        // Get Selectors
        result = findObjectInDocument(doc, "//wsman:SelectorSet",
                XPathConstants.NODESET);
        NodeList nodeLst = (NodeList) result;
        String attrNode = null;

        if (nodeLst == null) {
            logger.log(Level.INFO, "Unable to get SelectorSet from response");
            response.setbSuccess(Boolean.FALSE);
            response.setDetails("Unable to get SelectorSet from response");
            return response;
        } else {
            logger.log(Level.INFO, "size of nodelist is:" + nodeLst.getLength());
            result = findObjectInDocument(doc, "//wsman:Selector[@Name='InstanceID']/text()",
                    XPathConstants.STRING);

            jobID = (String) result;
            logger.info("job id is returned :" + jobID);

            if (jobID == null) {
                logger.log(Level.INFO, "Unable to get Instance Selector from response");
                response.setbSuccess(Boolean.FALSE);
                response.setDetails("Unable to get Instance Selector from response");
                return response;
            } else {
                logger.log(Level.INFO, "Successfully got the jobID from response xml: jobID is "
                        + jobID);
                response.setResourceURI(resourceURI);
                response.setJobID(jobID);
                response.setbSuccess(Boolean.TRUE);
                return response;
            }
        }

    }

}
