package com.tumuhairwe.prep.binary;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LeetCode 33 (medium)
 * Search in a rotated Array
 *
 * ref: NeetCode:: https://www.youtube.com/watch?v=QdVrY3stDD4
 * ref: NickWhite: https://www.youtube.com/watch?v=U8XENwh8Oy8
 */
public class SearchRotatedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int target = 0;
        System.out.println("Searching a rotated array for " + target + " results (should be 4)=> " + binarySearchRotated(list, 0));

        nums = new int[]{4,5,6,7,0,1,2};
        list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        target = 0;
        System.out.println("Searching a rotated array for " + target + " results (should be -1)=> " + binarySearchRotated(list, 3));
    }
    // iterative
    // Time complexity = O(log_n)
    // Space complexity = O (1) since no new data structure is created
    public static int binarySearchRotated(List<Integer> nums, int target) {
        int start = 0;
        int end = nums.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            //int mid = (start + end) / 2;      // also good
            if (nums.get(mid) == target)
                return mid;
            else if (nums.get(start) <= nums.get(mid)) {
                if (nums.get(start) <= target && target < nums.get(mid)) {
                    end = mid - 1;
                } else
                    start = mid + 1;
            } else {
                if (nums.get(mid) < target && target <= nums.get(end))
                    start = mid + 1;
                else
                    end = mid - 1;
            }
            //List.of(nums)
            //List<Integer> xx = Arrays.asList(new int[]{9});
        }
        return -1;
    }
}
