package com.tumuhairwe.prep.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode 20 (easy)
 * Given a string containing just the characters '(', ')', '{' '}', '[', ']'
 *
 * determine if the input string is valid
 *
 * valid if
 * a) open brackets must be closed by the same type of brackets
 * b) Open brackets must be closed in the same order
 *
 * e.g. "()" == true
 * e.g. "{}" == true
 * e.g. "[]" == true
 * e.g. "()[]{}" == true
 * e.g. "(]" == false
 * e.g. "([)] ==false
 *
 * e.g. "{[]}" == true (because nested)
 *
 * ref: https://leetcode.com/problems/valid-parentheses/description/
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0020-valid-parentheses.java
 * Summary == Use stack to push() (when adding) ... and pop when you encounter opposite/closing
 */
public class MatchingBrackets {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();
        String input_valid = "()[]{}";
        String input_invalid = "([)]";
        String inv2 = "){";

        //boolean isValid = isValid(input_valid);

        System.out.println(input_valid + " " + isValid(input_valid));
        System.out.println(input_invalid + " " + isValid(input_invalid));
        System.out.println("Should be false: ([}}]) ->" + isValid("([}}])"));
        System.out.println("Should be false: ([}}]) ->" + isValid(inv2));
    }

    static boolean isValid(String input){
        if(input.length() %2 != 0){
            return false;
        }

        // 0. create global mapping of open-close
        Map<Character,Character> closeToOpen = new HashMap<>();
        closeToOpen.put(')', '(');
        closeToOpen.put(']', '[');
        closeToOpen.put('}', '{');

        Stack<Character> stack = new Stack<>();
        for (Character c : input.toCharArray()){

            // 1. for each char, check if its contained in mapping && on top of non-empty stack
            if(closeToOpen.containsKey(c) && !stack.isEmpty() && stack.peek() == closeToOpen.get(c)){
                stack.pop();
            }
            else {
                // 2. else add to stack
                stack.push(c);
            }
        }

        // 4. return is stack is empty
        return stack.isEmpty();
    }
}
