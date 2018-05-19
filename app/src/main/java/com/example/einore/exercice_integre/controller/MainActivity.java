package com.example.einore.exercice_integre.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.einore.exercice_integre.R;
import com.example.einore.exercice_integre.model.User;
import com.example.einore.exercice_integre.model.UserBDD;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //MODIFTEST
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Connection");

        final Button connect_button = (Button) findViewById(R.id.connect_button);
        final EditText usernameEntry = (EditText) findViewById(R.id.username);
        final EditText codeEntry = (EditText) findViewById(R.id.code);

        User user = new User ("Loic", 1234);

        UserBDD userBdd = new UserBDD(this);
        userBdd.openForWrite();
        userBdd.insertUser (user);


        connect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isEmpty (usernameEntry) & !isEmpty(codeEntry)){

                    String username = usernameEntry.getText().toString();
                    int code = Integer.parseInt(codeEntry.getText().toString());


                }
            }
        });
    }

    private boolean isEmpty(EditText Text) {
        if(Text.getText().toString().trim().length() >0)    // .trim() permet de retirer les blancs en début et fin de chaîne
            return false;

        return true;
    }

}
