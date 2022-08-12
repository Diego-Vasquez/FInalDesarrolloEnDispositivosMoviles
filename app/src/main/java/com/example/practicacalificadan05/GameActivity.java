package com.example.practicacalificadan05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    Operation operation;

    TextView operation_view;
    String operation_string;

    Button checkerButton;
    Button continueButton;
    int correct_answer;

    TextView score_view;
    int score;

    TextView attempts_view;
    int lifes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = 0;
        lifes = 3;

        operation_view = findViewById(R.id.text_operation);
        score_view = findViewById(R.id.text_score);
        attempts_view = findViewById(R.id.text_attempts);

        checkerButton = findViewById(R.id.checker_button);
        continueButton = findViewById(R.id.continue_button);

        operation_string = getIntent().getStringExtra("OPERATION");
        operation = new Operation(operation_string);



        computeOperation();
        setScoreInDisplay();
        setAttemptsInDisplay();


    }

    public int random() {
        return (int) (Math.random() * 90 + 11);
    }

    public void computeOperation() {
        int a = random();
        int b = random();

        Resources res = getResources();
        correct_answer = operation.compute(a, b);
        if (Objects.equals(operation_string, "+")) operation_view.setText(String.format(res.getString(R.string.plus_operation), a, b));
        if (Objects.equals(operation_string, "-")) operation_view.setText(String.format(res.getString(R.string.subst_operation), a, b));
        if (Objects.equals(operation_string, "x")) operation_view.setText(String.format(res.getString(R.string.mult_operation), a, b));
    }

    public void setScoreInDisplay(){
        Resources res = getResources();
        score_view.setText(String.format(res.getString(R.string.score_text), score));
    }

    public void setAttemptsInDisplay(){
        Resources res = getResources();
        attempts_view.setText(String.format(res.getString(R.string.attempts_text), lifes));
    }
}