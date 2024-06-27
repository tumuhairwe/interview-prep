package com.tumuhairwe.prep.strings;

import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 12 (medium)
 * Seven different symbols represent Roman numerals with the following values:
 *
 * Symbol	Value
 * I	1
 * V	5
 * X	10
 * L	50
 * C	100
 * D	500
 * M	1000
 * Roman numerals are formed by appending the conversions of decimal place values from highest to lowest. Converting a decimal place value into a Roman numeral has the following rules:
 *
 * If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the input, append that symbol to the result, subtract its value, and convert the remainder to a Roman numeral.
 * If the value starts with 4 or 9 use the subtractive form representing one symbol subtracted from the following symbol, for example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less than 10 (X): IX. Only the following subtractive forms are used: 4 (IV), 9 (IX), 40 (XL), 90 (XC), 400 (CD) and 900 (CM).
 * Only powers of 10 (I, X, C, M) can be appended consecutively at most 3 times to represent multiples of 10. You cannot append 5 (V), 50 (L), or 500 (D) multiple times. If you need to append a symbol 4 times use the subtractive form.
 * Given an integer, convert it to a Roman numeral.
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
