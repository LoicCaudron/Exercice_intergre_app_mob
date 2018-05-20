package com.example.einore.exercice_integre.controller;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {


    UserBDD userBdd;

    Intent main2Activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //MODIFTEST
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Connexion");

        final Button connect_button = (Button) findViewById(R.id.connect_button);
        final EditText usernameEntry = (EditText) findViewById(R.id.username);
        final EditText codeEntry = (EditText) findViewById(R.id.code);

        User user = new User ("Loic", 1234);

        userBdd = new UserBDD(this);
        userBdd.openForWrite();
        userBdd.insertUser (user);


        connect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEntry.getText().toString();
                int code = Integer.parseInt(codeEntry.getText().toString());

                if(0000<=code && code<=9999){

                    userBdd.openForRead();    //ouverture de la base de données
                    User user = userBdd.getUser(username,code);

                    if(user != null){  // si le getUser renvoie qqch (les entrées sont dans la BDD), on passe à l'activité suivante

                        main2Activity = new Intent(MainActivity.this, Main2Activity.class);
                        userBdd.close();
                        startActivity(main2Activity);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Identification non valide",Toast.LENGTH_LONG).show();
                    }


                }
                //test
                else{
                    Toast.makeText(getApplicationContext(),"Le code PIN doit être de 4 chiffres (ex:1234)",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
