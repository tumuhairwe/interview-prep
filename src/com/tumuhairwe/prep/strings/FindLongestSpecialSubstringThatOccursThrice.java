package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2982 (medium)
 * Find Longest Special Substring That Occurs Thrice
 *
 * You are given a string s that consists of lowercase English letters.
 * A string is called special if it is made up of only a single character. For example, the string "abc" is not special, whereas the strings "ddd", "zz", and "f" are special.
 * Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special substring occurs at least thrice.
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 * ref: https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-ii/description/
 */
public class FindLongestSpecialSubstringThatOccursThrice {

    public static void main(String[] args) {
        String[] arr  = {"aaaa",
                "abcdef",
                "abcaba"};
        System.out.println(maximumLength("Should be 2: " + arr[0]));
        System.out.println(maximumLength("Should be -1: " + arr[1]));
        System.out.println(maximumLength("Should be 1: " + arr[2]));
    }
    /**
     * Solution summary:
     * - use 3 pointers
     *      - p1: beginning of string
     *      - p2: beginning of string -- will be incremented as long as condition is true (i.e. chars match at p1 and p2_
     *      - p3: end of string
     * - while not end of string (p3) and chars match ... extend p2
     * - get substring from p1 to p2 and process as follows
     *      - if length >= 3
     *             - take entire substring and increment by 1 in the freqMap
     *             - take substringOneChar behind and increment by 2
     *             - take substringTwoChar behind and increment by 3
     *      - if length >= 2
     *             - take entire substring and increment by 1 in the freqMap
     *             - take substringOneChar behind and increment by 2
     * -iterate freqMap and get string with highest frequency -> return its frequency
     */
    public static int maximumLength(String s){
        // base case
        if(s == null || s.length() <= 2){
            return -1;
        }

        // use 3 pointers to calc substring length until chars at p1 and p2 are not equal
        Map<String, Integer> freqMap = new HashMap<>();
        int start = 0;  //p1
        int end = s.length();   //p3

        while (start < end){    // exit when you get to end of string
            int strEnd = start; //p2
            while(strEnd < end && s.charAt(start) == s.charAt(strEnd)){
                strEnd++;
            }

            String s1 = s.substring(start, strEnd);
            if(s1.length() >= 3){   // give priority to strings of length >= 3
                String s2 = s.substring(start, strEnd - 1);
                String s3 = s.substring(start, strEnd - 2);

                freqMap.put(s1, freqMap.getOrDefault(s1, 0) + 1);
                freqMap.put(s2, freqMap.getOrDefault(s1, 0) + 2);
                freqMap.put(s3, freqMap.getOrDefault(s1, 0) + 3);
            }
            else if(s1.length() >= 2){  // next: substring of length >= 2
                String s2 = s.substring(start, strEnd - 1);

                freqMap.put(s1, freqMap.getOrDefault(s1, 0) + 1);
                freqMap.put(s2, freqMap.getOrDefault(s1, 0) + 2);
            }
            else {
                freqMap.put(s1, freqMap.getOrDefault(s1, 0) + 1);
            }
            start = strEnd;
        }

        int lengthOfLongest = -1;
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()){
            String key = entry.getKey();
            int val = entry.getValue();

            if(val >= 3){
                lengthOfLongest = Math.max(lengthOfLongest, key.length());
            }
        }

        return lengthOfLongest;
    }
}
