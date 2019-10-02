package com.example.colorrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    ArrayList<Joueur> playerList = new ArrayList<>();

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
        loadPlayer();
        playerList.add(player);
        os.writeObject(playerList);
        os.close();
        fos.close();
    }

    public void loadPlayer() throws Exception  {

        try
        {
            FileInputStream fis = new FileInputStream("dataPlayer");
            ObjectInputStream ois = new ObjectInputStream(fis);

            playerList = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        } catch (FileNotFoundException fnf){
            fnf.printStackTrace();
            FileOutputStream fos = getApplicationContext().openFileOutput("dataPlayer", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(playerList);
            os.close();
            fos.close();
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    public void saveClick(View view) throws Exception {
        Joueur player = new Joueur(name,score);
        savePlayer(player);
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }


}
