package com.gox.adventofcode.days;

import com.gox.adventofcode.utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day5 {

    public static String resolve() {
        List<String> lines = FileUtils.parseFileLines("resources/5.txt");
        int maxBoardingPass = -1;
        List<Integer> seats = new ArrayList<>();
        for(String seatCode : lines){
            int seat = processSeat(seatCode);
            seats.add(seat);
            if(maxBoardingPass <= seat){
                maxBoardingPass = seat;
            }
        }

        int mySeat = -1;
        Collections.sort(seats);
        for(int i = 0, j = 1; i < seats.size() - 1; i++, j++){
            if(seats.get(i) + 1 != seats.get(j)){
                mySeat = seats.get(i) + 1;
            }
        }
        return "max seat number: " + maxBoardingPass + "; my seat: " + mySeat;
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