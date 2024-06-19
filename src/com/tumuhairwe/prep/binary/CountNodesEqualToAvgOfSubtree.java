package com.tumuhairwe.prep.binary;


import java.util.AbstractMap;
import java.util.Map;

/**
 * LeetCode 2265 (medium)
 *
 * Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of
 * the values in its subtree.
 *
 * Note:
 * - The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
 * - A subtree of root is a tree consisting of root and all of its descendants.
 *
 * ref: https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/description/
 */
public class CountNodesEqualToAvgOfSubtree {
    private int resultNodes;

    /**
     * Solution summary
     * - base case (if root == null), return 0
     * - create class-level var of result to be returned
     * - call a dfs() method that will compute the value recursively
     */
    private int averageOfSubTree(TreeNode<Integer> root){
        resultNodes = 0;
        if(root == null){
            return 0;
        }

        dfs(root);
        return resultNodes;
    }

    /**
     * Solution summary
     * - Make method return a Pair/Map.Entry (key = countOfNodes, val=sumOfNode)
     * - do recursive calls on node.left and node.right (plus null checks)
     * - to compute the sum: node.val + left.val + right.val
     * - to compute the count: 1 + left.key + right.key
     * - compute average
     * - if a node's value == avg, increment result
     * - have the method return the pair/Entry of count and sum
     */
    // return Pair<count, sumOfNodes>
    public Map.Entry<Integer, Integer> dfs(TreeNode<Integer> node){
        Map.Entry<Integer, Integer> left = new AbstractMap.SimpleEntry<>(0, 0);
        if(node.left != null){
            left = dfs(node.left);
        }

        Map.Entry<Integer, Integer> right = new AbstractMap.SimpleEntry<>(0, 0);
        if(node.right != null){
            right = dfs(node.right);
        }

        int sum = node.data + left.getValue() + right.getValue();
        int count = 1 + left.getKey() + right.getKey();
        int avg = sum / count;

        if(avg == node.data){
            resultNodes++;
        }

        return new AbstractMap.SimpleEntry<>(count, sum);
    }
}
