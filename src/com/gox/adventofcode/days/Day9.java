package com.gox.adventofcode.days;

import com.gox.adventofcode.utils.FileUtils;

import java.util.List;

public class Day9 {

    public static long resolve() {
        List<Long> lines = FileUtils.parseFileToLongList("resources/9.txt");
        int preambuleSize = 25;

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

}
