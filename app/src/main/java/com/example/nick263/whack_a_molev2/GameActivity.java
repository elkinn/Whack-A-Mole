package com.example.nick263.whack_a_molev2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.RunnableFuture;

public class GameActivity extends AppCompatActivity {

    int score;
    int time;
    TextView timeView;
    ImageButton mole1;
    ImageButton mole2;
    ImageButton mole3;
    ImageButton mole4;
    ImageButton mole5;
    ImageButton mole6;
    ImageButton mole7;
    ImageButton[] moles;
    int currentMole;
    CountDownTimer timer;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        timeView = (TextView)findViewById(R.id.timer);

        mole1 = (ImageButton)findViewById(R.id.mole1);
        mole2 = (ImageButton)findViewById(R.id.mole2);
        mole3 = (ImageButton)findViewById(R.id.mole3);
        mole4 = (ImageButton)findViewById(R.id.mole4);
        mole5 = (ImageButton)findViewById(R.id.mole5);
        mole6 = (ImageButton)findViewById(R.id.mole6);
        mole7 = (ImageButton)findViewById(R.id.mole7);
        moles = new ImageButton[]{mole1, mole2, mole3, mole4, mole5, mole6, mole7};

        score = 0;
        time = 60;
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.konouha);
        mediaPlayer.start();

        final Runnable onClick = new Runnable() {
            @Override
            public void run() {
                score++;
                moles[currentMole].setVisibility(View.GONE);
            }
        };

        final Runnable showMole = new Runnable() {
            @Override
            public void run() {
                moles[currentMole].setVisibility(View.VISIBLE);
                moles[currentMole].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        runOnUiThread(onClick);
                    }
                });
            }
        };

        final Runnable runOnTick = new Runnable() {
            @Override
            public void run() {
                time--;
                timeView.setText(Integer.toString(time));
                currentMole = (int)(Math.random()*7);
                runOnUiThread(showMole);
            }
        };

        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                moles[currentMole].setVisibility(View.GONE);
                //runOnUiThread(runOnTick);
                time--;
                timeView.setText(Integer.toString(time));
                currentMole = (int)(Math.random()*7);
                moles[currentMole].setVisibility(View.VISIBLE);
                moles[currentMole].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        score++;
                        moles[currentMole].setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onFinish() {
                mediaPlayer.stop();
                Intent endIntent = new Intent(GameActivity.this, EndActivity.class);
                endIntent.putExtra("score", score);
                startActivity(endIntent);
            }
        }.start();
    }

    public void onClick(View v){
        score++;
        moles[currentMole].setVisibility(View.GONE);
    }
}
