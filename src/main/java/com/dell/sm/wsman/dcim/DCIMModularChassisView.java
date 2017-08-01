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
package com.dell.sm.wsman.dcim;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssetTag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="CMCModel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChassisDefaultLowerPowerCap" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="ChassisDefaultUpperPowerCap" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="ChassisExternalPowerCap" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="ChassisLowerPowerBound" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="ChassisPCIeSlotReassignmentEnabled" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="ChassisUpperPowerBound" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="DNSCMCName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DNSDomainName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ElementName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EnhancedCoolingMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ExpressServiceCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="FQDD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FlexFabricState" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FlexFabricStateDescription" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="HostName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IPv4Address" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InstanceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaxPowerConservationModeEnableTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaxPowerConservationModeEnabled" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MgmtControllerFirmwareVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Model" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PhysicalLocationAisle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PhysicalLocationChassisName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PhysicalLocationDataCenter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PhysicalLocationDeviceSize" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PhysicalLocationRack" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PhysicalLocationRackSlot" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PhysicalLocationRoom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PowerState" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="PrimaryStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="PwrInputInfrastructureAllocation" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="PwrInputSystemConsumption" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="RACType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="SNMPCommunityBladeIRAlert" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SNMPDestinationBladeIRAlert" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceTag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SystemID" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="SystemPSUInputPower" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="SystemPSUOutputPower" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="URLString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UseHostNameForSlotName" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "assetTag",
    "cmcModel",
    "chassisDefaultLowerPowerCap",
    "chassisDefaultUpperPowerCap",
    "chassisExternalPowerCap",
    "chassisLowerPowerBound",
    "chassisPCIeSlotReassignmentEnabled",
    "chassisUpperPowerBound",
    "dnscmcName",
    "dnsDomainName",
    "elementName",
    "enhancedCoolingMode",
    "expressServiceCode",
    "fqdd",
    "flexFabricState",
    "flexFabricStateDescription",
    "hostName",
    "iPv4Address",
    "instanceID",
    "location",
    "maxPowerConservationModeEnableTime",
    "maxPowerConservationModeEnabled",
    "mgmtControllerFirmwareVersion",
    "model",
    "physicalLocationAisle",
    "physicalLocationChassisName",
    "physicalLocationDataCenter",
    "physicalLocationDeviceSize",
    "physicalLocationRack",
    "physicalLocationRackSlot",
    "physicalLocationRoom",
    "powerState",
    "primaryStatus",
    "pwrInputInfrastructureAllocation",
    "pwrInputSystemConsumption",
    "racType",
    "snmpCommunityBladeIRAlert",
    "snmpDestinationBladeIRAlert",
    "serviceTag",
    "systemID",
    "systemPSUInputPower",
    "systemPSUOutputPower",
    "urlString",
    "useHostNameForSlotName"
})
@XmlRootElement(name = "DCIM_ModularChassisView")
public class DCIMModularChassisView {

    @XmlElement(name = "AssetTag")
    protected byte assetTag;
    @XmlElement(name = "CMCModel", required = true)
    protected String cmcModel;
    @XmlElement(name = "ChassisDefaultLowerPowerCap")
    protected short chassisDefaultLowerPowerCap;
    @XmlElement(name = "ChassisDefaultUpperPowerCap")
    protected short chassisDefaultUpperPowerCap;
    @XmlElement(name = "ChassisExternalPowerCap")
    protected short chassisExternalPowerCap;
    @XmlElement(name = "ChassisLowerPowerBound")
    protected short chassisLowerPowerBound;
    @XmlElement(name = "ChassisPCIeSlotReassignmentEnabled")
    protected byte chassisPCIeSlotReassignmentEnabled;
    @XmlElement(name = "ChassisUpperPowerBound")
    protected short chassisUpperPowerBound;
    @XmlElement(name = "DNSCMCName", required = true)
    protected String dnscmcName;
    @XmlElement(name = "DNSDomainName", required = true, nillable = true)
    protected String dnsDomainName;
    @XmlElement(name = "ElementName", required = true)
    protected String elementName;
    @XmlElement(name = "EnhancedCoolingMode", required = true, nillable = true)
    protected String enhancedCoolingMode;
    @XmlElement(name = "ExpressServiceCode")
    protected long expressServiceCode;
    @XmlElement(name = "FQDD", required = true)
    protected String fqdd;
    @XmlElement(name = "FlexFabricState")
    protected List<String> flexFabricState;
    @XmlElement(name = "FlexFabricStateDescription")
    protected List<String> flexFabricStateDescription;
    @XmlElement(name = "HostName", required = true)
    protected String hostName;
    @XmlElement(name = "IPv4Address", required = true)
    protected String iPv4Address;
    @XmlElement(name = "InstanceID", required = true)
    protected String instanceID;
    @XmlElement(name = "Location", required = true)
    protected String location;
    @XmlElement(name = "MaxPowerConservationModeEnableTime", required = true)
    protected String maxPowerConservationModeEnableTime;
    @XmlElement(name = "MaxPowerConservationModeEnabled", required = true)
    protected String maxPowerConservationModeEnabled;
    @XmlElement(name = "MgmtControllerFirmwareVersion", required = true)
    protected String mgmtControllerFirmwareVersion;
    @XmlElement(name = "Model", required = true)
    protected String model;
    @XmlElement(name = "PhysicalLocationAisle", required = true)
    protected String physicalLocationAisle;
    @XmlElement(name = "PhysicalLocationChassisName", required = true)
    protected String physicalLocationChassisName;
    @XmlElement(name = "PhysicalLocationDataCenter", required = true)
    protected String physicalLocationDataCenter;
    @XmlElement(name = "PhysicalLocationDeviceSize", required = true)
    protected String physicalLocationDeviceSize;
    @XmlElement(name = "PhysicalLocationRack", required = true)
    protected String physicalLocationRack;
    @XmlElement(name = "PhysicalLocationRackSlot", required = true)
    protected String physicalLocationRackSlot;
    @XmlElement(name = "PhysicalLocationRoom", required = true)
    protected String physicalLocationRoom;
    @XmlElement(name = "PowerState")
    protected byte powerState;
    @XmlElement(name = "PrimaryStatus")
    protected byte primaryStatus;
    @XmlElement(name = "PwrInputInfrastructureAllocation")
    protected short pwrInputInfrastructureAllocation;
    @XmlElement(name = "PwrInputSystemConsumption")
    protected short pwrInputSystemConsumption;
    @XmlElement(name = "RACType")
    protected byte racType;
    @XmlElement(name = "SNMPCommunityBladeIRAlert", required = true)
    protected String snmpCommunityBladeIRAlert;
    @XmlElement(name = "SNMPDestinationBladeIRAlert", required = true)
    protected String snmpDestinationBladeIRAlert;
    @XmlElement(name = "ServiceTag", required = true)
    protected String serviceTag;
    @XmlElement(name = "SystemID")
    protected short systemID;
    @XmlElement(name = "SystemPSUInputPower")
    protected short systemPSUInputPower;
    @XmlElement(name = "SystemPSUOutputPower")
    protected short systemPSUOutputPower;
    @XmlElement(name = "URLString", required = true)
    protected String urlString;
    @XmlElement(name = "UseHostNameForSlotName")
    protected byte useHostNameForSlotName;

    /**
     * Gets the value of the assetTag property.
     * 
     */
    public byte getAssetTag() {
        return assetTag;
    }

    /**
     * Sets the value of the assetTag property.
     * 
     */
    public void setAssetTag(byte value) {
        this.assetTag = value;
    }

    /**
     * Gets the value of the cmcModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCMCModel() {
        return cmcModel;
    }

    /**
     * Sets the value of the cmcModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCMCModel(String value) {
        this.cmcModel = value;
    }

    /**
     * Gets the value of the chassisDefaultLowerPowerCap property.
     * 
     */
    public short getChassisDefaultLowerPowerCap() {
        return chassisDefaultLowerPowerCap;
    }

    /**
     * Sets the value of the chassisDefaultLowerPowerCap property.
     * 
     */
    public void setChassisDefaultLowerPowerCap(short value) {
        this.chassisDefaultLowerPowerCap = value;
    }

    /**
     * Gets the value of the chassisDefaultUpperPowerCap property.
     * 
     */
    public short getChassisDefaultUpperPowerCap() {
        return chassisDefaultUpperPowerCap;
    }

    /**
     * Sets the value of the chassisDefaultUpperPowerCap property.
     * 
     */
    public void setChassisDefaultUpperPowerCap(short value) {
        this.chassisDefaultUpperPowerCap = value;
    }

    /**
     * Gets the value of the chassisExternalPowerCap property.
     * 
     */
    public short getChassisExternalPowerCap() {
        return chassisExternalPowerCap;
    }

    /**
     * Sets the value of the chassisExternalPowerCap property.
     * 
     */
    public void setChassisExternalPowerCap(short value) {
        this.chassisExternalPowerCap = value;
    }

    /**
     * Gets the value of the chassisLowerPowerBound property.
     * 
     */
    public short getChassisLowerPowerBound() {
        return chassisLowerPowerBound;
    }

    /**
     * Sets the value of the chassisLowerPowerBound property.
     * 
     */
    public void setChassisLowerPowerBound(short value) {
        this.chassisLowerPowerBound = value;
    }

    /**
     * Gets the value of the chassisPCIeSlotReassignmentEnabled property.
     * 
     */
    public byte getChassisPCIeSlotReassignmentEnabled() {
        return chassisPCIeSlotReassignmentEnabled;
    }

    /**
     * Sets the value of the chassisPCIeSlotReassignmentEnabled property.
     * 
     */
    public void setChassisPCIeSlotReassignmentEnabled(byte value) {
        this.chassisPCIeSlotReassignmentEnabled = value;
    }

    /**
     * Gets the value of the chassisUpperPowerBound property.
     * 
     */
    public short getChassisUpperPowerBound() {
        return chassisUpperPowerBound;
    }

    /**
     * Sets the value of the chassisUpperPowerBound property.
     * 
     */
    public void setChassisUpperPowerBound(short value) {
        this.chassisUpperPowerBound = value;
    }

    /**
     * Gets the value of the dnscmcName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDNSCMCName() {
        return dnscmcName;
    }

    /**
     * Sets the value of the dnscmcName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDNSCMCName(String value) {
        this.dnscmcName = value;
    }

    /**
     * Gets the value of the dnsDomainName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDNSDomainName() {
        return dnsDomainName;
    }

    /**
     * Sets the value of the dnsDomainName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDNSDomainName(String value) {
        this.dnsDomainName = value;
    }

    /**
     * Gets the value of the elementName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElementName() {
        return elementName;
    }

    /**
     * Sets the value of the elementName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElementName(String value) {
        this.elementName = value;
    }

    /**
     * Gets the value of the enhancedCoolingMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnhancedCoolingMode() {
        return enhancedCoolingMode;
    }

    /**
     * Sets the value of the enhancedCoolingMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnhancedCoolingMode(String value) {
        this.enhancedCoolingMode = value;
    }

    /**
     * Gets the value of the expressServiceCode property.
     * 
     */
    public long getExpressServiceCode() {
        return expressServiceCode;
    }

    /**
     * Sets the value of the expressServiceCode property.
     * 
     */
    public void setExpressServiceCode(long value) {
        this.expressServiceCode = value;
    }

    /**
     * Gets the value of the fqdd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFQDD() {
        return fqdd;
    }

    /**
     * Sets the value of the fqdd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFQDD(String value) {
        this.fqdd = value;
    }

    /**
     * Gets the value of the flexFabricState property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flexFabricState property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlexFabricState().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFlexFabricState() {
        if (flexFabricState == null) {
            flexFabricState = new ArrayList<String>();
        }
        return this.flexFabricState;
    }

    /**
     * Gets the value of the flexFabricStateDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flexFabricStateDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlexFabricStateDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFlexFabricStateDescription() {
        if (flexFabricStateDescription == null) {
            flexFabricStateDescription = new ArrayList<String>();
        }
        return this.flexFabricStateDescription;
    }

    /**
     * Gets the value of the hostName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Sets the value of the hostName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostName(String value) {
        this.hostName = value;
    }

    /**
     * Gets the value of the iPv4Address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPv4Address() {
        return iPv4Address;
    }

    /**
     * Sets the value of the iPv4Address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPv4Address(String value) {
        this.iPv4Address = value;
    }

    /**
     * Gets the value of the instanceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceID() {
        return instanceID;
    }

    /**
     * Sets the value of the instanceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceID(String value) {
        this.instanceID = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the maxPowerConservationModeEnableTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxPowerConservationModeEnableTime() {
        return maxPowerConservationModeEnableTime;
    }

    /**
     * Sets the value of the maxPowerConservationModeEnableTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxPowerConservationModeEnableTime(String value) {
        this.maxPowerConservationModeEnableTime = value;
    }

    /**
     * Gets the value of the maxPowerConservationModeEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxPowerConservationModeEnabled() {
        return maxPowerConservationModeEnabled;
    }

    /**
     * Sets the value of the maxPowerConservationModeEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxPowerConservationModeEnabled(String value) {
        this.maxPowerConservationModeEnabled = value;
    }

    /**
     * Gets the value of the mgmtControllerFirmwareVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMgmtControllerFirmwareVersion() {
        return mgmtControllerFirmwareVersion;
    }

    /**
     * Sets the value of the mgmtControllerFirmwareVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMgmtControllerFirmwareVersion(String value) {
        this.mgmtControllerFirmwareVersion = value;
    }

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the physicalLocationAisle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalLocationAisle() {
        return physicalLocationAisle;
    }

    /**
     * Sets the value of the physicalLocationAisle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalLocationAisle(String value) {
        this.physicalLocationAisle = value;
    }

    /**
     * Gets the value of the physicalLocationChassisName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalLocationChassisName() {
        return physicalLocationChassisName;
    }

    /**
     * Sets the value of the physicalLocationChassisName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalLocationChassisName(String value) {
        this.physicalLocationChassisName = value;
    }

    /**
     * Gets the value of the physicalLocationDataCenter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalLocationDataCenter() {
        return physicalLocationDataCenter;
    }

    /**
     * Sets the value of the physicalLocationDataCenter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalLocationDataCenter(String value) {
        this.physicalLocationDataCenter = value;
    }

    /**
     * Gets the value of the physicalLocationDeviceSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalLocationDeviceSize() {
        return physicalLocationDeviceSize;
    }

    /**
     * Sets the value of the physicalLocationDeviceSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalLocationDeviceSize(String value) {
        this.physicalLocationDeviceSize = value;
    }

    /**
     * Gets the value of the physicalLocationRack property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalLocationRack() {
        return physicalLocationRack;
    }

    /**
     * Sets the value of the physicalLocationRack property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalLocationRack(String value) {
        this.physicalLocationRack = value;
    }

    /**
     * Gets the value of the physicalLocationRackSlot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalLocationRackSlot() {
        return physicalLocationRackSlot;
    }

    /**
     * Sets the value of the physicalLocationRackSlot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalLocationRackSlot(String value) {
        this.physicalLocationRackSlot = value;
    }

    /**
     * Gets the value of the physicalLocationRoom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalLocationRoom() {
        return physicalLocationRoom;
    }

    /**
     * Sets the value of the physicalLocationRoom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalLocationRoom(String value) {
        this.physicalLocationRoom = value;
    }

    /**
     * Gets the value of the powerState property.
     * 
     */
    public byte getPowerState() {
        return powerState;
    }

    /**
     * Sets the value of the powerState property.
     * 
     */
    public void setPowerState(byte value) {
        this.powerState = value;
    }

    /**
     * Gets the value of the primaryStatus property.
     * 
     */
    public byte getPrimaryStatus() {
        return primaryStatus;
    }

    /**
     * Sets the value of the primaryStatus property.
     * 
     */
    public void setPrimaryStatus(byte value) {
        this.primaryStatus = value;
    }

    /**
     * Gets the value of the pwrInputInfrastructureAllocation property.
     * 
     */
    public short getPwrInputInfrastructureAllocation() {
        return pwrInputInfrastructureAllocation;
    }

    /**
     * Sets the value of the pwrInputInfrastructureAllocation property.
     * 
     */
    public void setPwrInputInfrastructureAllocation(short value) {
        this.pwrInputInfrastructureAllocation = value;
    }

    /**
     * Gets the value of the pwrInputSystemConsumption property.
     * 
     */
    public short getPwrInputSystemConsumption() {
        return pwrInputSystemConsumption;
    }

    /**
     * Sets the value of the pwrInputSystemConsumption property.
     * 
     */
    public void setPwrInputSystemConsumption(short value) {
        this.pwrInputSystemConsumption = value;
    }

    /**
     * Gets the value of the racType property.
     * 
     */
    public byte getRACType() {
        return racType;
    }

    /**
     * Sets the value of the racType property.
     * 
     */
    public void setRACType(byte value) {
        this.racType = value;
    }

    /**
     * Gets the value of the snmpCommunityBladeIRAlert property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSNMPCommunityBladeIRAlert() {
        return snmpCommunityBladeIRAlert;
    }

    /**
     * Sets the value of the snmpCommunityBladeIRAlert property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSNMPCommunityBladeIRAlert(String value) {
        this.snmpCommunityBladeIRAlert = value;
    }

    /**
     * Gets the value of the snmpDestinationBladeIRAlert property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSNMPDestinationBladeIRAlert() {
        return snmpDestinationBladeIRAlert;
    }

    /**
     * Sets the value of the snmpDestinationBladeIRAlert property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSNMPDestinationBladeIRAlert(String value) {
        this.snmpDestinationBladeIRAlert = value;
    }

    /**
     * Gets the value of the serviceTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTag() {
        return serviceTag;
    }

    /**
     * Sets the value of the serviceTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTag(String value) {
        this.serviceTag = value;
    }

    /**
     * Gets the value of the systemID property.
     * 
     */
    public short getSystemID() {
        return systemID;
    }

    /**
     * Sets the value of the systemID property.
     * 
     */
    public void setSystemID(short value) {
        this.systemID = value;
    }

    /**
     * Gets the value of the systemPSUInputPower property.
     * 
     */
    public short getSystemPSUInputPower() {
        return systemPSUInputPower;
    }

    /**
     * Sets the value of the systemPSUInputPower property.
     * 
     */
    public void setSystemPSUInputPower(short value) {
        this.systemPSUInputPower = value;
    }

    /**
     * Gets the value of the systemPSUOutputPower property.
     * 
     */
    public short getSystemPSUOutputPower() {
        return systemPSUOutputPower;
    }

    /**
     * Sets the value of the systemPSUOutputPower property.
     * 
     */
    public void setSystemPSUOutputPower(short value) {
        this.systemPSUOutputPower = value;
    }

    /**
     * Gets the value of the urlString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURLString() {
        return urlString;
    }

    /**
     * Sets the value of the urlString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURLString(String value) {
        this.urlString = value;
    }

    /**
     * Gets the value of the useHostNameForSlotName property.
     * 
     */
    public byte getUseHostNameForSlotName() {
        return useHostNameForSlotName;
    }

    /**
     * Sets the value of the useHostNameForSlotName property.
     * 
     */
    public void setUseHostNameForSlotName(byte value) {
        this.useHostNameForSlotName = value;
    }

}
