package com.example.myapplication;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ArrayList<Post> posts;
    private Context context;
    private ArrayList<Photo> ph;
    public PostAdapter(ArrayList<Post> posts,ArrayList<Photo> ph, Context context) {
        this.posts = posts;
        this.context = context;
        this.ph=ph;
    }

    @androidx.annotation.NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);

        return new PostAdapter.ViewHolder(v);
//        return null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull PostAdapter.ViewHolder holder, int position) {
        Post p = posts.get(position);
        Photo pho=ph.get(position);
        holder.userId.setText(Integer.toString(p.getUserId())+"\n");
        holder.id.setText(Integer.toString(p.getId())+"\n");
        holder.title.setText(p.getTitle()+"\n");
        holder.text.setText(p.getText()+"\n");
        Picasso.get().load(pho.getUrl()).resize(50, 50)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        if (posts != null) {
            return posts.size();
        } else {
            return 0;
        }
//        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public final View view;
        public final TextView userId;
        public final TextView id;
        public final TextView title;
        public final TextView text;

        public final ImageView image;

        public ViewHolder(View view) {
            super(view);
//            this.view = view;
            image = view.findViewById(R.id.imagepost);
            userId = view.findViewById(R.id.postuserId);
            id = view.findViewById(R.id.postid);
            title = view.findViewById(R.id.posttitle);
            text = view.findViewById(R.id.posttext);

        }
    }

}
