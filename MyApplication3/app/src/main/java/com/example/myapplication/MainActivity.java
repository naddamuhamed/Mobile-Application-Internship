package com.example.myapplication;
import static com.google.firebase.database.Logger.Level.DEBUG;
import static com.google.firebase.database.Logger.Level.ERROR;
import static com.google.firebase.database.Logger.Level.INFO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.audiofx.DynamicsProcessing;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
//        fetch('https://jsonplaceholder.typicode.com/posts/1')
//                .then((response) => response.json())
//  .then((json) => console.log(json));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//FirebaseDatabase.getInstance("URL here");

//        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");

        mDatabase = FirebaseDatabase.getInstance("https://koba2-e9720-default-rtdb.firebaseio.com/");
        mDatabase.setLogLevel(Logger.Level.DEBUG);
//        mDatabase.setLogLevel(DEBUG);
//        mDatabase.setLogLevel(INFO);
//        mDatabase.setLogLevel(ERROR);

         myRef = mDatabase.getReference();

//        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        mUserRef = mDatabase.child("cars");

//        myRef.child("dgfd").setValue("ghdd : welcome").addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (!task.isSuccessful()) {
//                    System.out.println("------------firebase Error getting data" +task.getException().toString());
//                }
//                else {
//                    System.out.println("----------firebase success"+String.valueOf(task.getResult()));
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                System.out.println("----------firebase exception"+e.toString());
//
//            }
//        });



        c=new ArrayList<>();
        writeNewCar("car4",123,23,"ds");
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
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


//                Car post = dataSnapshot.getValue(Car.class);
                System.out.println("-----------------------------"+dataSnapshot.getValue().toString());

//                System.out.println("-----------------------------"+post.getCarname());
//                System.out.println("-----------------------------"+value.toString());

            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("--------------------Failed to read value."+ error.toException());
            }
        });


        myRef.child("car4").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
//                    Log.e("-----------------firebase", "Error getting data", task.getException());
                }
                else {
//                    Log.d("---------------------firebase", String.valueOf(task.getResult().getValue()));
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

        private int carmodel;
        private int carnumber;
        private String carname;
        private String carobject;

        public Car() {
            // Default constructor required for calls to DataSnapshot.getValue(Car.class)
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

