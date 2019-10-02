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
import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ArrayList<Joueur> joueur= new ArrayList<>();
        joueur.add(new Joueur("Romain", 244));
        joueur.add(new Joueur("Marion", 242));
        ((ListView)findViewById(R.id.list_view)).setAdapter(new ListViewAdapter(joueur));

    }
}
