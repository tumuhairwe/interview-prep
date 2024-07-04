package com.tumuhairwe.prep.strings;

/**
 * LeetCode 678 (medium) Valid Parenthesis String
 *
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 *
 * The following rules define a valid string:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 *
 * ref: https://www.youtube.com/watch?v=QhPdNS143Qg
 * ref: https://leetcode.com/problems/valid-parenthesis-string/description/?envType=problem-list-v2&envId=plakya4j
 */
public class ValidParenthesis {

    //SC: O(1)
    //TC: O(n)

    /**
     * Solution summary
     * - track the count of POSSIBILITIES
     * - if opening ( -> increment both
     * - if closing ) -> decrement both
     * - if * --> increment max and decrement min
     *
     * - if at any point min is less than zero, reset to 0
     * - if any point max is less than 0, we can't recover i.e. return false
     */
    public boolean checkValidString(String s){
        int leftMin = 0;
        int leftMax = 0;

        for(char ch : s.toCharArray()){
            if(ch == '('){
                leftMax++;
                leftMin++;
            }
            else if(ch == ')'){
                leftMax--;
                leftMin--;
            }
            else{
                leftMax++;
                leftMin--;
            }

            if(leftMax < 0){
                return false;
            }
            if(leftMin < 0){
                leftMin = 0;
            }
        }

        return leftMin == 0;
    }
}
