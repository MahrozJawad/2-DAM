package bdtelefood;
// Generated 20-nov-2019 23:42:29 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tiendas generated by hbm2java
 */
public class Tiendas  implements java.io.Serializable {


     private String idtienda;
     private String provincia;
     private Date creada;
     private String encargado;
     private Integer trabajadores;
     private Set emailses = new HashSet(0);

    public Tiendas() {
    }

	
    public Tiendas(String idtienda) {
        this.idtienda = idtienda;
    }
    public Tiendas(String idtienda, String provincia, Date creada, String encargado, Integer trabajadores, Set emailses) {
       this.idtienda = idtienda;
       this.provincia = provincia;
       this.creada = creada;
       this.encargado = encargado;
       this.trabajadores = trabajadores;
       this.emailses = emailses;
    }
   
    public String getIdtienda() {
        return this.idtienda;
    }
    
    public void setIdtienda(String idtienda) {
        this.idtienda = idtienda;
    }
    public String getProvincia() {
        return this.provincia;
    }
    
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public Date getCreada() {
        return this.creada;
    }
    
    public void setCreada(Date creada) {
        this.creada = creada;
    }
    public String getEncargado() {
        return this.encargado;
    }
    
    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
    public Integer getTrabajadores() {
        return this.trabajadores;
    }
    
    public void setTrabajadores(Integer trabajadores) {
        this.trabajadores = trabajadores;
    }
    public Set getEmailses() {
        return this.emailses;
    }
    
    public void setEmailses(Set emailses) {
        this.emailses = emailses;
    }




}


