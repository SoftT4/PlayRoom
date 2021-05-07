package com.Team4.TTA.helper;

public class RandomNumberHelper {

    public static int[] randomLimits(int lvl) {
        int[] limits = new int[2];
        if(lvl == 1) {
            limits[0] = 1;
            limits[1] = 10;
            return limits;
        }
        else if(lvl == 2) {
            limits[0] = 10;
            limits[1] = 30;
            return limits;
        }
        else if(lvl == 3) {
            limits[0] = 30;
            limits[1] = 70;
            return limits;
        }
        else if(lvl == 4) {
            limits[0] = 70;
            limits[1] = 150;
            return limits;
        }
        else if(lvl == 5) {
            limits[0] = 150;
            limits[1] = 250;
            return limits;
        }
        else {
            limits[0] = 250;
            limits[1] = 500;
            return limits;
        }
    }

}

