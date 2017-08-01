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

import java.util.Date;
import java.net.InetAddress;

/**
 * class for representing the system instance
 *
 * @author Raveendra_Madala
 */
public class DCMSystemInstance {

    /**
     * Method for getting the collection time
     *
     * @return the time at which the instance information is collected
     */
    public Date getCollectionTime() {
        return mCollectionTime;
    }

    /**
     * Method for setting the collection time
     *
     * @param inTime specifies the time at which the instance information is
     * collected
     * @return SUCCESS is returned else appropriate error code is returned.
     */
    public int setCollectionTime(Date inTime) {
        mCollectionTime = inTime;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the system type identifier
     *
     * @return the system type identifier
     */
    public String getSystemTypeIdentifier() {
        return mSystemTypeIdentifier;
    }

    /**
     * Method for setting the system type identifier
     *
     * @param inIdentifier specifies the system type identifier
     * @return SUCCESS if the identifier is set else appropriate error code is
     * returned
     */
    public int setSystemTypeIdentifier(String inIdentifier) {
        mSystemTypeIdentifier = inIdentifier;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the service tag
     *
     * @return the service tag
     */
    public String getServiceTag() {
        return mServiceTag;
    }

    /**
     * Method for setting the service tag
     *
     * @param inServiceTag specifies the service tag being set
     * @return SUCCESS if the service tag is set else appropriate error code is
     * returned
     */
    public int setServiceTag(String inServiceTag) {
        mServiceTag = inServiceTag;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the host operating system identifier
     *
     * @return host operating system identifier
     */
    public String getHostOperatingSystemIdentifier() {
        return mHostOSIdentifier;
    }

    /**
     * Method for setting the host operating system identifier
     *
     * @param inIdentifier specifies the host operating system identifier being
     * set
     * @return SUCCESS if the identifier is set else appropriate error code is
     * returned
     */
    public int setHostOperatingSystemIdentifier(String inIdentifier) {
        mHostOSIdentifier = inIdentifier;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the host name
     *
     * @return the host name
     */
    public String getHostName() {
        return mHostName;
    }

    /**
     * Method for setting the host name
     *
     * @param inName specifies the name being set
     * @return SUCCESS if the name is set else appropriate error code is
     * returned
     */
    public int setHostName(String inName) {
        mHostName = inName;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the host IP
     *
     * @return host IP
     */
    public InetAddress getHostIP() {
        return mHostIP;
    }

    /**
     * Method for setting the host IP
     *
     * @param inIP specifies the IP address being set
     * @return SUCCESS if the IP could be set else appropriate error code is
     * returned.
     */
    public int setHostIP(InetAddress inIP) {
        mHostIP = inIP;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the iDRAC IP
     *
     * @return the iDRAC IP
     */
    public InetAddress getIDRACIP() {
        return miDRACIP;
    }

    /**
     * Method for setting the iDRAC IP
     *
     * @param inIP specifies the iDRAC IP
     * @return SUCCESS if the IP could be set else appropriate error code is
     * returned.
     */
    public int setIDRACIP(InetAddress inIP) {
        miDRACIP = inIP;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the agent name
     *
     * @return the agent name
     */
    public String getAgentName() {
        return mAgentName;
    }

    /**
     * Method for setting the agent name
     *
     * @param inName specifies the agent name
     * @return SUCCESS if the agent name could be set else appropriate error
     * code is returned.
     */
    public int setAgentName(String inName) {
        mAgentName = inName;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the agent version
     *
     * @return the agent version
     */
    public String getAgentVersion() {
        return mAgentVersion;
    }

    /**
     * Method for setting the agent version
     *
     * @param inVersion specifies the version being set
     * @return SUCCESS if the version could be set else appropriate error code
     * is returned
     */
    public int setAgentVersion(String inVersion) {
        mAgentVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting system context
     *
     * @return the system context
     */
    public DCMSystemContext getSystemContext() {
        return mSystemContext;
    }

    /**
     * Method for setting the system context
     *
     * @param inContext specifies the context being set
     * @return SUCCESS if the context could be set else appropriate error code
     * is returned.
     */
    public int setSystemContext(DCMSystemContext inContext) {
        mSystemContext = inContext;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting Unique SystemID
     *
     * @return SUCCESS if the context could be set else appropriate error code
     * is returned.
     */
    public String getUniqueID() {
        String retVal = "";
        if (null != this.getHostIP()) {
            retVal += this.getHostIP();
        }
        if (null != this.getIDRACIP()) {
            retVal += this.getIDRACIP();
        }
        if (null != this.getServiceTag()) {
            retVal += this.getServiceTag();
        }
        return retVal;
    }

    private Date mCollectionTime;
    private String mSystemTypeIdentifier;
    private String mServiceTag;
    private String mHostOSIdentifier;
    private String mHostName;
    private InetAddress mHostIP;
    private InetAddress miDRACIP;
    private String mAgentName;
    private String mAgentVersion;
    private DCMSystemContext mSystemContext;
}
