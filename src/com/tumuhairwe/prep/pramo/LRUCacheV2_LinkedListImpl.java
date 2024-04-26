package com.tumuhairwe.prep.pramo;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheV2_LinkedListImpl {
    int cacheCapacity;
    Map<Integer, LinkedListNode> cacheMap = new HashMap<>();
    KeyValuePairLL cacheList = new KeyValuePairLL();

    public LRUCacheV2_LinkedListImpl(int size){
        this.cacheCapacity = size;
    }

    int get(int key){
        LinkedListNode<Pair> iterator;

        if(cacheMap.containsKey(key)){
            iterator = cacheMap.get(key);
        }
        else return  -1;

        LinkedListNode<Pair> listIterator = iterator;
        cacheList.remove(iterator);
        cacheList.addFirst(listIterator);

        return listIterator.data.second;
    }

    void set(int key, int value){
        if(cacheMap.containsKey(key)){
            LinkedListNode<Pair> foundIterator = cacheMap.get(key);
            LinkedListNode<Pair> listIterator = foundIterator;

            cacheList.remove(foundIterator);
            cacheList.addFirst(listIterator);

            listIterator.data.second = value;
            return;
        }

        if(cacheMap.size() == cacheCapacity){
            int keyTemp = cacheList.getLast().data.first;
            cacheList.removeLast();;
            cacheMap.remove(keyTemp);
        }

        cacheList.insertAtHead(new Pair(key, value));

        if(cacheMap.containsKey(key)){
            cacheMap.replace(key, cacheList.getFirst());
        }
        else {
            cacheMap.put(key, cacheList.getFirst());
        }
    }
}
