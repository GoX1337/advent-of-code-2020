package com.gox.adventofcode.days;

import com.gox.adventofcode.utils.FileUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day8 {

    public static int resolvePart1() {
        List<String> lines = FileUtils.parseFileLines("resources/8.txt");
        Set<String> instructionHistory = new HashSet<>();
        int accumulator = 0;
        int i = 0;

        while(i >= 0 && i < lines.size()){
            String line = lines.get(i);
            String[] instruction = line.split(" ");
            String operation = instruction[0];
            int argument = Integer.valueOf(instruction[1]);
            if(instructionHistory.contains(i + "_" + operation)){
                break;
            }
            instructionHistory.add(i + "_" + operation);

            switch (operation){
                case "jmp": {
                    i += argument;
                    break;
                }
                case "acc": {
                    accumulator += argument;
                }
                default: i++;
            }
        }
        return accumulator;
    }
}
