package com.tumuhairwe.prep.twopointers;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of positive integers (nums) and want to erase a subarray
 * containing unique elements. The score you get by erasing the sub-array is equal
 * to the sum of its elements.
 * Return the maximum score you can get by erasing exactly one sub-array
 */
public class MaximumErasureValue {

    public int maximumUniqueSubarray(int[] nums) {
        //0. declare vars (set to contain uniques & local currentSum & 2 (dynamic-size) pointers
        int result = 0;
        int windowStart = 0;
        int currSum = 0;
        Set<Integer> set = new HashSet<>();

        //1. drag window over nums
        for(int windowEnd = 0; windowEnd < nums.length; windowEnd++){
            //2. remove element entering window if not unique -> decrement currentSum -> slide window forward
            while(set.contains(nums[windowEnd])){
                set.remove(nums[windowStart]);
                currSum -= nums[windowStart];
                windowStart++;
            }

            //3. add to window && update current sum
            set.add(nums[windowEnd]);
            currSum += nums[windowEnd];

            //4. update max
            result = Math.max(result, currSum);
        }
        return result;
    }
}
