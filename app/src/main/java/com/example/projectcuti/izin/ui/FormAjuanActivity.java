package com.example.projectcuti.izin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcuti.DatePickerFragment;
import com.example.projectcuti.R;
import com.example.projectcuti.izin.data.IzinRepository;
import com.example.projectcuti.izin.data.model.Izin;
import com.example.projectcuti.support.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class FormAjuanActivity extends AppCompatActivity {
    Button btnCheckout;
    EditText txtKeterangan, txtSince, txtUntil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputajuan);

        String jenis = getIntent().getStringExtra("jenis");

        String title = "Permohonan " + jenis;

        ((TextView) findViewById(R.id.pageTitle)).setText(title);

        txtKeterangan = findViewById(R.id.txt_keterangan);
        txtSince = findViewById(R.id.txt_since);
        txtUntil = findViewById(R.id.txt_until);
        btnCheckout = findViewById(R.id.btnCheckout);

        // show date picker for since date
        txtSince.setOnClickListener(v -> {
            DatePickerFragment newFragment = new DatePickerFragment(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                    txtSince.setText(convertToLatinDate(date));
                }
            });
            newFragment.show(getSupportFragmentManager(), "datePicker");
        });

        // show date picker for until date
        txtUntil.setOnClickListener(v -> {
            DatePickerFragment newFragment = new DatePickerFragment(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                    txtUntil.setText(convertToLatinDate(date));
                }
            });
            newFragment.show(getSupportFragmentManager(), "datePicker");
        });

        btnCheckout.setOnClickListener(view -> {
            try {
                submitForm(jenis);
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void submitForm(String type) {
        IzinRepository izinRepository = new IzinRepository();

        Result<Izin> response = izinRepository.izin(
                txtKeterangan.getText().toString(),
                convertToOriginalDate(txtSince.getText().toString()),
                convertToOriginalDate(txtUntil.getText().toString()),
                type,
                this
        );

        Log.i("IZIN", response.getClass().toString());

        if (response instanceof Result.Success) {
            Toast.makeText(this, "Berhasil input data cuti.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, RekapActivity.class));
            finish();
        } else if (response instanceof Result.Error) {
            Log.i("IZIN", Objects.requireNonNull(((Result.Error) response).getError().getMessage()));
            Toast.makeText(this, Objects.requireNonNull(((Result.Error) response).getError().getMessage()), Toast.LENGTH_LONG).show();
        }
    }

    public static String convertToLatinDate(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = inputFormat.parse(inputDate);

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return inputDate;
        }
    }

    public static LocalDate convertToOriginalDate(String latinDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault());
        return LocalDate.parse(latinDate, inputFormatter);
    }
}