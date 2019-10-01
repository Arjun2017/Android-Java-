package com.example.naruto.vehiclereconogition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
    }

    private void initializeView(){
        Username = findViewById(R.id.edt_username);
        Password = findViewById(R.id.edt_password);

    }

    public void login(View v){

        Intent login_Intent = new Intent(this, MainDashboard.class);
        startActivity(login_Intent);
    }
}
