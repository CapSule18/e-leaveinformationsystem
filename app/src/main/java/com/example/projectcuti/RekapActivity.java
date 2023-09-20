package com.example.projectcuti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.projectcuti.model.DatabaseHandler;
import com.example.projectcuti.model.Izin;

import java.util.ArrayList;
import java.util.Objects;

public class RekapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_rekapdata);

        SQLiteDatabase db = new DatabaseHandler(this).getReadableDatabase();
        ArrayList<Izin> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from cuti", null);
        cursor.moveToFirst();

        Izin izin;

        if (cursor.getCount() > 0) {
            do {

                izin = new Izin(
                        cursor.getString(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("nama")),
                        cursor.getString(cursor.getColumnIndexOrThrow("keterangan")),
                        cursor.getString(cursor.getColumnIndexOrThrow("status")),
                        cursor.getString(cursor.getColumnIndexOrThrow("jenis")),
                        cursor.getString(cursor.getColumnIndexOrThrow("tgl_mulai")),
                        cursor.getString(cursor.getColumnIndexOrThrow("tgl_akhir")),
                        cursor.getString(cursor.getColumnIndexOrThrow("nik"))
                );

                list.add(izin);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();

        RecyclerView recyclerView = findViewById(R.id.container);
        AdapterRekap adapter = new AdapterRekap(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}