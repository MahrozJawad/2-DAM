package EscrituraJAXB;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class EscrituraJAXB {

    public static void main(String[] args) throws JAXBException, IOException {
// P1 - Carga de objectos libros en libreria
        Libreria libreria = new Libreria();
        libreria.setNombre("Mi librería");
        ArrayList<Libro> libros = new ArrayList<>();
        Libro libro = new Libro();
        libro.setIsbn("123456789");
        libro.setTitulo("Libro 1");
        libro.setAutor("Autor 1");
        libros.add(libro);
        libro = new Libro();
        libro.setIsbn("023456789");
        libro.setTitulo("Libro 2");
        libro.setAutor("Autor 3");
        libros.add(libro);
        libreria.setLibros(libros);
// P2 - Marshaller libreria de libros a --> arhivo de XML
        JAXBContext context = JAXBContext.newInstance(Libreria.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(libreria, System.out);
// marshaller.marshal(libreria, new FileWriter("./datos/miLibreria.xml"));
        marshaller.marshal(libreria,
                new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream("./datos/miLibreria.xml", false),
                                StandardCharsets.UTF_8)));
        System.out.println("Archivo XML generado");
        System.out.println(libreria.getNombre());
    }
}
