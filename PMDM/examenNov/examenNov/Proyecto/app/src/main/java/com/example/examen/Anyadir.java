package com.example.examen;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class Anyadir extends AppCompatActivity {

    private TextView fchEntrada;
    private FloatingActionButton btFGuardar;
    DatePickerDialog datePickerDialog;
    final private String ID_CANAL = "Notificacion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anyadir);
        crearcanalesNotificacion();

        fchEntrada = findViewById(R.id.fchEntrada);

        fchEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(Anyadir.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                                fchEntrada.setText(d + "/" + (m + 1) + "/" + y);
                            }
                        }, year,month,day);
                datePickerDialog.show();
            }
        });

        btFGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder notificacion;
                notificacion = new NotificationCompat.Builder(getApplicationContext(), ID_CANAL);

                NotificationCompat.BigTextStyle n = new NotificationCompat.BigTextStyle(notificacion);
                n.bigText("Tenemos una nueva ...");
                n.setBigContentTitle("Nueva Reparacion");
                n.build();

            }
        });

    }

    public NotificationChannel crearCanal(String idCanal, String nombreCanal, String descripcion, int importancia)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel(idCanal, nombreCanal, importancia);
            canal.setDescription(descripcion);
            return  canal;

        }
        return null;
    }

    private  void crearcanalesNotificacion() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            NotificationChannel canal = crearCanal(ID_CANAL, "mensajes", "mensajes de añadir reparacion", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(canal);

        }
    }

}
