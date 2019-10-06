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
    Button butCorrect;
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
                intent.putExtra("score", score);
                startActivity(intent);
            }
            if (count==5){
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

    }


    public void onClick(View v) {
        Button b = (Button) v;
        b.setEnabled(false);
        startTime = System.currentTimeMillis();
            timerHandler.postDelayed(timerRunnable, 0);
            b.setText("En cours");
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

    public void generateRandomColor(Button button, int min, int max){
        int rand = getRandomNumberInRange(min,max);
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

    public void newResponseColor(){
        Button b = findViewById(R.id.buttonReponse);
        int rand = getRandomNumberInRange(0,3);
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
                    generateRandomColor(but,0,2);
                }
            }
        }
        listColor = generateColorList();
    }

}

