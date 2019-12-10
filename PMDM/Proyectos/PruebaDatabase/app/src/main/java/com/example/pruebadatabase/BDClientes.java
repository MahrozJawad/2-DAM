package com.example.pruebadatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDClientes extends SQLiteOpenHelper {

    String sentencia = "Create table if not exists clientes (dni VARCHAR PRIMARY KEY NOT NULL, nombre TEXT, apellidos TEXT);";

    public BDClientes(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sentencia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
