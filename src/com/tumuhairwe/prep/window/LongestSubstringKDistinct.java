package com.tumuhairwe.prep.window;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 3 (medium) --
 * Longest substring WITHOUT repeating characters
 * ref: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * Solution summary
 * - create frequency map ( Map<Character, Integer> )
 * - increment frequency of Char
 * - while freqMap.size() exceeds K,
 *      - get character at windowStart and decrement it
 *      - slide window forward (windowStart++) if that char's frequency is ZERO
 *  - always keep track of maxLength ( maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
 */
public class LongestSubstringKDistinct {
    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + findLength("araaci", 2));
        System.out.println("Length of the longest substring: " + findLength("araaci", 1));
        System.out.println("Length of the longest substring: " + findLength("cbbebi", 3));
    }
    // time complexity: O(N + N ) => O(N)
    // space complexity: O (K)
    static int findLength(String str, int k){
        // 0. handle base case
        if(str == null || str.length() == 0){
            return -1;
        }

        // 0. create frequency Map
        int maxLength = 0, windowStart = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {    // O (N)
            char rightChar = str.charAt(windowEnd);
            // 2. increment frequency
            freqMap.put(rightChar, freqMap.getOrDefault(rightChar, 0) + 1);

            // 3. slide window forward if frequencyMap.size() exceeds K
            while (freqMap.size() > k){
                char leftChar = str.charAt(windowStart);
                freqMap.put(leftChar, freqMap.get(leftChar)-1);

                if(freqMap.get(leftChar) == 0){
                    // remove
                    freqMap.remove(leftChar);
                }
                windowStart++;
            }
            // 4. remember the max Length so far
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        // 5. return maxLength
        return maxLength;
    }
}
