package com.example.tema12.cloudfirestore;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private ImageView imgView;
    private TextView txtView;
    private View.OnLongClickListener longListener;
    private View.OnClickListener clickListener;

    public Holder(@NonNull View v) {
        super(v);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        imgView = v.findViewById(R.id.imagen);
        txtView = v.findViewById(R.id.texto);
    }
    public void bind(Ciudad c) {
        //imgView.setImageBitmap();
        txtView.setText(c.getNombre() + "/" + c.getPais());
    }

    @Override
    public void onClick(View v) {
        if(clickListener != null) {
            clickListener.onClick(v);
        }
    }
    public void OnClickListener(View.OnClickListener listener) {
        this.clickListener = listener;
    }

    @Override
    public boolean onLongClick(View v) {
        if(longListener != null) {
            longListener.onLongClick(v);
        }
        return true;
    }
    public void OnLongClickListener(View.OnLongClickListener listener) {
        this.longListener = listener;
    }
}
