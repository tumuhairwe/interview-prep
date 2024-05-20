package com.tumuhairwe.prep;

/**
 * Given 2 numbers a and b, return the sum of the 2 integers without using the operators + and -
 * ref: https://leetcode.com/problems/sum-of-two-integers/
 */
public class SumOfTwoIntegers {

    public static void main(String[] args) {
        int a = 1, b = 2;
        System.out.println("Should be 3: " + getSum(a, b));
    }
    public static int getSum(int a, int b) {
        while(b != 0){
            // calc value of b before bit-shifting
            int temp = (a & b) << 1;
            a = (a ^ b);
            b = temp;
        }

        return a;
    }
}
