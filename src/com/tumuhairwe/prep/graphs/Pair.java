package com.tumuhairwe.prep.graphs;

import java.util.Comparator;

class Pair implements Comparator<Pair> {
    int nodeId;
    int distance;
    int sum;

    Pair(int sum, int nodeId, int distance) {
        this.sum = sum;
        this.nodeId = nodeId;
        this.distance = distance;
    }

    @Override
    public int compare(Pair o1, Pair o2) {
        return Integer.compare(o1.sum, o2.sum);
    }
}