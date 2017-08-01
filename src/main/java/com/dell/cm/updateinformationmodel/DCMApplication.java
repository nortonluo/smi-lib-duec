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
 * Class for representing Application
 *
 * @author Raveendra_Madala
 */
public class DCMApplication extends DCMBaseTarget {

    /**
     * Constructor
     *
     * @param inID specifies the component ID
     */
    public DCMApplication(long inID) {
        mComponentID = inID;
        mType = DCMTargetEntity.APPLICATION;
    }

    /**
     * Copy constructor
     *
     * @param inApplication specifies the object from which this object is to be
     * constructed
     */
    public DCMApplication(DCMApplication inApplication) {
        super(inApplication);
        mType = DCMTargetEntity.APPLICATION;
        if (inApplication != null) {
            mComponentID = inApplication.mComponentID;
        } else {
            mComponentID = -1;
        }
    }
    
    /**
     *
     * @return newly created object
     */
    @Override
    public DCMBaseTarget Copy(){
        return new DCMApplication(this); 
    }

    /**
     * Method for getting the component id
     *
     * @return the component id
     */
    public long getComponentID() {
        return mComponentID;
    }

    /**
     * Method for getting the unique identifier
     *
     * @return the application's unique identifier
     */
    @Override
    public String getUniqueIdentifier() {
        String retVal = new String();
        retVal += Long.toString(mComponentID);
        return retVal;
    }

    private final long mComponentID;
}
