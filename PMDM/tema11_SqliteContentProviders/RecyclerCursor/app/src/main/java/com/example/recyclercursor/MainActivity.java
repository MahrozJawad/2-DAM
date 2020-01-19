package com.example.recyclercursor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    OHCategoria ohCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] from = new String[]{"nombre", "cate", "imagen"};
        int[] to = new int[]{R.id.ciclo, R.id.cate, R.id.imagen};
        setContentView(R.layout.activity_main);
        ohCategoria = new OHCategoria(this, "BBDCategoria", null, 1);

        sqLiteDatabase = ohCategoria.getReadableDatabase();
        if (sqLiteDatabase != null) {
            RecyclerView desplegable = (RecyclerView) findViewById(R.id.recycler);
            Cursor cur = sqLiteDatabase.rawQuery("select  idcategoria as _id, nombre, cate, imagen from categoria", null);
            MiRecyclerAdapter mAdapter = new MiRecyclerAdapter(R.layout.recycler_layout, cur, from, to);
            desplegable.setAdapter(mAdapter);
            desplegable.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        }
    }

    @Override
    protected void onStop() {
        sqLiteDatabase.close();
        super.onStop();
    }
}