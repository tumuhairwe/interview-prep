package com.tumuhairwe.prep.graphs;

import java.util.Arrays;

public class NetworkDelayTime_impl {
    static class Node{
        int nodeId;
        int time;
    }

    public int networkDelayTime(int[][] times, int numberOfNodes, int k){
        //0. initialize path & fill with -1
        int[] paths = new int[numberOfNodes];
        Arrays.fill(paths, Integer.MAX_VALUE);

        for (int i = 0; i < numberOfNodes; i++) {
            int[] temp = Arrays.copyOf(paths, paths.length);

            for (int[] journey : times){
                int src = journey[0];
                int dest = journey[1];
                int time = journey[2];

                boolean srcIsUnset = temp[src - 1] != Integer.MAX_VALUE;
                boolean costToDestIsMoreExpensive = temp[src - 1] + time < temp[dest - 1];
                if(srcIsUnset && costToDestIsMoreExpensive){
                    temp[dest - 1] = temp[src - 1] + time;
                }
            }
            paths = temp;
        }

        // find costliest node to reach -> set result to cost-to-reach-that-node
        int result = 0;
        for (int i = 0; i < paths.length; i++) {
            if(paths[i] == Integer.MAX_VALUE) {
                return -1;
            }
            result = Math.max(result, paths[i]);
        }

        return result;
    }
}
