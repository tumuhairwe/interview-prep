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
     * Solution summary (simple BFS)
     * - Create & seed que with 0 (will track indices until index = s.length())
     * - Create visitedSet (will track all indices visited to avoid repetition)
     * - create Set<String> representation of wordDictionary (non-dupe
     *
     * - while !que.isEmpty()
     *      - poll currentIndex from queue
     *      - if visited, skip
     *      - iterate string from currentIndex + 1 to s.length()
     *      - if substring is in wordDictSet
     *          - if i == str.length() -> we've reached en-of-string, return
     *          - else ... add currentIndex to queOfIndices
     *      - add currentIndex to visitedIndices
     *   - return false is you exit the while loop and haven't found a word in wordDictSet & que is empty
     * TC: O(n^3 + m * k)
     * - where n == length of the string s
     * and m = size() of wordDict
     * and k is the average length of the word in worddict
     *
     * BFS could cost up to n^3 (since we have to iterate over n nodes in front of the current node) of which there
     * are n. (for each node, we have to create a n^2 substring)
     * We spent O(m * k) to creat the set of words
     */
    public boolean wordBreak(String str, List<String> wordDict){
        Deque<Integer> queOfIndices = new ArrayDeque<>();
        queOfIndices.add(0);

        Set<Integer> visitedIndices = new HashSet<>();
        Set<String> wordDictSet = new HashSet<>(wordDict);

        while (!queOfIndices.isEmpty()){
            Integer currentIndex = queOfIndices.pop();
            if(visitedIndices.contains(currentIndex)){
                continue;
            }

            for (int i = currentIndex + 1; i <= str.length(); i++) {
                String substring = str.substring(currentIndex, i);
                if(wordDictSet.contains(substring)){
                    if (i == str.length()){
                        return true;
                    }
                    else {
                        queOfIndices.push(currentIndex);
                    }
                }
            }

            visitedIndices.add(currentIndex);
        }

        return false;
    }

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
