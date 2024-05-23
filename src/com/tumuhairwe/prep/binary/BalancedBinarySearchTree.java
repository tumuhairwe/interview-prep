package com.tumuhairwe.prep.binary;

import java.util.AbstractMap;
import java.util.Map;

/**
 * LeetCode 110 (easy)
 *
 * Determine if a binary tree is balanced
 *
 * Solution Summary
 * - Recursively call dfs( node.left) and dfs(node.right)
 * - return true IFF children are balance && diff between heights <= 1
 */
public class BalancedBinarySearchTree {
    public boolean isBalanced(TreeNode root) {

        return dfs(root).getValue();
    }

    public Map.Entry<Integer, Boolean> dfs(TreeNode node){
        if(node == null){
            return new AbstractMap.SimpleEntry<>(0, true);
        }

        Map.Entry<Integer, Boolean> leftResult = dfs(node.left);
        Map.Entry<Integer, Boolean> rightResult = dfs(node.right);

        boolean childrenAreBalanced = leftResult.getValue() && rightResult.getValue();
        int diffInHeights = Math.abs(leftResult.getKey() - rightResult.getKey());

        boolean isBalanced =  childrenAreBalanced && diffInHeights <= 1;
        Integer maxSubtreeHeight = Math.max(rightResult.getKey(), leftResult.getKey()) ;

        Map.Entry<Integer, Boolean> result = new AbstractMap.SimpleEntry<>(maxSubtreeHeight + 1, isBalanced);
        return result;
    }
}
