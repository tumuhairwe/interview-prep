package com.tumuhairwe.prep.binary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 230 (medium)
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * Solution Summary
 * - Implement recursive in-order traversal (while collecting all values in a collection)
 * - IN-ORDER: LEFT -> ROOT -> RIGHT
 * - return i-th element in list where i = k-1 (since k is 1-indexed)
 *
 *  - alternatively, use a PQ with a reverse() integer.comparator()
 *  - while pq.size() > k ... poll() entry at top of pq/head
 *  - finally return the top of the heap (i.e. k-th entry in pq)
 *
 * ref: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class SmallestElementInBST {
    public int kthSmallest(TreeNode<Integer> root, int k) {
        List<Integer> list = inOrderTraversal(root);
        if(list.size() <= k){
            return list.get(list.size() - 1);
        }
        return list.get(k - 1);
    }

    public int kthSmallest_pq_imple(TreeNode<Integer> root, int k) {
        List<Integer> list = inOrderTraversal(root);
        Comparator<Integer> comp = Comparator.comparingInt(a -> a);
        PriorityQueue<Integer> pq = new PriorityQueue<>(comp.reversed());
        pq.addAll(list);

        while (pq.size() > k){
            pq.poll();
        }

        return pq.poll();
    }

    List<Integer> inOrderTraversal(TreeNode<Integer> node){
        if(node == null){
            return new ArrayList<>();
        }

        List<Integer> results = new ArrayList<>();
        if(node.left != null){
            results.addAll(inOrderTraversal(node.left));
        }
        results.add(node.data);

        if(node.right != null){
            results.addAll(inOrderTraversal(node.right));
        }

        return results;
    }
}
