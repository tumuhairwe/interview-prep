package com.tumuhairwe.prep.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 13. Roman to Integer (easy)
 * https://leetcode.com/problems/roman-to-integer/description/
 */
public class RomanToInteger {

    public static void main(String[] args) {
        System.out.println("Should be 3: " + romanToInt("III"));
        System.out.println("Should be 58: " + romanToInt("LVIII"));
        System.out.println("Should be 1994: " + romanToInt("MCMXCIV"));
    }
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
