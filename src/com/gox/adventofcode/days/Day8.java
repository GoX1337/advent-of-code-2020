package com.gox.adventofcode.days;

import com.gox.adventofcode.utils.FileUtils;

import java.util.ArrayList;
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

    public static int resolvePart2() {
        List<String> lines = FileUtils.parseFileLines("resources/8.txt");
        List<Instruction> instructions = new ArrayList<>();
        List<Integer> nopJmpIndexes = new ArrayList<>();
        for(int i = 0; i < lines.size(); i++){
            String[] instruction = lines.get(i).split(" ");
            String operation = instruction[0];
            int argument = Integer.valueOf(instruction[1]);
            instructions.add(new Instruction(operation, argument));
            if("nop".equals(operation) || "jmp".equals(operation)){
                nopJmpIndexes.add(i);
            }
        }

        boolean found = false;
        boolean firstTry = true;
        int nopJmpIndex = 0;

        while(!found) {
            try {
                int accumulator = processInstruction(instructions, nopJmpIndexes.get(nopJmpIndex), firstTry);
                return accumulator;
            } catch (LoopException e) {
                if(firstTry){
                    firstTry = false;
                } else {
                    nopJmpIndex++;
                }
            }
        }
        return 0;
    }

    public static int processInstruction(List<Instruction> instructions, int nopJmpIndex, boolean firstTry) throws LoopException {
        Set<String> instructionHistory = new HashSet<>();
        int accumulator = 0;
        int i = 0;

        while(i >= 0 && i < instructions.size()){
            Instruction instruction = instructions.get(i);
            String operation = inverseOperationIfNeeded(instruction.getOperation(), i, nopJmpIndex, firstTry);
            int argument = Integer.valueOf(instruction.getArgument());
            if(instructionHistory.contains(i + "_" + operation)){
                throw new LoopException();
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

    public static String inverseOperationIfNeeded(String operation, int index, int nopJmpIndex, boolean firstTry){
        if(!firstTry && index == nopJmpIndex){
            if("nop".equals(operation)){
                return "jmp";
            } else if("jmp".equals(operation)){
                return "nop";
            }
        }
        return operation;
    }
}

class LoopException extends Exception {}

class Instruction {
    public String operation;
    public int argument;

    public Instruction(String operation, int argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public String getOperation() {
        return operation;
    }

    public int getArgument() {
        return argument;
    }
}
