package com.tumuhairwe.prep.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 547 (medium)
 *
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 */
public class PalindromicSubstring {

    /**
     * Solution summary
     * - iterate the entire string ... for each i ... expand from center
     * - Add all palindromes found into a list
     * - return list.size()
     */
    public int countSubstrings(String s) {
        List<String> strings = new ArrayList<>();

        //0. base case
        if(s == null || s.length() == 0){
            return 0;
        }

        //1. iterate thru the string
        for (int i = 0; i < s.length(); i++) {
            // even length
            int left = i;
            int right = i;
            while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                String sub = s.substring(left, right +1);
                strings.add(sub);
                left--;
                right++;
            }

            // odd lengths
            left = i;
            right = i +1;
            while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                String sub = s.substring(left, right +1);
                strings.add(sub);
                left--;
                right++;
            }
        }
        return strings.size();
    }
}
