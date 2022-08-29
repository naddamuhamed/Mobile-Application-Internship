package com.example.myapplication;
//import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private ArrayList<Country> countries;

    public CountryAdapter(ArrayList<Country> countries) {
        this.countries = countries;
    }

    @androidx.annotation.NonNull
    @Override
    public ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);

        return new CountryAdapter.ViewHolder(v);
//        return null;
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull ViewHolder holder, int position) {
        Country country = countries.get(position);

        holder.name.setText(country.getCountry());
//        holder.description.setText(country.getDescription());

        Picasso.get().load(country.getFlags()).resize(100, 100)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        if (countries != null) {
            return countries.size();
        } else {
            return 0;
        }
//        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
//        public final TextView description;
        public final ImageView image;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.name);
//            description = view.findViewById(R.id.description);
            image = view.findViewById(R.id.imge);
        }
    }

}





