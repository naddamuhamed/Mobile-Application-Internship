package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private ArrayList<Car> CarArr;
    private Context context;
    PopupWindow popupadd;
    public CarAdapter(ArrayList<Car> CarArr, Context context) {
        this.CarArr = CarArr;
        this.context = context;
    }
    public ArrayList<Car> getCarArr(){
        return CarArr;
    }
    public Context getContext(){
        return context;
    }

    public void setCarArr(ArrayList<Car> carArr) {
        CarArr = carArr;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.ViewHolder holder, int position) {
        Car car = CarArr.get(position);
        holder.carname.setText(car.getCarname());
        holder.carnumber.setText(Integer.toString(car.getCarnumber()));
        holder.carmodel.setText(Integer.toString(car.getCarmodel()));
        holder.carobject.setText(car.getCarobject ());

    }

    @Override
    public int getItemCount() {
        return CarArr.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private TextView carname, carnumber,carmodel,carobject;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
    itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            Intent intent=new Intent(context,firebase.class);
            intent.putExtra("position",getAdapterPosition());
            intent.putExtra("model",CarArr.get(getAdapterPosition()).getCarmodel());
            intent.putExtra("name",CarArr.get(getAdapterPosition()).getCarname());
            intent.putExtra("object",CarArr.get(getAdapterPosition()).getCarobject());
            intent.putExtra("number",CarArr.get(getAdapterPosition()).getCarnumber());

            context.startActivity(intent);

//            CarArr.remove(getAdapterPosition());
//
//            notifyItemRemoved(getAdapterPosition());

            return true;
        }
    });
            // initializing our views with their ids.
            carname = itemView.findViewById(R.id.idcarname);
            carnumber = itemView.findViewById(R.id.idcarnumber);
            carmodel = itemView.findViewById(R.id.idcarmodel);
            carobject = itemView.findViewById(R.id.idcarobject);
        }


    }
}
