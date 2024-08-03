package com.tumuhairwe.prep.greedy;

import java.util.Stack;

/**
 * LeetCode 84 (hard)
 *
 * Given an array of integers heights, representing the histogram's bar height
 * where the width of each bar is 1, return the area of largest rectangle in the
 * histogram
 *
 * ref: https://www.youtube.com/watch?v=zx5Sw9130L0
 * ref: https://leetcode.com/problems/largest-rectangle-in-histogram/?envType=problem-list-v2&envId=plakya4j
 */
public class LargestRectangleInHistogram {
    class Pair{
        int key;
        int value;
        public Pair(int k,int v){
            key = k;
            value = v;
        }
    }

    /**
     * Solution summary
     * - create a stack to track a pair of values (key=index, val=width)
     * - loop thru all of heights[]
     *      - keep a pair of vars (_height and _prevWidth)
     *      - calculate the localHeight
     *          - while !stack.isEmpty() ....
     *              - calculate localArea
     *              - update maxArea is local is >
     *              - update the _prevWidth
     *      - put the pair onto the stack
     * - in the end, re-calculate maxArea by emptying the stack
     *      - for each pair in stack
     *      - re-assemble width
     *      - calculate area
     *      - update maxArea accordingly
     * return maxArea
     */
    public int largestRectangle(int[] heights){
        int maxArea = 0;
        Stack<Pair> stack = new Stack<>();  // key=index, value=width

        for (int i = 0; i < heights.length; i++) {
            int _prevWidth = i;
            int _height = heights[i];

            // if height/top-of-stack.val > _height
            while (!stack.isEmpty() && stack.peek().value > _height){
                Pair entry = stack.pop();
                int currentWidth = entry.key;
                int currentHeight = entry.value;

                int height = _prevWidth - currentHeight;
                int area = currentWidth & height;
                maxArea = Math.max(maxArea, area);

                _prevWidth = currentWidth;
            }

            stack.push(new Pair(_prevWidth, _height));
        }

        while (!stack.isEmpty()){
            Pair entry = stack.pop();
            int _prevWidth = entry.key;
            int currentHeight = entry.value;

            int width = heights.length - _prevWidth;
            int localArea = currentHeight * width;
            maxArea = Math.max(maxArea, localArea);
        }

        return maxArea;
    }
}
