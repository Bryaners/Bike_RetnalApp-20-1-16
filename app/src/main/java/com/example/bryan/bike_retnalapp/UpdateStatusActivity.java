package com.example.bryan.bike_retnalapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class UpdateStatusActivity extends AppCompatActivity {

    protected EditText mStatusUpdate;
    protected Button mstatusButton;
    //protected ImageView mStatusImgView;  // code at the end of this activity to work.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);


        //init
        mStatusUpdate = (EditText) findViewById(R.id.UpdateTextBox);
        mstatusButton = (Button) findViewById(R.id.statusUpdateButton);
        //mStatusImgView = (ImageView) findViewById(R.id.imgViewContactManger);  //for image to preview wth code at the end of this activity

        mstatusButton.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 //get the current user
                                                 ParseUser currentUser = ParseUser.getCurrentUser();
                                                 String currentUserUsername = currentUser.getUsername();

                                                 String newStatus = mStatusUpdate.getText().toString();

                                                 if (newStatus.isEmpty()) {

                                                     AlertDialog.Builder buidler = new AlertDialog.Builder(UpdateStatusActivity.this);
                                                     buidler.setMessage("Status can not be empty!");
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

                                                 } else {
                                                     ParseObject statusObject = new ParseObject("Status");//class name in parse
                                                     statusObject.put("newStatus", newStatus);
                                                     statusObject.put("user", currentUserUsername);

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

    /*  mStatusImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ImageIntent = new Intent();
                ImageIntent.setType("image/*");
                ImageIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(ImageIntent.createChooser(ImageIntent, "Select Upload Image"), 1);
            }
        });

    }
    //onactivityresult
    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode == RESULT_OK){
            if(reqCode == 1)
                mStatusImgView.setImageURI(data.getData());
        }
    }
    //XML Code

    <ImageView
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:id="@+id/imgViewContactManger"
        android:layout_gravity="center_horizontal"
        android:layout_marginTop="10dp"
        android:src="@drawable/mario" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceSmall"
        android:text="Click On image to select you own"
        android:id="@+id/textView7"
        android:layout_gravity="center_horizontal" />

    //

    */
    }

}
