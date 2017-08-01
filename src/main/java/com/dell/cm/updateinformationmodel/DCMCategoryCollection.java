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

import java.util.Collection;
import java.util.HashMap;

/**
 * class for representing the category collection
 *
 * @author Raveendra_Madala
 */
public class DCMCategoryCollection {

    /**
     * Constructor
     */
    public DCMCategoryCollection() {
        mCategoryMap = new HashMap<>();
    }

    /**
     * Method for getting the categories with the given code
     *
     * @param inCode specifies the code of the category being sought
     * @return the category with the given code
     */
    public DCMCategory getCategory(String inCode) {
        DCMCategory retVal = null;
        if (mCategoryMap.containsKey(inCode)) {
            retVal = mCategoryMap.get(inCode);
        }
        return retVal;
    }

    /**
     * Method for adding a category to this collection
     *
     * @param inCode specifies the code of the category being specified
     * @param inName specifies the name of the category being specified
     * @return SUCCESS if the category could be added else appropriate error
     * code is returned
     */
    public int addCategory(String inCode, DCMI18NString inName) {
        if (mCategoryMap.containsKey(inCode)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        DCMCategory category = new DCMCategory(inCode);
        category.setName(inName);
        mCategoryMap.put(inCode, category);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for adding a category to this collection
     *
     * @param inCategory specifies the category being to be inserted in the
     * Category Collection
     * @return SUCCESS if the category could be added else appropriate error
     * code is returned
     */
    public int addCategory(DCMCategory inCategory) {
        if (null == inCategory) {
            return DCMErrorCodes.INVALID_PARAMETER;
        }
        String categoryCode = inCategory.getCode();
        if (mCategoryMap.containsKey(categoryCode)) {
            return DCMErrorCodes.ALREADY_PRESENT;
        }
        DCMCategory category = new DCMCategory(categoryCode);
        category.setName(inCategory.getName());
        mCategoryMap.put(categoryCode, category);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for removing a category from this collection
     *
     * @param inCode specifies the code of the category being removed
     * @return SUCCESS if the category could be removed else appropriate error
     * code is returned
     */
    public int removeCategory(String inCode) {
        if (!mCategoryMap.containsKey(inCode)) {
            return DCMErrorCodes.DOES_NOT_EXIST;
        }
        DCMCategory removedCategory;
        removedCategory = mCategoryMap.remove(inCode);
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for getting all the categories
     *
     * @return the categories
     */
    public Collection<DCMCategory> getCategories() {
        return mCategoryMap.values();
    }

    public final HashMap<String, DCMCategory> mCategoryMap;
}
