package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 433 (medium)
 *
 */
public class MinimumGeneticMutation {
    public static void main(String[] args) {
        String stargGene = "AACCTTGG";
        String endGene = "AATTCCGG";
        String[] bank = new String[]{"AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"};
        int result = minMutation(stargGene, endGene, bank);;
        System.out.println("There are " + result + " mutations");
    }
    public static int minMutation(String startGene, String endGene, String[] bank) {
        List<Character> allowableGenes = List.of('A', 'C', 'G', 'T');
        //0. edge case
        Set<String> genes = new HashSet<>(Arrays.asList(bank));

        if(!genes.contains(endGene)){
            return 0;
        }

        // create que and seed in
        Queue<Map.Entry<String, Integer>> que = new LinkedList<>();
        que.offer(new AbstractMap.SimpleEntry<>(startGene, 1));

        while (!que.isEmpty()) {
            Map.Entry<String, Integer> p = que.poll();
            System.out.println(p.getKey());
            String gene = p.getKey();
            int step = p.getValue();

            if(gene.equals(endGene)){
                return step;
            }

            // create different permutation of genes & if one exists in bank -> add it to BFS que
            for(int i=0; i<gene.length(); i++){
                for(char ch = 'A'; ch<='Z'; ch++){
                    if(!allowableGenes.contains(ch)){
                        continue;
                    }

                    char[] oldGene = gene.toCharArray();
                    oldGene[i] = ch;
                    String newGene = new String(oldGene);
                    if(genes.contains(newGene)){
                        que.offer(new AbstractMap.SimpleEntry<>(newGene, step + 1));
                        genes.remove(newGene);  // to prevent re-visiting an infinite loops
                    }
                }
            }
        }

        return -1;
    }
    /*
    "AACCGGTT"
"AACCGGTA"
["AACCGGTA"]
"AACCGGTT"
"AAACGGTA"
["AACCGGTA","AACCGCTA","AAACGGTA"]
"AACCTTGG"
"AATTCCGG"
["AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"]
     */
}
