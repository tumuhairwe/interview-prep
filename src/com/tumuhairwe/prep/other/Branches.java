package com.tumuhairwe.prep.other;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given a series of commands
 * a) switch <<branchName>>
 * b) commit fileName
 *
 * Determine which branch has the most number files by the end of the commit log
 */
public class Branches {

    public static void main(String[] args) {
        String[] commitLog = new String[]{
                "switch branchA",
                "commit fileA",
                "commit fileB",

                "switch branchB",
                "commit fileD",
                "commit fileE",
                "commit fileF",

                "switch branchA",
                "commit fileA",
                "commit fileC",
        };
            Integer count = getMostNumberOfFilaes(commitLog);
    }

    static Integer getMostNumberOfFilaes(String[] commitLog){
        String SWITCH = "switch";
        String COMMIT = "commit";

        String currentBranch = "";
        Map<String, Set<String>> commitMessages = new HashMap<>();
        for (String command : commitLog){
            if(command.startsWith(SWITCH)){
                currentBranch = command.split("\\s")[1];

                if(!commitMessages.containsKey(currentBranch)){
                    commitMessages.put(currentBranch, new HashSet<>());
                }
            }
            else if(command.startsWith(COMMIT)){
                String fileName = command.split("\\s")[1];
                commitMessages.get(currentBranch).add(fileName);
            }
        }

        //commitMessages.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        //commitMessages.entrySet().stream().map(entry -> )

        Function<Set<String>, Integer> function = Set::size;
        //commitMessages.entrySet().stream().collect(Collectors.groupingBy(function, Collectors.counting()));
        Map<String, Integer> mapOfCounts = new HashMap<>();
        for (Map.Entry<String, Set<String>> branch : commitMessages.entrySet()){
            mapOfCounts.put(branch.getKey(), branch.getValue().size());
        }


        Set<Map.Entry<String, Integer>> highestCountOfFiles = mapOfCounts.entrySet()
                .stream().sorted(Map.Entry.comparingByValue())
                .limit(1)
                .collect(Collectors.toSet());
        String branchName = highestCountOfFiles.iterator().next().getKey();
        Integer count = highestCountOfFiles.iterator().next().getValue();

        System.out.println("The branch with the highest number of files is " + branchName + " with " + count + " files");
        return count;
    }
}
