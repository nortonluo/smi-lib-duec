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
package com.dell.cm.testPackage;

import com.dell.cm.repository.DCMRepository;
import com.dell.cm.updateinformationmodel.DCMBundleType;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.downloader.DSMCIFSLocation;
import com.dell.sm.downloader.DSMProtocolEnum;
import com.dell.sm.downloader.DSMProxy;
import com.dell.sm.downloader.IDSMLocation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Md_Shahbaz_Alam Purpose : To create a SUU Repository Required File
 * attached in the resources package
 */
public class TestSUURepository {

    private int suuTestFunction() {
        Collection<String> inventories = new HashSet<>();
        File inventoryFile = new File(this.inventoryFile);
        if (!inventoryFile.exists() || !inventoryFile.canRead()) {
            System.out.println(" Inventory File : " + inventoryFile.getAbsolutePath() + "  doesn't exist or insufficient previledge to access the File");
        } else {
            System.out.println(" Inventory File : " + inventoryFile.getAbsolutePath() + " Exist");
        }
        File catalogFile = new File(this.catalogFile);
        if (!catalogFile.exists() || !catalogFile.canRead()) {
            System.out.println(" Catalog File : " + catalogFile.getAbsolutePath() + "  doesn't exist or insufficient previledge to access the File");
        } else {
            System.out.println(" Catalog File : " + catalogFile.getAbsolutePath() + " Exist");
        }

        // Input Params.
        BufferedReader rd;
        try {
            rd = new BufferedReader(new FileReader(inventoryFile));

            String inputLine = null;
            StringBuilder builder = new StringBuilder();
            //Store the contents of the file to the StringBuilder.
            while ((inputLine = rd.readLine()) != null) {
                builder.append(inputLine);
            }

            String inventory = builder.toString();

            inventories.add(inventory);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestSUURepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestSUURepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        Collection<DSMProxy> proxyCollection = null;
        DSMAuthenticationParameters sourceAuthenticationParams = null;

        DSMProtocolEnum sourceProtocol = DSMProtocolEnum.HTTP;
        String baseLocation = this.baseLocation;
        DCMBundleType bundleType = DCMBundleType.BTW32;

        DSMAuthenticationParameters destinationAuthenticationParams = new DSMAuthenticationParameters();
        destinationAuthenticationParams.setDomainName(this.domainName);
        destinationAuthenticationParams.setUserName(this.userName);
        destinationAuthenticationParams.setPassword(this.password);

        DSMProtocolEnum destinationProtocol = DSMProtocolEnum.CIFS;
        String shareName = this.shareName;
        String sharePath = this.sharePath;

        IDSMLocation dsmLocation = new DSMCIFSLocation(destinationAuthenticationParams, shareName, sharePath);
        //IDSMLocation dsmLocation = new DSMLocation("test\\repog");
        DCMRepository repo = new DCMRepository();
        return repo.createSUURepository(inventories, proxyCollection, catalogFile.toString(), sourceAuthenticationParams, sourceProtocol, baseLocation, bundleType, dsmLocation);

    }

    public static void main(String[] args) {
        TestSUURepository suuRepository = new TestSUURepository();
        suuRepository.catalogFile = args[0];
        suuRepository.inventoryFile = args[1];
        suuRepository.baseLocation = args[2];
        suuRepository.domainName = args[3];
        suuRepository.userName = args[4];
        suuRepository.password = args[5];
        suuRepository.shareName = args[6];
        suuRepository.sharePath = args[7];

//        suuRepository.catalogFile = "C:\\duec-j\\Catalog-Dependency\\catalog.xml";
//        suuRepository.inventoryFile = "C:\\DUEC_November\\test\\resources\\inv.xml";
//        suuRepository.baseLocation = "ftp.dell.com";
//        suuRepository.domainName = "AMERICAS";
//        suuRepository.userName = "md_shahbaz_alam";
//        suuRepository.password = "password";
//        suuRepository.shareName = "\\\\WN7X64-7HBFT12\\C$";
//        suuRepository.sharePath = "duec-j\\DUECArtifact";
        int result = suuRepository.suuTestFunction();
        if (result == DCMErrorCodes.SUCCESS) {
            System.out.println("SUCCESSFUL : Creation of SUU Repository");
        } else {
            System.out.println("UNSUCCESSFUL : Creation of SUU Repository");
        }
    }
    public String catalogFile;
    public String inventoryFile;
    public String baseLocation;
    public String domainName;
    public String userName;
    public String password;
    public String shareName;
    public String sharePath;

}
