package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 *
 * IMPORTANT:
 *  - Use Dijkstra for weighted graphs (where we're looking for cost e.g. distance)
 *  - Use BFS for UNweighted graphs (e.g. we're looking for count-of-nodes e.g. number ot stops
 *
 *  - BFS is an algorithm that traverses a graph in a breadth-first manner.
 *  - This means that the algorithm starts at the source node and then visits all the nodes that are one edge away from the source node.
 *  - The algorithm then visits all the nodes that are two edges away from the source node, and so on.
 *  - The algorithm terminates when all the nodes in the graph have been visited.
 *
 * Given n cities, connected by m flights
 * Each flights starts from a source-city to a destination-city
 *
 * LeetCode 787 (Medium)
 * - Given flights array where flights[i] = [from, to, price] , indicating the distance of a flight from 1 city to another
 * - And given source and destination, and K, find THE CHEAPEST FLIGHT/path with at most K stops.
 * - If there's no route, return -1;
 *
 * Solution
 * 1. Creat a graph/adjacency-list of [airport] -> [destination, distance]
 * 2. Maintain PQ that orders Nodes based on distance
 *    2a) Seed PQ with a route-to-self (source = self, distance=0, numStops = -1)
 * 3. While PQ is not empty of reachable from sourceAirport, create a node and add it to the PQ
 * 4. Iterate over PQ
 *      - if node.cityId == destination, return node.distance
 *      - for each node.cityId's destinations (from adjacency matrix), ... create a node and add to PQ
 *      - next iteration should pick pq.poll() == lowest distance ... and if it finds the cityId, we've found ourd findl destination
 * 5 return distance
 *
 * TC: O(V+E), where V is the number of vertices and E is the number of edges in the graph. (bcoz push() and pop() on a queue a O(1) )
 *
 * Dijkstra's shortest path
 * ref: https://www.youtube.com/watch?v=Nq8cUZAfIoM
 * ref: https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
 */
public class CheapestFlights {
    static class NewNode implements Comparable<Node>{
        int cost = Integer.MAX_VALUE;
        Map<Node, Integer> adjacentNodes = new HashMap<>();
        //List<NewNode> shortestPath = new ArrayList<>();

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.distance);
        }
    }

    public static void main(String[] args) {
        NewNode source = new NewNode();
        Set<NewNode> visitedNodes = new HashSet<>();
        Queue<NewNode> unVisitedNodes = new PriorityQueue<>(Collections.singleton(source));

        while (!unVisitedNodes.isEmpty()){
            NewNode currentNode  = unVisitedNodes.poll();
            currentNode.adjacentNodes
                    .entrySet()
                    .stream().filter(entry -> visitedNodes.contains(entry.getKey()))
                    .forEach(entry -> {
                        evalDistanceAndPath(entry.getValue(), entry.getKey(), currentNode);
                    });
        }
    }

    private static void evalDistanceAndPath(Integer weight, Node source, NewNode adjacentNode) {
        Integer newCost = source.distance + weight;
        if(newCost < adjacentNode.cost){
            adjacentNode.cost = newCost;
        }
    }

    /**
     * BFS = Find shortest path for unweighted graphs
     */
    public int findCheapestPrice_BFS(int numberOfCities, int[][] flightSchedule, int source, int targetDestination, int k_maxNumberOfStops){
        // 0. create adjacency list
        Map<Integer, List<Node>> city_to_destination_adjList = new HashMap<>();
        for (int[] flight  : flightSchedule) {
            Integer departingCity = flight[0];
            Integer destinationCity = flight[1];
            Integer priceToDestination = flight[2];

            // 1a) pre-fill the whole adj list
            if (!city_to_destination_adjList.containsKey(departingCity)) {
                city_to_destination_adjList.put(departingCity, new ArrayList<>());
            }
            city_to_destination_adjList.get(targetDestination).add(new Node(destinationCity, priceToDestination));
        }

        //2. set default distances[] from src to $destination
        int[] destinations = new int[numberOfCities];
        Arrays.fill(destinations, Integer.MAX_VALUE);

        // 3. build a queue to store the Nodes/Cities, sorted by HIGHEST distance
        /**
         * - uses simple FIFO queue -> so the next node to visit is the first node added to the queue.
         * - BFS doesn't fail if a cycle is detected
         */
        Queue<Node> queue = new LinkedList<>(); //
        queue.offer(new Node(source, 0));
        int numStops = 0;

        while (numStops <  k_maxNumberOfStops && !queue.isEmpty()){
            int qDepth = queue.size();

            // 4. iterate on current level
            while (qDepth-- > 0){
                Node currentNode = queue.poll();

                if (!city_to_destination_adjList.containsKey(currentNode.cityId)){
                    continue;   // we haven't reached it yet ... might be an intermediate destination
                }

                // 5. loop over neighbors of current/popped node
                //  -> compute distance & add to global [] of distances
                //  -> add neighbor to queue
                for (Node neighbor : city_to_destination_adjList.get(currentNode.cityId)){
                    if(neighbor.distance + currentNode.distance >= destinations[neighbor.cityId]){
                        continue;
                    }
                    destinations[neighbor.cityId] = currentNode.distance + neighbor.distance;
                    queue.offer(new Node(neighbor.cityId, destinations[neighbor.distance]));
                }
            }

            numStops++;
        }
        return destinations[targetDestination] == Integer.MAX_VALUE ? -1 : destinations[targetDestination];
    }

    /**
     * Dijkstra = Finds shortest path for weighted graphs
     */
    public int findCheapestPrice_wrong_dijkstra(int numberOfCities, int[][] flights, int source, int destination, int k_stops){
        // 0. base case
        if(flights.length == 0){
            return -1;  // there's no destination
        }

        // 1. Create adjacency list
        // key= city_id, value = List<Destination_City, CostToThatCity>
        Map<Integer, List<Integer[]>> city_to_destination_graph = new HashMap<>();
        for (int[] flight : flights){
            Integer departingCity = flight[0];
            if(!city_to_destination_graph.containsKey(departingCity)){
                city_to_destination_graph.put(departingCity, new ArrayList());
            }

            int destinationCity = flight[1];
            int costToDestination = flight[2];
            city_to_destination_graph.get(departingCity).add(new Integer[]{destinationCity, costToDestination});
        }

        // 2. Create PQ to track lowest distance

        /**
         * Dijstra's doesn't visit all nodes .. just find the shortest path from source to destination
         * use PQ to pull node with the lowest cost
         */
        Comparator<Node> comp = Comparator.comparingInt((Node a) -> a.distance);    // only necessary if node doesn't implement Comparable
        Comparator<Node> reverseComp = comp.reversed(); // this comparator will sort by HIGHEST distance

        // use Queue to track the intermediate airports
        // build a queue to store the Nodes/Cities,
        Queue<Node> queue = new PriorityQueue<>(reverseComp);   // sorted by HIGHEST distance
        Queue<Node> queue_minHeap = new PriorityQueue<>(comp);          // sorted by LOWEST distance

        // 2a) Seed PQ with route to self
        queue.add(new Node(source, 0));
        int totalCost = 0;

        while (!queue.isEmpty()){
            Node currentCity = queue.poll();
            //int numStops = currentCity.stop;    // stopCount

            int numStops = 1;

            if(currentCity.cityId == destination && numStops == k_stops){
                totalCost += currentCity.distance;;
                return totalCost;
                //return currentCity.distance;
            }

            if(numStops < k_stops){
                List<Integer[]> nextStops = city_to_destination_graph.getOrDefault(currentCity, new ArrayList<>());

                for (Integer[] stop : nextStops){
                    int cityId = stop[0];
                    int destinationCity = stop[1];
                    //int destinationStops = currentCity.stop + 1;
                    Node node = new Node(cityId, currentCity.distance);
                    queue.add(node);
                }
            }
        }

        // we never found cityId == destination
        return -1;
    }

    record  Node(int cityId, int distance) implements Comparable<Node>{
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
}
