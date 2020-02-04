package com.example.tema12.cloudfirestore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Holder extends RecyclerView.ViewHolder {

    private ImageView imgView;
    private TextView txtView;

    public Holder(@NonNull View v) {
        super(v);
        imgView = v.findViewById(R.id.imagen);
        txtView = v.findViewById(R.id.texto);
    }
    public void bind(Ciudad c) {
        txtView.setText(c.getNombre() + "/" + c.getPais());

        StorageReference ref = FirebaseStorage.getInstance().getReference(c.getImagen());

        ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Picasso.get().load(task.getResult()).into(imgView);
            }
        });
    }
}
