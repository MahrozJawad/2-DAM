package HiberSeries2v2;

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

public class HiberSeries2v2 {

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
