package com.tumuhairwe.prep.stack;

import java.util.Stack;

/**
 * Design a stack that supports push(), pop(), retrieving the minimum element in constant time
 *
 * ref: https://www.youtube.com/watch?v=WxCuL3jleUA
 * ref: https://leetcode.com/problems/min-stack/description/
 */
public class MinStack {

    private int[] elements;
    private int _lastOccupiedSpace;

    private Stack<Integer> regularStack = new Stack<>();
    private Stack<Integer> newMinimumValues = new Stack<>();    // other strategy is to keep a minimum_variable and update it when you pop or push()

    public MinStack(int initialCapacity){
        this.elements = new int[initialCapacity];
        this._lastOccupiedSpace = 0;
    }
    private void resize(int newDepth){
        int[] _newElements = new int[newDepth];
        for (int i = 0; i < newDepth; i++) {
            if(elements.length < newDepth){
                _newElements[i] = this.elements[i];
            }
        }

        this.elements = _newElements;
    }
    public void push(int num){
        //this.elements[_lastOccupiedSpace] = num;
        //this._lastOccupiedSpace++;
        if(newMinimumValues.isEmpty() || num <= newMinimumValues.peek()){
            newMinimumValues.push(num);
        }
        this.regularStack.push(num);
    }
    public int peek(){
        return this.elements[_lastOccupiedSpace];
    }
    public int pop(){
        if(regularStack.peek() == newMinimumValues.peek()){
            this.newMinimumValues.pop();
        }
        return this.regularStack.pop();
        //this._lastOccupiedSpace--;
    }
    public int top(){
        //return this.peek();
        return this.regularStack.peek();
    }
    public int getMin(){
        return this.newMinimumValues.peek();
    }
}
