package com.tumuhairwe.prep.unionfind;

import java.util.Arrays;

/**
 * LeetCode 684 redundant connect
 *
 * - Disjoint Set:
 *      Definition: Two Sets are called "Disjoint Sets" if they don't have an element in common ancestor
 * -Union Find/Merge-Find:
 *      Definition: A data structure that stores a collection of  non-overlapping or disjoint sets or partitions a set into disjoint sets
 *      Operations
 *      - Add a new disjoint set
 *      - Find a representative/leader of a set
 *      - Find if 2 elements are in the same or different sets
 *
 * LeetCode 684
 * Given a tree that is an UNDIRECTED GRAPH ... that is connected and has not cycles
 * Given a tree (int[][] tree) with n nodes, labeled from 1 to n... with 1 additional node added
 * Find and return the edge to be removed so that the tree still has N nodes
 *
 * the graph is an array of edges of length n, where edges[i] = [a, b] indicates that there is
 * an edge between nodes a and b .
 *
 * If there are multiple answers, return the answer that occurs last in the input.
 * Example 1:
 * Given = [ [1,2],[1,3],[2,3] ]
 * output = [2, 3] (redundant edge)
 *
 * Example 2:
 * Given: [ [1,2],[2,3],[3,4],[1,4],[1,5] ]
 * output: [1, 4]
 *
 * ref: https://leetcode.com/problems/redundant-connection/
 */
public class RedundantConnections {

    public static void main(String[] args) {
        int[][] edges = new int[][]{{1,2},{1,3},{2,3}};
        int[] connections = findRedundantConnection(edges);
        System.out.println("The redundant connections are " + Arrays.toString(connections));
    }
    static int[] parent;

    /**
     * Solution Summary
     * - Implement UnionFind
     *      - create an int[] parent
     *      - findParent() -> a node's parent is the value at the [x-1] position
     *          - if its not x, recursively call find parent of parent (findParent( parent[x -1] )
     *      - union() -> assign node_A's parent to parent[ indexOfParentOfNode_B)
     * - loop thru the 2D array of edges
     *      - if both nodes have different parents, union them
     *      - if both nodes have the same parent, return that edge
     */
    public static int[] findRedundantConnection(int[][] edges){
        // 0. initialize parent
        parent = new int[edges.length];

        // 1. populate X number of parents (1 for each child) (1-based index)
        for (int i = 0; i < edges.length; i++) {
            parent[i] = i + 1;
        }

        // 2. for each edge, if both nodes have the same parent, return it ... else union them ( union(nodeA, nodeB)) & return empty array
        for (int[] edge : edges){
            // if they have they same parent
            int nodeA = edge[0];
            int nodeB = edge[1];
            if(find(nodeA) == find(nodeB)){
                return edge;    // the node is pointing to itself -> return self-pointing edge
            }
            else {  // else nodeA.parent != nodeB.parent -> union them
                union(edge[0], edge[1]);
            }
        }

        return new int[2];
    }

    public static int find(int x){
        // 0. if x is has a parent -> return it
        if(x == parent[x - 1]){
            return x;
        }

        // else call & return find( parent_of_x )
        return find(parent[x - 1]);
    }

    // Union both nodes (i.e. set nodeB's parent be nodeA
    public static void union(int nodeA, int nodeB){
        parent[find(nodeB) - 1 ] = find(nodeA);
    }
}
