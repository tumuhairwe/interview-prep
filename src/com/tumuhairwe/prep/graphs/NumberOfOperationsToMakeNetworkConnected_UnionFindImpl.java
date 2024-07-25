package com.tumuhairwe.prep.graphs;

import java.util.Arrays;

class NumberOfOperationsToMakeNetworkConnected_UnionFindImpl {
    
    int UNCONNECTED = -1;
    //int CONNECTED = 1;
    public int makeConnected(int n, int[][] connections) {
        int[] parent = new int[n];
        int extraCable = 0;
        
        Arrays.fill(parent, -1);
        
        for(int[] connection : connections) {
            
            if(find(connection[0], parent) == find(connection[1], parent)) {
                extraCable++;
            } else {
                union(connection[0], connection[1], parent);
            }
        }
        
        int component = 0;
        
        for(int i=0; i<parent.length; i++) {
           if( parent[i] == -1) {
               component++;
           }
        }
        
        return extraCable >= (component-1) ? component-1 : -1;
    }

    int find(int nodeA, int[] parent){
        while (parent[nodeA] != UNCONNECTED) {
            nodeA = parent[nodeA];
        }

        return nodeA;
    }
    

    void union(int nodeA, int nodeB, int[] parent){
        int parentOfA = find(nodeA, parent);
        int parentOfB = find(nodeB, parent);

        if(parentOfA != parentOfB){
            parent[parentOfB] = nodeA;
        }
    }
}