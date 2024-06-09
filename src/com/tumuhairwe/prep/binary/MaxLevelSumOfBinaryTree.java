package com.tumuhairwe.prep.binary;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Leetcode 1161
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 *
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 */
public class MaxLevelSumOfBinaryTree {

    public static void main(String[] args) {

    }

    /**
     * Solution summary (Use iterative BFS)
     * - create a que and seed it with root
     * - create maxLevel and maxSum vars to be updated later
     * - while queue is not empty
     *      -> Loop for the duration of the que depth,
     *      -> pull from queue
     *      -> push non-null children to queue
     *      -> update currentSum += node's value
     * -  if currentSum > maxSum -> { update maxSum and maxLevel }
     * - return maxLevel
     */
    public static int maxLevelSum(TreeNode<Integer> root){
        // 0. base case
        if(root == null){
            return 0;
        }

        // 1. do BFS: create queue and seed with root
        Queue<TreeNode<Integer>> que = new LinkedList<>();
        que.add(root);

        // 2. declare vars to track maxSm and level
        int maxSum = Integer.MIN_VALUE;
        int level = 1;
        int maxLevel = 1;

        // start BFS
        while (!que.isEmpty()) {
            int depth = que.size();
            int currentSum = 0;

            // 3. add left + right to queue
            for (int i = 0; i < depth; i++) {
                TreeNode<Integer> node = que.poll();
                if(node.left != null){
                    que.add(node.left);
                }
                if(node.right != null){
                    que.add(node.right);
                }
                currentSum += node.data;
            }

            // 4. update maxSum and maxLevel
            if(currentSum > maxSum){
                maxSum = currentSum;
                maxLevel = level;
            }
            level++;
        }

        return maxLevel;
    }
}
