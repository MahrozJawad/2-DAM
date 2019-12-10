package com.example.pruebadatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends Activity {

    BDClientes clientes;
    SQLiteDatabase dbClientes;
    ArrayList<Cliente> listaCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientes = new BDClientes(getBaseContext(), "BDCLIENTES", null, 1);
        dbClientes = clientes.getWritableDatabase();

        if(dbClientes != null) {
//            for(int i = 0 ; i < 10; i++) {
//                String sentencia = "INSERT INTO Clientes (dni, nombre, apellidos) VALUES('"+i+"', 'nombre "+i+"', 'apellido "+i+"');";
//                dbClientes.execSQL(sentencia);
//            }

            SeleccionarDatosSelect();

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

}
