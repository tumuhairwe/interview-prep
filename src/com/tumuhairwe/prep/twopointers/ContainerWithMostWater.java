package com.tumuhairwe.prep.twopointers;

/**
 * LeetCode 11 (medium)
 * Given an integer array (height) with N vertical lines drawn such that the 2 endpoints of the
 * i-th line are (i, 0) and (i, height[i])
 * Find the 2 lines that together with the x-axis form a container that contains the most water
 *
 * Return the maximum amount of water a container can store
 *
 * ref: https://www.youtube.com/watch?v=UuiTKBwPgAo
 * ref: https://leetcode.com/problems/container-with-most-water/description/
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {

    }

    /**
     * Solution Summary:
     * - Use a sliding window to track the container's length
     * - calculate containerLength = right - left
     * - calculate area: containerLength * min(height_on_the_left, height_on_the_right)
     * - Update returnValue with whichever is larger ( largestArea
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int  maxArea = 0;

        while (left < right){
            int currHeight = height[left] - height[right];
            int currWidth = right - left;
            int currArea = currHeight * currWidth;

            maxArea = Math.max(currArea, maxArea);
            if(height[left] < height[right]){
                left++;
            }
            else {
                right--;
            }
        }
        return maxArea;
    }
}
