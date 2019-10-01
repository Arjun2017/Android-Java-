package com.example.naruto.vehiclereconogition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class OwnerInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_information);


        String activity_label = getIntentValue();
        setTitle(activity_label + " Activity");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public String getIntentValue(){
        Intent id = getIntent();
        String label = id.getStringExtra("label");
        return label;
    }
}
