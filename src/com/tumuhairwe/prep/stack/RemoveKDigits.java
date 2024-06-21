package com.tumuhairwe.prep.stack;

import java.util.Stack;

/**
 * LeetCode 402 (medium)
 * Remove K Digits
 *
 * Given string num representing a non-negative integer num, and an integer k,
 * return the smallest possible integer after removing k digits from num.
 *
 * ref: https://leetcode.com/problems/remove-k-digits/description/
 * ref: https://www.youtube.com/watch?v=cFabMOnJaq0
 */
public class RemoveKDigits {

    public static void main(String[] args) {
        String s = "1432219";
        System.out.println(removeKDigits(s, 3));
    }
    public static String removeKDigits(String num, int k){
        if(k > num.length()){
            return "0";
        }

        //1. create a monotonic stack (stack with increasing elements)
        Stack<Character> stack = new Stack<>();
        for(char ch : num.toCharArray()){
            // remove from stack all characters that are greater that ch
            while (!stack.isEmpty() && stack.peek() > ch && k >0){
                stack.pop();
                k--;
            }

            // push ch onto stack -> ensures that all characters under ch are less than ch
            stack.push(ch);
        }

        //2. pop of all extra character (in case the stack.size() is larger than k)
        while (!stack.isEmpty() && k > 0){
            k--;
            stack.pop();
        }

        //3. construct the string to return (by insert every character at the END of the string i.e. offset zero)
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.insert(0, stack.pop());  // should lead to "12345" (where as stack had 5,4,3,2,1 on it)
        }

        //4 remove any leading zeros -> return the string (in case stack/string had leading zeros
        for (int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i) != 0){
                return sb.toString().substring(i, sb.length());
            }
        }
        return "0";
    }
}
