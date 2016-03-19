package com.example.bryan.bike_retnalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    protected EditText mUsername;
    protected EditText mEmail;
    protected EditText mPassword;
    protected Button mRegisterButton;
    protected Button mLoginBtnRegPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // change to make git work
        // Enable Local Datastore.
        //Parse.enableLocalDatastore(this);

        //Parse.initialize(this, "cRisvlAkyzGI9gpJSRklDLNflxZ1bDoM8iHdQTOj", "BQlKHhqkSnraH9WHGoS6y41AmH0QxnYCDMOWJCVE");
/*
        ParseObject testObject = new ParseObject("Transaction");
        testObject.put("byran", "Power");
        testObject.saveInBackground();*/

      //  Toast.makeText(RegisterActivity.this,"App is in launched on the 19th", Toast.LENGTH_LONG).show();
        //initialise the the edittexts
        mUsername = (EditText)findViewById(R.id.UsernameRegisterEditTextEditText);
        mEmail = (EditText)findViewById(R.id.EmailRegistereditText);
        mPassword = (EditText)findViewById(R.id.PasswordRegisterEditText);
        mRegisterButton=(Button)findViewById(R.id.Registerbutton);
        mLoginBtnRegPage=(Button)findViewById(R.id.LoginBtnRegPage);

       mLoginBtnRegPage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent takeUserLogin = new Intent(RegisterActivity.this, LoginActivity.class);
               startActivity(takeUserLogin);
           }
       });


        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //geting username, password and email and convert themn to string
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String email = mEmail.getText().toString().trim();

                // Toast.makeText(RegisterActivity.this,"clicked on button", Toast.LENGTH_LONG).show();
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            //user sign up complete
                            Toast.makeText(RegisterActivity.this, "logged In", Toast.LENGTH_LONG).show();
                            Intent takeUserHome = new Intent(RegisterActivity.this, HomePageActivity.class);
                            startActivity(takeUserHome);

                        } else {
                            //error signing the user up. tell user
                            Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });




    }

}