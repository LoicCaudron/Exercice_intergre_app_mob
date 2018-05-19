package com.example.einore.exercice_integre.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.einore.exercice_integre.R;
import com.example.einore.exercice_integre.model.User;
import com.example.einore.exercice_integre.model.UserBDD;

import static android.text.TextUtils.isEmpty;

public class Main2Activity extends AppCompatActivity {

    EditText tempMin;
    EditText tempMax;
    EditText humMin;
    EditText humMax;
    EditText battMin;
    EditText battMax;
    Button save;

    UserBDD userBdd;
    int id;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        setTitle("Conditions");

        tempMin = findViewById(R.id.tempMin);
        tempMax = findViewById(R.id.tempMax);
        humMin = findViewById(R.id.humMin);
        humMax = findViewById(R.id.humMax);
        battMin = findViewById(R.id.battMax);
        battMax = findViewById(R.id.battMax);


        try {
            Bundle b = getIntent().getExtras();
            if (b != null) {
                id = (int) b.get("ID");
            } else {
                id = 0;
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }


        try {
            userBdd = new UserBDD(Main2Activity.this);
            userBdd.openForRead();
            user = userBdd.getUser(id);
            userBdd.close();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }



    }

}