package com.tumuhairwe.prep;

import java.util.Arrays;

/**
 * LeetCode 641 (medium)
 *
 *  ref: https://leetcode.com/problems/design-circular-deque/solutions/3430742/java-array-simple-easy/
 */
public class CircularDequeue {

    private int[] queue;
    private int tailIdx;

    public CircularDequeue(int capacity){
        queue = new int[capacity];
        Arrays.fill(queue, -1);

        tailIdx = -1;
    }

    public boolean insertFront(int value){
        //0. check capacity
        boolean hasNoCapacity = tailIdx + 1 == queue.length;
        if(hasNoCapacity){
            return false;
        }

        //1. shift values forward to create space at front
        for (int i = tailIdx; i >= 0; i--) {
            queue[i + 1] = queue[i];
        }

        //2. place value at front
        queue[0] = value;
        tailIdx++;
        return true;
    }

    public boolean insertLast(int value){
        //0. check capacity
        boolean hasNoCapacity = tailIdx + 1 == queue.length;
        if(hasNoCapacity){
            return false;
        }

        //1. insert value
        tailIdx++;
        queue[tailIdx] = value;

        return true;
    }

    public boolean deleteFront(){
        boolean isHeadEmpty = queue[0] == -1;
        if (isHeadEmpty){
            return false;
        }

        //1. shift value forward to delete value at front
        for (int i = 0; i < tailIdx; i++) {
            queue[i] = queue[i + 1];
        }

        queue[tailIdx] = -1;
        tailIdx--;
        return true;
    }

    public boolean deleteLast(){
        boolean isTailEmpty = tailIdx == -1;
        if (isTailEmpty){
            return false;
        }

        queue[tailIdx] = -1;
        tailIdx--;
        return true;
    }

    public int getFront() {
        return queue[0];
    }

    public int getRear() {
        //0. check if tail is empty
        boolean isTailEmpty = tailIdx == -1;
        if(isTailEmpty){
            return -1;
        }

        //1. check if queue is full (avoid ArrayOutOfBoundsException
        boolean isQueueFull = tailIdx == queue.length;
        if (isQueueFull){
            return queue[tailIdx - 1];
        }

        return queue[tailIdx];
    }

    public boolean isFull(){
        boolean isTailOccupied = queue[queue.length - 1] != -1;
        return isTailOccupied;
    }
}
