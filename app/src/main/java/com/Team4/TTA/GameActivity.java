package com.Team4.TTA;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.Team4.TTA.helper.QuestionHelper;
import com.Team4.TTA.helper.RandomNumberHelper;
import com.Team4.TTA.helper.SharedPreferencesHelper;
import com.Team4.TTA.helper.ViewHelper;

import java.util.ArrayList;
import java.util.Collections;

import static com.Team4.TTA.helper.Constant.best_score;
import static com.Team4.TTA.helper.Constant.operator;

public class GameActivity extends AppCompatActivity {

    private TextView numberOfQuest;
    private TextView clock;
    private TextView clock_add;
    private int remainingTime, correctCounter = 0;
    private CountDownTimer countDownTimer;
    private ViewHelper viewHelper;
    private ViewFlipper gameViewFlipper, gameRootViewFlipper;
    private int level = 1, score = 0, numberOfQuestion = 1;
    private String ops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setContentView(R.layout.activity_game);
        viewHelper = new ViewHelper(this);

        ops = SharedPreferencesHelper.getStringSharedPreferences(operator);

        layouts();

        remainingTime = getResources().getInteger(R.integer.remaining_time);
        numberOfQuest.setText(String.format("Question: %s", numberOfQuestion));

        startGame(true);

    }

    private void startGame(boolean b) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] res;
        int[] limits = RandomNumberHelper.randomLimits(level);

        res = QuestionHelper.questionMixed(limits[0], limits[1], SharedPreferencesHelper
                .getStringSharedPreferences(operator));

        for (int i = 0; i < 4; i++) {
            list.add(res[i]);
        }

        Collections.shuffle(list);

        gameViewFlipper.addView(gameView(res, list));
        if(!b) {
            gameViewFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_right);
            gameViewFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_left);
            gameViewFlipper.showNext();
            gameViewFlipper.removeViewAt(gameViewFlipper.getDisplayedChild() - 1);
        }

    }

    @SuppressLint("SetTextI18n")
    private View gameView(final int[] res, ArrayList<Integer> list) {
        @SuppressLint("InflateParams") View inflatedView = getLayoutInflater().inflate(R.layout
                .game_play, null);
        TextView optionA = inflatedView.findViewById(R.id.optionA);
        TextView optionB = inflatedView.findViewById(R.id.optionB);
        TextView operation = inflatedView.findViewById(R.id.operatorTextView);
        final TextView correctOrWrong = inflatedView.findViewById(R.id.correctOrWrong);

        TextView[] buttons = new TextView[4];
        buttons[0] = inflatedView.findViewById(R.id.answerA);
        buttons[1] = inflatedView.findViewById(R.id.answerB);
        buttons[2] = inflatedView.findViewById(R.id.answerC);
        buttons[3] = inflatedView.findViewById(R.id.answerD);

        optionA.setText(Integer.toString(res[4]));
        optionB.setText(Integer.toString(res[5]));
        operation.setText(ops);

        buttons[0].setText(Integer.toString(list.get(0)));
        buttons[1].setText(Integer.toString(list.get(1)));
        buttons[2].setText(Integer.toString(list.get(2)));
        buttons[3].setText(Integer.toString(list.get(3)));

        for(int i = 0; i < 4; i++) {
            buttons[i].setOnClickListener((View view) -> {
                if(((TextView) view).getText().equals(Integer.toString(res[0]))) {
                    //True answer
                    correctCounter++;
                    numberOfQuestion++;
                    numberOfQuest.setText(String.format("Question: %s", numberOfQuestion));

//                    updateScoreTextView(score, 10);
                    score+=10;
                    correctOrWrong.setText(getString(R.string.true_answer_message));
                    if((correctCounter + 1) % 10 == 0) {
                        level++;
                        animation();
                        countDownTimer.cancel();
                        startCountDownTimer(remainingTime + 5);
                    }
                    startGame(false);
                }
                else {
                    //Wrong answer
                    view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.smallbigforth));
                    view.animate().alpha(0).setDuration(300);
                    view.setEnabled(false);
                    if(!(score - 3 < 0)) {
//                        updateScoreTextView(score, -3);
                        score-=3;
                    }
                    correctOrWrong.setText(getString(R.string.wrong_answer_message));
                }
            });
        }

        return inflatedView;
    }

    private void layouts() {
        gameViewFlipper = findViewById(R.id.gameViewFlipper);
        gameRootViewFlipper = findViewById(R.id.gameRootViewFlipper);
        clock = findViewById(R.id.clock);
        clock_add = findViewById(R.id.clock_add);
        numberOfQuest = findViewById(R.id.numberOfQuest);
//        TextView scoreTextView = findViewById(R.id.scoreTextView);
    }

    public void startCountDownTimer(int time_) {

        countDownTimer = new CountDownTimer(time_ * 1000 + 100, 1000){

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                clock.setText(millisUntilFinished / 1000 + "s");
                remainingTime = (int) (millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                openDialog();
            }
        }.start();
    }

    private void openDialog()
    {
        countDownTimer.cancel();
        setGameRootViewFlipper();
    }

    private void setGameRootViewFlipper() {
        gameRootViewFlipper.addView(openGameOver());
        gameRootViewFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_right);
        gameRootViewFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_left);
        for(int i = 0; i < gameRootViewFlipper.getChildCount(); i++) {
            View child = gameRootViewFlipper.getChildAt(i);
            child.setEnabled(false);
        }
        gameRootViewFlipper.showNext();
        gameRootViewFlipper.removeViewAt(gameRootViewFlipper.getDisplayedChild() - 1);
    }

    public View openGameOver() {
        @SuppressLint("InflateParams") View inflatedView = getLayoutInflater().inflate(R.layout
                .game_over_view, null);
        LinearLayout linearLayout = inflatedView.findViewById(R.id.gameOverTitleLinearLayout);

        inflatedView.findViewById(R.id.homeButton).setOnClickListener((View view) -> {
            goBackMainMenu();
        });

        inflatedView.findViewById(R.id.playAgainButton).setOnClickListener((View view) -> {
            startActivity(new Intent(this, GameActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();

        });

        TextView scoreBoard = inflatedView.findViewById(R.id.scoreBoardTextView);
        if(score > SharedPreferencesHelper.getIntSharedPreferences(best_score + ops))
            SharedPreferencesHelper.setSharedPreferences(best_score + ops, score);
        scoreBoard.setText(String.format(getString(R.string.score_text), score,
                SharedPreferencesHelper.getIntSharedPreferences(best_score + ops)));

        String[] title = this.getResources().getStringArray(R.array.game_over_title);
        for(String s: title) {
            linearLayout.addView(viewHelper.titleLinearLayout(s));
        }
        return inflatedView;
    }

    private void goBackMainMenu() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    private void animation() {
        clock_add.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeOut)
                .duration(2000)
                .repeat(0)
                .playOn(clock_add);

    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (remainingTime != 0) {
            startCountDownTimer(remainingTime);
        }
    }

    @Override
    public void onBackPressed() {
        countDownTimer.cancel();
        goBackMainMenu();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}