package com.example.colorrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    int score;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        score = getIntent().getIntExtra("score",0);
        name = getIntent().getStringExtra("name");
        TextView scoreView = findViewById(R.id.textScore);
        scoreView.setText(String.valueOf(score));
        TextView nameView = findViewById(R.id.textName);
        nameView.setText(name);
    }


    public void savePlayer(Joueur player) throws Exception {
        FileOutputStream fos = getApplicationContext().openFileOutput("dataPlayer", Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        ArrayList<Joueur> playerList = loadPlayer();
        os.writeObject(playerList);
        os.close();
        fos.close();

    }

    public ArrayList<Joueur> loadPlayer() throws IOException, ClassNotFoundException {


        File file = new File(getApplicationContext().getFilesDir(), "dataPlayer");
        if (file.exists()) {
            //Do something
            FileInputStream fis = getApplicationContext().openFileInput("dataPlayer");
            ObjectInputStream is = new ObjectInputStream(fis);
            ArrayList<Joueur> playerList = (ArrayList<Joueur>) is.readObject();
            is.close();
            fis.close();
            return playerList;
        } else {
            //Nothing
            ArrayList<Joueur> toto = new ArrayList<>();
            return toto;
        }
    }

    public void saveClick(View view) throws Exception {
        Joueur player = new Joueur(name,score);
        savePlayer(player);
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }


}
