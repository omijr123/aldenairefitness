package com.example.aldenairefitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_selection);

        Button bellyFatBtn = findViewById(R.id.bellyFatBtn);
        Button weightGainBtn = findViewById(R.id.weightGainBtn);

        bellyFatBtn.setOnClickListener(v -> {
            startTimerActivity("Belly Fat");
        });

        weightGainBtn.setOnClickListener(v -> {
            startTimerActivity("Weight Gain");
        });
    }

    private void startTimerActivity(String exerciseType) {
        Intent intent = new Intent(this, TimerActivity.class);
        intent.putExtra("exercise_type", exerciseType);
        startActivity(intent);
    }
}