package com.tumuhairwe.prep.binary;

/**
 * Given the root of a BST, and a node p,
 * return the in-order successor of that node in the BST. If the given node has no
 * in-order successor in the tree, return null
 *
 * Definition: successor of a node is the [smallest-key-greater | father-of ] than p.val
 * Solution Summary
 * - set current = root
 * - while current != null
 * - Use 2 pointers to track current and previous (in BST, smallest-key-greater-than === father-of)
 * - if node.data > needle.data -> go left (and update current & prevous pointers)
 * - else if node.data < needle.data -> go right
 * - At the end/exit (when you hit current == null), previous_pointer will point to father-of (i.e. successor with key greater than needle.data)
 * ref: https://leetcode.com/problems/inorder-successor-in-bst/description/
 */
public class BSTSuccessor_InOrder {

    public TreeNode<Integer> inOrderSuccessor(TreeNode<Integer> root, TreeNode<Integer> p){
        //in-order == LEFT -> root -> RIGHT (See BSTIterator_InOrder.fillTheQueue()

        //0. base case
        if(root == null){
            return null;
        }

        // 1. define vars
        TreeNode<Integer> current = root;
        TreeNode<Integer> previous = null;

        // 2. iterate tree while keeping 2 pointers (previous and current)
        while (current != null){
            if (current.data > p.data){
                previous = current;
                current = current.left;
            }
            else {
                current = current.right;
            }
        }

        // 3. return previous pointer
        return previous;
    }
}
