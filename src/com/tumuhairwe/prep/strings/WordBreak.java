package com.tumuhairwe.prep.strings;

import java.util.*;

/**
 * LeetCode 139 (medium)
 *
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * ref: https://leetcode.com/problems/word-break/description/
 */
public class WordBreak {

    /**
     * Solution summary (BFS with memo)
     * - Create a cache map to store already calculated state
     * - Change the wordDict from List to Map (to avoid duplicates)
     * - Call is_dfs_wordMatch() to recursively determine if there's a match
     */
    public boolean wordBreak(String word, List<String> wordDict, Map<String, Boolean> memo) {
        Map<String, Boolean> memoState = new HashMap<>();
        Set<String> wordSet = new HashSet<>(wordDict);

        return is_dfs_wordMatch(word, wordSet, memoState);
    }

    /**
     * Solution summary
     * - check if word exists in memo -> if so, return true
     * - check if word exist in wordSet .. if so, return true
     * - loop thru word's char and progressively build a bigger prefix
     *      - for each prefix/iteration, word exists in set and its recursive match, put in cache and return true
     */
    public boolean is_dfs_wordMatch(String word, Set<String> wordSet, Map<String, Boolean> memo){
        // if prefix is already pre-calculated ... return value
        if (memo.containsKey(word)){
            return memo.get(word);
        }
        // if word is complete word in set ... return true
        else if (wordSet.contains(word)){
            return true;
        }

        for (int i = 0; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            if(wordSet.contains(prefix) && is_dfs_wordMatch(word.substring(i), wordSet, memo)){
                memo.put(word, true);
                return true;
            }
        }

        memo.put(word, false);
        return false;
    }
}
