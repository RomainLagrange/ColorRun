package com.example.colorrun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
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

        ArrayList<Joueur> listJoueurs = null;
        try {
            listJoueurs = loadPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        final RecyclerView rv = (RecyclerView) findViewById(R.id.listPlayer);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecycleViewAdapter(listJoueurs));

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
            toto.add(new Joueur("Marion",251));
            toto.add(new Joueur("Julien",25));
            return toto;
        }
    }


}
