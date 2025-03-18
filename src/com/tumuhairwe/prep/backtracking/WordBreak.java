package com.tumuhairwe.prep.backtracking;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 139 (medium)
 * Given a string S, and a dictionary wordDict, return true if S can be segmented into a space
 * separated sequence of 1 or more dictionary words.
 * Note that the words in the dictionary can be reused multiple times.
 *
 * ref: https://leetcode.com/problems/word-break/description/
 * ref: https://leetcode.com/problems/concatenated-words/solutions/348972/java-common-template-word-break-i-word-break-ii-concatenated-words/
 * ref: https://medium.com/@_monitsharma/daily-leetcode-problems-problem-139-word-break-dd13297fbe85
 */
public class WordBreak {
    /**
     * Solution summary (top-down DP with 1-D array/memo)
     * - Call a recursive method that takes the string, the dictionary (converted to Set, for faster lookup)
     * and an boolean array/memo/cache of size str.length()
     * - implement recursiveMethod
     *      - // base case, startIndex == string.length(), return true (string ending at that index is cacheable)
     *      - // 2nd case, if result was cached in memo, return results (note that memo[i] can have 3 states where
     *      null == its never been calculated
     *      - iterate string from startIndex + 1 to str.length
     *      - create substring from startIndex to endIndex
     *      - if dictionary.contains(substring) && recursiveCall() -> update memo[i] = true, return true
     *      - update memo[i] = false
     *      - return false;
     *
     * TC: O(n ^ 3) -- where n == length of string
     * SC: O(N) -- n == size of string
     */
    public boolean wordBreak(String s, List<String> wordDict){
        return topDown(s, new HashSet<>(wordDict),  0, new Boolean[s.length()]);
    }

    private boolean topDown(String str, Set<String> wordDict, int startIndex, Boolean[] memo){
        // check if end-of-string -> return true
        if(startIndex == str.length()){
            return true;
        }

        // if result was pre-calculate -> return result
        if(memo[startIndex] != null){
            return memo[startIndex];
        }

        // iterate str, calculate substring, if dictionary.contains(substr) & recursiveCall() -> true
        for (int endIndex = startIndex + 1; endIndex <= str.length(); endIndex++) {
            String sub = str.substring(startIndex, endIndex);
            if(wordDict.contains(sub) && topDown(str, wordDict, endIndex, memo)){
                memo[startIndex] = true;    // cache the result
                return true;
            }
        }

        memo[startIndex] = false;
        return false;
    }
}
