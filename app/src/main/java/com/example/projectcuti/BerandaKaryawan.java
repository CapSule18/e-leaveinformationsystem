package com.example.projectcuti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.projectcuti.databinding.BerandaKaryawanBinding;
import com.example.projectcuti.izin.ui.FormAjuanActivity;
import com.example.projectcuti.izin.ui.RekapActivity;
import com.example.projectcuti.login.data.model.LoggedInUser;
import com.google.gson.Gson;

public class BerandaKaryawan extends AppCompatActivity {
    CardView cuti, izin, sakit, rekap, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // get authenticated user instance
        SharedPreferences userSharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String json = userSharedPreferences.getString("user", "");
        Gson gson = new Gson();
        LoggedInUser user = gson.fromJson(json, LoggedInUser.class);

        super.onCreate(savedInstanceState);

        BerandaKaryawanBinding binding = BerandaKaryawanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.displayName.setText(user.getDisplayName());

        cuti = binding.Cuti;
        izin = binding.Izin;
        sakit = binding.Sakit;
        rekap = binding.Rekapdata;
        about = binding.about;

        about.setOnClickListener(view -> {
            startActivity(new Intent(this, AboutActivity.class));
        });

        cuti.setOnClickListener(view -> {
            startActivity(new Intent(this, FormAjuanActivity.class).putExtra("jenis", "Cuti"));
        });

        izin.setOnClickListener(view -> {
            startActivity(new Intent(this, FormAjuanActivity.class).putExtra("jenis", "Izin"));
        });

        sakit.setOnClickListener(view -> {
            startActivity(new Intent(this, FormAjuanActivity.class).putExtra("jenis", "Sakit"));
        });

        rekap.setOnClickListener(view -> {
            startActivity(new Intent(this, RekapActivity.class));
        });
    }
}