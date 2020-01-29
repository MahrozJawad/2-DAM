package com.example.tema12.cloudfirestore;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

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

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("/Alicante.jpg");
        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Picasso.get().load(task.getResult()).into(imgView);
            }
        });
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
