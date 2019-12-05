package Mongo09Update;

import java.io.Serializable;
import org.bson.Document;

public class Asignatura implements Serializable {

    private String asignatura;
    private String profesor;
    private int horas;

    // Constructores 
    public Asignatura() {
    }

    public Asignatura(String asignatura, String profesor, int horas) {
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.horas = horas;
    }
    
    // getters y setters
    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    @Override
    public String toString() {
        return "Asignatura{" + "asignatura=" + asignatura + ", profesor=" + profesor + ", horas=" + horas + '}';
    }
    
    
    public Document toDoc() {
	return new Document().append("nombre",asignatura).append("profesor",profesor).append("horas",horas);
    }

}


