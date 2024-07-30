package com.tumuhairwe.prep.segmenttree;

/**
 * Given an integer array nums. Handle multiple queries as follows
 * - update(int index, int val) - updates the value of the element in numbs
 * - calculate the sum of the elements of nums between indices left and right (inclusive)
 *
 * LeetCode 307
 * ref: https://leetcode.com/problems/range-sum-query-mutable/description/
 *
 * A segment tree is a binary tree where each node represents and interval. Generally a node woiuld store one or more
 * properties of an interval which can be queried later
 *
 * ref: https://leetcode.com/articles/a-recursive-approach-to-segment-trees-range-sum-queries-lazy-propagation/
 */
public class SegmentTree {

    private int sum;
    private SegmentTree left;   // left subtree
    private SegmentTree right;  // right subtree
    private int leftTotal;
    private int rightTotal;

    public SegmentTree(int total, int L, int R){
        this.sum = total;
        this.left = null;
        this.right = null;
        this.leftTotal = L;
        this.rightTotal = R;
    }

    // TC = I(n)
    public static SegmentTree build(int[] nums, int left, int right){
        if (left == right){
            return new SegmentTree(nums[left], left, right );
        }

        int mid = (left + right) / 2;
        SegmentTree root = new SegmentTree(0, left, right);
        root.left = build(nums, left, mid);
        root.right = build(nums, mid + 1, right);
        root.sum = root.left.sum + root.right.sum;
        return root;
    }

    // TC = O(log n)
    public void update(int index, int val){
        if(this.leftTotal == this.rightTotal){
            this.sum = val;
            return;
        }

        int mid = (leftTotal + rightTotal) / 2;
        if(index > mid){
            right.update(index, val);
        }
        else {
            left.update(index, val);
        }

        sum = left.sum = right.sum;
    }

    // O(log n)
    public int rangeQuery(int leftBoundary, int rightBoundary){
        if(leftBoundary > this.leftTotal && rightBoundary == this.rightTotal){
            return this.sum;
        }

        int mid = (leftTotal + rightTotal) / 2;
        if(leftBoundary > mid){
            return right.rangeQuery(leftBoundary, rightBoundary);
        }
        else if(rightBoundary <= mid){
            return this.left.rangeQuery(leftBoundary, rightBoundary);
        }

        return left.rangeQuery(leftBoundary, mid) + right.rangeQuery(mid + 1, rightBoundary);
    }
}
