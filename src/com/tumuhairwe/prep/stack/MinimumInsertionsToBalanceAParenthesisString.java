package com.tumuhairwe.prep.stack;

/**
 * LeetCode 1541 (medium)
 *
 * Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:
 *
 * Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
 * Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
 * In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.
 *
 * For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
 * You can insert the characters '(' and ')' at any position of the string to balance it if needed.
 *
 * Return the minimum number of insertions needed to make s balanced.
 */
public class MinimumInsertionsToBalanceAParenthesisString {


    public int minInsertions(String s) {
        int right = 0;
        int result = 0;

        for(char ch : s.toCharArray()){
            if(ch == '('){
                if(right % 2 == 0){
                    right--;
                    result++;
                }
                right += 2;
            }
            else {
                right--;
                if(right < 0){
                    right += 2;
                    result++;
                }
            }
        }

        return right + result;
    }
}
