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
 * Error Codes
 *
 * @author Raveendra_Madala
 */
public class DCMErrorCodes {

    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;
    public static final int NULL_PARAMETER = 2;
    public static final int INVALID_PARAMETER = 3;
    public static final int ALREADY_PRESENT = 4;
    public static final int DOES_NOT_EXIST = 5;
    public static final int EMPTY = 6;
    public static final int FULL = 7;
    public static final int INDEX_OUT_OF_BOUNDS = 8;
    public static final int NOT_SUPPORTED = 9;
    public static final int AUTH_FAILURE = 10;
    public static final int PARTIAL_DOWNLOAD = 11;

    public static final int SIGN_HASH_MISSING = 12;
    public static final int SIGN_HASH_NOT_EQUAL = 13;
    public static final int SIGN_CHECK_FAILED = 13;
    public static final int SIGN_PACKAGE_INVALID = 14;

    public static final int SMB_VERSION_NOT_SUPPORTED = 15;
    public static final int PARTIAL_UPLOAD = 16;

}
