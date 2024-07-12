package com.tumuhairwe.prep.array;

import java.util.Map;

/**
 * LeetCode 303 (easy)
 *
 * Given an integer array nums, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 */
public class RangeSum {

    private int[] prefixSums;
    // implementation 2 uses pre-calculated sum in a map as opposed to array
    private Map<Integer, Integer> prefixSumMap;    // map of key=array_index, value=sumUpUntilThatPoint
    public RangeSum(int[] nums){
        int cumulativeSum = 0;
        prefixSums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            cumulativeSum += nums[i];
            prefixSums[i] = cumulativeSum;
        }
    }
    public void RangeSums2(int[] nums){
        int cumulativeSum = 0;
        // implementation 2
        for (int i = 0; i < nums.length; i++) {
            cumulativeSum += nums[i];
            prefixSumMap.put(i, cumulativeSum);
        }
    }

    /**
     * Solution Summary
     * - pre-compute the sums at initialization/construction so the reading/getting is O(1)
     * - get the sum of prefix[right]
     * - get the prefix[left] (make left 0 if out of bounds)
     * - return rightSum - leftSum
     */
    public int sumRange(int left, int right){
        int rightSum = prefixSums[right];
        int leftSum = left > 0 ? prefixSums[left - 1] : 0;
        return rightSum - leftSum;
    }

    /**
     * Solution summaryF
     * - pre-calculate the prefix sums at instantiation and store them in a map
     * - when retrieving, cost is cheaper, return sumUpToTheRight sumUpToTheLeft
     */
    public int sumRange2(int left, int right) {
        if(left == 0){
            return prefixSumMap.get(right);
        }

        int sumToTheLeft = prefixSumMap.get(left - 1);
        int sumToTheRight = prefixSumMap.get(right);

        return sumToTheRight - sumToTheLeft;
    }

    public static void main(String[] args) {
        int[] nums = {-2,0,3,-5,2,-1};
        RangeSum r = new RangeSum(nums);
        System.out.println("1"+ r.sumRange(0, 2));
        System.out.println("-1" + r.sumRange(2, 5));
        System.out.println("-3"+ r.sumRange(0, 5));
    }
}
