package lecturajaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
@XmlRootElement(name="libro")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"isbn","titulo","autor"})
public class Libro {
    @XmlAttribute(name="isbn")
    private String isbn;
    @XmlElement(name="titulo")
    private String titulo;
    @XmlElement(name="autor")
    private String autor;
    // **********************************
    // Constructor
    public Libro() {
    }
    // getters y setters
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
}