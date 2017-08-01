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
 *         &lt;element name="BuildNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Classifications" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="ComponentID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ComponentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeviceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ElementName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FQDD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdentityInfoType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdentityInfoValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InstallationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InstanceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsEntity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MajorVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MinorVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RevisionNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RevisionString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SubDeviceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SubVendorID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Updateable" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VendorID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VersionString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="impactsTPMmeasurements" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "buildNumber",
    "classifications",
    "componentID",
    "componentType",
    "deviceID",
    "elementName",
    "fqdd",
    "identityInfoType",
    "identityInfoValue",
    "installationDate",
    "instanceID",
    "isEntity",
    "majorVersion",
    "minorVersion",
    "revisionNumber",
    "revisionString",
    "status",
    "subDeviceID",
    "subVendorID",
    "updateable",
    "vendorID",
    "versionString",
    "impactsTPMmeasurements"
})
@XmlRootElement(name = "DCIM_SoftwareIdentity", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity")
public class DCIMSoftwareIdentity {

    @XmlElement(name = "BuildNumber", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String buildNumber;
    @XmlElement(name = "Classifications", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity")
    protected byte classifications;
    @XmlElement(name = "ComponentID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String componentID;
    @XmlElement(name = "ComponentType", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String componentType;
    @XmlElement(name = "DeviceID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true, nillable = true)
    protected String deviceID;
    @XmlElement(name = "ElementName", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String elementName;
    @XmlElement(name = "FQDD", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String fqdd;
    @XmlElement(name = "IdentityInfoType", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String identityInfoType;
    @XmlElement(name = "IdentityInfoValue", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String identityInfoValue;
    @XmlElement(name = "InstallationDate", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String installationDate;
    @XmlElement(name = "InstanceID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String instanceID;
    @XmlElement(name = "IsEntity", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String isEntity;
    @XmlElement(name = "MajorVersion", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String majorVersion;
    @XmlElement(name = "MinorVersion", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String minorVersion;
    @XmlElement(name = "RevisionNumber", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String revisionNumber;
    @XmlElement(name = "RevisionString", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true, nillable = true)
    protected String revisionString;
    @XmlElement(name = "Status", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String status;
    @XmlElement(name = "SubDeviceID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true, nillable = true)
    protected String subDeviceID;
    @XmlElement(name = "SubVendorID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true, nillable = true)
    protected String subVendorID;
    @XmlElement(name = "Updateable", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String updateable;
    @XmlElement(name = "VendorID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true, nillable = true)
    protected String vendorID;
    @XmlElement(name = "VersionString", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String versionString;
    @XmlElement(namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SoftwareIdentity", required = true)
    protected String impactsTPMmeasurements;

    /**
     * Gets the value of the buildNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildNumber() {
        return buildNumber;
    }

    /**
     * Sets the value of the buildNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildNumber(String value) {
        this.buildNumber = value;
    }

    /**
     * Gets the value of the classifications property.
     * 
     */
    public byte getClassifications() {
        return classifications;
    }

    /**
     * Sets the value of the classifications property.
     * 
     */
    public void setClassifications(byte value) {
        this.classifications = value;
    }

    /**
     * Gets the value of the componentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponentID() {
        return componentID;
    }

    /**
     * Sets the value of the componentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponentID(String value) {
        this.componentID = value;
    }

    /**
     * Gets the value of the componentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponentType() {
        return componentType;
    }

    /**
     * Sets the value of the componentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponentType(String value) {
        this.componentType = value;
    }

    /**
     * Gets the value of the deviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceID() {
        return deviceID;
    }

    /**
     * Sets the value of the deviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceID(String value) {
        this.deviceID = value;
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
     * Gets the value of the identityInfoType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityInfoType() {
        return identityInfoType;
    }

    /**
     * Sets the value of the identityInfoType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityInfoType(String value) {
        this.identityInfoType = value;
    }

    /**
     * Gets the value of the identityInfoValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityInfoValue() {
        return identityInfoValue;
    }

    /**
     * Sets the value of the identityInfoValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityInfoValue(String value) {
        this.identityInfoValue = value;
    }

    /**
     * Gets the value of the installationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallationDate() {
        return installationDate;
    }

    /**
     * Sets the value of the installationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallationDate(String value) {
        this.installationDate = value;
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
     * Gets the value of the isEntity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEntity() {
        return isEntity;
    }

    /**
     * Sets the value of the isEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEntity(String value) {
        this.isEntity = value;
    }

    /**
     * Gets the value of the majorVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMajorVersion() {
        return majorVersion;
    }

    /**
     * Sets the value of the majorVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMajorVersion(String value) {
        this.majorVersion = value;
    }

    /**
     * Gets the value of the minorVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinorVersion() {
        return minorVersion;
    }

    /**
     * Sets the value of the minorVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinorVersion(String value) {
        this.minorVersion = value;
    }

    /**
     * Gets the value of the revisionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevisionNumber() {
        return revisionNumber;
    }

    /**
     * Sets the value of the revisionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevisionNumber(String value) {
        this.revisionNumber = value;
    }

    /**
     * Gets the value of the revisionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevisionString() {
        return revisionString;
    }

    /**
     * Sets the value of the revisionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevisionString(String value) {
        this.revisionString = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the subDeviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubDeviceID() {
        return subDeviceID;
    }

    /**
     * Sets the value of the subDeviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubDeviceID(String value) {
        this.subDeviceID = value;
    }

    /**
     * Gets the value of the subVendorID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubVendorID() {
        return subVendorID;
    }

    /**
     * Sets the value of the subVendorID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubVendorID(String value) {
        this.subVendorID = value;
    }

    /**
     * Gets the value of the updateable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateable() {
        return updateable;
    }

    /**
     * Sets the value of the updateable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateable(String value) {
        this.updateable = value;
    }

    /**
     * Gets the value of the vendorID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorID() {
        return vendorID;
    }

    /**
     * Sets the value of the vendorID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorID(String value) {
        this.vendorID = value;
    }

    /**
     * Gets the value of the versionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionString() {
        return versionString;
    }

    /**
     * Sets the value of the versionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionString(String value) {
        this.versionString = value;
    }

    /**
     * Gets the value of the impactsTPMmeasurements property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImpactsTPMmeasurements() {
        return impactsTPMmeasurements;
    }

    /**
     * Sets the value of the impactsTPMmeasurements property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImpactsTPMmeasurements(String value) {
        this.impactsTPMmeasurements = value;
    }

}
