package com.example.bryan.bike_retnalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class RentalDetailViewMain extends AppCompatActivity {

    String objectId;
    protected TextView mBikeage;
    protected Button mRentBikeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_detail_view2);

        //initialise
        mBikeage = (TextView) findViewById(R.id.bikeDetailView);

        //get the intetn that started the activity
        Intent intent = getIntent();
        objectId = intent.getStringExtra("objectID");

        mRentBikeBtn = (Button) findViewById(R.id.rentBtn);

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Transaction");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    //success we have a bike selected
                    String userBike = parseObject.getString("NameOfBike");
                    mBikeage.setText(userBike);

                } else {
                    //there was an error
                    Toast.makeText(RentalDetailViewMain.this, "Error with bike rentals", Toast.LENGTH_LONG).show();
                }

            }
        });

        //Trying to fix login button
        mRentBikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(RentalDetailViewMain.this, "btn working clicking", Toast.LENGTH_LONG).show();
                Intent takeUsertoReg = new Intent(RentalDetailViewMain.this, MainCampusActivity.class);
                startActivity(takeUsertoReg);
                deleteStudent(objectId);


                // myObject.remove("brysn");
            }

        });

    }



        public void deleteStudent(String objectId){

            ParseQuery<ParseObject> query=ParseQuery.getQuery("Transaction");
            query.whereEqualTo("objectId",objectId);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> parseObjects, ParseException e) {
                    if (e == null) {


                        for (ParseObject delete : parseObjects) {
                            delete.deleteInBackground();
                            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Error in deleting", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

}
