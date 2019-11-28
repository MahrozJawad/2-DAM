
package ejemplodb4o;

public class Persona {
private String nombre;
private String apellidos;
private int edad;
public Persona() {
}
public Persona(String nombre, String apellidos, int edad) {
this.nombre = nombre;
this.apellidos = apellidos;
this.edad = edad;
}
public String getNombre() {
return nombre;
}
public void setNombre(String value) {
nombre = value;
}
public String getApellidos() {
return apellidos;
}
public void setApellidos(String value) {
apellidos = value;
}
public int getEdad() {
return edad;
}
public void setEdad(int value) {
edad = value;
}
public String toString() {
return "Persona: " + "nombre = " + nombre + ", " +
"apellidos = " + apellidos + ", " + "edad = " + edad;
}
public boolean equals(Object otro) {
if (otro == this) return true;
if (!(otro instanceof Persona)) return false;
Persona other = (Persona) otro;
return (this.nombre.equals(other.nombre) &&
this.apellidos.equals(other.apellidos) && this.edad == other.edad);
}
}
