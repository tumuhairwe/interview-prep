package com.tumuhairwe.prep;

import java.util.Stack;

/**
 * LeetCode 232 (easy)
 *
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the
 * functions of a normal queue (push, peek, pop, and empty).
 *
 * Solution Summary
 * - Initialize 2 stacks (primary & secondary)
 * - on push(int x) -> push value to the bottom of primary
 *      -> copy + paste values from primary to secondary
 *      -> push value into primary
 *      -> copy + paste values from secondary back into primary
 * ref: https://github.com/Cee/Leetcode/blob/master/232%20-%20Implement%20Queue%20using%20Stacks.java
 * ref:  https://www.youtube.com/watch?v=JnR94AEVV-U
 * ref: https://github.com/Cee/Leetcode/blob/master/232%20-%20Implement%20Queue%20using%20Stacks.java
 */
public class QueueImplUsingStacks {

    private Stack<Integer> primary;
    private Stack<Integer> secondary;

    public QueueImplUsingStacks(){
        this.primary = new Stack<>();
        this.secondary = new Stack<>();
    }

    // push element to end of queue
    public void push(int x){
        // 0. copy + paste elements from primary to secondary
        while (!primary.isEmpty()){
            secondary.push(primary.pop());
        }

        // 1. primary onto primary
        primary.push(x);

        // 2. copy + paste elements back from secondary to primary
        while (!secondary.isEmpty()){
            primary.push(secondary.pop());
        }
    }

    public int pop(){
        return this.primary.pop();
    }

    public int peek(){
        return primary.peek();
    }

    public boolean empty() {
        return primary.isEmpty();
    }
}