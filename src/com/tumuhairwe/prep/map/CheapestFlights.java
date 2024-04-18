package com.tumuhairwe.prep.map;

import java.util.*;

/**
 * LeetCode 787 (Medium)
 * Given n cities, connected by m flights
 * Each flights starts from a source-city to a destination-city
 *
 * - Given flights array where flights[i] = [from, to, price] , indicating the cost of a flight from 1 city to another
 * - And given source and destination, and K, find THE CHEAPEST FLIGHT/path with at most K stops.
 * - If there's no route, return -1;
 *
 * Solution
 * 1. Creat a graph/adjacency-list of [airport] -> [destination, cost]
 * 2. Maintain PQ that orders Nodes based on cost
 *    2a) Seed PQ with a route-to-self (source = self, cost=0, numStops = -1)
 * 3. While PQ is not empty reachable from sourceAirport, create a node and add it to the PQ
 * 4. Iterate over PQ
 *      - if node.cityId == destination, return node.cost
 *      - for each node.cityId's destinations (from adjacency matrix), ... create a node and add to PQ
 *      - next iteration should pick pq.pol() == lowest cost ... and if it finds the cityId, we've found ourd findl destination
 * 5 return cost
 *
 * Dijkstra's shortest path
 * ref: https://www.youtube.com/watch?v=Nq8cUZAfIoM
 * ref: https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
 */
public class CheapestFlights {

    public int findCheapestPrice(int n, int[][] flights, int source, int destination, int k_stops){
        // key= city_id, value = List<Destination_City, CostToThatCity>
        Map<Integer, List<Integer[]>> city_to_destination_graph = new HashMap<>();
        //Queue<Map.Entry<String, String>> q = new PriorityQueue<>();

        // use PQ to track lowest cost
        // use Queue to track the intermediate airpotrs

        if(flights.length == 0){
            return -1;  // there's no destination
        }

        for (int[] flight : flights){
            Integer departingCity = flight[0];
            if(!city_to_destination_graph.containsKey(departingCity)){
                city_to_destination_graph.put(departingCity, new ArrayList());
            }

            city_to_destination_graph.get(departingCity).add(new Integer[]{flight[1], flight[2]});
        }

        // build a queue to store the Nodes/Cities, sorted by cost
        Comparator<Node> comp = Comparator.comparingInt((Node a) -> a.cost);
        // Comparator<Node> comp = (Node a, Node b) -> a.cost - b.cost;

        PriorityQueue<Node> queue = new PriorityQueue<>(comp);
        // add base value
        queue.add(new Node(source, 0, -1));     // not going anywhere
        int cost = 0;

        while (!queue.isEmpty()){
            Node currentCity = queue.poll();
            if(currentCity.cityId == destination){
                cost += currentCity.cost;;
                return cost;
                //return currentCity.cost;
            }

            int numStops = currentCity.stop;    // stopCount
            if(numStops < k_stops){
                List<Integer[]> nextStops = city_to_destination_graph.getOrDefault(currentCity, new ArrayList<>());
                for (Integer[] stop : nextStops){
                    int cityId = stop[0];
                    Node node = new Node(stop[0], currentCity.cost + stop[1], currentCity.stop + 1);
                    queue.add(node);
                }
            }
        }

        return -1;
    }

    class Node{
        int cityId;
        int cost;
        int stop;
        public Node(int cityId, int cost, int stop){
            this.cityId = cityId;
            this.stop = stop;
            this.cost = cost;
        }
    }
}
