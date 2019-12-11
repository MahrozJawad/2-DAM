package com.example.pruebadatabase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorClientes extends ArrayAdapter {
    Activity activityContext;
    ArrayList<Cliente> objetos;

    public AdaptadorClientes(Activity context, int resource, ArrayList objetos) {
        super(context, resource, objetos);
        activityContext = context;
        this.objetos = objetos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;

        if(vista == null) {
            LayoutInflater inflater = activityContext.getLayoutInflater();
            vista = inflater.inflate(R.layout.list_layout,null);
            ((TextView)vista.findViewById(R.id.dniTextView)).setText(objetos.get(position).getDni());
            ((TextView)vista.findViewById(R.id.nombreTextView)).setText(objetos.get(position).getNombre());
            ((TextView)vista.findViewById(R.id.apellidosTextView)).setText(objetos.get(position).getApelllidos());
        }

        return vista;
    }
}
