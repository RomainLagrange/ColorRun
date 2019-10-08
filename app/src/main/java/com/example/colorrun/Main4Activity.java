package com.example.colorrun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main4Activity extends AppCompatActivity {

    int score;
    String name;
    String level;
    ListJoueurs listJoueurs = new ListJoueurs();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Globals g = Globals.getInstance();
        score = getIntent().getIntExtra("score",0);
        level = getIntent().getStringExtra("level");
        TextView scoreView = findViewById(R.id.textScore);
        scoreView.setText(String.valueOf(score));
        TextView nameView = findViewById(R.id.textName);
        name = g.getName();
        nameView.setText(name);

    }


    public void savePlayer(Joueur player) throws Exception {


        try {
            loadPlayer();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        File file = new File(this.getFilesDir().getAbsoluteFile()+File.separator+ "dataPlayer2.dat");


        FileOutputStream fis = new FileOutputStream(file);
        ObjectOutputStream objectOutStream = new ObjectOutputStream(fis);

        listJoueurs.add(player);
        objectOutStream.writeInt(listJoueurs.size()); // Save size first
        for(Joueur r:listJoueurs)
            objectOutStream.writeObject(r);
        objectOutStream.close();
    }

    public void loadPlayer() throws Exception  {

        File file = new File(this.getFilesDir().getAbsoluteFile()+File.separator+ "dataPlayer2.dat");

        if (file.exists()) {
            //Do something
            FileInputStream inStream = new FileInputStream(this.getFilesDir().getAbsoluteFile() + File.separator + "dataPlayer2.dat");
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);
            int count = objectInStream.readInt(); // Get the number of players
            for (int c = 0; c < count; c++)
                listJoueurs.add((Joueur) objectInStream.readObject());
            objectInStream.close();
        }
    }

    public void saveClick(View view) throws Exception {
        if (level.equals("h")){
            level = "Mode 2";
        }
        if (level.equals("e")){
            level = "Mode 1";
        }
        Joueur player = new Joueur(name,score, level);
        savePlayer(player);
        AlertDialog dialog = new AlertDialog.Builder(Main4Activity.this)
                .setTitle(R.string.Information)
                .setMessage(R.string.bienenregistre)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        Intent intent = new Intent(Main4Activity.this, Main5Activity.class);
                        startActivity(intent);
                        finish();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(false);

    }
    public void goActivity2(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        finish();
    }
}
