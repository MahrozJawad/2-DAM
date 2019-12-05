package com.example.examen;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

public class Adaptador extends RecyclerView.Adapter implements View.OnLongClickListener {

    Context c;
    Holder h;
    View.OnLongClickListener longClickListener;
    int pos = 0;


    public Adaptador(Context c) {
        this.c = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_activity, parent, false);
        h = new Holder(view,c);
        view.setOnLongClickListener(this);
        return h;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        pos = position;
        ((Holder)holder).bind(((ListarReparaciones)c).lista.get(position), position);
    }

    @Override
    public int getItemCount() {
        return ((ListarReparaciones)c).lista.size();
    }

    public void setLongClick(View.OnLongClickListener listener) {
        if(listener!=null) longClickListener= listener;
    }
    public int DevelvePosicion() {
        return pos;
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
}