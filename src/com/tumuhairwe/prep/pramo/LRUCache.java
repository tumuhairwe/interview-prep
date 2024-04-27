package com.tumuhairwe.prep.pramo;

class LinkedListNode {
    public int key;
    public int val;
    public LinkedListNode next;
    public LinkedListNode prev;

    public LinkedListNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
        this.prev = null;
    }
}
class LRUCache {
    public static void main(String[] args) {
        int cacheCapacity = 2;
        LRUCache cache = new LRUCache();
        System.out.println("Initial state of cache");
        System.out.println("Cache capacity: " + cacheCapacity);
        //cache.print();

        int[] keys = {10, 10, 15, 20, 15, 25, 5};
        String[] values = {"20", "get", "25", "40", "get", "85", "5"};

        for (int i = 0; i < keys.length; i++) {
            if (values[i] == "get") {
                System.out.println("Getting by Key: " + keys[i]);
                //System.out.println("Cached value returned: " + (cache.get(keys[i])));
            } else {
                System.out.println("Setting cache: Key: " + keys[i] + ", Value: " + values[i]);
                //cache.set(keys[i], Integer.parseInt(values[i]));
            }
            //cache.print();
        }
    }
}