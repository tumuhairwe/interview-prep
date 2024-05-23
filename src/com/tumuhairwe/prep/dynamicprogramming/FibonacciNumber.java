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
 * ref: https://blog.pramp.com/how-to-solve-any-dynamic-programming-problem-603b6fbbd771
 */
public class FibonacciNumber {

    public static void main(String[] args) {
        System.out.println("Given n = " + 3 + " there are " + fibonnaciSolution_recursive(3));
    }

    /**
     * TC: O(2^n)
     * Because the depth of our recursivion is N, and each level has twice as many calls
     *
     * Since we have overlapping sub-problems ... use dynamic programming
     */
    static int fibonnaciSolution_recursive(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return fibonnaciSolution_recursive(n - 1) + fibonnaciSolution_recursive(n - 2);
    }

    /**
     * Since we know the sulrs of fib(n) depend on the fib(n - 1) and fib(n -2), we can cache/memoize the results
     * and check if they exist in the cache before computing them
     *
     * This new solution only has to compute each value once, so it runs in O(n) time,
     * because of the cache though, it uses O9n) space
     */
    static int fibonacci_dp(int n){
        if(n < 2){
            return n;
        }

        //0. create cache
        int[] cache = new int[n + 1];

        // 1. seed the cache with default value e.g. -1
        for (int i = 0; i < cache.length; i++) {
            cache[i] = -1;
        }

        // 2.
        cache[0] = 0;
        cache[1] = 1;

        // 3. call recursive helper that can use cache
        return fib(n, cache);
    }

    // top-down solution:
    private static int fib(int n, int[] cache) {
        // if value is et in cache, return it
        if(cache[n] > 0){
            return cache[n];
        }
        cache[n] = fib(n - 1, cache) + fib(n -1, cache);
        return cache[n];
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
