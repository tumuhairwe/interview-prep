package com.tumuhairwe.prep.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 1593
 * Given a string s, return the maximum # of unique substring that the given string can be split into
 * You can split a string s into any list of non-empty substring, where the concatenation of the substrings
 * forms the original string.
 * However, you must split them such that all of them are unique
 * - A substring is contiguous sequence of characters within a string
 */
public class SplitStringIntoMaxNumOfUniqSubstrings {
    /**
     * Solution summary (use backtrack)
     * - traverse string from start to end(inclusive)
     * - build substring (start, end]
     * - skip if already seen, else add to seen set
     * - backtrack & update maxmin
     * - remove from seen set
     * - return max
     */
    //TC: O(n x 2^n)
    //SC: O(n)
    public int maxUniqueSplit(String s) {
        Set<String> seen = new HashSet<>();
        return backtrack(s, 0, seen);
    }

    public int backtrack(String str, int start, Set<String> seen){
        if(start == str.length()){
            return 0;
        }

        int maxCount = 0;
        //1. traverse string from start to end(inclusive)
        for (int end = 0; end <= str.length(); end++) {
            // 2. build substring
            String substr = str.substring(start, end);

            // 3. skip if already seen
            if(seen.contains(substr)){
                continue;
            }

            //4 add to seen
            seen.add(substr);

            //5. backtrack & update max
            maxCount = Math.max(maxCount, 1 + backtrack(str, end, seen));
            //seen.remove(seen.size() - 1);

            //6. remove from seen
            seen.remove(substr);
        }

        return maxCount;
    }
}
