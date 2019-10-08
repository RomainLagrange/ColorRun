package com.example.colorrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Main3Activity extends AppCompatActivity {

    Button timerTextView;
    Button butCorrect;
    long startTime = 0;
    int score = 0;
    ArrayList<String> listColor;
    String difficulty;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    int count = 60;
    int progress = 0;
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            count--;
            progress++;
            timerTextView.setText(count + " s");
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setProgress(progress);
            if (progress<30) {
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#51fa3d")));
            }
            else if (progress<50) {
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#f49409")));
            }
            else {
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#f41b09")));
            }
            if (count==0){
                Intent intent=new Intent(Main3Activity.this, Main4Activity.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
            if (count==10){
                timerTextView.getBackground().setColorFilter(Color.parseColor("#fa3d3d"), PorterDuff.Mode.DARKEN);
            }
            timerHandler.postDelayed(this, 1000);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        timerTextView = findViewById(R.id.timerTextView);
        difficulty = getIntent().getStringExtra("difficulty");
        listColor = generateColorList();

    }


    public void clikcStart(View v) {
        Button b = (Button) v;
        b.setEnabled(false);
        startTime = System.currentTimeMillis();
            timerHandler.postDelayed(timerRunnable, 0);
            b.setText(R.string.Running);
            newResponseColor();
            fillButtonsColor();
        }

    public void checkClick(View view){
        if (view==butCorrect){
            clickGood();
        newResponseColor();
        fillButtonsColor();
        }
        else{
            clickBad();
        }
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
        progress--;
        score+=3;
        Button sc = findViewById(R.id.Score);
        sc.setText("Score : " + score);
    }

    public ArrayList<String> generateColorList(){
        ArrayList<String> colorList = new ArrayList<String>();
        if (difficulty.equals("e")) {
            colorList.add("#3386FF");
            colorList.add("#33FFF0");
            colorList.add("#33FF4F");
            colorList.add("#7133FF");
        }
        else {
            colorList.add("#f41b09");
            colorList.add("#f49b09");
            colorList.add("#e4f705");
            colorList.add("#6ff705");
            colorList.add("#05f7a3");
            colorList.add("#056cf7");
            colorList.add("#7a05f7");
            colorList.add("#f705cb");
        }
        return colorList;
    }

    public static int getRandomNumberInRange(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    public void generateRandomColor(Button button, int min, int max){
        int rand = getRandomNumberInRange(min,max);
        String randColor = listColor.get(rand);
        button.getBackground().setColorFilter(Color.parseColor(randColor), PorterDuff.Mode.DARKEN);

    }

    public void newResponseColor(){
        Button b = findViewById(R.id.buttonReponse);
        int rand;
        if (difficulty.equals("e")){
            rand = getRandomNumberInRange(0,3);}
        else { rand = getRandomNumberInRange(0,7);}
        String randColor = listColor.get(rand);
        b.getBackground().setColorFilter(Color.parseColor(randColor), PorterDuff.Mode.DARKEN);
        butCorrect = generateRandomButton();
        butCorrect.getBackground().setColorFilter(Color.parseColor(randColor), PorterDuff.Mode.DARKEN);
        listColor.remove(rand);
    }

    public Button generateRandomButton(){
        int row = getRandomNumberInRange(0,3);
        int column = getRandomNumberInRange(0,3);
        TableLayout layout = findViewById(R.id.layout1);
        Button but = (Button) ((TableRow)layout.getChildAt(row)).getChildAt(column);
        return but;
    }

    public void fillButtonsColor(){
        TableLayout layout = findViewById(R.id.layout1);
        for(int i=0;i<4;i++){
            for (int j = 0; j < 4; j++) {
                Button but = (Button) ((TableRow) layout.getChildAt(i)).getChildAt(j);
                if (but != butCorrect) {
                    if (difficulty.equals("e")){
                        generateRandomColor(but,0,2);}
                    else { generateRandomColor(but,0,6);}

                }
            }
        }
        listColor = generateColorList();
    }

}





