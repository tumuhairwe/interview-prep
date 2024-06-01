package com.tumuhairwe.prep.graphs;



import java.util.*;

/**
 * Definition:
 * - Dijkstra's algorithm is a Single Source Shortest Path (SSSP) search that uses a greedy approach to calculate the shortest path
 * from a given source to all other vertices.
 * - Dijkstra's algorithm doesn't calculate all paths, but stops when it finds the shortest path.
 * - Uses  PQ (queue implementation) that pulls the node with the lowest cost
 * - Find the distance from source-node to all other nodes (where as BFS finds count-of-steps between source and destination
 *
 * Main points
 * - used to find the shortest path between two nodes in a weighted graph
 * - It uses a priority queue to track the nodes that have been visited and the nodes that still need to be visited.
 * - The algorithm terminates when the destination node is reached.
 * - The algorithm works by starting at the source node and then greedily adding the node with the shortest path to the priority queue.
 *
 * Solution summary
 * - Definition: An algorithm to find the shortest path
 * - Steps
 *  - Create adjacency-list of all nodes (Map: key=nodeId, value=[ {neighborNodeId}, {weight} ]
 *  - Create minHeap/PQ ordered by weight
 *  - Seed minHeap with sourceId + 0 distance
 *  - Populate minHeap (while iterating & getting destinations from adjList.get(sourceNodeId) until all destinations are exhausted
 *  - Return adjList Map { key=nodeId, value = weight-to-destination }
 *
 *  TC: O(V + E(log V)) bcoz PQ has O(log_n) tc for push() and pop() operations
 *
 *  ref: https://dzone.com/articles/from-dijkstra-to-a-star-a
 *  ref: https://www.baeldung.com/cs/graph-algorithms-bfs-dijkstra
 *  ref: https://neetcode.io/courses/advanced-algorithms/14
 */
public class Dijkstra {

    /**
     * As you can see, the first two steps are to initialize the distances with a large value and to add the source node to the queue.
     * Next, we perform iterations until the queue becomes empty. In each iteration, we extract a node from the queue that
     * has the shortest distance from the source node.
     *
     * After that, we visit all the neighbors of the extracted node and check the new distance we were able to achieve.
     * If the new distance is better than the old one, we update the distance of this node and push it to the queue.
     * Finally, the algorithm moves to perform one more iteration until the queue becomes empty.
     *
     * After the algorithm ends, we’ll have the shortest paths from the source node to all other nodes in the graph.
     */
    public static Map<Integer,Integer> getShortestPath(int[][] edges, int numberOfNodes, int src){
        // 0. initialized Adjacency list
        Map<Integer, List<Integer[]>> adjacencyList = new HashMap<>();
        for (int nodeId = 0; nodeId < numberOfNodes; nodeId++) {
            adjacencyList.put(nodeId, new ArrayList<>());
        }

        // 1. populate adjList with edges
        for (int[] edge : edges) {
            // s = src, d = dist, w = weight
            int source = edge[0], neighborNodeId = edge[1], weight = edge[2];
            adjacencyList.get(source).add(new Integer[]{neighborNodeId, weight});
        }

        // this adj list will contain all paths originating from all nodes in the edge
        Map<Integer, Integer> shortestPath = new HashMap<>();

        // 2. create PQ /minHeap that will sort by weight
        //Comparator<Pair> p = Comparator.comparingInt((Pair p2) -> p2.distance);
        Comparator<int[]> p = Comparator.comparingInt((int[] p2) -> p2[0]);
        Queue<int[]> minHeap = new PriorityQueue<>(p);
        minHeap.add(new int[]{0, src}); // weight/distance to "myself" = 0

        // minHeap.peek() and minHeap.poll() will take the SMALLEST element on the heap i.e. lowest cost
        // 3. iterate over min heap and poll() the top
        while (!minHeap.isEmpty()){
            int[] curr = minHeap.poll();    // distance from a minHeap/pq = O(log V) -- where V is the number of vertices in the graph
            int currentWeight = curr[0];
            int currentNodeId = curr[1];

            if(shortestPath.containsKey(currentNodeId)){
                continue;
            }

            //4.  put currentNodeId + weight in adj list
            shortestPath.put(currentNodeId, currentWeight);

            // 5. process the neighbor's destination's by pushing them to the minHeap
            List<Integer[]> destinations = adjacencyList.get(currentNodeId);
            for(Integer[] dest : destinations){
                int neighborNodeId = dest[0];
                int neighborWeight = dest[1];

                // if node has not already been visited/processed
                if(!shortestPath.containsKey(neighborNodeId)){
                    // min heap will sort by weight
                    minHeap.add(new int[]{      // O( log_E) -- where E = # of edges)
                            currentWeight + neighborWeight, // distance to reach neighbor_nodeId
                            currentNodeId
                    });
                }
            }
        }

        // 5 return adjList (showing all nodes accessible from src + their distance)
        return shortestPath;
    }
}
