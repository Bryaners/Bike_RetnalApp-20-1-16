package com.example.bryan.bike_retnalapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class ReturnsRentalsCollegeStreet extends AppCompatActivity {

    protected Button mContinue;
    protected Button mBack;
    protected TextView mReturnNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returns_rentals_college_street);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //  .setAction("Action", null).show();

                Intent takeUserToRentBike = new Intent(ReturnsRentalsCollegeStreet.this, HomePageActivity.class);
                startActivity(takeUserToRentBike);
            }
        });

//////////////////////////////////////////////////////////////////////

        //real stuff here
        mContinue =(Button)findViewById(R.id.continueWithReturnColl);
        mBack =(Button)findViewById(R.id.backToMainCampusColl);
        mReturnNum = (EditText) findViewById(R.id.ReturnNumBoxColl);


        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(ReturnsRentals.this, "BACK BTN", Toast.LENGTH_LONG).show();
                Intent takeUserToRentBike = new Intent(ReturnsRentalsCollegeStreet.this, CollegeCampusActivity.class);
                startActivity(takeUserToRentBike);
            }
        });


        mContinue.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {

                                             String returnNumber;
                                             returnNumber = mReturnNum.getText().toString().trim();

                                             //Toast.makeText(ReturnsRentals.this, "Continuye click", Toast.LENGTH_LONG).show();

                                             if (returnNumber.isEmpty()) {

                                                 AlertDialog.Builder buidler = new AlertDialog.Builder(ReturnsRentalsCollegeStreet.this);
                                                 buidler.setMessage("No Return code was entered Sorry!");
                                                 buidler.setTitle("Oops!");
                                                 buidler.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                     @Override
                                                     public void onClick(DialogInterface dialogInterface, int which) {
                                                         //close the dialog when button pressed
                                                         dialogInterface.dismiss();
                                                     }
                                                 });
                                                 AlertDialog dialog = buidler.create();
                                                 dialog.show();

                                             }else if(returnNumber.equals("1234") ){  //== "1234" wouldnt work? unsure why

                                                 ParseUser currentUser = ParseUser.getCurrentUser();
                                                 String currentUserUsername = currentUser.getUsername();

                                                 ParseObject returningBikeMain = new ParseObject("CollegeStreetBikes");
                                                 returningBikeMain.put("NameOfBike", "Bike");
                                                 returningBikeMain.put("Campus", "Main Campus");
                                                 returningBikeMain.put("user", currentUserUsername);
                                                 returningBikeMain.saveInBackground();

                                                 Intent takeUserToRentBike = new Intent(ReturnsRentalsCollegeStreet.this, CollegeCampusActivity.class);
                                                 startActivity(takeUserToRentBike);

                                             }
                                             else if(returnNumber.equals("4444")){

                                                 ParseUser currentUser = ParseUser.getCurrentUser();
                                                 String currentUserUsername = currentUser.getUsername();

                                                 ParseObject returningBikeMain = new ParseObject("CollegeStreetBikes");
                                                 returningBikeMain.put("NameOfBike", "Bike");
                                                 returningBikeMain.put("Campus", "Main Campus");
                                                 returningBikeMain.put("user", currentUserUsername);
                                                 returningBikeMain.saveInBackground();

                                                 Intent takeUserToRentBike = new Intent(ReturnsRentalsCollegeStreet.this, CollegeCampusActivity.class);
                                                 startActivity(takeUserToRentBike);

                                             }
                                             else{
                                                 AlertDialog.Builder buidler = new AlertDialog.Builder(ReturnsRentalsCollegeStreet.this);
                                                 buidler.setMessage("An incorrect return code has been entered Sorry!");
                                                 buidler.setTitle("Please try again!");
                                                 buidler.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                     @Override
                                                     public void onClick(DialogInterface dialogInterface, int which) {
                                                         //close the dialog when button pressed
                                                         dialogInterface.dismiss();
                                                     }
                                                 });
                                                 AlertDialog dialog = buidler.create();
                                                 dialog.show();
                                             }

                                         }
                                     }


        );





    }//end of on create

}
