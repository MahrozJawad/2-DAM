package ud2ejerjson1204fromxml;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class UD2EjerJson1204fromXML {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {

        DecimalFormat df = new DecimalFormat("#.00");
        
        JAXBContext jAXBContext = JAXBContext.newInstance(Alumnos.class);
        Unmarshaller unmarshaller = jAXBContext.createUnmarshaller();

        Alumnos todosAlumnos = (Alumnos) unmarshaller.unmarshal(new File("./datos/alumnos.xml"));

        ArrayList<Alumno> alumno = todosAlumnos.getAlumnos();

        JsonArrayBuilder jsonArrayB = Json.createArrayBuilder();
        for (Alumno obj : alumno) {
            JsonObjectBuilder jsonOB = Json.createObjectBuilder();
            jsonOB.add("id", obj.getId());
            jsonOB.add("nombre", obj.getNombre());
            jsonOB.add("localidad", obj.getLocalidad());
            jsonOB.add("notas", obj.getNotas().toString());

            ArrayList<Float> n = obj.getNotas().getNota();
            JsonArrayBuilder jsonNotas = Json.createArrayBuilder();
            for (double nota : n) {
                jsonNotas.add(df.format(nota));
            }
            JsonArray arrayNotas = jsonNotas.build();
            jsonOB.add("notas", arrayNotas);
            
            jsonArrayB.add(jsonOB);
        }
        JsonArray arrayJ = jsonArrayB.build();

        //prettyPrinter
        JsonWriterFactory jsonFactory = Json.createWriterFactory(
                Collections.singletonMap(JsonGenerator.PRETTY_PRINTING, true)
        );
        JsonWriter jsonW = jsonFactory.createWriter(new FileOutputStream("./datos/salida.json"));
        jsonW.writeArray(arrayJ);
        jsonW.close();

    }

}
