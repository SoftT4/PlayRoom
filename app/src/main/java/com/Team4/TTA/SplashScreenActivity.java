package com.Team4.TTA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.Team4.TTA.helper.SharedPreferencesHelper;

import static com.Team4.TTA.helper.Constant.sharedPreferences;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        TextView splashTextView = findViewById(R.id.splashTextView);


        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .repeat(0)
                .playOn(splashTextView);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, Login.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        }, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //If you want to change default parameters delete app and rerun
        if (SharedPreferencesHelper.getBoolSharedPreferences("first_run")) {
            // Do first run stuff here then set 'first_run' as false
            // using the following line to edit/commit prefs
            SharedPreferencesHelper.resetSharedPreferences();
        }
    }
}