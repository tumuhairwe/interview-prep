package com.tumuhairwe.prep.dynamicprogramming;

import java.util.Arrays;

/**
 * LeetCode 2320 medium
 * Given a street with n*2 plots where there are n plots on each side of the street,
 * The plots on each side are numbered 1 to n. On each plot a house can be placed
 *
 * Return the number of houses that can be placed such no two houses are adjacent to each other on the same side of the street
 * Since the answer may be very lard, return it modulo 10^9 + 7
 *
 * Note that if a house is placed on the i-th plot on the side of the street, a house can also be placed on teh i-th plot
 * on the other side of the street
 *
 * ref: https://www.youtube.com/watch?v=6yYtafKiCNQ
 * ref: https://leetcode.com/problems/count-number-of-ways-to-place-houses/description/
 */
public class CountNumberOfWaysToPlaceHouse {

    public static void main(String[] args) {
        System.out.println("Should be 4 :" + countHousePlacements(1));
        System.out.println("Should be 9 :" + countHousePlacements(2));
        System.out.println("Should be 500478595 :" + countHousePlacements(1000));
    }

    // req: "return answer 10^9 + 7"
    static long modulo = 1000000007;   // same as Math.pow(10, 9) + 7
    static long[] dp;

    /**
     * Solution summary
     * - init dp[] of length n + 1 & fill with UNPROCESSED_MARKER'
     * - call recursive function
     *      - base case: if n <0 return 1
     *      - if dp[n] has already been calculated, return it
     *      - $value = calculate(n - 2) + calculate(n - 1) % modulo_number
     *      - set dp[n] = $value
     *      - return dp[n]
     * - return (permutation ^ 2) % $num_specified_in_requirment
     */
    public static int countHousePlacements(int n){
        // 0. init tabulation array and fill with UNPROCESSED
        dp = new long[n + 1];
        Arrays.fill(dp, -1);

        long answer = solve(n);
        return (int)((answer * answer) % modulo);
    }

    public static long solve(int n){
        // base case
        if(n <= 0){
            return 1;
        }

        // use cached value instead of re-calculating
        if(dp[n] != -1){
            return dp[n];
        }

        long usedPlace = solve(n - 2);
        long unusedPlace = solve(n - 1);
        dp[n] = (unusedPlace + usedPlace) % modulo;
        return dp[n];
    }
}
