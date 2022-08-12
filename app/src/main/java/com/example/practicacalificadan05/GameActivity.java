package com.example.practicacalificadan05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    int remainAttempts;

    EditText guessNumber;
    boolean indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = 0;
        remainAttempts = 3;
        indicator = false;

        operation_view = findViewById(R.id.text_operation);
        score_view = findViewById(R.id.text_score);
        attempts_view = findViewById(R.id.text_attempts);

        guessNumber = findViewById(R.id.edit_text_answer);

        checkerButton = findViewById(R.id.checker_button);
        continueButton = findViewById(R.id.continue_button);
        continueButton.setVisibility(View.INVISIBLE);

        checkerButton.setOnClickListener(view -> {
            String sGuess = guessNumber.getText().toString();

            if (indicator) {
                guessNumber.setText("");
                Toast.makeText(GameActivity.this, R.string.press_continue, Toast.LENGTH_LONG).show();
                return;
            }

            if (sGuess.equals("")) {
                Toast.makeText(GameActivity.this, R.string.bad_imput, Toast.LENGTH_LONG).show();
                return;
            }

            int iGuess = Integer.parseInt(sGuess);

            if (iGuess == correct_answer) {
                continueButton.setVisibility(View.VISIBLE);
                score+=10;

                if (score >= 20){
                    Intent intent = new Intent(GameActivity.this, LastActivity.class);
                    intent.putExtra("WINNER", true);
                    intent.putExtra("SCORE", score);
                    intent.putExtra("REMAINED_ATTEMPTS", remainAttempts);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(GameActivity.this, R.string.continue_text, Toast.LENGTH_LONG).show();
                    indicator = true;
                    guessNumber.setText("");
                    setScoreInDisplay();
                }

            }
            else{
                score = Math.max((score - 5), 0);

                if(remainAttempts - 1 <0){
                    Intent intent = new Intent(GameActivity.this, LastActivity.class);
                    intent.putExtra("WINNER", false);
                    intent.putExtra("SCORE", score);
                    startActivity(intent);
                    finish();
                }
                else{
                    remainAttempts --;
                }
                setScoreInDisplay();
                setAttemptsInDisplay();
            }
        });

        continueButton.setOnClickListener(view -> {
            computeOperation();
            setScoreInDisplay();
            setAttemptsInDisplay();
            continueButton.setVisibility(View.INVISIBLE);
            indicator = false;
        });

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
        if (Objects.equals(operation_string, "+"))
            operation_view.setText(String.format(res.getString(R.string.plus_operation), a, b));
        if (Objects.equals(operation_string, "-"))
            operation_view.setText(String.format(res.getString(R.string.subst_operation), a, b));
        if (Objects.equals(operation_string, "x"))
            operation_view.setText(String.format(res.getString(R.string.mult_operation), a, b));
    }

    public void setScoreInDisplay() {
        Resources res = getResources();
        score_view.setText(String.format(res.getString(R.string.score_text), score));
    }

    public void setAttemptsInDisplay() {
        Resources res = getResources();
        attempts_view.setText(String.format(res.getString(R.string.attempts_text), remainAttempts));
    }
}