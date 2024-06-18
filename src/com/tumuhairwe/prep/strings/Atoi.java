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
        // 1. trim leading spaces
        String st = s.trim();
        if(st.isEmpty()){
            return 0;
        }

        // 1. check for sign
        int idx = 0;
        int sign = 1;
        if(st.charAt(idx) == '-'){
            sign = -1;
            idx++;
        }
        else if(st.charAt(idx) == '+'){
            sign = 1;
            idx++;
        }

        //2. check digits
        int parsed = 0;
        while (idx < st.length()) {
            char cur = st.charAt(idx);
            if (!Character.isDigit(cur)) {
                break; // we've met a non-digit char
            }
            else{
                int digit = cur - '0';
                parsed = parsed * 10 + digit;    // e.g. 123

                if(sign == 1 && parsed > Integer.MAX_VALUE){
                    return Integer.MAX_VALUE;
                }
                else if(sign == -1 && parsed < Integer.MIN_VALUE){
                    return Integer.MIN_VALUE;
                }
            }

            System.out.println(cur + " __ " + parsed);
            idx++;
        }

        //4. clamp down into allowable range
        parsed *= sign; // multiply by 1 or -1 to force into +ve or -ve

        //4. clamp down
        boolean isInRange = parsed >= Integer.MIN_VALUE && parsed <= Integer.MAX_VALUE;
        return parsed;
    }
}
