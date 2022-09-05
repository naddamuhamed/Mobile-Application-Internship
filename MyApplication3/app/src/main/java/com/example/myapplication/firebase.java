package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Map;

public class firebase extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    ArrayList<Car> c;
    ArrayList<Car> c1;
    private static final String TAG = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        mDatabase = FirebaseDatabase.getInstance();
        mDatabase.setLogLevel(Logger.Level.DEBUG);
        myRef = mDatabase.getReference();

        c=new ArrayList<>();
        c1=new ArrayList<>();



//        writeNewCar("car4",123,23,"ds");
//        c.add(new Car("car5",234,234,"dsf"));
//        c.add(new Car("car6",43,54,"fdh"));
//        c.add(new Car("car7",75,98,"reges"));
//        c.add(new Car("car8",765,223,"hrt"));
//        for (int i=0;i<c.size();i++)
//            writeNewCarobj(c.get(i));


        myRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                HashMap<String,Car>ghk=new HashMap<String,Car>();
                HashMap<String, HashMap<String,Car>> topScores = (HashMap<String, HashMap<String,Car>>) dataSnapshot.getValue();

                Collection<HashMap<String,Car>> collection;
                collection=topScores.values();
//                    Set<String> k=topScores.keySet();
//                Car[] foos = collection.toArray(new Car[collection.size()]);
//                    c1.add(foos[0]);
                Car cc=new Car();
//                collection.iterator().next();
                // for loop
                for (Iterator<HashMap<String,Car>> iterator = collection.iterator(); iterator.hasNext();) {
//                        if (e.getKey()=="carnumber")
//                    while (collection.iterator().hasNext()){
//                            cc.setCarnumber(iterator.next().getCarnumber());
//////                        if (e.getKey()=="carmodel")
//                            cc.setCarmodel(iterator.next().getCarmodel());
//////                        if (e.getKey()=="carobject")
//                        cc.setcarobject(iterator.next().getcarobject());
//////                        if (e.getKey()=="carname")
//                        cc.setCarname((Car) iterator.next().getCarname());
//                        c1.add(iterator.next());

//                        cc=new Car();
                    System.out.println("===================value= " + iterator.next());



//                    collection.toArray().toString();
//c1.add(csdf[0]);
                }

                topScores.entrySet().stream().forEach(e ->
                                System.out.println(e.getKey() + "=" + e.getValue())
//                            System.out.println(e.getKey() + "=" + e.getValue())
                );

                for(HashMap.Entry<String,HashMap<String,Car>> e: topScores.entrySet()){

                    System.out.println(e.getKey() + "=" + e.getValue());

                    for(Map.Entry<String,Car> g:e.getValue().entrySet()){
//                        if (g.getKey()=="carnumber")
                        System.out.println(g);
//                        cc.setCarnumber(g.getValue().getCarnumber());
////                        if (g.getKey()=="carmodel")
//                            cc.setCarmodel(g.getValue().getCarmodel());
////                        if (g.getKey()=="carobject")
//                            cc.setcarobject(g.getValue().getcarobject());
////                        if (g.getKey()=="carname")
//                            cc.setCarname(g.getValue().getCarname());
//                        c1.add(cc);
                    }
//                    for (:
//                         ) {
//
//                    }
//                        if (e.getKey()=="carnumber")
//                            cc.setCarnumber(e.getValue().getCarnumber());
////                        if (e.getKey()=="carmodel")
////                            cc.setCarmodel(e.getValue().getCarmodel());
////                        if (e.getKey()=="carobject")
//                            cc.setcarobject(e.getValue().getcarobject());
////                        if (e.getKey()=="carname")
//                            cc.setCarname(e.getValue().getCarname());
//                        c1.add(cc);
//                         cc=new Car();

                }

//                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()){
//
//                    HashMap<String,Car> topScores = (HashMap<String,Car>) messageSnapshot.getValue();
//
//                    Collection<Car> collection;
//                    collection=topScores.values();
//
////                    Car[] foos = collection.toArray(new Car[collection.size()]);
////                    c1.add(foos[0]);
////                    Set<String> k=topScores.keySet();
//                    Car cc=new Car();
////                    collection.iterator().next();
//                    // for loop
//                    for (Iterator<Car> iterator = collection.iterator(); iterator.hasNext();) {
////                        if (e.getKey()=="carnumber")
////                    while (collection.iterator().hasNext()){
////                            cc.setCarnumber(collection.iterator().next().getCarnumber());
//////                        if (e.getKey()=="carmodel")
////                            cc.setCarmodel(collection.iterator().next().getCarmodel());
//////                        if (e.getKey()=="carobject")
////                        cc.setcarobject(collection.iterator().next().getcarobject());
//////                        if (e.getKey()=="carname")
////                        cc.setCarname(collection.iterator().next().getCarname());
////                        c1.add(collection.iterator().);
//
////                        cc=new Car();
//                        System.out.println("===================value= " + iterator.next());
//
//
////                        collection.toArray().toString();
////c1.add(csdf[0]);
//                    }
//
//                    topScores.entrySet().stream().forEach(e ->
//                            System.out.println(e.getKey() + "=" + e.getValue())
////                            System.out.println(e.getKey() + "=" + e.getValue())
//                    );
//ArrayList<String>sdf=new ArrayList<>();
//                    for(HashMap.Entry<String,Car> e: topScores.entrySet()){
//
//                        System.out.println(e.getKey() + "=" + e.getValue());
//
////                        if (e.getKey()=="carnumber")
////                        sdf.add(e.getValue().toString());
////                            cc.setCarnumber(e.getValue().getCarnumber());
//////                        if (e.getKey()=="carmodel")
////                            cc.setCarmodel(e.getValue().getCarmodel());
//////                        if (e.getKey()=="carobject")
////                            cc.setcarobject(e.getValue().getcarobject());
//////                        if (e.getKey()=="carname")
////                            cc.setCarname(e.getValue().getCarname());
////                        c1.add(cc);
////                         cc=new Car();
//
//                    }
//
//                }

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

//        myRef.get();

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
    }

    public void writeNewCar(String carobject,int carmodel, int carnumber, String carname) {
        Car car = new Car(carobject,carmodel, carnumber,carname);
//        myRef.child("car3").child("carmodel").setValue(car.getCarmodel());
//        myRef.child("car3").child("carnumber").setValue(car.getCarnumber());
//        myRef.child("car3").child("carname").setValue(car.getCarname());
        myRef.child(car.getcarobject()).setValue(car);
//        mUserRef.child("car2").child("carname").setValue(car.getCarname());
        Log.e("=======================", "Clicked on add!");
    }

    public void writeNewCarobj(Car car) {
//        Car car = new Car(carobject,carmodel, carnumber,carname);
//        myRef.child("car3").child("carmodel").setValue(car.getCarmodel());
//        myRef.child("car3").child("carnumber").setValue(car.getCarnumber());
//        myRef.child("car3").child("carname").setValue(car.getCarname());
        myRef.child(car.getcarobject()).setValue(car);
//        mUserRef.child("car2").child("carname").setValue(car.getCarname());
        Log.e("=================", "Clicked on add!");
    }

}