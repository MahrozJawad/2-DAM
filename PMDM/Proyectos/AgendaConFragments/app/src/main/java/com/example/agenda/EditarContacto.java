package com.example.agenda;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class EditarContacto extends AppCompatActivity implements View.OnClickListener {

    private static final int CODIGO_GALERIA = 1;

    private Persona p = null;
    private ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_contact);

        final EditText editTextNombre = findViewById(R.id.editText);
        final EditText editTextApellidos = findViewById(R.id.editText2);
        final EditText editTextTelefono = findViewById(R.id.editText3);
        final EditText editTextCorreo = findViewById(R.id.editText4);
        imagen = findViewById(R.id.imagen);
        imagen.setOnClickListener(this);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        Intent intentR = getIntent();
        p = intentR.getParcelableExtra("DatoPersona");

        if(p!= null){

            editTextNombre.setText(p.getNombre());

            editTextApellidos.setText(p.getApellidos());

            editTextTelefono.setText(p.getTelefono());

            editTextCorreo.setText(p.getCorreo());

            String ArrayImagen = p.getImagen();
            imagen.setImageBitmap(MainActivity.StringABitmap(ArrayImagen));

        }
        else {
            p = new Persona();
        }
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    p.setNombre(editTextNombre.getText().toString());
                    p.setApellidos(editTextApellidos.getText().toString());
                    p.setCorreo(editTextCorreo.getText().toString());
                    p.setTelefono(editTextTelefono.getText().toString());

                    BitmapDrawable drawable = (BitmapDrawable) imagen.getDrawable();
                    if(drawable != null) {
                        Bitmap bitmap = drawable.getBitmap();
                        p.setImagen(MainActivity.BitmapAString(bitmap));
                    }
                    else
                        p.setImagen(MainActivity.BitmapAString(BitmapFactory.decodeResource(EditarContacto.this.getResources(), R.drawable.pordefecto_imagen)));


                    if(!p.getNombre().equals("")){
                        intent.putExtra("PersonaEditado", p);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"El nombre es obligatorio", Toast.LENGTH_SHORT).show();

                }
            });

            editTextNombre.setOnFocusChangeListener( new View.OnFocusChangeListener() {
                @Override
                public  void onFocusChange(View v, boolean hasFocus) {
                    onFocusChangeParaTodos(hasFocus, (ImageView) findViewById(R.id.imageView7));
                }
            });
            editTextApellidos.setOnFocusChangeListener( new View.OnFocusChangeListener() {
                @Override
                public  void onFocusChange(View v, boolean hasFocus) {
                    onFocusChangeParaTodos(hasFocus, (ImageView) findViewById(R.id.imageView11));
                }
            });
            editTextTelefono.setOnFocusChangeListener( new View.OnFocusChangeListener() {
                @Override
                public  void onFocusChange(View v, boolean hasFocus) {
                    onFocusChangeParaTodos(hasFocus, (ImageView) findViewById(R.id.imageView12));
                }
            });
            editTextCorreo.setOnFocusChangeListener( new View.OnFocusChangeListener() {
                @Override
                public  void onFocusChange(View v, boolean hasFocus) {
                    onFocusChangeParaTodos(hasFocus, (ImageView) findViewById(R.id.imageView9));
                }
            });


    }
    public void onFocusChangeParaTodos(boolean hasFocus, ImageView img) {
        ImageView ic = img;
        Drawable d = ic.getDrawable();
        d= DrawableCompat.wrap(d);
        if(hasFocus)
            DrawableCompat.setTint(d, ContextCompat.getColor(getApplicationContext(),R.color.design_default_color_primary));
        else
            DrawableCompat.setTint(d, ContextCompat.getColor(getApplicationContext(),R.color.design_default_color_primary_dark));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, "Seleccione una imagen"),
                CODIGO_GALERIA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage;

        switch (requestCode) {
            case CODIGO_GALERIA:
                if (resultCode == RESULT_OK) {
                    selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();
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
                            imagen.setImageBitmap(Bitmap.createScaledBitmap(bmp, 100, 100, true));
                        }
                    }
                }
                break;
        }
    }

}
