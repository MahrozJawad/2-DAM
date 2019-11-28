
package ud5ejer01;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import java.util.Scanner;

public class UD5Ejer01 {

    public static void main(String[] args) {
        ObjectContainer db = null;
        try {
            db = Db4o.openFile("./datos/libros.db4o");
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
}
