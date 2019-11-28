package com.example.examen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ListarReparaciones extends AppCompatActivity {

    protected ArrayList<Reparacion> lista;
    private EditText fecha;
    private TextView id;
    private TextView tecnico;
    private FloatingActionButton btnAdd;

    RecyclerView recyclerView;
    Adaptador adaptador;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anyadir);

        id = findViewById(R.id.id);
        fecha = findViewById(R.id.fecha);
        tecnico = findViewById(R.id.tecnico);
        btnAdd = findViewById(R.id.btnAdd);

        File f = new File("reparaciones.xml");

        if(f.exists()) {
            leerXML();
        }
        else
        {
            añadirlista();
            escribirXML();
        }


        recyclerView = findViewById(R.id.recycler);
        adaptador=new Adaptador(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adaptador.setLongClick(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar snackbar = Snackbar.make(findViewById(R.id.activity), "Seguro que quieres borrar el elemento", Snackbar.LENGTH_INDEFINITE);
                snackbar.setActionTextColor(Color.GREEN);
                snackbar.setAction("Aceptar", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lista.remove(adaptador.DevelvePosicion());
                        escribirXML();
                    }
                });
                snackbar.show();
                return false;
            }
        });
        recyclerView.setAdapter(adaptador);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(getApplicationContext(), Anyadir.class);
                startActivity(i);
            }
        });
    }

    public  void añadirlista()
    {
        lista.add(new Reparacion("1","Ana Sogor","01/11/2019","05/11/2019","El disco duro roto, cambiado por uno nuevo"));
        lista.add(new Reparacion("2","Ana Sogor","01/11/2019","02/11/2019","Se ha instalado el sistema operativo de nuevo"));
        lista.add(new Reparacion("3","Sofia Nieto","03/11/2019","05/11/2019","Problemas con la RAM, se ha sustituido"));
        lista.add(new Reparacion("4","Luis García","04/11/2019","","Problemas con la placa base, a espera de que llegue la nueva"));
        lista.add(new Reparacion("5","Luis García","04/11/2014","05/11/2019","Se ha liberado espacio en disco, y se ha añadido RAM"));
    }

    private void escribirXML() {
        FileOutputStream fout = null;
        try {
            fout = openFileOutput("reparaciones.xml", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XmlSerializer serializer= Xml.newSerializer();
        try {
            serializer.setOutput(fout, "UTF-8");
            serializer.startDocument(null, true);
            serializer.startTag(null, "Reparaciones");
            for (int i=0 ; i < lista.size(); i++) {


                serializer.startTag(null,"reparacion");
                serializer.attribute(null,"fechaEntrada", lista.get(i).getFchEntrada());
                serializer.endTag(null,"fechaEntrada");
                serializer.startTag(null, "Codigo");
                serializer.text( lista.get(i).getCodigo());
                serializer.endTag(null, "Codigo");

                serializer.startTag(null,"Tecnico");
                serializer.attribute(null,"fechaSalida", lista.get(i).getFchSolucion());
                serializer.endTag(null,"fechaSalida");
                serializer.startTag(null, "Comentarios");
                serializer.text( lista.get(i).getComentarios());
                serializer.endTag(null,"Comentarios");
            }
            serializer.endTag(null, "Reparaciones");
            serializer.endDocument();
            serializer.flush();
            fout.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    
    private void leerXML() {
        XmlPullParser parser = Xml.newPullParser();
        FileInputStream fin = null;
        
        try {
            fin=openFileInput("reparaciones.xml");
            parser.setInput(fin, null);
            
            int evento = parser.getEventType();
            
            Reparacion aux = null;

            lista = null;

            while (evento!=XmlPullParser.END_DOCUMENT) {
                switch (evento) {
                    case XmlPullParser.START_DOCUMENT:
                        lista = new ArrayList<Reparacion>();
                        break;
                    case  XmlPullParser.START_TAG:
                        if(parser.getName().equals("Reparacion")) {
                            aux = new Reparacion();
                            aux.setFchEntrada(parser.getAttributeValue(null, "FechaEntrada"));
                        }
                        else
                            if(parser.getName().equals("Codigo")) {
                                aux.setCodigo(parser.nextText());
                            }
                        else
                            if(parser.getName().equals("Tecnico")) {
                                aux.setFchSolucion(parser.getAttributeValue(null,"FechaSalida"));
                                aux.setTecnico(parser.nextText());
                            }
                        else
                            if(parser.getName().equals("Comentarios")) {
                                aux.setComentarios(parser.nextText());
                            }
                }
            }
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
