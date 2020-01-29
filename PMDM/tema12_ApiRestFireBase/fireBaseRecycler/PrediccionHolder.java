package com.example.leecon;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

/**
 * Created by josebalmasedadelalamo on 26/1/17.
 */

public class PrediccionHolder extends RecyclerView.ViewHolder  {
    EditText tCielo, tTemparatura,tHumedad,tFecha;

    public PrediccionHolder(View v) {
        super(v);
        tCielo=(EditText) v.findViewById(R.id.tCielo);
        tHumedad=(EditText) v.findViewById(R.id.tHumedad);
        tTemparatura=(EditText) v.findViewById(R.id.tTemperatura);
        tFecha=(EditText)v.findViewById(R.id.tFecha);
    }

    public void bind(Prediccion item) {
        tCielo.setText(item.getCielo() );
        tTemparatura.setText(item.getTemperatura()+"ºC");
        tHumedad.setText(item.getHumedad()+"%");
        tFecha.setText(item.getFecha());
    }
}