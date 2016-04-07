package com.example.bryan.bike_retnalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Help extends AppCompatActivity {

    protected Button mBackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mBackage =(Button)findViewById(R.id.backBtnHelp);

        mBackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(HomePageActivity.this, "clicked rent bike button", Toast.LENGTH_LONG).show();
                Intent takeUserToHome = new Intent(Help.this, HomePageActivity.class);
                startActivity(takeUserToHome);
            }
        });



    }

}
