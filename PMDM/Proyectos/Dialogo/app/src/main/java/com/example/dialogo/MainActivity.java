package com.example.dialogo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b;
    AlertDialog.Builder builder;
    AlertDialog ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.darDeAlta);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                View view = inflater.inflate(R.layout.dialogo_layout, null);
                builder.setView(view);
                ad = builder.create();

                Button bCreaCuenta= view.findViewById(R.id.CrearCuenta);
                bCreaCuenta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(MainActivity.this, "Se ha creado la cuenta, Perfectamente", Toast.LENGTH_LONG).show();
                        ad.dismiss();

                    }
                });

                Button bEntrar= view.findViewById(R.id.Entrar);
                final EditText editText_Nombre = view.findViewById(R.id.EditTextNombre);
                final EditText editText_Contraseña = view.findViewById(R.id.EditTextContraseña);

                bEntrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!editText_Nombre.getText().toString().equals("") && !editText_Contraseña.getText().toString().equals("")) {

                            Toast.makeText(MainActivity.this, "Se ha entrado ", Toast.LENGTH_LONG).show();
                            b.setVisibility(View.GONE);
                            ad.dismiss();

                            //Entrando en la cuenta
                            LinearLayout contenedor = new LinearLayout(getApplicationContext());
                            contenedor.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                            contenedor.setOrientation(LinearLayout.VERTICAL);
                            contenedor.setGravity(Gravity.CENTER);

                            TextView miTextView = new TextView(getApplicationContext());
                            miTextView.setTextSize(24);
                            miTextView.setText(editText_Nombre.getText().toString());
                            miTextView.setGravity(Gravity.CENTER);
                            contenedor.addView(miTextView);

                            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(400, 1500, Gravity.CENTER);
                            addContentView(contenedor, params);
                        }
                        else
                            Toast.makeText(MainActivity.this, "Debes de rellenar el nombre y Contraseña " , Toast.LENGTH_LONG).show();
                    }
                });

                ad.show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
