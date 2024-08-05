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
        //0. determine multiplier from 1st char
        int multiplier = x > 0 ? 1 : -1;
        //Character sign = x < 0 ? '-' : '+';
        //int multiplier = sign.equals('-') ? -1 : 1;

        //1. convert Math.abs(number) to String -> store sting in stack
        String num = String.valueOf(Math.abs(x));
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            stack.push(num.charAt(i));
        }

        //2. reconstruct String from stack -> stringBuilder
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        //3. if empty, return 0
        if(sb.toString().equals("")){
            return 0;
        }

        //4. cut off the negative sign if its there
        num = sb.toString().endsWith("-") ? sb.substring(0, sb.toString().length()-1) : sb.toString();

        //5. convert to number
        double l = Double.parseDouble(num) * multiplier;

        //6. check if its out of bounds ... return 0
        if(l > Integer.MAX_VALUE || l < Integer.MIN_VALUE){
            return 0;
        }

        return (int) l;
    }
}
