package com.gox.adventofcode.days;

import com.gox.adventofcode.utils.FileUtils;

import java.util.*;

public class Day6 {

    public static String resolve() {
        List<String> lines = FileUtils.parseFileLines("resources/6.txt");
        int responseCountSum = 0;
        int everyoneResponseCountSum = 0;
        Set<Character> groupResponses = new HashSet<>();
        Map<Character, Integer> groupResponsesCountMap = new HashMap<>();
        int nbPersonInGroup = 0;

        for(String line : lines){
            if("".equals(line)){
                responseCountSum += groupResponses.size();
                int respCount = 0;
                for(Map.Entry<Character, Integer> entry : groupResponsesCountMap.entrySet()){
                    if(entry.getValue() == nbPersonInGroup){
                        respCount++;
                    }
                }
                everyoneResponseCountSum += respCount;
                groupResponsesCountMap.clear();
                groupResponses.clear();
                nbPersonInGroup = 0;
            } else {
                nbPersonInGroup++;
                for(char c : line.toCharArray()){
                    groupResponses.add(c);
                    groupResponsesCountMap.put(c, groupResponsesCountMap.get(c) == null ? 1 : groupResponsesCountMap.get(c) + 1);
                }
            }
        }
        return "part 1 result: " + responseCountSum + " part 2 result: " + everyoneResponseCountSum;
    }
}

