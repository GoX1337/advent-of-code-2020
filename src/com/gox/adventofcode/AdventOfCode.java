package com.gox.adventofcode;

import com.gox.adventofcode.days.Day1;
import com.gox.adventofcode.days.Day2;
import com.gox.adventofcode.days.Day3;
import com.gox.adventofcode.days.Day4;

public class AdventOfCode {

    public static void main(String[] args) {
        System.out.println("Advent Of Code 2020");
        System.out.println("Day 1 part 1 result: " + Day1.resolvePart1());
        System.out.println("Day 1 part 2 result: " + Day1.resolvePart2());
        System.out.println("Day 2 result: " + Day2.resolve());
        System.out.println("Day 3 part 1 result : " + Day3.resolvePart1());
        System.out.println("Day 3 part 2 result : " + Day3.resolvePart2());
        System.out.println("Day 4 part 1 result : " + Day4.resolve(false));
        System.out.println("Day 4 part 2 result : " + Day4.resolve(true));
    }
}
