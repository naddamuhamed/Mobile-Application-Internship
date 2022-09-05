package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityDrawerBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class Drawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
private ActivityDrawerBinding binding;
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    PendingIntent fdg;
    AlarmManager alarmManager;
    Button onbtn;
    Button offbtn;
    long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityDrawerBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        onbtn=(Button) findViewById(R.id.alarmonbtn);
        offbtn=(Button) findViewById(R.id.alarmoffbtn);
        setSupportActionBar(binding.appBarDrawer.toolbar);
//        binding.drawerLayout;
        binding.appBarDrawer.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void GetAll(View v) {
        startActivity(new Intent(Drawer.this, GetAll.class));                     // Start the activity to get all images
    }

    public void Upload(View v) {
        startActivity(new Intent(Drawer.this, Upload.class));                     // Start the activity to upload an image
    }

    public void GetByName(View v) {
        startActivity(new Intent(Drawer.this, GetByName.class));                  // Start the activity to get an image by its name
    }
    public void takePhoto(View v){
        startActivity(new Intent(Drawer.this, takePhoto.class));
    }
    public void switchactivity(View v){
        Intent switchActivityIntent = new Intent(Drawer.this, alarm.class);
        startActivity(switchActivityIntent);
    }

    public void switchactivity2(View v){
        Intent switchActivityIntent = new Intent(Drawer.this, preferences.class);
        startActivity(switchActivityIntent);
    }

}