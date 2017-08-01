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
public class DCMCatalogComparisonResult {
    /**
     *  Constructor
     */
    public DCMCatalogComparisonResult() {
        mExclusiveSourceBundleIdentifiers = new ArrayList<>();
        mExclusiveDestinationBundleIdentifiers = new ArrayList<>();
        mEqualBundleIdentifiers = new ArrayList<>();
        mBundleDifferences = new HashMap<>();
        mExclusiveSourceComponentIdentifiers = new ArrayList<>();
        mExclusiveDestinationComponentIdentifiers = new ArrayList<>();
        mEqualComponentIdentifiers = new ArrayList<>();
        mComponentDifferences = new HashMap<> ();
    }
    /**
     * Method to return list of exclusive source identifiers
     * @return 
     */
    public List<String> getExclusiveSourceIdentifiers(){
        return mExclusiveSourceBundleIdentifiers;
    }
    /**
     * Method to return list of exclusive destination identifiers
     * @return 
     */
    public List<String> getExclusiveDestinationBundleIdentifiers(){
        return mExclusiveDestinationBundleIdentifiers;
    }
    /**
     * Method to return list of equal bundle identifiers
     * @return 
     */
    public List<String> getEqualBundleIdentifiers(){
        return mEqualBundleIdentifiers;
    }
    /**
     * Method to return list of exclusive source component identifiers
     * @return 
     */
    public List<String> getExclusiveSourceComponentIdentifiers(){
        return mExclusiveSourceComponentIdentifiers;
    }
    /**
     * Method to return list of exclusive destination component identifiers
     * @return 
     */
    public List<String> getExclusiveDestinationComponentIdentifiers(){
        return mExclusiveDestinationComponentIdentifiers;
    }
    /**
     * Method to return list of equal component identifiers
     * @return 
     */
    public List<String> getEqualComponentIdentifiers(){
        return mEqualComponentIdentifiers;
    }
    /**
     * Method to return bundle differences
     * @return 
     */
    public HashMap<String,DCMBundleComparisonResult> getBundleDifferences(){
        return mBundleDifferences;
    }
    /**
     * Method to return component differences
     * @return 
     */
    public HashMap<String,DCMComponentComparisonResult> getComponentDifference(){
        return mComponentDifferences;
    }/**
     * Method to set bundle differences
     * @param retVal
     * @return 
     */
    public int setBundleDifferences(HashMap<String,DCMBundleComparisonResult> retVal){
        mBundleDifferences = retVal;
        return DCMErrorCodes.SUCCESS;
    }
    /**
     * Method to set exclusive source bundles
     * @param bundles
     * @return 
     */
    public int setExclusiveSourceBundleIdentifiers(List<String> bundles){
        mExclusiveSourceBundleIdentifiers = bundles;
        return DCMErrorCodes.SUCCESS;
    }
    /**
     * Method to set exclusive latest bundles
     * @param bundles
     * @return 
     */
    public int setExclusiveDestinationBundleIdentifiers(List<String> bundles){
        mExclusiveDestinationBundleIdentifiers = bundles;
        return DCMErrorCodes.SUCCESS;
    }
    public int setEqualBundleIdentifiers(List<String> bundles){
        mEqualBundleIdentifiers = bundles;
        return DCMErrorCodes.SUCCESS;
    }
    public int setExclusiveSourceComponentIdentifiers(List<String> components){
        mExclusiveSourceComponentIdentifiers = components;
        return DCMErrorCodes.SUCCESS;
    }
    public int setExclusiveDestinationCcomponentIdentifiers(List<String> components){
        mExclusiveDestinationComponentIdentifiers = components;
        return DCMErrorCodes.SUCCESS;
    }
    public int setEqualComponentIdentifiers(List<String> components){
        mEqualComponentIdentifiers = components;
        return DCMErrorCodes.SUCCESS;
    }
    public int setComponentDifferences(HashMap<String, DCMComponentComparisonResult> diff){
        mComponentDifferences = diff;
        return DCMErrorCodes.SUCCESS;
    }
    /** The identifiers of bundles that are exclusively available only in source catalog */
    private List<String> mExclusiveSourceBundleIdentifiers;
    /** The identifiers of bundles that are exclusively available only in destination catalog */
    private List<String> mExclusiveDestinationBundleIdentifiers;
    /* The identifiers of bundles that are equal in both source and destination catalogs */
    private List<String> mEqualBundleIdentifiers;
    /** The information about the bundles that are different
     *  The key is the bundle identifier in the source catalog
     *  The value is the differences
     */
    private HashMap<String, DCMBundleComparisonResult> mBundleDifferences;
    
    /** The identifiers of components that are exclusively available only in source catalog */
    private List<String> mExclusiveSourceComponentIdentifiers;
    /** The identifiers of components that are exclusively available only in destination catalog */
    private List<String> mExclusiveDestinationComponentIdentifiers;
    /** The identifiers of components that are equal in both source and destination components */
    private List<String> mEqualComponentIdentifiers;
    /**
     *  The information about the components that are different
     *  The key is the component identifier in the source catalog
     *  The value is the difference
     */
    private HashMap<String, DCMComponentComparisonResult> mComponentDifferences;
}
