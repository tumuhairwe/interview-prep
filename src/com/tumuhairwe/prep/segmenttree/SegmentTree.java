package com.tumuhairwe.prep.segmenttree;

/**
 * Given an integer array nums. Handle multiple queries as follows
 * - update(int index, int val) - updates the value of the element in numbs
 * - calculate the sum of the elements of nums between indices lewft and right (inclusive)
 *
 * LeetCode 307
 * ref: https://leetcode.com/problems/range-sum-query-mutable/description/
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
    public static SegmentTree build(int[] nums, int L, int R){
        if (L == R){
            return new SegmentTree(nums[L],L, R );
        }

        int mid = (L + R) / 2;
        SegmentTree root = new SegmentTree(0, L, R);
        root.left = build(nums, L, mid);
        root.right = build(nums, mid + 1, R);
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
