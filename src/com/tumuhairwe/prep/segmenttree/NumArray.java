package com.tumuhairwe.prep.segmenttree;

class NumArray {

    private SegmentTree root;
    public NumArray(int[] nums) {
        if(nums.length > 0){
            root = build(nums, 0, nums.length -1);
        }
    }

    private SegmentTree build(int[] nums, int start, int end){
        SegmentTree node = new SegmentTree(start, end);
        if(start == end){
            node.sum = nums[start];
        }
        else{
            // recursively build tree
            int mid = start + (end - start) / 2;
            node.left = build(nums, start, mid);
            node.right = build(nums, mid + 1, end);

            // set sum
            node.sum = node.left.sum + node.right.sum;
        }

        return node;
    }

    public void update(int index, int value) {
        updateRecursive(root, index, value);
    }

    void updateRecursive(SegmentTree node, int index, int value){
        if(node.start == node.end){
            node.sum = value;
        }
        else{
            int mid = node.start + (node.end  - node.start)/2;
            if(index <= mid){
                updateRecursive(node.left, index, value);
            }
            else{
                updateRecursive(node.right, index, value);
            }

            node.sum = node.left.sum + node.right.sum;
        }
    }

    public int sumRange(int left, int right) {
        return sumRangeRecursive(root, left, right);
    }

    int sumRangeRecursive(SegmentTree node, int start, int end){
        if(node.start == start && node.end == end){
            return node.sum;
        }

        int mid = node.start + (node.end - node.start)/2;
        if(end <= mid){
            return sumRangeRecursive(node.left, start, end);
        }
        else if(start > mid){
            return sumRangeRecursive(node.right, start, end);
        }
        else{
            int leftSum = sumRangeRecursive(node.left, start, mid);
            int rightSum = sumRangeRecursive(node.left, mid + 1, end);
            return leftSum + rightSum;
        }
    }

    public static class SegmentTree{
        // children
        SegmentTree left;
        SegmentTree right;

        // boundaries
        int start;
        int end;

        // scalar (could be avg, sum, etc.)
        int sum;
        public SegmentTree(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}

