package com.tumuhairwe.prep.strings;

/**
 * LeetCode 482 License Key Formatting (easy)
 *
 * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes.
 * The string is separated into n + 1 groups by n dashes. You are also given an integer k.
 *
 * We want to reformat the string s such that each group contains exactly k characters, except for the first group,
 * which could be shorter than k but still must contain at least one character. Furthermore, there must be a dash
 * inserted between two groups, and you should convert all lowercase letters to uppercase.
 *
 * Return the reformatted license key.
 *
 * ref: https://www.youtube.com/watch?v=3KMlpe8eZ0E
 * ref: https://leetcode.com/problems/license-key-formatting/description/
 */
public class LicenseKeyFormatting {

    /**
     * Solution Summary
     * - Convert string to uppercase & remove all dashes (string will be easier to manipulate if contiguous)
     * - Construct new StringBuilder with new string
     * - Iterate stringBuilder from the end
     * - insert a dash every k characters
     */
    public String licenseKeyFormatting(String s, int k) {
        // 0. base case
        if(s == null || s.length() == 0 ){
            return "";
        }

        // 1. make toUpperCase &&  replace all dashesh so you treat string a contigous
        s = s.toUpperCase().replaceAll("-", "");

        // 2. create string builder and iterate from the back
        StringBuilder sb = new StringBuilder(s);

        // 3. iterate from back and insert K characters at a time into sb
        for(int i=s.length() - k; i>0; i=i-k){
            sb.insert(i, "-");
        }

        return sb.toString();
    }
    public String licenseKeyFormatting_not_space_efficient(String s, int k) {
        // 0. base case
        if(s == null || s.length() == 0 ){
            return "";
        }

        // 1. create string builder and iterate from the back
        StringBuilder sb = new StringBuilder();
        StringBuilder miniSb = new StringBuilder();

        char[] charArray = s.toCharArray();
        for(int i=charArray.length - 1; i>=0; i++){
            if(charArray[i] != '-'){
                if(miniSb.length() <= k ){
                    miniSb.append(charArray[i]);
                }
                else if(miniSb.length() == k){
                    sb.append(miniSb.toString().toLowerCase());
                    sb.append('-');

                    miniSb = new StringBuilder();
                }
            }
        }

        if(sb.toString().startsWith("-")){
            sb = new StringBuilder(sb.substring(1, sb.toString().length()- 1));
        }

        return sb.toString();
    }
}
