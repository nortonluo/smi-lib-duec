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
import java.util.Set;

/**
 * class for representing the payload configuration
 *
 * @author Raveendra_Madala
 */
public class DCMPayloadConfiguration {

    /**
     * Constructor
     */
    public DCMPayloadConfiguration() {
        mImageSet = new HashSet<>();
    }

    /**
     * Copy Constructor
     *
     * @param inConfiguration specifies the object from which this object is to
     * be constructed
     */
    public DCMPayloadConfiguration(DCMPayloadConfiguration inConfiguration) {
        mImageSet = new HashSet<>();
        if (inConfiguration != null) {
            for (DCMPayloadImage image : inConfiguration.mImageSet) {
                DCMPayloadImage createdImage = new DCMPayloadImage(image);
                mImageSet.add(createdImage);
            }
            if (inConfiguration.mUpdateDriver != null) {
                mUpdateDriver = new DCMPayloadUpdateDriver();
            }
        }
    }

    /**
     * Method for adding payload image
     *
     * @param inImage specifies the image to be added
     * @return SUCCESS if the image could be added else appropriate error code
     * is returned
     */
    public int addImage(DCMPayloadImage inImage) {
        if (inImage == null) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (mImageSet.contains(inImage)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        mImageSet.add(inImage);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing the payload image
     *
     * @param inImage specifies the image to be removed
     * @return SUCCESS if teh image could be removed else appropriate error code
     * is returned
     */
    public int removeImage(DCMPayloadImage inImage) {
        if (inImage == null) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        if (!mImageSet.contains(inImage)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        mImageSet.remove(inImage);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting the images
     *
     * @return the images
     */
    public Set<DCMPayloadImage> getImages() {
        return mImageSet;
    }

    /**
     * Method for getting the update driver
     *
     * @return the update driver
     */
    public DCMPayloadUpdateDriver getUpdateDriver() {
        return mUpdateDriver;
    }

    /**
     * Method for setting the update driver
     *
     * @param inDriver specifies driver to be set
     * @return SUCCESS if the driver could be set else appropriate error code is
     * returned.
     */
    public int setUpdateDriver(DCMPayloadUpdateDriver inDriver) {
        mUpdateDriver = new DCMPayloadUpdateDriver(inDriver);
        return DCMErrorCodes.SUCCESS;
    }

    private final HashSet<DCMPayloadImage> mImageSet;
    private DCMPayloadUpdateDriver mUpdateDriver;
}
