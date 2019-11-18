package HiberSeries3v1;

import bdseriestv.Cadenastv;
import bdseriestv.Series;
import bdseriestv.bdseriestvHibernateUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import packUtils.Menu;

public class HiberSeries3v1 {

    private static Session sesion;
    private static Scanner entrada = new Scanner(System.in);

    // ***************************************************
    // verSeries -> Mostrar datos de todos los series
    // ***************************************************
    public static void verSeries() {
        Query consulta = sesion.createQuery("FROM Series");
        List resultados = consulta.list();

        System.out.printf("Codigo Titulo                                   Tv         Duración\n");
        System.out.printf("------ ---------------------------------------- ---------- --------\n");
        for (Object resultado : resultados) {
            Series serie = (Series) resultado; // Cast
            System.out.printf("%6d %-40s %-10s %8d\n",
                    serie.getCodigo(), serie.getTitulo(), serie.getCadenastv().getNombre(), serie.getDuracion());
        }
    }

    public static void verCanalesTV() {
        Query consulta = sesion.createQuery("FROM Cadenastv");
        List resultados = consulta.list();

        System.out.printf("Codigo  Tv       \n");
        System.out.printf("------ ----------\n");
        for (Object resultado : resultados) {
            Cadenastv canal = (Cadenastv) resultado; // Cast
            System.out.printf("%6s %15s \n", canal.getCodigo(), canal.getNombre());
        }
    }

    public static void addCanal() {
        String id = "";
        String nombre;
        Set series = new HashSet(0);
        Transaction trans = null;
        System.out.println("===========================");
        System.out.println("Añadir un Canal");
        System.out.println("===========================");
        try {
// Java - Leer datos
            Scanner entrada = new Scanner(System.in);
            System.out.print("Introduzca el código del canal: ");
            id = entrada.nextLine();
            System.out.print("Introduzca el nombre: ");
            nombre = entrada.nextLine();
// Java - Cargar datos en un objeto cadenastv
            Cadenastv canal = new Cadenastv(id, nombre, series);
// Hibernate - Guardar datos del objeto directamente en la BD
            trans = sesion.beginTransaction();
            sesion.save(canal);
            trans.commit();
        } catch (NonUniqueObjectException e) {
// Ya existe un libro con ese Id
            System.out.println("El Id " + id + " ya existe");
            trans.rollback();
        }
    }

    public static void addSerie() {
        int duracion;
        String nombre, codigoCanal;
        Set series = new HashSet(0);
        Transaction trans = null;
        System.out.println("===========================");
        System.out.println("Añadir una Serie");
        System.out.println("===========================");

// Java - Leer datos
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca la duracion de la Serie: ");
        duracion = entrada.nextInt();
        System.out.print("Introduzca el nombre de la Serie: ");
        nombre = entrada.nextLine();
        nombre = entrada.nextLine();
        System.out.print("Introduzca el código canal de la Serie: ");
        codigoCanal = entrada.nextLine();

        Query consulta = sesion.createQuery("FROM Cadenastv WHERE codigo='" + codigoCanal + "'");
        List resultados = consulta.list();

        Series serie = new Series();
        serie.setDuracion(duracion);
        serie.setTitulo(nombre);
// Java - Cargar datos en un objeto cadenastv
        if (resultados.size() != 1) {
            System.out.println("El Autor " + codigoCanal + " no existe");
        } else {
            serie.setCadenastv((Cadenastv) resultados.get(0));
        }
// Hibernate - Guardar datos del objeto directamente en la BD
        trans = sesion.beginTransaction();
        sesion.save(serie);
        trans.commit();
    }

    public static void modificarSerie() {
        System.out.println("===========================");
        System.out.println("Modificar una Serie");
        System.out.println("===========================");

        System.out.print("Introduzca código de la Seire: ");
        int id = entrada.nextInt();

        Query consulta = sesion.createQuery("FROM Series WHERE codigo=" + id);
        List resultados = consulta.list();
        Series serieAModificar = (Series) resultados.get(0);

        System.out.print("La Serie a modificar es '" + serieAModificar.getTitulo() + "' con" + serieAModificar.getDuracion() + " minutos de duración");
        System.out.println();

        System.out.print("Introduzca el titulo: ");
        String titulo = entrada.nextLine();
        //esto lo escribo una vez más porque no sé por qué me salta y me pide la duración directamente.
        //titulo = entrada.nextLine();

        System.out.print("Introduzca la duración: ");
        int duracion = entrada.nextInt();

// Hibernate – Obtener libro a modificar
// Java – Modificar el libro
        serieAModificar.setTitulo(titulo);
        serieAModificar.setDuracion(duracion);
// Hibernate - Guardar datos del objeto directamente en la BD
        Transaction trans = sesion.beginTransaction();
        sesion.update(serieAModificar);
        trans.commit();
    }

    public static void modificarCanal() {
        System.out.println("===========================");
        System.out.println("Modificar un Canal");
        System.out.println("===========================");

        System.out.print("Introduzca código del Canal: ");
        String codigoCanal = entrada.nextLine();

        Query consulta = sesion.createQuery("FROM Cadenastv WHERE codigo='" + codigoCanal + "'");
        List resultados = consulta.list();
        Cadenastv canalAModificar = (Cadenastv) resultados.get(0);

        System.out.println("El canal de TV a modificar es '" + canalAModificar.getNombre() + "'");

        System.out.print("Introduzca el Nombre del canal: ");
        String nombre = entrada.nextLine();

// Hibernate – Obtener libro a modificar
// Java – Modificar el libro
        canalAModificar.setNombre(nombre);

// Hibernate - Guardar datos del objeto directamente en la BD
        Transaction trans = sesion.beginTransaction();
        sesion.update(canalAModificar);
        trans.commit();
    }

    public static void eliminarSerie() {
        System.out.println("===========================");
        System.out.println("Eliminar una Serie");
        System.out.println("===========================");
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("Introduzca código de la serie: ");
        int id = entrada.nextInt();
        
        Query consulta = sesion.createQuery("FROM Series WHERE id = " + id);
        List<Series> series = consulta.list();
        if (series.size() > 0) {
            System.out.println("La Serie a eliminar es '"+series.get(0).getTitulo()+"' con "+series.get(0).getDuracion()+" minutos de duración");
            System.out.println("¿Confirma su eliminación (S/N)?");
            
            String opcion = entrada.nextLine().toUpperCase();
            //esto lo escribo una vez más porque no sé por qué me salta:.
            //opcion = entrada.nextLine().toUpperCase();
            if (opcion.equals("S")) {
                Transaction trans = sesion.beginTransaction();
                sesion.delete(series.get(0));
                trans.commit();
                System.out.println("Serie eliminada");
                System.out.println("---------------------------------------");
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
        org.jboss.logging.Logger logger
                = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate")
                .setLevel(java.util.logging.Level.SEVERE);
// Hibernate - Abrir sesión
        sesion = bdseriestvHibernateUtil.getSessionFactory().openSession();
        Menu.Mostrar();
// Hibernate - Cerrar sesión
        sesion.close();
        bdseriestvHibernateUtil.closeSessionFactory();
    }

}
