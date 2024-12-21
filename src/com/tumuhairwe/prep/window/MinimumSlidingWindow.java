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
 * article: https://medium.com/@nikhil.cse16/mastering-the-sliding-window-technique-a-comprehensive-guide-6bb5e1e86f99
 *
 *  Solution:
 *  - Create a frequency-map that stores all the values of t and the frequency of their
 *  occurrence (look-up = O(1))
 *  - Loop thru the string ... check if CURRENT_CHAR is in the frequency-map
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
        //0. create freqMap of t
        Map<Character, Integer> t_freqMap = new HashMap<>();
        for(int i=0; i<t.length(); i++){
            char ch = t.charAt(i);
            t_freqMap.put(ch, t_freqMap.getOrDefault(ch, 0) + 1);
        }

        //1. declare vars
        int slow = 0;
        int fast = 0;
        int numUniqueChars = t_freqMap.size();
        double windowLength = Double.POSITIVE_INFINITY;
        String minWindow = "";
        int count = 0;

        while (fast < s.length()){
            char fastCh = s.charAt(fast);

            //3 decrement ch from t_freqMap
            if(t_freqMap.containsKey(fastCh)){
                t_freqMap.put(fastCh, t_freqMap.get(fastCh) - 1);
                if(t_freqMap.get(fastCh) == 0 ){
                    numUniqueChars--;
                }
            }

            //4. move fast at regular speed
            fast++;

            while(numUniqueChars == 0){
                if (fast - slow < windowLength){
                    windowLength = fast - slow;
                    minWindow = s.substring(slow, fast);
                }

                //5. put slowCh in window & add to freqMap
                char slowCh = s.charAt(slow);
                if(t_freqMap.containsKey(slowCh)){
                    t_freqMap.put(slowCh, t_freqMap.get(slowCh) + 1);
                    if(t_freqMap.get(slowCh) > 0){
                        numUniqueChars++;
                    }
                }

                //. forward fast ptr
                slow++;
            }
        }

        return minWindow;
    }
}
