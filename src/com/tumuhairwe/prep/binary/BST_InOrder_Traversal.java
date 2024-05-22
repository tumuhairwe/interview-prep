package com.tumuhairwe.prep.binary;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * Solution Summary
 * - If root is null, return empty list
 * - Create Queue (FIFO data structure)
 * - Traverse left recursively -> add results to queue
 * - Add node.value to queue
 * - Traverse right recursively -> add results to queue
 * - return queue
 * ref: https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 */
public class BST_InOrder_Traversal {
    public List<Integer> inOrderTraversal(TreeNode<Integer> root){
        // 0. base case
        if(root == null){
            return new ArrayList<>();
        }

        Queue<Integer> queue = new ArrayDeque<>();

        // 1. traverse left
        if(root.left != null){
            List<Integer> leftEntries = inOrderTraversal(root.left);
            queue.addAll(leftEntries);
        }

        //2. add node.data
        queue.add(root.data);

        // 3. traverse right
        if(root.right != null){
            List<Integer> rightEntries = inOrderTraversal(root.right);
            queue.addAll(rightEntries);
        }

        return new ArrayList<>(queue);
    }
}
