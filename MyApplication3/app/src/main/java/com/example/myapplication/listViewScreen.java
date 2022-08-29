package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;


public class listViewScreen extends AppCompatActivity {
    private static final String TAG = "";
    Button showPopupBtn, closePopupBtn,addbtn,imbttn;
    PopupWindow popupWindow;
    LinearLayout linearLayout1;
    ListView simpleList;
    EditText country;
    ArrayList<Country> c=new ArrayList<>();


//    String countryList[] = {"India", "China", "Australia", "Portugal", "America", "New Zealand"};
//    int flags[] = {R.drawable.india, R.drawable.china, R.drawable.australia, R.drawable.portugal, R.drawable.america, R.drawable.new_zealand};
//    final List<String> ListElementsArrayList = new ArrayList<>(Arrays.asList(countryList));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewog);
        c.add(new Country("India",R.drawable.india));
        c.add(new Country("China",R.drawable.china));
        c.add(new Country("Australia",R.drawable.australia));
        c.add(new Country("Portugal",R.drawable.portugal));
        c.add(new Country("America",R.drawable.america));
        c.add(new Country("New Zealand",R.drawable.new_zealand));
        simpleList = (ListView) findViewById(R.id.simpleListView);



        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), c);
        simpleList.setAdapter(customAdapter);
        showPopupBtn = (Button) findViewById(R.id.add);


        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, imagefromURL.class);
        startActivity(switchActivityIntent);
    }

    public void update(ArrayList<Country> c){
        setContentView(R.layout.listviewog);
        simpleList = (ListView) findViewById(R.id.simpleListView);


        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), c);
        simpleList.setAdapter(customAdapter);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Log.d(TAG, "Clicked on add!");
                LayoutInflater layoutInflater = (LayoutInflater) listViewScreen.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup,null);
                closePopupBtn = (Button) customView.findViewById(R.id.closebutton);
                addbtn = (Button) customView.findViewById(R.id.addbutton);
                imbttn=(Button) customView.findViewById(R.id.im);
                country = customView.findViewById(R.id.addc);
                //instantiate popup window
                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                //display the popup window
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
                popupWindow.setFocusable(true);
                popupWindow.update();
                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                addbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        countryList.(country.getText().toString());
//                        country.getText().toString();
//                        switchActivities();
                        c.add(new Country(country.getText().toString(),R.drawable.egypt));
////                        c.add(new Country("Egypt",R.drawable.egypt));
                        update(c);
                        popupWindow.dismiss();
//
                    }
                });
                imbttn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Clicked on image!");
                        switchActivities();
//
                    }
                });
                return true;
//            case R.id.helpMenu:
//                Log.d(TAG, "Clicked on Help!");
//                // Code for Help goes here
//                return true;
//            case R.id.signOutMenu:
//                Log.d(TAG, "Car signed out");
//                // SignOut method call goes here
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

