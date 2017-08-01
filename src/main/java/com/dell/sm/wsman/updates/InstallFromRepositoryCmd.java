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
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.util.logging.Logger;
import org.dmtf.schemas.wbem.wsman._1.wsman.SelectorSetType;
import org.w3c.dom.NodeList;

import com.dell.sm.wsman.utility.WSCommandRNDConstant;
import com.dell.sm.wsman.utility.WSManClassEnum;
import com.dell.sm.wsman.utility.WSManageSession.EnumReferenceParam;
import com.sun.ws.management.addressing.Addressing;
import com.sun.ws.management.enumeration.Enumeration;
import com.sun.ws.management.enumeration.EnumerationExtensions.Mode;
import java.util.logging.Level;

/**
 * This class is meant to Construct InstallFromRepository command with the
 * specified parameters and execute that command on the target server.
 * InstallFromRepository command takes source location and catalog file name and
 * apply all firmware which are applicable on the target server.
 *
 */
public class InstallFromRepositoryCmd extends WSManBaseCommand {

    private String shareIpAddr;
    private String shareName;
    private String catalogFileName;
    private int shareType;
    private String shareUserName;
    private String domain;
    private String sharePasswd;
    private String mountPoint;

    private int applyUpdate;
    private boolean rebootNeeded = false;

    private WSManageSession session = null;

    public String getShareIpAddr() {
        return shareIpAddr;
    }

    public void setShareIpAddr(String shareIpAddr) {
        this.shareIpAddr = shareIpAddr;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public String getCatalogFileName() {
        return catalogFileName;
    }

    public void setCatalogFileName(String catalogFileName) {
        this.catalogFileName = catalogFileName;
    }

    public int getShareType() {
        return shareType;
    }

    public void setShareType(int shareType) {
        this.shareType = shareType;
    }

    public String getShareUserName() {
        return shareUserName;
    }

    public void setShareUserName(String shareUserName) {
        this.shareUserName = shareUserName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSharePasswd() {
        return sharePasswd;
    }

    public void setSharePasswd(String sharePasswd) {
        this.sharePasswd = sharePasswd;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public int getApplyUpdate() {
        return applyUpdate;
    }

    public void setApplyUpdate(int applyUpdate) {
        this.applyUpdate = applyUpdate;
    }

    public boolean isRebootNeeded() {
        return rebootNeeded;
    }

    public void setRebootNeeded(boolean rebootNeeded) {
        this.rebootNeeded = rebootNeeded;
    }

    private static final Logger logger = Logger
            .getLogger(InstallFromRepositoryCmd.class.getName());

    /**
     * Construct InstallFromRepository command with the specified parameters
     *
     * @param iDracIpAddr specifies idrac ip address
     * @param port specifes idrac port number
     * @param iDracUserName specifies idrac username
     * @param iDracPasswd specifies idrac password
     * @param shareIpAddr specifies ipaddress of share location
     * @param shareName specifies share name
     * @param mountPoint specifies mount point
     * @param catalogFileName specifies catalog file name
     * @param shareType specifies share type
     * @param shareUserName specifies share user name
     * @param sharePasswd specifies share password
     * @param applyUpdate specifies applying update
     * @param rebootNeeded specifies whether reboot is required or not
     */
    public InstallFromRepositoryCmd(String iDracIpAddr, String port, String iDracUserName,
            String iDracPasswd, String shareIpAddr, String shareName, String mountPoint,
            String catalogFileName, int shareType, String shareUserName,
            String sharePasswd, int applyUpdate, boolean rebootNeeded) {
        super(iDracIpAddr, port, iDracUserName, iDracPasswd);
        // TODO Auto-generated constructor stub
        logger.log(Level.INFO, "Install From repository constructor is called");

        this.shareIpAddr = shareIpAddr;
        this.shareName = shareName;
        this.mountPoint = mountPoint;
        this.catalogFileName = catalogFileName;
        this.shareType = shareType;
        this.shareUserName = shareUserName;
        this.sharePasswd = sharePasswd;
        this.applyUpdate = applyUpdate;
        this.rebootNeeded = rebootNeeded;

        session = this.getSession();
        logger.log(Level.INFO, "Exiting from install from repository constructor");
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
            throws Exception {
        session.setResourceUri(getSoftwareInstallationServiceResourceURI());
        Enumeration responseEnum = this
                .sendRequestEnumeration(Mode.EnumerateEPR);
        List<EnumReferenceParam> items = session
                .extractRequestEnumerationRefParameters(responseEnum);
        return items;
    }

    /*
     * Gets the selector set for InstallFromRepository and construct the Soap
     * Message for InstallFromRepository command and executes
     * InstallFromRepository command (non-Javadoc)
     * 
     * @see com.dell.pg.spectre.common.protocol.wsman.WSManBaseCommand#execute()
     */
    @Override
    public Object execute() throws Exception {
        logger.log(Level.INFO, "Entering the method: execute for Installfromrepository");
		// Enumerate on DCIM_SoftwareInstallationService with End Point
        // Reference
        List<EnumReferenceParam> items = callEnumWithEndPointReference();
        SelectorSetType settype = items.get(0).getSelectorSetTypes();
        session.getSelectors().addAll(settype.getSelector());

        logger.log(Level.INFO, "Selector set size is :" + settype.getSelector().size());
        session.addUserParam("IPAddress", this.shareIpAddr);
        session.addUserParam("ShareType", String.valueOf(this.shareType));
        
        if (this.shareName != null) {
        session.addUserParam("ShareName", this.shareName);
        }
        
        if (this.mountPoint != null) {
            session.addUserParam("MountPoint", this.mountPoint);
        }

        if (this.shareUserName != null) {
            session.addUserParam("Username", this.shareUserName);
        }

        if (this.sharePasswd != null) {
            session.addUserParam("Password", this.sharePasswd);
        }

        session.addUserParam("ApplyUpdate", String.valueOf(this.applyUpdate));
        session.addUserParam("RebootNeeded", String.valueOf(this.rebootNeeded));
        session.addUserParam("CatalogFile", this.catalogFileName);

        session.setResourceUri(getSoftwareInstallationServiceResourceURI());

        session.setInvokeCommand("InstallFromRepository");
        logger.log(Level.INFO, "calling install from repository invoke request");
        Addressing response = session.sendInvokeRequest();

        String retValue = (String) findObjectInDocument(response.getBody(),
                "//pre:InstallFromRepository_OUTPUT/pre:ReturnValue/text()",
                XPathConstants.STRING);

        CommandResponse resCmd = new CommandResponse();

        if (("4096").equals(retValue)) {
            // Get JobID
            return extractCommandResponse(response);
        } else {
            String msgVal = (String) findObjectInDocument(response.getBody(),
                    "//pre:InstallFromRepository_OUTPUT/pre:Message/text()",
                    XPathConstants.STRING);
            resCmd.setDetails(msgVal);
            resCmd.setLCErrorCode(this.getLCErrorCode());
            resCmd.setLCErrorStr(this.getLCErrorStr());
        }

        resCmd.setbSuccess(Boolean.FALSE);
        logger.log(Level.INFO, "Exiting function: execute()");
        return resCmd;
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
            throws Exception {
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
            for (int i = 0; i < nodeLst.getLength(); i++) {
                attrNode = nodeLst.item(i).getFirstChild().getAttributes()
                        .item(0).getTextContent();
                if ((attrNode != null) && (attrNode.equals("InstanceID"))) {
                    jobID = nodeLst.item(i).getFirstChild().getTextContent();
                    break;
                }

            }

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
