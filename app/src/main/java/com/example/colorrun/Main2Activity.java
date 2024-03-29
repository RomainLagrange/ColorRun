package com.example.colorrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void goActivity3easy(View view) {
        Intent intent = new Intent(this, Main3Activity.class);
        intent.putExtra("difficulty", "e");
        startActivity(intent);
    }

    public void goActivity3hard(View view) {
        Intent intent = new Intent(this, Main3Activity.class);
        intent.putExtra("difficulty", "h");
        startActivity(intent);
    }

    public void goActivity5(View view) {
        Intent intent = new Intent(this, Main5Activity.class);
        startActivity(intent);
    }
}
