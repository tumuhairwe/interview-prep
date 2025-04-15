package com.tumuhairwe.prep.pramp;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * LeetCode 281 (medium)
 * Given 2 vectors of itegers v1 and v2, implement an iterators to return their elements alternately.
 * Implement the class ZigzagIterator
 * - constructor(List<int> v1, List<int> v2) initializes the object with 2 vectors
 * - boolean hasNext() returns true if the iterator still has elements and false otherwise
 * - int next() return the current element of the iterator and moves the iterator to the next elements
 *
 * ref: https://leetcode.com/problems/zigzag-iterator/description/?envType=company&envId=coinbase&favoriteSlug=coinbase-thirty-days
 */
public class ZigzagIterator {
    private LinkedList<Iterator<Integer>> list;

    /**
     * Solution summary
     * - Add both iterators to LinkedList of Iterators (maintains order or insertion)
     * - hasNext() -> return if list is NOT empty
     * - next() -> pull from linkedList, save next() as a val into "result" var,
     *          -> if recently pulled list is not empty, add it back to linkedList
     *          -> return result
     */
    public ZigzagIterator(Iterator<Integer> v1, Iterator<Integer> v2){
        if(v1.hasNext()){
            list.add(v1);
        }
        if(v2.hasNext()){
            list.add(v2);
        }
    }

    public boolean hasNext(){
        return !list.isEmpty();
    }

    public Integer next(){
        Iterator<Integer> poll = list.remove();
        Integer result = poll.next();

        if(poll.hasNext()){
            list.add(poll);
        }
        return result;
    }
}
