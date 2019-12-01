package com.example.agenda;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.example.agenda.MainActivity.BitmapAString;

public class MiFragmento extends Fragment implements View.OnClickListener,  View.OnLongClickListener {

    private static final int CODIGO_EDITAR = 2;
    private static final int CODIGO_GALERIA = 1;
    private static final int CODIGO_AÑADIR = 3;
    RecyclerView recyclerView;
    Adaptador adaptador;
    private SwipeDetector swipeDetector;
    ArrayList<Persona> datos;
    ImageView imagen;
    String imagenPorDefecto;
    int pos;
    Persona p;

    public MiFragmento(ArrayList<Persona> lista, String imagenPorDefecto){
        datos = lista;
        this.imagenPorDefecto = imagenPorDefecto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_recyclerview, container, false);

        imagen = rootView.findViewById(R.id.imageView);

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AñadirDatos();
            }
        });

        recyclerView = rootView.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        adaptador = new Adaptador(getContext());
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        adaptador.SetOnLongClick(this);
        swipeDetector = new SwipeDetector();
        adaptador.setClickOnView(this);
        adaptador.SetOnTouchListener(swipeDetector);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public boolean onLongClick(View v) {
        pos = recyclerView.getChildAdapterPosition(v);
        Persona d = datos.get(pos);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("¿Seguro que quieres eliminar a " + d.getNombre() + "?");
        builder.setPositiveButton("ELIMINAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                datos.remove(pos);
                recyclerView.setAdapter(adaptador);
            }
        });
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
        return true;
    }

    @Override
    public void onClick(View v) {
        pos = recyclerView.getChildAdapterPosition(v);
        if (swipeDetector.swipeDetected()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            switch (swipeDetector.getAction()) {
                case LR:
                    builder.setMessage("¿Estas seguro que quieres llamar a " + datos.get(pos).getNombre() + "?");
                    builder.setPositiveButton("LLAMAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("telefono:" + datos.get(pos).getTelefono()));
                            if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                                startActivity(intent);
                            }
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.create().show();
                    break;
                case RL:
                    builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("¿Estas seguro que quieres enviar mensaje a " + datos.get(pos).getNombre() + "?");
                    builder.setPositiveButton("enviar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_SENDTO);
                            intent.setData(Uri.fromParts("mailto", datos.get(pos).getCorreo(), null));
                            Intent chooser = Intent.createChooser(intent, "Enviar mensaje...");
                            startActivity(chooser);
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.create().show();
                    break;
            }
        } else {
            EditarDatos();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage;

        switch (requestCode) {
            case CODIGO_GALERIA:
                if (resultCode == RESULT_OK) {
                    selectedImage = data.getData();
                    String selectedPath=selectedImage.getPath();
                    if (requestCode == 1) {
                        if (selectedPath != null) {
                            InputStream imageStream = null;
                            try {
                                imageStream = getContext().getContentResolver().openInputStream(
                                        selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                            datos.get(pos).setImagen(BitmapAString(Bitmap.createScaledBitmap(bmp, 100,100, true)));
                        }
                    }
                }
                break;
            case CODIGO_EDITAR:
                if (resultCode == RESULT_OK) {
                    p = data.getParcelableExtra("PersonaEditado");
                    if(p != null){
                        datos.get(pos).setTelefono(p.getTelefono());
                        datos.get(pos).setApellidos(p.getApellidos());
                        datos.get(pos).setCorreo(p.getCorreo());
                        datos.get(pos).setNombre(p.getNombre());
                        datos.get(pos).setImagen(p.getImagen());

                    }
                }
                break;
            case CODIGO_AÑADIR:
                if (resultCode == RESULT_OK) {
                    p = data.getParcelableExtra("PersonaEditado");
                    if(p != null){
                        p.setImagen(imagenPorDefecto);
                        datos.add(p);
                    }
                }
                break;
        }
        recyclerView.setAdapter(adaptador);
    }
    private void EditarDatos() {
        Intent intent = new Intent(getContext(), EditarContacto.class);
        Persona p = datos.get(pos);
        intent.putExtra("DatoPersona", p);
        startActivityForResult(intent,CODIGO_EDITAR);
    }
    private void AñadirDatos() {
        Intent intent = new Intent(getContext(), EditarContacto.class);
        Persona p = null;
        intent.putExtra("DatoPersona", p);
        startActivityForResult(intent,CODIGO_AÑADIR);
    }
}
