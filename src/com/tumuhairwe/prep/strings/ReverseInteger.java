package com.tumuhairwe.prep.strings;

import java.util.Stack;

/**
 * LeetCode 7 (medium)
 *
 *  Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside
 *  the signed 32-bit integer range [-231, 231 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * ref: https://leetcode.com/problems/reverse-integer/
 */
public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println("should be 321 -> " + reverse(123));
        System.out.println("should be -321 -> " +reverse(-123));
        System.out.println("21 -> " +reverse(120));
        System.out.println("should be -1 -> " +reverse(-10));
        System.out.println("should be 0 -> " + reverse(-1563847412));
        System.out.println("should be 0 -> " + reverse(-2147483648));
    }
    public static int reverse(int x) {
        Character sign = x < 0 ? '-' : '+';

        String num = String.valueOf(Math.abs(x));
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            stack.push(num.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        int multiplier = sign.equals('-') ? -1 : 1;
        if(sb.toString() == ""){
            return 0;
        }

        num = sb.toString().endsWith("-") ? sb.substring(0, sb.toString().length()-1) : sb.toString();
        Double l = Double.parseDouble(num) * multiplier;
        if(l > Integer.MAX_VALUE || l < Integer.MIN_VALUE){
            return 0;
        }

        return l.intValue();
    }
}
