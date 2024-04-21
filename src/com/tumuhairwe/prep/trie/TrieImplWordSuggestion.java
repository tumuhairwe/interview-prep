package com.tumuhairwe.prep.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TrieImplWordSuggestion {

    private Node root = new Node();

    // TC = O(n) where n = number of characters in word (worst case, otherwise n = unique letters in word)
    // SC = O(n)
    public void insert(String word){
        Node node = this.root;
        for (int i = 0; i<word.toCharArray().length; i++){
            char ch = word.toCharArray()[i];

            // makes a char's value equivalent to 1 - 26 on the alphabet
            // e.g. ('a' in ASCII = 97), and ('b' in ASCII = 98) ...
            // therefore ('b' - 'a' = 1) and ('c' - 'a' = 3)  ==> bc ==> [0, 1, 1]
            int index = ch - 'a';

            // Create a new node if char does not exist in children dictionary
            if (node.children[index] == null){
                node.children[index] = new Node();
            }

            node = node.children[index];
            node.searchWords.offerFirst(word);

            if(node.searchWords.size() > 3){
                node.searchWords.pollLast();
            }
        }
    }

    // TC = 0(m) where m == length of the search prefix
    // SC = O(n)
    public List<List<String>> search(String searchWord){
        List<List<String>> result = new ArrayList<>();
        Node node = this.root;

        for (char ch : searchWord.toCharArray()){
            int index = ch - 'a';
            if(node != null){
                node = node.children[index];
            }

            result.add(node == null ? Arrays.asList() : node.searchWords);
        }

        return result;
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord){
        Arrays.sort(products);
        for (String product : products) {
            insert(product);
        }
        return search(searchWord);
    }

    public static void main(String[] args) {
        String[] products = {"bat", "bag", "bassinet", "bread", "cable", "table", "tree", "tarp"};
        String[] searchWordList = {"ba", "in", "ca", "t"};

        for (int i = 0; i < searchWordList.length; i++) {
            TrieImplWordSuggestion trie = new TrieImplWordSuggestion();
            System.out.println((i+1)+ ".\tProducts:"+ Arrays.toString(products));
            System.out.println("\tSearch keyword: "+ searchWordList[i]);
            System.out.println("\tSuggested Products: " + trie.suggestedProducts(products, searchWordList[i]));
        }
    }
}

class Node{
    Node[] children = new Node[26];
    LinkedList<String> searchWords = new LinkedList<>();
}