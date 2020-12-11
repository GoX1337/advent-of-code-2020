package com.gox.adventofcode.days;

import com.gox.adventofcode.utils.FileUtils;

import java.util.List;

public class Day9 {

    private static List<Long> lines = FileUtils.parseFileToLongList("resources/9.txt");
    private static int preambuleSize = 25;

    public static long resolvePart1() {
        return findFirstIncorrectValue();
    }

    public static long findFirstIncorrectValue(){
        for(int i = preambuleSize; i < lines.size(); i++){
            long value = lines.get(i);
            boolean check = hasPairSum(value, lines.subList(i - preambuleSize, i));
            if(!check){
                return value;
            }
        }
        return 0;
    }

    public static boolean hasPairSum(long value, List<Long> preambule){
        for(int i = 0; i < preambule.size(); i++){
            for(int j = 0; j < preambule.size(); j++){
                if(i != j && preambule.get(i) + preambule.get(j) == value){
                    return true;
                }
            }
        }
        return false;
    }

    public static long resolvePart2() {
        List<Long> lines = FileUtils.parseFileToLongList("resources/9.txt");
        long part1Value = findFirstIncorrectValue();

        for(int i = 0; i < lines.size() - 1; i++){
            int j = i + 1;
            long rangeSum = lines.get(i);
            boolean rangeFound = false;
            long maxRangeValue = lines.get(i);
            long minRangeValue = lines.get(i);

            while(j < lines.size()){
                rangeSum += lines.get(j);
                if(maxRangeValue <= lines.get(j)){
                    maxRangeValue = lines.get(j);
                }
                if(minRangeValue > lines.get(j)){
                    minRangeValue = lines.get(j);
                }
                if(rangeSum == part1Value){
                    rangeFound = true;
                    break;
                }
                j++;
            }

            if(rangeFound){
                return minRangeValue + maxRangeValue;
            }
            i++;
        }
        return 0;
    }
}
