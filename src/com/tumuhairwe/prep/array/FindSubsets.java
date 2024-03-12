package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of integers, find all possible subsets of nums, including the empty set
 *
 * Note: The solution must not contain duplicate subset. It can be returned in any order
 *
 * Summary:
 * 1. Calculate the subsets count using the formula 2^n (where n is the number of integers in the array)
 * 2. Iterate from 0 to the subsets count
 * 3. Generate subsets using bitwise operations
 * 4. Add each generated subset to the list of subsets
 * 5. Return the list of subsets
 */
public class FindSubsets {
    public static void main(String[] args) {
        int[][] inputSets = {
                {},
                {2, 5, 7},
                {1, 2},
                {1, 2, 3, 4},
                {7, 3, 1, 5}
        };

        for (int i = 0; i < inputSets.length; i++) {
            int[] set = inputSets[i];

            System.out.println((i+1) + " Set: " + Arrays.toString(set));
            List<List<Integer>> subsets = findAllSubsets(set);

            System.out.println(" Subsets: " + subsets);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
    static List<List<Integer>> findAllSubsets(int[] nums){
        List<List<Integer>> setsList = new ArrayList<>();
        if(nums.length == 0){
            List<Integer> emptySet = new ArrayList<>();
            setsList.add(emptySet);
        }

        int subsetsCount = (int) Math.pow(2, nums.length);
        for (int i = 0; i < subsetsCount; ++i){
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < nums.length; ++j) {
                if(getBit(i, j) == 1){
                    int x = nums[j];
                    subset.add(x);
                }
            }
        }
        return  setsList;
    }

    static int getBit(int num, int bit){
        int temp = 1 << bit;
        temp = temp & num;
        if(temp == 0){
            return 0;
        }
        return 1;
    }
}
