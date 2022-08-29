package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApiPosts {

    @GET("posts")
    Call<ArrayList<Post>> getPosts();
}
