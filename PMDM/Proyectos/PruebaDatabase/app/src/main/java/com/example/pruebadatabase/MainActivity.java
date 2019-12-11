package com.example.pruebadatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    BDClientes clientes;
    SQLiteDatabase dbClientes;
    ArrayList<Cliente> listaCliente;
    ListView listaView;
    Button bMostrar;
    Button binsertar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaView = findViewById(R.id.lista);
        bMostrar = findViewById(R.id.buttonMostrar);
        binsertar = findViewById(R.id.buttonInsertar);

        clientes = new BDClientes(getBaseContext(), "BDCLIENTES", null, 1);
        dbClientes = clientes.getWritableDatabase();

        SelectDatosCodigo(new String[]{"dni", "nombre", "apellidos"}, null,null, "apellidos");

            binsertar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            bMostrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AdaptadorClientes adapter = new AdaptadorClientes(MainActivity.this,R.layout.list_layout,listaCliente);
                    listaView.setAdapter(adapter);
                }
            });



//              ContentValues valores = new ContentValues();
//              valores.put("nombre", "Ana");
//              valores.put("dni", "123456789");
//              valores.put("apellidos", "Perez Rico");
//              dbClientes.insert("Clientes", null, valores);

//            valores.put("nombre", "Mahroz");
//            valores.put("dni", "Y3021214V");
//            valores.put("apellidos", "Jawad");
//            dbClientes.update("clientes", valores, "dni=0",null);

            //dbClientes.close();


    }

    private boolean SeleccionarDatosSelect() {

        dbClientes = clientes.getReadableDatabase();
        if(dbClientes != null) {
            Cursor cursor = dbClientes.rawQuery("SELECT * FROM Clientes order by apellidos", null);
            listaCliente = Cliente.getClientes(cursor);
            dbClientes.close();
            if(listaCliente == null) return false;
            else return true;
        }
        return false;
    }

    private boolean SelectDatosCodigo(String[] columna, String where, String[] valores, String orderBy) {
        dbClientes = clientes.getReadableDatabase();
        if(dbClientes!=null) {
            Cursor cursor = dbClientes.query("Clientes", columna, where, valores,null,null,orderBy);
            listaCliente = Cliente.getClientes(cursor);
            dbClientes.close();
            if(listaCliente == null) return false;
            else return true;
        }
        return false;
    }



}
