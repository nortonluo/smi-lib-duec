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
package com.dell.cm.updateinformationmodel.activationrule;

import com.dell.cm.updateinformationmodel.DCMBaseTarget;
import com.dell.cm.updateinformationmodel.DCMComponentType;
import com.dell.cm.updateinformationmodel.DCMConstants;
import com.dell.cm.updateinformationmodel.DCMI18NString;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import com.dell.cm.updateinformationmodel.DCMOSArchitecture;
import com.dell.cm.updateinformationmodel.DCMOSType;
import com.dell.cm.updateinformationmodel.DCMOperatingSystem;
import com.dell.cm.updateinformationmodel.DCMSystemInstance;
import com.dell.cm.updateinformationmodel.DCMUpdatePackageInformation;
import com.dell.cm.updateinformationmodel.DCMUpdateableComponent;
import com.dell.cm.updateinformationmodel.DCMVersionInformation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 */
public class DCMOSRule implements IDCMValidationRule {

    public static IDCMValidationRule create(Element inRule) {
        DCMOSRule rule = new DCMOSRule();

        if (null == inRule || !inRule.hasChildNodes()) {
            return null;
        }
        NodeList childNodeList = inRule.getElementsByTagName(DCMConstants.OPERATING_SYSTEM);
        if ((null != childNodeList) && (childNodeList.getLength() > 0)) {

            for (int childIndex = 0; childIndex < childNodeList.getLength(); ++childIndex) {
                if (childNodeList.item(childIndex).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element inOS = (Element) childNodeList.item(childIndex);
                DCMOperatingSystem retVal = new DCMOperatingSystem();
                // osCode
                String osCode = inOS.getAttribute(DCMConstants.OS_CODE_ATTRIBUTE);
                if (!osCode.isEmpty()) {
                    retVal.setCode(osCode);
                }
                // osVendor
                String osVendor = inOS.getAttribute(DCMConstants.OS_VENDOR_ATTRIBUTE);
                if (!osVendor.isEmpty()) {
                    retVal.setVendor(osVendor);
                }
                // osArch
                String osArch = inOS.getAttribute(DCMConstants.OS_ARCH_ATTRIBUTE);
                if (!osArch.isEmpty()) {
                    DCMOSArchitecture architectureEnum = DCMOSArchitecture.getEnumeration(osArch);
                    retVal.setArchitecture(architectureEnum);
                }
                // osType
                String osType = inOS.getAttribute(DCMConstants.OS_TYPE_ATTRIBUTE);
                if (!osType.isEmpty()) {
                    DCMOSType type = DCMOSType.getEnumeration(osType);
                    retVal.setType(type);
                }
                // majorVersion
                String majorVersion = inOS.getAttribute(DCMConstants.MAJOR_VERSION_ATTRIBUTE);
                if (!majorVersion.isEmpty()) {
                    retVal.setMajorVersion(majorVersion);
                }
                // minorVersion
                String minorVersion = inOS.getAttribute(DCMConstants.MINOR_VERSION_ATTRIBUTE);
                if (!minorVersion.isEmpty()) {
                    retVal.setMinorVersion(minorVersion);
                }
                // spMajorVersion
                String spMajorVersion = inOS.getAttribute(DCMConstants.SP_MAJOR_VERSION_ATTRIBUTE);
                if (!spMajorVersion.isEmpty()) {
                    retVal.setServicePackMajorVersion(spMajorVersion);
                }
                // spMinorVersion
                String spMinorVersion = inOS.getAttribute(DCMConstants.SP_MINOR_VERSION_ATTRIBUTE);
                if (!spMinorVersion.isEmpty()) {
                    retVal.setServicePackMinorVersion(spMinorVersion);
                }
                // preinstallationEnvironment
                String preEnvironment = inOS.getAttribute(DCMConstants.PRE_INSTALLATION_ENVIRONMENT_ATTRIBUTE);
                if (!preEnvironment.isEmpty()) {
                    retVal.setForPreInstallEnvironment(Boolean.parseBoolean(preEnvironment));
                }
                // suiteMask
                String suiteMask = inOS.getAttribute(DCMConstants.SUITE_MASK_ATTRIBUTE);
                if (!suiteMask.isEmpty()) {
                    retVal.setFlavorMask(Integer.parseInt(suiteMask));
                }

                DCMI18NString display = DCMI18NString.create(inOS);
                if ((null != display)) {
                    retVal.setName(display);
                }
                rule.getOperatingSystem().add(retVal);
            }
        }
        return rule;
    }

    /**
     * Gets the value of the operatingSystem property.
     *
     * @return possible object is {@link OperatingSystem }
     *
     */
    public List<DCMOperatingSystem> getOperatingSystem() {
        if (operatingSystems == null) {
            operatingSystems = new ArrayList<>();
        }
        return this.operatingSystems;
    }

    /**
     * Checks if this rule is applicable for this comparison.
     *
     * @param ruleData All Comparison Operation data.
     * @return the rule applicable after valuation
     *
     */
    @Override
    public boolean checkRule(DCMRuleData ruleData) {
        for (DCMOperatingSystem os : getOperatingSystem()) {
            if (os.getUniqueIdentifier().equals(ruleData.mOSIdentifier)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the object as XML Node.
     *
     * @param document document root element
     * @return Node created
     */
    @Override
    public Node toXML(Document document) {
        Element crule = document.createElement(DCMConstants.OSRULE);
        for (DCMOperatingSystem os : getOperatingSystem()) {
            crule.appendChild(os.toXML(document));
        }
        return crule;
    }

    protected List<DCMOperatingSystem> operatingSystems;
}
