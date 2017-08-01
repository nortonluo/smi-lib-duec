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
 * class for Comparing two versions
 *
 * @author Md Shahbaz Alam
 */
public class DCMVersionComparison {

    /**
     * Constructor for comparing versions
     *
     * @param inSourceVersion specifies the source version being compared
     * @param inDestinationVersion specifies the destination version against
     * which the comparison is to be done
     */
    public DCMVersionComparison(String inSourceVersion, String inDestinationVersion) {
        mSourceVersion = (inSourceVersion==null)?"":inSourceVersion.toUpperCase();
        mDestinationVersion = (inDestinationVersion==null)?"":inDestinationVersion.toUpperCase();
    }

    /**
     * Method for comparing the source version with the destination
     *
     * @return EQUAL if both the versions are same, GREATER if source version is
     * greater than the destination version, LOWER if the source version is
     * lower than the destination version
     */
    public DCMComparisonResultType compare() {
        if (null == mSourceVersion || mSourceVersion.isEmpty()) {
            return DCMComparisonResultType.LOWER;
        }
        if (null == mDestinationVersion || mDestinationVersion.isEmpty()) {
            return DCMComparisonResultType.GREATER;
        }
        tokenize();
        for (int tokenIndex = 0; tokenIndex < mSourceTokens.length; ++tokenIndex) {
            if (!mSourceTokens[tokenIndex].equals(mDestinationTokens[tokenIndex])) {
                if (mSourceTokens[tokenIndex].compareTo(mDestinationTokens[tokenIndex]) > 0) {
                    return DCMComparisonResultType.GREATER;
                } else {
                    return DCMComparisonResultType.LOWER;
                }
            }
        }
        return DCMComparisonResultType.EQUAL;
    }

    private void tokenize() {
        int numberOfSourceTokens = mSourceVersion.split(DCMConstants.VERSION_STRING_DELIMITERS).length;
        int numberOfDestinationTokens = mDestinationVersion.split(DCMConstants.VERSION_STRING_DELIMITERS).length;

        // Make equal number of tokens
        if (numberOfSourceTokens > numberOfDestinationTokens) {
            for (int tokenIndex = 0; tokenIndex < (numberOfSourceTokens - numberOfDestinationTokens); ++tokenIndex) {
                mDestinationVersion += DCMConstants.VERSION_EMPTY_TOKEN;
            }
        } else if (numberOfDestinationTokens > numberOfSourceTokens) {
            for (int tokenIndex = 0; tokenIndex < (numberOfDestinationTokens - numberOfSourceTokens); ++tokenIndex) {
                mSourceVersion += DCMConstants.VERSION_EMPTY_TOKEN;
            }
        }

        mSourceTokens = mSourceVersion.split(DCMConstants.VERSION_STRING_DELIMITERS);
        mDestinationTokens = mDestinationVersion.split(DCMConstants.VERSION_STRING_DELIMITERS);
        // Pad the tokens to make them equal length
        for (int tokenIndex = 0; tokenIndex < mSourceTokens.length; ++tokenIndex) {
            String sourceToken = mSourceTokens[tokenIndex];
            String destinationToken = mDestinationTokens[tokenIndex];
            if (sourceToken.length() > destinationToken.length()) {
                int numberOfCharactersToPad = sourceToken.length() - destinationToken.length();
                for (int characterIndex = 0; characterIndex < numberOfCharactersToPad; ++characterIndex) {
                    destinationToken = DCMConstants.ZERO + destinationToken;
                }
                mDestinationTokens[tokenIndex] = destinationToken;
            } else if (destinationToken.length() > sourceToken.length()) {
                int numberOfCharactersToPad = destinationToken.length() - sourceToken.length();
                for (int characterIndex = 0; characterIndex < numberOfCharactersToPad; ++characterIndex) {
                    sourceToken = DCMConstants.ZERO + destinationToken;
                }
                mSourceTokens[tokenIndex] = sourceToken;
            }
        }
    }

    private String mSourceVersion;
    private String mDestinationVersion;
    private String[] mSourceTokens;
    private String[] mDestinationTokens;
}
