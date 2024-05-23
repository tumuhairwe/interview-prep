package com.tumuhairwe.prep.binary;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * LeetCode 173 (Medium)
 * Implement a BST iterator that follow in-order traversal
 *
 * for reference
 * in-order = left -> current -> right
 * post-order= left -> right -> current
 * pre-order = current -> left -> right
 *
 * ref: https://leetcode.com/problems/binary-search-tree-iterator/
 * ref: https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 */
public class BSTIterator_InOrder implements Iterator<Integer> {
    TreeNode<Integer> iterator;
    Queue<Integer> traversal;

    public BSTIterator_InOrder(TreeNode root){
        this.iterator = root;
        traversal = new ArrayDeque<>();
        fillTheQueue(iterator);
    }

    private void fillTheQueue(TreeNode<Integer> iterator) {
        if(iterator.left != null){
            fillTheQueue(iterator.left);
        }

        traversal.add(iterator.data);

        if(iterator.right != null){
            fillTheQueue(iterator.right);
        }
    }

    @Override
    public Integer next() {
        if (!traversal.isEmpty()){
            return traversal.poll();
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        return !traversal.isEmpty();
    }
}
