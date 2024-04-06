package com.tumuhairwe.prep.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a map with key that are either Strings, Integer or another Mpa
 * Flatten the map to produce a non-nested Map
 *
 * Flatten Dictionary
 *
 * e.g.
 *
 * Key1 : a
 * Key2 : {
 *     c: 122
 *     d : {
 *         e : 777
 *     }
 * }
 *
 * should produce
 *
 * Key1 : a
 * Key2.c : 122
 * key2.d.e = 777
 */
public class FlattenMap {

    static Map<String, String> flattenMap(Map<String, Object> e){
        Map<String, String> result = new HashMap<>();

        for (Map.Entry<String, Object> entry : e.entrySet()){
            Map<String, String> flattenedEntry = flattenEntry(entry);

            for (Map.Entry<String, String> fe : flattenedEntry.entrySet()){
                result.put(fe.getKey(), fe.getValue());
            }
        }

        return result;
    }
    static  Map<String, String> flattenEntry(Map.Entry<String, Object> entry){
        Map<String, String> allEntriesCollapsed = new HashMap<>();

        if(entry.getValue() instanceof String || entry.getValue() instanceof  Integer){
            allEntriesCollapsed.put(entry.getKey(), entry.getValue().toString());
        }
        else if(entry.getValue() instanceof Map) {
            String key = entry.getKey();

            for (Map.Entry innerEntry : ((Map<?, ?>) entry.getValue()).entrySet()){
                Map<String, String> returnedEntry = flattenEntry(innerEntry);
                for (Map.Entry<String, String> fe : returnedEntry.entrySet()){
                    allEntriesCollapsed.put(key + "." + fe.getKey(), fe.getValue());
                }
            }
        }

        return allEntriesCollapsed;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("Key1", "a");

        Map<String, Object> subMap2 = new HashMap<>();
        subMap2.put("e", 777);

        Map<String, Object> subMap1 = new HashMap<>();
        subMap1.put("d", subMap2);
        subMap1.put("C", 123);
        map.put("Key2", subMap1);

        Map<String, String> flattenedMap = flattenMap(map);
        System.out.println(flattenedMap);
    }
}
