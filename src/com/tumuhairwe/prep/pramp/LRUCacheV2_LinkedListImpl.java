package com.tumuhairwe.prep.pramp;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 146 (medium)
 *
 * Implement LRU Cache that's
 * - initialized with a positive size (initialCapacity)
 * - get(int key) - should return the value of the key if it exists, otherwise return -1
 * - put(int key, int value) should update the value of the key if exists.
 * Otherwise, add the key-value to the cache.
 * If the number of the keys exceeds the capacity from this operation, evict the least recently used key
 *
 *  Solution Summary
 *  - initialize { int capacity, Map cacheMap, and Node head, and Node tail}
 *      - int capacity only track allowed capacity
 *      - head points to head --> useful w
 *  - implement get(key) ->
 *      - if key !exists in cacheMap, return NOT_FOUND;
 *      - else node = cacheMap.get(key)
 *      - remove(node)
 *      - re-add(node) --> will add node to the tail thereby making it the most recently accessed
 *      - return node.value
 *  - implement put(key, value)
 *      - if key exists, remove it (will remove by just changing pointers)
 *      - Create a new Node & add it to cache/map
 *      - re-size to evict entities that exceedd capacity by
 *          - iteratively call remove()
 *          - removing the key from the map
 *
 * ref: https://leetcode.com/problems/lru-cache/description/
 * ref: https://www.youtube.com/watch?v=et-yvRZ6nww
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0146-lru-cache.java
 */
public class LRUCacheV2_LinkedListImpl {
    int cacheCapacity;
    Map<Integer, LinkedListNode> cacheMap;
    LinkedListNode head;
    LinkedListNode tail;

    public LRUCacheV2_LinkedListImpl(int size){
        this.cacheCapacity = size;
        this.cacheMap = new HashMap<>();
        this.head = new LinkedListNode(-1, -1);
        this.tail = new LinkedListNode(-1, -1);

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * TC: O( 1 )
     * - Checking the hasMap = O(1)
     * - remove = O(1) -- since its just changing pointers
     * - add = O(1) -- since its just caching pointers to make tail.prev = nodeBeingAdded
     */
    int get(int key) {
        if (!this.cacheMap.containsKey(key)) {
            return -1;
        }
        LinkedListNode node = this.cacheMap.get(key);
        remove(node);   // remove from current position
        add(node);      // add it to the last position
        return node.val;
    }

    /**
     * TC: O(1)
     * - Checking if exists in map = O(1)
     * - adding node to map = O(1)
     * - adding to tail = O(1)
     */
    public void put(Integer key, Integer value){
        // 0. if exists, remove
        if(this.cacheMap.containsKey(key)){
            LinkedListNode oldNode = cacheMap.get(key);
            remove(oldNode);
        }

        // 1. add
        LinkedListNode node = new LinkedListNode(key,value);
        cacheMap.put(key, node);
        add(node);

        // 2. resize
        if(this.cacheMap.size() > cacheCapacity){
            LinkedListNode leastRecentlyUsed = this.head.next;
            remove(leastRecentlyUsed);
            cacheMap.remove(leastRecentlyUsed.key);
        }
    }
    private void remove(LinkedListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    // will make previous tail be this node, and node.next point to tail instead (+ updating tail.prev point to this node)
    // i.e. insert this node in the middle
    private void add(LinkedListNode node){
        LinkedListNode previousEnd = this.tail.prev;
        previousEnd.next = node;
        node.prev = previousEnd;
        node.next = this.tail;
        this.tail.prev = node;
    }
}
