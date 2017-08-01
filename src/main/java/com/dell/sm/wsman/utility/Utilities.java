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
package com.dell.sm.wsman.utility;

import java.io.Closeable;
import java.util.logging.Level;

import java.util.logging.Logger;

/**
 * Provide some common functionality for all the projects (Spectre, VCenter,
 * Security). More common code should be moved here.
 *
 * @author Umer_Shabbir
 * @author Jon_Gabriel
 *
 */
public class Utilities {

    private final static Logger log = Logger.getLogger(Utilities.class.getName());

    public static void closeStreamQuietly(Closeable cs) {
        if (cs != null) {
            try {
                cs.close();
            } catch (Exception e) {
                log.log(Level.SEVERE, null, e);
            }
        }
    }
}
