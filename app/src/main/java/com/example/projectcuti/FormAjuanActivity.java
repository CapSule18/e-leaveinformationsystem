package com.example.projectcuti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectcuti.model.DatabaseHandler;

import java.util.Random;
import java.util.UUID;

public class FormAjuanActivity extends AppCompatActivity {
    Button btnCheckout;
    EditText txt_keterangan, spKelas, txt_tgl, txt_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputajuan);

        String jenis = getIntent().getStringExtra("jenis");

//        System.out.println(jenis);

        txt_keterangan = findViewById(R.id.txt_keterangan);
        spKelas = findViewById(R.id.spKelas);
        txt_tgl = findViewById(R.id.txt_tgl);
        txt_end = findViewById(R.id.txt_end);

        btnCheckout = findViewById(R.id.btnCheckout);

        btnCheckout.setOnClickListener(view -> {
            SQLiteDatabase db = new DatabaseHandler(this).getWritableDatabase();

            ContentValues content = new ContentValues();

            content.put("jenis", jenis);
            content.put("nama", "Karyawan " + new Random().nextInt(999));
            content.put("keterangan", txt_keterangan.getText().toString());
            content.put("status", "pending");
            content.put("tgl_mulai", txt_tgl.getText().toString());
            content.put("tgl_akhir", txt_end.getText().toString());
            content.put("nik",  + new Random().nextInt(99999));
            try {
                db.insert("cuti", null, content);
                Toast.makeText(this, "Berhasil input data cuti.", Toast.LENGTH_LONG).show();
                txt_keterangan.setText("");
                spKelas.setText("");
                txt_tgl.setText("");
                txt_end.setText("");
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}