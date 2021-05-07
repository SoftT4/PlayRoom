package com.Team4.TTA;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LearnOrPlayABC extends AppCompatActivity {

    TextView choose;
    Button learn,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_abc);

        choose = findViewById(R.id.choose);
        learn = findViewById(R.id.learn_btn);
        back = findViewById(R.id.back_btn);

        back.setOnClickListener(view -> {

            Intent intent = new Intent(LearnOrPlayABC.this,Start.class);
            startActivity(intent);
        });

        learn.setOnClickListener(view->{
            Intent easyI = new Intent(LearnOrPlayABC.this,learn_alphabet.class);
            startActivity(easyI);

        });


    }
}