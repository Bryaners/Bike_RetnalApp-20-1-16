package com.example.bryan.bike_retnalapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class UpdateStatusActivity extends AppCompatActivity {

    protected EditText mStatusUpdate;
    protected Button mstatusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);


        //init
        mStatusUpdate = (EditText)findViewById(R.id.UpdateTextBox);
        mstatusButton = (Button)findViewById(R.id.statusUpdateButton);

        mstatusButton.setOnClickListener(new View.OnClickListener(){

                 @Override
                 public void onClick(View view){

                     String newStatus = mStatusUpdate.getText().toString();

                     if(newStatus.isEmpty()){

                         AlertDialog.Builder buidler = new AlertDialog.Builder(UpdateStatusActivity.this);
                         buidler.setMessage("Status can not be emphy!");
                         buidler.setTitle("Oops!");
                         buidler.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int which) {
                                 //close the dialog when button pressed
                                 dialogInterface.dismiss();
                             }
                         });

                         AlertDialog dialog = buidler.create();
                         dialog.show();

                     }else {

                         ParseObject statusObject = new ParseObject("Status");//class name in parse
                         statusObject.put("newStatus", newStatus);

                         statusObject.saveInBackground(new SaveCallback() {
                             @Override
                             public void done(ParseException e) {
                                 if (e == null) {
                                     //successfully stored it

                                     Toast.makeText(UpdateStatusActivity.this, "success !", Toast.LENGTH_LONG).show();

                                     Intent takeUserCollegeCampus = new Intent(UpdateStatusActivity.this, MainActivity.class);
                                     startActivity(takeUserCollegeCampus);

                                 } else {
                                     //there was an error storing new status advice the user

                                     AlertDialog.Builder buidler = new AlertDialog.Builder(UpdateStatusActivity.this);
                                     buidler.setMessage(e.getMessage());
                                     buidler.setTitle("Warning!");
                                     buidler.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                         @Override
                                         public void onClick(DialogInterface dialogInterface, int which) {
                                             //close the dialog when button pressed
                                             dialogInterface.dismiss();
                                         }
                                     });

                                     AlertDialog dialog = buidler.create();
                                     dialog.show();
                                     //+++++++++++
                                 }
                             }
                         });

                    // brackett for else isEmpty
                    }
                     //+++++++++++
                    }
               }

        );



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
