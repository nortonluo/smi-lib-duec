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
package com.dell.cm.inventory;

import com.dell.sm.wsman.inventory.CollectInventory;
import com.dell.sm.wsman.utility.WSCommandRNDConstant;
import com.dell.cm.updateinformationmodel.*;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.downloader.DSMProxy;
import com.dell.sm.wsman.dcim.DCIMModularChassisView;
import com.dell.sm.wsman.dcim.DCIMSoftwareIdentity;
import com.dell.sm.wsman.dcim.DCIMSystemString;
import com.dell.sm.wsman.dcim.DCIMSystemView;
import com.dell.sm.wsman.utility.WSManClassEnum;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * class for representing Inventory interface
 *
 * @author Raveendra_Madala
 */
public class DCMInventory implements IDCMInventory {

    /**
     * Method for creating an empty multi system inventory
     *
     * @return the empty multi system inventory
     */
    @Override
    public DCMMultiSystemInventory createEmptyMultiSystemInventory() {
        DCMMultiSystemInventory retVal = new DCMMultiSystemInventory();
        return retVal;
    }

    /**
     * Method for adding the inventory information (inventory collector output
     * or the one supplied by CMC) to the multi system inventory
     *
     * @param inSystem specifies DCM System Inventory created for system
     * @param inRoot specifies the root to which the parsed inventory is to be
     * added
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    @Override
    public int addInventory(DCMSystemInventory inSystem, DCMMultiSystemInventory inRoot) {
        if (null == inSystem || null == inRoot) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }

        DCMSystem system = inSystem.getSystem();

        inRoot.getSystemCollection().addSystem(system);

        DCMSystemInstance systemInstance = inSystem.getSystemInstance();
        // Check for if service tag is empty
        if (systemInstance.getServiceTag().isEmpty()) {
            systemInstance.setServiceTag("system-" + system.getUniqueIdentifier());
        }

        systemInstance.setSystemTypeIdentifier(system.getUniqueIdentifier());
        int index = inRoot.getCollectionOfVersionInformationCollections().getVersionInformationCollections().size() + 1;

        DCMVersionInformationCollection versionInfoCollection = new DCMVersionInformationCollection();
        versionInfoCollection.addSystemInstance(systemInstance.getServiceTag());
        versionInfoCollection.setUniqueIdentifier(index);

        inRoot.getCollectionOfVersionInformationCollections().addVersionInformationCollection(versionInfoCollection);
        inRoot.getSystemInstanceCollection().addSystemInstance(systemInstance);

        DCMI18NString displayI18NString = new DCMI18NString();
        for (Object[] deviceInfos : inSystem.getDevices()) {
            DCMBaseTarget device = (DCMBaseTarget) deviceInfos[0];
            // create version information for the device.
            DCMVersionInformation versionInformation = (DCMVersionInformation) deviceInfos[1];;
            DCMUpdateableComponent updatecomponent = (DCMUpdateableComponent) deviceInfos[2];

            DCMComponentType componentTypeValue = updatecomponent.getComponentType();
            displayI18NString.setDefaultLocaleString(componentTypeValue.name());
            inRoot.getComponentKindCollection().addComponentKind(componentTypeValue, displayI18NString);

            inRoot.addTarget(device);
            inRoot.getUpdateableComponentCollection().addUpdateableComponent(updatecomponent);
            versionInfoCollection.addVersionInformation(versionInformation);
        }

        logger.log(Level.INFO, "successfuly added system to inventory with " + inSystem.getDevices().size() + " number of devices");
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for adding the inventory information (inventory collector output
     * or the one supplied by CMC) to the multi system inventory
     *
     * @param inFile specifies the inventory file to be parsed
     * @param inIdentifier specifies the system or group identifier
     * @param inRoot specifies the root to which the parsed inventory is to be
     * added
     * @param inAddAsChild specifies whether the inventory is to be added as a
     * child to the group
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    @Override
    public int addInventory(File inFile, String inIdentifier, DCMMultiSystemInventory inRoot, Boolean inAddAsChild) {
        if (null == inFile || null == inRoot) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        try {

            BufferedReader rd = new BufferedReader(new FileReader(inFile));
            String inputLine = null;
            StringBuilder builder = new StringBuilder();
            //Store the contents of the file to the StringBuilder.
            while ((inputLine = rd.readLine()) != null) {
                builder.append(inputLine);
            }

            StringReader inventory = new StringReader(builder.toString());
            return addInventory(inventory, inIdentifier, inRoot, inAddAsChild);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return DCMErrorCodes.FAILURE;
    }

    /**
     * Method for adding the inventory information (inventory collector output
     * or the one supplied by CMC) to the multi system inventory
     *
     * @param inInventoryAsString specifies the inventory content as string to
     * be parsed
     * @param inIdentifier specifies the system or group identifier
     * @param inRoot specifies the root to which the parsed inventory is to be
     * added
     * @param inAddAsChild specifies whether the inventory is to be added as a
     * child to the group
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    @Override
    public int addInventory(StringReader inInventoryAsString, String inIdentifier, DCMMultiSystemInventory inRoot, Boolean inAddAsChild) {
        try {
            if (null == inInventoryAsString || null == inRoot) {
                return DCMErrorCodes.INVALID_PARAMETER;
            }
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            if (null == docBuilderFactory) {
                return DCMErrorCodes.FAILURE;
            }
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            if (null == docBuilder) {
                return DCMErrorCodes.FAILURE;
            }
            Document givenDocument = docBuilder.parse(new InputSource(inInventoryAsString));
            if (null == givenDocument) {
                return DCMErrorCodes.FAILURE;
            }
            Element givenDocumentRoot = givenDocument.getDocumentElement();
            if (null == givenDocumentRoot) {
                return DCMErrorCodes.FAILURE;
            }
            mSystemIndex = inRoot.getSystemInstanceCollection().getSystemInstances().size() + 1;
            if (inIdentifier == null || inIdentifier.isEmpty()) {
                mIdentifier = String.valueOf(mSystemIndex);
            } else {
                mIdentifier = inIdentifier;
            }
            String childName = givenDocumentRoot.getNodeName();

            NodeList childNodes = givenDocumentRoot.getElementsByTagName(DCMConstants.MULTIPLE_SYSTEM_INVENTORY);
            if (childNodes.getLength() == 1 || childName.equals(DCMConstants.MULTIPLE_SYSTEM_INVENTORY)) {
                logger.log(Level.INFO, "Parsing Multiple System Inventory from " + childName);
                Element multiInventoryNode = childName.equals(DCMConstants.MULTIPLE_SYSTEM_INVENTORY)
                        ? givenDocumentRoot : (Element) childNodes.item(0);
                return parseMultipleSystemInventory(multiInventoryNode, inRoot);
            } else {
                logger.log(Level.INFO, "Parsing " + childName);
                return addInventory(givenDocumentRoot, inRoot);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return DCMErrorCodes.INVALID_PARAMETER;
    }

    /**
     * Method for adding the inventory information (inventory collector output
     * or the one supplied by CMC) to the multi system inventory
     *
     * @param docRoot specifies the inventory content as string to be parsed
     * @param inRoot specifies the root to which the parsed inventory is to be
     * added
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    private int addInventory(Element docRoot, DCMMultiSystemInventory inRoot) {
        try {
            String serviceTagValue = docRoot.getAttribute(DCMConstants.SERVICE_TAG_ATTRIBUTE);
            NodeList childNodeList = docRoot.getChildNodes();
            if ((null != childNodeList) && (childNodeList.getLength() > 0)) {
                int numberOfChildren = childNodeList.getLength();
                DCMSystem system = null;
                DCMOperatingSystem os = null;
                boolean isParsingCMCInventory = true;
                DCMVersionInformationCollection versionInfoCollection = new DCMVersionInformationCollection();
                DCMSystemInstance systemInstance = new DCMSystemInstance();
                for (int childIndex = 0; childIndex < numberOfChildren; ++childIndex) {
                    Node childNode = childNodeList.item(childIndex);
                    short nodeType = childNode.getNodeType();
                    if (nodeType == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNode;
                        String childName = childElement.getTagName();
                        //Operating System Node under SVMInventory Node
                        if (childName.equals(DCMConstants.OPERATING_SYSTEM)) {
                            os = parseOperatingSystem(childElement, inRoot);
                            //System Node under SVMInventory Node
                        } else if (childName.equals(DCMConstants.SYSTEM)) {
                            system = parseSystem(childElement, inRoot);
                            if (system == null) {
                                continue;
                            }
                            //System Node under SVMInventory Node
                        } else if (childName.equals(DCMConstants.DEVICE)) {
                            DCMVersionInformation versionInformation = parseDevice(childElement, inRoot);
                            if (null != versionInformation) {
                                String targetIdentifier = versionInformation.getTargetIdentifier();

                                // getting the Display Name of the target and adding it to target Instance
                                DCMBaseTarget baseTarget = inRoot.getTargetCollection().get(targetIdentifier);
                                DCMI18NString displayName = baseTarget.getName();

                                String targetInstance = versionInformation.getTargetInstance();

                                String displayValue = "";
                                if (displayName.getLocales().contains(Locale.ENGLISH)) {
                                    displayValue = displayName.getLocaleString(Locale.ENGLISH);
                                }
                                if (!displayValue.equals(targetInstance)) {
                                    versionInformation.setTargetInstance(targetInstance);
                                } else {
                                    versionInformation.setTargetInstance(displayValue);
                                }
                                versionInfoCollection.addVersionInformation(versionInformation);
                                isParsingCMCInventory = false;
                            }
                        }
                    } else if (nodeType == Node.ATTRIBUTE_NODE) {

                    }
                }
                if (null != system && !isParsingCMCInventory) {
                    boolean isVersionInfoCollectionAlreadyExist = false;
                    for (DCMVersionInformationCollection versionInformationCollection : inRoot.getCollectionOfVersionInformationCollections().getVersionInformationCollections()) {
                        if (versionInformationCollection.areSameCollections(versionInfoCollection)) {
                            versionInfoCollection = versionInformationCollection;
                            isVersionInfoCollectionAlreadyExist = true;
                        }
                    }
                    systemInstance.setSystemTypeIdentifier(system.getUniqueIdentifier());
                    //UUID uid = UUID.randomUUID();
                    //versionInfoCollection.setUniqueIdentifier(uid.hashCode());
                    if (serviceTagValue.equals("") && null != mIdentifier) {
                        systemInstance.setServiceTag(mIdentifier);
                        versionInfoCollection.addSystemInstance(mIdentifier);
                    } else {
                        systemInstance.setServiceTag(serviceTagValue);
                        versionInfoCollection.addSystemInstance(serviceTagValue);
                    }
                    if (null != os) {
                        systemInstance.setHostOperatingSystemIdentifier(os.getUniqueIdentifier());
                    }
                    if (docRoot.getAttribute(DCMConstants.INVENTORY_COLLECTOR_NAME_ATTRIBUTE).equals("")) {
                        systemInstance.setAgentName(DCMConstants.INVCOL);
                    } else {
                        systemInstance.setAgentName(docRoot.getAttribute(DCMConstants.INVENTORY_COLLECTOR_NAME_ATTRIBUTE));
                    }
                    systemInstance.setAgentVersion(docRoot.getAttribute(DCMConstants.INVENTORY_COLLECTOR_VERSION_ATTRIBUTE));
                    String timeStampString = docRoot.getAttribute(DCMConstants.TIME_STAMP_ATTRIBUTE);
                    DateFormat timeStampDateFormat = new SimpleDateFormat(DCMConstants.DATE_TIME_FORMAT);
                    if (null != timeStampString && !timeStampString.equals("")) {
                        try {
                            Date timeStampDate = timeStampDateFormat.parse(timeStampString);
                            systemInstance.setCollectionTime(timeStampDate);
                        } catch (Exception ex) {
                            logger.log(Level.SEVERE, null, ex);
                        }
                    } else {
                        systemInstance.setCollectionTime(null);
                    }
                    inRoot.getSystemInstanceCollection().addSystemInstance(systemInstance);
                    if (!isVersionInfoCollectionAlreadyExist) {
                        int index = inRoot.getCollectionOfVersionInformationCollections().getVersionInformationCollections().size() + 1;
                        versionInfoCollection.setUniqueIdentifier(index);
                        inRoot.getCollectionOfVersionInformationCollections().addVersionInformationCollection(versionInfoCollection);
                    }
                }
            } else {
                return DCMErrorCodes.FAILURE;
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            return DCMErrorCodes.FAILURE;
        }
        return DCMErrorCodes.SUCCESS;
    }

    private int addWSInventory(CollectInventory inv, DCMMultiSystemInventory inRoot) {
        try {
            int errorCode = 0;
            Collection<DCIMSystemString> systemString = null; //collection to hold systemString in case of iDRAC

            // getting Operating System information from SystemString XML
            DCMSystem system = new DCMSystem();
            DCMSystemInstance systemInstance = new DCMSystemInstance();

            String model = "";
            String hostname = "";
            system.setLOBKey(DCMConstants.LOB_SERVER);
            int systemId = 0;
            String serviceTag = null;

            // getting system identity XML for iDRAC
            Collection<DCIMSystemView> iDracViews = inv.enumerate(WSManClassEnum.DCIM_SystemView, DCIMSystemView.class);
            if (iDracViews != null) {
                DCIMSystemView iDracView = iDracViews.iterator().next();
                systemId = iDracView.getSystemID();
                system.setIDType(DCMSystemIDType.BIOS);
                serviceTag = iDracView.getServiceTag();
                hostname = iDracView.getHostName();
                systemInstance.setAgentName(WSCommandRNDConstant.SYSTEM_NAME);
                model = iDracView.getModel();
                systemString = inv.enumerate(WSManClassEnum.DCIM_SystemString, DCIMSystemString.class);
            } else {
                // getting system identity XML for CMC
                Collection<DCIMModularChassisView> cmcViews = inv.enumerate(WSManClassEnum.DCIM_ModularChassisView, DCIMModularChassisView.class);

                if (cmcViews == null) {
                    throw new Exception("Cannot enumerate System Model");
                }
                DCIMModularChassisView cmcView = cmcViews.iterator().next();
                systemId = cmcView.getSystemID();
                system.setIDType(DCMSystemIDType.MULTISYSTEMCHASSIS);
                serviceTag = cmcView.getServiceTag();
                hostname = cmcView.getHostName();
                systemInstance.setAgentName(WSCommandRNDConstant.CMC);
                model = cmcView.getModel();
            }
            system.setID(String.format("%04X", systemId));

            DCMI18NString displayI18NString1 = new DCMI18NString();
            displayI18NString1.setDefaultLocaleString(model);
            system.setName(displayI18NString1);

            systemInstance.setAgentVersion("");
            systemInstance.setCollectionTime(new Date());
            systemInstance.setServiceTag(serviceTag);
            systemInstance.setHostName(hostname);

            systemInstance.setSystemTypeIdentifier(system.getUniqueIdentifier());

            inRoot.getSystemCollection().addSystem(system);
            inRoot.getSystemInstanceCollection().addSystemInstance(systemInstance);

            // getting software identity XML
            Collection<DCIMSoftwareIdentity> softwareIdentity = inv.enumerate(WSManClassEnum.DCIM_SoftwareIdentity, DCIMSoftwareIdentity.class);

            if (softwareIdentity != null) {
                errorCode = parseSoftwareIdentity(inRoot, systemInstance, softwareIdentity, systemString);
                if (errorCode == DCMErrorCodes.FAILURE) {
                    return DCMErrorCodes.FAILURE;
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            return DCMErrorCodes.FAILURE;
        }
        return DCMErrorCodes.SUCCESS;
    }

    @Override
    public int addWSInventory(String iDRACipAddress, String iDRACport, DSMAuthenticationParameters authentication, DCMMultiSystemInventory inRoot, DSMProxy mProxy) {
        try {
            CollectInventory inv;
            if (mProxy != null) {
                if (null == iDRACport || iDRACport.isEmpty()) {
                    iDRACport = "443";
                }
                inv = new CollectInventory(iDRACipAddress, authentication.getUserName(), authentication.getPassword(), iDRACport, mProxy);
                return addWSInventory(inv, inRoot);
            } else {
                return addWSInventory(iDRACipAddress, authentication, inRoot);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            return DCMErrorCodes.FAILURE;
        }
    }

    /**
     * Method for adding the inventory information (WSMan call output) to the
     * multi system inventory
     *
     * @param iDRACipAddress specifies the iDRAC ip address
     * @param authentication specifies iDRAC system Username/password
     * @param inRoot specifies the root to which the parsed inventory is to be
     * added
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    @Override
    public int addWSInventory(String iDRACipAddress, DSMAuthenticationParameters authentication, DCMMultiSystemInventory inRoot) {
        try {
            CollectInventory inv = new CollectInventory(iDRACipAddress, authentication.getUserName(), authentication.getPassword(), "443");
            return addWSInventory(inv, inRoot);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            return DCMErrorCodes.FAILURE;
        }
    }

    /**
     * Method for adding the inventory information (RedFish call output) to the
     * multi system inventory
     *
     * @param inFile specifies the RedFish output to be parsed
     * @param inRoot specifies the root to which the parsed inventory is to be
     * added
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    @Override
    public int addRedFishInventory(File inFile, DCMMultiSystemInventory inRoot) {
        //TODO
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for adding the inventory from a set of files
     *
     * @param inFileSet specifies the file set from which the inventory is to be
     * populated
     * @param inIdentifier specifies the group identifier
     * @param inRoot specifies the root to which the inventory is to be added
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    @Override
    public int addInventory(HashSet<File> inFileSet, String inIdentifier, DCMMultiSystemInventory inRoot) {
        int retVal = DCMErrorCodes.SUCCESS;
        if (null == inFileSet) {
            return DCMErrorCodes.NULL_PARAMETER;
        }
        for (File inFile : inFileSet) {
            int compRetVal = addInventory(inFile, inIdentifier, inRoot, true);
            if (DCMErrorCodes.SUCCESS != compRetVal) {
                retVal = compRetVal;
            }
        }
        return retVal;
    }

    /**
     * Method for adding the inventory from a set of files (WSMan output)
     *
     * @param inFileSet specifies the file set from which the inventory is to be
     * populated
     * @param inRoot specifies the root to which the inventory is to be added
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    @Override
    public int addWSInventory(HashSet<File> inFileSet, DCMMultiSystemInventory inRoot) {
        int retVal = DCMErrorCodes.SUCCESS;
        if (null == inFileSet) {
            return DCMErrorCodes.NULL_PARAMETER;
        }
//        for (File inFile : inFileSet) {
//            int compRetVal = addWSInventory(inFile,inRoot);
//            if (DCMErrorCodes.SUCCESS != compRetVal)
//                retVal = compRetVal;
//        }
        return retVal;
    }

    /**
     * Method for adding the inventory from a set of files (RedFish output)
     *
     * @param inFileSet specifies the file set from which the inventory is to be
     * populated
     * @param inRoot specifies the root to which the inventory is to be added
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    @Override
    public int addRedFishInventory(HashSet<File> inFileSet, DCMMultiSystemInventory inRoot) {
        int retVal = DCMErrorCodes.SUCCESS;
        if (null == inFileSet) {
            return DCMErrorCodes.NULL_PARAMETER;
        }
        for (File inFile : inFileSet) {
            int compRetVal = addRedFishInventory(inFile, inRoot);
            if (DCMErrorCodes.SUCCESS != compRetVal) {
                retVal = compRetVal;
            }
        }
        return retVal;
    }

    /**
     * Method to parse/deserialize a XML file into an object
     *
     * @param inMultiInventoryFile which takes MultiInventory File
     * @param inRoot XML file is converted into this object
     * @return SUCCESS if the XML file is converted to an object otherwise error
     * code is returned
     */
    @Override
    public int deserializeXMLFile(File inMultiInventoryFile, DCMMultiSystemInventory inRoot) {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inMultiInventoryFile);
            Element documentRoot = doc.getDocumentElement();
            logger.log(Level.INFO, "RootElement: " + doc.getDocumentElement().getNodeName());

            NodeList listOfChildNodes = documentRoot.getChildNodes();

            if ((null != listOfChildNodes) && (listOfChildNodes.getLength() > 0)) {
                int noOfChildNodes = listOfChildNodes.getLength();

                for (int childIndex = 0; childIndex < noOfChildNodes; ++childIndex) {
                    Node childNode = listOfChildNodes.item(childIndex);
                    short childNodeType = childNode.getNodeType();
                    if (childNodeType == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNode;
                        String childName = childElement.getTagName();
                        logger.log(Level.INFO, "NextElement: " + childName);
                        if (childName.equals(DCMConstants.MULTIPLE_SYSTEM_INVENTORY)) {
                            parseMultipleSystemInventory(childElement, inRoot);
                        }

                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for outputting the inventory to the specified file
     *
     * @param inRoot specifies the multi system inventory
     * @param inOutFile specifies the file in which the output is to be written
     * @return SUCCESS if the output could be written else appropriate error
     * code is returned.
     */
    @Override
    public int serializeToFile(DCMMultiSystemInventory inRoot, File inOutFile) {
        if (null == inRoot || null == inOutFile) {
            return DCMErrorCodes.SUCCESS;
        }
        try {
            ByteArrayOutputStream byteOutputStream = serializeMultiSystemInventoryHelper(inRoot);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            byte[] bytes = byteOutputStream.toByteArray();
            InputStream myInputStream = new ByteArrayInputStream(bytes);
            transformer.transform(new StreamSource(myInputStream), new StreamResult(new FileOutputStream(inOutFile)));

        } catch (TransformerConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for outputting the inventory to the specified file
     *
     * @param inRoot specifies the multi system inventory
     * @return String of xml Document.
     */
    @Override
    public String serializeToString(DCMMultiSystemInventory inRoot) {
        try {
            ByteArrayOutputStream byteOutputStream = serializeMultiSystemInventoryHelper(inRoot);
            if (null == byteOutputStream) {
                return null;
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            byte[] bytes = byteOutputStream.toByteArray();
            InputStream myInputStream = new ByteArrayInputStream(bytes);
            OutputStream outStream = new ByteArrayOutputStream();
            transformer.transform(new StreamSource(myInputStream), new StreamResult(outStream));
            String outputString = outStream.toString();

            return outputString;

        } catch (TransformerConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private ByteArrayOutputStream serializeMultiSystemInventoryHelper(DCMMultiSystemInventory inRoot) {
        if (null == inRoot) {
            return null;
        }
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        ByteArrayOutputStream byteOutputStream = null;
        try {
            byteOutputStream = new ByteArrayOutputStream();
            try (
                    OutputStreamWriter osw = new OutputStreamWriter(byteOutputStream, DCMConstants.UTF8)) {
                XMLStreamWriter writer = factory.createXMLStreamWriter(osw);
                writer.writeStartDocument(DCMConstants.UTF8, "1.0");
                writer.writeStartElement(DCMConstants.RM_INTEGRATION_SERVICE_RESPONSE);
                writer.writeAttribute(DCMConstants.XMLNS, DCMConstants.DELL_SYSTEM_MANAGEMENT);

                writer.writeStartElement(DCMConstants.MULTIPLE_SYSTEM_INVENTORY);

                writer.writeStartElement(DCMConstants.LOB_COLLECTION);
                for (DCMLineOfBusiness lob : inRoot.getLineOfBusinessCollection().getLinesOfBusiness()) {
                    writer.writeStartElement(DCMConstants.LOB);
                    writer.writeAttribute(DCMConstants.LOB_KEY_ATTRIBUTE, Integer.toString(lob.getKey()));
                    writer.writeAttribute(DCMConstants.PREFIX_ATTRIBUTE, lob.getPrefix());
                    DCMI18NString name = lob.getName();
                    // Write the name
                    writer.writeEndElement();
                }
                writer.writeEndElement();

                writer.writeStartElement(DCMConstants.SYSTEM_TYPE_COLLECTION);
                for (DCMSystem system : inRoot.getSystemCollection().getSystems()) {
                    writer.writeStartElement(DCMConstants.SYSTEM);
                    writer.writeAttribute(DCMConstants.ID_ATTRIBUTE, system.getID());
                    writer.writeAttribute(DCMConstants.ID_TYPE_ATTRIBUTE, DCMSystemIDType.toString(system.getIDType()));
                    writer.writeAttribute(DCMConstants.UID_ATTRIBUTE, system.getUniqueIdentifier());
                    writer.writeStartElement(DCMConstants.NAME);
                    for (Locale locale : system.getName().getLocales()) {
                        writer.writeStartElement(DCMConstants.LOCAL_STRING);
                        writer.writeAttribute(DCMConstants.LOCALE_ATTRIBUTE, locale.getISO3Language());
                        writer.writeCharacters(system.getName().getDefaultLocaleString());
                        writer.writeEndElement();
                    }
                    writer.writeEndElement();
                    writer.writeEndElement();
                }
                writer.writeEndElement();

                writer.writeStartElement(DCMConstants.OPERATING_SYSTEM_TYPE_COLLECTION);
                for (DCMOperatingSystem os : inRoot.getOSCollection().getOperatingSystems()) {
                    writer.writeStartElement(DCMConstants.OPERATING_SYSTEM);
                    writer.writeAttribute(DCMConstants.OS_CODE_ATTRIBUTE, os.getUniqueIdentifier());
                    writer.writeAttribute(DCMConstants.OS_VENDOR_ATTRIBUTE, os.getVendor());
                    writer.writeAttribute(DCMConstants.OS_ARCH_ATTRIBUTE, DCMOSArchitecture.toString(os.getArchitecture()));
                    writer.writeAttribute(DCMConstants.MAJOR_VERSION_ATTRIBUTE, os.getMajorVersion());
                    writer.writeAttribute(DCMConstants.MINOR_VERSION_ATTRIBUTE, os.getMinorVersion());
                    writer.writeAttribute(DCMConstants.SP_MAJOR_VERSION_ATTRIBUTE, os.getServicePackMajorVersion());
                    writer.writeAttribute(DCMConstants.SP_MINOR_VERSION_ATTRIBUTE, os.getServicePackMinorVersion());
                    writer.writeAttribute(DCMConstants.OS_TYPE_ATTRIBUTE, DCMOSType.toString(os.getType()));
                    if (os.isForPreInstallEnvironment()) {
                        writer.writeAttribute(DCMConstants.PRE_INSTALLATION_ENVIRONMENT_ATTRIBUTE, "1");
                    } else {
                        writer.writeAttribute(DCMConstants.PRE_INSTALLATION_ENVIRONMENT_ATTRIBUTE, "0");
                    }
                    writer.writeEndElement();
                }
                writer.writeEndElement();

                writer.writeStartElement(DCMConstants.COMPONENT_KIND_COLLECTION);
                for (DCMComponentKind componentKind : inRoot.getComponentKindCollection().getComponentKinds()) {
                    writer.writeStartElement(DCMConstants.COMPONENT_KIND);
                    writer.writeAttribute(DCMConstants.TYPE_ATTRIBUTE, DCMComponentType.toString(componentKind.getType()));
                    writer.writeStartElement(DCMConstants.NAME);
                    for (Locale locale : componentKind.getName().getLocales()) {
                        writer.writeStartElement(DCMConstants.LOCAL_STRING);
                        writer.writeAttribute(DCMConstants.LOCALE_ATTRIBUTE, locale.getISO3Language());
                        writer.writeCharacters(componentKind.getName().getDefaultLocaleString());
                        writer.writeEndElement();
                    }
                    writer.writeEndElement();
                    writer.writeEndElement();
                }
                writer.writeEndElement();

                writer.writeStartElement(DCMConstants.ITEM_TYPE_COLLECTION);
                for (DCMBaseTarget item : inRoot.getTargetCollection().values()) {
                    writer.writeStartElement(DCMConstants.ITEM);
                    if (DCMTargetEntity.HARDWARE == item.getType()) {
                        writer.writeStartElement(DCMConstants.HWDEVICE);
                        DCMNonPCIPnPHardware hwDevice = (DCMNonPCIPnPHardware) item;
                        writer.writeAttribute(DCMConstants.COMPONENT_ID_ATTRIBUTE, Long.toString(hwDevice.getComponentID()));
                        writer.writeAttribute(DCMConstants.UID_ATTRIBUTE, item.getUniqueIdentifier());
                        writer.writeAttribute(DCMConstants.TYPE_ATTRIBUTE, DCMConstants.HARDWARE_ATTRIBUTE);
                        writer.writeStartElement(DCMConstants.NAME);
                        for (Locale locale : hwDevice.getName().getLocales()) {
                            writer.writeStartElement(DCMConstants.LOCAL_STRING);
                            writer.writeAttribute(DCMConstants.LOCALE_ATTRIBUTE, locale.getISO3Language());
                            writer.writeCharacters(hwDevice.getName().getDefaultLocaleString());
                            writer.writeEndElement();
                        }
                        writer.writeEndElement();
                        writer.writeEndElement();
                        writer.writeStartElement(DCMConstants.UPDATEABLE_COMPONENT_COLLECTION);
                        for (DCMUpdateableComponent updateableComponent : inRoot.getUpdateableComponentCollection().getUpdateableComponents(item.getUniqueIdentifier())) {
                            writer.writeStartElement(DCMConstants.UPDATEABLE_COMPONENT);
                            writer.writeAttribute(DCMConstants.TYPE_ATTRIBUTE, DCMComponentType.toString(updateableComponent.getComponentType()));
                            writer.writeAttribute(DCMConstants.UID_ATTRIBUTE, updateableComponent.getUniqueIdentifier());
                            if (null != updateableComponent.getName()) {
                                writer.writeStartElement(DCMConstants.NAME);
                                for (Locale locale : updateableComponent.getName().getLocales()) {
                                    writer.writeStartElement(DCMConstants.LOCAL_STRING);
                                    writer.writeAttribute(DCMConstants.LOCALE_ATTRIBUTE, locale.getISO3Language());
                                    writer.writeCharacters(updateableComponent.getName().getDefaultLocaleString());
                                    writer.writeEndElement();
                                }
                                writer.writeEndElement();
                            }
                            writer.writeEndElement();
                        }
                        writer.writeEndElement();
                    } else if (DCMTargetEntity.PCI == item.getType()) {
                        writer.writeStartElement(DCMConstants.PCIDEVICE);
                        DCMPCIDevice pciDevice = (DCMPCIDevice) item;
                        writer.writeAttribute(DCMConstants.VENDOR_ID_ATTRIBUTE, pciDevice.getPCIInfo().getVendorID());
                        writer.writeAttribute(DCMConstants.DEVICE_ID_ATTRIBUTE, pciDevice.getPCIInfo().getDeviceID());
                        writer.writeAttribute(DCMConstants.SUB_VENDOR_ID_ATTRIBUTE, pciDevice.getPCIInfo().getSubVendorID());
                        writer.writeAttribute(DCMConstants.SUB_DEVICE_ID_ATTRIBUTE, pciDevice.getPCIInfo().getSubDeviceID());
                        writer.writeAttribute(DCMConstants.TYPE_ATTRIBUTE, DCMConstants.PCI_ATTRIBUTE);
                        writer.writeAttribute(DCMConstants.UID_ATTRIBUTE, item.getUniqueIdentifier());
                        if (pciDevice.getName() != null) {
                            writer.writeStartElement(DCMConstants.NAME);
                            for (Locale locale : pciDevice.getName().getLocales()) {
                                writer.writeStartElement(DCMConstants.LOCAL_STRING);
                                writer.writeAttribute(DCMConstants.LOCALE_ATTRIBUTE, locale.getISO3Language());
                                writer.writeCharacters(pciDevice.getName().getDefaultLocaleString());
                                writer.writeEndElement();
                            }
                            writer.writeEndElement();
                        }
                        writer.writeEndElement();
                        writer.writeStartElement(DCMConstants.UPDATEABLE_COMPONENT_COLLECTION);
                        for (DCMUpdateableComponent updateableComponent : inRoot.getUpdateableComponentCollection().getUpdateableComponents(item.getUniqueIdentifier())) {
                            writer.writeStartElement(DCMConstants.UPDATEABLE_COMPONENT);
                            writer.writeAttribute(DCMConstants.TYPE_ATTRIBUTE, DCMComponentType.toString(updateableComponent.getComponentType()));
                            writer.writeAttribute(DCMConstants.UID_ATTRIBUTE, updateableComponent.getUniqueIdentifier());
                            if (null != updateableComponent.getName()) {
                                writer.writeStartElement(DCMConstants.NAME);
                                for (Locale locale : updateableComponent.getName().getLocales()) {
                                    writer.writeStartElement(DCMConstants.LOCAL_STRING);
                                    writer.writeAttribute(DCMConstants.LOCALE_ATTRIBUTE, locale.getISO3Language());
                                    writer.writeCharacters(updateableComponent.getName().getDefaultLocaleString());
                                    writer.writeEndElement();
                                }
                                writer.writeEndElement();
                            }
                            writer.writeEndElement();
                        }
                        writer.writeEndElement();
                    }
                    // write item
                    writer.writeEndElement();
                }
                writer.writeEndElement();

                writer.writeStartElement(DCMConstants.VERSION_INFO_COLLECTION_AGGREGATION);
                for (DCMVersionInformationCollection versionInfoCollection : inRoot.getCollectionOfVersionInformationCollections().getVersionInformationCollections()) {
                    writer.writeStartElement(DCMConstants.VERSION_INFO_COLLECTION);
                    writer.writeAttribute(DCMConstants.UID_ATTRIBUTE, Integer.toString(versionInfoCollection.getUniqueIdentifier()));
                    for (DCMVersionInformation versionInformation : versionInfoCollection.getVersionInformationObjects()) {
                        writer.writeStartElement(DCMConstants.VERSION_INFO);
                        writer.writeAttribute(DCMConstants.ITEM_ID_ATTRIBUTE, versionInformation.getTargetIdentifier());
                        if (null != versionInformation.getTargetInstance()) {
                            writer.writeAttribute(DCMConstants.ITEM_INSTANCE_ATTRIBUTE, versionInformation.getTargetInstance());
                        }
                        writer.writeAttribute(DCMConstants.UCID_ATTRIBUTE, versionInformation.getUpdateableComponentIdentifier());
                        writer.writeAttribute(DCMConstants.VERSION_ATTRIBUTE, versionInformation.getVersion());
                        writer.writeEndElement();
                        //writer.writeAttribute(DCMConstants.UID_ATTRIBUTE,Integer.toString(versionInfoCollection.getUniqueIdentifier()));
                    }
                    writer.writeStartElement(DCMConstants.SYSTEMS);
                    for (String systemInstanceIdentifier : versionInfoCollection.getSystemInstanceIdentifiers()) {
                        for (DCMSystemInstance systemInstance : inRoot.getSystemInstanceCollection().getSystemInstances()) {
                            if (systemInstanceIdentifier.equals(systemInstance.getServiceTag())) {
                                writer.writeStartElement(DCMConstants.SYSTEM_INSTANCE);
                                writer.writeAttribute(DCMConstants.SYSTEM_UID_ATTRIBUTE, systemInstance.getSystemTypeIdentifier());
                                writer.writeAttribute(DCMConstants.SERVICE_TAG_ATTRIBUTE, systemInstance.getServiceTag());
                                if (null != systemInstance.getHostOperatingSystemIdentifier()) {
                                    writer.writeAttribute(DCMConstants.HOST_OS_UID_ATTRIBUTE, systemInstance.getHostOperatingSystemIdentifier());
                                } else {
                                    writer.writeAttribute(DCMConstants.HOST_OS_UID_ATTRIBUTE, "");
                                }
                                writer.writeAttribute(DCMConstants.COLLECTION_AGENT_ATTRIBUTE, systemInstance.getAgentName());
                                writer.writeAttribute(DCMConstants.AGENT_VERSION_ATTRIBUTE, systemInstance.getAgentVersion());
                                DateFormat timeStampDateFormat = new SimpleDateFormat(DCMConstants.DATE_TIME_FORMAT);
                                Date timeStampDate = systemInstance.getCollectionTime();
                                if (null != timeStampDate) {
                                    try {
                                        String timeStampString = timeStampDateFormat.format(timeStampDate);
                                        writer.writeAttribute(DCMConstants.COLLECTION_TIME_ATTRIBUTE, timeStampString);
                                    } catch (Exception ex) {
                                        logger.log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    writer.writeAttribute(DCMConstants.COLLECTION_TIME_ATTRIBUTE, "");
                                }
                                writer.writeEndElement();
                            }
                        }
                    }
                    writer.writeEndElement();
                    writer.writeEndElement();
                }
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeEndDocument();
            }

        } catch (FileNotFoundException | UnsupportedEncodingException | XMLStreamException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return byteOutputStream;
    }

    private DCMOperatingSystem parseOperatingSystem(Element inOSNode, DCMMultiSystemInventory inRoot) {
        if (null == inOSNode || null == inRoot) {
            return null;
        }
        DCMOperatingSystemCollection osCollection = inRoot.getOSCollection();
        DCMOperatingSystem os = new DCMOperatingSystem();
        String osCode = inOSNode.getAttribute(DCMConstants.OS_CODE_ATTRIBUTE);
        if (null != osCode) {
            os.setCode(osCode);
        }
        String osVendor = inOSNode.getAttribute(DCMConstants.OS_VENDOR_ATTRIBUTE);
        if (null != osVendor) {
            os.setVendor(osVendor);
        }
        String osArch = inOSNode.getAttribute(DCMConstants.OS_ARCH_ATTRIBUTE);
        if (null != osArch) {
            os.setArchitecture(DCMOSArchitecture.getEnumeration(osArch));
        }
        String osType = inOSNode.getAttribute(DCMConstants.OS_TYPE_ATTRIBUTE);
        if (null != osType) {
            os.setType(DCMOSType.getEnumeration(osType));
        }
        String majorVersion = inOSNode.getAttribute(DCMConstants.MAJOR_VERSION_ATTRIBUTE);
        if (null != majorVersion) {
            os.setMajorVersion(majorVersion);
        }
        String minorVersion = inOSNode.getAttribute(DCMConstants.MINOR_VERSION_ATTRIBUTE);
        if (null != minorVersion) {
            os.setMinorVersion(minorVersion);
        }
        String spMajorVersion = inOSNode.getAttribute(DCMConstants.SP_MAJOR_VERSION_ATTRIBUTE);
        if (null != spMajorVersion) {
            os.setServicePackMajorVersion(spMajorVersion);
        }
        String spMinorVersion = inOSNode.getAttribute(DCMConstants.SP_MINOR_VERSION_ATTRIBUTE);
        if (null != spMinorVersion) {
            os.setServicePackMinorVersion(spMinorVersion);
        }
        String preInstallEnvironment = inOSNode.getAttribute(DCMConstants.PRE_INSTALLATION_ENVIRONMENT_ATTRIBUTE);
        if (null != preInstallEnvironment) {
            if (preInstallEnvironment.equals(DCMConstants.TRUE)) {
                os.setForPreInstallEnvironment(true);
            } else {
                os.setForPreInstallEnvironment(false);
            }
        }
        String suiteMask = inOSNode.getAttribute(DCMConstants.SUITE_MASK_ATTRIBUTE);
        if (null != suiteMask && !suiteMask.isEmpty()) {
            os.setFlavorMask(Integer.parseInt(suiteMask));
        }
        int addOperatingSystem = osCollection.addOperatingSystem(os);
        if (DCMErrorCodes.SUCCESS != addOperatingSystem) {
            logger.log(Level.INFO, "Could not add Operating System");
        }
        return os;
    }

    private DCMSystem parseSystemDCMIC(DCMInventoryComponent inComponent, DCMMultiSystemInventory inRoot) {
        if (null == inComponent || null == inRoot) {
            return null;
        }

        return null;
    }

    private DCMSystem parseSystem(Element inSystemNode, DCMMultiSystemInventory inRoot) {
        if (null == inSystemNode || null == inRoot) {
            return null;
        }
        String systemIDValue = inSystemNode.getAttribute(DCMConstants.SYSTEM_ID_ATTRIBUTE);
        if (null == systemIDValue) {
            return null;
        }
        DCMSystem system = new DCMSystem();
        String serviceTagValue;
        if (null != system) {
            system.setID(systemIDValue);
            system.setLOBKey(3);
            String systemIDTypeValue = inSystemNode.getAttribute(DCMConstants.SYSTEM_ID_TYPE_ATTRIBUTE);
            if (null != systemIDTypeValue) {
                system.setIDType(DCMSystemIDType.getEnumeration(systemIDTypeValue));
            } else {
                system.setIDType(DCMSystemIDType.BIOS);
            }
            String displayValue = inSystemNode.getAttribute(DCMConstants.DISPLAY_ATTRIBUTE);
            DCMI18NString displayI18NString = new DCMI18NString();
            displayI18NString.setDefaultLocaleString(displayValue);
            system.setName(displayI18NString);
            serviceTagValue = inSystemNode.getAttribute(DCMConstants.SERVICE_TAG_ATTRIBUTE);
        } else {
            return null;
        }
        DCMVersionInformationCollection versionInfoCollection = new DCMVersionInformationCollection();
        DCMSystemInstance systemInstance = new DCMSystemInstance();
        DCMOperatingSystem os = null;
        boolean isParsingCMCInventory = false;
        NodeList childNodeList = inSystemNode.getChildNodes();
        if ((null != childNodeList) && (childNodeList.getLength() > 0)) {
            int numberOfChildren = childNodeList.getLength();
            for (int childIndex = 0; childIndex < numberOfChildren; ++childIndex) {
                Node childNode = childNodeList.item(childIndex);
                short nodeType = childNode.getNodeType();
                if (nodeType == Node.ELEMENT_NODE) {
                    Element childElement = (Element) childNode;
                    String childName = childElement.getTagName();
                    if (childName.equals(DCMConstants.DEVICE)) {
                        DCMVersionInformation versionInformation = parseDevice(childElement, inRoot);

                        if (null != versionInformation) {
                            String targetIdentifier = versionInformation.getTargetIdentifier();

                            // getting the Display Name of the target and adding it to target Instance
                            DCMBaseTarget baseTarget = inRoot.getTargetCollection().get(targetIdentifier);
                            DCMI18NString displayName = baseTarget.getName();

                            String targetInstance = versionInformation.getTargetInstance();
                            String displayValue = "";
                            if (displayName.getLocales().contains(Locale.ENGLISH)) {
                                displayValue = displayName.getLocaleString(Locale.ENGLISH);
                            }
                            if (!displayValue.equals(targetInstance)) {
                                versionInformation.setTargetInstance(targetInstance);
                            } else {
                                versionInformation.setTargetInstance(displayValue);
                            }
                            versionInfoCollection.addVersionInformation(versionInformation);

                            isParsingCMCInventory = true;
                        }
                    }
                    if (childName.equals(DCMConstants.DISPLAY)) {
                        //TODO get system name here
                    } else if (childName.equals(DCMConstants.OPERATING_SYSTEM)) {
                        os = parseOperatingSystem(childElement, inRoot);
                    }
                }
            }
        }
        inRoot.getSystemCollection().addSystem(system);
        boolean isVersionInfoCollectionAlreadyExist = false;
        if (null != system && isParsingCMCInventory) {
            for (DCMVersionInformationCollection versionInformationCollection : inRoot.getCollectionOfVersionInformationCollections().getVersionInformationCollections()) {
                if (versionInformationCollection.areSameCollections(versionInfoCollection)) {
                    versionInfoCollection = versionInformationCollection;
                    isVersionInfoCollectionAlreadyExist = true;
                }
            }
            //UUID uid = UUID.randomUUID();
            //versionInfoCollection.setUniqueIdentifier(uid.hashCode());
            systemInstance.setSystemTypeIdentifier(system.getUniqueIdentifier());
            if (serviceTagValue.equals("") && null != mIdentifier) {
                systemInstance.setServiceTag(mIdentifier.concat(Integer.toString(mSystemIndex)));
                versionInfoCollection.addSystemInstance(mIdentifier.concat(Integer.toString(mSystemIndex)));
                mSystemIndex++;
            } else {
                systemInstance.setServiceTag(serviceTagValue);
                versionInfoCollection.addSystemInstance(serviceTagValue);
            }
            if (null != os) {
                systemInstance.setHostOperatingSystemIdentifier(os.getUniqueIdentifier());
            }
            if (inSystemNode.getAttribute(DCMConstants.INVENTORY_COLLECTOR_NAME_ATTRIBUTE).equals("")) {
                systemInstance.setAgentName(DCMConstants.CMC);
            } else {
                systemInstance.setAgentName(DCMConstants.INVENTORY_COLLECTOR_NAME_ATTRIBUTE);
            }
            systemInstance.setAgentVersion(inSystemNode.getAttribute(DCMConstants.INVENTORY_COLLECTOR_VERSION_ATTRIBUTE));
            String timeStampString = inSystemNode.getAttribute(DCMConstants.TIME_STAMP_ATTRIBUTE);
            DateFormat timeStampDateFormat = new SimpleDateFormat(DCMConstants.DATE_TIME_FORMAT);
            if (null != timeStampString && !timeStampString.equals("")) {
                try {
                    Date timeStampDate = timeStampDateFormat.parse(timeStampString);
                    systemInstance.setCollectionTime(timeStampDate);
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            } else {
                systemInstance.setCollectionTime(null);
            }
            inRoot.getSystemInstanceCollection().addSystemInstance(systemInstance);
            if (!isVersionInfoCollectionAlreadyExist) {
                int index = inRoot.getCollectionOfVersionInformationCollections().getVersionInformationCollections().size() + 1;
                versionInfoCollection.setUniqueIdentifier(index);
                inRoot.getCollectionOfVersionInformationCollections().addVersionInformationCollection(versionInfoCollection);
            }
        }
        return system;
    }

    private DCMVersionInformation parseDeviceDCMIC(DCMInventoryComponent inComponent, DCMMultiSystemInventory inRoot) {
        if (null == inComponent || null == inRoot) {
            return null;
        }
        String componentIDValue = inComponent.getComponentID();

        String vendorIDValue = inComponent.getVendorID();
        DCMHexBinary4 vendorID = null;
        if (null != vendorIDValue && !vendorIDValue.isEmpty()) {
            vendorID = new DCMHexBinary4();
            vendorID.setValue(vendorIDValue);
        }

        String deviceIDValue = inComponent.getDeviceID();
        DCMHexBinary4 deviceID = null;
        if (null != deviceIDValue && !deviceIDValue.isEmpty()) {
            deviceID = new DCMHexBinary4();
            deviceID.setValue(deviceIDValue);
        }

        String subDeviceIDValue = inComponent.getSubDeviceID();
        DCMHexBinary4 subDeviceID = null;
        if (null != subDeviceIDValue && !subDeviceIDValue.isEmpty()) {
            subDeviceID = new DCMHexBinary4();
            subDeviceID.setValue(subDeviceIDValue);
        }

        String subVendorIDValue = inComponent.getSubDeviceID();
        DCMHexBinary4 subVendorID = null;
        if (null != subVendorIDValue && !subVendorIDValue.isEmpty()) {
            subVendorID = new DCMHexBinary4();
            subVendorID.setValue(subVendorIDValue);
        }

        DCMBaseTarget target = null;
        DCMPCIInfo pciInfo = null;
        if (null != vendorID && null != deviceID) {
            pciInfo = new DCMPCIInfo(vendorID, deviceID, subVendorID, subDeviceID);
        }

        String displayValue = inComponent.getDeviceDisplay();

        if (null != pciInfo) {
            DCMPCIDevice pciDevice = new DCMPCIDevice(pciInfo);
            if (null != componentIDValue && !componentIDValue.isEmpty()) {
                pciDevice.setComponentID(Long.getLong(componentIDValue));
            }
            if (null != displayValue) {
                DCMI18NString name = new DCMI18NString();
                name.setDefaultLocaleString(displayValue);
                pciDevice.setName(name);
            }
            target = pciDevice;

            if (!(inRoot.getTargetCollection().containsKey(pciDevice.getUniqueIdentifier()))) {
                inRoot.getTargetCollection().put(pciDevice.getUniqueIdentifier(), pciDevice);
            }

        } else if (null != componentIDValue && !componentIDValue.isEmpty()) {
            DCMNonPCIPnPHardware hardware = new DCMNonPCIPnPHardware(Long.parseLong(componentIDValue));
            if (null != displayValue) {
                DCMI18NString name = new DCMI18NString();
                name.setDefaultLocaleString(displayValue);
                hardware.setName(name);
            }
            target = hardware;

            if (!(inRoot.getTargetCollection().containsKey(hardware.getUniqueIdentifier()))) {
                inRoot.getTargetCollection().put(hardware.getUniqueIdentifier(), hardware);
            }
        }

        if (null == target) {
            return null;
        }

        String instanceValue = inComponent.getDeviceType();
        if (instanceValue.equals("")) {
            instanceValue = inComponent.getComponentInstance();
            if (instanceValue.equals("")) {
                instanceValue = inComponent.getDeviceDisplay();
            }
        }

        String componentTypeValue = inComponent.getComponentType();
        displayValue = inComponent.getDeviceName();
        DCMI18NString displayI18NString = new DCMI18NString();
        displayI18NString.setDefaultLocaleString(DCMComponentType.getEnumeration(componentTypeValue).name());
        inRoot.getComponentKindCollection().addComponentKind(DCMComponentType.getEnumeration(componentTypeValue), displayI18NString);
        if (null != componentTypeValue && !componentTypeValue.isEmpty()) {
            DCMUpdateableComponent uc = new DCMUpdateableComponent(DCMComponentType.getEnumeration(componentTypeValue), target.getUniqueIdentifier());
            if (null != displayValue && !displayValue.isEmpty()) {
                DCMI18NString name = new DCMI18NString();
                name.setDefaultLocaleString(displayValue);
                uc.setName(name);
            }
            inRoot.getUpdateableComponentCollection().addUpdateableComponent(uc);
            DCMVersionInformation versionInformation = new DCMVersionInformation();
            versionInformation.setTargetIdentifier(target.getUniqueIdentifier());
            versionInformation.setUpdateableComponentIdentifier(uc.getUniqueIdentifier());
            if (null != instanceValue && !instanceValue.isEmpty()) {
                versionInformation.setTargetInstance(instanceValue);
            }
            String versionValue = inComponent.getVersion();
            if (null != versionValue && !versionValue.isEmpty()) {
                versionInformation.setVersion(versionValue);
            }
            return versionInformation;
        }

        return null;
    }

    private DCMVersionInformation parseDevice(Element inDeviceNode, DCMMultiSystemInventory inRoot) {
        if (null == inDeviceNode || null == inRoot) {
            return null;
        }
        String componentIDValue = inDeviceNode.getAttribute(DCMConstants.COMPONENT_ID_ATTRIBUTE);

        String vendorIDValue = inDeviceNode.getAttribute(DCMConstants.VENDOR_ID_ATTRIBUTE);
        DCMHexBinary4 vendorID = null;
        if (null != vendorIDValue && !vendorIDValue.isEmpty()) {
            vendorID = new DCMHexBinary4();
            vendorID.setValue(vendorIDValue);
        }
        String deviceIDValue = inDeviceNode.getAttribute(DCMConstants.DEVICE_ID_ATTRIBUTE);
        DCMHexBinary4 deviceID = null;
        if (null != deviceIDValue && !deviceIDValue.isEmpty()) {
            deviceID = new DCMHexBinary4();
            deviceID.setValue(deviceIDValue);
        }
        String subDeviceIDValue = inDeviceNode.getAttribute(DCMConstants.SUB_DEVICE_ID_ATTRIBUTE);
        DCMHexBinary4 subDeviceID = null;
        if (null != subDeviceIDValue && !subDeviceIDValue.isEmpty()) {
            subDeviceID = new DCMHexBinary4();
            subDeviceID.setValue(subDeviceIDValue);
        }

        String subVendorIDValue = inDeviceNode.getAttribute(DCMConstants.SUB_VENDOR_ID_ATTRIBUTE);
        DCMHexBinary4 subVendorID = null;
        if (null != subVendorIDValue && !subVendorIDValue.isEmpty()) {
            subVendorID = new DCMHexBinary4();
            subVendorID.setValue(subVendorIDValue);
        }

        DCMBaseTarget target = null;
        DCMPCIInfo pciInfo = null;
        if (null != vendorID && null != deviceID) {
            pciInfo = new DCMPCIInfo(vendorID, deviceID, subVendorID, subDeviceID);
        }

        String displayValue = inDeviceNode.getAttribute(DCMConstants.DISPLAY_ATTRIBUTE);

        if (null != pciInfo) {
            DCMPCIDevice pciDevice = new DCMPCIDevice(pciInfo);
            if (null != componentIDValue && !componentIDValue.isEmpty()) {
                pciDevice.setComponentID(Long.getLong(componentIDValue));
            }
            if (null != displayValue) {
                DCMI18NString name = new DCMI18NString();
                name.setDefaultLocaleString(displayValue);
                pciDevice.setName(name);
            }
            target = pciDevice;

            if (!(inRoot.getTargetCollection().containsKey(pciDevice.getUniqueIdentifier()))) {
                inRoot.getTargetCollection().put(pciDevice.getUniqueIdentifier(), pciDevice);
            }

        } else if (null != componentIDValue && !componentIDValue.isEmpty()) {
            DCMNonPCIPnPHardware hardware = new DCMNonPCIPnPHardware(Long.parseLong(componentIDValue));
            if (null != displayValue) {
                DCMI18NString name = new DCMI18NString();
                name.setDefaultLocaleString(displayValue);
                hardware.setName(name);
            }
            target = hardware;

            if (!(inRoot.getTargetCollection().containsKey(hardware.getUniqueIdentifier()))) {
                inRoot.getTargetCollection().put(hardware.getUniqueIdentifier(), hardware);
            }
        }

        if (null == target) {
            return null;
        }

        String instanceValue = inDeviceNode.getAttribute(DCMConstants.ENUM_ATTRIBUTE);
        if (instanceValue.equals("")) {
            instanceValue = inDeviceNode.getAttribute(DCMConstants.COMPONENT_INSTANCE_ATTRIBUTE);
            if (instanceValue.equals("")) {
                instanceValue = inDeviceNode.getAttribute(DCMConstants.DISPLAY_ATTRIBUTE);
            }

        }
        NodeList childNodeList = inDeviceNode.getChildNodes();
        if ((null != childNodeList) && (childNodeList.getLength() > 0)) {
            int numberOfChildren = childNodeList.getLength();
            for (int childIndex = 0; childIndex < numberOfChildren; ++childIndex) {
                Node childNode = childNodeList.item(childIndex);
                short nodeType = childNode.getNodeType();
                if (nodeType == Node.ELEMENT_NODE) {
                    Element childElement = (Element) childNode;
                    if (childElement.getTagName().equals(DCMConstants.APPLICATION)) {
                        String componentTypeValue = childElement.getAttribute(DCMConstants.COMPONENT_TYPE_ATTRIBUTE);
                        displayValue = childElement.getAttribute(DCMConstants.DISPLAY_ATTRIBUTE);
                        DCMI18NString displayI18NString = new DCMI18NString();
                        displayI18NString.setDefaultLocaleString(DCMComponentType.getEnumeration(componentTypeValue).name());
                        inRoot.getComponentKindCollection().addComponentKind(DCMComponentType.getEnumeration(componentTypeValue), displayI18NString);
                        if (null != componentTypeValue && !componentTypeValue.isEmpty()) {
                            DCMUpdateableComponent uc = new DCMUpdateableComponent(DCMComponentType.getEnumeration(componentTypeValue), target.getUniqueIdentifier());
                            if (null != displayValue && !displayValue.isEmpty()) {
                                DCMI18NString name = new DCMI18NString();
                                name.setDefaultLocaleString(displayValue);
                                uc.setName(name);
                            }
                            inRoot.getUpdateableComponentCollection().addUpdateableComponent(uc);
                            DCMVersionInformation versionInformation = new DCMVersionInformation();
                            versionInformation.setTargetIdentifier(target.getUniqueIdentifier());
                            versionInformation.setUpdateableComponentIdentifier(uc.getUniqueIdentifier());
                            if (null != instanceValue && !instanceValue.isEmpty()) {
                                versionInformation.setTargetInstance(instanceValue);
                            }
                            String versionValue = childElement.getAttribute(DCMConstants.VERSION_ATTRIBUTE);
                            if (null != versionValue && !versionValue.isEmpty()) {
                                versionInformation.setVersion(versionValue);
                            }
                            return versionInformation;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method to parse LOBCollection Tag
     *
     * @param inLOBNode Specifies Lob Nodes
     * @param inRoot specifies an object
     */
    private void parseLOBCollectionObject(Element inLOBNode, DCMMultiSystemInventory inRoot) {
        if (null == inLOBNode || null == inRoot) {
            return;
        }

        logger.log(Level.INFO, "parsing LOBCollection Tag ");

        DCMLineOfBusinessCollection lobCollection = inRoot.getLineOfBusinessCollection();

        NodeList listOfLOBChildNodes = inLOBNode.getChildNodes();

        for (int index = 0; index < listOfLOBChildNodes.getLength(); index++) {
            Node lobNode = listOfLOBChildNodes.item(index);
            short type = lobNode.getNodeType();
            if (type == Node.ELEMENT_NODE) {
                Element osElement = (Element) lobNode;
                String name = osElement.getNodeName();

                logger.log(Level.INFO, "Under LOBCollection : nextElement " + name);

                if (name.equals(DCMConstants.LOB)) {
                    DCMLineOfBusiness lob = new DCMLineOfBusiness();

                    String lobKey = inLOBNode.getAttribute(DCMConstants.LOB_KEY_ATTRIBUTE);
                    logger.log(Level.INFO, "printing LobKey value " + lobKey);
                    if (null != lobKey && lobKey != "") {
                        lob.setKey(Integer.parseInt(lobKey));
                    }

                    String lobPrefix = inLOBNode.getAttribute(DCMConstants.PREFIX_ATTRIBUTE);
                    logger.log(Level.INFO, "printing lobPrefix Value " + lobPrefix);
                    if (null != lobPrefix) {
                        lob.setPrefix(lobPrefix);
                    }

                    DCMI18NString nameAttr = getDisplay(inLOBNode);
                    lob.setName(nameAttr);

                    int addLineOfBusiness = lobCollection.addLineOfBusiness(lob);
                    if (DCMErrorCodes.SUCCESS != addLineOfBusiness) {
                        logger.log(Level.INFO, "Could not write into LineOfBusiness Object");
                    }
                }
            }
        }
    }

    /**
     * Method to parse MultiSystemInventory Tag
     *
     * @param childElement specifies the MultiSystemInventory Nodes
     * @param inRoot specifies an object
     * @return child name of SystemInventory Tag
     */
    private int parseMultipleSystemInventory(Element childElement, DCMMultiSystemInventory inRoot) {
        NodeList listOfChildNodes = childElement.getChildNodes();
        String systemInventoryChildName = null;

        if ((null != listOfChildNodes) && (listOfChildNodes.getLength() > 0)) {
            for (int index = 0; index < listOfChildNodes.getLength(); ++index) {
                Node sysInvChildNode = listOfChildNodes.item(index);
                short type = sysInvChildNode.getNodeType();
                if (type == Node.ELEMENT_NODE) {
                    Element systemInventorychildElement = (Element) sysInvChildNode;
                    systemInventoryChildName = systemInventorychildElement.getTagName();
                    if (systemInventoryChildName.equals(DCMConstants.LOB_COLLECTION)) {
                        logger.log(Level.FINER, "Inside LOBCollection Tag " + systemInventoryChildName);
                        parseLOBCollectionObject(systemInventorychildElement, inRoot);
                    } else if (systemInventoryChildName.equals(DCMConstants.SYSTEM_TYPE_COLLECTION)) {
                        logger.log(Level.FINER, "Inside SystemTypeCollection Tag " + systemInventoryChildName);
                        parseSystemType(systemInventorychildElement, inRoot);
                    } else if (systemInventoryChildName.equals(DCMConstants.OPERATING_SYSTEM_TYPE_COLLECTION)) {
                        logger.log(Level.FINER, "Inside OperatingSystemTypeCollection Tag " + systemInventoryChildName);
                        parseOperatingSystemType(systemInventorychildElement, inRoot);
                    } else if (systemInventoryChildName.equals(DCMConstants.COMPONENT_KIND_COLLECTION)) {
                        logger.log(Level.FINER, "Inside ComponentKindCollection Tag " + systemInventoryChildName);
                        parseComponentKind(systemInventorychildElement, inRoot);
                    } else if (systemInventoryChildName.equals(DCMConstants.ITEM_TYPE_COLLECTION)) {
                        logger.log(Level.FINER, "Inside ItemTypeCollection Tag " + systemInventoryChildName);
                        parseItemTypeCollection(systemInventorychildElement, inRoot);
                    } else if (systemInventoryChildName.equals(DCMConstants.VERSION_INFO_COLLECTION_AGGREGATION)) {
                        logger.log(Level.FINER, "Inside VersionInfoCollectionAggregation Tag " + systemInventoryChildName);
                        parseVersionInfoCollection(systemInventorychildElement, inRoot);
                    }
                }
            }
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method to parse SystemTypeCollection
     *
     * @param inSystemNode specifies system type node
     * @param inRoot specifies object
     */
    private void parseSystemType(Element inSystemNode, DCMMultiSystemInventory inRoot) {

        if (null == inSystemNode || null == inRoot) {
            return;
        }
        logger.log(Level.INFO, "parsing SystemTypeCollection Tag ");
        NodeList listOfSystemNodes = inSystemNode.getChildNodes();
        DCMSystemCollection systemCollection = inRoot.getSystemCollection();
        Element systemElement = null;

        for (int index = 0; index < listOfSystemNodes.getLength(); index++) {
            Node systemNode = listOfSystemNodes.item(index);
            short type = systemNode.getNodeType();
            if (type == Node.ELEMENT_NODE) {
                systemElement = (Element) systemNode;
                String name = systemElement.getNodeName();
                logger.log(Level.INFO, "Under SystemType : Next Element " + name);
                if (name.equals(DCMConstants.SYSTEM)) {
                    DCMSystem system = new DCMSystem();
                    String systemId = systemElement.getAttribute(DCMConstants.ID_ATTRIBUTE);
                    logger.log(Level.INFO, "Printing Systemid value " + systemId);
                    if (null != systemId) {
                        system.setID(systemId);
                    }
                    String systemIdType = systemElement.getAttribute(DCMConstants.ID_TYPE_ATTRIBUTE);
                    logger.log(Level.INFO, "Printing SystemIdType value " + systemIdType);
                    if (null != systemIdType) {
                        system.setIDType(DCMSystemIDType.getEnumeration(systemIdType));
                    }
                    DCMI18NString nameAttr = getDisplay(systemElement);
                    system.setName(nameAttr);
                    systemCollection.addSystem(system);
                }
            }
        }
    }

    /**
     * Method to parse OperatingSystemTypeCollection
     *
     * @param inOSTypeNode specifies the OperatingSystem Type Element
     * @param inRoot specifies an object
     */
    private void parseOperatingSystemType(Element inOSTypeNode, DCMMultiSystemInventory inRoot) {
        if (null == inOSTypeNode || null == inRoot) {
            return;
        }
        logger.log(Level.INFO, "parsing OperatingSystemTypeCollection Tag ");
        DCMOperatingSystemCollection OSCollection = inRoot.getOSCollection();
        NodeList listOfOSNodes = inOSTypeNode.getChildNodes();

        for (int index = 0; index < listOfOSNodes.getLength(); index++) {
            Node OSNode = listOfOSNodes.item(index);
            short type = OSNode.getNodeType();
            if (type == Node.ELEMENT_NODE) {
                Element osElement = (Element) OSNode;
                String name = osElement.getNodeName();
                logger.log(Level.INFO, "Under OperatingSystemType : Next Element " + name);
                if (name.equals(DCMConstants.OPERATING_SYSTEM)) {
                    DCMOperatingSystem os = new DCMOperatingSystem();
                    String osCode = osElement.getAttribute(DCMConstants.OS_CODE_ATTRIBUTE);
                    logger.log(Level.FINER, "Printing osCode value " + osCode);
                    if (null != osCode) {
                        os.setCode(osCode);
                    }
                    String osVendor = osElement.getAttribute(DCMConstants.OS_VENDOR_ATTRIBUTE);
                    logger.log(Level.FINER, "Printing osVendor value " + osVendor);
                    if (null != osVendor) {
                        os.setVendor(osVendor);
                    }
                    String osArch = osElement.getAttribute(DCMConstants.OS_ARCH_ATTRIBUTE);
                    logger.log(Level.FINER, "Printing osArch value " + osArch);
                    if (null != osArch) {
                        os.setArchitecture(DCMOSArchitecture.getEnumeration(osArch));
                    }
                    String majorVersion = osElement.getAttribute(DCMConstants.MAJOR_VERSION_ATTRIBUTE);
                    logger.log(Level.FINER, "Printing majorVersion value " + majorVersion);
                    if (null != majorVersion) {
                        os.setMajorVersion(majorVersion);
                    }
                    String minorVersion = osElement.getAttribute(DCMConstants.MINOR_VERSION_ATTRIBUTE);
                    logger.log(Level.FINER, "Printing minorVersion value " + minorVersion);
                    if (null != minorVersion) {
                        os.setMinorVersion(minorVersion);
                    }
                    String spMajorVersion = osElement.getAttribute(DCMConstants.SP_MAJOR_VERSION_ATTRIBUTE);
                    logger.log(Level.FINER, "Printing spMajorVersion value " + spMajorVersion);
                    if (null != spMajorVersion) {
                        os.setServicePackMajorVersion(spMajorVersion);
                    }
                    String spMinorVersion = osElement.getAttribute(DCMConstants.SP_MINOR_VERSION_ATTRIBUTE);
                    logger.log(Level.FINER, "Printing spMinorVersion value " + spMinorVersion);
                    if (null != spMinorVersion) {
                        os.setServicePackMinorVersion(spMinorVersion);
                    }
                    String osType = osElement.getAttribute(DCMConstants.OS_TYPE_ATTRIBUTE);
                    logger.log(Level.FINER, "Printing osType value " + osType);
                    if (null != osType) {
                        os.setType(DCMOSType.getEnumeration(osType));
                    }
                    String enviAttr = osElement.getAttribute(DCMConstants.PRE_INSTALLATION_ENVIRONMENT_ATTRIBUTE);
                    logger.log(Level.FINER, "Printing enviAttr value " + enviAttr);
                    if (null != enviAttr) {
                        if (enviAttr.equals("1")) {
                            os.setForPreInstallEnvironment(true);
                        } else {
                            os.setForPreInstallEnvironment(false);
                        }
                    }

                    OSCollection.addOperatingSystem(os);
                }
            }
        }
    }

    /**
     * Method to parse ComponentKindCollection Tag
     *
     * @param inComponentKindNode specifies componentKind Nodes
     * @param inRoot specifies an object
     */
    private void parseComponentKind(Element inComponentKindNode, DCMMultiSystemInventory inRoot) {
        if (null == inComponentKindNode || null == inRoot) {
            return;
        }

        logger.log(Level.INFO, "parsing ComponentKindCollection Tag ");

        DCMComponentKindCollection compKindCollection = inRoot.getComponentKindCollection();

        NodeList listOfCompKindNodes = inComponentKindNode.getChildNodes();

        for (int index = 0; index < listOfCompKindNodes.getLength(); index++) {
            Node compKindNode = listOfCompKindNodes.item(index);
            short type = compKindNode.getNodeType();
            if (type == Node.ELEMENT_NODE) {
                Element compKindElement = (Element) compKindNode;
                String name = compKindElement.getNodeName();
                logger.log(Level.INFO, "Under ComponentKind : Next Element " + name);
                if (name.equals(DCMConstants.COMPONENT_KIND)) {
                    DCMComponentKind componentKind = null;
                    String typeAttr = compKindElement.getAttribute(DCMConstants.TYPE_ATTRIBUTE);
                    logger.log(Level.INFO, "Printing typeAttr value " + typeAttr);
                    if (null != typeAttr) {
                        componentKind = new DCMComponentKind(DCMComponentType.getEnumeration(typeAttr));
                        DCMI18NString nameAttr = getDisplay(compKindElement);
                        componentKind.setName(nameAttr);
                        compKindCollection.addComponentKind(componentKind);
                    }
                }
            }
        }
    }

    /**
     * Method to parse an ItemTypeCollection Tag
     *
     * @param inItemTypeNode specifies ItemType Nodes
     * @param inRoot specifies an Object
     */
    private void parseItemTypeCollection(Element inItemTypeNode, DCMMultiSystemInventory inRoot) {
        if (null == inItemTypeNode || null == inRoot) {
            return;
        }

        logger.log(Level.INFO, "parsing ItemTypeCollection Tag ");

        DCMUpdateableComponentCollection compCollection = inRoot.getUpdateableComponentCollection();

        NodeList listOfItems = inItemTypeNode.getElementsByTagName(DCMConstants.ITEM);
        for (int temp = 0; temp < listOfItems.getLength(); temp++) {
            Node node = listOfItems.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element type = (Element) node;
                DCMBaseTarget itemType = null;
                NodeList allNodes = type.getChildNodes();
                for (int index = 0; index < allNodes.getLength(); index++) {
                    Node nodeType = allNodes.item(index);
                    if (nodeType.getNodeName().equals(DCMConstants.HWDEVICE)) {

                        Element hardwareDevice = (Element) nodeType;
                        String componentId = hardwareDevice.getAttribute(DCMConstants.COMPONENT_ID_ATTRIBUTE);
                        logger.log(Level.INFO, "Inside HWDevice : componentId value" + componentId);
                        String uid = hardwareDevice.getAttribute(DCMConstants.UID_ATTRIBUTE);
                        String typeAttr = hardwareDevice.getAttribute(DCMConstants.TYPE_ATTRIBUTE);
                        itemType = new DCMNonPCIPnPHardware(Long.parseLong(componentId));
                        DCMI18NString name = getDisplay(hardwareDevice);
                        itemType.setName(name);
                    } else if (nodeType.getNodeName().equals(DCMConstants.PCIDEVICE)) {
                        Element pciDevice = (Element) nodeType;//getFirstLevelChildWithGivenName(type, DCMConstants.PCIDEVICE);

                        DCMPCIInfo pciInformation = parsePCIInfo(pciDevice);

                        if (pciInformation != null) {
                            itemType = new DCMPCIDevice(pciInformation);
                            DCMI18NString pciDeviceName = getDisplay(pciDevice);
                            itemType.setName(pciDeviceName);
                        }
                    }
                }
                if (itemType != null) {
                    Element updateableCompCollection = getFirstLevelChildWithGivenName(type, DCMConstants.UPDATEABLE_COMPONENT_COLLECTION);
                    Element updateableComp = getFirstLevelChildWithGivenName(updateableCompCollection, DCMConstants.UPDATEABLE_COMPONENT);
                    String updateableCompTypeAttr = updateableComp.getAttribute(DCMConstants.TYPE_ATTRIBUTE);

                    DCMUpdateableComponent component = new DCMUpdateableComponent(DCMComponentType.getEnumeration(updateableCompTypeAttr), itemType.getUniqueIdentifier());

                    DCMI18NString updateableCompName = getDisplay(updateableComp);
                    if (!updateableCompName.isEmpty()) {
                        component.setName(updateableCompName);
                    }
                    compCollection.addUpdateableComponent(component);

                    HashMap<String, DCMBaseTarget> itemCollection = inRoot.getTargetCollection();
                    if (!(inRoot.getTargetCollection().containsKey(itemType.getUniqueIdentifier()))) {
                        itemCollection.put(itemType.getUniqueIdentifier(), itemType);
                    }

                }
            }
        }
    }

    /**
     * Method to parse a child for the given element
     *
     * @param inParent specifies the parent element
     * @param inName specifies the name of child element
     * @return child element object
     */
    private Element getFirstLevelChildWithGivenName(Element inParent, String inName) {
        if (inParent == null) {
            return null;
        }
        logger.log(Level.INFO, "Parsing through the child element " + inName);
        NodeList childNodeList = inParent.getChildNodes();
        if ((null != childNodeList) && (childNodeList.getLength() > 0)) {
            int numberOfChildren = childNodeList.getLength();
            for (int childIndex = 0; childIndex < numberOfChildren; ++childIndex) {
                Node childNode = childNodeList.item(childIndex);
                short nodeType = childNode.getNodeType();
                if (nodeType == Node.ELEMENT_NODE) {
                    Element childElement = (Element) childNode;
                    if (childElement.getTagName().equals(inName)) {
                        return childElement;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method to parse PCIDevice under ItemTypeCollection
     *
     * @param inPCI specifies the PCI element
     * @return pciInformation object which contains pci information under item
     * Collection tag
     */
    private DCMPCIInfo parsePCIInfo(Element inPCI) {
        if (inPCI == null || !inPCI.getNodeName().equals(DCMConstants.PCIDEVICE)) {
            return null;
        }
        logger.log(Level.INFO, "Parsing PCIDevice" + inPCI.getTagName());

        // deviceID
        DCMHexBinary4 deviceID = new DCMHexBinary4();
        deviceID.setValue(inPCI.getAttribute(DCMConstants.DEVICE_ID_ATTRIBUTE));
        // vendorID
        DCMHexBinary4 vendorID = new DCMHexBinary4();
        vendorID.setValue(inPCI.getAttribute(DCMConstants.VENDOR_ID_ATTRIBUTE));
        // subDeviceID
        DCMHexBinary4 subDeviceID = new DCMHexBinary4();
        subDeviceID.setValue(inPCI.getAttribute(DCMConstants.SUB_DEVICE_ID_ATTRIBUTE));
        // subVendorID
        DCMHexBinary4 subVendorID = new DCMHexBinary4();
        subVendorID.setValue(inPCI.getAttribute(DCMConstants.SUB_VENDOR_ID_ATTRIBUTE));
        DCMPCIInfo pciInformation = new DCMPCIInfo(vendorID, deviceID, subVendorID, subDeviceID);
        return pciInformation;
    }

    /**
     * Method to parse the Name Tag
     *
     * @param inNode specifies Name Nodes
     * @return DCMI18NString object containing name tag attribute values
     */
    private DCMI18NString getDisplay(Element inNode) {
        if (inNode == null) {
            return null;
        }
        DCMI18NString retVal = new DCMI18NString();
        logger.log(Level.INFO, "Parsing through name tag ");
        Element nameElement = getFirstLevelChildWithGivenName(inNode, DCMConstants.NAME);
        Element locale = getFirstLevelChildWithGivenName(nameElement, DCMConstants.LOCAL_STRING);
        if (locale != null) {
            String langValue = locale.getAttribute(DCMConstants.LOCALE_ATTRIBUTE);
            logger.log(Level.INFO, "displaying locale attribute value " + langValue);
            if (langValue.equals(Locale.ENGLISH.getISO3Language())) {
                retVal.setLocaleString(Locale.ENGLISH, locale.getTextContent());
            }
        }
        //TODO add support for other languages here  
        return retVal;
    }

    /**
     * Method to parse VersionInfoCollection Tag
     *
     * @param inSysInventoryNode specifies the versionInfo node
     * @param inRoot specifies the object
     */
    private void parseVersionInfoCollection(Element inSysInventoryNode, DCMMultiSystemInventory inRoot) {
        if (null == inSysInventoryNode || null == inRoot) {
            return;
        }
        logger.log(Level.INFO, "parsing VersionInfoCollectionAggregation Tag ");

        DCMVersionInformationCollectionOfCollection versionInfoCollectionOfCollection = inRoot.getCollectionOfVersionInformationCollections();

        NodeList listOfVersionInfoCollectionElements = inSysInventoryNode.getElementsByTagName(DCMConstants.VERSION_INFO_COLLECTION);
        for (int temp = 0; temp < listOfVersionInfoCollectionElements.getLength(); temp++) {

            Node versionNode = listOfVersionInfoCollectionElements.item(temp);
            if (versionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element type = (Element) versionNode;
                DCMVersionInformationCollection versionInfoCollectionObj = new DCMVersionInformationCollection();
                String uidAttr = type.getAttribute(DCMConstants.UID_ATTRIBUTE);
                versionInfoCollectionObj.setUniqueIdentifier(Integer.parseInt(uidAttr));

                NodeList listOfVersionInfoElements = type.getElementsByTagName(DCMConstants.VERSION_INFO);
                for (int index = 0; index < listOfVersionInfoElements.getLength(); index++) {
                    Node node = listOfVersionInfoElements.item(index);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) node;
                        logger.log(Level.FINER, "parsing VersionInfo Tag ");
                        DCMVersionInformation versionInfoObj = new DCMVersionInformation();
                        String itemId = info.getAttribute(DCMConstants.ITEM_ID_ATTRIBUTE);
                        logger.log(Level.FINER, "printing itemId value " + itemId);
                        if (itemId != null) {
                            versionInfoObj.setTargetIdentifier(itemId);
                        }
                        String itemInstance = info.getAttribute(DCMConstants.ITEM_INSTANCE_ATTRIBUTE);
                        logger.log(Level.FINER, "printing itemInstance value " + itemInstance);
                        if (itemInstance != null && !itemInstance.isEmpty()) {
                            versionInfoObj.setTargetInstance(itemInstance);
                        }
                        String ucidAttr = info.getAttribute(DCMConstants.UCID_ATTRIBUTE);
                        logger.log(Level.FINER, "printing ucidAttr value " + ucidAttr);
                        if (ucidAttr != null) {
                            versionInfoObj.setUpdateableComponentIdentifier(ucidAttr);
                        }
                        String versionAttr = info.getAttribute(DCMConstants.VERSION_ATTRIBUTE);
                        logger.log(Level.FINER, "printing versionAttr value " + versionAttr);
                        versionInfoObj.setVersion(versionAttr);
                        versionInfoCollectionObj.addVersionInformation(versionInfoObj);
                    }
                }
                Element system = getFirstLevelChildWithGivenName(type, DCMConstants.SYSTEMS);
                Element systemInstElem = getFirstLevelChildWithGivenName(system, DCMConstants.SYSTEM_INSTANCE);

                String serviceTagAttr = systemInstElem.getAttribute(DCMConstants.SERVICE_TAG_ATTRIBUTE);

                if (serviceTagAttr != null && !serviceTagAttr.isEmpty()) {
                    String systemAttr = systemInstElem.getAttribute(DCMConstants.SYSTEM_UID_ATTRIBUTE);
                    String hostOSUidAttr = systemInstElem.getAttribute(DCMConstants.HOST_OS_UID_ATTRIBUTE);
                    String collectionAgentAttr = systemInstElem.getAttribute(DCMConstants.COLLECTION_AGENT_ATTRIBUTE);
                    String agentVersionAttr = systemInstElem.getAttribute(DCMConstants.AGENT_VERSION_ATTRIBUTE);
                    String collectionTimeAttr = systemInstElem.getAttribute(DCMConstants.COLLECTION_TIME_ATTRIBUTE);

                    DCMSystemInstance systemInstance = new DCMSystemInstance();
                    systemInstance.setServiceTag(serviceTagAttr);
                    systemInstance.setAgentName(collectionAgentAttr);
                    systemInstance.setAgentVersion(agentVersionAttr);
                    systemInstance.setSystemTypeIdentifier(systemAttr);
                    systemInstance.setHostOperatingSystemIdentifier(hostOSUidAttr);
                    if (collectionTimeAttr != null && !collectionTimeAttr.isEmpty()) {
                        try {
                            Date timeStampDate = new SimpleDateFormat(DCMConstants.DATE_TIME_FORMAT).parse(collectionTimeAttr);
                            systemInstance.setCollectionTime(timeStampDate);
                        } catch (ParseException ex) {
                            logger.log(Level.SEVERE, null, ex);
                        }
                    }
                    versionInfoCollectionObj.addSystemInstance(serviceTagAttr);
                    inRoot.getSystemInstanceCollection().addSystemInstance(systemInstance);
                }

                versionInfoCollectionOfCollection.addVersionInformationCollection(versionInfoCollectionObj);
            }
        }
    }

    /**
     * Method to parse SystemIdentity.XML
     *
     * @param systemInstance specifies the instance of System(iDRAC/CMC)
     * @param inRoot specifies the root to which the parsed inventory is to be
     * added
     * @param softwareIdentity specifies the softwareIdentity Document
     * @param systemString specifies the systemString Document
     */
    private int parseSoftwareIdentity(DCMMultiSystemInventory inRoot, DCMSystemInstance systemInstance,
            Collection<DCIMSoftwareIdentity> softwareIdentities, Collection<DCIMSystemString> systemStrings) {

        DCMVersionInformationCollection versionInfoCollection = new DCMVersionInformationCollection();

        for (DCIMSoftwareIdentity identity : softwareIdentities) {

            if (!"Installed".equalsIgnoreCase(identity.getStatus())) {
                continue;
            }

            DCMVersionInformation versionInformation = new DCMVersionInformation();

            String componentId = identity.getComponentID();

            String componentType = identity.getComponentType();
            String versionValue = identity.getVersionString();

            String displayValue = identity.getElementName();
            DCMBaseTarget target = null;

            String vendorid = identity.getVendorID();
            String deviceid = identity.getDeviceID();
            if (null != vendorid && !vendorid.isEmpty() && null != deviceid && !deviceid.isEmpty()) {
                DCMPCIInfo pciInfo = DCMPCIInfo.parse(vendorid, deviceid, identity.getSubVendorID(), identity.getSubDeviceID());

                DCMPCIDevice pciDevice = new DCMPCIDevice(pciInfo);
                if (null != componentId && !componentId.isEmpty()) {
                    pciDevice.setComponentID(Long.getLong(componentId));
                }
                if (null != displayValue) {
                    DCMI18NString name = new DCMI18NString();
                    name.setDefaultLocaleString(displayValue);
                    pciDevice.setName(name);
                }
                target = pciDevice;

                if (!(inRoot.getTargetCollection().containsKey(pciDevice.getUniqueIdentifier()))) {
                    inRoot.getTargetCollection().put(pciDevice.getUniqueIdentifier(), pciDevice);
                }

            } else if (null != componentId && !componentId.isEmpty()) {
                DCMNonPCIPnPHardware hardware = new DCMNonPCIPnPHardware(Long.parseLong(componentId));
                if (null != displayValue) {
                    DCMI18NString name = new DCMI18NString();
                    name.setDefaultLocaleString(displayValue);
                    hardware.setName(name);
                }
                target = hardware;

                if (!(inRoot.getTargetCollection().containsKey(hardware.getUniqueIdentifier()))) {
                    inRoot.getTargetCollection().put(hardware.getUniqueIdentifier(), hardware);
                }
            }
            DCMI18NString displayI18NString2 = new DCMI18NString();
            displayI18NString2.setDefaultLocaleString(DCMComponentType.getEnumeration(componentType).name());
            inRoot.getComponentKindCollection().addComponentKind(DCMComponentType.getEnumeration(componentType), displayI18NString2);
            if (null != componentType && !componentType.isEmpty() && null != target) {
                DCMUpdateableComponent uc = new DCMUpdateableComponent(DCMComponentType.getEnumeration(componentType), target.getUniqueIdentifier());
                if (null != displayValue && !displayValue.isEmpty()) {
                    DCMI18NString name = new DCMI18NString();
                    name.setDefaultLocaleString(displayValue);
                    uc.setName(name);
                    //versionInformation.setTargetInstance(displayValue);
                }
                inRoot.getUpdateableComponentCollection().addUpdateableComponent(uc);

                versionInformation.setTargetIdentifier(target.getUniqueIdentifier());
                versionInformation.setUpdateableComponentIdentifier(uc.getUniqueIdentifier());

                if (null != versionValue && !versionValue.isEmpty()) {
                    versionInformation.setVersion(versionValue);
                }
            }
            versionInformation.setTargetInstance(identity.getInstanceID());

            versionInfoCollection.addVersionInformation(versionInformation);

        }
        if (systemStrings == null) {
            DCMOperatingSystem os = new DCMOperatingSystem();
            os.setVendor("");
            inRoot.getOSCollection().addOperatingSystem(os);
            systemInstance.setHostOperatingSystemIdentifier("");
        } else {
            for (DCIMSystemString systemString : systemStrings) {
                if (systemString.getAttributeDisplayName().equals(WSCommandRNDConstant.OSNAME)) {
                    DCMOperatingSystem os = new DCMOperatingSystem();

                    String currentValue = systemString.getCurrentValue();
                    if (currentValue.contains(WSCommandRNDConstant.WINDOWS)) {
                        os.setVendor("Microsoft");
                    } else if (currentValue.contains(WSCommandRNDConstant.LINUX)) {
                        os.setVendor("Redhat");
                    }
                    os.setArchitecture(DCMOSArchitecture.X8664);
                    os.setType(DCMOSType.SERVER);
                    inRoot.getOSCollection().addOperatingSystem(os);
                    systemInstance.setHostOperatingSystemIdentifier(os.getUniqueIdentifier());
                }
            }
        }
        versionInfoCollection.addSystemInstance(systemInstance.getServiceTag());

        int index = inRoot.getCollectionOfVersionInformationCollections().getVersionInformationCollections().size() + 1;
        versionInfoCollection.setUniqueIdentifier(index);
        inRoot.getCollectionOfVersionInformationCollections().addVersionInformationCollection(versionInfoCollection);

        return DCMErrorCodes.SUCCESS;
    }

    private String mIdentifier;
    private int mSystemIndex;
    static Logger logger = Logger.getLogger(DCMInventory.class.getName());

}
