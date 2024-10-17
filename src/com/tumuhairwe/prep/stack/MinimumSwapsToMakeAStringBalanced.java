package com.tumuhairwe.prep.stack;

import java.util.Stack;

/**
 * LeetCode 1963 (medium)
 * Given a 0-indexed string S of length n. The string consists of exactly n/2.
 * The string consists of exactly n/2 opening brackets '[' AND N/2 closing brackets ']'
 * A string is balanced IFF
 * - is empty or
 * - can written as AB where both 'A' & 'B' are balanced or
 * - can be written as [C] where C is a balanced string
 * You may swap the brackets at any 2 indices any number of times
 * Return the minimum number of swaps needs to make the string balanced
 */
public class MinimumSwapsToMakeAStringBalanced {

    /**
     * TC: O(n)
     * SC: O(n)
     */
    public int minSwaps(String s) {
        Stack<Character> st = new Stack<>();
        int unbalancedCount = 0;
        for(char ch : s.toCharArray()){
            if(ch == ']'){
                if(!st.isEmpty()){
                    st.pop();
                }
                else {
                    st.push(ch);
                    unbalancedCount++;
                }
            }
        }
        return (unbalancedCount + 1)/2;
    }
}
