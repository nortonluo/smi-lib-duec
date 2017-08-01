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
 *         &lt;element name="AttributeDisplayName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AttributeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DefaultValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Dependency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DisplayOrder" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="FQDD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupDisplayName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InstanceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsReadOnly" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaxLength" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MinLength" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="PendingValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "attributeDisplayName",
    "attributeName",
    "currentValue",
    "defaultValue",
    "dependency",
    "displayOrder",
    "fqdd",
    "groupDisplayName",
    "groupID",
    "instanceID",
    "isReadOnly",
    "maxLength",
    "minLength",
    "pendingValue"
})
@XmlRootElement(name = "DCIM_SystemString", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString")
public class DCIMSystemString {

    @XmlElement(name = "AttributeDisplayName", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString", required = true)
    protected String attributeDisplayName;
    @XmlElement(name = "AttributeName", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString", required = true)
    protected String attributeName;
    @XmlElement(name = "CurrentValue", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString", required = true)
    protected String currentValue;
    @XmlElement(name = "DefaultValue", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString", required = true, nillable = true)
    protected String defaultValue;
    @XmlElement(name = "Dependency", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString", required = true, nillable = true)
    protected String dependency;
    @XmlElement(name = "DisplayOrder", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString")
    protected short displayOrder;
    @XmlElement(name = "FQDD", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString", required = true)
    protected String fqdd;
    @XmlElement(name = "GroupDisplayName", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString", required = true)
    protected String groupDisplayName;
    @XmlElement(name = "GroupID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString", required = true)
    protected String groupID;
    @XmlElement(name = "InstanceID", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString", required = true)
    protected String instanceID;
    @XmlElement(name = "IsReadOnly", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString", required = true)
    protected String isReadOnly;
    @XmlElement(name = "MaxLength", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString", required = true)
    protected String maxLength;
    @XmlElement(name = "MinLength", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString")
    protected byte minLength;
    @XmlElement(name = "PendingValue", namespace = "http://schemas.dell.com/wbem/wscim/1/cim-schema/2/DCIM_SystemString", required = true, nillable = true)
    protected String pendingValue;

    /**
     * Gets the value of the attributeDisplayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeDisplayName() {
        return attributeDisplayName;
    }

    /**
     * Sets the value of the attributeDisplayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeDisplayName(String value) {
        this.attributeDisplayName = value;
    }

    /**
     * Gets the value of the attributeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Sets the value of the attributeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeName(String value) {
        this.attributeName = value;
    }

    /**
     * Gets the value of the currentValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentValue() {
        return currentValue;
    }

    /**
     * Sets the value of the currentValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentValue(String value) {
        this.currentValue = value;
    }

    /**
     * Gets the value of the defaultValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the value of the defaultValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultValue(String value) {
        this.defaultValue = value;
    }

    /**
     * Gets the value of the dependency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDependency() {
        return dependency;
    }

    /**
     * Sets the value of the dependency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDependency(String value) {
        this.dependency = value;
    }

    /**
     * Gets the value of the displayOrder property.
     * 
     */
    public short getDisplayOrder() {
        return displayOrder;
    }

    /**
     * Sets the value of the displayOrder property.
     * 
     */
    public void setDisplayOrder(short value) {
        this.displayOrder = value;
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
     * Gets the value of the groupDisplayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupDisplayName() {
        return groupDisplayName;
    }

    /**
     * Sets the value of the groupDisplayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupDisplayName(String value) {
        this.groupDisplayName = value;
    }

    /**
     * Gets the value of the groupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * Sets the value of the groupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupID(String value) {
        this.groupID = value;
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
     * Gets the value of the isReadOnly property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsReadOnly() {
        return isReadOnly;
    }

    /**
     * Sets the value of the isReadOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsReadOnly(String value) {
        this.isReadOnly = value;
    }

    /**
     * Gets the value of the maxLength property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxLength() {
        return maxLength;
    }

    /**
     * Sets the value of the maxLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxLength(String value) {
        this.maxLength = value;
    }

    /**
     * Gets the value of the minLength property.
     * 
     */
    public byte getMinLength() {
        return minLength;
    }

    /**
     * Sets the value of the minLength property.
     * 
     */
    public void setMinLength(byte value) {
        this.minLength = value;
    }

    /**
     * Gets the value of the pendingValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPendingValue() {
        return pendingValue;
    }

    /**
     * Sets the value of the pendingValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPendingValue(String value) {
        this.pendingValue = value;
    }

}
