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
package com.dell.sm.wsman.updates;

import java.io.Serializable;

/**
 * @author sangita_pandit This class is used to pass properties and values
 * returned from the WSMan command
 *
 */
public class CommandResponse implements Serializable {

    private static final long serialVersionUID = 361632112072750930L;

    private String resourceURI;
    private String jobID;
    private boolean bSuccess;
    private String details;
    private String LCErrorCode;
    private String LCErrorStr;

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public boolean isbSuccess() {
        return bSuccess;
    }

    public void setbSuccess(boolean bSuccess) {
        this.bSuccess = bSuccess;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setLCErrorCode(String lCErrorCode) {
        LCErrorCode = lCErrorCode;
    }

    public String getLCErrorCode() {
        return LCErrorCode;
    }

    public void setLCErrorStr(String lCErrorStr) {
        LCErrorStr = lCErrorStr;
    }

    public String getLCErrorStr() {
        return LCErrorStr;
    }
}
