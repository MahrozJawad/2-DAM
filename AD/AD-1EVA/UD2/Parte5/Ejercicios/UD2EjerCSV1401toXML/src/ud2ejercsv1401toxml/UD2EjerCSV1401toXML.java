package ud2ejercsv1401toxml;

import com.csvreader.CsvReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

public class UD2EjerCSV1401toXML {

    public static void main(String[] args) throws PropertyException, JAXBException {
        try {
            CsvReader products = new CsvReader("./datos/contenedor-frutas-50.csv", ',', StandardCharsets.UTF_8);
            products.setDelimiter(',');
            products.setRecordDelimiter('\n');
            products.readHeaders();
            
            FrutasJAXB f = new FrutasJAXB();
            ArrayList<Contenedor> contenedores = new ArrayList<>();
            
            while (products.readRecord()) {

                if (products.get("producto").equals("Naranjas")) {
                    String id = products.get("idContenedor"); //products.get(1)
                    String producto = products.get("producto"); //products.get(2)
                    String pais = products.get("pais"); //products.get(3)
                    String ciudad = products.get("ciudad"); //products.get(4)

                    Contenedor c = new Contenedor(id, producto, pais, ciudad);

                    contenedores.add(c);
                }

            }
            products.close();
            
            f.setContenedores(contenedores);

            JAXBContext context = JAXBContext.newInstance(FrutasJAXB.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(f, System.out);
// marshaller.marshal(libreria, new FileWriter("./datos/frutas.xml"));
            marshaller.marshal(f,
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream("./datos/frutas.xml", false),
                                        StandardCharsets.UTF_8)));
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
