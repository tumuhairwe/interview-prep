package com.tumuhairwe.prep.lists;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 341
 *
 *  You are given a nested list of integers nestedList. Each element is either an integer or a list
 *  whose elements may also be integers or other lists. Implement an iterator to flatten it.
 *
 * ref: https://leetcode.com/problems/flatten-nested-list-iterator/description/
 */
class NestedIterator implements Iterator<Integer> {

    private Queue<NestedInteger> queue;
    public NestedIterator(List<NestedInteger> list){
        this.queue = new LinkedList<>();
        dfs_setup(list);
    }

    private void dfs_setup(List<NestedInteger> list) {
        for (NestedInteger ni : list){
            if(ni.isInteger()){
                queue.add(ni);
            }
            else {
                dfs_setup(ni.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return queue.poll().next();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
interface NestedInteger{
    int next();
    boolean isInteger();
    List<NestedInteger> getList();
}
