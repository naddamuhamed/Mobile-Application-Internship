package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GetAll extends AppCompatActivity {

    TableLayout table;
    ProgressBar progressBar;
    List<Image> images;
ImageView img;
public static String TAG="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_all);
        img=findViewById(R.id.imageviewgetall);
//        table = findViewById(R.id.all);
//        progressBar = findViewById(R.id.progressBar1);
//        FileInputStream fis = openFileInput(filename);
//        Scanner scanner = new Scanner(fis);
//        scanner.useDelimiter("\\Z");
//        String content = scanner.next();
//        scanner.close();


        fetch();
    }
    private void loadImageFromStorage()
    {

        try {
            ContextWrapper cw = new ContextWrapper(GetAll.this);
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File f=new File(directory, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//            ImageView img=(ImageView)findViewById(R.id.imgPicker);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
    public static String saveToCacheMemory(Activity activity, Bitmap bitmapImage){

        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);

        ContextWrapper cw = new ContextWrapper(activity);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,mDateFormat.format(new Date()));
//        + StaticVariables.imageFormat

        Log.d(TAG, "==================directory: " + directory);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Log.d(TAG, "===============bit exception: Success" );
        } catch (Exception e) {
            Log.d(TAG, "===========bit exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "============io exce: " + e.getMessage());
            }
        }
        Log.d(TAG, "=============absolute path " + mypath.getAbsolutePath());
        return mypath.getAbsolutePath();
    }


    public void fetch() {

        FirebaseStorage storage= FirebaseStorage.getInstance();
        StorageReference imageref=storage.getReference().child("test").child("30e6ea90-2d80-4253-917e-aea8e3f73204");
imageref.getBytes(1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
    @Override
    public void onSuccess(byte[] bytes) {
        Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        saveToCacheMemory(GetAll.this,bitmap);
        img.setImageBitmap(bitmap);

    }
});


        // Method to fetch all images
//        API api = RetrofitClient.getInstance().getAPI();
//        Call<List<Image>> getImages = api.getImages();
//        getImages.enqueue(new Callback<List<Image>>() {
//            @Override
//            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
//                if (response.isSuccessful()) {
//                    images = response.body();                                                           // Get the response and create an image object model
//                    table.removeAllViews();                                                             // Remove all views from the table parent view
//
//                    for (int j = 0; j < images.size(); j += 2) {
//                        TableRow tr = new TableRow(GetAll.this);                                // Create TableView Object
//                        // Set orientation parameters for the TableRow
//                        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
//                        int top = 40;
//                        int bottom = 40;
//                        int left = 40;
//                        int right = 40;
//                        tableRowParams.setMargins(left, top, right, bottom);
//                        tr.setLayoutParams(tableRowParams);
//                        ImageView img1 = new ImageView(GetAll.this);                            // Create ImageView object
//                        TableRow.LayoutParams imgParams = new TableRow.LayoutParams(500, 500);   // Set layout parameters for ImageView
//                        if (images.get(j).getUrl() == null) {
//                            img1.setBackgroundResource(R.drawable.ic_baseline_image_not_supported_24);                            // If no image is available, set a default image
//                        } else {
//                            img1.setBackgroundResource(R.drawable.image_border);
//                            img1.setImageBitmap(getBitmapFromURL(images.get(j).getUrl()));              // Set the image for the ImageView that we get from the response
//                        }
//                        tr.addView(img1, imgParams);
//                        try {
//                            ImageView img2 = new ImageView(GetAll.this);
//                            if (images.get(j+1).getUrl() == null) {
//                                img2.setBackgroundResource(R.drawable.ic_baseline_image_not_supported_24);
//                            } else {
//                                img2.setBackgroundResource(R.drawable.image_border);
//                                img2.setImageBitmap(getBitmapFromURL(images.get(j+1).getUrl()));
//                            }
//                            tr.addView(img2, imgParams);
//                        } catch (NullPointerException | IndexOutOfBoundsException ignored) {
//                        }
//                        progressBar.setVisibility(View.GONE);
//                        table.addView(tr, tableRowParams);                                              // Add the views to the TableView parent
//                    }
//                    Toast.makeText(GetAll.this, "Images Loaded", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Image>> call, Throwable t) {
//                Toast.makeText(GetAll.this, "Request failed", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    // This method will be generating the Bitmap Object from the URL of image.
    public static Bitmap getBitmapFromURL(String src) {
        final Bitmap[] bmp = new Bitmap[1];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(src);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    bmp[0] = BitmapFactory.decodeStream(input);
                } catch (IOException e) {
                    e.printStackTrace();
                    bmp[0] = null;
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bmp[0];
    }
}

