package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floating_action_button);

        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Pulsaste Fab", Toast.LENGTH_SHORT).show();
            }
        });

    }

}




/*package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button botonOrdenar=(Button)findViewById(R.id.botonImagen);
        ImageButton botonImagen = (ImageButton) findViewById(R.id.buttonImage);
        botonOrdenar.setOnClickListener(this);
        botonImagen.setOnClickListener(this);


        /*Button botonOrdenar=(Button)findViewById(R.id.botonImagen);
        botonOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Pulsaste Ordenar", Toast.LENGTH_SHORT).show();
            }
        });*/

        /*final ToggleButton btnToggle = (ToggleButton)findViewById(R.id.toggleButton);
        btnToggle.setOnClickListener(new View.OnClickListener() {
            public  void onClick(View arg0)
            {
                if(btnToggle.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Pulsaste Togle a ON", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Pulsaste Togle a OFF", Toast.LENGTH_SHORT).show();
            }
        });

        final Switch btnSwitch = (Switch)findViewById(R.id.switchButton);
        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(btnSwitch.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Desplazaste Switch a ON", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Pulsaste Togle a OFF", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public  void clickAgregar(View v) {
        Toast.makeText(this,"Pulsaste Agregar", Toast.LENGTH_LONG).show();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botonImagen:
                Toast.makeText(getApplicationContext(), "Pulsaste Ordenar", Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonImage:
                Toast.makeText(getApplicationContext(), "Pulsaste Imagen", Toast.LENGTH_LONG).show();
                break;
        }
    }



}*/
