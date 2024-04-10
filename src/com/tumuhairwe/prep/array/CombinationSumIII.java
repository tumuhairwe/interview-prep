package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * combination sum
 * LeetCode 216 Combination Sum III
 *
 * Find valid combinations of k numbers that sum up to n such that the following are true
 * - Only numbers 1 thru 9 are used
 * - Each number is use at most once
 *
 * Return a list of all possible valid combinations
 *
 * ref: https://leetcode.com/problems/combination-sum-iii/description/
 * ref: https://www.youtube.com/watch?v=z2Zk04UDYnY
 *
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int target){
        Stream.iterate(1, i -> i+1)
                .takeWhile(n -> n <= 10)
                .map(x -> x * x)
                .forEach(x -> System.out.println(x));

        int[] arr =new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        return new ArrayList<>();
    }
}
