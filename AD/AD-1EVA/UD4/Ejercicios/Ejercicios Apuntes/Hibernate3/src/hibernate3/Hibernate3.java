package hibernate3;

import bibliotecah3.Autores;
import bibliotecah3.Biblio3HibernateUtil;
import bibliotecah3.Libros;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import packUtils.Menu;

public class Hibernate3 {

    private static Session sesion;

    public static void verLibros() {
        System.out.println("Mostrando todos los libros y sus autores:");
        System.out.println("-----------------------------------------");
        Query consulta = sesion.createQuery("from Libros");
        List resultados = consulta.list();
        for (Object resultado : resultados) {
            Libros libro = (Libros) resultado;
            System.out.println(libro.getTitulo() + " (" + libro.getId() + "). Autores: ");
            for (Object aut : libro.getAutoreses()) {
                System.out.println(" => " + ((Autores) aut).getNombre());
            }
            System.out.println();
        }
    }

// ***************************************************
// MAIN
// ***************************************************
    public static void main(String[] args) {
// Eliminar mensajes de control de Hibernate
        org.jboss.logging.Logger logger
                = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate")
                .setLevel(java.util.logging.Level.SEVERE);
// Hibernate - Abrir sesión
        sesion = Biblio3HibernateUtil.getSessionFactory().openSession();
        
        Menu.Mostrar();
        
        
// Hibernate - Cerrar sesión
        sesion.close();
        Biblio3HibernateUtil.closeSessionFactory();
    }

}
