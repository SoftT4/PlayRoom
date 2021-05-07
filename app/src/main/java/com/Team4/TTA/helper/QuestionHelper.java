package com.Team4.TTA.helper;

import java.util.ArrayList;
import java.util.Random;

import static com.Team4.TTA.helper.Constant.addition;
import static com.Team4.TTA.helper.Constant.division;
import static com.Team4.TTA.helper.Constant.multiplication;
import static com.Team4.TTA.helper.Constant.subtraction;

public class QuestionHelper {

    public static int[] questionAddition(int lowerLimit, int upperLimit) {
        int[] res = new int[6];
        int optionA = new Random().nextInt(upperLimit) + lowerLimit;
        int optionB = new Random().nextInt(upperLimit) + lowerLimit;

        res[0] = optionA + optionB;
        for(int i = 0; i < 3; i++) {
            int answer = new Random().nextInt(res[0]) + lowerLimit;
            while(answer == res[i] || answer == res[0]){
                answer = new Random().nextInt(upperLimit) + lowerLimit;
            }
            res[i + 1] = answer;
        }
        res[4] = optionA;
        res[5] = optionB;
        return res;
    }

    public static int[] questionSubtraction(int lowerLimit, int upperLimit) {
        int[] res = new int[6];
        int optionA = new Random().nextInt(upperLimit) + lowerLimit;
        int optionB = new Random().nextInt(optionA) + lowerLimit;
        while (optionA == 1)
            optionA = new Random().nextInt(upperLimit) + lowerLimit;
        if(optionA <= optionB) return questionSubtraction(lowerLimit, upperLimit);
        res[0] = optionA - optionB;
        for(int i = 0; i < 3; i++) {
            int answer = new Random().nextInt(res[0]) + lowerLimit;

            while(answer == res[i] || answer == res[0]){
                answer = new Random().nextInt(upperLimit) + lowerLimit;
            }
            res[i + 1] = answer;
        }
        res[4] = optionA;
        res[5] = optionB;
        return res;
    }

    public static int[] questionMultiplication(int lowerLimit, int upperLimit) {
        int[] res = new int[6];
        int optionA = new Random().nextInt(upperLimit) + lowerLimit;
        int optionB = new Random().nextInt(upperLimit) + lowerLimit;
        res[0] = optionA * optionB;

        for(int i = 0; i < 3; i++) {
            int answer = (new Random().nextInt(res[0] / 2 + res[0]));
            while(answer == res[i] || answer == res[0]){
                answer = (new Random().nextInt(res[0] / 2 + res[0]));
                if(answer == 1 || answer == 0) {
                    questionMultiplication(lowerLimit, upperLimit);
                }
            }
            res[i + 1] = answer;
        }
        res[4] = optionA;
        res[5] = optionB;
        return res;
    }

    public synchronized static int[] questionDivision(int lowerLimit, int upperLimit) {
        int[] res = new int[6];
        int optionA = new Random().nextInt(upperLimit) + lowerLimit;
        while (optionA == 1)
            optionA = new Random().nextInt(upperLimit) + lowerLimit;
        int optionB = divisors(optionA);
        if(optionB == -1) return questionDivision(lowerLimit, upperLimit);

        res[0] = optionA / optionB;
        for(int i = 0; i < 3; i++) {
            int answer = new Random().nextInt(res[0]) + (upperLimit - lowerLimit) / 2;
            while(answer == res[i] || answer == res[0]){
                answer = new Random().nextInt(upperLimit / 2) + lowerLimit;
            }
            res[i + 1] = answer;
        }
        res[4] = optionA;
        res[5] = optionB;
        return res;
    }

    private static int divisors(int row) {
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = row - 1; i > 1; i--) {
            if(row % i == 0) {
                res.add(i);
            }
        }
        if(res.size() == 0) return -1;
        return res.get(new Random().nextInt(res.size()));
    }

    public static int[] questionMixed(int lowerLimit, int upperLimit, String gameType) {
        switch (gameType) {
            case "+":
                return questionAddition(lowerLimit, upperLimit);
            case "-":
                return questionSubtraction(lowerLimit, upperLimit);
            case "*":
                return questionMultiplication(lowerLimit, upperLimit);
            default:
                return questionDivision(lowerLimit, upperLimit);
        }
    }

    public static String operatorChoice(String type) {
        if(type.equals(addition)) return "+";
        if(type.equals(subtraction)) return "-";
        if(type.equals(multiplication)) return "*";
        else return "/";
    }

    public static String operatorToGameType(String type) {
        if(type.equals("+")) return addition;
        if(type.equals("-")) return subtraction;
        if(type.equals("*")) return multiplication;
        else return division;
    }

    public static String gameTypeRandom(){
        ArrayList<String> list = new ArrayList<>();
        list.add(addition);
        list.add(subtraction);
        list.add(multiplication);
        list.add(division);
        return list.get(new Random().nextInt(4));
    }
}
