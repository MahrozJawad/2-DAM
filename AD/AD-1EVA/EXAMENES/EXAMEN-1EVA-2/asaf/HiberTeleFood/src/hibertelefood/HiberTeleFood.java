package hibertelefood;

import bdtelefood.Emails;
import bdtelefood.EmailsId;
import bdtelefood.TfoodHibernateUtil;
import bdtelefood.Tiendas;
import org.hibernate.Session;
import org.hibernate.Query;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.hibernate.Transaction;
import packUtils.Menu;

public class HiberTeleFood {

    private static Session sesion;

    // ***************************************************
    // ver las Tiendas
    // ***************************************************
    public static void verTiendas() {

        System.out.println("Mostrando todas las tiendas y sus emails:");
        System.out.println("-----------------------------------------");
        Query consulta = sesion.createQuery("from Tiendas");

        List resultados = consulta.list();
        for (Object resultado : resultados) {
            Tiendas tienda = (Tiendas) resultado;
            System.out.println(tienda.getIdtienda() + " - " + tienda.getProvincia() + " - " + tienda.getEncargado() + " - " + tienda.getCreada() + " - " + tienda.getTrabajadores() + "). Emails: ");
            for (Object em : tienda.getEmailses()) {
                System.out.println(" => " + ((Emails) em).getId().getEmail());
            }

            System.out.println();
        }

    }

    // ***************************************************
    // Cambiar el Encargado
    // ***************************************************
    public static void cambiarEncargado() {
        String nombre;
        String codigo;
        Transaction trans = null;

        System.out.println("===========================");
        System.out.println("Cambiar encargado de Tienda");
        System.out.println("===========================");

        // Java - Leer datos
        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduzca código de la tienda: ");
        codigo = teclado.nextLine();

        Query consulta = sesion.createQuery("FROM Tiendas WHERE idtienda='" + codigo + "'");
        List resultados = consulta.list();
        if (resultados.size() != 1) {
            System.out.println("La tienda " + codigo + " no existe");
        } else {
            Tiendas tienda = (Tiendas) resultados.get(0);
            System.out.println("La tienda a modifica es '" + tienda.getIdtienda() + "-" + tienda.getProvincia() + "'");

            System.out.print("Introduzca el encargado: ");
            nombre = teclado.nextLine();

            if ((!nombre.isEmpty())) {
                // Guardar nuevos datos
                tienda.setEncargado(nombre);

                // Hibernate - Guardar datos del objeto directamente en la BD
                trans = sesion.beginTransaction();
                sesion.update(tienda);
                trans.commit();
            } else {
                System.out.println("Encargado de tienda modificado");
            }

        }

    }

    // ***************************************************
    // Añadir nuevo email a una tienda
    // ***************************************************
    public static void addEmailToTienda() {
        String mail;
        String codigo;
        Transaction trans = null;

        System.out.println("===========================");
        System.out.println("Añadir nuevo email a Tienda");
        System.out.println("===========================");

        // Java - Leer datos
        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduzca código de la tienda: ");
        codigo = teclado.nextLine();

        Query consulta = sesion.createQuery("FROM Tiendas WHERE idtienda='" + codigo + "'");
        List resultados = consulta.list();
        if (resultados.size() != 1) {
            System.out.println("La tienda " + codigo + " no existe");
        } else {
            Tiendas tienda = (Tiendas) resultados.get(0);
            System.out.println("La tienda a modifica es '" + tienda.getIdtienda() + "-" + tienda.getProvincia() + "'");

            System.out.print("Introduzca el nuevo email: ");
            mail = teclado.nextLine();

            if ((!mail.isEmpty())) {

                // Crear objeto emailId
                EmailsId emailId = new EmailsId(tienda.getIdtienda(),mail);
                    
                // Crear el objecto email de tienda a grabar
                Emails email = new Emails();
                email.setId(emailId);       // Asignamos objecto emailId
                email.setTiendas(tienda);   // Asignamos objecto tienda

                // Hibernate - Guardar datos del objeto directamente en la BD
                trans = sesion.beginTransaction();
                sesion.save(email);
                trans.commit();

                sesion.evict(tienda); // Lo elimina de la cache de lectura para que se actualice al volver a leerlo
                // sesion.clear():  // Elimina toda la cache
                    
            } else {
                System.out.println("Encargado de tienda modificado");
            }

        }

    }

    public static void main(String[] args) {
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

        sesion = TfoodHibernateUtil.getSessionFactory().openSession();

        Menu.Mostrar();

        sesion.close();
        TfoodHibernateUtil.closeSessionFactory();
    }
}
