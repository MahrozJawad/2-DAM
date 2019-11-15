package HiberSeries2v1;

import bdseriestv.Series;
import bdseriestv.bdseriestvHibernateUtil;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import packUtils.Menu;

public class HiberSeries2v1 {

    private static Session sesion;

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
        Query consulta = sesion.createQuery("FROM Series");
        List resultados = consulta.list();

        System.out.printf("Codigo  Tv       \n");
        System.out.printf("------ ----------\n");
        for (Object resultado : resultados) {
            Series serie = (Series) resultado; // Cast
            System.out.printf("%6s %40s \n",serie.getCadenastv().getCodigo(), serie.getCadenastv().getNombre());
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
