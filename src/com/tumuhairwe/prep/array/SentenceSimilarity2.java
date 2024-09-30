package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * LeetCode 737 (medium)
 *
 * ref: https://leetcode.com/problems/sentence-similarity-ii/
 */
public class SentenceSimilarity2 {
    /**
     * Solution summary
     * - check that lengths match
     * - traverse arrays & check for matches -> continue) || (do dfs())
     * @return
     */
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        //0. check lengths
        if(sentence1.length != sentence2.length){
            return false;
        }

        //1. build adjList
        Map<String, List<String>> adjList = new HashMap<>();
        for(List<String> pair : similarPairs){
            String word1 = pair.get(0);
            String word2 = pair.get(1);

            // 2. add bi-directional relationship to graph
            adjList.putIfAbsent(word1, new ArrayList<>());
            adjList.putIfAbsent(word2, new ArrayList<>());
            adjList.get(word1).add(word2);
            adjList.get(word2).add(word1);
        }
        //2. traverse arrays & (check for matches -> continue) || (do dfs())
        for (int i = 0; i < sentence2.length; i++) {
            String word1 = sentence1[i];
            String word2 = sentence2[i];

            if(word1.equals(word2)){
                continue;
            }

            if(!adjList.containsKey(word1)){
                return false;
            }

            if(!dfs(adjList, word1, word2, new HashSet<>())){
                return false;
            }
        }

        return true;
    }

    boolean dfs(Map<String, List<String>> adjList, String source, String target, Set<String> visited){
        if(adjList.get(source).contains(target)){
            return true;
        }

        if(visited.add(source)){
            for(String neighbor : adjList.get(source)){
                if(!visited.contains(neighbor)
                        && dfs(adjList, neighbor, target, visited)){
                    return true;
                }
            }
        }

        return false;
    }
}
