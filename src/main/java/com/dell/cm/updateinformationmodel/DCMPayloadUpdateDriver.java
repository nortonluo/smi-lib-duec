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

/**
 * class for representing the update driver element
 *
 * @author Raveendra_Madala
 */
public class DCMPayloadUpdateDriver {

    /**
     * Constructor
     */
    public DCMPayloadUpdateDriver() {
    }

    /**
     * Copy constructor
     *
     * @param inDriver specifies the object from which this object is to be
     * constructed
     */
    public DCMPayloadUpdateDriver(DCMPayloadUpdateDriver inDriver) {
        if (inDriver != null) {
            mName = inDriver.mName;
            mPath = inDriver.mPath;
            mVersion = inDriver.mVersion;
        }
    }

    /**
     * Method for getting the name
     *
     * @return the name
     */
    public String getName() {
        return mName;
    }

    /**
     * Method for setting the name
     *
     * @param inName specifies the name to be set
     * @return SUCCESS if the name could be set else appropriate error code is
     * returned
     */
    public int setName(String inName) {
        mName = inName;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the path
     *
     * @return the path
     */
    public String getPath() {
        return mPath;
    }

    /**
     * Method for setting the path
     *
     * @param inPath specifies the path to be set
     * @return SUCCESS if the path could be set else appropriate error code is
     * returned
     */
    public int setPath(String inPath) {
        mPath = inPath;
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the version
     *
     * @return the version
     */
    public String getVersion() {
        return mVersion;
    }

    /**
     * Method for setting the version
     *
     * @param inVersion specifies the version to be set
     * @return SUCCESS if the version could be set else appropriate error code
     * is returned
     */
    public int setVersion(String inVersion) {
        mVersion = inVersion;
        return DCMErrorCodes.SUCCESS;
    }

    private String mName;
    private String mPath;
    private String mVersion;
}
