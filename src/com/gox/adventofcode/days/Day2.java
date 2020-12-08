package com.gox.adventofcode.days;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day2 {

    public static String resolve() {
        int nbValidPasswordsPart1 = 0, nbValidPasswordsPart2 = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("resources/2.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(": ");
                String password = s[1].trim();
                String requirements = s[0].trim();
                String[] reqSplited = requirements.split(" ");
                char letter = reqSplited[1].charAt(0);
                String[] minMax = reqSplited[0].split("-");
                int min = Integer.valueOf(minMax[0]);
                int max = Integer.valueOf(minMax[1]);
                if(isPasswordValidPart1(password, letter, min, max)){
                    nbValidPasswordsPart1++;
                }
                if(isPasswordValidPart2(password, letter, min, max)){
                    nbValidPasswordsPart2++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Part 1: " + nbValidPasswordsPart1 + ", Part 2: " + nbValidPasswordsPart2;
    }

    public static boolean isPasswordValidPart1(String password, char letter, int min, int max){
        int nbLetter = 0;
        for(char c : password.toCharArray()){
            if(c == letter){
                nbLetter++;
            }
        }
        return nbLetter >= min && nbLetter <= max;
    }

    public static boolean isPasswordValidPart2(String password, char letter, int min, int max){
        char[] chars = password.toCharArray();
        return (chars[min - 1] == letter && chars[max - 1] != letter) || (chars[min - 1] != letter && chars[max - 1] == letter);
    }
}
