package com.example.colorrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Main3Activity extends AppCompatActivity {

    Button timerTextView;
    long startTime = 0;
    int score = 0;
    ArrayList<String> listColor = generateColorList();

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

        Button buttonReponse = findViewById(R.id.buttonReponse);
        buttonReponse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button butCorrect = newResponseColor();
            }
        });

    }


    public void onClick(View v) {
        Button b = (Button) v;
        b.setEnabled(false);
        startTime = System.currentTimeMillis();
            timerHandler.postDelayed(timerRunnable, 0);
            b.setText("En cours");
            Button but = newResponseColor();
            fillButtonsColor(but);
        }


    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.buttonClock);
        b.setText("start");
    }

    public void clickBad()
    {
        score--;
        Button sc = findViewById(R.id.Score);
        sc.setText("Score : " + score);
    }

    public void clickGood()
    {
        count++;
        score+=3;
        Button sc = findViewById(R.id.Score);
        sc.setText("Score : " + score);
    }

    public void generateRandomColor(Button button, ArrayList<String> listColor){
        int rand = getRandomNumberInRange(0,3);
        String randColor = listColor.get(rand);
        button.getBackground().setColorFilter(Color.parseColor(randColor), PorterDuff.Mode.DARKEN);

    }

    public static int getRandomNumberInRange(int min, int max) {

        return (int)(Math.random() * ((max - min) + 1)) + min;

    }


    public ArrayList<String> generateColorList(){
        ArrayList<String> colorList = new ArrayList<String>();
        colorList.add("#3386FF");
        colorList.add("#33FFF0");
        colorList.add("#33FF4F");
        colorList.add("#7133FF");
        return colorList;
    }

    public Button newResponseColor(){
        Button b = findViewById(R.id.buttonReponse);
        int rand = getRandomNumberInRange(0,3);
        String randColor = listColor.get(rand);
        b.getBackground().setColorFilter(Color.parseColor(randColor), PorterDuff.Mode.DARKEN);
        Button but = generateRandomButton();
        but.getBackground().setColorFilter(Color.parseColor(randColor), PorterDuff.Mode.DARKEN);
        return  but;
    }

    public Button generateRandomButton(){
        int row = getRandomNumberInRange(0,3);
        int column = getRandomNumberInRange(0,3);
        TableLayout layout = findViewById(R.id.layout1);
        Button but = (Button) ((TableRow)layout.getChildAt(row)).getChildAt(column);
        return but;
    }

    public void fillButtonsColor(Button butCorrect){
        TableLayout layout = findViewById(R.id.layout1);
        for(int i=0;i<4;i++){
            for (int j = 0; j < 4; j++) {
                Button but = (Button) ((TableRow) layout.getChildAt(i)).getChildAt(j);
                if (but != butCorrect) {
                    generateRandomColor(but, listColor);
                }
            }
        }
    }

}

