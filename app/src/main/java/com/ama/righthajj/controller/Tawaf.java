package com.ama.righthajj.controller;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.ama.righthajj.R;
import com.ama.righthajj.model.Gate;

import java.util.ArrayList;

public class Tawaf extends AppCompatActivity {
    ImageView gate1 ;
    ImageView gate2 ;
    ImageView gate3 ;
    ImageView gate4 ;
    ImageView gate5 ;
    ImageView gate6 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tawaf);
        Bundle bundle = getIntent().getExtras();
        ArrayList<Gate> item = (ArrayList<Gate>)bundle.getSerializable("gates");
        gate1 = findViewById(R.id.gate1);
        adapt(gate1,item.get(0).getNumber());
        gate2 = findViewById(R.id.gate2);
        adapt(gate2,item.get(1).getNumber());
        gate3 = findViewById(R.id.gate3);
        adapt(gate3,item.get(2).getNumber());
        gate4 = findViewById(R.id.gate4);
        adapt(gate4,item.get(3).getNumber());
        gate5 = findViewById(R.id.gate5);
        adapt(gate5,item.get(4).getNumber());
        gate6 = findViewById(R.id.gate6);
        adapt(gate6,item.get(5).getNumber());



    }


    @SuppressLint("ResourceAsColor")
    void adapt (ImageView image, int value){
      if (value < 10)
          image.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
      else if (value <60)
            image.setBackgroundColor(getResources().getColor(R.color.orenge));
      else if (value<= 100)
          image.setBackgroundColor(getResources().getColor(R.color.red));


    }

}
