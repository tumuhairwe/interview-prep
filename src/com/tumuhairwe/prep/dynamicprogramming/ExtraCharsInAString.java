package com.tumuhairwe.prep.dynamicprogramming;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * LeetCode 2707 (medium)
 * Givena 0-index string s and dictionary of words. Breaks into 1 or more non-overlapping substring
 * such that each substr is present in the dictionary.
 * There may be some chars in s that NOT present in the substring
 * Return the minimum number of characters left ov3er if you break up s optimally.
 *
 * ref: https://leetcode.com/problems/extra-characters-in-a-string/solutions/5822289/how-to-solve-extra-characters-in-a-string-with-step-by-step-solutions-in-multiple-languages/
 */
public class ExtraCharsInAString {

    /**
     * Solution summary (top down dp)
     *
     * - Declare dp[] (size = str.length() + 1) & fill with str.length()
     * - Seed dp[0] = 0
     * - convert dict (string[]) to set for east .contains() look up
     * - traverse str (quadratic time), for each index (string.substring(from i to j) and check if its in dictionary
     * - if in dictionary, set dp[i] = min(dp[i], dp[j),
     * - return the last index (dp[str.length]
     *
     * TC: O(n ^ 2) where is the length of the string. Since we check all possible substring (for each index i)
     *      this leads to quadratic time complexity
     * SC: O(n + d) where n == length of string and c == dictionary.size() (dp[] & dictionary)
     */
    public int minExtraChar(String s, String[] dictionary) {
        //0. declare dp[] to track return value (min extra chars)
        int[] dp = new int[s.length() + 1];

        //1. convert dictionary into set
        Set<String> dict = Arrays.stream(dictionary).collect(Collectors.toSet());
        // same as below
//        for (String d : dictionary){
//            dict.add(d);
//        }

        //2. seed dep[] with max extra chars (s.length())
        // same as below
//        for (int i = 0; i <= s.length(); i++) {
//            dp[i] = s.length();
//        }
        Arrays.fill(dp, s.length());
        dp[0] = 0;

        //3. iterate thru each index in string
        for (int i = 1; i <=s.length(); i++) {
            //4. check all substring end at 1
            for (int j = 0; j < i; j++) {
                String fragment = s.substring(j, i);    // get str[from: j, upto: i]
                if(dict.contains(fragment)){
                    dp[i] = Math.min(dp[i], dp[j]); // if substring is found in dict, update dp[]
                }
            }
        }

        return dp[s.length()];
    }
}
