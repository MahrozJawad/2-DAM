package com.example.agenda;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {

    Context context;
    View itemView;

    TextView txtNombre, txtApellido, txtTelefono, txtCorreo;
    ImageView imagen;
    View.OnClickListener listener;

    public Holder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
        txtNombre =  itemView.findViewById(R.id.Nombre);
        txtApellido= itemView.findViewById(R.id.Apellidos);
        txtCorreo = itemView.findViewById(R.id.Correo);
        txtTelefono = itemView.findViewById(R.id.Telefono);
        imagen = itemView.findViewById(R.id.imageView);

    }
    public  void bind(Persona persona, int pos){

        txtNombre.setText(persona.getNombre());
        txtApellido.setText(persona.getApellidos());
        txtCorreo.setText(persona.getCorreo());
        txtTelefono.setText(persona.getTelefono());
        imagen.setImageBitmap(MainActivity.StringABitmap(persona.getImagen()));
    }

}
