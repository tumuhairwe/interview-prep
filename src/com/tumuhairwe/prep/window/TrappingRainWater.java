package com.tumuhairwe.prep.window;

/**
 * Given
 * LeetCode 76
 * ref: https://leetcode.com/problems/trapping-rain-water/description/
 * ref: https://www.youtube.com/watch?v=8cqpkCreiwM
 * ref: https://www.youtube.com/watch?v=AFl_VxaO_F0
 * ref: https://www.youtube.com/watch?v=KFdHpOlz8hs
 * ref: https://github.com/gahogg/Leetcode-Solutions/blob/main/Trapping%20Rain%20Water%20-%20Leetcode%2042.py
 * ref: https://leetcode.com/problems/trapping-rain-water/solutions/5274707/trapping-rain-water-efficient-linear-solution/?envType=problem-list-v2&envId=plakya4j
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Should be 6 " + trap(arr));
    }

    /**
     * Solution summary (pre-processing)
     * - build 2 arrays
     *      - max_left: track the max height of the wall fom the left up to that index
     *      - max_right: track the max height of the wall fom the right up to that index
     * - Calculate trapped water
     *      - Get the waterLevel i.e. min between max_left[i and max_right[i] -- to get the height of the shorter wall btwen the 2 (at that index)
     *      - subtract waterLevel from height[i]
     *      - Add that index's watter to the total
     *  - return the total
     */
    public static int trap(int[] height){
        //0. initialize and fill left max array
        int[] max_left = new int[height.length];    // store the highest preceding left-wall from the left up to that index
        max_left[0] = height[0];    // seed max left
        for (int i = 1; i < height.length; i++) {
            max_left[i] = Math.max(height[i], max_left[i - 1]); // Max height from the left up to index i
        }

        //1. initialize and fill the right max array
        int[] max_right = new int[height.length];   // stores the highest preceding right-wall from the right up to that index
        max_right[height.length - 1] = height[height.length - 1];   // seed max right
        for (int i = height.length - 2; i < height.length; i--) {
            max_right[i] = Math.max(height[i], max_right[i + 1]);   // Max height from the right up to index i
        }

        //2. calculate the trapped water
        int trappedWater =0;
        for (int i = 0; i < height.length; i++) {
            int waterLevel = Math.min(max_left[i], max_right[i]);   // Water level at index i
            trappedWater += waterLevel - height[i];                 // Trapped water at index i
        }

        return trappedWater;
    }
}
