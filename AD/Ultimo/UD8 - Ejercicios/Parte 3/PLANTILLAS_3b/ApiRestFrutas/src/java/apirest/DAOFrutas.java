package apirest;

import clases.Frutas;
import java.util.ArrayList;
import java.util.List;


public class DAOFrutas {
    private static final List<Frutas> lista = new ArrayList<>();
    
    static  {
       lista.add(new Frutas("platano"));
       lista.add(new Frutas("pera"));
       lista.add(new Frutas("manzana"));
    }

    // Método para recuperar todos los datos
    public static List<Frutas> getAll() {
        return lista;
    }
    
    // Método para recuperar un dato determinado
    public static Frutas get(Frutas fruit) {
        if (lista.contains(fruit)) {
            return fruit;
        }  else {
            return null;
        }
    }
    
    // Método para añadir un dato a la base de datos
    public static void add(Frutas fruit) {
        lista.add(fruit);
    }
    
    // Método para eliminar un dato de la base de datos
    public static boolean delete(Frutas fruit) {
        if (lista.contains(fruit)) {
            lista.remove(fruit);
            return true;
        } else {
            return false;
        }
    }    
}
