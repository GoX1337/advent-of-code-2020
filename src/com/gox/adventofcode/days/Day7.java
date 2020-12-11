package com.gox.adventofcode.days;

import com.gox.adventofcode.utils.FileUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {

    private static Pattern bagPattern = Pattern.compile("^([1-9]*) (.*) (bag|bags)\\.?$");
    private static String shinyGold = "shiny gold";

    public static String resolve() {
        List<String> lines = FileUtils.parseFileLines("resources/7.txt");
        Map<String, List<Bag>> bagsMap = new HashMap<>();

        for(String line : lines){
            String[] ruleElems = line.split(" bags contain ");
            String[] bagsSplit = ruleElems[1].split(", ");
            List<Bag> bags = new ArrayList<>();
            for(String bagStr : bagsSplit){
                Matcher m = bagPattern.matcher(bagStr);
                if (m.find()) {
                    bags.add(new Bag(m.group(2), Integer.valueOf(m.group(1))));
                }
            }
            bagsMap.put(ruleElems[0], bags);
        }

        int nbShinyGoldBagsContainerCount = 0;
        for(Map.Entry<String, List<Bag>> entry : bagsMap.entrySet()){
            if(!shinyGold.equals(entry.getKey())) {
                if (canContainShinyGold(entry.getKey(), entry.getValue(), bagsMap)) {
                    nbShinyGoldBagsContainerCount++;
                }
            }

        }
        int nestedBagsCount = countShinyGoldNestedBags(bagsMap.get(shinyGold), bagsMap);
        return "part 1: " + nbShinyGoldBagsContainerCount + " part 2: " + nestedBagsCount;
    }

    public static boolean canContainShinyGold(String bag, List<Bag> bags, Map<String, List<Bag>> bagsMap){
        for(Bag b : bags){
            if(shinyGold.equals(bag) || canContainShinyGold(b.name, bagsMap.get(b.name), bagsMap)){
                return true;
            }
        }
        return false;
    }

    public static int countShinyGoldNestedBags(List<Bag> bags, Map<String, List<Bag>> bagsMap){
        int bagCount = 0;
        for(Bag b : bags){
            int count = b.count + (b.count * countNestedBags(b, bagsMap));
            bagCount += count;
        }
        return bagCount;
    }

    public static int countNestedBags(Bag bag, Map<String, List<Bag>> bagsMap){
        if(bagsMap.get(bag.name).isEmpty()){
            return 0;
        } else {
            int bagCount = 0;
            for(Bag b : bagsMap.get(bag.name)){
                int count = b.count + (b.count * countNestedBags(b, bagsMap));
                bagCount += count;
            }
            return bagCount;
        }
    }
}

class Bag {
    public int count;
    public String name;

    public Bag(String name, int count) {
        this.count = count;
        this.name = name;
    }

    public String toString(){
        return name + " x" + count;
    }
}