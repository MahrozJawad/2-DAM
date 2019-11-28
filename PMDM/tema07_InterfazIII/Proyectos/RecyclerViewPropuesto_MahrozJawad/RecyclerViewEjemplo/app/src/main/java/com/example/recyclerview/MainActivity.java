package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerviewejemplo.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected ArrayList datosLista = new ArrayList<>();
    private static final int TYPE_ITEM_1 = 0;
    private static final int TYPE_ITEM_2 = 1;
    private static final int TYPE_ITEM_3 = 2;

    RecyclerView recyclerView;
    AdaptadorItemType adaptadorItemType1;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View view = findViewById(R.id.activity_main);

        datosLista.add(new Dato("Cohete espacial","Soy un cohete espacial que viajo por el espacio interestela",BitmapFactory.decodeResource(this.getResources(), R.drawable.cohete_flat),TYPE_ITEM_1));
        datosLista.add(new Dato("Coordillera de noche","No hay nada como una noche plácida en la montaña, ¿verdad?",BitmapFactory.decodeResource(this.getResources(), R.drawable.material_flat),TYPE_ITEM_2));
        datosLista.add(new Dato("London city","No hay nada como pasear por la orilla del Támesis en una mañana con niebla",BitmapFactory.decodeResource(this.getResources(), R.drawable.london_flat),TYPE_ITEM_3));
        datosLista.add(new Dato("Discovery en la noche","Viajar al epacio, recorrer la vía láctea, y volver a casa con mi Discovery...", BitmapFactory.decodeResource(this.getResources(), R.drawable.moon_flat),TYPE_ITEM_3));

        recyclerView = findViewById(R.id.recycler);
        adaptadorItemType1 = new AdaptadorItemType(this);
        recyclerView.setAdapter(adaptadorItemType1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adaptadorItemType1.setClickAImagenListener(new OnImagenClickListener() {
            @Override
            public void OnImageClick(Dato d ,View v) {
                switch (v.getId()) {
                    case R.id.imageView:
                        Toast.makeText(MainActivity.this,d.getTextoLargo(),Toast.LENGTH_LONG).show();
                        break;
                    case R.id.imageView1:
                        Toast.makeText(MainActivity.this,"Favorito",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.imageView2:
                        Toast.makeText(MainActivity.this,"Social",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.imageView3:
                        Toast.makeText(MainActivity.this,"Turned in",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bExplore:
                        Toast.makeText(MainActivity.this,"Button Explore",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bShare:
                        Toast.makeText(MainActivity.this,"Button share",Toast.LENGTH_LONG).show();
                        break;
                }

            }
        });

        recyclerView.setAdapter(adaptadorItemType1);
    }
    public interface OnImagenClickListener {
        void OnImageClick(Dato d, View v);
    }
}
