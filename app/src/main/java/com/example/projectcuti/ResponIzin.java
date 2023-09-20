package com.example.projectcuti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcuti.model.DatabaseHandler;

public class ResponIzin extends AppCompatActivity {
    TextView txt_status, txt_nik, txt_nama, txt_tgl, txt_selesai, txt_jenis, txt_keterangan;
    Button btn_ok, btn_tolak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respon_izin);

        String[] id = {getIntent().getStringExtra("id")};
        System.out.println(id.toString());

        txt_status = findViewById(R.id.txt_status);
        txt_nik = findViewById(R.id.txt_nik);
        txt_nama = findViewById(R.id.txt_nama);
        txt_tgl = findViewById(R.id.txt_tgl);
        txt_selesai = findViewById(R.id.txt_selesai);
        txt_jenis = findViewById(R.id.txt_jenis);
        txt_keterangan = findViewById(R.id.txt_keterangan);
        btn_ok = findViewById(R.id.btn_ok);
        btn_tolak = findViewById(R.id.btn_tolak);

        SQLiteDatabase db = new DatabaseHandler(this).getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from cuti where id = ?", id);
        cursor.moveToFirst();

        txt_status.setText(cursor.getString(cursor.getColumnIndexOrThrow("status")));
        txt_nik.setText(cursor.getString(cursor.getColumnIndexOrThrow("nik")));
        txt_nama.setText(cursor.getString(cursor.getColumnIndexOrThrow("nama")));
        txt_tgl.setText(cursor.getString(cursor.getColumnIndexOrThrow("tgl_mulai")));
        txt_selesai.setText(cursor.getString(cursor.getColumnIndexOrThrow("tgl_akhir")));
        txt_keterangan.setText(cursor.getString(cursor.getColumnIndexOrThrow("keterangan")));

        cursor.close();

        btn_tolak.setOnClickListener(view -> {
            db.execSQL("UPDATE cuti SET status = 'Ditolak' WHERE id = ?", id);
            Toast.makeText(this, "Berhasil merespon", Toast.LENGTH_LONG).show();
        });

        btn_ok.setOnClickListener(view -> {
            db.execSQL("UPDATE cuti SET status = 'Disetujui' WHERE id = ?", id);
            Toast.makeText(this, "Berhasil merespon", Toast.LENGTH_LONG).show();
        });


    }
}