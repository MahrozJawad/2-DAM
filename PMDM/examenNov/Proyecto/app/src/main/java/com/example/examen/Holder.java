package com.example.examen;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder  {

    Context context;
    View itemView;

    TextView id, tecnico;
    EditText fecha;

    public Holder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;

        id = itemView.findViewById(R.id.id);
        tecnico = itemView.findViewById(R.id.tecnico);
        fecha = itemView.findViewById(R.id.fecha);

    }
    public  void bind(Reparacion reparacion, int pos){

        id.setText(reparacion.getCodigo());
        tecnico.setText(reparacion.getTecnico());
        fecha.setText(reparacion.getFchEntrada());
    }

}
