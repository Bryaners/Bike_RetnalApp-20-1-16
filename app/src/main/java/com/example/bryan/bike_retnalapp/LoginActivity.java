package com.example.bryan.bike_retnalapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {

    protected EditText mUsername;
    protected EditText mPassword;
    protected Button mLoginBtn;
    protected Button mCreateAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    //inistizing

        mUsername = (EditText) findViewById(R.id.usernameLoginTextbox);
        mPassword = (EditText) findViewById(R.id.passwordLoginTextbox);
        mLoginBtn = (Button) findViewById(R.id.LoginButton);
        mCreateAccountBtn = (Button) findViewById(R.id.CreateAccountButton);
        //Toast.makeText(LoginActivity.this, "Step1 !", Toast.LENGTH_SHORT).show();
        //button working clicking here down


        //Trying to fix login button
        mCreateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeUsertoReg = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(takeUsertoReg);
            }
        });




        //when the LogginButton is Clicked
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the user input from above(edittext) and change to string
                String usernameLogin = mUsername.getText().toString().trim();
                String passwordLogin = mPassword.getText().toString().trim();

               // Toast.makeText(LoginActivity.this, "Step2222222 !", Toast.LENGTH_LONG).show();

                ParseUser.logInInBackground(usernameLogin, passwordLogin, new LogInCallback() {
                    @Override
                    public void done(ParseUser User, ParseException e) {
                        // Toast.makeText(LoginActivity.this, "Step333333333333333333 !", Toast.LENGTH_LONG).show();
                        if (e == null) {
                            // Hooray! The user is logged in.
                            Toast.makeText(LoginActivity.this, "Welcome Back !", Toast.LENGTH_LONG).show();
                            Intent takeUserHome = new Intent(LoginActivity.this, HomePageActivity.class);
                            startActivity(takeUserHome);

                        } else {
                            // Signup failed. Look at the ParseException to see what happened.

                            AlertDialog.Builder buidler = new AlertDialog.Builder(LoginActivity.this);
                            buidler.setMessage(e.getMessage());
                            buidler.setTitle("Sorry no data entered!");
                            buidler.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
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
                });


            }
        });



        //up working button






            }


    }


