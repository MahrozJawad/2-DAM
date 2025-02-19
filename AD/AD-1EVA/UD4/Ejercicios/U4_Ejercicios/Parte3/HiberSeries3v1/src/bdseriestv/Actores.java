package bdseriestv;
// Generated 16-nov-2019 2:48:01 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Actores generated by hbm2java
 */
public class Actores  implements java.io.Serializable {


     private String idactor;
     private String nombre;
     private String apellidos;
     private Set serieses = new HashSet(0);

    public Actores() {
    }

	
    public Actores(String idactor) {
        this.idactor = idactor;
    }
    public Actores(String idactor, String nombre, String apellidos, Set serieses) {
       this.idactor = idactor;
       this.nombre = nombre;
       this.apellidos = apellidos;
       this.serieses = serieses;
    }
   
    public String getIdactor() {
        return this.idactor;
    }
    
    public void setIdactor(String idactor) {
        this.idactor = idactor;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public Set getSerieses() {
        return this.serieses;
    }
    
    public void setSerieses(Set serieses) {
        this.serieses = serieses;
    }




}


