package com.tumuhairwe.prep.dynamicprogramming;

/**
 * A message containing letters from A-Z can be decoded into numbers using the following mapping
 * 'A' - > '1'
 * 'B' - > '2'
 * ...
 * 'B' - > '26'
 * To decode an encoded message all the digits must be grouped then mapped back into letters
 * using the reverse of the mapping above (there may be multiple ways)
 * e.g. '11106' could be:
 * - AAJF -> (1 1 10 6)
 * - KJF -> (11 10 6)
 * Note that the grouping (1 11 06) is invalid because '06' cannot be mapped into 'F'
 * since its '6' is diffent from '06'
 *
 * Given a string containing only digits, return the number of ways to decode it
 * The test cases are generated so that the answer fits in a 32 bit integer
 * ref: https://leetcode.com/problems/decode-ways/description/
 */
public class DecodeWays {

    public static void main(String[] args) {
        String input = "12";
        System.out.println("Number of ways " + input + " can be decoded = " + numDecodings(input));

        input = "226";
        System.out.println("Number of ways " + input + " can be decoded = " + numDecodings(input));

        input = "06";
        System.out.println("Number of ways " + input + " can be decoded = " + numDecodings(input));
    }
    public static int numDecodings(String s) {
        // intialize dp array (stores max number of ways to decode a string of length -- up to that index)
        int[] dp = new int[s.length() + 1];

        // 1. take care of base cases
        // dp[X] caches the answer to "number of way to decode a string of length X"

        dp[0] = 1;  // bcoz there's only 1 way to decode a string of legnth 0
        dp[1] = s.charAt(0) == '0' ? 0 : 1;   // will epend on whether or not the char is a 0

        for(int i = 2; i <= s.length(); i++){
            int oneDigit = Integer.valueOf(s.substring(i - 1, i));   // just current digit
            int twoDigits = Integer.valueOf(s.substring(i - 2, i));   // just current digit and previous digit

            if(oneDigit >= 1){
                dp[i] += dp[i -1 ];
            }
            if(twoDigits >= 10 && twoDigits <= 26){
                dp[i] += dp[i -2 ];
            }
        }

        return dp[s.length()];
    }
}
