package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
    PopupWindow popupadd,popupobtion;
    RelativeLayout linearLayout1;
    ArrayList<Car> c1;
    Button addcar;
    Bundle b;
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
        mDatabase = FirebaseDatabase.getInstance();
//        mDatabase.setLogLevel(Logger.Level.DEBUG);
        myRef = mDatabase.getReference();
        carrec = findViewById(R.id.firebaserecview);
        add=findViewById(R.id.addfloatingpoint);

        LayoutInflater layoutInflater = (LayoutInflater) firebase.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addpopup = layoutInflater.inflate(R.layout.popupaddcarfirebase,null);
        popupadd = new PopupWindow(addpopup, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        LayoutInflater tytr = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View optionpopup = tytr.inflate(R.layout.optionpopup,null);
        popupobtion = new PopupWindow(optionpopup, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

         linearLayout1=findViewById(R.id.relativefirebase);
        c=new ArrayList<>();
        addcar=addpopup.findViewById(R.id.addcarfirebase);
        c1=new ArrayList<>();
        add.show();
        carname=addpopup.findViewById(R.id.addcarname);
        carmodel=addpopup.findViewById(R.id.addcarmodel);
        carnumber=addpopup.findViewById(R.id.addcarnumber);
        carobject=addpopup.findViewById(R.id.addcarobject);
        Intent intent = getIntent();
         b=intent.getExtras();

//int position=(int)getIntent().getSerializableExtra("position");
        if (b!=null){
            int position=b.getInt("position");
            int model=b.getInt("model");
            String name=b.getString("name");
            String object=b.getString("object");
            int number=b.getInt("number");

            popupobtion.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
            popupobtion.setFocusable(true);

            popupobtion.update();

//Car sdfs= (Car) b.get("car");
            Log.i("DFsdgf=================sdgsde","position"+position);
            Log.i("DFsdg================fsdgsde","position"+model);
            Log.i("DFsdgf===================sdgsde","position"+name);
            Log.i("DFsdgf================sdgsde","position"+object);
            Log.i("DFsdg==================fsdgsde","position"+number);
//            c=caradapter.getCarArr();
//            Car sdfs=   c.get(position);
//            Log.i("DFsdgfsdg=======================sde","object"+sdfs.getCarobject());

        }
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
        Car n= new Car(carname.getText().toString(), Integer.parseInt(carmodel.getText().toString()),Integer.parseInt(carnumber.getText().toString()),carobject.getText().toString());
        c.add(n);
        writeNewCarobj(n);
        caradapter.notifyItemInserted(c.size());
//        caradapter.notifyItemInserted();
       Log.i("dsfsfgfgfgfgfgfgfgfgfgfgd","sdfsd"+carrec.getChildAdapterPosition(view)) ;
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
                        Log.i("fsdfwesfwee","ewfsd key="+child.getKey());
                    Log.i("fsdfwesfwee","ewfsd value="+child.getValue());
                    Car message = child.getValue(Car.class);

                            c.add(message);


                    Log.i("fsdfwesfwee","ewfsd object="+message.getCarobject());
                    Log.i("fsdfwesfwee","ewfsd number="+message.getCarnumber());
                    Log.i("oilo9888888888", "name = " + message.getCarname());
                    Log.i(TAG, "jhjjjjjjj67676767676767 model= " + message.getCarmodel());
                }
                buildRecyclerView(c);

                String path = dataSnapshot.getRef().toString();
                long count = dataSnapshot.getChildrenCount();
                System.out.println("-----------------------------"+dataSnapshot.getValue().toString());
//                System.out.println("--------------"+path);
                System.out.println("--------------"+count);
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
//        mUserRef.child("car2").child("carname").setValue(car.getCarname());
        Log.e("=======================", "Clicked on add!");
    }

    public void writeNewCarobj(Car car) {
//        Car car = new Car(carobject,carmodel, carnumber,carname);
//        myRef.child("car3").child("carmodel").setValue(car.getCarmodel());
//        myRef.child("car3").child("carnumber").setValue(car.getCarnumber());
//        myRef.child("car3").child("carname").setValue(car.getCarname());
        myRef.child(car.getCarobject()).setValue(car);
//        mUserRef.child("car2").child("carname").setValue(car.getCarname());
        Log.e("=================", "Clicked on add!");
    }
    private void buildRecyclerView(ArrayList<Car> cerw) {
        // initializing our adapter class.
        caradapter = new CarAdapter(cerw, this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        carrec.setHasFixedSize(true);

        // setting layout manager to our recycler view.
        carrec.setLayoutManager(manager);

        // setting adapter to our recycler view.
        carrec.setAdapter(caradapter);
    }

}