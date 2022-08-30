package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class takePhoto extends AppCompatActivity {
    TextView imgPath;
    private static final int PICK_IMAGE_REQUEST = 9544;
    ImageView image;
    Button create;
    public  static final int RequestPermissionCode  = 1 ;
    Intent intent ;
    Uri selectedImage;
    String part_image;

    // Permissions for accessing the storage
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        imgPath = findViewById(R.id.item_img);
        image = findViewById(R.id.img);
        create=findViewById(R.id.create_item);

        EnableRuntimePermission();

        create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                // Create the camera_intent ACTION_IMAGE_CAPTURE
                // it will open the camera for capture the image
//                Intent camera_intent
//                        = new Intent(MediaStore
//                        .ACTION_IMAGE_CAPTURE);
                intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 7);
                // Start the activity with camera_intent,
                // and request pic id
//                startActivityForResult(camera_intent, pic_id);
            }
        });
    }


    protected void  onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            image.setImageBitmap(bitmap);
        }
    }


    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA))
        {

            Toast.makeText(this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }


    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        super.onRequestPermissionsResult(RC, per, PResult);
        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

}
