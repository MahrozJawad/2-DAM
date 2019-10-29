package lecturajaxb;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LecturaJAXB {

    public static void main(String[] args) throws JAXBException {

        // P1 - Unmarshaller de XML a libreria de libros
        JAXBContext context = JAXBContext.newInstance(Libreria.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Libreria libreria = (Libreria) unmarshaller.unmarshal(new File("./datos/libreria.xml"));

        // P2 - Mostrar libros de la librería
        System.out.println("Nombre: " + libreria.getNombre());
        ArrayList<Libro> libros = libreria.getLibros();

        for (Libro lib : libros) {
            System.out.println(lib.getIsbn() + " - " + lib.getTitulo() + " - " + lib.getAutor());
        }
    }
}
