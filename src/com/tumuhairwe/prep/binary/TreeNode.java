package com.tumuhairwe.prep.binary;

class TreeNode<T>{
    T data;
    TreeNode<T> left;
    TreeNode<T> right;
    TreeNode(T data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}