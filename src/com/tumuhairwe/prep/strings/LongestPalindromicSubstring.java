package com.tumuhairwe.prep.strings;

import java.util.TreeSet;
import java.util.stream.IntStream;

/**
 * LeetCode 5 (medium) (409 -- easy asks for LENGTH). This asks for SUBSTRING
 * Longest palindromic substring
 *
 * ref: https://leetcode.com/problems/longest-palindromic-substring/description/
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        int[] arr = IntStream.range(0, 10).toArray();
        IntStream.range(0, 10).forEach(i -> set.add(i));

        System.out.println("First=" + set.first());
        System.out.println("Last=" + set.last());
    }

    /**
     * Solution summary
     * - iterate thru the entire string
     * - for each char, expand from center (treating char as center)
     * - when length exceeds maxLength (update maxLength and the substring itself)
     * // Time Complexity: O(n^2)
     * // Extra Space Complexity: O(1)
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0 ){
            return "";
        }

        int maxLength = 0;
        String sub = "";
        //0. iterate thru string
        for (int i = 0; i < s.length(); i++) {

            //expand from middle : even numbered String
            int left = i;
            int right = i;
            while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if(right - left + 1 > maxLength){
                    maxLength = right - left +1;
                    sub = s.substring(left, right + 1);
                }
                left--;
                right++;
            }

            //expand from middle : odd numbered strings
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if(right - left + 1 > maxLength){
                    maxLength = right - left +1;
                    sub = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
        }

        return sub;
    }
}
