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

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

//import org.apache.commons.lang.StringUtils;
import java.util.logging.Logger;
import org.dmtf.schemas.wbem.wsman._1.wsman.SelectorSetType;
import org.w3c.dom.NodeList;

import com.dell.sm.wsman.utility.WSCommandRNDConstant;
import com.dell.sm.wsman.utility.WSManClassEnum;
import com.dell.sm.wsman.utility.WSManageSession.EnumReferenceParam;
//import com.dell.pg.tetris.business.common.entity.CommandResponse;
import com.sun.ws.management.addressing.Addressing;
import com.sun.ws.management.enumeration.Enumeration;
import com.sun.ws.management.enumeration.EnumerationExtensions.Mode;
import java.util.logging.Level;

public class CreateRebootJobCmd extends WSManBaseCommand {

    private static final Logger logger = Logger
            .getLogger(CreateRebootJobCmd.class.getName());
    private WSManageSession session = null;

    public CreateRebootJobCmd(String ipAddr, String port, String userName, String passwd,
            boolean bCertificateCheck) {
        super(ipAddr, port, userName, passwd);
        logger.log(Level.INFO, "Entering the CreateRebootJobCmd constructor");
        session = this.getSession();
        logger.log(Level.INFO, "Exiting the CreateRebootJobCmd constructor");
    }

    @Override
    public Object execute() throws Exception {
        logger.log(Level.INFO, "Entering method : execute() for create reboot job");
        List<EnumReferenceParam> items = callEnumWithEndPointReference();
        SelectorSetType settype = items.get(0).getSelectorSetTypes();
        session.getSelectors().addAll(settype.getSelector());

        logger.log(Level.INFO, "Selector set size is :" + settype.getSelector().size());
        // this.addUserParms();
        session.addUserParam("RebootJobType", String.valueOf(3));
        session.setInvokeCommand("CreateRebootJob");
        Addressing response = session.sendInvokeRequest();
        // Object result = parseResponseByXPath(response,"CreateRebootJob");
        String retValue = (String) findObjectInDocument(response.getBody(),
                "//pre:CreateRebootJob_OUTPUT/pre:ReturnValue/text()",
                XPathConstants.STRING);
        logger.info("returned value is :" + retValue);

        CommandResponse resCmd = new CommandResponse();
        if (("4096").equals(retValue)) {
            // Get JobID
            return extractCommandResponse(response);
        } else {
            String msgVal = (String) findObjectInDocument(response.getBody(),
                    "//pre:CreateRebootJob_OUTPUT/pre:Message/text()",
                    XPathConstants.STRING);
            resCmd.setDetails(msgVal);
            resCmd.setLCErrorCode(this.getLCErrorCode());
            resCmd.setLCErrorStr(this.getLCErrorStr());
        }

        resCmd.setbSuccess(Boolean.FALSE);
        logger.info("Exiting method : execute() for create reboot job");
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

    /**
     * Enumerates and returns selector set for SoftwareInstallationService
     *
     * @return
     * @throws SOAPException
     * @throws JAXBException
     * @throws DatatypeConfigurationException
     * @throws IOException
     * @throws Exception
     */
    private List<EnumReferenceParam> callEnumWithEndPointReference()
            throws SOAPException, JAXBException,
            DatatypeConfigurationException, IOException, Exception {
        session.setResourceUri(getSoftwareInstallationServiceResourceURI());
        Enumeration responseEnum = this
                .sendRequestEnumeration(Mode.EnumerateEPR);
        List<EnumReferenceParam> items = session
                .extractRequestEnumerationRefParameters(responseEnum);
        return items;
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
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        xpath.setNamespaceContext(new PersonalNamespaceContext(
                getSoftwareInstallationServiceResourceURI()));
        XPathExpression expr = xpath.compile(xPathLocation);
        Object result = expr.evaluate(doc, qname);
        return result;
    }

}
