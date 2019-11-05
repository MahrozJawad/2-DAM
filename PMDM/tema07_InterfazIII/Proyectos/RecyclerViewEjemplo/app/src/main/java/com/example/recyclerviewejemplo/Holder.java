package com.example.recyclerviewejemplo;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {
    TextView txtNombre, txtApellido;
    public Holder(View itemView) {
        super(itemView);
        txtNombre = (TextView) itemView.findViewById(R.id.textonombre);
        txtApellido= (TextView) itemView.findViewById(R.id.textapellido);
    }
    public  void bind(Usuario usuario){
        txtNombre.setText(usuario.getNombre());
        txtApellido.setText(usuario.getApellido());
    }
}
