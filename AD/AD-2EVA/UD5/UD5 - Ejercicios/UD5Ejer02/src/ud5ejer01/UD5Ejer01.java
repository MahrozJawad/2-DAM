
package ud5ejer01;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.Scanner;

public class UD5Ejer01 {

    public static void main(String[] args) {
        ObjectContainer db = null;
        try {
            db = Db4o.openFile("./datos/libros.db4o");
// Mostrar número de registros
            System.out.println("BD con "
                    + db.queryByExample(new LibroLectura(null, null, 0)).size() + " registros");
// Buscamos todos los "Juan" (el campo con valor null o 0 no se filtra)
            ObjectSet<LibroLectura> libros = db.queryByExample(new LibroLectura(null, null, 0));
            while (libros.hasNext()) {
                System.out.println(libros.next());
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
