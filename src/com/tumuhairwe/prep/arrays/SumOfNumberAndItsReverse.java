package com.tumuhairwe.prep.arrays;

/**
 * LeetCode 2443 (medium)
 * Given a non-negative integer num, return true if num can b e expressed as the sum of any non-negative
 * integers AND its reverse, or false otherwise
 */
public class SumOfNumberAndItsReverse {
    /**
     * Solution summary
     * - iterate from 1 to num
     * - get the reversed version of that number
     * - if(1 + reversed == num) -> return true
     * - else return false
     *
     * TC: O(n)
     * SC: O(1)
     * @return
     */
    public boolean sumOfNumberAndReverse(int num) {
        //0. base case
        if(num == 0){
            return true;
        }

        //1. iterate from 1 to num, reverse the num ... check if i + reversed == num, return true
        for(int i = 1; i<num; i++){
            int reversed = reverse(num);
            if(i + reversed == num){
                return true;
            }
        }

        return false;
    }

    int reverse(int num){
        String str = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        for(int i=str.length()-1; i>=0; i--){
            sb.append(str.charAt(i));
        }

        return Integer.parseInt(sb.toString());
    }
}
