package com.example.tema12.cloudfirestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class RecyclerActivity extends AppCompatActivity {

    FirestoreRecyclerOptions<Ciudad> firestoreRecyclerOptions;
    public Adaptador adaptador;
    public RecyclerView recyclerView;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);

        query = FirebaseFirestore.getInstance().collection("Ciudades");

        firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Ciudad>()
                .setQuery(query, Ciudad.class).build();
        adaptador = new Adaptador(firestoreRecyclerOptions);

        adaptador.onClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptador.getSnapshots().getSnapshot(recyclerView.getChildAdapterPosition(v)).getId();
                Intent i = new Intent(RecyclerActivity.this, EditarCiudad.class);

                startActivity(i);
            }
        });
        adaptador.onLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(RecyclerActivity.this, "Long Click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        adaptador.startListening();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStop() {
        adaptador.stopListening();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
