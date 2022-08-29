package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import androidx.recyclerview.widget.ConcatAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class jasonAPI extends AppCompatActivity {
//    private TextView textViewResult;
private RecyclerView ps;
    private RecyclerView.Adapter adapter;
//    private RecyclerView.Adapter adapterp;
   public ArrayList<Post> p;
    public ArrayList<Photo> ph;
    public List<String> pho;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        ps = (RecyclerView) findViewById(R.id.countries);
        p = new ArrayList<>();
        ph = new ArrayList<>();
        pho = new ArrayList<>();
        photogetter();
//        viewJsonData();

//        concatAdapter = ConcatAdapter(firstAdapter, secondAdapter,
//                thirdAdapter);
//
//        p=viewJsonData();
//        adapter = new PostAdapter(p,jasonAPI.this);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(jasonAPI.this,RecyclerView.VERTICAL,false );
//        ps.setLayoutManager(mLayoutManager);
//        ps.setAdapter(adapter);
    }

    private void photogetter(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiPhotos jsonPlaceHolderApiPhotos=retrofit.create(JsonPlaceHolderApiPhotos.class);
        Call<ArrayList<Photo>> c = jsonPlaceHolderApiPhotos.getPhotos();

        c.enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {

                if (!response.isSuccessful()) {
//                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                ph = response.body();
                postgetter(ph);
            }

            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
            }
        });

    }

    private void postgetter(ArrayList<Photo> photo){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiPosts jsonPlaceHolderApiPosts = retrofit.create(JsonPlaceHolderApiPosts.class);
        Call<ArrayList<Post>> a = jsonPlaceHolderApiPosts.getPosts();

        a.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {

                if (!response.isSuccessful()) {
//                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                p = response.body();
                configureadapter(p,photo);

            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
            }
        });


    }

    private void configureadapter(ArrayList<Post> post,ArrayList<Photo> photo ){
        adapter = new PostAdapter(post,photo, jasonAPI.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(jasonAPI.this,RecyclerView.VERTICAL,false );
        ps.setLayoutManager(mLayoutManager);
        ps.setAdapter(adapter);
    }


    private void viewJsonData() {



//        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiPosts jsonPlaceHolderApiPosts = retrofit.create(JsonPlaceHolderApiPosts.class);
        JsonPlaceHolderApiPhotos jsonPlaceHolderApiPhotos=retrofit.create(JsonPlaceHolderApiPhotos.class);
        Call<ArrayList<Photo>> c = jsonPlaceHolderApiPhotos.getPhotos();



        c.enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {

                if (!response.isSuccessful()) {
//                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                ph = response.body();
                Call<ArrayList<Post>> a = jsonPlaceHolderApiPosts.getPosts();
//int i=0;
                a.enqueue(new Callback<ArrayList<Post>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {

                        if (!response.isSuccessful()) {
//                    textViewResult.setText("Code: " + response.code());
                            return;
                        }

                        p = response.body();
//int i=0;
//
//                for (i=0;i<p.size ();i++){



                        adapter = new PostAdapter(p,ph, jasonAPI.this);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(jasonAPI.this,RecyclerView.VERTICAL,false );
                        ps.setLayoutManager(mLayoutManager);
                        ps.setAdapter(adapter);

//                    String content = "";
//                    content += "ID: " + post.getId() + "\n";
//                    content += "Car ID: " + post.getUserId() + "\n";
//                    content += "Title: " + post.getTitle() + "\n";
//                    content += "Text: " + post.getText() + "\n\n";
//                    Log.
//                       textViewResult.append(content);


//                }

                    }

                    @Override
                    public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
                    }
                });
//
//                for (Photo x : ph) {
//                for (i=0;i<99 ;i++){
//i++;
//pho.add(x.getUrl());

//                adapterp = new PhotoAdapter(ph,jasonAPI.this);
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(jasonAPI.this,RecyclerView.VERTICAL,false );
//                ps.setLayoutManager(mLayoutManager);
//                ps.setAdapter(adapterp);
//                ps.adap

//                    String content = "";
//                    content += "ID: " + post.getId() + "\n";
//                    content += "Car ID: " + post.getUserId() + "\n";
//                    content += "Title: " + post.getTitle() + "\n";
//                    content += "Text: " + post.getText() + "\n\n";
//                    Log.
//                       textViewResult.append(content);
//if(i==99)
//    break;

//                }

            }

            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
            }
        });





//        return p;

    }

}