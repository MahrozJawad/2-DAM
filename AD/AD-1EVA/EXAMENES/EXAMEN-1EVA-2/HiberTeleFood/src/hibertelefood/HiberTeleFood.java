package hibertelefood;

import bdtelefood.Emails;
import bdtelefood.TeleFoodHibernateUtil;
import bdtelefood.Tiendas;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import packUtils.Menu;

public class HiberTeleFood {

    private static Session sesion;
    
// ***************************************************
// verLibros -> Mostrar datos de todos los libros
// ***************************************************

    public static void MostrarTienda() {
        System.out.println("Mostrando todos los libros:");

        Query consulta = sesion.createQuery("from tiendas");
        List resultados = consulta.list();
        for (Object resultado : resultados) {
            Tiendas tienda = (Tiendas) resultado; // Cast
            System.out.printf(tienda.getIdtienda() + " - " + tienda.getEncargado() + ""
                    + " - " + tienda.getCreada() + " - " + tienda.getTrabajadores() + ").Emails: ");

            for (Object aut : tienda.getEmailses()) {
                //System.out.println(" => " + ((Emails) aut).getTiendas().);
            }
        }
    }

    public static void CambiaEncargado() {
        System.out.println("===========================");
        System.out.println("Modificar un libro");
        System.out.println("===========================");
        Scanner e = new Scanner(System.in);
        
        System.out.print("Introduce codigo: ");
        String id = e.nextLine();
        
        
// Hibernate – Obtener libro a modificar
        Query consulta = sesion.createQuery("FROM Tiendas WHERE idtienda=" + id);
        List resultados = consulta.list();
        Tiendas tiendaAModificar = (Tiendas) resultados.get(0);
// Java – Modificar el libro
System.out.println("La tienda a modificar es: " + tiendaAModificar.getEncargado());

System.out.println("Introduce el encargado:");
        String encargado = e.nextLine();
        tiendaAModificar.setEncargado(encargado);
// Hibernate - Guardar datos del objeto directamente en la BD
        Transaction trans = sesion.beginTransaction();
        sesion.update(tiendaAModificar);
        trans.commit();
    }
    
    public static void AñadirEmail() {
        String codigo;
        String email;
        Transaction trans = null;
        System.out.println("===========================");
        System.out.println("Añadir Participación de Actor en una Serie");
        System.out.println("===========================");

// Java - Leer datos
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca código de la tienda: ");
        codigo = entrada.nextLine();
        System.out.print("Emial nuevo: ");
        email = entrada.nextLine();

        Query consulta = sesion.createQuery("FROM tiendas WHERE codigo=" + codigo);
        List resultadosSeries = consulta.list();
        Tiendas s = (Tiendas) resultadosSeries.get(0);

        //s.getEmailses().add(email);

        sesion.update(s);

// Hibernate - Guardar datos del objeto directamente en la BD
        trans = sesion.beginTransaction();
        sesion.save(s);
        trans.commit();
        
        sesion.clear();
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
        sesion = TeleFoodHibernateUtil.getSessionFactory().openSession();

        Menu.Mostrar();
// Hibernate - Cerrar sesión
        sesion.close();
        TeleFoodHibernateUtil.closeSessionFactory();
    }
}
