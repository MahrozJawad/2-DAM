package HiberTareas4v1;

import bdagenda.AgendaHibernateUtil;
import bdagenda.Tareas;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import packUtils.Menu;

public class HiberTareas4v1 {

    private static Session sesion;
    private static Scanner entrada = new Scanner(System.in);

    // ***************************************************
    // verSeries -> Mostrar datos de todos los series
    // ***************************************************
    public static void verTareas() {
        Query consulta = sesion.createQuery("FROM Tareas");
        List resultados = consulta.list();

        System.out.printf("Codigo Fecha        Descripción                            Completada\n");
        System.out.printf("------ ------------ -------------------------------------- --------\n");
        for (Object resultado : resultados) {
            Tareas t = (Tareas) resultado; // Cast
            System.out.printf("%6s %-20s %-30s %8s\n",
                    t.getCodigo(), t.getFecha(), t.getDescripcion(), t.getCompletada());
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
        sesion = AgendaHibernateUtil.getSessionFactory().openSession();
        Menu.Mostrar();
// Hibernate - Cerrar sesión
        sesion.close();
        AgendaHibernateUtil.closeSessionFactory();
    }

}
