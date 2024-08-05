package com.tumuhairwe.prep.binary;

import java.net.Inet4Address;
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
 * ref: https://www.youtube.com/watch?v=5LUXSvjmGCw&list=TLPQMjIwNTIwMjQJITbpcQLnxg&index=3
 * ref: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class SmallestElementInBST {
    public int kthSmallest(TreeNode<Integer> root, int k) {
//        List<Integer> list = kSmallest_list_impl(root, k);
//        if(list.size() <= k){
//            return list.get(list.size() - 1);
//        }
        int res = kSmallest_list_impl(root, k);
        return res;
    }

    /**
     * Solution summary
     * - Collect all nodes into a list
     * - put list into a pq
     * - pop everything from the pq until the pq is of size k
     * - return entry on top of pq;
     */
    public int kthSmallest_pq_impl(TreeNode<Integer> root, int k) {
        List<Integer> list = inOrderTraversal_pq_impl(root);
        Comparator<Integer> comp = Comparator.comparingInt(a -> a);
        PriorityQueue<Integer> pq = new PriorityQueue<>(comp);  // minHeap will return the SMALLEST number in the PQ
        // co,p.reversed() will return the LARGEST number
        pq.addAll(list);

        while (pq.size() > k){
            pq.poll();
        }

        return pq.poll();
    }
    List<Integer> inOrderTraversal_pq_impl(TreeNode<Integer> node){
        if(node == null){
            return new ArrayList<>();
        }

        List<Integer> results = new ArrayList<>();
        if(node.left != null){
            results.addAll(inOrderTraversal_pq_impl(node.left));
        }
        results.add(node.data);

        if(node.right != null){
            results.addAll(inOrderTraversal_pq_impl(node.right));
        }

        return results;
    }

    /**
     * Solution summary
     * - Collect all nodes into a list
     * - stop collecting when list.size() == k
     * - return entry at end of list
     * TC: O(n)
     * SC: O(n)
     */
    int kSmallest_list_impl(TreeNode<Integer> root, int k){
        if(root == null){
            return 0;
        }

        //0. create list
        List<Integer> list = new ArrayList<>();
        inOrderTraversal(root, list);
        if(list.size() <= k){
            list.get(k - 1);
        }
        return list.get(k - 1);
    }
    void inOrderTraversal(TreeNode<Integer> node, List<Integer> list){
        if(node == null){
            return ;
        }

        if(node.left != null){
            inOrderTraversal(node.left, list);
        }
        list.add(node.data);

        if(node.right != null){
            inOrderTraversal(node.right, list);
        }
    }

    /**
     * Solution summary
     * - Define 2 vars (int result, and count)
     * - perform inOrder traversal (left, node, right)
     *      - after adding node.val, increment count
     *      - after increment count, check if it's equal to k. If true, return
     * - return result
     */
    int count = 0;
    int result = 0;
    int kthSmallest_impl2(TreeNode<Integer> root, int k){
        inOrder(root, k);
        return result;
    }
    void inOrder(TreeNode<Integer> node, int k){
        if(node == null){
            return;
        }

        inOrder(node.left, k);
        count++;
        if(count == k){
            result = node.data;
        }
        inOrder(node.right, k);
    }
}
