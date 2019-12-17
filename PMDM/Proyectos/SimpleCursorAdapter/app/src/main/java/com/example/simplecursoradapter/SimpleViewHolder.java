package com.example.simplecursoradapter;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class SimpleViewHolder extends RecyclerView.ViewHolder {
    TextView[] view = new TextView[2];
    ImageView imagen;

    public SimpleViewHolder(@NonNull View itemView, TextView[] view, ImageView imagen) {
        super(itemView);
        this.view = view;
        this.imagen = imagen;
    }

    public void bind(int pos, String dato) {
        view[pos].setText(dato);
    }
    public  void  bind(Bitmap dato) {
        imagen.setImageBitmap(dato);
    }
}
