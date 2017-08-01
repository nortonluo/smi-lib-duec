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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class for representing important information
 *
 * @author Raveendra_Madala
 */
public class DCMImportantInformation {

    /**
     * Constructor
     */
    public DCMImportantInformation() {

    }

    public DCMImportantInformation(DCMImportantInformation inInformation) {
        if (inInformation != null) {
            mInformation = new DCMI18NString(inInformation.mInformation);
            setURL(inInformation.mURL);
        }
    }

    /**
     * Method for getting the important information
     *
     * @return Important information
     */
    public DCMI18NString getInformation() {
        return mInformation;
    }

    /**
     * Method for setting the information
     *
     * @param inInformation specifies the information being set
     * @return SUCCESS if the information could be set else appropriate error
     * code is returned.
     */
    public int setInformation(DCMI18NString inInformation) {
        mInformation = new DCMI18NString(inInformation);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the URL
     *
     * @return the URL
     */
    public URL getURL() {
        return mURL;
    }

    /**
     * Method for setting the URL
     *
     * @param inURL specifies the URL being set
     * @return SUCCESS if the URL could be set else appropriate error code is
     * returned.
     */
    public int setURL(URL inURL) {
        if (inURL != null) {
            try {
                mURL = new URL(inURL.getProtocol(), inURL.getHost(), inURL.getPort(), inURL.getFile());
            } catch (MalformedURLException ex) {
                Logger.getLogger(DCMImportantInformation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            mURL = null;
        }
        return DCMErrorCodes.SUCCESS;
    }

    private DCMI18NString mInformation;
    private URL mURL;
}
