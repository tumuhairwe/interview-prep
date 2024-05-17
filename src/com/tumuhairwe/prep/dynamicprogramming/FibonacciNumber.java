package com.tumuhairwe.prep.dynamicprogramming;

/**
 * LeetCode 509 (easy)
 *
 * The fibonacci numbers commonly denoted as F(n) form a sequence called the finbonnaci sequence
 * such that each number is the sum of the 2 preceding numbers , starting form o and 1
 *
 * e.g.
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 *
 * Given N, calculate f(n)
 */
public class FibonacciNumber {

    public static void main(String[] args) {
        System.out.println("Given n = " + 3 + " there are " + fibonnaciSolution_recursive(3));
    }

    //TC: O(2^n)
    static int fibonnaciSolution_recursive(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return fibonnaciSolution_recursive(n + 1) + fibonnaciSolution_recursive(n - 2);
    }
    /**
     * Solution Summary
     * - Base case: if n == 1, return 1, if n== 2, return 2
     * - Build fibonnaci sequence until n by sliding current forward
     *      - sum = previous_1 + previous_2
     *      - previous_1 = previous_2
     *      - previous_2 = sum
     *  return current (since loop was upt to N)
     */
    static int fibonnaciSolution_iterative(int n){
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int previous_1 = 0;
        int previous_2 = 1;
        int sum = 0;

        while (n > 1){
            sum = previous_1 + previous_2;
            previous_1 = previous_2;
            previous_2 = sum;

            n--;
        }

        return sum;
    }
}
