package com.Team4.TTA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LearnOrPlay123 extends AppCompatActivity {

    TextView choose;
    Button learn, play, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_or_play123);

        choose = findViewById(R.id.choose);
        learn = findViewById(R.id.learn_btn);
        play = findViewById(R.id.play_btn);
        back = findViewById(R.id.back_btn);

        back.setOnClickListener(view -> {
            Intent intent = new Intent(LearnOrPlay123.this, Start.class);
            startActivity(intent);
        });

        learn.setOnClickListener(view->{
            Intent easyI = new Intent(LearnOrPlay123.this,learn_123.class);
            startActivity(easyI);

        });

        play.setOnClickListener(view->{
            Intent easyI = new Intent(LearnOrPlay123.this,MainActivity.class);
            startActivity(easyI);

        });

    }
}