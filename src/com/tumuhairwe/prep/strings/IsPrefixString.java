package com.tumuhairwe.prep.strings;

/**
 * LeetCode 1961 (easy)
 * 1961. Check If String Is a Prefix of Array
 * ref: https://leetcode.com/problems/check-if-string-is-a-prefix-of-array/
 */
public class IsPrefixString {
    public boolean isPrefixString(String s, String[] words) {
        int k = words.length;
        StringBuilder sb = new StringBuilder();
        for(String w : words){
            sb.append(w);
            if(sb.toString().equals(s)){
                break;
            }
        }
        return sb.toString().equals(s);
    }
}
