package com.Team4.TTA;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.Team4.TTA.helper.SharedPreferencesHelper;
import com.Team4.TTA.helper.ViewHelper;


import static com.Team4.TTA.helper.Constant.operator;


public class MainActivity extends AppCompatActivity {

    private ViewFlipper rootViewFlipper;
    private ViewHelper viewHelper;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewHelper = new ViewHelper(this);

        rootViewFlipper = findViewById(R.id.rootViewFlipper);
        rootViewFlipper.setDisplayedChild(0);

        findViewById(R.id.cloud1).startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_x));
        findViewById(R.id.cloud2).startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_x_reverse));

        setFirstView();
        setSecondView();
    }

    private void setSecondView() {
        LinearLayout linearLayout = findViewById(R.id.settingsTitleLinearLayout);

        String[] mainTitle = getResources().getStringArray(R.array.settings_title);
        for(String s: mainTitle) {
            linearLayout.addView(viewHelper.titleLinearLayout(s));
        }

        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.clearCheck();
        switch (SharedPreferencesHelper.getStringSharedPreferences(operator)) {
            case "+":
                radioGroup.check(R.id.addition);
                break;
            case "-":
                radioGroup.check( R.id.subtraction);
                break;
            case "*":
                radioGroup.check(R.id.multiplication);
                break;
            default:
                radioGroup.check(R.id.division);
                break;
        }


    }

    public void radioButtonOnClick(View view) {
        radioGroup.clearCheck();
        radioGroup.check((view).getId());
        SharedPreferencesHelper.setSharedPreferences(operator,
                ((RadioButton) view).getText().toString());
    }

    private void setFirstView() {
        LinearLayout linearLayout = findViewById(R.id.mainTitleLinearLayout);

        String[] mainTitle = getResources().getStringArray(R.array.main_title);
        for(String s: mainTitle) {
            linearLayout.addView(viewHelper.titleLinearLayout(s));
        }
    }

    public void playOnClick(View view) {
        startActivity(new Intent(this, GameActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    public void settingsOnClick(View view) {
        rootViewFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_right);
        rootViewFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_left);
        rootViewFlipper.showNext();
    }

    public void mainBackButtonOnClick(View view) {
        rootViewFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_left);
        rootViewFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_right);
        rootViewFlipper.setDisplayedChild(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if(rootViewFlipper.getDisplayedChild() != 0) {
            rootViewFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_left);
            rootViewFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_right);
            rootViewFlipper.setDisplayedChild(0);
        }
    }
}