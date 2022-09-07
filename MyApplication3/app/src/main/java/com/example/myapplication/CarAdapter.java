package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private ArrayList<Car> CarArr;
    private Context context;
    PopupWindow popupadd;
    AlertDialog.Builder builderdelete,builderedit,builderoption;
    DatabaseReference myRef;
    EditText editcarname,editcarmodel,editcarnumber;
Button editcar,deletecar,editeditcar;
    public CarAdapter(ArrayList<Car> CarArr, Context context,DatabaseReference myRef) {
        this.CarArr = CarArr;
        this.context = context;
        this.myRef=myRef;
    }

    public ArrayList<Car> getCarArr(){
        return CarArr;
    }
    public Context getContext(){
        return context;
    }
public DatabaseReference getMyRef(){return myRef;}
    public void setCarArr(ArrayList<Car> carArr) {
        CarArr = carArr;
    }
    public void setContext(Context context) {
        this.context = context;
    }
public void setMyRef(DatabaseReference myRef){this.myRef=myRef;}

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


            LayoutInflater ret = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View htfg = ret.inflate(R.layout.popupeditfirebase, null);

            LayoutInflater rtr = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View etw = rtr.inflate(R.layout.optionpopup, null);


            editcar=etw.findViewById(R.id.editcarbtn);
            deletecar=etw.findViewById(R.id.deletecarbtn);

            editcarname=htfg.findViewById(R.id.editcarname);
            editcarnumber=htfg.findViewById(R.id.editcarnumber);
            editcarmodel=htfg.findViewById(R.id.editcarmodel);
            editeditcar=htfg.findViewById(R.id.editcarfirebase);


            builderoption = new AlertDialog.Builder(context);
            builderoption.setView(etw);
            AlertDialog alert = builderoption.create();
            alert.show();



            editcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //        popup tany
                    editcarname.setText(CarArr.get(getAdapterPosition()).getCarname());
                    editcarmodel.setText(String.valueOf(CarArr.get(getAdapterPosition()).getCarmodel()));
                    editcarnumber.setText(String.valueOf(CarArr.get(getAdapterPosition()).getCarnumber()));


                    builderedit = new AlertDialog.Builder(context);
                    builderedit.setView(htfg);
                    AlertDialog alert3 = builderedit.create();
                    alert3.show();


                    editeditcar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Car n= new Car(editcarname.getText().toString().trim(), Integer.parseInt(editcarmodel.getText().toString().trim()),Integer.parseInt(editcarnumber.getText().toString().trim()),CarArr.get(getAdapterPosition()).getCarobject());
                            CarArr.add(n);
                            writeNewCarobj(n);
                            notifyItemInserted(CarArr.size());

                            alert3.dismiss();
                            alert.dismiss();
                            Toast.makeText(context, "Car edited", Toast.LENGTH_SHORT).show();


                        }
                    });

                }
            });


            deletecar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builderdelete = new AlertDialog.Builder(context);

            builderdelete.setMessage("Are you sure you want to delete this?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            myRef.child(CarArr.get(getAdapterPosition()).getCarobject()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
//                                    CarArr.remove(getAdapterPosition());
//                                    notifyItemRemoved(getAdapterPosition()); //could remove
                                    Toast.makeText(context,"item deleted successfully",
                                            Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    alert.dismiss();
                                }
                            });
//
//                            Toast.makeText(context,"you choose yes action for alertbox",
//                                    Toast.LENGTH_SHORT).show();

////                            popupoption.dismiss();
//                            context.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();
                            Toast.makeText(context,"you choose no action for alertbox",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });


            AlertDialog alert2 = builderdelete.create();
            alert2.show();
                }
            });
















//            Intent intent=new Intent(view.getContext(),firebase.class);
//            intent.putExtra("position",getAdapterPosition());
//            intent.putExtra("model",CarArr.get(getAdapterPosition()).getCarmodel());
//            intent.putExtra("name",CarArr.get(getAdapterPosition()).getCarname());
//            intent.putExtra("object",CarArr.get(getAdapterPosition()).getCarobject());
//            intent.putExtra("number",CarArr.get(getAdapterPosition()).getCarnumber());
//            view.getContext().startActivity(intent);



//            CarArr.remove(getAdapterPosition());
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

    public void writeNewCarobj(Car car) {
        myRef.child(car.getCarobject()).setValue(car);
    }
}
