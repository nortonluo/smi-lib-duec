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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Rahul_Ranjan2
 */
public class DCMBundleComparisonResult {
     public DCMBundleComparisonResult() {
        mExclusiveSourceComponents = new ArrayList<>();
        mExclusiveDestinationComponents = new ArrayList<>();
        mUpgradedComponents = new HashMap<>();
        mDowngradedComponents = new HashMap<>();
        mEqualComponents = new HashMap<>();
    }
;
    public String getSourceVersion(){
        return mSourceVersion;
    }
    public String getDestinationVersion(){
        return mDestinationVersion;
    }
    public List<String> getExclusiveSourceComponents(){
        return mExclusiveSourceComponents;
    }
    public List<String> getExclusiceDestinationComponents(){
        return mExclusiveDestinationComponents;
    }
    public HashMap<String,String> getUpgradedComponents(){
        return mUpgradedComponents;
    }
    public HashMap<String,String> getDowngradedComponents(){
        return mDowngradedComponents;
    }
    public HashMap<String,String> getEqualComponents(){
        return mEqualComponents;
    }
    public int setSourceVersion(String version){
        mSourceVersion=version;
        return DCMErrorCodes.SUCCESS;
    }
    public int setDestinationVersion(String version){
        mDestinationVersion = version;
        return DCMErrorCodes.SUCCESS;
    }
    public int setExclusiveSourceComponents(List<String> srcComponents){
        mExclusiveSourceComponents = srcComponents;
        return DCMErrorCodes.SUCCESS;
    }
    public int setExclusiveDestinationComponents(List<String> dstComponents){
        mExclusiveDestinationComponents = dstComponents;
        return DCMErrorCodes.SUCCESS;
    }
    public int setUpgradedComponents(HashMap<String,String> upgrdComponents){
        mUpgradedComponents = upgrdComponents;
       return DCMErrorCodes.SUCCESS; 
    }
    public int setDowngradedComponents(HashMap<String,String> dwngrdComponents){
        mDowngradedComponents = dwngrdComponents;
        return DCMErrorCodes.SUCCESS;
    }
    public int setEqualComponents(HashMap<String,String> equalComponents){
        mEqualComponents = equalComponents;
        return DCMErrorCodes.SUCCESS;
    }
    private String mSourceVersion;
    private String mDestinationVersion;
    private  List<String> mExclusiveSourceComponents;
    private  List<String> mExclusiveDestinationComponents;
    private  HashMap<String,String> mUpgradedComponents;
    private  HashMap<String,String> mDowngradedComponents;
    private  HashMap<String,String> mEqualComponents;
}
