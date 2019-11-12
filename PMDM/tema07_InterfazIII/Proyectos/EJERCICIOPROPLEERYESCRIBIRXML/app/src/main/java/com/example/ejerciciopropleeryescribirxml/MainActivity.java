package com.example.ejerciciopropleeryescribirxml;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    Button leer, escribir, buttonNuevoJugador;
    EditText nombre, puntos;
    TextView contenedorSalida;
    RadioButton mujer;
    LinearLayout contenedorDatos;
    ArrayList<Datos> jugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jugadores = new ArrayList<Datos>();
        leer = findViewById(R.id.leer);
        escribir = findViewById(R.id.escribir);
        buttonNuevoJugador = findViewById(R.id.buttonNuevoJugador);
        contenedorSalida = findViewById(R.id.contenedorSalida);
        contenedorDatos = findViewById(R.id.contenedorDatos);

        leer.setOnClickListener(this);
        escribir.setOnClickListener(this);
        buttonNuevoJugador.setOnClickListener(this);


    }

    private void EscribirXML(ArrayList<Datos> datos) {
        FileOutputStream fout=null;
        XmlSerializer serializer = Xml.newSerializer();
        try {
            serializer.setOutput(fout, "UTF-8");
            serializer.startDocument(null,true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#ident-output",true);
            serializer.startTag(null, "juagdores");
            for (Datos x : datos) {
                EscribirTag(serializer, x);
            }
            serializer.endTag(null,"jugadores");
            serializer.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private XmlSerializer EscribirTag(XmlSerializer serializer, Datos datos) throws IOException {
        serializer.startTag(null, "jugador");
        serializer.startTag(null,"nombre");
        serializer.attribute(null,"sexo", datos.isMujer()?"Mujer":"hombre");
        serializer.text(datos.getNombre());
        serializer.endTag(null,"nombre");
        serializer.startTag(null, "puntos");
        serializer.text(Integer.toString(datos.getPuntos()));
        serializer.endTag(null,"puntos");
        serializer.endTag(null,"jugador");
        return serializer;

    }

    private void leerXML() {
        XmlPullParser parser = Xml.newPullParser();
        FileInputStream fin = null;

        try {
            fin= openFileInput("datos.xml");
            parser.setInput(fin, null);
            int evento = parser.getEventType();

            while (evento != XmlPullParser.END_DOCUMENT){
                switch (evento){
                    case XmlPullParser.START_DOCUMENT:
                        contenedorSalida.setText("");
                        break;
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("nombre")){
                            String sexo = parser.getAttributeValue(null,"sexo");
                            contenedorSalida.setText(contenedorSalida.getText() + "\nNombre: " +
                                    "" + parser.nextText() + "\nSEXO: " + sexo);
                        }
                        else if (parser.getName().equals("puntos")) {
                            contenedorSalida.setText(contenedorSalida.getText()+"\n" +"Puntos: "
                            + parser.nextText() + "\n\n");
                        }
                        break;
                }
                evento= parser.next();
            }
            fin.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonNuevoJugador) {
            nombre = findViewById(R.id.nombre);
            puntos = findViewById(R.id.puntuacion);
            mujer = findViewById(R.id.radiobuttonMujer);

            if(nombre.getText().toString().length()>0 && puntos.getText().toString().length()>0) {
                jugadores.add(new Datos(nombre.getText().toString(),
                        Integer.parseInt(puntos.getText().toString()),mujer.isChecked()));
                nombre.setText("");
                puntos.setText("");
                mujer.setChecked(false);
            }
            else
                Toast.makeText(this, "Debe rellenar los dos datos", Toast.LENGTH_LONG).show();
        }
        else if()

    }
}
