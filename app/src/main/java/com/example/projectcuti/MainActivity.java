package com.example.projectcuti;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectcuti.model.DatabaseHandler;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler tb_databaseHandler;
    Button login;
    TextView txt_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        tb_databaseHandler = new DatabaseHandler(this);
        tb_databaseHandler.onUpgrade(tb_databaseHandler.getWritableDatabase(), 1, 1);

        login = findViewById(R.id.loginButton);
        txt_pass = findViewById(R.id.password);

        login.setOnClickListener(view -> {
            if (txt_pass.getText().toString().equals("atasan")) {
                startActivity(new Intent(this, BerandaAtasan.class));
            } else {
                startActivity(new Intent(this, BerandaKaryawan.class));
            }
        });
    }
}