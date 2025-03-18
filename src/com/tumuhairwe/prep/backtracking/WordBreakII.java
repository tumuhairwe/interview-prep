package com.tumuhairwe.prep.backtracking;

import java.util.*;

/**
 * Word Break II
 *
 * ref: https://leetcode.com/problems/word-break-ii/description/
 * ref: https://www.youtube.com/watch?v=QgLKdluDo08
 */
public class WordBreakII {
    public static void main(String[] args) {

    }

    /**
     * Solution summary (DFS on que of indices)
     * - create dq and seed with 0. init visited set
     * - while !dq.isEmpty()
     *      - if visited, skip
     *      - for each substring of s (from currentIdx up to s.length() inclusive)
     *      - if substring/fragment is in dictionary (either add idx to q or if-we've reached-end-of-string, return true
     * - return true if we exit look (i.e. didn't reach end of string & Q got empty)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        //1. declare vars
        Queue<Integer> queIndices = new ArrayDeque<>();
        queIndices.add(0);

        //2. track visited
        Set<Integer> visited = new HashSet<>();

        //do DFS of que
        while (!queIndices.isEmpty()){
            int currIdx = queIndices.poll();

            if(visited.contains(currIdx)){
                continue;
            }

            // for each substring, if its in dictionary, add idx to que. if we've reach end of str. return true;
            for (int i = currIdx + 1; i<=s.length(); i++) {
                String fragment = s.substring(currIdx, i);
                if(wordDict.contains(fragment)){
                    // check if we've reached of str .. if so, return true;
                    if(i == s.length()){
                        return true;
                    }
                    else {
                        queIndices.add(i);
                    }
                }

                // add currId to visited set
                visited.add(currIdx);
            }
        }

        // que became empty & we didn't reach end of word
        return false;
    }
}
