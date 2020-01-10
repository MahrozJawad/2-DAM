package com.example.contentproviderejemplo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.UrlQuerySanitizer;
import android.renderscript.Sampler;

public class ClienteSqliteHelper extends SQLiteOpenHelper {

    String cadena = "CREATE TABLE Clientes " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, " +
            "telefono TEXT, " +
            "email TEXT);";

    public ClienteSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String nombre, telefono, email;
        sqLiteDatabase.execSQL(cadena);

        for (int i =1; i <= 15 ; i++) {
            nombre = "Cliente" + i; telefono = "900-123-00" + i; email = "email" + i + "@mail.com";
            sqLiteDatabase.execSQL("INSERT INTO Clientes(nombre, telefono, email) " + "VALUES('"+nombre+"', '"+telefono+"', '"+email+"')");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Clientes");
        sqLiteDatabase.execSQL(cadena);
    }
}
