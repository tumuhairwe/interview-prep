package com.tumuhairwe.prep.backtracking;

/**
 * LeetCode 7
 * Given a string s, return the longest palindromic substring
 *
 * ref: https://leetcode.com/problems/longest-palindromic-substring/description/
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0005-longest-palindromic-substring.java
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s = "abccccdd";
        String result = longestPalindrome(s);
        System.out.println("The longest palindrom with DP is " + result);
    }

    // regular expand around center
    // TC: O(n^2)
    // SC: O(1) -- because its done in place
    static String longestPalindrome_expandAroundCewnter(String s){
        // 0. account for the base case
        int strLength = s.length();
        if(strLength < 2){
            return s;
        }

        //
        int resultLength = 0;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            int left = i, right = i;

            // odd length
            while (left >=0 &&
                    right < s.length() &&
                    s.charAt(left) == s.charAt(right)){
                if((right - left + 1) == resultLength){
                    result = s.substring(left, right + 1);
                    resultLength = right - left + 1;
                }
                left--;
                right++;
            }

            // even Length
            left = i;
            right = i + 1;
            while (left > 0 &&
                    right < s.length() &&
                    s.charAt(left) == s.charAt(right)){
                if((right - left + 1) == resultLength){
                    result = s.substring(left, right + 1);
                    resultLength = right - left + 1;
                }
                left--;
                right++;
            }
        }

        return result;
    }
    public static int helper(String s, int l, int r) {
        int maxLength = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            if (r - l + 1 > maxLength) {
                maxLength = r - l + 1;
            }
            l--;
            r++;
        }
        return maxLength;
    }

    // optimized expand around center
    // TC: O(n^2)
    // SC: O(1) -- because its done in place
    public static String longestPalindrome(String s){
        int best = 0;
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int left = i - 1;
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)){
                ++i;
            }

            int right = i+ 1;
            while (left >= 0 &&
                    right < s.length() &&
                    s.charAt(left) == s.charAt(right)){
                --left;
                ++right;
            }

            if(right - left > best){
                best = right - left;
                start = left + 1;
                end = right;
            }
        }

        return s.substring(start, end);
    }


    // TC = O(n^2)

    public static String longestPalindrome_dp(String s){
        int length = s.length();
        int left = 0, right = 1, max = 0;

        var isPalindrome = new boolean[length][length];
        for (int i = length - 1; i >= 0; i--) {
            for (int j = 0; j < length; j++) {
                if (i == j){
                    isPalindrome[i][j] = true;
                }
                else if(s.charAt(i) == s.charAt(j)){
                    if(j - i == 1){
                        isPalindrome[i][j] = true;
                    }
                    else {
                        isPalindrome[i][j] = isPalindrome[i + 1][j - 1];
                    }
                }

                if (isPalindrome[i][j] && j - i + 1 > max){
                    max = j - i + 1;
                    left = i;
                    right = j + 1;
                }
            }
        }

        return s.substring(left, right);
    }
}
