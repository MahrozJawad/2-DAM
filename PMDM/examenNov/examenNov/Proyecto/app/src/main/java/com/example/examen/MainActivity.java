package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ArrayList<Bitmap> listaIOmagenes = new ArrayList<>();
    private Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imagen);
        b  = findViewById(R.id.btListar);

        Tarea t= new Tarea();

        t.execute();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListarReparaciones.class);
                startActivity(intent);
            }
        });

        listaIOmagenes.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.imagen));
        listaIOmagenes.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.imagen1));
        listaIOmagenes.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.imagen2));
        listaIOmagenes.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.imagen3));
        listaIOmagenes.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.imagen4));

    }

    public class Tarea extends AsyncTask<Void, Integer, Void> {

        int index = 0;

        @Override
        protected Void doInBackground(Void... voids) {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            imageView.setImageBitmap(listaIOmagenes.get(index++));
        }
    }
}
