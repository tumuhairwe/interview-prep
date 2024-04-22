package com.tumuhairwe.prep.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 77 (Medium)
 *
 * Given 2 integers n and k,
 * return all possible combinations of k numbers
 * chosen from the rang of [1, n]
 *
 * in any order
 * The difference between a combination and a permutation:
 * A permutation: list of data where ORDER of the data matters.
 *      - Formula: p = (n!)/(n-r) where
 *      - r = things chosen from a set of n things.
 *      - n = global set of things (where order maters) but the order is re-arranged
 *      - In a permutation -- every single member is included
 * A combination: A group of data where order doesn't matter
 */
public class Combination {
    public static void main(String[] args) {
        int n = 4, k = 2;
        List<List<Integer>> combinations = combine(n, k);
        System.out.println("There are " + n + " choose combinations with size " + k+ " = 6 total combinations.");
        System.out.println("Combinations: " + combinations);
    }

    static List<List<Integer>> results;
    private static List<List<Integer>> combine(int n, int k) {
        results = new ArrayList<>();

        List<Integer> emptyList = new ArrayList<>();
        backtrack(1, emptyList, n, k);
        return results;
    }

    private static void backtrack(int start, List<Integer> combinations, int n, int k) {
        if(combinations.size() == k){
            results.add(new ArrayList<>(combinations));     // base case : odd combinations of K numbers
            return;
        }

        for (int i = start; i <= n; i++) {
            combinations.add(i);    // add i
            backtrack(i+1, combinations, n, k);
            combinations.remove((Integer) i); // remove i (object)  i.e. backtrack
        }
    }
}
