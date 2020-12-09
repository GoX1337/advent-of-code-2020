package com.gox.adventofcode.days;

import com.gox.adventofcode.utils.FileUtils;
import java.util.List;

public class Day5 {

    public static int resolve() {
        List<String> lines = FileUtils.parseFileLines("resources/5.txt");
        int maxBoardingPass = -1;
        for(String seatCode : lines){
            int seat = processSeat(seatCode);
            if(maxBoardingPass <= seat){
                maxBoardingPass = seat;
            }
        }
        return maxBoardingPass;
    }

    private static int processSeat(String seatCode) {
        if(seatCode != null && seatCode.length() != 10){
            return -1;
        }
        int row = processRow(seatCode);
        int col = processColumn(seatCode);
        return row * 8 + col;
    }

    private static int processRow(String seatCode) {
        String seat = seatCode.substring(0, 7);
        int min = 0;
        int max = 127;
        for(char c : seat.toCharArray()){
            switch (c){
                case 'F':
                    max =  Math.round(max - (max - min) /2) - 1;
                    break;
                case 'B':
                    min = Math.round(min + (max - min) / 2) + 1;
                    break;
            }
            if(min == max){
                return min;
            }
        }
        return -1;
    }

    private static int processColumn(String seatCode) {
        String seat = seatCode.substring(7, 10);
        int min = 0;
        int max = 7;
        for(char c : seat.toCharArray()){
            switch (c){
                case 'L':
                    max =  Math.round(max - (max - min) /2) - 1;
                    break;
                case 'R':
                    min = Math.round(min + (max - min) / 2) + 1;
                    break;
            }
            if(min == max){
                return min;
            }
        }
        return -1;
    }
}