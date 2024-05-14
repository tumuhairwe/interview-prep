package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 133 (Medium)
 *
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * ref: https://leetcode.com/problems/clone-graph/
 */

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraph {

    public static void main(String[] args) {
        Node node = new Node();
    }
    Map<Integer, Node> visited = new HashMap<>();

    /**
     * LC Summary: recursive dfs, hashmap for visited nodes
     * DFS solution summary
     *
     * - Account for null, return null;
     * - Create cache Map (key = Integer, value=List)
     *
     * - check if there's an e
     * - Create a new copy of the node (not clone)
     * - Add to cache/map of visited nodes
     * - for each neighbor, recursively call cloneGraph( neighbor ) to clone them
     *
     * TC: O(V + E) where V = vertices/nodes && E = number of edges
     * SC:O(N) space that would be occupied by the visted map. The space occupied by the
     * recursion stack = O( H ) where h = height of the graph
     */
    public Node cloneGraph(Node node) {
        // 0. base case
        if(node == null){
            return null;
        }

        // 1.check if exists, don't duplicate (gonna be called recursively)
        if(visited.containsKey(node.val)){
            return visited.get(node.val);
        }

        // 2. create new node  & save in cache/map
        Node newNode = new Node(node.val, new ArrayList<>());
        visited.put(node.val, newNode); // add to visited Map BEFORE entering recursion

        // 3. recursively call cloneGraph() on each neighbor
        for (Node neighbor : node.neighbors){
            newNode.neighbors.add(cloneGraph(neighbor));
        }
        return newNode;
    }
}