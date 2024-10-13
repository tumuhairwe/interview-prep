package com.tumuhairwe.prep.stack;

import java.util.Stack;

/**
 * LeetCode 844 (easy) Backspace String Compare
 * Given two strings s and t, return true if they are equal when both are
 * typed into empty text editors. '#' means a backspace character.
 *
 * Note that after backspacing an empty text, the text will continue empty.
 */
public class BackspaceStringCompare {

    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stack_s = new Stack<>();
        Stack<Character> stack_t = new Stack<>();

        for(char ch : s.toCharArray()){
            if(ch == '#'){
                if(!stack_s.isEmpty()){
                    stack_s.pop();
                }
            }
            else{
                stack_s.push(ch);
            }
        }

        for(char ch : t.toCharArray()){
            if(ch == '#'){
                if(!stack_t.isEmpty()){
                    stack_t.pop();
                }
            }
            else{
                stack_t.push(ch);
            }
        }
        return stack_s.equals(stack_t);
    }
}
