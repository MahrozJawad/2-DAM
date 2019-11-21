
package hibernate1;

import bibliotecah.BiblioHibernateUtil;
import bibliotecah.Libros;
import java.util.List;
import java.util.Scanner;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Hibernate1 {

  private static Session sesion;
    
  // ***************************************************
  // verLibros -> Mostrar datos de todos los libros
  // ***************************************************
  public static void verLibros() { 

    System.out.println ("==========================="); 
    System.out.println ("Mostrando todos los libros:"); 
    System.out.println ("==========================="); 

    Query consulta = sesion.createQuery("from Libros"); 

    List resultados = consulta.list(); 
    for(Object resultado : resultados) { 
      Libros libro = (Libros) resultado; 
      System.out.println ( libro.getId() + ": " + libro.getTitulo() 
                + ", de " + libro.getAutor()); 
    } 

  } 
  
  public static void verLibrosSELECT() { 
    System.out.println ("==========================="); 
    System.out.println ("Ver libros con SELECT"); 
    System.out.println ("==========================="); 
      
      
    Query consulta = sesion.createQuery("SELECT id, autor FROM Libros");

    List<Object[]> resultados = consulta.list(); 
    for( Object[] dato : resultados) { 
      System.out.println ( (Integer) dato[0] ); 
      System.out.println ( (String) dato[1] ); 
    }  
  }  
  
  
  public static void verLibrosGROUPBY() { 
    System.out.println ("==========================="); 
    System.out.println ("Ver libros con GROUP BY"); 
    System.out.println ("==========================="); 
      
    Query consulta = 
           sesion.createQuery("SELECT autor, count(*) FROM Libros GROUP BY autor");

    List<Object[]> resultados = consulta.list(); 
    for( Object[] dato : resultados) { 
      System.out.println ( (String) dato[0] ); 
      System.out.println ( (Long) dato[1] ); 
    }  
  }  
  
    public static void anyadir() { 
      int id = 0 ;
      String titulo, autor;
      Transaction trans = null;
      
      System.out.println ("==========================="); 
      System.out.println ("Añadir un libro"); 
      System.out.println ("==========================="); 
    
      try {
        // Java - Leer datos
        Scanner teclado = new Scanner(System.in); 
        System.out.print("Introduzca el código del libro: "); 
        id = Integer.parseInt(teclado.nextLine()); 
        System.out.print("Introduzca el título: "); 
        titulo = teclado.nextLine(); 
        System.out.print("Introduzca el autor: "); 
        autor = teclado.nextLine(); 

        // Java - Cargar datos en un objeto Libro
        Libros libro = new Libros(id, titulo, autor); 

        // Hibernate - Guardar datos del objeto directamente en la BD
        trans = sesion.beginTransaction(); 
          sesion.save(libro); 
        trans.commit(); 
      } catch (NonUniqueObjectException e) {
        System.out.println("El Id "+id+" ya existe");          
        trans.rollback();
      }

    }  

    public static void modificarAutor(int idBuscado, String newAutor) { 
        System.out.println("==========================="); 
        System.out.println("Modificar un libro"); 
        System.out.println("==========================="); 

        // Hibernate – Obtener libro a modificar 
        Query consulta = sesion.createQuery("FROM Libros WHERE id="+idBuscado); 
        List resultados = consulta.list(); 
        Libros libroAModificar = (Libros) resultados.get(0); 

        // Java – Modificar el libro
        libroAModificar.setAutor(newAutor); 

        // Hibernate - Guardar datos del objeto directamente en la BD
        Transaction trans = sesion.beginTransaction();
          sesion.update(libroAModificar); 
        trans.commit(); 

    }    
    
    public static void borrarPorId(int IdBuscado) { 
        System.out.println("==========================="); 
        System.out.println("Eliminar un libro"); 
        System.out.println("==========================="); 
        
        Scanner teclado = new Scanner(System.in);
        Query consulta = sesion.createQuery("FROM Libros WHERE id = " + IdBuscado);
        List<Libros> libros = consulta.list(); 
        if (libros.size() > 0) { 
          System.out.println("¿Es este libro (S/N)? " + libros.get(0).getTitulo()); 
          String opcion = teclado.nextLine().toUpperCase(); 
          if (opcion.equals("S")) { 

            Transaction trans = sesion.beginTransaction();
              sesion.delete(libros.get(0)); 
            trans.commit(); 

            System.out.println("Libro borrado"); 
          } 
        } else { 
          System.out.println("No existe un libro con ese código"); 
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
    
    verLibrosSELECT();
    
    verLibrosGROUPBY();
    
    anyadir();
    
    modificarAutor(10,"Juan Ramirez");
    
    borrarPorId(10);
    
    // Hibernate - Cerrar sesión 
    sesion.close();     
    BiblioHibernateUtil.closeSessionFactory();
  } 
    
}
