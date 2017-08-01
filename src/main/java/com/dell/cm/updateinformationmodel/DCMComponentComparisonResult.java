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
 * @author Rahul_Ranjan2
 */
public class DCMComponentComparisonResult {
    private String mSourceVersion;
    private String mDestinationVersion;
    private DCMUpdatePackageInformation mSourcePackage;
    private DCMUpdatePackageInformation mDestinationPackage;
    
    
    public DCMComponentComparisonResult(){
        mSourceVersion = new String();
        mDestinationVersion = new String();
    }
    public int setSourcePackage(DCMUpdatePackageInformation pkgInfo){
        mSourcePackage = pkgInfo;
        return DCMErrorCodes.SUCCESS;
    }
    public int setDestinationPackage(DCMUpdatePackageInformation pkgInfo){
        mDestinationPackage=pkgInfo;
        return DCMErrorCodes.SUCCESS;
    }
    public int setSourceVersion(String version){
        mSourceVersion = version;
        return DCMErrorCodes.SUCCESS;
    }
    public int setDestinationVersion(String version){
        mDestinationVersion = version;
        return DCMErrorCodes.SUCCESS;
    }
    public DCMUpdatePackageInformation getSourcePackage(){
        return mSourcePackage;
    }
    public DCMUpdatePackageInformation getDestinationPackage(){
        return mDestinationPackage;
    }
    public String getSourceVersion(){
        return mSourceVersion;
    }
    public String getDestinationVersion(){
        return mDestinationVersion;
    }
}
