package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 13. Roman to Integer (easy)
 *
 *  Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *  Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 *
 * ref: https://www.youtube.com/watch?v=3jdxYj3DD98
 * ref: https://www.youtube.com/watch?v=JlVOzbOJiv0&t=302s
 * ref: https://leetcode.com/problems/roman-to-integer/description/
 */
public class RomanToInteger {

    public static void main(String[] args) {
        System.out.println("Should be 3: " + romanToInt("III"));
        System.out.println("Should be 58: " + romanToInt("LVIII"));
        System.out.println("Should be 1994: " + romanToInt("MCMXCIV"));
    }

    /**
     * Solution summary
     * - define a Map<Character,Integer> that is the source of truth for conversions (roman to int)
     * - define int result (to be computed as you iterate thru the char)
     * - iterate the roman/string for each char
     *      - if char doesn't exist as key in map, skip
     *      - if char exists, but its value is < the value of the char at charIndex+1, .. subtract it intValue from result
     *      - if char exists, else, add/+ it to the resumt (result += map.get( s.charAt(i) ) )
     *  - return result
     */
    public static int romanToInt(String s){
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if(!map.containsKey(s.charAt(i))){
                continue;
            }
            else if(i+1 < s.length() && map.get(s.charAt(i)) < map.get(s.charAt( i + 1))){
                result -= map.get(s.charAt(i));
            }
            else{
                result += map.get(s.charAt(i));
            }
        }

        return result;
    }
}
