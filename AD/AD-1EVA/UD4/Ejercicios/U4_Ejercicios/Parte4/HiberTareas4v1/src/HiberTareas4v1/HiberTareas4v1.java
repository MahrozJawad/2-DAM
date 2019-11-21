package HiberTareas4v1;

import bdagenda.AgendaHibernateUtil;
import bdagenda.Tareas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.hibernate.criterion.Projections.id;
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

    public static void verTareasPendiente() {
        Query consulta = sesion.createQuery("FROM Tareas");
        List resultados = consulta.list();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
// Obtiene la fecha de hoy
        Calendar fechaDesde = Calendar.getInstance();
// Obtiene la fecha de dentro de 3 días
        Calendar fechaHasta = Calendar.getInstance();
        fechaHasta.add(Calendar.DAY_OF_YEAR, 3);
        
        System.out.println("===========================");
        System.out.println("Tareas pendientes desde: " + formatter.format(fechaDesde.getTime()) + " hasta " + formatter.format(fechaHasta.getTime()) + "");
        System.out.println("===========================");

        System.out.printf("Codigo Fecha        Descripción                            Completada\n");
        System.out.printf("------ ------------ -------------------------------------- --------\n");
        
        for (Object resultado : resultados) {
            Tareas t = (Tareas) resultado; // Cast
            Calendar c = Calendar.getInstance();
            c.setTime(t.getFecha());
            if ((c.getTime().getTime() >= fechaDesde.getTime().getTime() && c.getTime().getTime() <= fechaHasta.getTime().getTime()) && !t.getCompletada()) {
                System.out.printf("%6s %-20s %-30s %8s\n",
                        t.getCodigo(),formatter.format(t.getFecha()) , t.getDescripcion(), t.getCompletada());
            }

        }
    }
    public static void buscarTareasPorDescripcion() {
        Query consulta = sesion.createQuery("FROM Tareas");
        List resultados = consulta.list();
        String palabra = "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.print("Introduzca la descripción: ");
        palabra = entrada.nextLine();
        
        System.out.println("===========================");
        System.out.println("Buscar Tareas por descripcion");
        System.out.println("===========================");

        System.out.printf("Codigo Fecha        Descripción                            Completada\n");
        System.out.printf("------ ------------ -------------------------------------- --------\n");

        for (Object resultado : resultados) {
            Tareas t = (Tareas) resultado; // Cast
            Calendar c = Calendar.getInstance();
            c.setTime(t.getFecha());
            if (t.getDescripcion().contains(palabra)) {
                System.out.printf("%6s %-20s %-30s %8s\n",
                        t.getCodigo(),formatter.format(t.getFecha()) , t.getDescripcion(), t.getCompletada());
            }

        }
    }

    public static void addTarea() throws ParseException {
        String fecha, descripcion, estaCompletada;
        boolean estaComp = false;
        Transaction trans = null;
        System.out.println("===========================");
        System.out.println("Añadir una Tarea");
        System.out.println("===========================");

// Java - Leer datos
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca la fecha (AAAA-MM-DD): ");
        fecha = entrada.nextLine();
        System.out.print("Introduzca la descripcion: ");
        descripcion = entrada.nextLine();
        do {
            System.out.print("Introduzca si está completada (SI/NO): ");
            estaCompletada = entrada.nextLine();
            if (estaCompletada.equals("SI")) {
                estaComp = true;
                break;
            } else if (estaCompletada.equals("NO")) {
                estaComp = false;
                break;
            }
        } while (true);

// Java - Cargar datos en un objeto cadenastv
        Tareas tarea = new Tareas(new SimpleDateFormat("yyyy-MM-dd").parse(fecha), descripcion, estaComp);
// Hibernate - Guardar datos del objeto directamente en la BD
        trans = sesion.beginTransaction();
        sesion.save(tarea);
        trans.commit();
    }

    // ***************************************************
// MAIN
// ***************************************************
    public static void main(String[] args) throws ParseException {
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
