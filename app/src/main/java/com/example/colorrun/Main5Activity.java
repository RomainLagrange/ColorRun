package com.example.colorrun;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Comparator;

public class Main5Activity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        ListJoueurs listJoueurs = new ListJoueurs();
        try {
            listJoueurs = loadPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        listJoueurs.sort(Comparator.comparing(Joueur::getScore).reversed());

        final RecyclerView rv = (RecyclerView) findViewById(R.id.listPlayer);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecycleViewAdapter(listJoueurs));

    }

    public ListJoueurs loadPlayer() throws IOException, ClassNotFoundException {


        File file = new File(this.getFilesDir().getAbsoluteFile()+File.separator+ "dataPlayer2.dat");

        if (file.exists()) {
            //Do something
            FileInputStream inStream = new FileInputStream(this.getFilesDir().getAbsoluteFile()+File.separator+ "dataPlayer2.dat");
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);
            int count = objectInStream.readInt(); // Get the number of players
            ListJoueurs lJ = new ListJoueurs();
            for (int c=0; c < count; c++)
                lJ.add((Joueur) objectInStream.readObject());
            objectInStream.close();
            return lJ;
        } else {
            //Nothing
            ListJoueurs toto = new ListJoueurs();
            toto.add(new Joueur("Marion",251, "Mode 1"));
            toto.add(new Joueur("Julien",25, "Mode 2"));
            return toto;
        }
    }


}