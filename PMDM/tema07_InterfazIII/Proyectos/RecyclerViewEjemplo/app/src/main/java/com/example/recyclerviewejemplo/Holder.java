package com.example.recyclerviewejemplo;

import android.content.Context;
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
    View.OnClickListener listener;

    public Holder(View itemView, Context context) {
        super(itemView);
        this.itemView = itemView;
        txtNombre =  itemView.findViewById(R.id.textonombre);
        txtApellido= itemView.findViewById(R.id.textapellido);
        imagen = itemView.findViewById(R.id.imageButton);
        imagen.setOnClickListener(this);
    }
    public  void bind(Usuario usuario, int pos){
        if (pos%2 == 0) itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.cardview_dark_background));
        else itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_light_background));


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
