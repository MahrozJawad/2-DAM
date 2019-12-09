
package ud7ejer203;

import java.io.Serializable;
import org.bson.Document;


public class Profesor implements Serializable{

    private String nombre;
    private String apellido;
    private String turno;

    public Profesor(String nombre, String apellido, String turno) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.turno = turno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Profesores{" + "nombre=" + nombre + ", apellido=" + apellido + ", turno=" + turno + '}';
    }
    
    public Document toDoc() {
        return new Document().append("nombre", nombre)
                .append("apellido", apellido)
                .append("turno", turno);
    }
    
}
