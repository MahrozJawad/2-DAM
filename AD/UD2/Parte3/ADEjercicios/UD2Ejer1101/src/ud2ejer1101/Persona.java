/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud2ejer1101;

import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable {
    private String nombre;
    private String email;
    private int añoNacimiento;
    private int edad;

    public Persona() {
    }

    public Persona(String nombre, String email, int edad) {
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
    }

    

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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
        return "Persona{" + "nombre=" + nombre + ", email=" + email + ", a\u00f1oNacimiento=" + (2019 - edad) + ", edad=" + edad + '}';
    }
}
