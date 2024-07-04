package com.tumuhairwe.prep.unionfind;

import java.util.*;

/**
 * LeetCode 721 (medium)
 * Given a list of account where each element accounts[i] is a list of string,
 * Where the 1st element in the list is a name and the rest of the elements are
 * emails representing that account
 * Given that 2 accounts belong to the same person if they have the same email (fot both accounts)
 *
 * - After merging the accounts, return the accounts in the following format:
 * the first element of each account is the name,
 * and the rest of the elements are emails in sorted order.
 * The accounts themselves can be returned in any order.
 * ref: https://leetcode.com/problems/accounts-merge/description/
 */
public class AccountsMerge {
    public static void main(String[] args) {
        List<List<String>> lists = List.of(
                List.of("John","johnsmith@mail.com","john_newyork@mail.com"),
                List.of("John","johnsmith@mail.com","john00@mail.com"),
                List.of("Mary","mary@mail.com"),
                List.of("John","johnnybravo@mail.com")
        );
        List<List<String>> result = accountsMerge(lists);
        System.out.println("Accounts Merged = " + result);

        List<List<String>> lists2 = List.of(
                List.of("Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"),
                List.of("Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"),
                List.of("Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"),
                List.of("Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"),
                List.of("Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co")
        );
        System.out.println("Accounts Merged 2 = " + lists2);
    }
    public static List<List<String>> accountsMerge(List<List<String>> accounts){
        int n = accounts.size();
        UnionFind dsu = new UnionFind(n);

        Map<String, Integer> email_to_indexMap = new HashMap<>();

        // populate email_to_index_ofAccount_map
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                String name = accounts.get(i).get(0);

                if(!email_to_indexMap.containsKey(email)){
                    email_to_indexMap.put(email, i);
                }
                else {
                    dsu.union(i, email_to_indexMap.get(email));
                }
            }
        }

        // populate account-to-email map
        Map<Integer, List<String>> merged = new HashMap<>();
        for (String email : email_to_indexMap.keySet()){
            int group = email_to_indexMap.get(email);
            int lead = dsu.find(group);

            if(!merged.containsKey(lead)){
                merged.put(lead, new ArrayList<>());
            }
            else merged.get(lead).add(email);
        }

        // create and populate result
        List<List<String>> res = new ArrayList<>();
        for (int accountId : merged.keySet()){
            List<String> group = merged.get(accountId);
            Collections.sort(group);
            group.add(0, accounts.get(accountId).get(0));
            res.add(group);
        }

        return res;
    }

    static class UnionFind{
        private int[] parent;
        private int[] rank;

        public UnionFind(int size){
            rank = new int[size];
            parent = new int[size];

            // initialize
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x){
            if(this.parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // function to check if x and y are part of the same component
        public boolean belongs(int x, int y){
            return find(x) == find(y);
        }

        // implementation of union by rank
        public boolean union(int x, Integer y) {
            int parent_of_x = find(x);
            int parent_of_y = find(y);

            if(parent_of_x == parent_of_y){
                return false;
            }
            // else compare the ranks
            else if(rank[parent_of_x] < rank[parent_of_y]){
                parent[parent_of_x] = parent_of_y;
            }
            else if(rank[parent_of_y] < rank[parent_of_x]){
                parent[parent_of_y] = parent_of_y;
            }
            else {
                parent[parent_of_y] = parent_of_x;
                rank[parent_of_x]++;
            }

            return true;
        }
    }
}
