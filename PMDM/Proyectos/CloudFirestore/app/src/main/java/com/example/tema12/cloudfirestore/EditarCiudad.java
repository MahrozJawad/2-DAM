package com.example.tema12.cloudfirestore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class EditarCiudad extends AppCompatActivity {

    private EditText nombre;
    private EditText pais;
    private FloatingActionButton fab;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);

        nombre = findViewById(R.id.nombre);
        pais = findViewById(R.id.pais);
        fab = findViewById(R.id.fabEditar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference refNuevoPuntaje = FirebaseFirestore.getInstance().collection("Ciudades").document();
                ActualizarValorFirebase(refNuevoPuntaje);
                EditarCiudad.super.onBackPressed();
                Intent i = new Intent(EditarCiudad.this, RecyclerActivity.class);
            }
        });
    }


    private void ActualizarValorFirebase(DocumentReference refNuevoPuntaje) {

        refNuevoPuntaje.update("Nombre","Hola").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(EditarCiudad.this, "", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditarCiudad.this, "Error en modificar", Toast.LENGTH_SHORT).show();
                    }
                });
    }



}
