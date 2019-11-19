package com.xusa.tema7.fragmentv3recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by xusa on 22/11/2015.
 */
public class Adapter extends RecyclerView.Adapter implements View.OnClickListener {
    ArrayList<String> datos;
    View.OnClickListener listener;

    public Adapter(ArrayList<String> datos) {
        this.datos = datos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.linea_recycler,parent,false);
        Holder holder=new Holder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).bind(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void miOnClick(View.OnClickListener listener)
    {
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null) listener.onClick(v);

    }
}

class Holder extends  RecyclerView.ViewHolder {
    TextView lineTexto;
    public Holder(View itemView) {
        super(itemView);
        lineTexto=(TextView)itemView.findViewById(R.id.linea_lista);
    }
    public  void bind(String cadena)
    {
        lineTexto.setText(cadena+"\n\n");
    }
}

