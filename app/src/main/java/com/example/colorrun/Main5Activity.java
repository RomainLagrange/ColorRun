package com.example.colorrun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);



        final String[] col1 = {"JOHN", "ROMAIN", "BOB", "GERTRUDE", "LORENZO"};
        final String[] col2 = {"5673", "8765", "9876", "6534", "198"};

        TableRow row; // ligne
        TextView tv1, tv2; //  cellules

        // pour chaque ligne faire une boucle


        for (
                int i = 0;
                i < col1.length; i++) {
            row = new TableRow(this); // création d'une nouvelle ligne
            TableLayout table = (TableLayout) findViewById(R.id.table);
            tv1 = new TextView(this); // création cellule
            tv1.setText(col1[i]); // ajout du texte
            tv1.setGravity(10); // centrage dans la cellule
            // adaptation de la largeur de colonne à l'écran :
            tv1.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            // idem 2ème cellule
            tv2 = new TextView(this);
            tv2.setText(col2[i]);
            tv2.setGravity(10);
            tv2.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            // ajout des cellules à la ligne
            row.addView(tv1);
            row.addView(tv2);

            // ajout de la ligne au tableau
            table.addView(row);
        }




    }
    private void saveInterne() throws IOException {

        FileOutputStream outputStream= openFileOutput("TABLEAUSCORE",MODE_PRIVATE);
        String audio_name="TEST METHODE FICHIER INTERNE";
        outputStream.write(audio_name.getBytes());
        outputStream.close();

    }
    private String getFromInterne() throws IOException {
        String value = null;

        FileInputStream inputStream=openFileInput("TABLEAUSCORE");
        StringBuilder stringb= new StringBuilder();
        int content;
        while ((content=inputStream.read())!=-1){
            value = String.valueOf(stringb.append((char)content));
        }

        return value ;
    }
}
