package com.tumuhairwe.prep.stack;

import java.util.Stack;

/**
 * LeetCode 155 (medium)
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 *
 * ref: https://www.youtube.com/watch?v=qkLl7nAwDPo
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0155-min-stack.java
 */
class Solution{
    public static void main(String[] args) {
        MinStackImpl2 s = new MinStackImpl2();
        s.push(-2);
        s.push(0);
        s.push(-3);

        //getMin
        System.out.println(s.getMin());

        //pop
        s.pop();

        //top
        System.out.println(s.top());

        //getMin
        System.out.println(s.getMin());
    }
}

/**
 * Solution Summary
 * - Track min by using 2 stacks (minStack simply exist to keep track of the min on top)
 * - onPush()
 *      - push onto regular stack
 *      - calculate value of minStack.peek() and val
 *      - push that val on minStack
 * - onPop()
 *      - pop from both stacks
 * - to getMin()
 *      - Always get from minStack
 * - on top()
 *      - return the too of the regular stack (minStack is only for accessing the min)
 */
class MinStackImpl2 {
    private Stack<Integer> regularStack;
    private Stack<Integer> mininumStack;

    public MinStackImpl2() {
        regularStack = new Stack<>();
        mininumStack = new Stack<>();
    }

    public void push(int val) {
        regularStack.push(val);

        val = mininumStack.isEmpty() ? val : mininumStack.peek();
        mininumStack.push(val);
    }

    public void pop() {
        mininumStack.pop();
        regularStack.pop();
    }

    public int top() {
        return regularStack.peek();
    }

    public int getMin() {
        return this.mininumStack.peek();
    }
}
