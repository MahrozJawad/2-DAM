
package hibernate1;

import bibliotecah.BiblioHibernateUtil;
import bibliotecah.Libros;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class Hibernate1 {

  private static Session sesion;
    
  // ***************************************************
  // verLibros -> Mostrar datos de todos los libros
  // ***************************************************
  public static void verLibros() { 

    System.out.println ("Mostrando todos los libros:"); 

    Query consulta = sesion.createQuery("from Libros"); 

    List resultados = consulta.list(); 
    for(Object resultado : resultados) { 
      Libros libro = (Libros) resultado; 
      System.out.println ( libro.getId() + ": " + libro.getTitulo() 
                + ", de " + libro.getAutor()); 
    } 

  } 

  // ***************************************************
  // MAIN 
  // ***************************************************  
  public static void main(String[] args) { 
    // Eliminar mensajes de control de Hibernate
    org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate"); 
    java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);     

    // Hibernate - Abrir sesión 
    sesion = BiblioHibernateUtil.getSessionFactory().openSession(); 
    
    verLibros(); 
    
    // Hibernate - Cerrar sesión 
    sesion.close();     
    BiblioHibernateUtil.closeSessionFactory();
  } 
    
}
