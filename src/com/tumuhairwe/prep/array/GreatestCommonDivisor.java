package com.tumuhairwe.prep.array;

/**
 * LeetCode 1071 (easy)
 * For 2 strings s and t, we say "t divides s", IFF t can be concatenated with itself 1 or more times to make s
 *
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 * ref: https://leetcode.com/problems/greatest-common-divisor-of-strings/description/?envType=study-plan-v2&envId=leetcode-75
 * ref: https://www.youtube.com/watch?v=i5I_wrbUdzM
 */
public class GreatestCommonDivisor {

    public String gcdOfStrings(String str1, String str2){
        //0. base case
        if(!(str1 + str2).equals(str2 + str1)){
            return "";
        }

        int l1 = str1.length();
        int l2 = str2.length();
        return str1.substring(0, gcd(l1, l2));
    }

    private int gcd(int len1, int len2){
        if(len2 == 0){
            return len1;
        }

        return gcd(len2, len1 % len2);
    }
}