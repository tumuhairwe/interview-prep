package com.tumuhairwe.prep.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 1762 (medium)
 *
 * There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.
 *
 * The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.
 *
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 *
 * ref: https://leetcode.com/problems/buildings-with-an-ocean-view/description/
 */
public class BuildingWithOceanView {

    public static void main(String[] args) {
        int[] arr = {4,2,3,1};
        System.out.println("Should be 0, 2, 3: " + Arrays.toString(findBuildings(arr)));

        arr = new int[]{4,3,2,1};
        System.out.println("Should be 0, 1, 2, 3: " + Arrays.toString(findBuildings(arr)));

        arr = new int[]{1,3,2,4};
        System.out.println("Should be  3: " + Arrays.toString(findBuildings(arr)));
    }
    /**
     * Solution summary
     * - declare last and initialize to Integer.MIN_VALUE
     * - iterate the array from the end
     * - for each entry, if height[i] > last ... add entry to result list, set last to that entry
     * - return result list -> sorted -> mapped to intArray)
     */
    public static int[] findBuildings(int[] heights){
        List<Integer> results = new ArrayList<>();

        int last = Integer.MIN_VALUE;
        for (int i = heights.length - 1; i >= 0; i--) {
            if(heights[i] > last){
                results.add(i);
                last = heights[i];
            }
        }

        return results.stream().sorted().mapToInt(a -> a).toArray();
    }
}
