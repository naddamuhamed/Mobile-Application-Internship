package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class imageUploadTrial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageuploadtrial);
    }

    public void GetAll(View v) {
        startActivity(new Intent(imageUploadTrial.this, GetAll.class));                     // Start the activity to get all images
    }

    public void Upload(View v) {
        startActivity(new Intent(imageUploadTrial.this, Upload.class));                     // Start the activity to upload an image
    }

    public void GetByName(View v) {
        startActivity(new Intent(imageUploadTrial.this, GetByName.class));                  // Start the activity to get an image by its name
    }
    public void takePhoto(View v){
        startActivity(new Intent(imageUploadTrial.this, takePhoto.class));
    }

}