package com.gox.adventofcode.days;

import com.gox.adventofcode.utils.FileUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {

    private static Pattern bagPattern = Pattern.compile("^[1-9]* (.*) (bag|bags)\\.?$");
    private static String shinyGold = "shiny gold";

    public static int resolve() {
        List<String> lines = FileUtils.parseFileLines("resources/7.txt");
        Map<String, List<String>> bagsMap = new HashMap<>();

        for(String line : lines){
            String[] ruleElems = line.split(" bags contain ");
            String[] bagsSplit = ruleElems[1].split(", ");
            List<String> bags = new ArrayList<>();
            for(String bagStr : bagsSplit){
                Matcher m = bagPattern.matcher(bagStr);
                if (m.find()) {
                    bags.add(m.group(1));
                }
            }
            bagsMap.put(ruleElems[0], bags);
        }

        int nbShinyGoldBagsContainerCount = 0;
        for(Map.Entry<String, List<String>> entry : bagsMap.entrySet()){
            if(!shinyGold.equals(entry.getKey())) {
                if (canContainShinyGold(entry.getKey(), entry.getValue(), bagsMap)) {
                    nbShinyGoldBagsContainerCount++;
                }
            }
        }
        return nbShinyGoldBagsContainerCount;
    }

    public static boolean canContainShinyGold(String bag, List<String> bags, Map<String, List<String>> bagsMap){
        for(String b : bags){
            if(shinyGold.equals(b) || canContainShinyGold(b, bagsMap.get(b), bagsMap)){
                return true;
            }
        }
        return false;
    }

}
