package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class listViewFragment extends ListFragment {
    ArrayList<Country> c = initCountries();
    ListView simpleList;
    SimpleAdapter adapter;
    public listViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


//        simpleList = (ListView) findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), c);
//        simpleList.setAdapter(customAdapter);
        setListAdapter(customAdapter);
//        adapter=new SimpleAdapter(getActivity(), c, R.layout.model, from, to);
//        setListAdapter(adapter);

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
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