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

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.soap.SOAPException;

import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sun.ws.management.addressing.Addressing;
import com.sun.ws.management.enumeration.Enumeration;
import com.sun.ws.management.enumeration.EnumerationExtensions.Mode;

import java.util.logging.Level;

public abstract class WSManBaseCommand {

    private WSManageSession WSManSession = null;

    // set up a logger
    private static final Logger logger = Logger.getLogger(WSManBaseCommand.class.getName());

    /**
     * Original enum mappings
     *
     * SOFTWARE_INVENTORY("DCIM_SoftwareIdentity"),
     * SOFTWARE_INSTALL_SERVICE("DCIM_SoftwareInstallationService"),
     * SYSTEM_VIEW("DCIM_SystemView"), DCIM_JOB_SERVICE("DCIM_LifecycleJob"),
     * RAID_CONTROLLER_VIEW("DCIM_ControllerView"),
     * RAID_VD_VIEW("DCIM_VirtualDiskView"),
     * RAID_PD_VIEW("DCIM_PhysicalDiskView"),
     * BIOS_ENUMERATION("DCIM_BIOSEnumeration"),
     * BOOT_SOURCE_SETTING("DCIM_BootSourceSetting"),
     * BOOT_CONFIG_SETTTING("DCIM_BootConfigSetting"),
     * BIOS_STRING("DCIM_BIOSString"), BIOS_INTEGER("DCIM_BIOSInteger"),
     * LIFECYCLE_JOB_CLASSNAME("DCIM_LifecycleJob"),
     *
     * OSDSVC_NAMESPACE("root/dcim/"),
     * DCIM_JOB_SERVICE_CLASS("DCIM_JobService"),
     * FIRMWARE_UPDATE_METHOD("InstallFromURI"),
     * CREATE_REBOOT_METHOD("CreateRebootJob"),
     * SETUP_JOB_QUEUE("SetupJobQueue");
     */
    /**
     * Constructor
     *
     * @param ipAddr specifies idrac ip address
     * @param userName specifies idrac username
     * @param passwd specifies idrac password
     * @param port specifies idrac port number
     */
    protected WSManBaseCommand(String ipAddr, String port, String userName, String passwd) {

        this(ipAddr, port, userName, passwd, false);

    }

    protected WSManBaseCommand(String ipAddr, String port, String userName, String passwd, boolean bCertCheck) {
        logger.log(Level.INFO, "Entering Constructor: WSManBaseCommand(String ipAddr - %s, String port - %s, String userName - %s, String passwd - %s, boolean bCertCheck - %s)");
        WSManSession = new WSManageSession(ipAddr, userName, passwd, port, bCertCheck,null);
        WSManSession.owningCmd = this.getClass().getName();
        logger.info("Firing command " + WSManSession.owningCmd);

        logger.log(Level.INFO, "Exiting Constructor: WSManBaseCommand()");
    }

    protected WSManageSession getSession() {

        return WSManSession;
    }

    protected void setUserParam(String key, String value) {

        getSession().addUserParam(key, value);

    }

    public abstract Object execute() throws Exception;

	// FIXME: Attempt to factor out the following, or at least enum it
    // protected static String getInstanceId(String updateType) {
    // String type = updateType.toLowerCase();
    // if (type.equals("bios")) {
    // return "DCIM:INSTALLED:NONPCI:159:1.3.4";
    // } else if (type.equals("raid")) {
    // return "DCIM:INSTALLED:NONPCI:159:6.22.00";
    // } else if (type.equals("nic") || type.equals("lom")) {
    // return "DCIM:INSTALLED:PCI:14E4:1639:0237:1028:5.0.10";
    // } else if (type.equals("firmware")) {
    // return "";
    // } else if (type.equals("drvpk")) {
    // return "DCIM:INSTALLED:NONPCI:18981:5111.1";
    // } else if (type.equals("diags")) {
    // return "DCIM:INSTALLED:NONPCI:196:5116.1";
    // } else if (type.equals("perc6e")) {
    // return "DCIM:INSTALLED:PCI:1000:0060:1F0A:1028:06.20.00.13";
    // } else if (type.equals("perc6i")) {
    // return "DCIM:INSTALLED:PCI:1000:0060:1F0C:1028:06.20.00.13";
    // } else if (type.equals("h700a")) {
    // return "DCIM:INSTALLED:PCI:1000:0079:1F16:1028:12.01.00.53";
    // } else if (type.equals("h800a")) {
    // return "DCIM:INSTALLED:PCI:1000:0079:1F15:1028:12.01.00.53";
    // } else if (type.equals("idrac")) {
    // return "DCIM:INSTALLED:NONPCI:15051:1.30";
    // } else if (type.equals("idracmono")) {
    // return "DCIM:INSTALLED:NONPCI:20137:1.30";
    // } else if (type.equals("sas")) {
    // return "DCIM:INSTALLED:PCI:1000:0058:1F10:1028:06.22.02";
    // } else if (type.equals("psu")) {
    // return "DCIM:INSTALLED:NONPCI:1750:3.02.50";
    // } else if (type.equals("usc")) {
    // return "DCIM:INSTALLED:NONPCI:18980:1.30.60";
    // }
    //
    // else {
    // throw new IllegalArgumentException("Update Type: '" + updateType
    // + "' is not supported!");
    // }
    // }
    protected NodeList sendRequestEnumerationReturnNodeList() throws Exception {

        Document response = sendRequestEnumerationReturnDocument();
        Element element = response.getDocumentElement();
        NodeList nodeList = element.getElementsByTagNameNS(WSCommandRNDConstant.WS_MAN_NAMESPACE, WSCommandRNDConstant.WSMAN_ITEMS_TAG);

        return nodeList;

    }

    protected org.w3c.dom.Document sendRequestEnumerationReturnDocument() throws Exception {

        Addressing retAddressing = getSession().sendRequestEnumeration();
        return getSession().extractAddressBody(retAddressing);
    }

    protected Addressing sendRequestEnumerationReturnAddressing() throws Exception {

        Addressing retAddressing = getSession().sendRequestEnumeration();
        return retAddressing;
    }

    protected org.w3c.dom.Document sendRequestEnumerationReturnDocument(Mode enumMode) throws Exception {

        Addressing retAddressing = getSession().sendRequestEnumeration(enumMode);
        return getSession().extractAddressBody(retAddressing);
    }

    protected Enumeration sendRequestEnumeration() throws Exception {
        return new Enumeration(getSession().sendRequestEnumeration());
    }

    protected Enumeration sendRequestEnumeration(Mode enumMode) throws SOAPException, JAXBException, DatatypeConfigurationException, IOException {
        try {
            return new Enumeration(getSession().sendRequestEnumeration(enumMode));
        } catch (Exception e) {
            return null;
        }

    }

//    public enum WSManClassEnum {
//
//        DCIM_SoftwareIdentity,
//        DCIM_SoftwareInstallationService,
//        DCIM_SoftwareUpdateConcreteJob,
//        DCIM_SystemView,
//        DCIM_ControllerView,
//        DCIM_VirtualDiskView,
//        DCIM_PhysicalDiskView,
//        DCIM_BIOSEnumeration,
//        DCIM_BootSourceSetting,
//        DCIM_BootConfigSetting,
//        DCIM_BIOSString,
//        DCIM_BIOSInteger,
//        DCIM_NICView,
//        DCIM_LifecycleJob,
//        DCIM_JobService,
//        InstallFromURI,
//        CreateRebootJob,
//        SetupJobQueue,
//        DCIM_ComputerSystem,
//        DCIM_OSDeploymentService,
//        DCIM_BIOSService,
//        DCIM_OEM_DataAccessModule,
//        DCIM_RAIDService,
//        CIM_ComputerSystem,
//        DCIM_LCService,
//        DCIM_PersistentStorageService,
//        DCIM_LCEnumeration,
//        DCIM_IDRACCardView,
//        DCIM_SPComputerSystem,
//        CIM_IPProtocolEndpoint,
//        CIM_SoftwareIdentity,
//        CIM_InstalledSoftwareIdentity,
//        DCIM_Sellogentry,
//        DCIM_Memoryview,
//        DCIM_Powersupplyview,
//        DCIM_PCIDeviceView,
//        DCIM_CPUView,
//        DCIM_EnclosureView,
//        DCIM_iDRACCardAttribute,
//        DCIM_PSNumericsensor,
//        DCIM_iDRACCardString,
//        DCIM_iDRACCardEnumeration,
//        DCIM_NICStatistics,
//        DCIM_BaseMetricValue,
//        DCIM_AggregationMetricValue,
//        CIM_Account,
//        DCIM_LicenseManagementService,
//        DCIM_LicensableDevice,
//        DCIM_SelRecordLog,
//        DCIM_License,
//        DCIM_SystemManagementService,
//        DCIM_ModularChassisView,
//        DCIM_LCLogEntry,
//        DCIM_TimeService;
//
//    }





    public Document getlastRequestBody() {
        return getSession().getLastRequestBody();
    }

    public Addressing getlastAddressingResponse() {
        return getSession().getlastAddressingResponse();
    }

    public Addressing getlastAddressingRequest() {
        return getSession().getlastAddressingRequest();
    }

    /**
     * Set Read timeout (in seconds) for this http request
     *
     * @param to specifies time
     */
    protected void setTimeoutInSeconds(int to) {
        getSession().setTimeoutInSeconds(to);
    }

    public String getLCErrorCode() {
        return this.getSession().getLCMessageID();
    }

    public String getLCErrorStr() {
        return this.getSession().getLCMessageStr();
    }
}
