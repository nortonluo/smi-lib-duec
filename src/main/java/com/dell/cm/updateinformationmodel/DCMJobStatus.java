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
 *
 * @author GARGI_PRIYADARSHINI
 *
 * Enumeration Class for representing the status
 * of a job
 */
public enum DCMJobStatus {

    /**
     * Job has not yet started
     */
    NOTSTARTED,
    /**
     * Job started and is still running
     */
    INPROGRESS,
    /**
     * Job Completed successfully 
     */
    COMPLETED,
    /**
     * Job failed 
     */
    FAILED,
    /**
     * CMC not available
     */
    CMCNotAvailable;

    /**
     * To get the JobStatus enumeration value for a specific string
     * @param inValue the input string whose enumeration is required
     * @return the enumeration value for inValue
     */
    public static DCMJobStatus getEnumeration(String inValue) {
        if (inValue == null) {
            return DCMJobStatus.NOTSTARTED;
        }
        if (inValue.equals(DCMConstants.STATUSNOTSTARTED)) {
            return DCMJobStatus.NOTSTARTED;
        } else if (inValue.equals(DCMConstants.STATUSINPROGRESS)) {
            return DCMJobStatus.INPROGRESS;
        } else if (inValue.equals(DCMConstants.STATUSCOMPLETED)) {
            return DCMJobStatus.COMPLETED;
        } else if (inValue.equals(DCMConstants.STATUSFAILED)) {
            return DCMJobStatus.FAILED;
        } 
        else if(inValue.equals(DCMConstants.CMCNOTAVAILABLE)){
            return DCMJobStatus.CMCNotAvailable;
        }
        return DCMJobStatus.NOTSTARTED;
    }
    
    /**
     * To get the corresponding String of a JobStatus enumeration
     * @param inType the JobStatus Enumeration
     * @return the String corresponding to given Enumeration inType
     */
    public static String toString(DCMJobStatus inType) {
        if (inType == null) {
            return DCMConstants.STATUSNOTSTARTED;
        }
        if (inType.equals(DCMJobStatus.NOTSTARTED)) {
            return DCMConstants.STATUSNOTSTARTED;
        } else if (inType.equals(DCMJobStatus.INPROGRESS)) {
            return DCMConstants.STATUSINPROGRESS;
        } else if (inType.equals(DCMJobStatus.COMPLETED)) {
            return DCMConstants.STATUSCOMPLETED;
        } else if (inType.equals(DCMJobStatus.FAILED)) {
            return DCMConstants.STATUSFAILED;
        } 
        else if(inType.equals(DCMJobStatus.CMCNotAvailable)){
            return DCMConstants.CMCNOTAVAILABLE;
        }
        return DCMConstants.STATUSNOTSTARTED;
    }
    
    
}
