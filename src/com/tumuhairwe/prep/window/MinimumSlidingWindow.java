package com.tumuhairwe.prep.window;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 76 (Hard)
 * Given 2 strings, s and t ... of lengths m and n (respectively),
 * return the minimum substring of s ... that every character in t (including duplicates)
 * is included in the window
 *
 * Template: https://leetcode.com/problems/find-all-anagrams-in-a-string/solutions/92007/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem/
 *  Solution:
 *  - Create a frequency-map that stores all the values of t and the frequency of their
 *  occurrence (look-up = O(1))
 *  - Looks thru the string ... check if CURRENT_CHAR is in the frequency-map
 *  - if it is, decrement the value associated with that letter
 *  - Once the frequency map is all == 0, then we know we've found the substring
 *  Constraint: O(m + n )
 *   variables needed
 *   - count == size of smaller/substring
 *  A common trade off is to increase space complexity in order to hit the time
 *  complexity (i.e. using a map data structure is quick look up whe .. esp. when comparing 2 values)
 * ref: https://leetcode.com/problems/minimum-window-substring/description/
 */
public class MinimumSlidingWindow {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println("Given s=" + s + " and t=" + t + " output=" + minWindow(s, t));

        s = "a"; t = "a";
        System.out.println("Given s=" + s + " and t=" + t + " output=" + minWindow(s, t));

        s = "a"; t = "aa";
        System.out.println("Given s=" + s + " and t=" + t + " output=" + minWindow(s, t));
    }

    // time complexity = O( t + s)
    public static String minWindow(String s, String t) {
        Map<Character, Integer> freqMap = new HashMap<>();

        // 0. populate frequency map
        for(int i=0; i<t.length(); i++){
            Character c = t.charAt(i);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // 1. declare pointers + count
        int leftPointer = 0, rightPointer = 0;
        int count = freqMap.size();
        int length = Double.valueOf(Double.POSITIVE_INFINITY).intValue();
        String minWindow = "";

        // 2. iterate over the long string
        while(rightPointer < s.length()){
            char letter = s.charAt(rightPointer);
            if(freqMap.containsKey(letter)){
                freqMap.put(letter, freqMap.get(letter) - 1);
                if(freqMap.get(letter) == 0){
                    count--;
                }
            }

            // move right pointer
            rightPointer++;

            while(count == 0){
                if(rightPointer - leftPointer < length){
                    length = rightPointer - leftPointer;
                    minWindow = s.substring(leftPointer, rightPointer);
                }

                char leftLetter = s.charAt(leftPointer);
                if(freqMap.containsKey(leftLetter)){
                    freqMap.put(leftLetter, freqMap.get(leftLetter) + 1);
                    if(freqMap.get(leftLetter) > 0){
                        count++;
                    }
                }

                leftPointer++;
            }
        }

        return minWindow;
    }
}
