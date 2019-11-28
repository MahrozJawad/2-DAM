package ud5ejer01;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.sun.webkit.graphics.RenderTheme;
import java.util.List;
import java.util.Scanner;
import packUtils.Menu;

public class UD5Ejer01 {

    private static String fichero = "./datos/libros.db4o";
    private static Scanner e = new Scanner(System.in);

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

    public static void VerLibros() {
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

    public static void BuscarLibros() {
        ObjectContainer db = null;
        try {
            System.out.print("Introduce una palabra para buscar: ");
            String palabra = e.nextLine();

            db = Db4o.openFile(fichero);
// Buscamos las personas de más de 17 años
            List<LibroLectura> libros = db.query(new Predicate<LibroLectura>() {
                @Override
                public boolean match(LibroLectura candidato) {
                    return candidato.getTitulo().contains(palabra);
                }
            });
            for (LibroLectura libro : libros) {
                System.out.println(libro);
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static void BuscarLibrosPorCantidadDePaginas() {
        ObjectContainer db = null;
        try {
            System.out.print("Introduce cantidad de páginas del libro: ");
            int cantidad = e.nextInt();

            db = Db4o.openFile(fichero);
// Buscamos las personas de más de 17 años
            List<LibroLectura> libros = db.query(new Predicate<LibroLectura>() {
                @Override
                public boolean match(LibroLectura candidato) {
                    return candidato.getNumPaginas() > cantidad;
                }
            });
            for (LibroLectura libro : libros) {
                System.out.println(libro);
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static void BuscarLibrosPorTituloOAutor() {
        ObjectContainer db = null;
        try {
            System.out.print("Introduce una palabra para buscar: ");
            String palabra = e.nextLine().toUpperCase();

            db = Db4o.openFile(fichero);
// Buscamos las personas de más de 17 años
            List<LibroLectura> libros = db.query(new Predicate<LibroLectura>() {
                @Override
                public boolean match(LibroLectura candidato) {
                    return candidato.getTitulo().toUpperCase().contains(palabra) || candidato.getAutor().toUpperCase().contains(palabra);
                }
            });
            for (LibroLectura libro : libros) {
                System.out.println(libro);
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static void ModificarNumeroPagina() throws Exception {
        ObjectContainer db = null;
        try {
            db = Db4o.openFile("./datos/personas.db4o");
            System.out.println("Introduce el título del libro: ");
            String titulo = e.nextLine();
            
            System.out.println("Introduce la cantidad de paginas del libro a modificar: ");
            int cantidad = e.nextInt();
            
// Vamos a modificar la edad de la ficha de "A B"
            ObjectSet<LibroLectura> listamod = db.queryByExample(new LibroLectura(titulo, null, 0));
            if (listamod.hasNext()) {
                LibroLectura l = (LibroLectura) listamod.next();
                l.setNumPaginas(cantidad);
                db.store(l);
                db.commit();
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
