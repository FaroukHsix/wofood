package com.ama.righthajj.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ama.righthajj.R;
import com.ama.righthajj.tasks.Auth;

public class Intro extends AppCompatActivity {

    Auth connexion;
    EditText id ;
    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        connexion = new Auth(getApplicationContext());
        id = findViewById(R.id.id_user);
        bar = findViewById(R.id.authBar);

        Button submit = findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_ = id.getText().toString().trim();
                connexion.getData(id_,"https://assiwad.000webhostapp.com/v1/login",bar);
            }
        });

    }
}
