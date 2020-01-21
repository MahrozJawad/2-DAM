package com.example.tema12.pruebafirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Prediction;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

//    EditText etTexto;
//    TextView tvSalida;
//    Button btEnviar;
//    ChildEventListener childEventListener;
//    FirebaseDatabase database;
//    Adapter mAdapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        etTexto = findViewById(R.id.etTexto);
//        tvSalida = findViewById(R.id.tvSalida);
//        btEnviar = findViewById(R.id.btEnviar);
//        database = FirebaseDatabase.getInstance();
//        DatabaseReference dato = FirebaseDatabase.getInstance().getReference().child("items").child("msg");
//        btEnviar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!etTexto.getText().toString().isEmpty()) {
//                    database.getReference("items").push().setValue(new Items(etTexto.getText().toString()));
//                }
//            }
//        });
//
//        dato.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Toast.makeText(getApplicationContext(), (String)dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        database.getReference("items").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                tvSalida.setText(dataSnapshot.getValue().toString());
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Toast.makeText(getApplicationContext(),dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                tvSalida.setText(dataSnapshot.getValue().toString());
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                tvSalida.setText(dataSnapshot.getValue().toString() + "ha sido eliminado");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    //Ejercicio2

    TextView etCielo, etHumedad, etTiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCielo = findViewById(R.id.tCielo);
        etHumedad = findViewById(R.id.tHumedad);
        etTiempo = findViewById(R.id.tTemperatura);
        DatabaseReference dbPred = FirebaseDatabase.getInstance().getReference().child("items").child("msg");

        dbPred.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Prediccion p = dataSnapshot.getValue(Prediccion.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
