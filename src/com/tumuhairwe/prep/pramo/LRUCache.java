package com.tumuhairwe.prep.pramo;


import java.util.HashMap;
//import java.util.LinkedList;

class Pair {
    public int first;
    public int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

// We will use a linkedlist of a pair of integers
// where the first integer will be the key
// and the second integer will be the value
class KeyValuePairLL extends LinkedList<Pair> {
};
class LinkedListNode<T> {
    public T data;
    public LinkedListNode<T> next;
    public LinkedListNode<T> prev;

    public LinkedListNode(T dataVal) {
        this.data = dataVal;
        this.next = null;
        this.prev = null;
    }
}
class LRUCache {
    int cacheCapacity;

    HashMap<Integer, LinkedListNode<Pair>> cacheMap = new HashMap<Integer, LinkedListNode<Pair>>();

    KeyValuePairLL cacheList = new KeyValuePairLL();

    // Constructor that sets the size of the cache
    public LRUCache(int size) {
        this.cacheCapacity = size;
    }

    int get(int key) {
        LinkedListNode<Pair> foundIter;

        if (cacheMap.containsKey(key))
            foundIter = cacheMap.get(key);
        else
            return -1;

        LinkedListNode<Pair> listIterator = foundIter;

        cacheList.remove(foundIter);
        cacheList.addFirst(listIterator);

        return listIterator.data.second;
    }

    void set(int key, int value) {
        if (cacheMap.containsKey(key)) {
            LinkedListNode<Pair> foundIter = cacheMap.get(key);
            LinkedListNode<Pair> listIterator = foundIter;

            cacheList.remove(foundIter);
            cacheList.addFirst(listIterator);

            listIterator.data.second = value;
            return;
        }

        if (cacheMap.size() == cacheCapacity) {
            int keyTmp = cacheList.getLast().data.first;
            cacheList.removeLast();
            cacheMap.remove(keyTmp);
        }

        cacheList.insertAtHead(new Pair(key, value));

        if (cacheMap.containsKey(key)) {
            cacheMap.replace(key, cacheList.getFirst());
        } else {
            cacheMap.put(key, cacheList.getFirst());
        }
    }

    // Prints the current state of the cache
    void print() {
        System.out.print("Cache current size: " + cacheList.size() + ", ");
        System.out.print("Cache contents: {");
        LinkedListNode<Pair> iter = cacheList.getFirst();
        while (iter != null) {
            Pair pair = iter.data;
            System.out.print("{" + pair.first + ": " + pair.second + "}");
            iter = iter.next;
            if (iter != null)
            {
                System.out.print(", ");
            }
        }

        System.out.print("}\n");
        System.out.println(new String(new char[100]).replace('\0', '-'));
    }

    public static void main(String[] args) {
        int cacheCapacity = 2;
        LRUCache cache = new LRUCache(cacheCapacity);
        System.out.println("Initial state of cache");
        System.out.println("Cache capacity: " + cacheCapacity);
        cache.print();

        int[] keys = {10, 10, 15, 20, 15, 25, 5};
        String[] values = {"20", "get", "25", "40", "get", "85", "5"};

        for (int i = 0; i < keys.length; i++) {
            if (values[i] == "get") {
                System.out.println("Getting by Key: " + keys[i]);
                System.out.println("Cached value returned: " + (cache.get(keys[i])));
            } else {
                System.out.println("Setting cache: Key: " + keys[i] + ", Value: " + values[i]);
                cache.set(keys[i], Integer.parseInt(values[i]));
            }
            cache.print();
        }
    }
}