package com.example.recyclerviewejemplo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

public class Adaptador extends RecyclerView.Adapter implements View.OnClickListener, View.OnLongClickListener {

    Context c;
    Holder h;
    View.OnClickListener listener, listenerBtImagen;
    View.OnLongClickListener longClickListener;

    public Adaptador(Context c) {
        this.c = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        h = new Holder(view,c);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        h.setClickBtImage(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listenerBtImagen!=null) listenerBtImagen.onClick(view);
            }
        });
        return h;
    }
    public  void setClickBtImage(View.OnClickListener listener) {
        if(listener!=null) listenerBtImagen= listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).bind(((MainActivity)c).datos[position], position);
    }

    @Override
    public int getItemCount() {
        return ((MainActivity)c).datos.length;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null) listener.onClick(view);
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
}
