package com.tumuhairwe.prep.binary;


import com.tumuhairwe.prep.lists.MergeKSortedLists;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCpode 114 (medium)
 *
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * ref: https://www.youtube.com/watch?v=rKnD7rLT0lI
 */
public class FlattenBinaryTreeToLinkedList {

    // preOrder traversal = root -> left -> right
    public void flatten_impl1(TreeNode<Integer> root) {
        this.flattenTree_dfs(root);
    }

    /**
     * Solution Summary
     * - Use a list to collect the righ
     */
    public void flatten_impl2(TreeNode<Integer> root) {
        if (root == null){
            return;
        }

        //0. declare vars
        List<Integer> values = new ArrayList<>();

        //1. call recursively traverse (pre-order == root > left > right)
        preorder(root, values);
    }

    void preorder(TreeNode<Integer> root, List<Integer> values){
        if(root == null){
            return;
        }

        values.add(root.data);  // add index 0
        preorder(root.left, values);
        preorder(root.right, values);

        //reconstruct the tree based on the values in list
        TreeNode<Integer> current = root;
        for (int i = 1; i < values.size(); i++) {   // start from index 1
            current.left = null;
            current.right = new TreeNode<>(values.get(i));
            current = current.right;
        }
    }

    TreeNode<Integer> flattenTree_dfs(TreeNode<Integer> node) {
        //0. handle null scenario
        if(node == null){
            return null;
        }

        //1. for a leaf, we simply return the node as is
        if(node.left == null && node.right == null){
            return node;
        }
        //2. recursively flatten the left-subtree
        TreeNode<Integer> leftTail = flattenTree_dfs(node.left);

        //3. recursively flatten the left-subtree
        TreeNode<Integer> rightTail = flattenTree_dfs(node.right);

        // 4. if there's a left subtree we shuffle the connections around so there's nothing
        // on the left side anymore
        if(leftTail != null){
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        //5. return the rightmost node after we are done wiring the connections
        return rightTail != null ? rightTail : leftTail;
    }
}
