package com.tumuhairwe.prep.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 228 (easy)
 * Given a sorted unique array of all integers from a to b inclusives
 *
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 *
 * "a->b" if a != b
 * "a" if a == b
 *
 * ref: https://leetcode.com/problems/summary-ranges/description/
 * ref: https://www.youtube.com/watch?v=2TuwrFmSshk
 */
public class SummaryRanges {

    public static void main(String[] args) {
        int[] arr = {0,1,2,4,5,7};
        System.out.println(summaryRanges(arr));

        int[] arr2 = {0,2,3,4,6,8,9};
        System.out.println(summaryRanges(arr2));
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        //0. iterate over nums
        for (int endOfRange = 0; endOfRange < nums.length; endOfRange++) {
            int startOfRange = nums[endOfRange];

            //boolean isLastIndex = i == nums.length -1;
            //boolean isBrokenSequence = nums[i] + 1 != nums[i + 1];
            //1. iterate & increment i until we have a break or we reach the end
            while (endOfRange+1 < nums.length && nums[endOfRange] + 1 == nums[endOfRange + 1]){
                endOfRange++;
            }

            //2. if start & i are diff, its a range
            if(startOfRange != endOfRange){
                result.add(startOfRange + "->" + endOfRange);
            }
            else {
                result.add(endOfRange + "");
            }
        }

        return result;
    }
}
