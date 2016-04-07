package com.example.bryan.bike_retnalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Information extends AppCompatActivity {

    protected Button mMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        mMainMenu =(Button)findViewById(R.id.MM);

        mMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(HomePageActivity.this, "clicked rent bike button", Toast.LENGTH_LONG).show();
                Intent takeUserToRentBike = new Intent(Information.this, HomePageActivity.class);
                startActivity(takeUserToRentBike);
            }
        });

    }

}
