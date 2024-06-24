package com.tumuhairwe.prep.array;

/**
 * LeetCode 915 (medium)
 *
 * ref: https://leetcode.com/problems/partition-array-into-disjoint-intervals/description/
 */
public class PartitionArrayIntoDisjointIntervals {

    public static void main(String[] args) {
        int[] arr = {5,0,3,8,6};
        System.out.println("Should be 3 " + partitionDisjoint(arr));

        int[] arr2 = {1,1,1,0,6,12};
        System.out.println("Should be 4 " + partitionDisjoint(arr2));

    }
    /**
     * Solution summary
     * - Initialize current & max to the num[0]
     * - As you iterate thru the array
     *      -if the current element (nums[i]) is less than curr,
     *          - update partitionIndex to i+1
     *          - update curr to the max
     *      - otherwise, update max to max(nums[i] max) (i.e.  update max to be the maximum of the current element and the existing max.)
     *
     */
    public static int partitionDisjoint(int[] nums){
        if(nums.length == 0){
            return 0;
        }

        int curr = nums[0];
        int max = nums[0];;
        int partitionIndex = 1;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < curr){
                partitionIndex = i +1;
                curr = max;
            }
            else {
                max = Math.max(max, nums[i]);
            }
        }

        return partitionIndex;
    }
}
