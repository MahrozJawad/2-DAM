package lecturajaxb;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name="libreria")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"nombre","libros"})
public class Libreria {
    @XmlElement(name="nombre")
    private String nombre;
    @XmlElementWrapper(name="libros")
    @XmlElement(name="libro")
    private ArrayList<Libro> libros = new ArrayList<>();
    // **********************************
    // Constructor
    public Libreria() {
    }
    // getters y setters
    public String getNombre() {
         return nombre;
    }
    public void setNombre(String nombre) {
         this.nombre = nombre;
    }
    public ArrayList<Libro> getLibros() {
        return libros;
    }
    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }
}