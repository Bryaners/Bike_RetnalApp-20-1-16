package com.example.bryan.bike_retnalapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GalleryActivity extends ListActivity {


    //protected List<ParseObject> mStatus;
    public static final int TAKE_PIC_REQUEST_CODE = 0;
    public static final int CHOOSE_PIC_REQUEST_CODE = 1;
    public static final int MEDIA_TYPE_IMAGE = 2;

    protected Button mAddImageBtn;
    protected Button mUploadImageBtn;// changed to mstatusButton
    protected ImageView mPreviewImageView;
    private Uri mMediaUri;



    public void queryImagesFromParse(){                         //ImageUploads
        ParseQuery<ParseObject> imagesQuery = new ParseQuery<>("ImageUploads");
        imagesQuery.orderByDescending("createdAt");
        imagesQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> images, ParseException e) {
                if(e == null){
                    GalleryActivityAdapter adapter = new GalleryActivityAdapter(GalleryActivity.this, images);
                    setListAdapter(adapter);

                    //tells me how many images are in parse
                    Toast.makeText(GalleryActivity.this, images.size() + " images in the parse table", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GalleryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_gallery);

        queryImagesFromParse();

        //Initialize the protected above

        mAddImageBtn = (Button)findViewById(R.id.addimagebutton);
        mUploadImageBtn = (Button)findViewById(R.id.uploadimagebutton);
        mPreviewImageView = (ImageView)findViewById(R.id.previewimageView);


        //listen to button
        mAddImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(GalleryActivity.this);
                builder.setTitle("Upload or Take a photo");

                builder.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //upload
                        Intent choosePictureIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        choosePictureIntent.setType("image/*");
                        startActivityForResult(choosePictureIntent, CHOOSE_PIC_REQUEST_CODE);
                    }
                });
                builder.setNegativeButton("Take Photo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //take image
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        mMediaUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                        if (mMediaUri == null) {
                            //display error
                            Toast.makeText(getApplicationContext(), "Sorry there was an error! Try again.", Toast.LENGTH_LONG).show();
                        } else {
                            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, mMediaUri);
                            startActivityForResult(takePicture, TAKE_PIC_REQUEST_CODE);
                        }

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        /////////////////
        //listen to upload button click
        mUploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               final ParseUser currentUser = ParseUser.getCurrentUser();
                 final String currentUserUsername = currentUser.getUsername();


                //create parse object for image to upload       //ImageUploads
                final ParseObject imageUpload = new ParseObject("ImageUploads");
                try{
                    //convert image to bytes for upload.
                    byte[] fileBytes = FileHelper.getByteArrayFromFile(GalleryActivity.this, mMediaUri);
                    if(fileBytes == null){
                        //there was an error
                        Toast.makeText(getApplicationContext(), "There was an error. Try again!", Toast.LENGTH_LONG).show();
                    }else{

                        fileBytes = FileHelper.reduceImageForUpload(fileBytes);
                        String fileName = FileHelper.getFileName(GalleryActivity.this, mMediaUri, "image");
                        final ParseFile file = new ParseFile(fileName, fileBytes);
                        imageUpload.saveEventually(new SaveCallback() {
                            @Override
                            public void done(com.parse.ParseException e) {
                                if(e == null){
                                    imageUpload.put("user", currentUserUsername);
                                                    //imageContent
                                    imageUpload.put("imageContent", file);
                                    imageUpload.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(com.parse.ParseException e) {
                                            Intent takeusertoGallery = new Intent(GalleryActivity.this,GalleryActivity.class);
                                            startActivity(takeusertoGallery);
                                            Toast.makeText(getApplicationContext(), "Success Uploading image!", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }else{
                                    //there was an error
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }

                }catch (Exception e1){
                    Toast.makeText(getApplicationContext(), e1.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        //////////////////


    }

    //inner helper method
    private Uri getOutputMediaFileUri(int mediaTypeImage) {

        if(isExternalStorageAvailable()){
            //get the URI
            //get external storage dir
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "UPLOADIMAGES");
            //create subdirectore if it does not exist
            if(!mediaStorageDir.exists()){
                //create dir
                if(! mediaStorageDir.mkdirs()){

                    return null;
                }
            }
            //create a file name
            //create file
            File mediaFile = null;
            Date now = new Date();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(now);

            String path = mediaStorageDir.getPath() + File.separator;
            if(mediaTypeImage == MEDIA_TYPE_IMAGE){
                mediaFile = new File(path + "IMG_" + timestamp + ".jpg");
            }
            //return file uri
            Log.d("UPLOADIMAGE", "FILE: "+Uri.fromFile(mediaFile));

            return Uri.fromFile(mediaFile);
        }else {

            return null;
        }

    }
    //check if external storage is mounted. helper method
    private boolean isExternalStorageAvailable(){
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else{
            return false;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == CHOOSE_PIC_REQUEST_CODE){
                if(data == null){
                    Toast.makeText(getApplicationContext(), "Image cannot be null!", Toast.LENGTH_LONG).show();
                }else{
                    mMediaUri = data.getData();
                    //set previews
                    mPreviewImageView.setImageURI(mMediaUri);
                }
            }else {

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                mediaScanIntent.setData(mMediaUri);
                sendBroadcast(mediaScanIntent);
                //set previews

                mPreviewImageView.setImageURI(mMediaUri);

            }

        }else if(resultCode != RESULT_CANCELED){
            Toast.makeText(getApplicationContext(), "Cancelled!", Toast.LENGTH_LONG).show();
        }
    }


}
