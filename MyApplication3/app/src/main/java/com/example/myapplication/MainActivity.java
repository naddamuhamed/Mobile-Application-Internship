package com.example.myapplication;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {
    Button switchToSecondActivity;
    EditText email;
    EditText pw;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    ArrayList<Car> c;

    private static final String TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabase.setLogLevel(Logger.Level.DEBUG);
//        mDatabase.setLogLevel(DEBUG);
//        mDatabase.setLogLevel(INFO);
//        mDatabase.setLogLevel(ERROR);

        myRef = mDatabase.getReference();

//        Intent switchActivityIntent = new Intent(this, Drawer.class);
//        startActivity(switchActivityIntent);


//        Intent switchActivityIntent = new Intent(this, imageUploadTrial.class);
//        startActivity(switchActivityIntent);

//        Intent switchActivityIntent = new Intent(this, alarm.class);
//        startActivity(switchActivityIntent);

        Intent switchActivityIntent = new Intent(this, preferences.class);
        startActivity(switchActivityIntent);


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }


        c=new ArrayList<>();
//        writeNewCar("car4",123,23,"ds");
        c.add(new Car("car5",234,234,"dsf"));
        c.add(new Car("car6",43,54,"fdh"));
        c.add(new Car("car7",75,98,"reges"));
        c.add(new Car("car8",765,223,"hrt"));
        for (int i=0;i<c.size();i++)
            writeNewCarobj(c.get(i));



        setupHyperlink();
        setupHyperlink2();
        switchToSecondActivity = findViewById(R.id.button);
        email=findViewById(R.id.editTextTextEmailAddress);
        pw=findViewById(R.id.editTextTextPassword);
        switchToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkDataEntered()) switchActivities2();


            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

//                Car retrievedUser=new Car();

                ArrayList<Car> c1=new ArrayList<>();



//                HashMap<String,Car> topScores = (HashMap<String,Car>) dataSnapshot.getValue();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()){

//                    String retrievedUser=messageSnapshot.getValue().toString();

                    HashMap<String,Car> topScores = (HashMap<String,Car>) messageSnapshot.getValue();

                    Collection<Car> collection = new ArrayList<Car>();
                    collection=topScores.values();



                    // for loop
                    for (Iterator<Car> iterator = collection.iterator(); iterator.hasNext();) {
                        System.out.println("===================value= " + iterator.next());
                    }

                    topScores.entrySet().stream().forEach(e ->
                            System.out.println(e.getKey() + "=" + e.getValue())
                    );

                }



                String path = dataSnapshot.getRef().toString();
                long count = dataSnapshot.getChildrenCount();
                System.out.println("-----------------------------"+dataSnapshot.getValue().toString());
                System.out.println("--------------"+path);
                System.out.println("--------------"+count);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                System.out.println("--------------------Failed to read value."+ error.toException());
            }
        });


        myRef.child("car4").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("-----------------firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("---------------------firebase", String.valueOf(task.getResult().getValue()));
                }
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

    private void switchActivities2() {
        Intent switchActivityIntent = new Intent(this, Tabs.class);
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
    public void writeNewCar(String carobject,int carmodel, int carnumber, String carname) {
        Car car = new Car(carobject,carmodel, carnumber,carname);
//        myRef.child("car3").child("carmodel").setValue(car.getCarmodel());
//        myRef.child("car3").child("carnumber").setValue(car.getCarnumber());
//        myRef.child("car3").child("carname").setValue(car.getCarname());
        myRef.child(car.getcarobject()).setValue(car);
//        mUserRef.child("car2").child("carname").setValue(car.getCarname());
        Log.d(TAG, "Clicked on add!");
    }

    public void writeNewCarobj(Car car) {
//        Car car = new Car(carobject,carmodel, carnumber,carname);
//        myRef.child("car3").child("carmodel").setValue(car.getCarmodel());
//        myRef.child("car3").child("carnumber").setValue(car.getCarnumber());
//        myRef.child("car3").child("carname").setValue(car.getCarname());
        myRef.child(car.getcarobject()).setValue(car);
//        mUserRef.child("car2").child("carname").setValue(car.getCarname());
        Log.d(TAG, "Clicked on add!");
    }

    public class Car {

        private int carmodel=0;
        private int carnumber=0;
        private String carname="";
        private String carobject="";
        public void constructor(){}

        public Car() {
            // Default constructor required for calls to DataSnapshot.getValue(Car.class)
            this.carmodel = 0;
            this.carnumber = 0;
            this.carname = "";
            this.carobject="";
        }

        public Car(String carobject,int carmodel, int carnumber, String carname) {
            this.carmodel = carmodel;
            this.carnumber = carnumber;
            this.carname = carname;
            this.carobject=carobject;

        }

        public int getCarmodel() {
            return carmodel;
        }

        public void setCarmodel(int carmodel) {
            this.carmodel = carmodel;
        }

        public int getCarnumber() {
            return carnumber;
        }

        public void setCarnumber(int carnumber) {
            this.carnumber = carnumber;
        }

        public String getCarname() {
            return carname;
        }

        public void setCarname(String carname) {
            this.carname = carname;
        }

        public String getcarobject() {
            return carobject;
        }

        public void setcarobject(String carobject) {
            this.carname = carobject;
        }

    }



}

