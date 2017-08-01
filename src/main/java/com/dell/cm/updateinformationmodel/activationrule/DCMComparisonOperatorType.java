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
package com.dell.cm.updateinformationmodel.activationrule;

/**
 * <p>
 * Java class for ComparisonOperatorType.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 */
public enum DCMComparisonOperatorType {

    /**
     * Enumeration for equal to operation
     *
     */
    EQUAL("Equal"),
    /**
     * Enumeration for not equal to operation
     *
     */
    NOT_EQUAL_TO("NotEqualTo"),
    /**
     * Enumeration for Greater Than operation
     *
     */
    GREATER_THAN("GreaterThan"),
    /**
     * Enumeration for Less Than operation
     *
     */
    LESS_THAN("LessThan"),
    /**
     * Enumeration for Greater Than or Equal To operation
     *
     */
    GREATER_THAN_OR_EQUAL_TO("GreaterThanOrEqualTo"),
    /**
     * Enumeration for Less Than or Equal To operation
     *
     */
    LESS_THAN_OR_EQUAL_TO("LessThanOrEqualTo");
    private final String value;

    DCMComparisonOperatorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DCMComparisonOperatorType fromValue(String v) {
        for (DCMComparisonOperatorType c : DCMComparisonOperatorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public boolean compare(DCMComparisonOperatorType inValue) {
        if (inValue.equals(EQUAL) && (this.equals(EQUAL) || this.equals(GREATER_THAN_OR_EQUAL_TO) || this.equals(LESS_THAN_OR_EQUAL_TO))) {
            return true;
        }
        if (inValue.equals(LESS_THAN) && (this.equals(LESS_THAN) || this.equals(LESS_THAN_OR_EQUAL_TO))) {
            return true;
        }
        if (inValue.equals(LESS_THAN_OR_EQUAL_TO) && (this.equals(LESS_THAN) || this.equals(EQUAL) || this.equals(LESS_THAN_OR_EQUAL_TO))) {
            return true;
        }
        if (inValue.equals(GREATER_THAN) && (this.equals(GREATER_THAN) || this.equals(GREATER_THAN_OR_EQUAL_TO))) {
            return true;
        }
        if (inValue.equals(GREATER_THAN_OR_EQUAL_TO) && (this.equals(GREATER_THAN) || this.equals(EQUAL) || this.equals(GREATER_THAN_OR_EQUAL_TO))) {
            return true;
        }
        if (inValue.equals(NOT_EQUAL_TO) && (this.equals(GREATER_THAN) || this.equals(NOT_EQUAL_TO) || this.equals(LESS_THAN))) {
            return true;
        }
        return false;
    }

}
