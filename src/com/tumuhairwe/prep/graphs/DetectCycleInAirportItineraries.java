package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * Given a map with key that are either Strings, Integer or another Mpa
 * Determine if there's a cycle
 * e.g.
 *
 * London : Paris, Barcelona
 * Barcelona : Vienna, Milan
 * Paris : Lisbon, Rome
 * -- Paris : Tokyo, Amsterdam
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
        // does not  have cycle -> has path
        visited = new Stack<>();
        Map<String, List<String>> grid = new HashMap<>();
        grid.put("London", List.of("Paris", "Barcelona"));
        grid.put("Barcelona", List.of("Vienna", "Milan"));
        grid.put("Paris", List.of("Lisbon", "Rome"));
        grid.put("Tokyo", List.of("Seoul"));
        grid.put("Lisbon", List.of("Rome", "New York"));
        grid.put("Vienna", List.of("Budapest"));

        String source = "London";
        String destination = "Budapest";
        double distance = getDistance(source, destination, grid);
        printResult(source, destination, distance);

        // does not have path
        visited = new Stack<>();
        grid = new HashMap<>();
        source = "Nairobi";
        destination = "Abidjan";
        grid.put("Nairobi", List.of("Kampala", "Lagos"));
        grid.put("Kampala", List.of("Kigali"));
        distance = getDistance(source, destination, grid);
        printResult(source, destination, distance);

        // has cycle
        visited = new Stack<>();
        grid = new HashMap<>();
        source = "Rio de Janeiro";
        destination = "Bogota";
        grid.put("Rio de Janeiro", List.of("Chile", "Kingston"));
        grid.put("Kingston", List.of("Kigali"));
        grid.put("Bogota", List.of("Port au Prince", "Rio de Janeiro"));

        distance = getDistance(source, destination, grid);
        printResult(source, destination, distance);
    }

    private static void printResult(String source, String destination, double distance) {
        if(distance == Double.POSITIVE_INFINITY){
            System.out.println("There is no path between " + source + " and " + destination);
        }
        else if(distance == Double.NEGATIVE_INFINITY){
            System.out.println("There is a cyclic dependency between " + source + " and " + destination);
        }
        else {
            System.out.println("The distance between " + source + " and " + destination + " is " + distance + " units");
        }
    }

    static Stack<String> visited;
    private static final int UNKNOWN = -1;

    /**
     * Gets distance between source & destination
     * Use BFS when given source + destination tasks
     */
    static double getDistance(String source, String destination, Map<String, List<String>> adjList_of_airports){

        if(!adjList_of_airports.containsKey(source)){
            return UNKNOWN;
        }

        if(visited.contains(destination)){
            return Integer.MIN_VALUE;   // has cycle
        }
//        else for (String key : adjList_of_airports.get(source)){
//            visited.push(key);
//            return 1 + getDistance(key, destination, adjList_of_airports);
//        }

        for (String key : adjList_of_airports.keySet()){
            List<String> destinations = adjList_of_airports.get(source);
            if(destinations.contains(destination)){
                return  1;
            }
            else {
                visited.push(key);
                return  1 + getDistance(key, destination, adjList_of_airports);
            }
        }

        return 0;
    }
}
