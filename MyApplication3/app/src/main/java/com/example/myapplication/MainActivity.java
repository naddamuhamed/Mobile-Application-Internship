package com.example.myapplication;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    Button loginbtn;
    EditText email;
    EditText pw;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
//    ArrayList<Car> c;

    private static final String TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent switchActivityIntent = new Intent(this, firebase.class);
        startActivity(switchActivityIntent);



        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }






        setupHyperlink();
        setupHyperlink2();
        loginbtn = findViewById(R.id.loginbtn);
        email=findViewById(R.id.editTextTextEmailAddress);
        pw=findViewById(R.id.editTextTextPassword);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkDataEntered()) layoutoptionstarter();


            }
        });


        Button OpenBottomSheet = findViewById(R.id.open_bottom_sheet);

        OpenBottomSheet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        BottomSheetDialog bottomSheet = new BottomSheetDialog();
                        bottomSheet.show(getSupportFragmentManager(),
                                "ModalBottomSheet");
                    }
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void setupHyperlink() {
        TextView linkTextView = findViewById(R.id.hyperlink);
//        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
//
//        linkTextView.setLinkTextColor(Color.RED);

        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities3();
            }
        });
    }

    private void setupHyperlink2() {
        TextView linkTextView = findViewById(R.id.hp);


        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, SignUp.class);
        startActivity(switchActivityIntent);
    }

    private void layoutoptionstarter() {
        Intent switchActivityIntent = new Intent(this, layout_option.class);
        startActivity(switchActivityIntent);
    }
    private void switchActivities3() {
        Intent switchActivityIntent = new Intent(this, jasonAPI.class);
        startActivity(switchActivityIntent);
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean checkDataEntered(){
        boolean b;
        if (isEmpty(email)) {
            email.setError("You must enter email to login!");
            b= false;
        }
        if (isEmpty(pw)) {
            pw.setError("You must enter paassword to login!");
            b= false;
        }
        else
            b=true;
        return b;
    }




}

