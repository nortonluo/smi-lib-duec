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

import com.dell.sm.downloader.DSMProxy;
import com.dell.sm.downloader.DSMProxyAuthenticator;
import com.dell.sm.wsman.inventory.XMLTool;
import com.dell.sm.wsman.updates.KeyValuePair;
import java.io.IOException;
import java.net.Authenticator;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.util.logging.Logger;
import org.dmtf.schemas.wbem.wsman._1.wsman.SelectorType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlsoap.schemas.ws._2004._08.addressing.ReferenceParametersType;

import com.sun.ws.management.Management;
import com.sun.ws.management.ManagementUtility;
import com.sun.ws.management.addressing.Addressing;
import com.sun.ws.management.enumeration.Enumeration;
import com.sun.ws.management.enumeration.EnumerationExtensions.Mode;
import com.sun.ws.management.enumeration.EnumerationMessageValues;
import com.sun.ws.management.enumeration.EnumerationUtility;
import com.sun.ws.management.server.EnumerationItem;
import com.sun.ws.management.transfer.Transfer;
import com.sun.ws.management.transfer.TransferMessageValues;
import com.sun.ws.management.transfer.TransferUtility;
import java.util.logging.Level;

/**
 * @author ANTHONY_CROUCH
 *
 */
public class WSManageSession {

    private static String transferMsgActionTypePut = "http://schemas.xmlsoap.org/ws/2004/09/transfer/Put";

    // FIXME: Do these need to be here, they are never used...
    // private static String transferMsgActionTypeGet = "";
    // private static String transferMsgActionTypeEnum = "";
    protected String destination;
    protected String resourceUri;
    protected String ipAddress;
    protected Set<SelectorType> selectors = new HashSet<SelectorType>();
    protected Map<String, List<Object>> userParam = new HashMap<String, List<Object>>();
    private List<KeyValuePair> filters = new ArrayList<KeyValuePair>();
    protected Document docIn;
    protected String action = null;
    protected String xpathVal;
    protected int maxElements = 256;
    private String user = "";
    private String password = null;
    protected Mode mode;
    protected String invokeCommand;
    protected long enumerateExpiration;
    protected long enumerateOperationTimeout;
    public static final String ACTION_ARG = "-a";
    public static final String DESTINATION_ARG = "-d";
    public static final String RESOURCE_URI_ARG = "-r";
    public static final String SELECTOR_ARG = "-s";
    public static final String BODY_ARG = "-b";
    public static final String XPATH_ARG = "-x";
    public static final String MAX_ELEM_ARG = "-m";
    public static final String USER_ARG = "-u";
    public static final String PASSWORD_ARG = "-p";
    public static final String COMMAND_ARG = "-i";
    public static final String USER_PARAM_ARG = "-k";
    public static final String GET_ACTION = "get";
    public static final String PUT_ACTION = "put";
    public static final String CREATE_ACTION = "create";
    public static final String DELETE_ACTION = "delete";
    public static final String ENUMERATE_ACTION = "enumerate";
    public static final String INVOKE_ACTION = "invoke";
    public static final String PREFIX = "p";

    private boolean certificateCheck = true;
    public String owningCmd = "";
    protected Addressing lastAddressingResponse = null;
    protected Addressing lastAddressingRequest = null;
    protected Document lastRequestBody = null;
    private HttpConnection httpConnection = null;
    private String messageID = null;
    private String messageStr = null;

    // set up a logger
    private static final Logger logger = Logger.getLogger(WSManageSession.class.getName());

    private static final Logger soapLogger = Logger.getLogger(WSManageSession.class.getName() + ".WSManSoap");

    public Set<SelectorType> getSelectors() {
        return selectors;
    }

    public void setSelectors(Set<SelectorType> selectors) {
        this.selectors = selectors;
    }

    public String getDestination() {
        return destination;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public Document getDocIn() {
        return docIn;
    }

    public String getInvokeCommand() {
        return invokeCommand;
    }

    public void setInvokeCommand(String invokeCommand) {
        this.invokeCommand = invokeCommand;
    }

    public String getXpathVal() {
        return xpathVal;
    }

    public void setXpathVal(String xpathVal) {
        this.xpathVal = xpathVal;
    }

    public int getMaxElements() {
        return maxElements;
    }

    public void setMaxElements(int maxElements) {
        this.maxElements = maxElements;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public long getEnumerateExpiration() {
        return enumerateExpiration;
    }

    public void setEnumerateExpiration(long enumerateExpiration) {
        this.enumerateExpiration = enumerateExpiration;
    }

    public long getEnumerateOperationTimeout() {
        return enumerateOperationTimeout;
    }

    public void setEnumerateOperationTimeout(long enumerateOperationTimeout) {
        this.enumerateOperationTimeout = enumerateOperationTimeout;
    }

    public boolean isCertificateCheck() {
        return certificateCheck;
    }

    public void setCertificateCheck(boolean certificateCheck) {
        this.certificateCheck = certificateCheck;
    }

    public Map<String, List<Object>> getUserParam() {
        return userParam;
    }

    public void setUserParam(Map<String, List<Object>> userParam) {
        this.userParam = (HashMap<String, List<Object>>) userParam;
    }

    protected void setTimeoutInSeconds(int to) {
        httpConnection.setTimeout(to * 1000);
    }

    /**
     * Add Selector with this name/value pair
     *
     * @param key specifes the tag name in xml
     * @param value specifies the tag value in xml
     */
    public void addSelector(String key, String value) {
        SelectorType sel = new SelectorType();
        sel.setName(key);
        sel.getContent().add(value);
        selectors.add(sel);
    }

    public void addUserParam(String key, Object value) {
        if (userParam.containsKey(key) == false) {
            List<Object> valueList = new LinkedList<Object>();
            valueList.add(value);
            userParam.put(key, valueList);
        } else {
            List<Object> list = userParam.get(key);
            list.add(value);
        }
    }

    public WSManageSession(String destIP, String login, String password) {
        this(destIP, login, password, "443");
    }

    public WSManageSession(String destIP, String login, String password, String port) {
        this(destIP, login, password, port, false, null);
    }

    public WSManageSession(String destIP, String login, String password, String port,DSMProxy mProxy) {
        this(destIP, login, password, port, false, mProxy);
    }

    public WSManageSession(String destIP, String login, String password, String port, boolean bCertCheck, DSMProxy mProxy) {
        initializeSession(destIP, login, password, port, bCertCheck, mProxy);
        initializeSecurity();
//		setupDebug();
        logger.log(Level.INFO, "Exiting constructor: WSManageSession()");
    }

    private void initializeSession(String destIP, String login, String password, String port, boolean bCertCheck, DSMProxy mProxy) {
        if (destIP == null || login == null || password == null || port == null || destIP.trim().equals("") || login.trim().equals("") || password.trim().equals("") || port.trim().equals("")) {
            throw new IllegalArgumentException();
        }
        this.user = login;
        this.password = password;
        this.ipAddress = destIP;
        this.certificateCheck = bCertCheck;
        this.destination = String.format("https://%s:%s/wsman", destIP, port);
        this.httpConnection = new HttpConnection(destination, destIP, this.certificateCheck, mProxy);
//		this.connection.setTimeout(to);
    }

    /**
     * Method used to set authentication correctly
     */
    private void initializeSecurity() {
        Authenticator.setDefault(new DSMProxyAuthenticator(null, this.ipAddress, this.user, this.password));
        try {
            try {
                httpConnection.setTrustManager();
            } catch (KeyManagementException ex) {
                java.util.logging.Logger.getLogger(WSManageSession.class.getName()).log(Level.SEVERE, null, ex);
            }
            httpConnection.setHostnameVerifier(hv);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, null, e);
        }

    }

    public Document extractAddressBody(Addressing reqGetManagement) throws SOAPException {
        Document requestBody = reqGetManagement.getBody().extractContentAsDocument();
        lastRequestBody = XMLTool.cloneDocument(requestBody);
        return requestBody;
    }

    public Object findObjectInDocument(SOAPElement doc, String xPathLocation, QName qname) throws XPathExpressionException {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        xpath.setNamespaceContext(new PersonalNamespaceContext(resourceUri));

        XPathExpression expr = xpath.compile(xPathLocation);
        Object result = expr.evaluate(doc, qname);
        return result;
    }

    /**
     * Method needed to fix the message for Dell Idrac specic WSMAN issues.
     *
     * @param settings
     * @return
     * @throws SOAPException
     */
    private Enumeration fixMessage(Management settings) throws SOAPException {

        //Fix Header elements that need it.
        Enumeration msgEnum = new Enumeration(settings);
        SOAPHeader head = msgEnum.getHeader();
        this.fixOperationTimeout(head);

        //fix body elements that need it.
        SOAPBody body = msgEnum.getBody();
        this.fixExpires(body);

        return msgEnum;
    }

    /**
     * Fix message body issues due to Dell Specific WSMAN issue
     *
     * @param body
     */
    private void fixExpires(SOAPBody body) {
        NodeList nList = body.getElementsByTagName("wsen:Enumerate"); // ("wsen:Expires");
        NodeList childNodes = nList.item(0).getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeName() != null
                    && childNode.getNodeName().equalsIgnoreCase("wsen:Expires")) {
//				childNode.setTextContent("PT30.000S");
                //remove the Expires node as this won't work for Modular systems
                childNode.getParentNode().removeChild(childNode);
                break;
            }
        }
    }

    /**
     * Fix operationTimeout * @param head
     */
    private void fixOperationTimeout(SOAPHeader head) {
        NodeList nList = head.getElementsByTagName("wsman:OperationTimeout");
        nList.item(0).getFirstChild().setNodeValue("PT60.001S");
    }

    public List<EnumReferenceParam> extractRequestEnumerationRefParameters(Enumeration response) throws Exception {
        List<EnumReferenceParam> paramList = null;

        List<EnumerationItem> items = EnumerationUtility.extractEnumeratedValues(response);

        if (items != null && items.size() > 0) {
            paramList = new LinkedList<EnumReferenceParam>();

            for (EnumerationItem item : items) {

                ReferenceParametersType refParam = item.getEndpointReference().getReferenceParameters();

                List<Object> objects = refParam.getAny();
                JAXBElement<?> object1 = (JAXBElement<?>) objects.get(0);
                JAXBElement<?> object2 = (JAXBElement<?>) objects.get(1);

                org.dmtf.schemas.wbem.wsman._1.wsman.AttributableURI attrURI = (org.dmtf.schemas.wbem.wsman._1.wsman.AttributableURI) object1.getValue();
                org.dmtf.schemas.wbem.wsman._1.wsman.SelectorSetType selectorSetTypes = (org.dmtf.schemas.wbem.wsman._1.wsman.SelectorSetType) object2.getValue();

                EnumReferenceParam enumRefParam = new WSManageSession.EnumReferenceParam();
                paramList.add(enumRefParam);

                enumRefParam.selectorSetTypes = selectorSetTypes;
                enumRefParam.attrURI = attrURI;

            }
        }
        return paramList;
    }

    public Addressing sendRequestEnumeration() throws Exception {
        return sendRequestEnumeration(null);
    }

    /**
     * Method to send Enum request through WSMAN return Enumeration object.
     *
     * @param enumMode specifies the Enum mode
     * @return Enumeration
     * @throws SOAPException SOAPException
     * @throws DatatypeConfigurationException DatatypeConfigurationException
     * @throws JAXBException JAXBException
     * @throws IOException IOException
     * @throws XPathExpressionException XPathExpressionException
     */
    public Addressing sendRequestEnumeration(Mode enumMode) throws SOAPException, JAXBException, DatatypeConfigurationException, IOException, XPathExpressionException {

        if (destination == null) {
            throw new IllegalArgumentException("Destination is Null.");
        }

        if (resourceUri == null) {
            throw new IllegalArgumentException("resourceUrI is null.");
        }

        EnumerationMessageValues enumRequest = EnumerationMessageValues.newInstance();
        enumRequest.setExpires(-1);// this will be changed later in code.
        enumRequest.setRequestForOptimizedEnumeration(true);

        if (maxElements != 0) {
            enumRequest.setMaxElements(maxElements);
        }
        enumRequest.setTo(destination);
        enumRequest.setResourceUri(resourceUri);
        enumRequest.setMaxTime(6000L);

        if (enumMode != null) {
            enumRequest.setEnumerationMode(enumMode);
        }
        if (selectors != null && selectors.size() > 0) {
            enumRequest.setSelectorSet(selectors);
        }

        Enumeration msg = EnumerationUtility.buildMessage(null, enumRequest);
        Management settings = ManagementUtility.buildMessage(msg, null);

        // Add enumeration filters to soap message body
        if (!filters.isEmpty()) {
            addEnumFilter(settings.getBody());
        }

        //Fix the data.
        settings = new Management(this.fixMessage(settings));

        // Send the WS-Management request
        writeCmdDetails(settings, "Request");

        Addressing response = httpConnection.sendHttp(settings);
        lastAddressingRequest = settings;
        lastAddressingResponse = response;

        writeCmdDetails(response, "Response");

        if (response.getBody().hasFault()) {
            SOAPFault fault = response.getBody().getFault();
            throw new SOAPException(fault.getFaultString());
        } else {
            setLCMessageID((String) findObjectInDocument(response.getBody(), "//*/pre:MessageID/text()", XPathConstants.STRING));
            logger.log(Level.INFO, "MessageID = " + getLCMessageID());
            setLCMessageStr((String) findObjectInDocument(response.getBody(), "//*/pre:Message/text()", XPathConstants.STRING));
            logger.log(Level.INFO, "MessageStr = " + getLCMessageStr());
            return response;

        }
    }

    public Addressing sendGetRequest()
            throws IllegalArgumentException, SOAPException, JAXBException, DatatypeConfigurationException, IOException, XPathExpressionException {

        if (destination == null) {
            throw new IllegalArgumentException("Destination is Null.");
        }

        if (resourceUri == null) {
            throw new IllegalArgumentException("resourceUrI is null.");
        }

        if (selectors == null || selectors.size() == 0) {
            throw new IllegalArgumentException("No selectors specified.");
        }

        TransferMessageValues settings = TransferMessageValues.newInstance();
        settings.setResourceUri(resourceUri);
        settings.setTo(destination);
        settings.setSelectorSet(selectors);
        settings.setTransferMessageActionType("http://schemas.xmlsoap.org/ws/2004/09/transfer/Get");
        if (xpathVal != null) {
            settings.setFragment(xpathVal);
            settings.setFragmentDialect("http://www.w3.org/TR/1999/REC-xpath-19991116");
        }
        Transfer mgmt = TransferUtility.buildMessage(null, settings);

        writeCmdDetails(mgmt, "Request");

        Addressing response = httpConnection.sendHttp(mgmt);
        lastAddressingResponse = response;
        lastAddressingRequest = mgmt;

        writeCmdDetails(response, "Response");

        if (response.getBody().hasFault()) {
            SOAPFault fault = response.getBody().getFault();
            throw new SOAPException(fault.getFaultString());
        } else {
            setLCMessageID((String) findObjectInDocument(response.getBody(), "//*/pre:MessageID/text()", XPathConstants.STRING));
            logger.log(Level.INFO, "MessageID = " + getLCMessageID());
            setLCMessageStr((String) findObjectInDocument(response.getBody(), "//*/pre:Message/text()", XPathConstants.STRING));
            logger.log(Level.INFO, "MessageStr = " + getLCMessageStr());
            return response;

        }
    }

    public Addressing sendInvokeRequest()
            throws IllegalArgumentException, SOAPException, JAXBException, DatatypeConfigurationException, IOException, XPathExpressionException {

        if (this.getInvokeCommand() == null || this.getInvokeCommand().length() == 0) {
            throw new IllegalArgumentException("invokeCommand is not set");
        }

        if (destination == null) {
            throw new IllegalArgumentException("Destination is Null.");
        }

        if (resourceUri == null) {
            throw new IllegalArgumentException("resourceUrI is null.");
        }

        TransferMessageValues settings = TransferMessageValues.newInstance();
        settings.setResourceUri(resourceUri);
        settings.setTo(destination);
        if (selectors != null && selectors.size() > 0) {
            settings.setSelectorSet(selectors);
        }
        settings.setTransferMessageActionType(this.getResourceUri() + "/" + this.getInvokeCommand());

        Transfer mgmt = TransferUtility.buildMessage(null, settings);

        SOAPBody body = mgmt.getBody();
        SOAPEnvelope envelope = mgmt.getEnvelope();

        envelope.addNamespaceDeclaration(PREFIX, this.getResourceUri());
        SOAPElement parent = body.addChildElement(this.getInvokeCommand() + "_INPUT", PREFIX);

        if (userParam != null && userParam.size() > 0) {
            SOAPElement element = null;
            String key;
            List<Object> valueList;
            Object value;

            Iterator<String> iterator = userParam.keySet().iterator();

            while (iterator.hasNext()) {
                key = iterator.next().toString();
                valueList = userParam.get(key);
                Iterator<Object> valueSetIterator = valueList.iterator();
                while (valueSetIterator.hasNext()) {
                    value = valueSetIterator.next();
                    if (value instanceof String) {
                        element = parent.addChildElement(key, PREFIX);
                        element.addTextNode(value.toString());
                    } else if (value instanceof SOAPElement) {
                        element = parent.addChildElement((SOAPElement) value);
                    } else if (value == null) {
                        element = parent.addChildElement(key, PREFIX);
                        element.addTextNode("");
                    } else {
                        logger.log(Level.INFO, "Unexpected element found: " + element);
                        //throw new RuntimeSpectreException("Session:  Invalid UserParam set");
                        throw new SOAPException("Session:  Invalid UserParam set");
                    }
                }
            }
        }

        writeCmdDetails(mgmt, "Request");

        Addressing response = httpConnection.sendHttp(mgmt);
        lastAddressingResponse = response;
        lastAddressingRequest = mgmt;

        writeCmdDetails(response, "Response");

        if (response.getBody().hasFault()) {

            SOAPFault fault = response.getBody().getFault();
            throw new SOAPException(fault.getFaultString());
        } else {
            setLCMessageID((String) findObjectInDocument(response.getBody(), "//*/pre:MessageID/text()", XPathConstants.STRING));
            logger.log(Level.INFO, "MessageID = " + getLCMessageID());
            setLCMessageStr((String) findObjectInDocument(response.getBody(), "//*/pre:Message/text()", XPathConstants.STRING));
            logger.log(Level.INFO, "MessageStr = " + getLCMessageStr());
            return response;
        }
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @author anthony_crouch
     *
     * Class to contain the SelectorTypes.
     */
    public static class EnumReferenceParam {

        public org.dmtf.schemas.wbem.wsman._1.wsman.AttributableURI getAttrURI() {
            return attrURI;
        }

        public org.dmtf.schemas.wbem.wsman._1.wsman.SelectorSetType getSelectorSetTypes() {
            return selectorSetTypes;
        }
        private org.dmtf.schemas.wbem.wsman._1.wsman.AttributableURI attrURI;
        private org.dmtf.schemas.wbem.wsman._1.wsman.SelectorSetType selectorSetTypes;
    }
    /**
     *
     */
    static HostnameVerifier hv = new HostnameVerifier() {

        public boolean verify(String urlHostName, SSLSession session) {
            return true;
        }
    };

    public Addressing getlastAddressingResponse() {
        return lastAddressingResponse;
    }

    public Addressing getlastAddressingRequest() {
        return lastAddressingRequest;
    }

    public Document getLastRequestBody() {
        return lastRequestBody;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    private void writeCmdDetails(Addressing message, String messageType) {

        if (message != null) {
            Document Doc = message.getBody().getOwnerDocument();
            logger.log(Level.INFO, String.format("%s\n\n--------------%s--------------\n\n%s\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n", messageType, this.owningCmd, XMLTool.printDocToFormattedString(Doc)));
        } else {
            logger.log(Level.INFO, String.format("%s\n\n--------------%s--------------\n\n!@#$ NO DATA FOUND !@#$\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n", messageType, this.owningCmd));
        }
    }

    /**
     *
     * @param body
     * @throws SOAPException
     * @throws JAXBException
     * @throws DatatypeConfigurationException
     * @throws IOException
     */
    private void addEnumFilter(SOAPBody body) throws SOAPException, JAXBException, DatatypeConfigurationException, IOException {

        Document doc = body.getOwnerDocument();
        doc.setStrictErrorChecking(false);

        String dialect = "http://schemas.dmtf.org/wbem/wsman/1/wsman/SelectorFilter";

        Element filterElement = doc.createElement("Filter");
        filterElement.setPrefix("wsman");
        filterElement.setAttribute("Dialect", dialect);
        Element selectorSetNode = doc.createElement("SelectorSet");
        selectorSetNode.setPrefix("wsman");
        filterElement.appendChild(selectorSetNode);

        Iterator<KeyValuePair> iterator = filters.iterator();
        String name;
        String value;
        KeyValuePair filter;
        while (iterator.hasNext()) {
            filter = iterator.next();
            name = filter.getKey();
            value = filter.getValue();
            Element selectorElement = doc.createElement("Selector");
            selectorElement.setPrefix("wsman");
            selectorElement.setAttribute("Name", name);
            Node selectorNode = selectorSetNode.appendChild(selectorElement);
            selectorNode.setTextContent(value);
        }
        NodeList nList = body.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            Node childNode = nList.item(i);
            if (childNode.getNodeName() != null && childNode.getNodeName().equalsIgnoreCase("wsen:Enumerate")) {
                childNode.appendChild(filterElement);
                break;
            }
        }
    }

    public void setLCMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getLCMessageID() {
        return messageID;
    }

    public void setLCMessageStr(String messageStr) {
        this.messageStr = messageStr;
    }

    public String getLCMessageStr() {
        return messageStr;
    }

}
