package com.example.colorrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void activity(View view){
        EditText nameField = (EditText)findViewById(R.id.nameEdit);
        name=nameField.getText().toString();
        Globals g = Globals.getInstance();
        g.setName(name);
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}
