package com.tumuhairwe.prep.strings;

/**
 * LeetCode 10. Regular Expression Matching (hard)
 *
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * ref: https://leetcode.com/problems/regular-expression-matching/description/
 */
public class RegularExpressionMatching {

    public boolean isMatch(String string, String pattern){
        //0. create cache with dimensions of (1+s.length() ad 1+pattern.length())
        Boolean[][] memo = new Boolean[string.length()][pattern.length()];
        return isMatch(string, pattern, 0, 0, memo);
    }

    private boolean isMatch(String s, String p, int sIndex, int pIndex, Boolean[][] memo){
        //1. case 1: we've reached the end i.e. found the match
        if(sIndex == s.length() && pIndex == p.length()){
            return true;
        }

        //2. pattern index > patter
        if(pIndex >= p.length()){
            return false;
        }

        //3. if its been cached/computed already -> return value
        if(memo[sIndex][pIndex] != null){
            return memo[sIndex][pIndex];
        }

        //4. define vars that are important in decision tree
        boolean charactersMatch = (sIndex < s.length()) && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.');
        boolean nextOneIsStar = (pIndex + 1 < p.length()) && (p.charAt(pIndex + 1) == '*');

        final boolean isMatch;  // final will make compiler enforce the "assigned once only" rule
        if(charactersMatch){
            if(nextOneIsStar){
                isMatch = isMatch(s, p, sIndex, pIndex + 1, memo) || isMatch(s, p, sIndex + 1, pIndex + 1, memo);
            }
            else {
                isMatch = isMatch(s, p, sIndex + 1, pIndex + 1, memo);
            }
        }
        else {
            if(nextOneIsStar){
                isMatch = isMatch(s, p, sIndex, pIndex + 2, memo);
            }
            else {
                isMatch = false;
            }
        }

        //5. cache the result
        memo[sIndex][pIndex] = isMatch;
        return isMatch;
    }
}
