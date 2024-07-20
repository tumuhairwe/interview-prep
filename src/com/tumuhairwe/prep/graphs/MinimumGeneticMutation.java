package com.tumuhairwe.prep.graphs;

import java.util.*;

/**
 * LeetCode 433 (medium)
 *
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 *
 * Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.
 *
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 *
 * Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.
 *
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 */
public class MinimumGeneticMutation {
    public static void main(String[] args) {
        String stargGene = "AACCTTGG";
        String endGene = "AATTCCGG";
        String[] bank = new String[]{"AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"};
        int result = minMutation(stargGene, endGene, bank);;
        System.out.println("There are " + result + " mutations");
    }

    /**
     * Solution summary
     * - preparing the ground
     *      - Create Set<String> of genes (to prevent TimeLimitExceeded exceptions when there are duplicate genes in the array)
     *      - Define list of allowable/valid Set<Character> to check against before adding to queue
     * - edge case: if endGene does not exist in geneBank, return -1 (impossible to reach destination)
     * - Create and seed BFS que with (key=startGene, step=1)
     * - while !que.isEmpty()
     *      - poll() entry from que (call it currentGene)
     *      - if currentGene == endGene, we've reached destination -> return entry.step
     *      - generate permutation for currentGene by changing one letter at a time ... if permutation is a gene in the geneBank,
     *          -> add to queue and remove from geneBank (to prevent infinite loops)
     *      - while-loop will continue until end/destination is reached since each permutation will need to be checked against the bank.
     * - if we exit the loop (i.e. queue becomes empty before destination is reached ... return -1 --- impossible to reach destination)
     */
    public static int minMutation(String startGene, String endGene, String[] bank) {
        Set<Character> allowableGenes = Set.of('A', 'C', 'G', 'T');
        //0. edge case
        Set<String> genes = new HashSet<>(Arrays.asList(bank));

        if(!genes.contains(endGene)){
            return -1;
        }

        // create que and seed it
        Queue<Map.Entry<String, Integer>> que = new LinkedList<>();
        que.offer(new AbstractMap.SimpleEntry<>(startGene, 1)); //key=gene, val=stepCount

        while (!que.isEmpty()) {
            Map.Entry<String, Integer> p = que.poll();

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
}
