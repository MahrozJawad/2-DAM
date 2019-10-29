package com.example.pickers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText fecha;
    DatePickerDialog datePickerDialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker_dialog);


        //*************Time picker -> se muestra por dentro la horas  hasta 24h.************

       /*TimePicker simpleTp = findViewById(R.id.simpleTimePicker);
        simpleTp.setIs24HourView(true);

        simpleTp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hoursOfDay, int minute) {
                Toast.makeText(getApplicationContext(), hoursOfDay+" "+minute, Toast.LENGTH_SHORT).show();
            }
        });*/

       //******************DatePicker Dialogo********************

        /*fecha = findViewById(R.id.fechaNac);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                fecha.setText(day + "/"+(month+1)+"/"+year);
                            }
                        },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });*/

        builder= new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Quieres salir de la aplicación")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        Button b = findViewById(R.id.butonCerrar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }
}

