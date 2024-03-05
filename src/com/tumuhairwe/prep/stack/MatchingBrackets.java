package com.tumuhairwe.prep.stack;

import java.util.Stack;

/**
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
 *
 * Summary == Use stack to push() (when adding) ... and pop when you encounter opposite/closing
 */
public class MatchingBrackets {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();
        String input_valid = "()[]{}";
        String input_invalid = "([)]";

        //boolean isValid = isValid(input_valid);

        System.out.println(input_valid + " " + isValid(input_valid));
        System.out.println(input_invalid + " " + isValid(input_invalid));
    }

    static boolean isValid(String input){
        if(input.length() %2 != 0){
            return false;
        }
        Character OPENING_CURLY_BRACE = '{';
        Character CLOSING_CURLY_BRACE = '}';

        Character OPENING_BRACKET = '(';
        Character CLOSING_BRACKET = ')';

        Character OPENING_PARENTHESIS = '[';
        Character CLOSING_PARENTHESIS = ']';
        Stack<Character> stack = new Stack<>();
        for (Character c : input.toCharArray()){
            if(c == OPENING_BRACKET || c == OPENING_PARENTHESIS || c == OPENING_CURLY_BRACE){
                stack.push(c);
            }
            else if(!stack.isEmpty() && c == CLOSING_BRACKET && stack.peek() == OPENING_BRACKET){
                stack.pop();
            }
            else if(!stack.isEmpty() && c == CLOSING_CURLY_BRACE && stack.peek() == OPENING_CURLY_BRACE){
                stack.pop();
            }
            else if(!stack.isEmpty() && c == CLOSING_PARENTHESIS && stack.peek() == OPENING_PARENTHESIS){
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
