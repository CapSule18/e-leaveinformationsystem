package com.example.projectcuti;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectcuti.login.data.model.BearerToken;
import com.example.projectcuti.login.data.model.LoggedInUser;
import com.example.projectcuti.login.ui.login.LoginActivity;
import com.example.projectcuti.model.DatabaseHandler;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler tb_databaseHandler;
    Button login;
    TextView txt_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // get authenticated user instance
        SharedPreferences userSharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String json = userSharedPreferences.getString("user", null);

        // get bearer token
        SharedPreferences tokenSharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        String jsonToken = userSharedPreferences.getString("token", null);

        if (json != null && jsonToken != null) {
            startActivity(new Intent(this, BerandaKaryawan.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

//        setContentView(R.layout.login);
//
//        tb_databaseHandler = new DatabaseHandler(this);
//        tb_databaseHandler.onUpgrade(tb_databaseHandler.getWritableDatabase(), 1, 1);
//
//        login = findViewById(R.id.loginButton);
//        txt_pass = findViewById(R.id.password);
//
//        login.setOnClickListener(view -> {
//            if (txt_pass.getText().toString().equals("atasan")) {
//                startActivity(new Intent(this, BerandaAtasan.class));
//            } else {
//                startActivity(new Intent(this, BerandaKaryawan.class));
//            }
//        });
    }
}