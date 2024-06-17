package com.tumuhairwe.prep.strings;

import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 12 (medium)
 *
 * ref: https://leetcode.com/problems/integer-to-roman/description/
 * ref: https://www.youtube.com/watch?v=ohBNdSJyLh8
 */
public class IntegerToRoman {

    public static void main(String[] args) {
        System.out.println("3749 = " + intToRomain(3749));
        System.out.println("58 = " + intToRomain(58));
        System.out.println("1994 = " + intToRomain(1994));
    }

    /***
     * Solution summary
     * - create a treeMap (source of truth) with key = denomination, value = String representation
     * - use map.floorEntry() to get "exact value or value immediately less than $key) from map
     * - iteratively get the value for num ... use that to build the string
     * - update num = num - entry.getKey() and repeat until num = 0
     * - return built string
     */
    public static String intToRomain(int  num){
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1, "I");

        map.put(4, "IV");
        map.put(5, "V");

        map.put(9, "IX");
        map.put(10, "X");

        map.put(40, "XL");
        map.put(50, "L");

        map.put(90, "XC");
        map.put(100, "C");

        map.put(400, "CD");
        map.put(500, "D");

        map.put(900, "CM");
        map.put(1000, "M");

        StringBuilder sb = new StringBuilder();
        while (num > 0){
            Map.Entry<Integer, String> entry = map.floorEntry(num);
            sb.append(entry.getValue());
            num -= entry.getKey();
        }
        return sb.toString();
    }
}
