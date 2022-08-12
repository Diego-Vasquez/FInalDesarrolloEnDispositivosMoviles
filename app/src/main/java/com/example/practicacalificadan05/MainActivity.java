package com.example.practicacalificadan05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button_plus;
    Button button_subs;
    Button button_multi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_plus = findViewById(R.id.plus_button);
        button_subs = findViewById(R.id.substraction_button);
        button_multi = findViewById(R.id.multiplication_button);

        button_plus.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("OPERATION", "+");
            startActivity(intent);
            finish();
        });

        button_subs.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("OPERATION", "-");
            startActivity(intent);
            finish();
        });

        button_multi.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("OPERATION", "x");
            startActivity(intent);
            finish();
        });
    }
}