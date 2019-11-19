package com.example.agenda;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Persona> datos = new ArrayList<>();

    RecyclerView recyclerView;
    Adaptador adaptador;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Añadir Contacto", Toast.LENGTH_SHORT).show();
            }
        });

        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com"));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com"));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com"));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com"));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com"));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com"));

        recyclerView = findViewById(R.id.recycler);
        adaptador=new Adaptador(this);
        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adaptador.setLongClickOnView(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "Eliminar", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        adaptador.setClickOnView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Recycler", Toast.LENGTH_SHORT).show();
            }
        });
        adaptador.setClickImage(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hola", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
