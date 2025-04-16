package com.example.aldenairefitness;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private CountDownTimer countDownTimer;
    private TextView timerText;
    private int timeLeft = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerText = findViewById(R.id.timerText);
        timerText.setText(String.valueOf(timeLeft));

        textToSpeech = new TextToSpeech(this, this);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.setLanguage(Locale.US);
            textToSpeech.speak("Please Do These Exercise For 30 Seconds",
                    TextToSpeech.QUEUE_FLUSH, null, null);
            startTimer();
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = (int) (millisUntilFinished / 1000);
                timerText.setText(String.valueOf(timeLeft));

                if (timeLeft == 2) {
                    textToSpeech.speak("2 seconds left",
                            TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }

            @Override
            public void onFinish() {
                timerText.setText("0");
                textToSpeech.speak("Good Job, Next Exercise",
                        TextToSpeech.QUEUE_FLUSH, null, null);
                finish();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        super.onDestroy();
    }
}