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
package com.dell.cm.repository;

import com.dell.cm.updateinformationmodel.DCMGroupManifest;
import com.dell.cm.updateinformationmodel.DCMManifestInformation;
import com.dell.cm.updateinformationmodel.DCMManifestInformationCollection;
import com.dell.cm.updateinformationmodel.DCMGroupManifestCollection;
import com.dell.cm.updateinformationmodel.DCMManifestIndex;
import com.dell.cm.inventory.DCMInventory;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMI18NString;
import com.dell.cm.updateinformationmodel.DCMLineOfBusiness;
import com.dell.cm.updateinformationmodel.DCMLineOfBusinessCollection;
import com.dell.cm.updateinformationmodel.DCMSystem;
import com.dell.cm.updateinformationmodel.DCMSystemCollection;
import com.dell.cm.updateinformationmodel.DCMSystemIDType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
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
import org.xml.sax.SAXException;

/**
 *
 * @author Ujjwal_Kumar_Gupta
 */
public class DCMIndexCatalogHelper {

    /**
     * Function to deserialize the manifest catalog
     *
     * @param inManifestFile as manifest catalog in .xml format
     * @param inRoot as root node for xml deserialization
     * @return corresponding DCMErrorCodes
     */
    public int deserializeXMLFile(File inManifestFile, DCMManifestIndex inRoot) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inManifestFile);
            doc.getDocumentElement().normalize();
            Element documentRoot = doc.getDocumentElement();
            LOG.log(Level.FINER, "RootElement: " + doc.getDocumentElement().getNodeName());
            String baseLocation = documentRoot.getAttribute(DCMConstants.BASE_LOCATION_ATTRIBUTE);
            LOG.log(Level.FINER, "Printing baseLocation " + baseLocation);
            if (null != baseLocation) {
                inRoot.setBaseLocation(baseLocation);
            }
            //Setting creation Date time for manifest Index
            String creationDateTime = documentRoot.getAttribute(DCMConstants.CREATION_DATE_TIME_ATTRIBUTE);
            LOG.log(Level.FINER, "Printing creationDatetime " + creationDateTime);
            if (null != creationDateTime) {
                inRoot.setCeationDateTime(creationDateTime);
            }
            //Setting version in manifest Index
            String version = documentRoot.getAttribute(DCMConstants.VERSION_ATTRIBUTE);
            LOG.log(Level.FINER, "Printing creationDatetime " + version);
            if (null != version) {
                inRoot.setVersion(version);
            }
            //Setting id in manifest index
            String id = documentRoot.getAttribute(DCMConstants.IDENTIFIER_ATTRIBUTE);
            LOG.log(Level.FINER, "Printing identifier " + id);
            if (null != id) {
                inRoot.setId(id);
            }
            //Settting predecessor id in manifest index
            String predecessorId = documentRoot.getAttribute(DCMConstants.PREDECESSOR_ID_ATTRIBUTE);
            LOG.log(Level.FINER, "Priting predecessorId " + predecessorId);
            if (null != predecessorId) {
                inRoot.setPredecessorId(predecessorId);
            }

            NodeList listOfChildNodes = documentRoot.getChildNodes();
            if ((null != listOfChildNodes) && (listOfChildNodes.getLength() > 0)) {
                int noOfChildNodes = listOfChildNodes.getLength();
                for (int childIndex = 0; childIndex < noOfChildNodes; ++childIndex) {
                    Node childNode = listOfChildNodes.item(childIndex);
                    short childNodeType = childNode.getNodeType();
                    if (childNodeType == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNode;
                        String childName = childElement.getTagName();
                        LOG.log(Level.FINER, "NextElement: " + childName);
                        if (childName.equals(DCMConstants.GROUP_MANIFEST)) {
                            if (inRoot.getGroupManifestCollection() == null) {
                                DCMGroupManifestCollection gmCollection = new DCMGroupManifestCollection();
                                inRoot.setGroupManifestCollection(gmCollection);
                            }
                            DCMGroupManifest gm = new DCMGroupManifest();
                            gm = parseGroupManifest(childElement);
                            inRoot.getGroupManifestCollection().addGroupManifest(gm);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Parse group manifest node/element in manifest catalog file
     *
     * @param grpManifestElement Group manifest node
     * @return parsed group manifest as DCMGroupManifest object
     */
    private DCMGroupManifest parseGroupManifest(Element grpManifestElement) {
        DCMGroupManifest gm = new DCMGroupManifest();
        String name = grpManifestElement.getAttribute(DCMConstants.NAME_ATTRIBUTE);
        LOG.log(Level.FINER, "Printing name " + name);
        if (null != name) {
            gm.setName(name);
        }
        String id = grpManifestElement.getAttribute(DCMConstants.ID_ATTRIBUTE);
        LOG.log(Level.FINER, "Printing Id " + id);
        if (null != id) {
            gm.setId(id);
        }
        String manifesttype = grpManifestElement.getAttribute(DCMConstants.TYPE_ATTRIBUTE);
        LOG.log(Level.FINER, "Printing type " + manifesttype);
        if (null != manifesttype) {
            gm.setType(manifesttype);
        }
        String creationDateTime = grpManifestElement.getAttribute(DCMConstants.CREATION_DATE_TIME_ATTRIBUTE);
        LOG.log(Level.FINER, "Printing creationDateTime " + creationDateTime);
        if (null != creationDateTime) {
            gm.setCreationDateTime(creationDateTime);
        }
        String latest = grpManifestElement.getAttribute(DCMConstants.LATEST_ATTRIBUTE);
        LOG.log(Level.FINER, "Printing latest " + latest);
        if (null != latest) {
            gm.setLatest(latest);
        }
        NodeList listOfChildNodes = grpManifestElement.getChildNodes();
        String grpManifestChildName = null;
        if ((null != listOfChildNodes) && (listOfChildNodes.getLength() > 0)) {
            for (int index = 0; index < listOfChildNodes.getLength(); ++index) {
                Node grpManifestChildNode = listOfChildNodes.item(index);
                short type = grpManifestChildNode.getNodeType();
                if (type == Node.ELEMENT_NODE) {
                    Element grpManifestchildElement = (Element) grpManifestChildNode;
                    grpManifestChildName = grpManifestchildElement.getTagName();
                    if (grpManifestChildName.equals(DCMConstants.DISPLAY)) {
                        LOG.log(Level.FINER, "Next Element: " + grpManifestChildName);
                        Element grpmanifestNode = grpManifestElement;
                        DCMI18NString i18nString = new DCMI18NString();
                        i18nString.setDefaultLocaleString(grpmanifestNode.getElementsByTagName(DCMConstants.DISPLAY).item(0).getTextContent());
                        gm.setDisplayName(i18nString);
                    } else if (grpManifestChildName.equals(DCMConstants.MANIFEST_INFORMATION)) {
                        LOG.log(Level.FINER, "Next Element: " + grpManifestChildName);
                        if (gm.getManifestInformationCollection() == null) {
                            DCMManifestInformationCollection miCollection = new DCMManifestInformationCollection();
                            gm.setManifestInformationCollection(miCollection);
                        }
                        DCMManifestInformation mi = new DCMManifestInformation();
                        mi = parseManifestInformation(grpManifestchildElement);
                        gm.getManifestInformationCollection().addManifestInformation(mi);
                    } else if (grpManifestChildName.equals(DCMConstants.SUPPORTED_SYSTEMS)) {
                        LOG.log(Level.FINER, "Next Element: " + grpManifestChildName);
                        NodeList inSSChildNodes = grpManifestchildElement.getChildNodes();
                        String inSSChildName = null;
                        if ((null != inSSChildNodes) && (inSSChildNodes.getLength() > 0)) {
                            for (int i = 0; i < inSSChildNodes.getLength(); ++i) {
                                Node inSSChildNode = inSSChildNodes.item(i);
                                short t = inSSChildNode.getNodeType();
                                if (t == Node.ELEMENT_NODE) {
                                    Element inSSChildElement = (Element) inSSChildNode;
                                    inSSChildName = inSSChildElement.getTagName();
                                    if (inSSChildName.equals(DCMConstants.BRAND)) {
                                        LOG.log(Level.FINER, "Next Element: " + inSSChildName);
                                        if (null == gm.getLOBSet()) {
                                            gm.setLOBSet(new HashMap<Integer, HashSet<String>>());
                                        }
                                        if (gm.getSystemCollection() == null) {
                                            gm.setSystemCollection(new DCMSystemCollection());
                                        }
                                        if (null == gm.getLOBcollection()) {
                                            gm.setLOBcollection(new DCMLineOfBusinessCollection());
                                        }
                                        parseBrand(inSSChildElement, gm);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return gm;
    }

    /**
     * Parse Brand Node/Element in Manifest Catalog
     *
     * @param inSSChildElement Supported System node
     * @param gm Group manifest object
     */
    private void parseBrand(Element inSSChildElement, DCMGroupManifest gm) {
        String key = inSSChildElement.getAttribute(DCMConstants.KEY_ATTRIBUTE);
        String prefix = inSSChildElement.getAttribute(DCMConstants.PREFIX_ATTRIBUTE);
        NodeList listOfChildNodes = inSSChildElement.getChildNodes();
        String brandChildName = null;
        HashSet<String> systemSet = new HashSet<String>();
        if ((null != listOfChildNodes) && (listOfChildNodes.getLength() > 0)) {
            for (int index = 0; index < listOfChildNodes.getLength(); ++index) {
                Node brandChildNode = listOfChildNodes.item(index);
                short type = brandChildNode.getNodeType();
                if (type == Node.ELEMENT_NODE) {
                    Element brandChildElement = (Element) brandChildNode;
                    brandChildName = brandChildElement.getTagName();
                    if (brandChildName.equals(DCMConstants.DISPLAY)) {
                        LOG.log(Level.FINER, "Next Element: " + brandChildName);
                        DCMI18NString i18nString = new DCMI18NString();
                        i18nString.setDefaultLocaleString(inSSChildElement.getElementsByTagName(DCMConstants.DISPLAY).item(0).getTextContent());
                        gm.getLOBcollection().addLineOfBusiness(Integer.parseInt(key), prefix, i18nString);
                    } else if (brandChildName.equals(DCMConstants.MODEL)) {
                        LOG.log(Level.FINER, "Next Element: " + brandChildName);
                        String sysName;
                        DCMI18NString i18nString = new DCMI18NString();
                        if (brandChildElement.hasChildNodes()) {
                            sysName = brandChildElement.getElementsByTagName(DCMConstants.DISPLAY).item(0).getTextContent();
                            i18nString.setDefaultLocaleString(sysName);
                        }
                        DCMSystem inSystem = new DCMSystem();
                        String sysID = brandChildElement.getAttribute(DCMConstants.SYSTEM_ID_ATTRIBUTE);
                        inSystem.setID(sysID);
                        inSystem.setLOBKey(Integer.parseInt(key));
                        inSystem.setName(i18nString);
                        String sysType = DCMSystemIDType.getEnumeration(brandChildElement.getAttribute(DCMConstants.SYSTEM_ID_TYPE_ATTRIBUTE)).toString();
                        if (sysType == DCMSystemIDType.MULTISYSTEMCHASSIS.toString()) {
                            sysType = "CHASSIS";
                        }
                        inSystem.setIDType(DCMSystemIDType.getEnumeration(brandChildElement.getAttribute(DCMConstants.SYSTEM_ID_TYPE_ATTRIBUTE)));
                        gm.getSystemCollection().addSystem(inSystem);
                        systemSet.add(key + sysType + sysID);
                    }
                }
            }
        }
        gm.getLOBSet().put(Integer.parseInt(key), systemSet);
    }

    /**
     * Parse Manifest Information Node/Element
     *
     * @param manifestInfoElement ManifestInformationElement
     * @return Parsed DCMManifestInformation Object
     */
    private DCMManifestInformation parseManifestInformation(Element manifestInfoElement) {
        DCMManifestInformation mi = new DCMManifestInformation();
        String id = manifestInfoElement.getAttribute(DCMConstants.ID_ATTRIBUTE);
        LOG.log(Level.FINER, "Printing id " + id);
        if (null != id) {
            mi.setId(id);
        }
        String releaseId = manifestInfoElement.getAttribute(DCMConstants.RELEASE_ID_ATTRIBUTE);
        LOG.log(Level.FINER, "Printing releaseID " + releaseId);
        if (null != releaseId) {
            mi.setReleaseID(releaseId);
        }
        String creationDateTime = manifestInfoElement.getAttribute(DCMConstants.CREATION_DATE_TIME_ATTRIBUTE);
        LOG.log(Level.FINER, "Printing creationDateTime " + creationDateTime);
        if (null != creationDateTime) {
            mi.setCreationDateTime(creationDateTime);
        }
        String version = manifestInfoElement.getAttribute(DCMConstants.VERSION_ATTRIBUTE);
        LOG.log(Level.FINER, "Printing version " + version);
        if (null != version) {
            mi.setVersion(version);
        }
        String path = manifestInfoElement.getAttribute(DCMConstants.PATH_ATTRIBUTE);
        LOG.log(Level.FINER, "Printing path " + path);
        if (null != path) {
            mi.setPath(path);
        }
        String hashMD5 = manifestInfoElement.getAttribute(DCMConstants.HASH_MD5_ATTRIBUTE);
        LOG.log(Level.FINER, "Printing hashMD5 " + hashMD5);
        if (null != hashMD5) {
            mi.setHashMD5(hashMD5);
        }
        String signPath = manifestInfoElement.getAttribute(DCMConstants.SIGN_PATH_ATTRIBUTE);
        LOG.log(Level.FINER, "Printing signPath " + signPath);
        if (null != signPath) {
            mi.setSignPath(signPath);
        } else {
            mi.setSignPath();
        }
        String name = manifestInfoElement.getAttribute(DCMConstants.NAME_ATTRIBUTE);
        LOG.log(Level.FINER,"Printing name " +name);
        if(name != null){
            mi.setName(name);
        }
        NodeList listOfChildNodes = manifestInfoElement.getChildNodes();
        String manifestInfoChildName = null;
        if ((null != listOfChildNodes) && (listOfChildNodes.getLength() > 0)) {
            for (int index = 0; index < listOfChildNodes.getLength(); ++index) {
                Node manifestInfoChildNode = listOfChildNodes.item(index);
                short type = manifestInfoChildNode.getNodeType();
                if (type == Node.ELEMENT_NODE) {
                    Element manifestInfochildElement = (Element) manifestInfoChildNode;
                    manifestInfoChildName = manifestInfochildElement.getTagName();
                    if (manifestInfoChildName.equals(DCMConstants.DISPLAY)) {
                        LOG.log(Level.FINER, "Next Element: " + manifestInfoChildName);
                        Element manifestInfoNode = manifestInfoElement;
                        DCMI18NString i18nString = new DCMI18NString();
                        i18nString.setDefaultLocaleString(manifestInfoNode.getElementsByTagName(DCMConstants.DISPLAY).item(0).getTextContent());
                        mi.setDisplayName(i18nString);
                    }
                }
            }
        }
        return mi;
    }

    /**
     * Serialize XML file to ByteArrayOutput Stream
     *
     * @param inRoot DCMManifestIndex Object
     * @return ByteArrayOutputStream
     */
    private ByteArrayOutputStream serializeXMLFile(DCMManifestIndex inRoot) {
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

                writer.writeStartElement(DCMConstants.MANIFEST_INDEX);
                writer.writeAttribute(DCMConstants.BASE_LOCATION_ATTRIBUTE, inRoot.getBaseLocation());
                writer.writeAttribute(DCMConstants.CREATION_DATE_TIME_ATTRIBUTE, inRoot.getCeationDateTime());
                writer.writeAttribute(DCMConstants.VERSION_ATTRIBUTE, inRoot.getVersion());

                for (DCMGroupManifest gm : inRoot.getGroupManifestCollection().getGroupManifests()) {
                    writer.writeStartElement(DCMConstants.GROUP_MANIFEST);
                    writer.writeAttribute(DCMConstants.ID_ATTRIBUTE, gm.getId());
                    writer.writeAttribute(DCMConstants.NAME_ATTRIBUTE, gm.getName());
                    writer.writeAttribute(DCMConstants.TYPE_ATTRIBUTE, gm.getType());
                    writer.writeAttribute(DCMConstants.LATEST_ATTRIBUTE, gm.getLatest());
                    writer.writeAttribute(DCMConstants.CREATION_DATE_TIME_ATTRIBUTE, gm.getCreationDateTime());

                    DCMI18NString i18NString = new DCMI18NString(gm.getDisplayName());
                    writer.writeStartElement(DCMConstants.DISPLAY);
                    writer.writeAttribute(DCMConstants.LANG_ATTRIBUTE, "en");
                    writer.writeCharacters(i18NString.getDefaultLocaleString());
                    writer.writeEndElement(); // For Display in Group Manifest

                    if ((null != gm.getLOBSet()) && (null != gm.getLOBcollection()) && (null != gm.getSystemCollection())) {
                        writer.writeStartElement(DCMConstants.SUPPORTED_SYSTEMS);
                        for (int lobKey : gm.getLOBSet().keySet()) {
                            DCMLineOfBusiness lob = gm.getLOBcollection().getLineOfBusiness(lobKey);
                            writer.writeStartElement(DCMConstants.BRAND);
                            writer.writeAttribute(DCMConstants.KEY_ATTRIBUTE, Integer.toString(lob.getKey()));
                            writer.writeAttribute(DCMConstants.PREFIX_ATTRIBUTE, lob.getPrefix());
                            writer.writeStartElement(DCMConstants.DISPLAY);
                            writer.writeAttribute(DCMConstants.LANG_ATTRIBUTE, "en");
                            DCMI18NString i18NSt = new DCMI18NString(lob.getName());
                            writer.writeCharacters(i18NSt.getDefaultLocaleString());
                            writer.writeEndElement(); // For display in Brand
                            HashSet<String> systemSet = gm.getLOBSet().get(lobKey);
                            for (String systemID : systemSet) {
                                DCMSystem system = gm.getSystemCollection().getSystem(systemID);
                                writer.writeStartElement(DCMConstants.MODEL);
                                writer.writeAttribute(DCMConstants.SYSTEM_ID_ATTRIBUTE, system.getID());
                                String sysIDType = DCMSystemIDType.toString(system.getIDType());
                                if (sysIDType == "CHASSIS") {
                                    sysIDType = DCMSystemIDType.MULTISYSTEMCHASSIS.toString();
                                }
                                writer.writeAttribute(DCMConstants.SYSTEM_ID_TYPE_ATTRIBUTE, sysIDType);

                                if (system.getName() != null) {
                                    writer.writeStartElement(DCMConstants.DISPLAY);
                                    writer.writeAttribute(DCMConstants.LANG_ATTRIBUTE, "en");
                                    DCMI18NString i18NStr = new DCMI18NString(system.getName());
                                    writer.writeCharacters(i18NStr.getDefaultLocaleString());
                                    writer.writeEndElement(); // For Display
                                }
                                writer.writeEndElement(); // For MODEL
                            }
                            writer.writeEndElement(); // For Brand
                        }
                        writer.writeEndElement(); // For supported Systems
                    }

                    for (DCMManifestInformation mi : gm.getManifestInformationCollection().getManifestInformations()) {
                        writer.writeStartElement(DCMConstants.MANIFEST_INFORMATION);
                        writer.writeAttribute(DCMConstants.PATH_ATTRIBUTE, mi.getPath());
                        writer.writeAttribute(DCMConstants.HASH_MD5_ATTRIBUTE, mi.getHashMD5());
                        writer.writeAttribute(DCMConstants.RELEASE_ID_ATTRIBUTE, mi.getReleaseID());
                        writer.writeAttribute(DCMConstants.VERSION_ATTRIBUTE, mi.getVersion());
                        writer.writeAttribute(DCMConstants.CREATION_DATE_TIME_ATTRIBUTE, mi.getCreationDateTime());
                        writer.writeAttribute(DCMConstants.ID_ATTRIBUTE, mi.getId());
                        writer.writeAttribute(DCMConstants.SIGN_PATH_ATTRIBUTE, mi.getSignPath());

                        DCMI18NString i18NStr = new DCMI18NString(mi.getDisplayName());
                        writer.writeStartElement(DCMConstants.DISPLAY);
                        writer.writeAttribute(DCMConstants.LANG_ATTRIBUTE, "en");
                        writer.writeCharacters(i18NStr.getDefaultLocaleString());
                        writer.writeEndElement(); // For Display in Manifest Information

                        writer.writeEndElement(); // For Manifest Information    
                    }
                    writer.writeEndElement(); // For Group Manifest 
                }

                writer.writeEndElement();  // For Manifest Index  
                writer.writeEndDocument();
            }
        } catch (FileNotFoundException | UnsupportedEncodingException | XMLStreamException ex) {
            Logger.getLogger(DCMInventory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DCMInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return byteOutputStream;
    }

    /**
     * Serialize DCMManifestIndex object to xml file
     *
     * @param inRoot DCMManifest Object
     * @param inOutFile Output xml file as File
     * @return corresponding DCMErrorCodes
     */
    public int serializeToFile(DCMManifestIndex inRoot, File inOutFile) {
        if (null == inRoot || null == inOutFile) {
            return DCMErrorCodes.FAILURE;
        }
        try {
            ByteArrayOutputStream byteOutputStream = serializeXMLFile(inRoot);
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
            Logger.getLogger(DCMInventory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DCMInventory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DCMInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DCMErrorCodes.SUCCESS;
    }

    private static final Logger LOG = Logger.getLogger(DCMIndexCatalogHelper.class.getName());
}
