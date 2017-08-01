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
package com.dell.cm.updateinformationmodel;

import java.util.HashSet;
import java.util.Locale;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Operating System
 *
 * @author Raveendra_Madala
 */
public class DCMOperatingSystem {

    /**
     * Constructor
     */
    public DCMOperatingSystem() {
        mLocales = new HashSet<>();
    }

    /**
     * Copy Constructor
     *
     * @param inOS specifies the object from which this object is to be
     * constructed
     */
    public DCMOperatingSystem(DCMOperatingSystem inOS) {
        mLocales = new HashSet<>();
        if (inOS != null) {
            if (inOS.mName != null) {
                mName = new DCMI18NString(inOS.mName);
            }
            mCode = inOS.mCode;
            mVendor = inOS.mVendor;
            mArchitecture = inOS.mArchitecture;
            mType = inOS.mType;
            mMajorVersion = inOS.mMajorVersion;
            mMinorVersion = inOS.mMinorVersion;
            mServicePackMajorVersion = inOS.mServicePackMajorVersion;
            mServicePackMinorVersion = inOS.mServicePackMinorVersion;
            mPreInstallEnvironment = inOS.mPreInstallEnvironment;
            mSuiteMask = inOS.mSuiteMask;
            mLocales.addAll(inOS.mLocales);
        }
    }

    /**
     * Method for getting the name
     *
     * @return name of type DCMI18NString
     */
    public DCMI18NString getName() {
        return mName;
    }

    /**
     * Method for setting the name
     *
     * @param inName specifies the name being set
     * @return SUCCESS if the name could be set else appropriate error code is
     * returned
     */
    public int setName(DCMI18NString inName) {
        mName = new DCMI18NString(inName);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for adding a supported locale
     *
     * @param inLocale specifies the locale being added
     * @return SUCCESS if the locale could be added else appropriate error code
     * is returned
     */
    public int addSupportForLocale(Locale inLocale) {
        if (mLocales.contains(inLocale)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mLocales.add(inLocale);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the supported locale
     *
     * @param inLocale specifies the locale being removed
     * @return SUCCESS if the locale could be removed else appropriate error
     * code is returned
     */
    public int removeSupportForLocale(Locale inLocale) {
        if (!mLocales.contains(inLocale)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mLocales.remove(inLocale);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the supported locales
     *
     * @return the supported locales
     */
    public HashSet<Locale> getSupportedLocales() {
        return mLocales;
    }

    /**
     * Method for getting the code
     *
     * @return the code
     */
    public String getCode() {
        return mCode;
    }

    /**
     * Method for setting the code
     *
     * @param inCode specifies the code to be set
     * @return SUCCESS if the code could be set else appropriate error code is
     * returned.
     */
    public int setCode(String inCode) {
        mCode = inCode;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the vendor
     *
     * @return the vendor
     */
    public String getVendor() {
        return mVendor;
    }

    /**
     * Method for getting the vendor
     *
     * @param inVendor specifies the vendor being set
     * @return Success if the vendor could be set else appropriate error code is
     * returned.
     */
    public int setVendor(String inVendor) {
        mVendor = inVendor;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the architecture
     *
     * @return the operating system architecture
     */
    public DCMOSArchitecture getArchitecture() {
        return mArchitecture;
    }

    /**
     * Method for setting the architecture
     *
     * @param inArchitecture specifies the architecture being set
     * @return SUCCESS if architecture could be set else appropriate error code
     * is returned
     */
    public int setArchitecture(DCMOSArchitecture inArchitecture) {
        mArchitecture = inArchitecture;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the type
     *
     * @return the operating system type
     */
    public DCMOSType getType() {
        return mType;
    }

    /**
     * Method for setting the type
     *
     * @param inType specifies the type being set
     * @return SUCCESS if the type could be set else appropriate error code is
     * returned
     */
    public int setType(DCMOSType inType) {
        mType = inType;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the major version
     *
     * @return the major version
     */
    public String getMajorVersion() {
        return mMajorVersion;
    }

    /**
     * Method for setting the major version
     *
     * @param inVersion specifies the version being set
     * @return SUCCESS if the major version could be set else appropriate error
     * code is returned
     */
    public int setMajorVersion(String inVersion) {
        mMajorVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the minor version
     *
     * @return the minor version
     */
    public String getMinorVersion() {
        return mMinorVersion;
    }

    /**
     * Method for setting the minor version
     *
     * @param inVersion specifies the version being set
     * @return SUCCESS if the minor version could be set else appropriate error
     * code is returned
     */
    public int setMinorVersion(String inVersion) {
        mMinorVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the service pack major version
     *
     * @return the service pack major version
     */
    public String getServicePackMajorVersion() {
        return mServicePackMajorVersion;
    }

    /**
     * Method for setting the service pack major version
     *
     * @param inVersion specifies the version being set
     * @return SUCCESS if the service pack major version could be set else
     * appropriate error code is returned
     */
    public int setServicePackMajorVersion(String inVersion) {
        mServicePackMajorVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the service pack minor version
     *
     * @return the service pack minor version
     */
    public String getServicePackMinorVersion() {
        return mServicePackMinorVersion;
    }

    /**
     * Method for setting the service pack minor version
     *
     * @param inVersion specifies the version being set
     * @return SUCCESS if the service pack minor version could be set else
     * appropriate error code is returned
     */
    public int setServicePackMinorVersion(String inVersion) {
        mServicePackMinorVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for determining whether the OS is for pre-install environment or
     * not
     *
     * @return true if the OS is for pre-install environment else false is
     * returned
     */
    public boolean isForPreInstallEnvironment() {
        return mPreInstallEnvironment;
    }

    /**
     * Method for setting the pre-install environment value
     *
     * @param inValue specifies the value being set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned
     */
    public int setForPreInstallEnvironment(boolean inValue) {
        mPreInstallEnvironment = inValue;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the OS suite/flavor mask
     *
     * @return OS flavor mask
     */
    public int getFlavorMask() {
        return mSuiteMask;
    }

    /**
     * Method for setting the OS suite/flavor mask
     *
     * @param inMask specifies the value being set
     * @return SUCCESS if the value could be set else appropriate error code is
     * returned
     */
    public int setFlavorMask(int inMask) {
        mSuiteMask = inMask;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the unique identifier
     *
     * @return operating system's unique identifier
     */
    public String getUniqueIdentifier() {
        String retVal = new String();
        if (null != mVendor) {
            retVal = retVal.concat(mVendor);
        }
        if (null != mMajorVersion) {
            retVal = retVal.concat(mMajorVersion);
        }
        if (null != mMinorVersion) {
            retVal = retVal.concat(mMinorVersion);
        }
        if (null != mServicePackMajorVersion) {
            retVal = retVal.concat(mServicePackMajorVersion);
        }
        if (null != mServicePackMinorVersion) {
            retVal = retVal.concat(mServicePackMinorVersion);
        }
        if (null != mArchitecture) {
            switch (mArchitecture) {
                case X8632:
                    retVal = retVal.concat("X32");
                    break;
                case IA32:
                    retVal = retVal.concat("Ia32");
                    break;
                case IA64:
                    retVal = retVal.concat("Ia64");
                    break;
                case X8664:
                default:
                    retVal = retVal.concat("X64");
                    break;
            }
        }
        if (null != mType) {
            switch (mType) {
                case CLIENT:
                    retVal = retVal.concat("Client");
                    break;
                case CONTROLLER:
                    retVal = retVal.concat("Controller");
                    break;
                case SWITCH:
                    retVal = retVal.concat("Switch");
                    break;
                case SERVER:
                default:
                    retVal = retVal.concat("Server");
                    break;
            }
        }
        if (null != mCode) {
            retVal = retVal.concat(mCode);
        }
        if (mPreInstallEnvironment) {
            retVal = retVal.concat("1");
        } else {
            retVal = retVal.concat("0");
        }
        retVal = retVal.concat(Integer.toString(mSuiteMask));
        return retVal;
    }

    public Node toXML(Document document) {

        Element os = document.createElement(DCMConstants.OPERATING_SYSTEM);
        if (mName != null) {
            os.appendChild(mName.toXML(document));
        }

        if (!mCode.isEmpty()) {
            os.setAttribute(DCMConstants.OS_CODE_ATTRIBUTE, this.mCode);
        }
        if (!mVendor.isEmpty()) {
            os.setAttribute(DCMConstants.OS_VENDOR_ATTRIBUTE, mVendor);
        }
        // osArch
        if (null != mArchitecture) {

            os.setAttribute(DCMConstants.OS_ARCH_ATTRIBUTE, mArchitecture.toString());
        }
        // osType
        if (null != mType) {

            os.setAttribute(DCMConstants.OS_TYPE_ATTRIBUTE, mType.toString());
        }
        // majorVersion
        if (null != mMajorVersion) {
            os.setAttribute(DCMConstants.MAJOR_VERSION_ATTRIBUTE, mMajorVersion);
        }
        // minorVersion
        if (null != mMinorVersion) {
            os.setAttribute(DCMConstants.MINOR_VERSION_ATTRIBUTE, mMinorVersion);
        }
        // spMajorVersion
        if (null != mServicePackMajorVersion) {
            os.setAttribute(DCMConstants.SP_MAJOR_VERSION_ATTRIBUTE, mServicePackMajorVersion);
        }
        // spMinorVersion
        if (null != mServicePackMinorVersion) {
            os.setAttribute(DCMConstants.SP_MINOR_VERSION_ATTRIBUTE, mServicePackMinorVersion);
        }
        // preinstallationEnvironment
        os.setAttribute(DCMConstants.PRE_INSTALLATION_ENVIRONMENT_ATTRIBUTE, ((Boolean) mPreInstallEnvironment).toString());
        // suiteMask
        String suiteMask = Integer.toString(mSuiteMask);
        if (null != suiteMask) {
            os.setAttribute(DCMConstants.SUITE_MASK_ATTRIBUTE, suiteMask);
        }
        return os;
    }

    private DCMI18NString mName;
    private final HashSet<Locale> mLocales;
    private String mCode;
    private String mVendor;
    private DCMOSArchitecture mArchitecture;
    private DCMOSType mType;
    private String mMajorVersion = "";
    private String mMinorVersion = "";
    private String mServicePackMajorVersion = "";
    private String mServicePackMinorVersion = "";
    private boolean mPreInstallEnvironment;
    private int mSuiteMask;
}
