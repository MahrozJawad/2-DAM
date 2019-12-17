package com.example.simplecursoradapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    OHCategoria ohCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] from = new String[] {"nombre", "cate" };
        int[] to = new int[] {R.id.ciclo, R.id.cate};
        ohCategoria = new OHCategoria(this, "BBDCategorias", null, 1);
        sqLiteDatabase = ohCategoria.getWritableDatabase();
        insertDatosCodigo();

        sqLiteDatabase = ohCategoria.getReadableDatabase();
        if(sqLiteDatabase!=null) {
            RecyclerView desplegable = findViewById(R.id.recycler);
            Cursor cur = sqLiteDatabase.rawQuery("select idcategoria as _id, nombre, cate, imagen from categoria", null);
            MyRecyclerAdapter mAdapter = new MyRecyclerAdapter(R.layout.activity_main, cur,from,to);
            desplegable.setAdapter(mAdapter);
            desplegable.setLayoutManager(new LinearLayoutManager(this,desplegable.VERTICAL, false));
            sqLiteDatabase.close();
        }
    }

    public static Bitmap convertirStringbitmap(String imagen) {
        byte[] decodedString = Base64.decode(imagen, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0,decodedString.length);
    }

    static public String ConvertirImagenString(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] byte_arr = stream.toByteArray();
        String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);
        return image_str;
    }

    private void insertDatosCodigo() {
        sqLiteDatabase = ohCategoria.getWritableDatabase();
        String img;
        if(sqLiteDatabase!=null) {
            ContentValues valores = new ContentValues();
            valores.put("Nombre", "ASIR");
            valores.put("Cate", "Superior");
            valores.put("idcategoria", 1);
            img = ConvertirImagenString(BitmapFactory.decodeResource(getResources(), R.drawable.tweety));
            valores.put("imagen", img);
            sqLiteDatabase.insert("categoria", null, valores);

            valores.put("Nombre", "DAM");
            valores.put("Cate", "Superior");
            valores.put("idcategoria", 2);
            img = ConvertirImagenString(BitmapFactory.decodeResource(getResources(), R.drawable.tweety));
            valores.put("imagen", img);
            sqLiteDatabase.insert("categoria", null, valores);

            valores.put("Nombre", "SMR");
            valores.put("Cate", "Medio");
            valores.put("idcategoria", 3);
            img = ConvertirImagenString(BitmapFactory.decodeResource(getResources(), R.drawable.tweety));
            valores.put("imagen", img);
            sqLiteDatabase.insert("categoria", null, valores);


            sqLiteDatabase.close();
        }
    }
}
