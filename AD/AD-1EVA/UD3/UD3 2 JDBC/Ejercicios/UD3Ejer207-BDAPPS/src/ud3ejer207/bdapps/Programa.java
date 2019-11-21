
package ud3ejer207.bdapps;

public class Programa {

    private int codigo;
    private String nombre;
    private String carpeta;

    public Programa(int codigo, String nombre, String carpeta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.carpeta = carpeta;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCarpeta() {
        return carpeta;
    }

    @Override
    public String toString() {
        return '{' + "codigo:" + codigo + ", nombre:" + nombre + ", carpeta:" + carpeta + '}';
    }

   
    
    
    
    
}
