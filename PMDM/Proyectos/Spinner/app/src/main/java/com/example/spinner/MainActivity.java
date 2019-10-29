package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    final String[] semana = new String[]{"Lunes", "Martes","Miércoles","Jueves","Viernes","Sábado","Domingo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_dropdown_item_1line, semana);

        Spinner dias = findViewById(R.id.CmbOpciones);
        dias.setAdapter(adaptador);
    }
}
