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

public class SetActivity extends AppCompatActivity {

    EditText tempMin;
    EditText tempMax;
    EditText humMin;
    EditText humMax;
    EditText battMin;
    EditText battMax;
    Button save;

    UserBDD userBdd;
    int id;
    User currentUser;

    // Intent CheckActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_activity);
        setTitle("Paramétrage");

        TextView textView = findViewById(R.id.message);
        String Hello = "Bonjour" + getIntent().getStringExtra("name")+" ! ";
        textView.setText(Hello);

        tempMin = findViewById(R.id.tempMin);
        tempMax = findViewById(R.id.tempMax);
        humMin = findViewById(R.id.humMin);
        humMax = findViewById(R.id.humMax);
        battMin = findViewById(R.id.battMax);
        battMax = findViewById(R.id.battMax);


        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                id = (int) bundle.get("ID");
            } else {
                id = 0;
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }


        try {
            userBdd = new UserBDD(SetActivity.this);
            userBdd.openForRead();
            currentUser = userBdd.getUser(id);
            userBdd.close();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float tempmin, tempmax, hummin, hummax;
                int battmin, battmax;

                try{

                    tempmin = Float.parseFloat(tempMin.getText().toString());
                    tempmax = Float.parseFloat(tempMax.getText().toString());
                    hummin = Float.parseFloat(humMin.getText().toString());
                    hummax = Float.parseFloat(humMax.getText().toString());
                    battmin = Integer.parseInt(battMin.getText().toString());
                    battmax = Integer.parseInt(battMax.getText().toString());
                } catch (Exception exception) {
                    Toast.makeText(SetActivity.this, "Veuillez vérifier les données entrées !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(hummin >= 0 && hummin <=100 && hummax >=0 && hummax <=100 && battmin >=0 && battmin <= 100 && battmax >=0 && battmax <= 100){

                    currentUser.setT_min(tempmin);
                    currentUser.setT_max(tempmax);
                    currentUser.setHumidity_min(hummin);
                    currentUser.setHumidity_max(hummax);
                    currentUser.setBattery_min(battmin);
                    currentUser.setBattery_max(battmax);
                    userBdd.openForWrite();
                    userBdd.updateUser(currentUser.getId(), currentUser);
                    userBdd.close();

                    /*
                    CheckActivity = new Intent(SetActivity.this, CheckActivity.class);
                    CheckActivity.putExtra("ID", currentUser.getId());
                    startActivity(CheckActivity);
                    */
                }

                else{
                    Toast.makeText(SetActivity.this, "Les valeurs doivent être comprises entre 0 et 100.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}