package com.gox.adventofcode.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Day1 {

    public static int resolve() {

        Map<Integer, Integer> expenseReportMap = new HashMap();

        try (BufferedReader br = new BufferedReader(new FileReader("resources/1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int expenseValue = Integer.valueOf(line);
                int complement = 2020 - expenseValue;
                Integer val = expenseReportMap.get(expenseValue);
                if(val == null) {
                    expenseReportMap.put(complement, expenseValue);
                } else {
                    return val * expenseValue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
