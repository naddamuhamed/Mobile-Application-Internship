package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {
    private RecyclerView v;
    private RecyclerView.Adapter adapter;
//    =new ArrayList<>();
//    ListView simpleList;
//    SimpleAdapter adapter;
ArrayList<Country> c = initCountries();

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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



