package com.example.myapplication;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.ArrayList;


//import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecyclerViewFragment extends Fragment {
    private RecyclerView v;
    private RecyclerView.Adapter adapter;
//    =new ArrayList<>();
//    ListView simpleList;
//    SimpleAdapter adapter;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<Country> c = initCountries();
        View rec= inflater.inflate(R.layout.recyclerview, container, false);
        v = (RecyclerView) rec.findViewById(R.id.countries);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager mLayoutManager=new GridLayoutManager(getActivity(), 4);
        v.setLayoutManager(mLayoutManager);
        v.setHasFixedSize(true);
        adapter = new CountryAdapter(c);
        v.setAdapter(adapter);


        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.recyclerview, container, false);
        return  v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private ArrayList<Country> initCountries() {
        ArrayList<Country> c = new ArrayList<>();

        c.add(new Country("India",R.drawable.india));
        c.add(new Country("China",R.drawable.china));
        c.add(new Country("Australia",R.drawable.australia));
        c.add(new Country("Portugal",R.drawable.portugal));
        c.add(new Country("America",R.drawable.america));
        c.add(new Country("New Zealand",R.drawable.new_zealand));
        return c;
    }

}



//import android.support.v7.app.AppCompatActivity;
//        import android.os.Bundle;
//        import android.support.v7.widget.LinearLayoutManager;
//        import android.support.v7.widget.RecyclerView;
//
//        import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//
//    private RecyclerView cities;
//    private RecyclerView.Adapter adapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ArrayList<City> cities = initCities();
//
//        this.cities = (RecyclerView) findViewById(R.id.cities);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
//        this.cities.setLayoutManager(mLayoutManager);
//
//        adapter = new CityAdapter(cities);
//        this.cities.setAdapter(adapter);
//    }
//
//    private ArrayList<City> initCities() {
//        ArrayList<City> list = new ArrayList<>();
//
//        list.add(new City("Cinque Terre", "The coastline, the five villages in Italy.", "https://bit.ly/CBImageCinque"));
//        list.add(new City("Paris", "Paris is the capital city of France", "https://bit.ly/CBImageParis"));
//        list.add(new City("Rio de Janeiro", "Rio has been one of Brazil's most popular destinations.", "https://bit.ly/CBImageRio"));
//        list.add(new City("Sydney", "Sydney is the state capital of New South Wales.", "https://bit.ly/CBImageSydney"));
//
//        return list;
//    }
//}


