package com.gox.adventofcode.days;

import com.gox.adventofcode.utils.FileUtils;
import java.util.List;

public class Day3 {

    public static long resolvePart1() {
        return processSlope(FileUtils.parseFileLines("resources/3.1.txt"), 3, 1);
    }

    public static long resolvePart2() {
        List<String> lines = FileUtils.parseFileLines("resources/3.1.txt");
        return processSlope(lines, 1, 1)
                 *  processSlope(lines, 3, 1)
                 *  processSlope(lines, 5, 1)
                 *  processSlope(lines, 7, 1)
                 *  processSlope(lines, 1, 2);
    }

    public static long processSlope(List<String> lines, int right, int down){
        int nbTrees = 0;
        int x = right;

        for(int y = down; y < lines.size(); y += down){
            String line = lines.get(y);
            char c = line.charAt(x);
            if(c == '#'){
                nbTrees++;
            }
            x += right;
            if(x > line.length() - 1){
                x = x - line.length();
            }
        }
        return nbTrees;
    }
}
