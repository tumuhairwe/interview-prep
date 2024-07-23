package com.tumuhairwe.prep.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 2610 (medium)
 *
 * You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:
 *
 * The 2D array should contain only the elements of the array nums.
 * Each row in the 2D array contains distinct integers.
 * The number of rows in the 2D array should be minimal.
 * Return the resulting array. If there are multiple answers, return any of them.
 *
 * Note that the 2D array can have a different number of elements on each row.
 */
public class FindMatrix {

    public static void main(String[] args) {
        int[] arr = {1,3,4,1,2,3,1};
        System.out.println(findMatrix(arr));
    }

    /**
     * Solution summary
     * - create frequency array of size n + 1
     * - Create 2D grid List of Lists
     * - iterate over nums
     *      - initialize value as emptyList
     *      - add num to grid (x-axis = frequency, y-axis= num)
     *      - increment frequency of num (so next time is encountered e..g. if duplicates, it will be in the next index's list)
     * - return 2D list
     */
    public static List<List<Integer>> findMatrix(int[] nums){
        //0. create a frequency array of size n + 1
        int[] freq = new int[nums.length + 1];

        List<List<Integer>> grid = new ArrayList<>();
        // for each number in array, use its frequency (initially 0) as x-index,
        // and number itself as y-index
        for (int num : nums){
            //0. initialize to list if its frequency >= size_of_grid
            int existingFrequency = freq[num];
            if(existingFrequency >= grid.size()){
                grid.add(new ArrayList<>());
            }

            //1. always add the integer to the list corresponding to its current frequency
            // list-index = frequency, value = num
            grid.get(freq[num]).add(num);

            //3. increment frequency of that number (so that it encountered again, it'll be the next index's list)
            freq[num]++;
        }

        return grid;
    }
}
