package com.tumuhairwe.prep;

import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 2502 (medium)
 * (Similar to 359 (easy) )
 * You are given an integer n representing the size of a 0-index memory array.
 * All memory units are initially free. And you have a memory allocator with the following constraints
 *
 * 1. Allocate a block of size consecutive free memory units and assign it the id mID.
 * 2. Free all memory units with the given id mID.
 *
 * ref: https://leetcode.com/problems/design-memory-allocator/
 */
public class Allocator {

    // tracks start and end of currently free blocks (key: memId)
    private TreeMap<Integer, Integer> freeBlocks;

    // tracks memId and what's allocated to that memID (key: memId, val: {key: start, val: end})
    private TreeMap<Integer, TreeMap<Integer, Integer>> allocatedBlocks;

    public Allocator(int n){
        //0. create and init vars
        freeBlocks = new TreeMap<>();
        freeBlocks.put(0, n);

        allocatedBlocks = new TreeMap<>();
    }

    // allocate & return the block's 1st index, if it exists, else -1

    /**
     * Solution summary
     * - for each unoccupiedEntry (entry in freeBlock)  ... if it has capacity < size ... skip
     * - find the 1st available block with capacity ....
     *      - initialize allocatedBlock with empty map as value
     *      - UPDATE allocatedBlock of that memId with (endIndex and capacity) as key/value
     *      - REMOVE endIndex from freeBlacks (since its now not free anymore)
     *      - calculate the newStartIndex and put in the freeBlock (if the new space is smaller than existing space)
     */
    public int allocate(int size, int memId){
        // find a freeBlock ... allocate
        for (Map.Entry<Integer, Integer> unoccupiedEntry : freeBlocks.entrySet()){
            int startIndex = unoccupiedEntry.getKey();
            int endIndex = unoccupiedEntry.getValue();

            int capacity = endIndex - startIndex;
            if(capacity >= size) {
                allocatedBlocks.putIfAbsent(memId, new TreeMap<>());
                allocatedBlocks.get(memId).put(endIndex, capacity);

                // remove from previously allocated freeBlock
                freeBlocks.remove(endIndex);

                // mark as free ... the available space that is not used
                int newStartIndex = startIndex + size;
                if (newStartIndex < endIndex) {
                    freeBlocks.put(newStartIndex, endIndex);
                }

                return startIndex;
            }
        }
        return -1;  // if we got here .. we didn't find enough space
    }

    public int free(int memId){
        int totalFreedSpace = 0;
        for (Map.Entry<Integer, Integer> allocatedEntry : allocatedBlocks.get(memId).entrySet()){
            int startIndex = allocatedEntry.getKey();
            int endIndex = allocatedEntry.getValue();

            totalFreedSpace += endIndex - startIndex;

            // if there's a freeBlock that at the starts at the left most -> find it, unFree it, update freeable endIndex
            if(freeBlocks.containsKey(endIndex)){
                int rightEnd = freeBlocks.get(memId);
                freeBlocks.remove(endIndex);
                endIndex = rightEnd;
            }

            // if there's freeBlock @ startIndex -> unFree it (remove from freeBlock)
            Map.Entry<Integer, Integer> leftMostFreeEntry = freeBlocks.floorEntry(startIndex);
            if(leftMostFreeEntry != null && leftMostFreeEntry.getValue() == startIndex){
                startIndex = leftMostFreeEntry.getKey();
                freeBlocks.remove(leftMostFreeEntry.getKey());
            }

            // save the blocked space in the freeBlock map
            freeBlocks.put(startIndex, endIndex);
        }

        allocatedBlocks.remove(memId);
        return totalFreedSpace;
    }
}
