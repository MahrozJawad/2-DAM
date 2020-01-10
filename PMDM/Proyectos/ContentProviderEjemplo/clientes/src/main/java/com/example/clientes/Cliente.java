package com.example.clientes;

import android.net.Uri;
import android.provider.BaseColumns;

public class Cliente implements BaseColumns {
    public static final String COL_Nombre = "nombre", COL_Telefono = "telefono", COL_EMAIL = "email";
    static String uri = "content://com.example.contentproviderejemplo/clientes";
    public static Uri CONTENT_URI = Uri.parse(uri);
}
