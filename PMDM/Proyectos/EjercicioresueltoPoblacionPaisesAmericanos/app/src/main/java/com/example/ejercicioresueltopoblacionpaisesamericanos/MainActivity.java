package com.example.ejercicioresueltopoblacionpaisesamericanos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] paises = {"Argentina", "Chile", "PAraguay", "Bolivia", "perú",
                                "Aquador", "Brasil", "Colombia", "Venezuela", "Uruguay", "Todos"};
    private int[] habitantes = {40,17,65,10,30,14,183,44,29,35};



    private TextView tv1;
    private ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
        lv1 = findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, paises);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (paises[i] == "Todos"){
                    int total = 0;
                    for (int j=0;j<paises.length-1;j++)
                        total = total + habitantes[j];
                    tv1.setText("Poblacion de " + lv1.getItemAtPosition(i) + " es " + total + " millones ");
                }
                else
                    tv1.setText("Poblacion de " + lv1.getItemAtPosition(i) + " es " + habitantes[i] + " millones ");
            }
        });
    }
}
