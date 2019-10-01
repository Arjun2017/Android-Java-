package com.example.naruto.vehiclereconogition;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainDashboard extends AppCompatActivity{
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {  // fb ko message padh
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindashboard);
        setTitle("Activity Dashboard");
        Toolbar mainToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);

        myRecyclerView = findViewById(R.id.recycle_view);
        GridLayoutManager grid = new GridLayoutManager(this,2);
        myRecyclerView.setLayoutManager(grid);
        VehicleListAdapter adapter = new VehicleListAdapter(this, getList());
        myRecyclerView.setAdapter(adapter);
        adapter.setClickListener(new VehicleListAdapter.ViewItemClickListener() {
            @Override
            public void onClick(VehicleList result) {
                Intent vehicleActivity = new Intent(MainDashboard.this, VehicleActivity.class);
                vehicleActivity.putExtra("label", result.getText());
                startActivity(vehicleActivity);
            }
        });
    }

    private ArrayList<VehicleList> getList(){
        ArrayList<VehicleList> arrayList = new ArrayList<>();
        arrayList.add(new VehicleList(R.drawable.truck, "Truck"));
        arrayList.add(new VehicleList(R.drawable.bus, "Bus"));
        arrayList.add(new VehicleList(R.drawable.car, "Car"));
        arrayList.add(new VehicleList(R.drawable.bike, "Bike"));
        return arrayList;
    }
}


