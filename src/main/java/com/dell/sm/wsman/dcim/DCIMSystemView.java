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
 *         &lt;element name="AssetTag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BIOSReleaseDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BIOSVersionString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BaseBoardChassisSlot" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BatteryRollupStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="BladeGeometry" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="BoardPartNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BoardSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CMCIP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CPLDVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CPURollupStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="ChassisModel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChassisName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChassisServiceTag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChassisSystemHeight" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="DeviceDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EstimatedSystemAirflow" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="ExpressServiceCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="FQDD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FanRollupStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="HostName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InstanceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastSystemInventoryTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastUpdateTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LicensingRollupStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="LifecycleControllerVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Manufacturer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaxCPUSockets" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="MaxDIMMSlots" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="MaxPCIeSlots" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="MemoryOperationMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Model" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NodeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PSRollupStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="PlatformGUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PopulatedCPUSockets" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="PopulatedDIMMSlots" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="PopulatedPCIeSlots" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="PowerCap" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="PowerCapEnabledState" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="PowerState" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="PrimaryStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="RollupStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="ServerAllocation" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="ServiceTag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StorageRollupStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="SysMemErrorMethodology" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="SysMemFailOverState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SysMemLocation" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="SysMemMaxCapacitySize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SysMemPrimaryStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="SysMemTotalSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SystemGeneration" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SystemID" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="SystemRevision" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="TempRollupStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="UUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VoltRollupStatus" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="smbiosGUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "biosReleaseDate",
    "biosVersionString",
    "baseBoardChassisSlot",
    "batteryRollupStatus",
    "bladeGeometry",
    "boardPartNumber",
    "boardSerialNumber",
    "cmcip",
    "cpldVersion",
    "cpuRollupStatus",
    "chassisModel",
    "chassisName",
    "chassisServiceTag",
    "chassisSystemHeight",
    "deviceDescription",
    "estimatedSystemAirflow",
    "expressServiceCode",
    "fqdd",
    "fanRollupStatus",
    "hostName",
    "instanceID",
    "lastSystemInventoryTime",
    "lastUpdateTime",
    "licensingRollupStatus",
    "lifecycleControllerVersion",
    "manufacturer",
    "maxCPUSockets",
    "maxDIMMSlots",
    "maxPCIeSlots",
    "memoryOperationMode",
    "model",
    "nodeID",
    "psRollupStatus",
    "platformGUID",
    "populatedCPUSockets",
    "populatedDIMMSlots",
    "populatedPCIeSlots",
    "powerCap",
    "powerCapEnabledState",
    "powerState",
    "primaryStatus",
    "rollupStatus",
    "serverAllocation",
    "serviceTag",
    "storageRollupStatus",
    "sysMemErrorMethodology",
    "sysMemFailOverState",
    "sysMemLocation",
    "sysMemMaxCapacitySize",
    "sysMemPrimaryStatus",
    "sysMemTotalSize",
    "systemGeneration",
    "systemID",
    "systemRevision",
    "tempRollupStatus",
    "uuid",
    "voltRollupStatus",
    "smbiosGUID"
})
@XmlRootElement(name = "DCIM_SystemView", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
public class DCIMSystemView {

    @XmlElement(name = "AssetTag", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String assetTag;
    @XmlElement(name = "BIOSReleaseDate", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String biosReleaseDate;
    @XmlElement(name = "BIOSVersionString", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String biosVersionString;
    @XmlElement(name = "BaseBoardChassisSlot", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String baseBoardChassisSlot;
    @XmlElement(name = "BatteryRollupStatus", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte batteryRollupStatus;
    @XmlElement(name = "BladeGeometry", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte bladeGeometry;
    @XmlElement(name = "BoardPartNumber", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String boardPartNumber;
    @XmlElement(name = "BoardSerialNumber", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String boardSerialNumber;
    @XmlElement(name = "CMCIP", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String cmcip;
    @XmlElement(name = "CPLDVersion", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String cpldVersion;
    @XmlElement(name = "CPURollupStatus", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte cpuRollupStatus;
    @XmlElement(name = "ChassisModel", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String chassisModel;
    @XmlElement(name = "ChassisName", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String chassisName;
    @XmlElement(name = "ChassisServiceTag", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String chassisServiceTag;
    @XmlElement(name = "ChassisSystemHeight", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte chassisSystemHeight;
    @XmlElement(name = "DeviceDescription", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String deviceDescription;
    @XmlElement(name = "EstimatedSystemAirflow", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected short estimatedSystemAirflow;
    @XmlElement(name = "ExpressServiceCode", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected long expressServiceCode;
    @XmlElement(name = "FQDD", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String fqdd;
    @XmlElement(name = "FanRollupStatus", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte fanRollupStatus;
    @XmlElement(name = "HostName", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String hostName;
    @XmlElement(name = "InstanceID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String instanceID;
    @XmlElement(name = "LastSystemInventoryTime", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String lastSystemInventoryTime;
    @XmlElement(name = "LastUpdateTime", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String lastUpdateTime;
    @XmlElement(name = "LicensingRollupStatus", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte licensingRollupStatus;
    @XmlElement(name = "LifecycleControllerVersion", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String lifecycleControllerVersion;
    @XmlElement(name = "Manufacturer", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String manufacturer;
    @XmlElement(name = "MaxCPUSockets", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte maxCPUSockets;
    @XmlElement(name = "MaxDIMMSlots", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte maxDIMMSlots;
    @XmlElement(name = "MaxPCIeSlots", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte maxPCIeSlots;
    @XmlElement(name = "MemoryOperationMode", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String memoryOperationMode;
    @XmlElement(name = "Model", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String model;
    @XmlElement(name = "NodeID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String nodeID;
    @XmlElement(name = "PSRollupStatus", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte psRollupStatus;
    @XmlElement(name = "PlatformGUID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String platformGUID;
    @XmlElement(name = "PopulatedCPUSockets", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte populatedCPUSockets;
    @XmlElement(name = "PopulatedDIMMSlots", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte populatedDIMMSlots;
    @XmlElement(name = "PopulatedPCIeSlots", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte populatedPCIeSlots;
    @XmlElement(name = "PowerCap", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected short powerCap;
    @XmlElement(name = "PowerCapEnabledState", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte powerCapEnabledState;
    @XmlElement(name = "PowerState", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte powerState;
    @XmlElement(name = "PrimaryStatus", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte primaryStatus;
    @XmlElement(name = "RollupStatus", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte rollupStatus;
    @XmlElement(name = "ServerAllocation", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected short serverAllocation;
    @XmlElement(name = "ServiceTag", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String serviceTag;
    @XmlElement(name = "StorageRollupStatus", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte storageRollupStatus;
    @XmlElement(name = "SysMemErrorMethodology", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte sysMemErrorMethodology;
    @XmlElement(name = "SysMemFailOverState", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String sysMemFailOverState;
    @XmlElement(name = "SysMemLocation", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte sysMemLocation;
    @XmlElement(name = "SysMemMaxCapacitySize", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected int sysMemMaxCapacitySize;
    @XmlElement(name = "SysMemPrimaryStatus", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte sysMemPrimaryStatus;
    @XmlElement(name = "SysMemTotalSize", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected int sysMemTotalSize;
    @XmlElement(name = "SystemGeneration", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String systemGeneration;
    @XmlElement(name = "SystemID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected short systemID;
    @XmlElement(name = "SystemRevision", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte systemRevision;
    @XmlElement(name = "TempRollupStatus", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte tempRollupStatus;
    @XmlElement(name = "UUID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String uuid;
    @XmlElement(name = "VoltRollupStatus", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView")
    protected byte voltRollupStatus;
    @XmlElement(namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemView", required = true)
    protected String smbiosGUID;

    /**
     * Gets the value of the assetTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssetTag() {
        return assetTag;
    }

    /**
     * Sets the value of the assetTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssetTag(String value) {
        this.assetTag = value;
    }

    /**
     * Gets the value of the biosReleaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBIOSReleaseDate() {
        return biosReleaseDate;
    }

    /**
     * Sets the value of the biosReleaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBIOSReleaseDate(String value) {
        this.biosReleaseDate = value;
    }

    /**
     * Gets the value of the biosVersionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBIOSVersionString() {
        return biosVersionString;
    }

    /**
     * Sets the value of the biosVersionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBIOSVersionString(String value) {
        this.biosVersionString = value;
    }

    /**
     * Gets the value of the baseBoardChassisSlot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseBoardChassisSlot() {
        return baseBoardChassisSlot;
    }

    /**
     * Sets the value of the baseBoardChassisSlot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseBoardChassisSlot(String value) {
        this.baseBoardChassisSlot = value;
    }

    /**
     * Gets the value of the batteryRollupStatus property.
     * 
     */
    public byte getBatteryRollupStatus() {
        return batteryRollupStatus;
    }

    /**
     * Sets the value of the batteryRollupStatus property.
     * 
     */
    public void setBatteryRollupStatus(byte value) {
        this.batteryRollupStatus = value;
    }

    /**
     * Gets the value of the bladeGeometry property.
     * 
     */
    public byte getBladeGeometry() {
        return bladeGeometry;
    }

    /**
     * Sets the value of the bladeGeometry property.
     * 
     */
    public void setBladeGeometry(byte value) {
        this.bladeGeometry = value;
    }

    /**
     * Gets the value of the boardPartNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoardPartNumber() {
        return boardPartNumber;
    }

    /**
     * Sets the value of the boardPartNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoardPartNumber(String value) {
        this.boardPartNumber = value;
    }

    /**
     * Gets the value of the boardSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoardSerialNumber() {
        return boardSerialNumber;
    }

    /**
     * Sets the value of the boardSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoardSerialNumber(String value) {
        this.boardSerialNumber = value;
    }

    /**
     * Gets the value of the cmcip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCMCIP() {
        return cmcip;
    }

    /**
     * Sets the value of the cmcip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCMCIP(String value) {
        this.cmcip = value;
    }

    /**
     * Gets the value of the cpldVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPLDVersion() {
        return cpldVersion;
    }

    /**
     * Sets the value of the cpldVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPLDVersion(String value) {
        this.cpldVersion = value;
    }

    /**
     * Gets the value of the cpuRollupStatus property.
     * 
     */
    public byte getCPURollupStatus() {
        return cpuRollupStatus;
    }

    /**
     * Sets the value of the cpuRollupStatus property.
     * 
     */
    public void setCPURollupStatus(byte value) {
        this.cpuRollupStatus = value;
    }

    /**
     * Gets the value of the chassisModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChassisModel() {
        return chassisModel;
    }

    /**
     * Sets the value of the chassisModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChassisModel(String value) {
        this.chassisModel = value;
    }

    /**
     * Gets the value of the chassisName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChassisName() {
        return chassisName;
    }

    /**
     * Sets the value of the chassisName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChassisName(String value) {
        this.chassisName = value;
    }

    /**
     * Gets the value of the chassisServiceTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChassisServiceTag() {
        return chassisServiceTag;
    }

    /**
     * Sets the value of the chassisServiceTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChassisServiceTag(String value) {
        this.chassisServiceTag = value;
    }

    /**
     * Gets the value of the chassisSystemHeight property.
     * 
     */
    public byte getChassisSystemHeight() {
        return chassisSystemHeight;
    }

    /**
     * Sets the value of the chassisSystemHeight property.
     * 
     */
    public void setChassisSystemHeight(byte value) {
        this.chassisSystemHeight = value;
    }

    /**
     * Gets the value of the deviceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceDescription() {
        return deviceDescription;
    }

    /**
     * Sets the value of the deviceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceDescription(String value) {
        this.deviceDescription = value;
    }

    /**
     * Gets the value of the estimatedSystemAirflow property.
     * 
     */
    public short getEstimatedSystemAirflow() {
        return estimatedSystemAirflow;
    }

    /**
     * Sets the value of the estimatedSystemAirflow property.
     * 
     */
    public void setEstimatedSystemAirflow(short value) {
        this.estimatedSystemAirflow = value;
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
     * Gets the value of the fanRollupStatus property.
     * 
     */
    public byte getFanRollupStatus() {
        return fanRollupStatus;
    }

    /**
     * Sets the value of the fanRollupStatus property.
     * 
     */
    public void setFanRollupStatus(byte value) {
        this.fanRollupStatus = value;
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
     * Gets the value of the lastSystemInventoryTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastSystemInventoryTime() {
        return lastSystemInventoryTime;
    }

    /**
     * Sets the value of the lastSystemInventoryTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastSystemInventoryTime(String value) {
        this.lastSystemInventoryTime = value;
    }

    /**
     * Gets the value of the lastUpdateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * Sets the value of the lastUpdateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdateTime(String value) {
        this.lastUpdateTime = value;
    }

    /**
     * Gets the value of the licensingRollupStatus property.
     * 
     */
    public byte getLicensingRollupStatus() {
        return licensingRollupStatus;
    }

    /**
     * Sets the value of the licensingRollupStatus property.
     * 
     */
    public void setLicensingRollupStatus(byte value) {
        this.licensingRollupStatus = value;
    }

    /**
     * Gets the value of the lifecycleControllerVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLifecycleControllerVersion() {
        return lifecycleControllerVersion;
    }

    /**
     * Sets the value of the lifecycleControllerVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLifecycleControllerVersion(String value) {
        this.lifecycleControllerVersion = value;
    }

    /**
     * Gets the value of the manufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the value of the manufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    /**
     * Gets the value of the maxCPUSockets property.
     * 
     */
    public byte getMaxCPUSockets() {
        return maxCPUSockets;
    }

    /**
     * Sets the value of the maxCPUSockets property.
     * 
     */
    public void setMaxCPUSockets(byte value) {
        this.maxCPUSockets = value;
    }

    /**
     * Gets the value of the maxDIMMSlots property.
     * 
     */
    public byte getMaxDIMMSlots() {
        return maxDIMMSlots;
    }

    /**
     * Sets the value of the maxDIMMSlots property.
     * 
     */
    public void setMaxDIMMSlots(byte value) {
        this.maxDIMMSlots = value;
    }

    /**
     * Gets the value of the maxPCIeSlots property.
     * 
     */
    public byte getMaxPCIeSlots() {
        return maxPCIeSlots;
    }

    /**
     * Sets the value of the maxPCIeSlots property.
     * 
     */
    public void setMaxPCIeSlots(byte value) {
        this.maxPCIeSlots = value;
    }

    /**
     * Gets the value of the memoryOperationMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemoryOperationMode() {
        return memoryOperationMode;
    }

    /**
     * Sets the value of the memoryOperationMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemoryOperationMode(String value) {
        this.memoryOperationMode = value;
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
     * Gets the value of the nodeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeID() {
        return nodeID;
    }

    /**
     * Sets the value of the nodeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeID(String value) {
        this.nodeID = value;
    }

    /**
     * Gets the value of the psRollupStatus property.
     * 
     */
    public byte getPSRollupStatus() {
        return psRollupStatus;
    }

    /**
     * Sets the value of the psRollupStatus property.
     * 
     */
    public void setPSRollupStatus(byte value) {
        this.psRollupStatus = value;
    }

    /**
     * Gets the value of the platformGUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlatformGUID() {
        return platformGUID;
    }

    /**
     * Sets the value of the platformGUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlatformGUID(String value) {
        this.platformGUID = value;
    }

    /**
     * Gets the value of the populatedCPUSockets property.
     * 
     */
    public byte getPopulatedCPUSockets() {
        return populatedCPUSockets;
    }

    /**
     * Sets the value of the populatedCPUSockets property.
     * 
     */
    public void setPopulatedCPUSockets(byte value) {
        this.populatedCPUSockets = value;
    }

    /**
     * Gets the value of the populatedDIMMSlots property.
     * 
     */
    public byte getPopulatedDIMMSlots() {
        return populatedDIMMSlots;
    }

    /**
     * Sets the value of the populatedDIMMSlots property.
     * 
     */
    public void setPopulatedDIMMSlots(byte value) {
        this.populatedDIMMSlots = value;
    }

    /**
     * Gets the value of the populatedPCIeSlots property.
     * 
     */
    public byte getPopulatedPCIeSlots() {
        return populatedPCIeSlots;
    }

    /**
     * Sets the value of the populatedPCIeSlots property.
     * 
     */
    public void setPopulatedPCIeSlots(byte value) {
        this.populatedPCIeSlots = value;
    }

    /**
     * Gets the value of the powerCap property.
     * 
     */
    public short getPowerCap() {
        return powerCap;
    }

    /**
     * Sets the value of the powerCap property.
     * 
     */
    public void setPowerCap(short value) {
        this.powerCap = value;
    }

    /**
     * Gets the value of the powerCapEnabledState property.
     * 
     */
    public byte getPowerCapEnabledState() {
        return powerCapEnabledState;
    }

    /**
     * Sets the value of the powerCapEnabledState property.
     * 
     */
    public void setPowerCapEnabledState(byte value) {
        this.powerCapEnabledState = value;
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
     * Gets the value of the rollupStatus property.
     * 
     */
    public byte getRollupStatus() {
        return rollupStatus;
    }

    /**
     * Sets the value of the rollupStatus property.
     * 
     */
    public void setRollupStatus(byte value) {
        this.rollupStatus = value;
    }

    /**
     * Gets the value of the serverAllocation property.
     * 
     */
    public short getServerAllocation() {
        return serverAllocation;
    }

    /**
     * Sets the value of the serverAllocation property.
     * 
     */
    public void setServerAllocation(short value) {
        this.serverAllocation = value;
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
     * Gets the value of the storageRollupStatus property.
     * 
     */
    public byte getStorageRollupStatus() {
        return storageRollupStatus;
    }

    /**
     * Sets the value of the storageRollupStatus property.
     * 
     */
    public void setStorageRollupStatus(byte value) {
        this.storageRollupStatus = value;
    }

    /**
     * Gets the value of the sysMemErrorMethodology property.
     * 
     */
    public byte getSysMemErrorMethodology() {
        return sysMemErrorMethodology;
    }

    /**
     * Sets the value of the sysMemErrorMethodology property.
     * 
     */
    public void setSysMemErrorMethodology(byte value) {
        this.sysMemErrorMethodology = value;
    }

    /**
     * Gets the value of the sysMemFailOverState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysMemFailOverState() {
        return sysMemFailOverState;
    }

    /**
     * Sets the value of the sysMemFailOverState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysMemFailOverState(String value) {
        this.sysMemFailOverState = value;
    }

    /**
     * Gets the value of the sysMemLocation property.
     * 
     */
    public byte getSysMemLocation() {
        return sysMemLocation;
    }

    /**
     * Sets the value of the sysMemLocation property.
     * 
     */
    public void setSysMemLocation(byte value) {
        this.sysMemLocation = value;
    }

    /**
     * Gets the value of the sysMemMaxCapacitySize property.
     * 
     */
    public int getSysMemMaxCapacitySize() {
        return sysMemMaxCapacitySize;
    }

    /**
     * Sets the value of the sysMemMaxCapacitySize property.
     * 
     */
    public void setSysMemMaxCapacitySize(int value) {
        this.sysMemMaxCapacitySize = value;
    }

    /**
     * Gets the value of the sysMemPrimaryStatus property.
     * 
     */
    public byte getSysMemPrimaryStatus() {
        return sysMemPrimaryStatus;
    }

    /**
     * Sets the value of the sysMemPrimaryStatus property.
     * 
     */
    public void setSysMemPrimaryStatus(byte value) {
        this.sysMemPrimaryStatus = value;
    }

    /**
     * Gets the value of the sysMemTotalSize property.
     * 
     */
    public int getSysMemTotalSize() {
        return sysMemTotalSize;
    }

    /**
     * Sets the value of the sysMemTotalSize property.
     * 
     */
    public void setSysMemTotalSize(int value) {
        this.sysMemTotalSize = value;
    }

    /**
     * Gets the value of the systemGeneration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemGeneration() {
        return systemGeneration;
    }

    /**
     * Sets the value of the systemGeneration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemGeneration(String value) {
        this.systemGeneration = value;
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
     * Gets the value of the systemRevision property.
     * 
     */
    public byte getSystemRevision() {
        return systemRevision;
    }

    /**
     * Sets the value of the systemRevision property.
     * 
     */
    public void setSystemRevision(byte value) {
        this.systemRevision = value;
    }

    /**
     * Gets the value of the tempRollupStatus property.
     * 
     */
    public byte getTempRollupStatus() {
        return tempRollupStatus;
    }

    /**
     * Sets the value of the tempRollupStatus property.
     * 
     */
    public void setTempRollupStatus(byte value) {
        this.tempRollupStatus = value;
    }

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUUID(String value) {
        this.uuid = value;
    }

    /**
     * Gets the value of the voltRollupStatus property.
     * 
     */
    public byte getVoltRollupStatus() {
        return voltRollupStatus;
    }

    /**
     * Sets the value of the voltRollupStatus property.
     * 
     */
    public void setVoltRollupStatus(byte value) {
        this.voltRollupStatus = value;
    }

    /**
     * Gets the value of the smbiosGUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmbiosGUID() {
        return smbiosGUID;
    }

    /**
     * Sets the value of the smbiosGUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmbiosGUID(String value) {
        this.smbiosGUID = value;
    }

}
