package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

public class preferences extends AppCompatActivity {
EditText editText;
TextView textView;
Button setdata,getdata;
Set<String> s;
SharedPreferences mypref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        editText=findViewById(R.id.eedittextpreferences);
        textView=findViewById(R.id.textviewpreferences);
        setdata=findViewById(R.id.btnprefrencesetdata);
        getdata=findViewById(R.id.btnpreferencesgetdata);
//        s=new Set<String>() {
//
//        }
        mypref=getApplicationContext().getSharedPreferences("mypref",MODE_PRIVATE);

        setdata.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String st=editText.getText().toString().trim();
                mypref.edit().putString("channel",st).apply();
            }
        });

        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getst= mypref.getString("channel","no data found");
                textView.setText(getst);
            }
        });

    }



}