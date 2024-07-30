package com.tumuhairwe.prep;

import java.util.TreeMap;

/**
 * LeetCode 2502 (medium)
 *
 * You are given an integer n representing the size of a 0-index memory array.
 * All memory units are initially free. And you have a memory allocator with the following constraints
 *
 * 1. Allocate a block of size consecutive free memory units and assign it the id mID.
 * 2. Free all memory units with the given id mID.
 *
 *
 */
public class DesignMemoryAllocator {

    // tracks start and end of currently free blocks (key: memId)
    private TreeMap<Integer, Integer> freeBlocks;

    // tracks memId and what's allocated to that memID (key: memId, val
}
