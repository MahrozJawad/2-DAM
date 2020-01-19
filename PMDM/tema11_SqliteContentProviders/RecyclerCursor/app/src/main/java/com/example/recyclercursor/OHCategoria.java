package com.example.recyclercursor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class  OHCategoria extends SQLiteOpenHelper {
    String img;
    String cadena="create table if not exists categoria(idcategoria INTEGER PRIMARY KEY NOT NULL, nombre TEXT, cate TEXT, imagen blob);";
    Context c;

    public OHCategoria(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        c=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(cadena);
        ContentValues valores = new ContentValues();

        valores.put("idcategoria",1);
        valores.put("nombre", "ASIR");
        valores.put("cate", "Superior");
        img=LibreriaImagenes.ConvertirImagenString(BitmapFactory.decodeResource(c.getResources(), R.drawable.ic_adb_black_24dp));
        valores.put("imagen", img);
        db.insertOrThrow("categoria",null,valores);

        valores.put("idcategoria", 2);
        valores.put("nombre", "DAM");
        valores.put("cate", "Superior");
        img=LibreriaImagenes.ConvertirImagenString(BitmapFactory.decodeResource(c.getResources(), R.drawable.ic_launcher));
        valores.put("imagen", img);
        db.insertOrThrow("categoria",null,valores);

        valores.put("idcategoria", 3);
        valores.put("nombre", "SMR");
        valores.put("cate", "Medio");
        img=LibreriaImagenes.ConvertirImagenString(BitmapFactory.decodeResource(c.getResources(), R.drawable.ic_local_florist_black_24dp));
        valores.put("imagen", img);
        db.insertOrThrow("categoria",null,valores);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}