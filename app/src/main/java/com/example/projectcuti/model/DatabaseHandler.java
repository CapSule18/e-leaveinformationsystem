package com.example.projectcuti.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase db = getWritableDatabase();
    }

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        SQLiteDatabase db = getWritableDatabase();
    }

    public DatabaseHandler(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
        SQLiteDatabase db = getWritableDatabase();
    }

    public DatabaseHandler(@Nullable Context context) {
        super(context, "db_cuti.db", null, 1);
        SQLiteDatabase db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE karyawan (  \n" +
                "  nik TEXT NOT NULL PRIMARY KEY,\n" +
                "  nama TEXT,\n" +
                "  jabatan TEXT,\n" +
                "  cabang TEXT,\n" +
                "  email TEXT,\n" +
                "  password TEXT\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE cuti (  \n" +
                "  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  nama TEXT,\n" +
                "  keterangan TEXT,\n" +
                "  status TEXT,\n" +
                "  jenis TEXT,\n" +
                "  tgl_mulai TEXT,\n" +
                "  tgl_akhir TEXT,\n" +
                "  nik TEXT\n" +
                ");");

//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues content = new ContentValues();
//        content.put("id", 1);
//        content.put("nama", "test insert");
//        content.put("keterangan", "cuti");
//        content.put("status", "pending");
//        content.put("email", "test@test.com");
//        content.put("tgl_mulai", "2110/10/10");
//        content.put("tgl_akhir", "2110/10/11");
//        content.put("nik", "1627382947");
//        db.insert("cuti", null, content);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists cuti");
        sqLiteDatabase.execSQL("drop table if exists karyawan");
        onCreate(sqLiteDatabase);
    }
}
