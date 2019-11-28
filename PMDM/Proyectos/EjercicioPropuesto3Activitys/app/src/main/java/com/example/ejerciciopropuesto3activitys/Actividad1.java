package com.example.ejerciciopropuesto3activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Actividad1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1);

        Toast t = Toast.makeText(getApplicationContext(),"Actividad Secundario 1 iniciado desde Actividad principal",Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Actividad1.this,MainActivity.class);
        i.putExtra("Texto","Volviendo de Actividad 1 -> Activity1 Finalizado");
        startActivity(i);
    }
}
