package com.tumuhairwe.prep.strings;

/**
 * LeetCode 2710 (easy)
 * Given a positive integer num, represented as a string, return n without trailing zeros
 *
 * e.g. "51230100" -> "512301"
 * e.g. "123" -> "123"
 * ref: https://leetcode.com/problems/remove-trailing-zeros-from-a-string/
 */
public class RemoveTrailingZeros {

    public static void main(String[] args) {
        System.out.println(removeTrailingZeros("31514216007864075756059111751787923413952537015859352242147727420"));
        System.out.println(removeTrailingZeros("51230100"));
        System.out.println(removeTrailingZeros("123"));
    }

    public static String removeTrailingZeros(String num){
        if(num == null || num.length() == 1){
            return num;
        }

        int idx = num.length() - 1;
        while (idx >=0 && num.charAt(idx) == '0'){
            idx--;
        }

        return num.substring(0, idx+1);
    }

    /**
     * Solution summary
     * - Create a new stringBuilder seeded with the integer "num"
     * - iterate "num" backward ...
     * - for each character==0, sb.deleteCharAt(idx)
     * - return sb.toString()
     */
    public String removeTrailingZeros2(String num) {

        StringBuilder sb = new StringBuilder(num);
        int idx = num.length() - 1;
        while(idx >=0 && num.charAt(idx) == '0'){
            sb.deleteCharAt(idx);
            idx--;
        }

        return sb.toString();
    }
}
