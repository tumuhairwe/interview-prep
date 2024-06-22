package com.tumuhairwe.prep.binary;

/**
 * LeetCode 112 Path sum (easy)
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 */
public class PathSum {

    /**
     * Solution summary
     * - check if root is leaf -> return currentSum == target
     * - do recursive binary search on root.left OR root.right passing in the diff between
     * currentSum (i.e, root.value and target)
     * - Recursive call should return true if any on the left path or right path is a match
     */
    public boolean hasPathSum(TreeNode<Integer> root, int targetSum) {
        if(root == null){
            return false;
        }

        int currSum = root.data;
        if(root.left == null && root.right == null){
            return currSum == targetSum;
        }
        else{
            return hasPathSum(root.left, targetSum- currSum) || hasPathSum(root.right, targetSum - currSum);
        }

    }
}
