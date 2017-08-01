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

import com.dell.sm.wsman.utility.HttpConnection;
import com.dell.sm.wsman.utility.WSCommandRNDConstant;
import com.dell.sm.wsman.utility.WSManClassEnum;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMHexBinary4;
import com.dell.sm.downloader.DSMProxy;
import com.dell.sm.downloader.DSMProxyAuthenticator;
import com.dell.sm.wsman.dcim.*;
import com.sun.ws.management.Management;
import com.sun.ws.management.ManagementUtility;
import com.sun.ws.management.addressing.Addressing;
import com.sun.ws.management.enumeration.Enumeration;
import com.sun.ws.management.enumeration.EnumerationExtensions;
import com.sun.ws.management.enumeration.EnumerationMessageValues;
import com.sun.ws.management.enumeration.EnumerationUtility;
import java.io.IOException;
import java.net.Authenticator;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.xpath.XPathExpressionException;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.http.HTTPException;
import org.dmtf.schemas.wbem.wsman._1.wsman.SelectorType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Md_Shahbaz_Alam
 */
public class CollectInventory {

    public CollectInventory(String ipAddress, String userName, String password, String port, DSMProxy proxy) {
        this(ipAddress,userName,password,port);
        this.mProxy = proxy;
        this.httpConnection = new HttpConnection(destination, ipAddress, certificateCheck,mProxy);
    }

    public CollectInventory(String ipAddress, String userName, String password, String port) {
        this.ipAddress = ipAddress;
        this.userName = userName;
        this.password = password;
        this.port = port;
        this.mProxy = null;
        this.certificateCheck = false;
        this.destination = String.format("https://%s:%s/wsman", ipAddress, port);
        this.maxElements = 256;
        this.httpConnection = new HttpConnection(destination, ipAddress, certificateCheck,mProxy);

        try {
            if (jc == null || unmarshaller == null) {
                jc = JAXBContext.newInstance(ObjectFactory.class);
                unmarshaller = jc.createUnmarshaller();
            }
        } catch (JAXBException ex) {
            Logger.getLogger(CollectInventory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            return true;
        }
    };

    private void resetSecurity() {
        Authenticator.setDefault(null);
    }

    private void initializeSecurity() {
        if (mProxy == null) {
            Authenticator.setDefault(new DSMProxyAuthenticator(null, this.ipAddress, this.userName, this.password));
        } else {
            Authenticator.setDefault(new DSMProxyAuthenticator(mProxy, this.ipAddress, this.userName, this.password));
        }
        try {
            httpConnection.setTrustManager();
            httpConnection.setHostnameVerifier(hv);
        } catch (KeyManagementException e) {
            logger.log(Level.SEVERE, null, e);

        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    private void fixExpires(SOAPBody body) {
        NodeList nList = body.getElementsByTagName("wsen:Enumerate"); // ("wsen:Expires");
        NodeList childNodes = nList.item(0).getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeName() != null
                    && childNode.getNodeName().equalsIgnoreCase("wsen:Expires")) {
                //remove the Expires node as this won't work for Modular systems
                childNode.getParentNode().removeChild(childNode);
                break;
            }
        }
    }

    private void fixOperationTimeout(SOAPHeader head) {
        NodeList nList = head.getElementsByTagName("wsman:OperationTimeout");
        nList.item(0).getFirstChild().setNodeValue("PT60.001S");
    }

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

    public Addressing sendRequestEnumeration() throws Exception {
        return sendRequestEnumeration(null);
    }

    public Document extractAddressBody(Addressing reqGetManagement) throws SOAPException {
        Document requestBody = reqGetManagement.getBody().extractContentAsDocument();
        lastRequestBody = XMLTool.cloneDocument(requestBody);
        return requestBody;
    }

    protected org.w3c.dom.Document sendRequestEnumerationReturnDocument() throws Exception {
        Addressing retAddressing = this.sendRequestEnumeration();
        Document doc = this.extractAddressBody(retAddressing);

        return doc;
    }

    public Addressing sendRequestEnumeration(EnumerationExtensions.Mode enumMode) throws SOAPException, JAXBException, DatatypeConfigurationException, IOException, XPathExpressionException {
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

        if (selectors != null && selectors.size() > 0) {
            enumRequest.setSelectorSet(selectors);
        }
        Enumeration msg = EnumerationUtility.buildMessage(null, enumRequest);
        Management settings = ManagementUtility.buildMessage(msg, null);

        //Fix the data.
        settings = new Management(fixMessage(settings));
        Addressing response = null;
        try {
            response = httpConnection.sendHttp(settings);
        } catch (Exception ex) {
            Logger.getLogger(CollectInventory.class.getName()).log(Level.SEVERE, "Authentication Error", ex);
            throw new HTTPException(DCMErrorCodes.AUTH_FAILURE);
        }

        lastAddressingRequest = settings;
        lastAddressingResponse = response;

        if (response.getBody().hasFault()) {
            SOAPFault fault = response.getBody().getFault();
            throw new SOAPException(fault.getFaultString());
        } else {
            return response;
        }
    }

    /**
     * Method to enumerate the CMC Views, iDRAC view, systemString,
     * softwareIdentity
     *
     * @return Inventory document
     * @throws java.lang.Exception Lang Exception
     */
    /**
     * Method to enumerate the CMC Views, iDRAC view, systemString,
     * softwareIdentity
     *
     * @param item
     * @param declaredType
     * @return Inventory document
     * @throws java.lang.Exception Lang Exception
     */
    public <T> Collection<T> enumerate(WSManClassEnum item, Class<T> declaredType) throws Exception {
        try {
            initializeSecurity();

            this.resourceUri = new StringBuilder(WSCommandRNDConstant.WSMAN_BASE_URI).
                    append(WSCommandRNDConstant.WS_OS_SVC_NAMESPACE).append(item).toString();

            Document dom = sendRequestEnumerationReturnDocument();

            if (dom != null) {
                NodeList listOfChildNodes = dom.getElementsByTagNameNS("*", item.toString());
                if ((listOfChildNodes != null) && (listOfChildNodes.getLength() > 0)) {
                    int numberOfNodes = listOfChildNodes.getLength();
                    Collection<T> softwares = new ArrayList<T>();// [numberOfNodes];
                    for (int i = 0; i < numberOfNodes; i++) {
                        Node wsmanItemsElement = (Node) listOfChildNodes.item(i);
                        JAXBElement<T> feed = unmarshaller.unmarshal(wsmanItemsElement, declaredType);
                        softwares.add(feed.getValue());
                    }
                    return softwares;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CollectInventory.class.getName()).log(Level.SEVERE, "Enumeration Error", ex);
        } finally {
            resetSecurity();
        }
        return null;
    }

    /**
     * Method to get the instanceID
     *
     * @param componentId
     * @param vendorId
     * @param deviceId
     * @param subVendorId
     * @param subDeviceId
     * @return instanceID
     * @throws Exception
     */
    public String getInstanceId(String componentId, String vendorId, String deviceId, String subVendorId, String subDeviceId) throws Exception {
        String instanceId = null;

        for (DCIMSoftwareIdentity softwareIdentity : this.enumerate(WSManClassEnum.DCIM_SoftwareIdentity, DCIMSoftwareIdentity.class)) {

            instanceId = softwareIdentity.getInstanceID();

            String status = softwareIdentity.getStatus();
            if (status != null && status.equalsIgnoreCase("Installed")) {
                String invComponentId = softwareIdentity.getComponentID();
                if (invComponentId != null && !invComponentId.equals("")) {
                    if (componentId.equalsIgnoreCase(invComponentId)) {
                        return instanceId;
                    }
                } else if ((deviceId != null && !deviceId.equals("")) && (vendorId != null && !vendorId.equals(""))) {
                    DCMHexBinary4 invVendorID = new DCMHexBinary4();
                    invVendorID.setValue(softwareIdentity.getVendorID());
                    DCMHexBinary4 invDeviceId = new DCMHexBinary4();
                    invDeviceId.setValue(softwareIdentity.getDeviceID());
                    DCMHexBinary4 invSubDeviceID = new DCMHexBinary4();
                    invSubDeviceID.setValue(softwareIdentity.getSubDeviceID());
                    DCMHexBinary4 invSubVendorID = new DCMHexBinary4();
                    invSubVendorID.setValue(softwareIdentity.getSubVendorID());

                    if (invDeviceId.getValue() != null && invVendorID.getValue() != null) {
                        if (deviceId.equals(invDeviceId.getValue()) && vendorId.equals(invVendorID.getValue())) {
                            return instanceId;
                        }
                    }
                }
            }
        }
        return instanceId;

    }
    HttpConnection httpConnection;
    String ipAddress;
    String userName;
    String password;
    String port;
    DSMProxy mProxy;
    boolean certificateCheck;
    String destination;
    int maxElements = 256;
    String resourceUri;
    protected Addressing lastAddressingResponse = null;
    protected Addressing lastAddressingRequest = null;
    Set<SelectorType> selectors = new HashSet<>();
    protected Document lastRequestBody = null;
    JAXBContext jc = null;
    Unmarshaller unmarshaller = null;

    private static final Logger logger = Logger.getLogger(CollectInventory.class.getName());

}
