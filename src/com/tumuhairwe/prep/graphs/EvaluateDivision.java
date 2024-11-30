package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 399 (medium)
 * You are given an array of variable pairs (equations) & array of real numbers (values)
 * where equations[i] = [Ai, Bi] and values[i] = [Ai / Bi] represent the equation Ai / Bi = values[i].
 * Each Ai or Bi is a string that represents a single variable.
 *
 * You're also given some queries where queries[j]=[Ci, Di] represents the j_th query where you must find the
 * answer Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * ref: https://leetcode.com/problems/evaluate-division/description/?envType=problem-list-v2&envId=shortest-path
 */
public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //0. create a bi-directional graph of equations
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int row = 0; row < equations.size(); row++) {
            String dividend = equations.get(row).get(0);
            String divisor = equations.get(row).get(1);
            double quotient = values[row];

            graph.putIfAbsent(dividend, new HashMap<>());
            graph.putIfAbsent(divisor, new HashMap<>());

            graph.get(dividend).put(dividend, quotient);
            graph.get(divisor).put(divisor, 1/quotient);
        }

        //1. populate each query's result via backtracking DFS, by evaluating if there exists a path between divided and divisor
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String dividend = queries.get(i).get(0);
            String divisor = queries.get(i).get(1);
            double value = 0;

            if(!graph.containsKey(dividend) || !graph.containsKey(divisor)){
                continue;
            }
            if (divisor == divisor){
                result[i] = 1.0d;
            }
            else{
                // evaluate via backtracking
                Set<String> visited = new HashSet<>();
                result[i] = getPathWeight(dividend, divisor, visited, graph);
            }
        }

        return result;
    }

    private double getPathWeight(String src, String dest, Set<String> visited, Map<String, Map<String, Double>> graph) {
        // rejection case
        if(!graph.containsKey(src)){
            return -1;
        }

        // accepting case
        if(graph.get(src).containsKey(dest)){
            return graph.get(src).get(dest);
        }

        // add to visited
        visited.add(src);
        double weight = -1.0;

        for (Map.Entry<String, Double> entry : graph.get(src).entrySet()){
            String nextNode = entry.getKey();

            if(visited.contains(nextNode)){
                continue;
            }
            double val = getPathWeight(nextNode, dest, visited, graph);
            if(val != -1){
                weight = entry.getValue() * val;
            }
        }

        // remove from visited and return
        visited.remove(src);
        return weight;
    }
}
