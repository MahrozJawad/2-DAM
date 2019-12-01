package com.example.agenda;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Persona> datos = new ArrayList<>();
    String imagenPorDefecto;
    MiFragmento fragmentLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagenPorDefecto = BitmapAString(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.pordefecto_imagen),100,100,true));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com", imagenPorDefecto));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com", imagenPorDefecto));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com", imagenPorDefecto));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com", imagenPorDefecto));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com", imagenPorDefecto));

        fragmentLista = new MiFragmento(datos, imagenPorDefecto);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragmentLista);
        fragmentTransaction.commit();

    }

    public static String BitmapAString(Bitmap imagen) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] byte_arr = stream.toByteArray();
        String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);
        return image_str;
    }
    public static Bitmap StringABitmap(String imagen) {
        byte[] decodedString = Base64.decode(imagen, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

}
