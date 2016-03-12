package com.example.bryan.bike_retnalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RentalDetailView extends AppCompatActivity {

    String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_detail_view);

        Intent intent = getIntent();
        objectId = intent.getStringExtra("objectId");




    }

}
