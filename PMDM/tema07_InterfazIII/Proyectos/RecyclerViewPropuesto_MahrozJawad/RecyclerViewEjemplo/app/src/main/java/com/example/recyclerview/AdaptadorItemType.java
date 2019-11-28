package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewejemplo.R;


public class AdaptadorItemType extends RecyclerView.Adapter implements View.OnClickListener {

    Context c;
    HolderItemType h;
    MainActivity.OnImagenClickListener listener;

    Dato d;

    public AdaptadorItemType(Context c) {
        this.c = c;
    }

    @Override
    public int getItemViewType(int position) {
        d = (Dato) (((MainActivity)c).datosLista).get(position);
        return d.getTipo();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
            System.out.println(getItemViewType(viewType) + "Hola");

        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_1, parent, false);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_2, parent, false);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_3, parent, false);
                break;
            default:
                break;
        }

        h = new HolderItemType(view,c, viewType);
        view.setOnClickListener(this);

        h.setClickAImagenListener(new MainActivity.OnImagenClickListener() {
            @Override
            public void OnImageClick(Dato d, View v) {
                listener.OnImageClick(d, v);
            }
        });

        return h;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((HolderItemType)holder).bind(d, position);
    }

    @Override
    public int getItemCount() {
        return ((MainActivity)c).datosLista.size();
    }

    public void setClickAImagenListener(MainActivity.OnImagenClickListener listener){
        if(listener!=null)
            this.listener = listener;
    }


    @Override
    public void onClick(View v) {

    }
}
