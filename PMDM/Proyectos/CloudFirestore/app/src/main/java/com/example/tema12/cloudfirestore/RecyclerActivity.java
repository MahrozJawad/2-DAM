package com.example.tema12.cloudfirestore;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class RecyclerActivity extends AppCompatActivity {

    FirebaseRecyclerOptions<Ciudad> firebaseRecyclerOptions;
    Adaptador adaptador;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);

        Query query = FirebaseDatabase.getInstance().getReference("/Addresses");

        recyclerView = findViewById(R.id.recycler);
        firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Ciudad>()
                .setQuery(query, Ciudad.class).build();
        adaptador = new Adaptador(firebaseRecyclerOptions);
        adaptador.onClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        adaptador.onLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
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
        adaptador.startListening();
    }
}
