package com.example.myapplication;

import java.util.ArrayList;

public class Country {
    private String country;
    private int flags;
    public Country(String c,int f){
        this.country=c;
        this.flags=f;
    }
    public String getCountry(){
        return country;
    }
    public int getFlags(){
        return flags;
    }
    public void setCountry(String c){
        this.country=c;
    }
    public void setFlags(int f){
        this.flags=f;
    }

}


