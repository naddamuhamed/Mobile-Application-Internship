package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabase.setLogLevel(Logger.Level.DEBUG);
//        mDatabase.setLogLevel(DEBUG);
//        mDatabase.setLogLevel(INFO);
//        mDatabase.setLogLevel(ERROR);

        myRef = mDatabase.getReference();
        c=new ArrayList<>();
//        writeNewCar("car4",123,23,"ds");
        c.add(new Car("car5",234,234,"dsf"));
        c.add(new Car("car6",43,54,"fdh"));
        c.add(new Car("car7",75,98,"reges"));
        c.add(new Car("car8",765,223,"hrt"));
        for (int i=0;i<c.size();i++)
            writeNewCarobj(c.get(i));
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
//        Butto
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

    public void writeNewCar(String carobject,int carmodel, int carnumber, String carname) {
        Car car = new Car(carobject,carmodel, carnumber,carname);
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