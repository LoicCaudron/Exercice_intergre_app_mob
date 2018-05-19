package com.example.einore.exercice_integre.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.einore.exercice_integre.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //MODIFTEST
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Connection");

        Button connect_button = (Button) findViewById(R.id.connect_button);
        EditText username = (EditText) findViewById(R.id.username);
        EditText code = (EditText) findViewById(R.id.code);

        connect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
