package com.gox.adventofcode.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<Integer> parseFile(String filePath){
        List<Integer> lines = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(Integer.valueOf(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static List<String> parseFileLines(String filePath){
        List<String> lines = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}
