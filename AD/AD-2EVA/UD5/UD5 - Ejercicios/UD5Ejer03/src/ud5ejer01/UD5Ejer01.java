
package ud5ejer01;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.sun.webkit.graphics.RenderTheme;
import java.util.Scanner;
import packUtils.Menu;

public class UD5Ejer01 {
    private static String fichero = "./datos/libros.db4o";
    
    public static void AnadirLibro() {
        ObjectContainer db = null;
        try {
            db = Db4o.openFile(fichero);
            Scanner e = new Scanner(System.in);
            
            System.out.print("Introduce el titulo: ");
            String titulo = e.nextLine();
            System.out.print("Introduce el autor: ");
            String autor = e.nextLine();
            System.out.print("Introduce numero de las páginas que lleva: ");
            int numPagina = e.nextInt();
            
            LibroLectura p1 = new LibroLectura(titulo, autor, numPagina);
            
            db.store(p1);
            db.commit();
            System.out.println("BD creada");
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
    
    public static void verLibros() {
        ObjectContainer db = null;
        try {
            db = Db4o.openFile(fichero);
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
    
    public static void main(String[] args) {
        Menu.Mostrar();
    }
}
