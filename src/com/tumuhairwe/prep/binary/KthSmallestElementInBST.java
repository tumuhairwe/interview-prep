package com.tumuhairwe.prep.binary;

import java.util.*;

/**
 * LeetCode 230 (medium)
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the
 * values of the nodes in the tree.
 *
 * Solution Summary
 * - Collect the whole tree into a list
 * - Put the List into a PQ to sort by a minHeap comparator
 * - Remove all elements from PQ until pq is of size K
 * - stream() & collect() pq elements and return them
 *
 * ref: https://www.youtube.com/watch?v=5LUXSvjmGCw&list=TLPQMjIwNTIwMjQJITbpcQLnxg&index=3
 * ref: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElementInBST {

    /**
     * Solution summary
     * - Convert tree into List<Integer> of values ( pre-order: root -> left -> right)
     * - Put tree-as-list into maxHeap
     * - while maxHeap.size() > K ... poll()/remove() top of heap
     * - At the end, heap is of size K -> return top of heap
     */
    public Integer kthSmallest(TreeNode<Integer> root, int k) {
        if(root == null){
            return 0;
        }

        List<Integer> list = getBST_preOrder_asList(root);

        // same
        //Comparator<Integer> comp = Comparator.comparingInt(a -> a);
        //PriorityQueue<Integer> maxHeap = new PriorityQueue<>(comp.reversed());
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(list);

        while(maxHeap.size() > k){
            maxHeap.poll();
        }

        return maxHeap.poll();
    }

    // preorder: root -> left -> right
    // inorder: left -> root -> right
    // postorder : left -> right -> root
    // level-order: visit nodes level by level and left-to-right at the same level
    List<Integer> getBST_preOrder_asList(TreeNode<Integer> node){
        if(node == null){
            return new ArrayList<>();
        }

        List<Integer> results = new ArrayList<>();
        results.add(node.data);

        if(node.left != null){
            results.addAll(getBST_preOrder_asList(node.left));
        }

        if(node.right != null){
            results.addAll(getBST_preOrder_asList(node.right));
        }

        return results;
    }
}
