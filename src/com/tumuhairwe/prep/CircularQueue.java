package com.tumuhairwe.prep;

/**
 * LeetCode 622 (medium)
 *
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle, and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 *
 * Implement the MyCircularQueue class:
 *
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 * You must solve the problem without using the built-in queue data structure in your programming language.
 *
 * ref: https://www.youtube.com/watch?v=aBbsfn863oA
 * ref: https://leetcode.com/problems/design-circular-queue/solutions/149420/concise-java-using-array/?envType=company&envId=crowdstrike&favoriteSlug=crowdstrike-three-months
 */
public class CircularQueue {
    private int[] queue;
    private int headIndex;
    private int tailIndex;
    private int length;

    /**
     * Solution summary
     * - initialize que
     * - set headIndex = 0;
     * - set tailIndex = -1
     */
    public CircularQueue(int k){
        queue = new int[k];
        headIndex = 0;
        tailIndex = -1;
    }
    public boolean enQueue(int val){
        if(isFull()){
            return false;
        }
        int idx = (tailIndex + 1) % queue.length;
        queue[idx] = val;
        headIndex++;
        length++;
        return true;
    }
    public boolean deQueue(){
        if(isEmpty()){
            return false;
        }
        headIndex = (headIndex + 1) % queue.length;
        //queue[idx] = null;
        length--;
        tailIndex--;
        return true;
    }
    public int front(){
        if (isEmpty()){
            return -1;
        }
        return queue[headIndex];
    }
    public int rear(){
        if (isEmpty()){
            return -1;
        }

        return queue[tailIndex];
    }
    // true if length == 0
    public boolean isEmpty(){
        return length == 0;
    }
    // true if length == q.length
    public boolean isFull(){
        return length == queue.length;
    }
}
