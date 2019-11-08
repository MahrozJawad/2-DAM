package hiberseries1v1;

import bdseries.BDSeriesHibernateUtil;
import bdseries.Series;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import packUtils.Menu;

public class HiberSeries1v1 {

    private static Session sesion;

    // ***************************************************
    // verSeries -> Mostrar datos de todos los series
    // ***************************************************
    public static void verSeries() {
        Query consulta = sesion.createQuery("from Series");
        List resultados = consulta.list();

        System.out.printf("Codigo Titulo                                   Tv         Duración\n");
        System.out.printf("------ ---------------------------------------- ---------- --------\n");
        for (Object resultado : resultados) {
            Series serie = (Series) resultado; // Cast
            System.out.printf("%6d %-40s %-10s %8d\n",
                    serie.getCodigo(), serie.getTitulo(), serie.getTv(), serie.getDuracion());
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
        sesion = BDSeriesHibernateUtil.getSessionFactory().openSession();

        Menu.Mostrar();

        sesion.close();
        BDSeriesHibernateUtil.closeSessionFactory();

    }

}
