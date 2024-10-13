package com.tumuhairwe.prep.dynamicprogramming;

/**
 * LeetCode 3179 (medum)
 * Find the N-th value after k seconds
 *
 * Given 2 integers n & k,
 * initially, you start with an array (a) of N integers where a[i]=1 (for all 0 <= i <= n-1)
 * After each second, you simultaneously update each element to be the sum of all its preceeding elements plus the element
 * itself.
 * e.g.
 * return the value of a[n-1] after k seconds
 * Since the answer may be very large, return it module 10^9 + 7
 *
 * ref: https://leetcode.com/problems/find-the-n-th-value-after-k-seconds/description/
 */
public class ValueAfterKSeconds {

    public static void main(String[] args) {
        System.out.println(valueAfterKSeconds(4, 5));
        System.out.println(valueAfterKSeconds(5, 3));
    }

    /**
     * Solution summary
     * - create vars (arr & mod)
     * - seed arr with initial values
     * - do dp calc to populate all the values
     * - return arr[n-1;
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int valueAfterKSeconds(int n, int k) {
        //0. init vars (arr & mod)
        double arr[] = new double[n];
        double MOD = Math.pow(10, 9) + 7;

        //1. seed arr with initial values
        for (int i = 0; i < n; i++) {
            arr[i] = 1;
        }

        int numSeconds = k;
        while (numSeconds-- > 0){
            //2 do dp to calc all values
            for (int i = 1; i < n; i++) {
                arr[i] = (arr[i - 1] + arr[i]) % MOD;
            }
        }
        return (int)arr[n-1];
    }
}
