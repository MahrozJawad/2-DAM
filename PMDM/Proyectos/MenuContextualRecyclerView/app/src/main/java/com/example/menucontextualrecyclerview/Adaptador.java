package com.example.menucontextualrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter {

    Context c;
    Holder h;
    ArrayList<String> datos = new ArrayList<>();


    public Adaptador(Context c) {
        datos.add("A");
        datos.add("B");
        datos.add("C");
        this.c = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fichero_text_view, parent, false);

        h = new Holder(view);
        return h;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).Bind(datos.get(position));

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

}
