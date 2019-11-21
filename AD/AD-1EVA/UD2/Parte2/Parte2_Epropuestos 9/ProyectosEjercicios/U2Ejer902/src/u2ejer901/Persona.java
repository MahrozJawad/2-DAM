/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2ejer901;

/**
 *
 * @author Mahroz
 */
import java.io.Serializable;

public class Persona implements Serializable{
    private String nombre;
    private String email;
    private int añoNacimiento;

    public Persona() {
    }

    public Persona(String nombre, String email, int añoNacimiento) {
        this.nombre = nombre;
        this.email = email;
        this.añoNacimiento = añoNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(int añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", email=" + email + ", a\u00f1oNacimiento=" + añoNacimiento + '}';
    }
    
}
