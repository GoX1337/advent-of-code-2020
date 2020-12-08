package com.gox.adventofcode.days;

import com.gox.adventofcode.utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {

    public static int resolvePart1() {

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

    public static int resolvePart2() {
        List<Integer> valuesList = FileUtils.parseFile("resources/1.txt");

        for(int i = 0; i < valuesList.size() - 2; i++){
            for(int j = i + 1; j < valuesList.size() - 1; j++){
                for(int k = j + 1; k < valuesList.size(); k++){
                    if(valuesList.get(i) + valuesList.get(j) + valuesList.get(k) == 2020){
                        return valuesList.get(i) * valuesList.get(j) * valuesList.get(k);
                    }
                }
            }
        }
        return -1;
    }
}
