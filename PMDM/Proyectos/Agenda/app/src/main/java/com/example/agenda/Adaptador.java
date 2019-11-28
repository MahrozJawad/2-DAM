package com.example.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

public class Adaptador extends RecyclerView.Adapter implements View.OnClickListener {

    Context c;
    Holder h;
    View.OnClickListener listener, listenerImagen;

    public Adaptador(Context c) {
        this.c = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacto, parent, false);
        h = new Holder(view,c);
        view.setOnClickListener(this);
        h.setImagenListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listenerImagen!=null) listenerImagen.onClick(view);
            }
        });
        return h;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).bind(((MainActivity)c).datos.get(position), position);
    }

    public  void setClickImage(View.OnClickListener listener) {
        if(listener!=null) listenerImagen= listener;
    }
    public  void setClickOnView(View.OnClickListener listener) {
        if(listener!=null) this.listener= listener;
    }
    @Override
    public int getItemCount() {
        return ((MainActivity)c).datos.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null) listener.onClick(view);
    }

}
