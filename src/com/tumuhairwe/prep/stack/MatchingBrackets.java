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

        System.out.println(input_valid + " " + isValid(input_valid));
        System.out.println(input_invalid + " " + isValid(input_invalid));
        System.out.println("Should be false: ([}}]) ->" + isValid("([}}])"));
        System.out.println("Should be false: ([}}]) ->" + isValid(inv2));
    }

    /**
     * Solution summary
     * - Use a stack to
     */
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
        for (Character closing : input.toCharArray()){

            // 1. for each char, check if its (contained in mapping) && (on top of non-empty stack)
            if(closeToOpen.containsKey(closing) && !stack.isEmpty() && stack.peek() == closeToOpen.get(closing)){
                stack.pop();
            }
            else {
                // 2. else add to stack
                stack.push(closing);
            }
        }

        // 4. return is stack is empty
        return stack.isEmpty();
    }

    /**
     * Solution summary
     * - Use 1 var to track opening brackets (decrement when closing bracket || increment when opening)
     * - at any time in the loop the counter, if counter < 0, return false (i.e. too many closing ... not enough opening)
     * - return true if the count is exactly zero (i.e. no dangling opening bracket left)
     *
     * TC: O(n) -- where n == length of string
     * SC: O(1) -- for counter variable
     *
     * NOTE: This only keeps track of the count ... test cases that track the ORDER of the parenthesis fail
     */
    public boolean isValid_spaceEfficient(String s) {
        if(s == null || s.length() == 0){
            return false;
        }

        char OPENING_BRACKET = '(';
        char CLOSING_BRACKET = ')';

        char OPENING_SQUARE_BRACKET = '[';
        char CLOSING_SQUARE_BRACKET = ']';

        char OPENING_CURLY_BRACKET = '{';
        char CLOSING_CURLY_BRACKET = '}';

        int leftBracketCounter = 0;
        int leftSquareCounter = 0;
        int leftCurlyCounter = 0;

        for(char c : s.toCharArray()){
            if(c == OPENING_BRACKET){
                leftBracketCounter++;
            }
            else if(c == CLOSING_BRACKET){
                leftBracketCounter--;
            }

            else if(c == OPENING_SQUARE_BRACKET){
                leftSquareCounter++;
            }
            else if(c == CLOSING_SQUARE_BRACKET){
                leftSquareCounter--;
            }

            else if(c == OPENING_CURLY_BRACKET){
                leftCurlyCounter++;
            }
            else if(c == CLOSING_CURLY_BRACKET){
                leftCurlyCounter--;
            }

            if(leftBracketCounter < 0 || leftCurlyCounter < 0 || leftSquareCounter < 0){
                return false;
            }
        }

        return leftBracketCounter == 0 && leftCurlyCounter == 0 && leftSquareCounter == 0;
    }
}
