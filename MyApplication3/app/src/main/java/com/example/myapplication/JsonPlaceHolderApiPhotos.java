package com.example.myapplication;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApiPhotos {
    @GET("photos")
    Call<ArrayList<Photo>> getPhotos();
}
