package com.example.leecon;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class Adapter extends FirebaseRecyclerAdapter<Prediccion, PrediccionHolder> implements View.OnClickListener {
    private View.OnClickListener listener;

   Adapter(@NonNull FirebaseRecyclerOptions<Prediccion> options) {
       super(options);
   }

    @Override
    protected void onBindViewHolder(@NonNull PrediccionHolder holder, int position, @NonNull Prediccion model) {
       holder.bind(model);
    }

    @NonNull
    @Override
    public PrediccionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_re,viewGroup,false);
       view.setOnClickListener(this);
       return new PrediccionHolder(view);
    }

    void onClickListener(View.OnClickListener listener) {
         this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null) listener.onClick(v);
    }
}
