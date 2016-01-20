package com.example.bryan.bike_retnalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TransactionActivity extends AppCompatActivity {

    protected Button mMainCampus;
    protected Button mCollegeStreet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        //button for Main campous street and rent a bike then once in that activty
        mMainCampus=(Button)findViewById(R.id.Mainbutton);

        mMainCampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(TransactionActivity.this, "Main", Toast.LENGTH_LONG).show();
                Intent takeUserTMainCampus = new Intent(TransactionActivity.this, MainCampusActivity.class);
                startActivity(takeUserTMainCampus);
            }
        });


        //button for college street and rent a bike then once in that activty
        mCollegeStreet=(Button)findViewById(R.id.Collegebutton);

        mCollegeStreet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(TransactionActivity.this, "College", Toast.LENGTH_LONG).show();
                Intent takeUserCollegeCampus = new Intent(TransactionActivity.this, CollegeCampusActivity.class);
                startActivity(takeUserCollegeCampus);
            }
        });

    }

}
