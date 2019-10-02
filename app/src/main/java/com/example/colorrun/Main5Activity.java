package com.example.colorrun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ArrayList<Joueur> listJoueurs= null;
        try {
            listJoueurs = loadPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        listJoueurs.add(new Joueur("Romain", 244));
        listJoueurs.add(new Joueur("Marion", 242));
        ((ListView)findViewById(R.id.list_view)).setAdapter(new ListViewAdapter(listJoueurs));

    }

    public ArrayList<Joueur> loadPlayer() throws IOException, ClassNotFoundException {
        FileInputStream fis = getApplicationContext().openFileInput("dataPlayer");
        ObjectInputStream is = new ObjectInputStream(fis);
        ArrayList<Joueur> playerList = (ArrayList<Joueur>) is.readObject();
        is.close();
        fis.close();
        return playerList;
    }
}
