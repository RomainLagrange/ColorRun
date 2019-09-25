package com.example.colorrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    Button timerTextView;
    long startTime = 0;
    int score = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    int count = 60;
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            count--;
            timerTextView.setText(count + " s");
            if (count==0){
                Intent intent=new Intent(Main3Activity.this, Main4Activity.class);
                startActivity(intent);
            }
            timerHandler.postDelayed(this, 1000);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        timerTextView = (Button) findViewById(R.id.timerTextView);

    }

    Handler h3=new Handler();

    public void onClick(View v) {
        Button b = (Button) v;
        b.setEnabled(false);
        startTime = System.currentTimeMillis();
            timerHandler.postDelayed(timerRunnable, 0);
            b.setText("En cours");
        }


    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.buttonClock);
        b.setText("start");
    }

    public void clickBad(View view)
    {
        score--;
        Button sc = findViewById(R.id.Score);
        sc.setText("Score : " + score);
    }

    public void clickGood(View view)
    {
        count++;
        score+=3;
        Button sc = findViewById(R.id.Score);
        sc.setText("Score : " + score);
    }
}
