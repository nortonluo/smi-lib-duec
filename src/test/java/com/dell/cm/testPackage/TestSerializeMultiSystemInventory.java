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
package com.dell.cm.testPackage;

import com.dell.cm.inventory.DCMInventory;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMMultiSystemInventory;
import java.io.File;

/**
 *
 * @author Md_Shahbaz_Alam Purpose : Serialize multiSystemInventory to String
 * Purpose : Serialize multiSystemInventory to xml File Required File attached
 * in the resources package
 */
public class TestSerializeMultiSystemInventory {

    public static void main(String[] args) {
        TestSerializeMultiSystemInventory serialize = new TestSerializeMultiSystemInventory();
        serialize.inventoryFile = args[0];
        serialize.outputFile = args[1];

        File inventoryFile = new File(serialize.inventoryFile);
        File outputFile = new File(serialize.outputFile);
        DCMInventory inventoryObj = new DCMInventory();

        DCMMultiSystemInventory baseMultiSystemInventory = inventoryObj.createEmptyMultiSystemInventory();
        // converting normal inventory to multiSystemInventory schema.
        inventoryObj.addInventory(inventoryFile, "", baseMultiSystemInventory, Boolean.FALSE);
        // Serialize multiSystemInventory object to baseOutput file.
        int result = inventoryObj.serializeToFile(baseMultiSystemInventory, outputFile);
        if (result == DCMErrorCodes.SUCCESS) {
            System.out.println("SUCCESSFUL : serialization of inventory File : " + serialize.inventoryFile + " to output MultiSystemInventory : " + serialize.outputFile);
        } else {
            System.out.println("UNSUCCESSFUL : serialization of inventory File : " + serialize.inventoryFile + " to output MultiSystemInventory : " + serialize.outputFile);
        }
    }
    public String inventoryFile;
    public String outputFile;

}
