package com.gox.adventofcode.days;

import com.gox.adventofcode.utils.FileUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    private static String[] fields = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    private static Pattern passportIdPattern = Pattern.compile("^[0-9]{9}$");
    private static Pattern eyeColorPattern = Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth){1}$");
    private static Pattern hairColorPattern = Pattern.compile("^#[0-9a-z]{6}$");
    private static Pattern heightPattern = Pattern.compile("^([0-9]*)(cm|in)");

    public static int resolve(boolean part2) {
        List<String> lines = FileUtils.parseFileLines("resources/4.txt");
        String passport = "";
        int nbValidPassports = 0;

        for(String line : lines){
            if(line.equals("")){
                if(checkPassportValid(passport, part2)){
                    nbValidPassports++;
                }
                passport = "";
            } else {
                if(passport != ""){
                    passport += " ";
                }
                passport += line;
            }
        }
        return nbValidPassports;
    }

    private static boolean checkPassportValid(String passport, boolean part2) {
        String[] elems = passport.split(" ");
        Map<String, String> elemsMap = new HashMap<>();
        for(String elem : elems){
            String[] pair = elem.split(":");
            elemsMap.put(pair[0], pair[1]);
        }

        for(String field : fields){
            if(elemsMap.get(field) == null || (part2 && !isValidField(field, elemsMap.get(field)))){
                return false;
            }
        }
        return true;
    }

    private static boolean isValidField(String key, String value) {
        switch(key){
            case "byr": return checkBirthYear(value);
            case "iyr": return checkIssueYear(value);
            case "eyr": return checkExpirationYear(value);
            case "hgt": return checkHeight(value);
            case "hcl": return checkHairColor(value);
            case "ecl": return checkEyeColor(value);
            case "pid": return checkPassportId(value);
        }
        return false;
    }

    private static boolean checkIntValue(String value, int length, int min, int max){
        int intVal = Integer.valueOf(value);
        return value.length() == length && intVal >= min && intVal <= max;
    }

    private static boolean checkBirthYear(String value){
        return checkIntValue(value, 4, 1920, 2002);
    }

    private static boolean checkIssueYear(String value){
        return checkIntValue(value, 4, 2010, 2020);
    }

    private static boolean checkExpirationYear(String value){
        return checkIntValue(value, 4, 2020, 2030);
    }

    private static boolean checkHeight(String value){
        Matcher m = heightPattern.matcher(value);
        if (m.find()) {
            String unit = m.group(2);
            int height = Integer.valueOf(m.group(1));
            if("cm".equals(unit)){
                return height >= 150 && height <= 193;
            } else if("in".equals(unit)){
                return height >= 59 && height <= 76;
            }
        }
        return false;
    }

    private static boolean checkRegex(String value, Pattern pattern){
        return pattern.matcher(value).find();
    }

    private static boolean checkHairColor(String value){
        return checkRegex(value, hairColorPattern);
    }

    private static boolean checkEyeColor(String value){
        return checkRegex(value, eyeColorPattern);
    }

    private static boolean checkPassportId(String value){
        return checkRegex(value, passportIdPattern);
    }
}
