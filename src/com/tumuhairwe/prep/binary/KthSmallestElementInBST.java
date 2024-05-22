package com.tumuhairwe.prep.binary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
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
    public int kthSmallest(TreeNode<Integer> root, int k) {
        if(root == null){
            return 0;
        }

        List<Integer> list = getBST_inOrder_asList(root);

        Comparator<Integer> comp = Comparator.comparingInt(a -> a);
        PriorityQueue<Integer> pq = new PriorityQueue<>(comp.reversed());
        pq.addAll(list);

        while(pq.size() > k){
            pq.poll();
        }

        return pq.poll();
    }

    List<Integer> getBST_inOrder_asList(TreeNode<Integer> node){
        if(node == null){
            return new ArrayList<>();
        }

        List<Integer> results = new ArrayList<>();
        results.add(node.data);

        if(node.left != null){
            results.addAll(getBST_inOrder_asList(node.left));
        }

        if(node.right != null){
            results.addAll(getBST_inOrder_asList(node.right));
        }

        return results;
    }
}
