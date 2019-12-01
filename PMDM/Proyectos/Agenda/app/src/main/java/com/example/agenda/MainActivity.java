package com.example.agenda;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.example.agenda.SwipeDetector.Action.RL;

public class MainActivity extends AppCompatActivity implements OnLongClickListener, View.OnClickListener {

    private static final int CODIGO_EDITAR = 2;
    private static final int CODIGO_GALERIA = 1;
    private static final int CODIGO_AÑADIR = 3;
    ArrayList<Persona> datos = new ArrayList<>();
    String imagenPorDefecto;
    private SwipeDetector swipeDetector;

    RecyclerView recyclerView;
    Adaptador adaptador;
    ImageView imagen;
    int pos;
    Persona p;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage;

        switch (requestCode) {
            case CODIGO_GALERIA:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImage = data.getData();
                    String selectedPath=selectedImage.getPath();
                    if (requestCode == 1) {
                        if (selectedPath != null) {
                            InputStream imageStream = null;
                            try {
                                imageStream = getContentResolver().openInputStream(
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        imagen = findViewById(R.id.imageView);
        imagenPorDefecto = BitmapAString(BitmapFactory.decodeResource(this.getResources(), R.drawable.pordefecto_imagen));

        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AñadirDatos();
            }
        });

        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com", imagenPorDefecto));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com", imagenPorDefecto));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com", imagenPorDefecto));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com", imagenPorDefecto));
        datos.add(new Persona("Nombre", "Apellido", "631637415", "m@hola.com", imagenPorDefecto));

        recyclerView = findViewById(R.id.recycler);
        adaptador=new Adaptador(this);

        adaptador.SetOnLongClick(this);
        swipeDetector = new SwipeDetector();
        adaptador.setClickOnView(this);
        adaptador.SetOnTouchListener(swipeDetector);
        adaptador.setClickImage(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(intent, "Seleccione una imagen"),
                        CODIGO_GALERIA);

            }
        });
        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void EditarDatos() {
        Intent intent = new Intent(MainActivity.this, EditarContacto.class);
        Persona p = datos.get(pos);
        intent.putExtra("DatoPersona", p);
        startActivityForResult(intent,CODIGO_EDITAR);
    }
    private void AñadirDatos() {
        Intent intent = new Intent(MainActivity.this, EditarContacto.class);
        Persona p = null;
        intent.putExtra("DatoPersona", p);
        startActivityForResult(intent,CODIGO_AÑADIR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    public boolean onLongClick(View v) {
        pos = recyclerView.getChildAdapterPosition(v);
        Persona d = datos.get(pos);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            switch (swipeDetector.getAction()) {
                case LR:
                        builder.setMessage("¿Estas seguro que quieres llamar a " + datos.get(pos).getNombre() + "?");
                        builder.setPositiveButton("LLAMAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("telefono:" + datos.get(pos).getTelefono()));
                                    if (intent.resolveActivity(getPackageManager()) != null) {
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
                        builder = new AlertDialog.Builder(MainActivity.this);
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
}
