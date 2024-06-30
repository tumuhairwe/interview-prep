package com.tumuhairwe.prep.strings;

/**
 * LeetCode 8 (medium)
 *
 * ref: https://leetcode.com/problems/string-to-integer-atoi/
 * ref: https://www.youtube.com/watch?v=YA0LYrKI1CQ&t=38s
 */
public class Atoi {
    public static void main(String[] args) {
        System.out.println(myAtoi("   -042"));
    }
    /**
     Solution summary
     - 1. strip leading spaces
     - 2. check for + or / sign
     - 3. parse digits
     - 4. check num is between allowable integer range (2^31) - 1

     TC: O(n) becoz we need to parse the entire string
     SC: O(1)
     */
    public static int myAtoi(String s) {
        //0. base case: empty string
        if(s.trim().length() == 0){
            return 0;
        }

        s = s.trim();

        //0. base case: just + or -
        boolean hasSign = s.charAt(0) == '-' || s.charAt(0) == '+';
        if(s.length() == 1 && hasSign){
            return 0;
        }

        //1 has sign
        int idx = 0;
        int sign = s.charAt(idx) == '-' ? -1 : 1;
        if(hasSign){
            idx++;
        }

        int parsed = 0;
        //2.conversion
        while (idx < s.length()) {
            char ch = s.charAt(idx);

            if(!Character.isDigit(ch)){
                break;  // "read integer until non-digit char is encountered"
            }

            int digit = Integer.parseInt(ch + "");
            parsed = (parsed * 10) + digit;
            idx++;
        }


        int abs = parsed * sign;
        if(abs >= Integer.MAX_VALUE || abs <= Integer.MIN_VALUE){
            return sign == 1 ? Integer.MAX_VALUE: Integer.MIN_VALUE;
        }

        return parsed * sign;
    }
}
