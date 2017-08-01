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
 * class for representing the authentication parameters
 *
 * @author Raveendra_Madala
 */
public class DSMAuthenticationParameters {

    /**
     * Method for getting the user name
     *
     * @return the user name
     */
    public String getUserName() {
        return mUserName;
    }

    /**
     * Method for setting the user name
     *
     * @param inUserName specifies the user name to be set
     * @return SUCCESS if the user name could be set else appropriate error code
     * is returned.
     */
    public int setUserName(String inUserName) {
        mUserName = inUserName;
        return DSMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the password
     *
     * @return the password
     */
    public String getPassword() {
        return mPassword;
    }

    /**
     * Method for setting the password
     *
     * @param inPassword specifies the password to be set
     * @return SUCCESS if the password could be set else appropriate error code
     * is returned.
     */
    public int setPassword(String inPassword) {
        mPassword = inPassword;
        return DSMErrorCodes.SUCCESS;
    }

    public String getDomainName() {
        return mDomainName;
    }

    public int setDomainName(String inDomainName) {
        this.mDomainName = inDomainName;
        return DSMErrorCodes.SUCCESS;
    }

    private String mUserName;
    private String mPassword;
    private String mDomainName;
}

