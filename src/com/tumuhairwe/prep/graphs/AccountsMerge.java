package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 721 (medium)
 */
public class AccountsMerge {

    Map<String, List<String>> adjList;
    Set<String> visited;

    /**
     * Solution summary
     * - Create adjList from accountList (List<List<String>)
     *
     *      - primaryEmail = idx=1;
     *      - for the remaining/secondary emails ... add a bi-directional entry in adjList
     * - perform DFS on each account in accountList
     *      - add name 1st (idx=0), to allow for sorting of the sublist (idx=1 to n=size)
     *      - add email (idx=1)
     *      - if email is not visited,
     *             - create new List (mergedAccount)
     *             - name = idx=0
     *             - do DFS passing the mergedAccount and email (visitedSet and adjList are global vars)
     *             - sort the mergedAccount (but only all entries except the name)
     *             - add mergedAccount to resultList
     * - return resultList
     */
    public List<List<String>> accountsMerge(List<List<String>> accountList){
        //0. declare vars
        adjList = new HashMap<>();
        visited = new HashSet<>();

        //1. create adjList & populate
        for(List<String> account : accountList){
            //String name = account.get(0);
            String primaryEmail = account.get(1);

            for (int j = 0; j < account.size(); j++) {
                String secondaryEmail = account.get(2);

                // add primary to adjList
                adjList.putIfAbsent(primaryEmail, new ArrayList<>());
                adjList.get(primaryEmail).add(secondaryEmail);

                adjList.putIfAbsent(secondaryEmail, new ArrayList<>());
                adjList.get(secondaryEmail).add(primaryEmail);
            }
        }

        //2. perform dfs on adjList
        List<List<String>> resultList = new ArrayList<>();
        for (List<String> account : accountList){
            String name = account.get(0);
            String email = account.get(1);

            // if unvisited -> dfs to merge all secondaries into the same account
            if(!visited.contains(email)){
                List<String> mergedAccount = new ArrayList<>();
                mergedAccount.add(name);    // add name to merge account 1st (idx = 0)

                doDFS(mergedAccount, email); // will add primary and all secondary

                //sort sublist (idx1 -> n)
                Collections.sort(mergedAccount.subList(1, mergedAccount.size()));

                // add to resultList
                resultList.add(mergedAccount);
            }
        }

        return resultList;
    }

    private void doDFS(List<String> mergedAccount, String email) {
        visited.add(email);
        mergedAccount.add(email);

        if(!adjList.containsKey(email)){
            return;
        }
        // do recursive DFS
        for (String neighbor: adjList.get(email)){
            if(!visited.contains(neighbor)){
                doDFS(mergedAccount, neighbor);
            }
        }
    }
}
