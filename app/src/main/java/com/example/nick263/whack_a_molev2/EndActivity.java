package com.example.nick263.whack_a_molev2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EndActivity extends AppCompatActivity {

    int score;
    Button button;
    TextView scoreTextView;
    TextView scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        score = getIntent().getIntExtra("SCORE", 0);
        button = (Button)findViewById(R.id.button);
        scoreTextView = (TextView)findViewById(R.id.scoreText);
        scoreView = (TextView)findViewById(R.id.score);

        scoreView.setText(Integer.toString(score));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
