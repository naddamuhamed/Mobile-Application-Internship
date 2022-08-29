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

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private ArrayList<Photo> photos;
    private Context context;
    public PhotoAdapter(ArrayList<Photo> photos, Context context) {
        this.photos = photos;
        this.context = context;
    }


    @androidx.annotation.NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);

        return new PhotoAdapter.ViewHolder(v);
//        return null;
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull PhotoAdapter.ViewHolder holder, int position) {
        Photo p = photos.get(position);

//        holder.userId.setText(Integer.toString(p.getUserId())+"\n");
//        holder.id.setText(Integer.toString(p.getId())+"\n");
//        holder.title.setText(p.getTitle()+"\n");
//        holder.text.setText(p.getText()+"\n");
        Picasso.get().load(p.getUrl()).resize(50, 50)
                .into(holder.image);

    }



    @Override
    public int getItemCount() {
        if (photos != null) {
            return photos.size();
        } else {
            return 0;
        }
//        return 0;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
//                public final View view;
//        public final TextView userId;
//        public final TextView id;
//        public final TextView title;
//        public final TextView text;

        public final ImageView image;

        public ViewHolder(View view) {
            super(view);
//            this.view = view;
//            userId = view.findViewById(R.id.postuserId);
//            id = view.findViewById(R.id.postid);
//            title = view.findViewById(R.id.posttitle);
//            text = view.findViewById(R.id.posttext);
            image = view.findViewById(R.id.imagepost);
        }
    }


}
