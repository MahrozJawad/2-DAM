package csvtojson;

import clases.Domicilio;
import clases.Persona;
import com.csvreader.CsvReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CSVtoJSON {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        // **************************************************
        // Cargar datos en objeto ArrayList de Personas
        // **************************************************
        ArrayList<Persona> personas = new ArrayList<>();
        
        CsvReader censo = new CsvReader("./datos/censo.csv", ',',  StandardCharsets.UTF_8);
        censo.setDelimiter('|');
        censo.setRecordDelimiter('\n');
        censo.readHeaders();
        while (censo.readRecord()) {
            Persona persona = new Persona();
                
            persona.setDni( censo.get("dni") ); 
            persona.setNombre( censo.get("nombre") ); 

            Domicilio dom = new Domicilio();
            dom.setCalle(censo.get("calle") ); 
            dom.setNumero(Integer.parseInt(censo.get("numero")) ); 
            dom.setCpostal(Integer.parseInt(censo.get("cpostal")) ); 
            dom.setPoblacion(censo.get("poblacion") ); 

            persona.setDomicilio( dom );
             
            personas.add(persona);
        }
        censo.close();
            
        
        // ***************************************************************************************
        // Mostrar por pantalla la lista de personas usando la serialización a String (toString()
        // ***************************************************************************************
        
        System.out.println(personas.toString());
            
        
        // **************************************************
        // Serializar Personas a JSON
        // **************************************************

        // Crear el GsonBuilder
        Gson gson = new GsonBuilder().setPrettyPrinting().create();        
        
        String personasListaString = gson.toJson(personas);
        
        String nomFile = "./datos/personas.json";
        BufferedWriter ficheroSalida = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nomFile), StandardCharsets.UTF_8));
        ficheroSalida.write(personasListaString);
        ficheroSalida.close();         
        
        
        
    }

}
