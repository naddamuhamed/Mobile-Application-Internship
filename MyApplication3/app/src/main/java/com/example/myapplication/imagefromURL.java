package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlin.jvm.internal.Ref.ObjectRef;


public final class imagefromURL extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.imagefromurl);

        final ImageView imageView = (ImageView) this.findViewById(R.id.imageView);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        final Handler handler = new Handler(Looper.getMainLooper());
        final ObjectRef image = new ObjectRef();
        image.element = (Bitmap) null;
        executor.execute((Runnable) (new Runnable() {
            public final void run() {
                String imageURL = "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png";

                try {
                    InputStream in = (new URL(imageURL)).openStream();
                    image.element = BitmapFactory.decodeStream(in);
                    handler.post((Runnable) (new Runnable() {
                        public final void run() {
                            imageView.setImageBitmap((Bitmap) image.element);
                        }
                    }));
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        }));
    }
}
