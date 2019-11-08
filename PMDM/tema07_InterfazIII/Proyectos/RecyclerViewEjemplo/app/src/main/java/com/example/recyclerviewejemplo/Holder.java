package com.example.recyclerviewejemplo;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    Context context;
    View itemView;

    TextView txtNombre, txtApellido;
    ImageButton imagen;
    ImageButton i;
    View.OnClickListener listener;

    public Holder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
        txtNombre =  itemView.findViewById(R.id.textonombre);
        txtApellido= itemView.findViewById(R.id.textapellido);
        imagen = itemView.findViewById(R.id.imageButton);
        i = itemView.findViewById(R.id.estrella);
        imagen.setOnClickListener(this);

    }
    public  void bind(Usuario usuario, int pos){
        if (pos%2 == 0)
            itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAccent));
        else
            itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

        if(usuario.nombre.contains("o"))
            i.setVisibility(View.VISIBLE);
        else
            i.setVisibility(View.INVISIBLE);


        txtNombre.setText(usuario.getNombre());
        txtApellido.setText(usuario.getApellido());
    }
    public  void setClickBtImage(View.OnClickListener listener) {
        if(listener!=null) this.listener= listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) listener.onClick(view);
    }
}
