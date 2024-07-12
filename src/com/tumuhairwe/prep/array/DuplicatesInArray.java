package com.tumuhairwe.prep.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an INTEGER array of length n, where all integers are in
 * range [1...n], and each integer appears one or twice,
 * return an array of all the integers that appear twice
 *
 * Given that 0 < a[i] < LENGTH_OF_ARRAY (where each value is between 1 & length of array),
 * Some elements can appear once to twice
 *
 * Must run in O(n) linear runtime and must not use extra space
 * Find all the elements that appear twice
 * - WITH no extra space
 * - and in linear runtime
 *
 * Summary of solution:
 * - Find the value that the index the current value references
 * - Go to that index ... that the value references ... and make it negative
 * - When you encounter it again .... (and its negative) ... that means its already been seen
 *
 * LeetCode 442 (medium) Find all duplicates
 * ref: https://www.youtube.com/watch?v=aMsSF1Il3IY
 * ref: https://www.youtube.com/watch?v=Y8x0iAVEITo
 * ref: https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
 */
public class DuplicatesInArray {

    /**
     * Solution Summary
     * - iterate over the array and create a char-frequency Map
     * - find all entries with value > 1 and collect them into a list
     *
     * TC: O(n)
     * SC: O(n)
     *
     * Compile Map of frequency of every number is nums (key=number, value=frequency)
     * return only those have a frequency of more than 1
     */
    public List<Integer> findDuplicates_byFrequencyMap(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(Integer n : nums){
            int existingCount = freqMap.getOrDefault(n, 0);
            freqMap.put(n, existingCount + 1);
        }

        List<Integer> duplicates = freqMap.entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .map(e -> e.getKey())
                .collect(Collectors.toList());
        return duplicates;
    }

    /**
     * Solution Summary
     *
     * - Get absolute value of an entry/number
     * - Use that as the index
     * TC: O(n)
     * SC: O(1) (if you don't include the result)
     */
    public List<Integer> findDuplicates(int[] nums){
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // find absolute value of the number, subtract 1 (to get the Zero-based index)
            int index = Math.abs(nums[i]) - 1;  // goal is to a) get the absolute/positive value and b) -1 to get the index
            if (nums[index] < 0){       // if the value is already negative
                result.add(index + 1);
            }
            else {
                nums[index] = -nums[index];     // update it to negative
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        int[] nums2 = new int[]{1,1,2};
        List<Integer> result = new ArrayList<>();

        // when 0 < a[i] < LENGTH of array = the value can be a valid index of the array
        for (int i = 0; i< nums.length; i++){
            // get index
            int value = nums[i];
            int index = Math.abs(value) - 1;
            if(nums[index] < 0){    // if value is already negative (i.e. already encountered) -- collect to resultss array
                result.add(index + 1);  // collect the value itself
                nums[index] = -nums[index]; // update it the negative value
            }
        }

        System.out.println("Given Nums: " + Arrays.toString(nums) + " ListOfDupes=" + result);
    }
    /*
    Given = [4, 3, 2, 7, 8, 2, 3, 1]

    nums[i] = 4
    index = 4 - 1 = 3
    nums[3] = 7
    (7 < 0) = false
    7 = -1 .... nums[index= = -nums[index]
    // state: [4, 3, 2, -7, 8, 2, 3, 1]

    nums[i] = 3
    index = 3 - 1 = 2
    nums[2] = 2
    (1 < 0) = false
    1 = -1 .... nums[index= = -nums[index]
    // state: [4, 3, -2, -7, 8, 2, 3, 1]

    nums[i] = -2
    index = 2 - 1 = 2
    nums[2] = 2
    (2 < 0) = false
    2 = -2 .... nums[index= = -nums[index]
    // state: [4, 3, -2, -7, 8, 2, 3, 1]

    nums[i] = -2
    index = 2 - 1 = 2
    nums[2] = 2
    (2 < 0) = false
    2 = -2 .... nums[index= = -nums[index]
    // state: [4, 3, -2, -7, 8, 2, 3, 1]

     */
}
