package com.tumuhairwe.prep.map;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Given a map with key that are either Strings, Integer or another Mpa
 * Determine if there's a cycle
 * e.g.
 *
 * London : Paris, Barcelona
 * Barcelona : Vienna, Milan
 * Paris : Lisbon, Rome
 * Paris : Tokyo, Amsterdam
 * Tokyo : Seoul
 * Lisbon : Rome, New York
 * Vienna : Budapest
 *
 * Figure out how to go from London to Budapest and return the route
 * i.e. London -> Barcelona -> Vienna -> Budapest
 *
 * Key1 : a
 * Key2.c : 122
 * key2.d.e = 777
 */
public class DetectCycleInAirportItineraries {

    public static void main(String[] args) {

    }

    Stack<String> visited = new Stack<>();
    int getDistance(String source, String destination, Map<String, List<String>> airports){

        if(airports.containsKey(source)){

        }

        for (String key : airports.keySet()){
            visited.push(source);

            List<String> destinations = airports.get(source);
            if(destinations.contains(destination)){
                return  1;
            }
            else return  1 + getDistance(source, destination, airports);
        }

        return 0;
    }
}
