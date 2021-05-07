package com.Team4.TTA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Start extends AppCompatActivity {

    TextView choose;
    ImageButton abc, num;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        choose = findViewById(R.id.choose);
        abc = findViewById(R.id.abc_btn);
        num = findViewById(R.id.number_btn);
        back = findViewById(R.id.back_btn);

        abc.setOnClickListener(view -> {

            Intent intent = new Intent(Start.this, LearnOrPlayABC.class);
            startActivity(intent);
        });

        num.setOnClickListener(view -> {

            Intent intent = new Intent(Start.this, LearnOrPlay123.class);
            startActivity(intent);
        });

        back.setOnClickListener(view -> {

            Intent intent = new Intent(Start.this, Profile.class);
            startActivity(intent);
        });

    }
}