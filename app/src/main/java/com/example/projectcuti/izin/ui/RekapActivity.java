package com.example.projectcuti.izin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projectcuti.R;
import com.example.projectcuti.interfaces.IzinInterface;
import com.example.projectcuti.izin.data.AdapterRekap;
import com.example.projectcuti.izin.data.IzinRepository;
import com.example.projectcuti.izin.data.model.Izin;
import com.example.projectcuti.model.DatabaseHandler;
import com.example.projectcuti.support.Result;
import com.example.projectcuti.support.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class RekapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_rekapdata);

        IzinRepository repo = new IzinRepository();
        Result<List<Izin>> result = repo.getIzin(this);

        List<Izin> list;

        if (result instanceof Result.Success) {
            list = ((Result.Success<List<Izin>>) result).getData();
        } else {
            Toast.makeText(this, ((Result.Error) result).getError().getMessage(), Toast.LENGTH_LONG).show();
            list = new ArrayList<>(0);
        }

        RecyclerView recyclerView = findViewById(R.id.container);
        AdapterRekap adapter = new AdapterRekap(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}