
package clases;

import java.io.Serializable;


public class Persona implements Serializable {
    String dni;
    String nombre;
    Domicilio domicilio;

    public Persona() {
    }

    public Persona(String dni, String nombre, Domicilio domicilio) {
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Persona{" + "dni=" + dni + ", nombre=" + nombre + ", domicilio=" + domicilio + "}\n";
    }
    
    
    
}
