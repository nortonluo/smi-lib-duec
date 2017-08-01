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

import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMSystemInventory;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.downloader.DSMProxy;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.xpath.XPathExpressionException;

/**
 *
 * @author Raveendra_Madala
 */
public interface IDCMInventory {

    /**
     * Method for creating an empty multi system inventory
     *
     * @return the empty multi system inventory
     */
    public DCMMultiSystemInventory createEmptyMultiSystemInventory();

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
    public int addInventory(File inFile, String inIdentifier, DCMMultiSystemInventory inRoot, Boolean inAddAsChild);

    /**
     * Method for adding the inventory information(WSMan call output) to the
     * multi system inventory using proxy setting in the environment.
     *
     * @param iDRACipAddress
     * @param authentication
     * @param inRoot
     * @param mProxy
     * @return
     */
    public int addWSInventory(String iDRACipAddress, String iDRACport, DSMAuthenticationParameters authentication, DCMMultiSystemInventory inRoot, DSMProxy mProxy);

    /**
     * Method for adding the inventory information (WSMan call output) to the
     * multi system inventory
     *
     * @param iDRACipAddress specifies the IP address for iDRAC
     * @param authentication specifies the authentication parameters for iDRAC
     * @param inRoot specifies the root to which the parsed inventory is to be
     * added
     * @throws SOAPException SOAPException
     * @throws JAXBException JAXBException
     * @throws DatatypeConfigurationException DatatypeConfigurationException
     * @throws IOException IOException
     * @throws XPathExpressionException XPathExpressionException
     * @throws Exception Exception
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    public int addWSInventory(String iDRACipAddress, DSMAuthenticationParameters authentication, DCMMultiSystemInventory inRoot) throws SOAPException, JAXBException, DatatypeConfigurationException, IOException, XPathExpressionException, Exception;

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
    public int addRedFishInventory(File inFile, DCMMultiSystemInventory inRoot);

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
    public int addInventory(HashSet<File> inFileSet, String inIdentifier, DCMMultiSystemInventory inRoot);

    /**
     * Method for adding the inventory from a set of files (WSMan output)
     *
     * @param inFileSet specifies the file set from which the inventory is to be
     * populated
     * @param inRoot specifies the root to which the inventory is to be added
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    public int addWSInventory(HashSet<File> inFileSet, DCMMultiSystemInventory inRoot);

    /**
     * Method for adding the inventory from a set of files (RedFish output)
     *
     * @param inFileSet specifies the file set from which the inventory is to be
     * populated
     * @param inRoot specifies the root to which the inventory is to be added
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    public int addRedFishInventory(HashSet<File> inFileSet, DCMMultiSystemInventory inRoot);

    /**
     * Method for outputting the inventory to the specified file
     *
     * @param inRoot specifies the multi system inventory
     * @param outFile specifies the file in which the output is to be written
     * @return SUCCESS if the output could be written else appropriate error
     * code is returned.
     */
    public int serializeToFile(DCMMultiSystemInventory inRoot, File outFile);

    /**
     * Method for outputting the inventory to the specified file
     *
     * @param inRoot specifies the multi system inventory
     * @return String of xml Document.
     */
    public String serializeToString(DCMMultiSystemInventory inRoot);

    /**
     * Method for adding the inventory information (inventory collector output
     * or the one supplied by CMC) to the multi system inventory
     *
     * @param inInventoryAsString specifies the inventory content as String to
     * be parsed
     * @param inIdentifier specifies the system or group identifier
     * @param inRoot specifies the root to which the parsed inventory is to be
     * added
     * @param inAddAsChild specifies whether the inventory is to be added as a
     * child to the group
     * @return SUCCESS if the inventory could be added else appropriate error
     * code is returned.
     */
    public int addInventory(StringReader inInventoryAsString, String inIdentifier, DCMMultiSystemInventory inRoot, Boolean inAddAsChild);

    /**
     * Method to parse/de-serialize a XML file into an object
     *
     * @param inMultiInventoryFile which takes MultiInventory File
     * @param inRoot XML file is converted into this object
     * @return SUCCESS if the XML file is converted to an object otherwise error
     * code is returned
     *
     */
    public int deserializeXMLFile(File inMultiInventoryFile, DCMMultiSystemInventory inRoot);

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
    public int addInventory(DCMSystemInventory inSystem, DCMMultiSystemInventory inRoot);
}
