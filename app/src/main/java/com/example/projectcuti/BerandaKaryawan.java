package com.example.projectcuti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class BerandaKaryawan extends AppCompatActivity {
    CardView cuti, izin, sakit, rekap, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beranda_karyawan);

        cuti = findViewById(R.id.Cuti);
        izin = findViewById(R.id.Izin);
        sakit = findViewById(R.id.Sakit);
        rekap = findViewById(R.id.Rekapdata);
        about = findViewById(R.id.about);

        about.setOnClickListener(view -> {
            startActivity(new Intent(this, AboutActivity.class));
        });

        cuti.setOnClickListener(view -> {
            startActivity(new Intent(this, FormAjuanActivity.class).putExtra("jenis", "cuti"));
        });
        izin.setOnClickListener(view -> {
            startActivity(new Intent(this, FormAjuanActivity.class).putExtra("jenis", "izin"));
        });
        sakit.setOnClickListener(view -> {
            startActivity(new Intent(this, FormAjuanActivity.class).putExtra("jenis", "sakit"));
        });
        rekap.setOnClickListener(view -> {
            startActivity(new Intent(this, RekapActivity.class));
        });


    }
}