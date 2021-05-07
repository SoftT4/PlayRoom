package com.Team4.TTA.helper;


import static com.Team4.TTA.helper.Constant.add;
import static com.Team4.TTA.helper.Constant.best_score;
import static com.Team4.TTA.helper.Constant.div;
import static com.Team4.TTA.helper.Constant.mul;
import static com.Team4.TTA.helper.Constant.operator;
import static com.Team4.TTA.helper.Constant.sharedPreferences;
import static com.Team4.TTA.helper.Constant.sub;

public class SharedPreferencesHelper {
    public static void setSharedPreferences(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static void setSharedPreferences(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public static void setSharedPreferences(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static int getIntSharedPreferences(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public static String getStringSharedPreferences(String key) {
        return sharedPreferences.getString(key, " ");
    }

    public static boolean getBoolSharedPreferences(String key) {
        return sharedPreferences.getBoolean(key, true);
    }

    public static void resetSharedPreferences() {
        boolean music = sharedPreferences.getBoolean(Constant.music, true);
        boolean sound = sharedPreferences.getBoolean(Constant.sound, true);
        sharedPreferences.edit().clear().apply();

        setSharedPreferences(Constant.music, music);
        setSharedPreferences(Constant.sound, sound);
        setSharedPreferences("first_run", false);
        setSharedPreferences(operator, "+");
        setSharedPreferences(best_score + add, 0);
        setSharedPreferences(best_score + sub, 0);
        setSharedPreferences(best_score + mul, 0);
        setSharedPreferences(best_score + div, 0);

    }
}

