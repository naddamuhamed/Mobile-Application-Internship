package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.ListFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class listViewFragment extends ListFragment {
    ArrayList<Country> c=new ArrayList<>();
    ListView simpleList;
    SimpleAdapter adapter;
    public listViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        c.add(new Country("India",R.drawable.india));
        c.add(new Country("China",R.drawable.china));
        c.add(new Country("Australia",R.drawable.australia));
        c.add(new Country("Portugal",R.drawable.portugal));
        c.add(new Country("America",R.drawable.america));
        c.add(new Country("New Zealand",R.drawable.new_zealand));
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

}