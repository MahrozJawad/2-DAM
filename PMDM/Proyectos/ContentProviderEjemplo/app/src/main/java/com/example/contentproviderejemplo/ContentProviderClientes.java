package com.example.contentproviderejemplo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ContentProviderClientes extends ContentProvider {

    String uri = "content://com.example.contentproviderejemplo/clientes";
    Uri CONTENT_URI = Uri.parse(uri);
    final int CLIENTES = 1, CLIENTES_ID = 2;
    String TABLA_CLIENTES = "clientes";
    private ClienteSqliteHelper bDSH;
    UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.contentproviderejemplo", "clientes", CLIENTES);
        uriMatcher.addURI("com.example.contentproviderejemplo", "clientes/#", CLIENTES_ID);
        bDSH = new ClienteSqliteHelper(getContext(), "bdClientes", null, 1);
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        String where = selection;
        if(uriMatcher.match(uri) == CLIENTES_ID) where = "_id" + uri.getLastPathSegment();
        SQLiteDatabase db = bDSH.getReadableDatabase();
        Cursor c = db.query(TABLA_CLIENTES, projection, where, selectionArgs, null, null, sortOrder);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        int cont;
        String where = selection;

        if(uriMatcher.match(uri) == CLIENTES_ID) where = "_id" + uri.getLastPathSegment();
        SQLiteDatabase db = bDSH.getReadableDatabase();
        cont = (int) db.update(TABLA_CLIENTES, where, selectionArgs);

        return cont;
    }
}
