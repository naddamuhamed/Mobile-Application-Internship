package com.example.myapplication;


public class Car {

    private int carmodel=0;
    private int carnumber=0;
    private String carname="";
    private String carobject="";
    public void constructor(){}

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