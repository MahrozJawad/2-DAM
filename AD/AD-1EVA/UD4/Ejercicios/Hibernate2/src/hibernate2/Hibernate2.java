package hibernate2;

import bibliotecah2.Autores;
import bibliotecah2.Biblio2HibernateUtil;
import bibliotecah2.Libros;
import org.hibernate.Session;
import org.hibernate.Query;
import java.util.List;

public class Hibernate2 {

    private static Session sesion;
// verLibros

    public static void verLibros() {
        System.out.println("Mostrando todos los libros:");
        System.out.println("---------------------------");
        Query consulta = sesion.createQuery("from Libros");
        List resultados = consulta.list();
        for (Object resultado : resultados) {
            Libros libro = (Libros) resultado;
            System.out.println(libro.getId() + ": " + libro.getTitulo()
                    + ", de " + libro.getAutores().getNombre());
        }
    }

    public static void verAutores() {
        System.out.println("Mostrando todos los autores y sus libros:");
        System.out.println("-----------------------------------------");
        Query consulta = sesion.createQuery("from Autores");
        List resultados = consulta.list();
        for (Object resultado : resultados) {
            Autores autor = (Autores) resultado;
            System.out.println(autor.getNombre() + " (" + autor.getCod() + "). Libros: ");
            for (Object l : autor.getLibroses()) {
                System.out.println(" => " + ((Libros) l).getTitulo());
            }
            System.out.println();
        }
    }

// main
    public static void main(String[] args) {
        org.jboss.logging.Logger logger
                = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate")
                .setLevel(java.util.logging.Level.SEVERE);
        sesion = Biblio2HibernateUtil.getSessionFactory().openSession();
        //verLibros();
        //verAutores();
        sesion.close();
        Biblio2HibernateUtil.closeSessionFactory();
    }
}
