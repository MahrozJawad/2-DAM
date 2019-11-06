package com.example.recyclerviewejemplo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Usuario[] datos = new Usuario[] {new Usuario("Juan", "gomez", "juanGomez@gmail.com"),
        new Usuario("jose", "gomez", "juanGomez@gmail.com"),
        new Usuario("Julia", "gomez", "juanGomez@gmail.com")};

RecyclerView recyclerView;
Adaptador adaptador;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        adaptador=new Adaptador(this);
        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adaptador.setClickBtImage(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Pulsado imagen", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
