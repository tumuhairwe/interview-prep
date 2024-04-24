package com.tumuhairwe.prep.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TreeMap -> Main function is to maintain order by keeping the keys in a sorted manner (must be Comparable)
 *      -> Insertion and lookup == O (log_n)
 *      -> does not allow null key ... but allows null values
 * LinkedHashMap -> Main function is maintain FIFO order
 *      -> Insertion and lookup ==  O(1)
 *      -> Only allows a single null key
 */
public class OrderedHashMap<K, V> extends HashMap<K, V> {
    private final Comparator<? extends V> comparator;

    public OrderedHashMap(Comparator<? extends V> comparator) {
        this.comparator = comparator;
    }

    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }

    public Map<K, V> getSortedMap(){
        Map<K, V> sortedMap = new LinkedHashMap<>();
        entrySet()
                .stream()
                .forEach(kvEntry -> sortedMap.put(kvEntry.getKey(), kvEntry.getValue()));

        for (Entry<K, V> entry : entrySet()){
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static void main(String[] args) {
        OrderedHashMap<String, Integer> orderedHashMap = new OrderedHashMap<>(Comparator.comparing(Integer::intValue));
        orderedHashMap.put("one", 1);
        orderedHashMap.put("two", 2);
        orderedHashMap.put("three", 3);

        Map<String, Integer> sortedMap = orderedHashMap.getSortedMap();

        for (Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
