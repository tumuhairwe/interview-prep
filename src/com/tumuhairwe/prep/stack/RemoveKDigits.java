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
            while (!stack.isEmpty() && stack.peek() > ch && k >0){
                stack.pop();
                k--;
            }

            stack.push(ch);
        }

        //2.
        while (!stack.isEmpty() && k > 0){
            k--;
            stack.pop();
        }

        //3. construct the string to return
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.insert(0, stack.pop());
        }

        //4 if you find the 1st non-zero number (no leading zeros), return the string
        for (int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i) != 0){
                return sb.toString().substring(i, sb.length());
            }
        }
        return "0";
    }
}
