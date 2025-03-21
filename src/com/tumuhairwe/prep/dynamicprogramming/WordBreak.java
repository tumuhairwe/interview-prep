package com.tumuhairwe.prep.dynamicprogramming;

import java.util.*;

/**
 * LeetCode 139. Word Break (medium)
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * ref: https://www.youtube.com/watch?v=Sx9NNgInc3A (dp)
 * ref: https://leetcode.com/problems/word-break/
 */
public class WordBreak {

    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = List.of("cats","dog","sand","and","cat");
        System.out.println("Should be false: " + wordBreak_dp(s, wordDict));

        s = "applepenapple";
        wordDict = List.of("apple","pen");
        System.out.println("Should be true: " + wordBreak_dfs(s, wordDict));
    }
    public static boolean wordBreak_dp(String s, List<String> wordDict){
        //0. declare db array
        boolean[] dp = new boolean[s.length() + 1];

        //1. iterate String from the end ... populate dp[n] if
        //      - word.length <= s.length()
        //      - s.startsWith(word)
        //   update DP array
        for (int i = s.length()-1; i >=0; i--) {
            for (String word : wordDict){
                if(i + word.length() <= s.length()
                    && s.startsWith(word, i)){
                    dp[i] = dp[i + word.length()];      // cache the result to
                }

                // if we've found one, get out of this loop & go to the next letter
                if(dp[i]){
                    break;
                }
            }
        }

        return dp[0];
    }

    public static boolean wordBreak_dfs(String s, List<String> wordDict){
        Map<String, Boolean> memoState = new HashMap<>();
        Set<String> wordSet = new HashSet<>(wordDict);  // de-dupe

        return is_recursive_wordBreak(s, wordSet, memoState);
    }

    private static boolean is_recursive_wordBreak(String word, Set<String> wordSet, Map<String, Boolean> cache) {
        //0. base case: if work was already pre-calculated ... return result
        if(cache.containsKey(word)){
            return cache.get(word);
        }
        //1. if word exists in dictionary ... return true
        else if(wordSet.contains(word)){
            return true;
        }
        //2. iterate string fromm the end, if substring/prefix is contained in wordDict ... recursively check if word.substring(i) is contained
        // should return true when entire word is traversed OR words are found in memo already pre-calculated OR contained in word
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            if(wordSet.contains(prefix) && is_recursive_wordBreak(word.substring(i), wordSet, cache)){
                cache.put(word, true);
                return true;
            }
        }

        cache.put(word, false);  // cache the result to avoid future re-calculation
        return false;
    }

    public boolean wordBreak(String str, List<String> wordDict){
        //0. declare vars
        Deque<Integer> queOfIndices = new ArrayDeque<>();

        //1. seed dq
        queOfIndices.add(0);

        //2. track visited indices
        Set<Integer> visited = new HashSet<>();

        while (!queOfIndices.isEmpty()){
            int currIdx = queOfIndices.pop();

            // 3check if visited
            if(visited.contains(currIdx)){
                continue;
            }

            //4. iterate from currIdx + 1 till s.length()
            for(int i=currIdx + 1; i<=str.length(); i++){
                String fragment = str.substring(currIdx , i);
                if(wordDict.contains(fragment)){
                    // check if we've reached end of word
                    if(i == str.length()){
                        return true;
                    }
                    else {
                        // add i to queOfIndices
                        queOfIndices.add(i);
                    }
                }
            }

            visited.add(currIdx);
        }

        return false;
    }
}
