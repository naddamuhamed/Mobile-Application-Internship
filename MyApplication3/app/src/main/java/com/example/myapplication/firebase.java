package com.example.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class firebase extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    static ArrayList<Car> c;
    PopupWindow popupadd;
    RelativeLayout linearLayout1;
    ArrayList<Car> c1;
    Button addcar;
    EditText carname,carmodel,carnumber,carobject;
    private RecyclerView carrec;
    FloatingActionButton add;
    // variable for our adapter class and array list
    private CarAdapter caradapter;
    private static final String TAG = "";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        if(isNetworkAvailable())
            Toast.makeText(firebase.this, "internet available", Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(firebase.this, "internet not available", Toast.LENGTH_SHORT).show();

        mDatabase = FirebaseDatabase.getInstance();
//        mDatabase.setLogLevel(Logger.Level.DEBUG);
        myRef = mDatabase.getReference();
        carrec = findViewById(R.id.firebaserecview);
        add=findViewById(R.id.addfloatingpoint);

        LayoutInflater layoutInflater = (LayoutInflater) firebase.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addpopup = layoutInflater.inflate(R.layout.popupaddcarfirebase,null);
        popupadd = new PopupWindow(addpopup, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        carname=addpopup.findViewById(R.id.addcarname);
        carmodel=addpopup.findViewById(R.id.addcarmodel);
        carnumber=addpopup.findViewById(R.id.addcarnumber);
        carobject=addpopup.findViewById(R.id.addcarobject);

        linearLayout1=findViewById(R.id.relativefirebase);
        c=new ArrayList<>();
        addcar=addpopup.findViewById(R.id.addcarfirebase);
        c1=new ArrayList<>();
        add.show();


add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
        popupadd.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
        popupadd.setFocusable(true);

        popupadd.update();


    }
});

addcar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Car n= new Car(carname.getText().toString().trim(), Integer.parseInt(carmodel.getText().toString().trim()),Integer.parseInt(carnumber.getText().toString().trim()),carobject.getText().toString().trim());
        c.add(n);
        writeNewCarobj(n);
        caradapter.notifyItemInserted(c.size());
        popupadd.dismiss();
        Toast.makeText(firebase.this, "Car added", Toast.LENGTH_SHORT).show();


    }
});




        myRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                c=new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
//                        Log.i("fsdfwesfwee","ewfsd key="+child.getKey());
//                    Log.i("fsdfwesfwee","ewfsd value="+child.getValue());
                    Car message = child.getValue(Car.class);

                            c.add(message);


//                    Log.i("fsdfwesfwee","ewfsd object="+message.getCarobject());
//                    Log.i("fsdfwesfwee","ewfsd number="+message.getCarnumber());
//                    Log.i("oilo9888888888", "name = " + message.getCarname());
//                    Log.i(TAG, "jhjjjjjjj67676767676767 model= " + message.getCarmodel());
                }
                buildRecyclerView(c);

                String path = dataSnapshot.getRef().toString();
                long count = dataSnapshot.getChildrenCount();
//                System.out.println("-----------------------------"+dataSnapshot.getValue().toString());
////                System.out.println("--------------"+path);
//                System.out.println("--------------"+count);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                System.out.println("--------------------Failed to read value."+ error.toException());
            }
        });


    }

    public void  writeNewCar(String carobject,int carmodel, int carnumber, String carname) {
        Car car = new Car(carobject,carmodel, carnumber,carname);
//        myRef.child("car3").child("carmodel").setValue(car.getCarmodel());
//        myRef.child("car3").child("carnumber").setValue(car.getCarnumber());
//        myRef.child("car3").child("carname").setValue(car.getCarname());
        myRef.child(car.getCarobject()).setValue(car);
//        Log.e("=======================", "Clicked on add!");
    }

    public void writeNewCarobj(Car car) {
//
        myRef.child(car.getCarobject()).setValue(car);
//        Log.e("=================", "Clicked on add!");
    }
    private void buildRecyclerView(ArrayList<Car> cerw) {
        // initializing our adapter class.
        caradapter = new CarAdapter(cerw, this,myRef);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        carrec.setHasFixedSize(true);

        // setting layout manager to our recycler view.
        carrec.setLayoutManager(manager);

        // setting adapter to our recycler view.
        carrec.setAdapter(caradapter);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}