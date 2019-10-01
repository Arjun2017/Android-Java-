package com.example.naruto.vehiclereconogition;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.VehicleListViewHolder>{
private Context myContext;
private ArrayList<VehicleList> myVehicleList;
private ViewItemClickListener viewItemClickListener;

    public VehicleListAdapter(Context myContext, ArrayList<VehicleList> myVehicleList) {
        this.myContext = myContext;
        this.myVehicleList = myVehicleList;
    }

    @NonNull
    @Override
    public VehicleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vehicleListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_vehiclelist, parent, false);
        VehicleListViewHolder viewHolder=new VehicleListViewHolder(vehicleListView);
        return viewHolder;
    }

    public void setClickListener(ViewItemClickListener viewItemClickListener) {
        this.viewItemClickListener = viewItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleListViewHolder holder, final int position) {
        Log.i("VehicleListAdapter", "onBindViewHolder: "+myVehicleList.size());
        holder.vehicleListImage.setImageResource(myVehicleList.get(position).getImgResource());
        holder.vehicleListText.setText(myVehicleList.get(position).getText());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewItemClickListener!=null)
                    viewItemClickListener.onClick(myVehicleList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return myVehicleList.size();
    }


    public class VehicleListViewHolder extends RecyclerView.ViewHolder{
    private ImageView vehicleListImage;
    private TextView vehicleListText;
    private CardView cardView;
    public VehicleListViewHolder(View itemView) {
        super(itemView);
        vehicleListImage= (ImageView) itemView.findViewById(R.id.vehiclelist_image);
        vehicleListText= (TextView) itemView.findViewById(R.id.vehiclelist_text);
        cardView=itemView.findViewById(R.id.cardview);
    }
}

public interface ViewItemClickListener{
        public void onClick(VehicleList result);
    }

}
