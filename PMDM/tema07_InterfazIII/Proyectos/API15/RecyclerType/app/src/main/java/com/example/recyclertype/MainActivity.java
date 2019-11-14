package com.example.recyclertype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Dato> datos = new ArrayList<>();
    Dato d1 = new Dato("Cohete espacial","Soy un cohete espacial que viajo por el espacio interestela",BitmapFactory.decodeResource(this.getResources(), R.drawable.cohete_flat,TYPE_ITEM_1));
    Dato d2 = new Dato("Coordillera de noche","No hay nada como una noche plácida en la montaña, ¿verdad?",BitmapFactory.decodeResource(this.getResources(), R.drawable.material_flat),TYPE_ITEM_2));
    Dato d3 = new Dato("London city","No hay nada como pasear por la orilla del Támesis en una mañana con niebla",BitmapFactory.decodeResource(this.getResources(), R.drawable.london_flat),TYPE_ITEM_3));
    Dato d4 = new Dato("Discovery en la noche","Viajar al epacio, recorrer la vía láctea, y volver a casa con mi Discovery...",BitmapFactory.decodeResource(this.getResources(), R.drawable.moon_flat),TYPE_ITEM_3));

    datos.add(d1);
    datos.add(d2);
    datos.add(d3);
    datos.add(d4);



RecyclerView recyclerView;
Adaptador adaptador;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.lista);
        adaptador=new Adaptador(this);
        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adaptador.setClickBtImage(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Pulsado imagen" , Toast.LENGTH_SHORT).show();
            }
        });

    }
}
