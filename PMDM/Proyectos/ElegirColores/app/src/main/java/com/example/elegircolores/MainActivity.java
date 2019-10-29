package com.example.elegircolores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colores,android.R.layout.simple_expandable_list_item_1);
        Spinner listaColores = findViewById(R.id.spinner);

        listaColores.setAdapter(adapter);

    }
}
