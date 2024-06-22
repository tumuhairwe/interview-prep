package com.tumuhairwe.prep.binary;

/**
 * LeetCode 1448 (medium)
 * Count Good Nodes in Binary Tree
 *
 * ref: https://leetcode.com/problems/count-good-nodes-in-binary-tree
 */
public class CountGoodNodes {

    int count = 0;
    public int goodNode(TreeNode<Integer> root){
        if(root == null){
            return 0;
        }

        checkGoodNodes(root, root.data);
        return count;
    }

    /**
     * Solution summary
     * - if a node is good (less than or equal to maxValue, increment count * update maxValue
     * - check both left & right with the updated maxValue
     */
    private void checkGoodNodes(TreeNode<Integer> node, int maxValue){
        if(node == null){
            return;
        }

        // if is good node: increment count and set maxValue (to be obeyed later)
        if(maxValue <= node.data){
            maxValue = node.data;
            count++;
        }

        checkGoodNodes(node.left, maxValue);
        checkGoodNodes(node.right, maxValue);
    }
}
