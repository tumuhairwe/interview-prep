package com.tumuhairwe.prep.stack;

import java.util.Stack;

/**
 * LeetCode 921 (medium)
 * A parenthesis is valid IFF
 * - is an empty string
 * - can be written as 'AB' ('A' concatenated with 'B') where A and B are valid strings or
 * - can be written as (A) where A is a valid string
 * Give a parenthesis string s, in 1 move, you can insert a parenthesis at any position of the string
 * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
 *
 * return the minimum number of moves required to make S a valid string
 * ref: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/
 */
public class MinimumAddToMakeParenthesisValid {
    public int minAddToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        for(char ch : s.toCharArray()){
            if(ch == ')' && !st.isEmpty() && st.peek() == '('){
                st.pop();
            }
            else{
                st.push(ch);
            }
        }

        return st.size();
    }
}
