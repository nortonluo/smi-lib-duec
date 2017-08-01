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
package com.dell.sm.downloader;

/**
 * class for representing the proxy information
 *
 * @author Raveendra_Madala
 */
public class DSMProxy {

    /**
     * default constructor place holder
     */
    public DSMProxy() {
    }

    /**
     * default constructor place holder
     *
     * @param protocol specifies the protocol to be set
     * @param portnumber specifies the port number to be set
     * @param url specifies the URL
     * @param user Proxy user name
     * @param pass Proxy password
     */
    public DSMProxy(DSMProtocolEnum protocol, int portnumber, String url, String user, String pass) {
        mProtocol = protocol;
        mPortNumber = portnumber;
        mURL = url;
        mProxyUserName = user;
        mProxyPassword = pass;
    }

    /**
     * Method for getting the protocol enumeration for this proxy
     *
     * @return the protocol enumeration for this proxy
     */
    public DSMProtocolEnum getProtocol() {
        return mProtocol;
    }

    /**
     * Method for setting the protocol enumeration for this proxy
     *
     * @param inProtocol specifies the protocol to be set
     * @return SUCCESS if the protocol could be set else appropriate error code
     * is returned
     */
    public int setProtocol(DSMProtocolEnum inProtocol) {
        mProtocol = inProtocol;
        return DSMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the port number
     *
     * @return the port number
     */
    public int getPortNumber() {
        return mPortNumber;
    }

    /**
     * Method for setting the port number
     *
     * @param inPortNumber specifies the port number to be set
     * @return SUCCESS if the port number could be set else appropriate error
     * code is returned
     */
    public int setPortNumber(int inPortNumber) {
        mPortNumber = inPortNumber;
        return DSMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the URL
     *
     * @return the URL
     */
    public String getURL() {
        return mURL;
    }

    /**
     * Method for setting the URL
     *
     * @param inURL specifies the URL
     * @return SUCCESS if the URL could be set else appropriate error code is
     * returned
     */
    public int setURL(String inURL) {
        mURL = inURL;
        return DSMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting proxy user name
     *
     * @return proxy user name
     */
    public String getProxyUserName() {
        return mProxyUserName;
    }

    /**
     * Method for setting the proxy user name
     *
     * @param inProxyUserName specifies the username for proxy
     */
    public void setProxyUserName(String inProxyUserName) {
        this.mProxyUserName = inProxyUserName;
    }

    /**
     * Method for getting the proxy password
     *
     * @return proxy password
     */
    public String getProxyPassword() {
        return mProxyPassword;
    }

    /**
     * Method for setting the proxy password
     *
     * @param inProxyPassword specifies the password for proxy
     */
    public void setProxyPassword(String inProxyPassword) {
        this.mProxyPassword = inProxyPassword;
    }

    @Override
    public String toString() {
        return "DSMProxy [mURL=" + mURL + "]";
    }

    private DSMProtocolEnum mProtocol;
    private int mPortNumber;
    private String mURL;
    private String mProxyUserName;
    private String mProxyPassword;

}

